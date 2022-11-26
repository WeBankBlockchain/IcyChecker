/*
 * Copyright (C) 2009-2012 University of Freiburg
 *
 * This file is part of SMTInterpol.
 *
 * SMTInterpol is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SMTInterpol is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SMTInterpol.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.uni_freiburg.informatik.ultimate.logic;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * A logging script variant.  This is actually a wrapper around a concrete
 * implementation of the {@link Script} interface that produces an interaction
 * file in (almost) SMTLIB 2 compliant format.  We still have some extra
 * commands like "simplify", "reset", or "get-interpolants".
 * @author Juergen Christ
 */
public class LoggingScript implements Script {

	/**
	 * The actual script.
	 */
	private final Script mScript;
	/**
	 * The interaction log writer.
	 */
	private final PrintWriter mPw;

	/**
	 * The auxiliary class to print terms and sorts.
	 */
	private final PrintTerm mTermPrinter = new PrintTerm();

	/**
	 * Common subexpression elimination support if requeste by user. Will be
	 * <code>null</code> if cse should not be performed.
	 */
	private final FormulaLet mLetter;

	/**
	 * Create a new script logging the commands by the user.  Most commands
	 * are not supported, e.g., checkSat always returns unknown.  Furthermore,
	 * common subexpression elimination is not used in the output.
	 * @param file      The name of the logging file (should end in .smt2).
	 * @param autoFlush Automatically flush the output stream after every
	 *                  command.
	 * @throws FileNotFoundException If the file cannot be opened.
	 */
	public LoggingScript(final String file, final boolean autoFlush)
		throws FileNotFoundException {
		this (new NoopScript(), file, autoFlush);
	}

	/**
	 * Create a new script logging the commands by the user.  Most commands
	 * are not supported, e.g., checkSat always returns unknown.
	 * This constructor can be used to set up
	 * logging using common subexpression elimination.
	 *
	 * @param file      The name of the logging file (should end in .smt2).
	 * @param autoFlush Automatically flush the output stream after every
	 *                  command.
	 * @param useCSE    Use common subexpression elimination in output
	 *                  (introduces let terms)
	 * @throws FileNotFoundException If the file cannot be opened.
	 */
	public LoggingScript(final String file, final boolean autoFlush, final boolean useCSE)
			throws FileNotFoundException {
		this (new NoopScript(), file, autoFlush, useCSE);
	}

	/**
	 * Create a new script logging the interaction between the user and the
	 * wrapped script into a file.  This constructor sets up logging to not use
	 * common subexpression elimination.
	 * @param script    The wrapped script.
	 * @param file      The name of the logging file (should end in .smt2).
	 * @param autoFlush Automatically flush the output stream after every
	 *                  command.
	 * @throws FileNotFoundException If the file cannot be opened.
	 */
	public LoggingScript(final Script script, final String file, final boolean autoFlush)
		throws FileNotFoundException {
		this(script, file, autoFlush, false);
	}

	/**
	 * Create a new script logging the interaction between the user and the
	 * wrapped script into a file.  This constructor can be used to set up
	 * logging using common subexpression elimination.
	 * @param script    The wrapped script.
	 * @param file      The name of the logging file (should end in .smt2).
	 * @param autoFlush Automatically flush the output stream after every
	 *                  command.
	 * @param useCSE    Use common subexpression elimination in output
	 *                  (introduces let terms)
	 * @throws FileNotFoundException If the file cannot be opened.
	 */
	public LoggingScript(final Script script, final String file, final boolean autoFlush,
			final boolean useCSE)
		throws FileNotFoundException {
		mScript = script;
		OutputStream out;
		if (file.equals("<stdout>")) {
			out = System.out;
		} else if (file.equals("<stderr>")) {
			out = System.err;
		} else {
			out = new FileOutputStream(file);
		}
		mPw = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(out)), autoFlush);
		mLetter = useCSE ? new FormulaLet() : null;
	}

	private final Term formatTerm(final Term input) {
		return mLetter == null ? input : new FormulaLet().let(input);
	}

	@Override
	public void setLogic(final String logic)
		throws UnsupportedOperationException, SMTLIBException {
		mPw.println("(set-logic " + logic + ")");
		mScript.setLogic(logic);
	}

	@Override
	public void setLogic(final Logics logic)
		throws UnsupportedOperationException, SMTLIBException {
		mPw.println("(set-logic " + logic.name() + ")");
		mScript.setLogic(logic);
	}

	@Override
	public void setOption(final String opt, final Object value)
		throws UnsupportedOperationException, SMTLIBException {
		mPw.print("(set-option ");
		mPw.print(opt);
		mPw.print(' ');
		mPw.print(PrintTerm.quoteObjectIfString(value));
		mPw.println(")");
		mScript.setOption(opt, value);
	}

	@Override
	public void setInfo(final String info, final Object value) {
		mPw.print("(set-info ");
		mPw.print(info);
		mPw.print(' ');
		mPw.print(PrintTerm.quoteObjectIfString(value));
		mPw.println(")");
		mScript.setInfo(info, value);
	}

	@Override
	public void declareSort(final String sort, final int arity) throws SMTLIBException {
		mPw.print("(declare-sort ");
		mPw.print(PrintTerm.quoteIdentifier(sort));
		mPw.print(' ');
		mPw.print(arity);
		mPw.println(")");
		mScript.declareSort(sort, arity);
	}

	@Override
	public void defineSort(final String sort, final Sort[] sortParams, final Sort definition)
		throws SMTLIBException {
		mPw.print("(define-sort ");
		mPw.print(PrintTerm.quoteIdentifier(sort));
		mPw.print(" (");
		String sep = "";
		for (final Sort p : sortParams) {
			mPw.print(sep);
			mTermPrinter.append(mPw, p);
			sep = " ";
		}
		mPw.print(") ");
		mTermPrinter.append(mPw, definition);
		mPw.println(")");
		mScript.defineSort(sort, sortParams, definition);
	}

	@Override
	public void declareFun(final String fun, final Sort[] paramSorts, final Sort resultSort)
		throws SMTLIBException {
		mPw.print("(declare-fun ");
		mPw.print(PrintTerm.quoteIdentifier(fun));
		mPw.print(" (");
		String sep = "";
		for (final Sort p : paramSorts) {
			mPw.print(sep);
			mTermPrinter.append(mPw, p);
			sep = " ";
		}
		mPw.print(") ");
		mTermPrinter.append(mPw, resultSort);
		mPw.println(")");
		mScript.declareFun(fun, paramSorts, resultSort);
	}

	@Override
	public void defineFun(final String fun, final TermVariable[] params, final Sort resultSort,
			final Term definition) throws SMTLIBException {
		mPw.print("(define-fun ");
		mPw.print(PrintTerm.quoteIdentifier(fun));
		mPw.print(" (");
		String sep = "(";
		for (final TermVariable t : params) {
			mPw.print(sep);
			mPw.print(t);
			mPw.print(' ');
			mTermPrinter.append(mPw, t.getSort());
			mPw.print(')');
			sep = " (";
		}
		mPw.print(") ");
		mTermPrinter.append(mPw, resultSort);
		mPw.print(' ');
		mTermPrinter.append(mPw, formatTerm(definition));
		mPw.println(")");
		mScript.defineFun(fun, params, resultSort, definition);
	}

	@Override
	public void push(final int levels) throws SMTLIBException {
		mPw.println("(push " + levels + ")");
		mScript.push(levels);
	}

	@Override
	public void pop(final int levels) throws SMTLIBException {
		mPw.println("(pop " + levels + ")");
		mScript.pop(levels);
	}

	@Override
	public LBool assertTerm(final Term term) throws SMTLIBException {
		mPw.print("(assert ");
		mTermPrinter.append(mPw, formatTerm(term));
		mPw.println(")");
		return mScript.assertTerm(term);
	}

	@Override
	public LBool checkSat() throws SMTLIBException {
		mPw.println("(check-sat)");
		return mScript.checkSat();
	}

	@Override
	public LBool checkSatAssuming(final Term... assumptions) throws SMTLIBException {
		mPw.print("(check-sat-assuming (");
		String sep = "";
		for (final Term t : assumptions) {
			mPw.print(sep);
			mTermPrinter.append(mPw, formatTerm(t));
			sep = " ";
		}
		mPw.println("))");
		return mScript.checkSatAssuming(assumptions);
	}

	@Override
	public Term[] getAssertions() throws SMTLIBException {
		mPw.println("(get-assertions)");
		return mScript.getAssertions();
	}

	@Override
	public Term getProof() throws SMTLIBException,
			UnsupportedOperationException {
		mPw.println("(get-proof)");
		return mScript.getProof();
	}

	@Override
	public Term[] getUnsatCore() throws SMTLIBException,
			UnsupportedOperationException {
		mPw.println("(get-unsat-core)");
		return mScript.getUnsatCore();
	}

	@Override
	public Map<Term, Term> getValue(final Term[] terms) throws SMTLIBException,
			UnsupportedOperationException {
		mPw.print("(get-value (");
		String sep = "";
		for (final Term t : terms) {
			mPw.print(sep);
			mTermPrinter.append(mPw, formatTerm(t));
			sep = " ";
		}
		mPw.println("))");
		return mScript.getValue(terms);
	}

	@Override
	public Assignments getAssignment() throws SMTLIBException,
			UnsupportedOperationException {
		mPw.println("(get-assignment)");
		return mScript.getAssignment();
	}

	@Override
	public Object getOption(final String opt) throws UnsupportedOperationException {
		mPw.println("(get-option " + opt + ")");
		return mScript.getOption(opt);
	}

	@Override
	public Object getInfo(final String info) throws UnsupportedOperationException {
		mPw.println("(get-info " + info + ")");
		return mScript.getInfo(info);
	}

	@Override
	public Term simplify(final Term term) throws SMTLIBException {
		mPw.print("(simplify ");
		mTermPrinter.append(mPw, term);
		mPw.println(")");
		return mScript.simplify(term);
	}

	@Override
	public void reset() {
		mPw.println("(reset)");
		mScript.reset();
	}

	@Override
	public Term[] getInterpolants(final Term[] partition) throws SMTLIBException,
			UnsupportedOperationException {
		mPw.print("(get-interpolants");
		for (final Term t : partition) {
			mPw.print(' ');
			mTermPrinter.append(mPw, t);
		}
		mPw.println(')');
		return mScript.getInterpolants(partition);
	}

	// [a,b,c], [0,1,0] -> a (b) c
	//  c
	// a b
	@Override
	public Term[] getInterpolants(final Term[] partition, final int[] startOfSubtree)
		throws SMTLIBException,	UnsupportedOperationException {
		mPw.print("(get-interpolants ");
		mTermPrinter.append(mPw, partition[0]);
		for (int i = 1; i < partition.length; ++i) {
			int prevStart = startOfSubtree[i - 1];
			while (startOfSubtree[i] < prevStart) {
				mPw.print(')');
				prevStart = startOfSubtree[prevStart - 1];
			}
			mPw.print(' ');
			if (startOfSubtree[i] == i) {
				mPw.print('(');
			}
			mTermPrinter.append(mPw, partition[i]);
		}
		mPw.println(')');
		return mScript.getInterpolants(partition, startOfSubtree);
	}

	@Override
	public void exit() {
		mPw.println("(exit)");
		mPw.flush();
		mPw.close();
		mScript.exit();
	}

	@Override
	public Sort sort(final String sortname, final Sort... params) throws SMTLIBException {
		return mScript.sort(sortname, params);
	}

	@Override
	public Sort sort(final String sortname, final BigInteger[] indices, final Sort... params)
		throws SMTLIBException {
		return mScript.sort(sortname, indices, params);
	}

	@Override
	public Term term(final String funcname, final Term... params) throws SMTLIBException {
		return mScript.term(funcname, params);
	}

	@Override
	public Term term(final String funcname, final BigInteger[] indices, final Sort returnSort,
			final Term... params) throws SMTLIBException {
		return mScript.term(funcname, indices, returnSort, params);
	}

	@Override
	public TermVariable variable(final String varname, final Sort sort)
		throws SMTLIBException {
		return mScript.variable(varname, sort);
	}

	@Override
	public Term quantifier(final int quantor, final TermVariable[] vars, final Term body,
			final Term[]... patterns) throws SMTLIBException {
		return mScript.quantifier(quantor, vars, body, patterns);
	}

	@Override
	public Term let(final TermVariable[] vars, final Term[] values, final Term body)
		throws SMTLIBException {
		return mScript.let(vars, values, body);
	}

	@Override
	public Term annotate(final Term t, final Annotation... annotations)
		throws SMTLIBException {
		return mScript.annotate(t, annotations);
	}

	@Override
	public Term numeral(final String num) throws SMTLIBException {
		return mScript.numeral(num);
	}

	@Override
	public Term numeral(final BigInteger num) throws SMTLIBException {
		return mScript.numeral(num);
	}

	@Override
	public Term decimal(final String decimal) throws SMTLIBException {
		return mScript.decimal(decimal);
	}

	@Override
	public Term decimal(final BigDecimal decimal) throws SMTLIBException {
		return mScript.decimal(decimal);
	}

	@Override
	public Term string(final String str) throws SMTLIBException {
		return mScript.string(str);
	}

	@Override
	public Term hexadecimal(final String hex) throws SMTLIBException {
		return mScript.hexadecimal(hex);
	}

	@Override
	public Term binary(final String bin) throws SMTLIBException {
		return mScript.binary(bin);
	}

	@Override
	public Sort[] sortVariables(final String... names) throws SMTLIBException {
		return mScript.sortVariables(names);
	}

	@Override
	public Model getModel() throws SMTLIBException,
			UnsupportedOperationException {
		mPw.println("(get-model)");
		return mScript.getModel();
	}

	@Override
	public Iterable<Term[]> checkAllsat(final Term[] predicates)
		throws SMTLIBException,	UnsupportedOperationException {
		final PrintTerm pt = new PrintTerm();
		mPw.print("(check-allsat (");
		String spacer = "";
		for (final Term p : predicates) {
			mPw.print(spacer);
			pt.append(mPw, p);
			spacer = " ";
		}
		mPw.println("))");
		return mScript.checkAllsat(predicates);
	}

	@Override
	public Term[] findImpliedEquality(final Term[] x, final Term[] y) {
		final PrintTerm pt = new PrintTerm();
		mPw.print("(find-implied-equality (");
		String spacer = "";
		for (final Term p : x) {
			mPw.print(spacer);
			pt.append(mPw, p);
			spacer = " ";
		}
		mPw.print(") (");
		spacer = "";
		for (final Term p : x) {
			mPw.print(spacer);
			pt.append(mPw, p);
			spacer = " ";
		}
		mPw.println("))");
		return mScript.findImpliedEquality(x, y);
	}

	@Override
	public QuotedObject echo(final QuotedObject msg) {
		mPw.print("(echo ");
		mPw.print(msg);
		mPw.println(')');
		return mScript.echo(msg);
	}

	/**
	 * Write a comment to the generated SMTLIB dump file.  Note that this
	 * function is only available in the LoggingScript and not in the interface
	 * {@link Script} since it only makes sense for logging and not for solving.
	 * @param comment The comment to write to the dump file.
	 */
	public void comment(final String comment) {
		mPw.print("; ");
		mPw.println(comment);
	}

	@Override
	public void resetAssertions() {
		mPw.println("(reset-assertions)");
		mScript.resetAssertions();
	}

	@Override
	public Term[] getUnsatAssumptions() throws SMTLIBException,
			UnsupportedOperationException {
		mPw.println("(get-unsat-assumptions)");
		return mScript.getUnsatAssumptions();
	}
}
