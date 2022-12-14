package edu.utexas.cs.utopia.specLang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import de.uni_freiburg.informatik.ultimate.boogie.BoogieTransformer;
import de.uni_freiburg.informatik.ultimate.boogie.ast.CallStatement;
import de.uni_freiburg.informatik.ultimate.boogie.ast.Declaration;
import de.uni_freiburg.informatik.ultimate.boogie.ast.LeftHandSide;
import de.uni_freiburg.informatik.ultimate.boogie.ast.Procedure;
import de.uni_freiburg.informatik.ultimate.boogie.ast.Statement;
import de.uni_freiburg.informatik.ultimate.boogie.ast.Unit;
import de.uni_freiburg.informatik.ultimate.boogie.ast.VarList;
import de.uni_freiburg.informatik.ultimate.boogie.ast.VariableDeclaration;
import de.uni_freiburg.informatik.ultimate.boogie.ast.VariableLHS;
import de.uni_freiburg.informatik.ultimate.boogie.preprocessor.Activator;
import de.uni_freiburg.informatik.ultimate.core.model.models.IElement;
import de.uni_freiburg.informatik.ultimate.core.model.models.ModelType;
import de.uni_freiburg.informatik.ultimate.core.model.observers.IUnmanagedObserver;
import de.uni_freiburg.informatik.ultimate.core.model.services.ILogger;
import de.uni_freiburg.informatik.ultimate.core.model.services.IUltimateServiceProvider;

public class ModSetAnalyzer extends BoogieTransformer implements IUnmanagedObserver {
	private Map<String, Set<String>> mModifiedGlobals;
	private Set<String> mGlobals;
	private final IUltimateServiceProvider mServices;
	private final ILogger logger;
	private String mCurrentProcedure;
	private Map<String, Set<String>> mCallGraph;

	public ModSetAnalyzer(IUltimateServiceProvider services) {
		mServices = services;
		logger = mServices.getLoggingService().getLogger(Activator.PLUGIN_ID);
	}

	protected Map<String, Set<String>> getModifiedGlobals() {
		return mModifiedGlobals;
	}

	@Override
	public boolean process(IElement root) throws Throwable {
		if (root instanceof Unit) {
			final Unit unit = (Unit) root;
			mGlobals = new HashSet<String>();
			mModifiedGlobals = new HashMap<String, Set<String>>();
			mCallGraph = new HashMap<String, Set<String>>();
			// First pass: Collect all global variable declarations
			for (final Declaration decl : unit.getDeclarations()) {
				if (decl instanceof VariableDeclaration) {
					processGlobalVariableDeclaration((VariableDeclaration) decl);
				}
			}

			// Second pass: Process all declarations
			for (final Declaration decl : unit.getDeclarations()) {
				processDeclaration(decl);
			}

			// Propagate transitive modifies
			propagateModifies();
			return false;
		}
		return true;
	}

	private void propagateModifies() {
		for (final Entry<String, Set<String>> proc : mCallGraph.entrySet()) {
			// TODO: do this only for graph roots
			for (final String callee : proc.getValue()) {
				final HashSet<String> visited = new HashSet<String>();
				visited.add(proc.getKey());
				final Set<String> modifiedGlobals = mModifiedGlobals.get(proc.getKey());
				assert(modifiedGlobals != null);
				modifiedGlobals.addAll(getModifiesRecursive(visited, callee));
			}
		}
	}

	private Set<String> getModifiesRecursive(Set<String> visited, String proc) {
		final Set<String> result = new HashSet<String>();
		if (visited.contains(proc)) {
			return result;
		}
		visited.add(proc);
		final Set<String> modifiedGlobals = mModifiedGlobals.get(proc);
		if (modifiedGlobals != null) {
			result.addAll(modifiedGlobals);
		}
		final Set<String> callees = mCallGraph.get(proc);
		if (callees != null) {
			for (final String callee : callees) {
				result.addAll(getModifiesRecursive(visited, callee));
			}
		}
		return result;
	}

	@Override
	public void init(ModelType modelType, int currentModelIndex, int numberOfModels) throws Throwable {
		// TODO Auto-generated method stub

	}

	@Override
	public void finish() throws Throwable {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performedChanges() {
		// TODO Auto-generated method stub
		return false;
	}

	private void processGlobalVariableDeclaration(VariableDeclaration varDecl) {
		for (final VarList varlist : varDecl.getVariables()) {
			for (final String id : varlist.getIdentifiers()) {
				mGlobals.add(id);
			}
		}
	}

	@Override
	protected Declaration processDeclaration(Declaration decl) {
		mCurrentProcedure = null;
		if (decl instanceof Procedure) {
			final Procedure proc = ((Procedure) decl);
			if (proc.getBody() != null) { // We are processing an implementation
				if (logger.isDebugEnabled()) {
					logger.debug("Processing procedure " + proc.getIdentifier());
				}
				mCurrentProcedure = proc.getIdentifier();
				mModifiedGlobals.put(mCurrentProcedure, new HashSet<String>());
				mCallGraph.put(mCurrentProcedure, new HashSet<String>());
			}
		}
		return super.processDeclaration(decl);
	}

	@Override
	protected LeftHandSide processLeftHandSide(LeftHandSide lhs) {
		String identifier = null;
		if (mCurrentProcedure != null && lhs instanceof VariableLHS) {
			identifier = ((VariableLHS) lhs).getIdentifier();
			if (mGlobals.contains(identifier)) {
				mModifiedGlobals.get(mCurrentProcedure).add(identifier);
			}
		}
		return super.processLeftHandSide(lhs);
	}

	@Override
	protected Statement processStatement(Statement statement) {
		if (mCurrentProcedure != null && statement instanceof CallStatement) {
			final CallStatement call = (CallStatement) statement;
			final String method = call.getMethodName();
			// Only add if it is not a recursive call
			if (!method.equals(mCurrentProcedure)) {
				mCallGraph.get(mCurrentProcedure).add(method);
			}
		}
		return super.processStatement(statement);
	}
}
