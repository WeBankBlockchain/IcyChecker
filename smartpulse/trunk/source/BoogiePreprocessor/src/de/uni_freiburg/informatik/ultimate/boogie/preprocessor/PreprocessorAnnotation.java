/*
 * Copyright (C) 2014-2015 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 *
 * This file is part of the ULTIMATE BoogiePreprocessor plug-in.
 *
 * The ULTIMATE BoogiePreprocessor plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE BoogiePreprocessor plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE BoogiePreprocessor plug-in. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE BoogiePreprocessor plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE BoogiePreprocessor plug-in grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.boogie.preprocessor;


import de.uni_freiburg.informatik.ultimate.boogie.symboltable.BoogieSymbolTable;
import de.uni_freiburg.informatik.ultimate.core.lib.models.annotation.AbstractAnnotations;
import de.uni_freiburg.informatik.ultimate.core.model.models.IElement;

/**
 *
 * @author dietsch
 *
 */
public class PreprocessorAnnotation extends AbstractAnnotations {

	private static final long serialVersionUID = 1L;

	private BoogieSymbolTable mSymbolTable;

	public BoogieSymbolTable getSymbolTable() {
		return mSymbolTable;
	}

	public void setSymbolTable(final BoogieSymbolTable symbolTable) {
		mSymbolTable = symbolTable;
	}

	public void annotate(final IElement elem) {
		if(elem == null){
			return;
		}
		elem.getPayload().getAnnotations().put(Activator.PLUGIN_ID, this);
	}

	public static PreprocessorAnnotation getAnnotation(final IElement elem) {
		if (elem.hasPayload() && elem.getPayload().hasAnnotation()) {
			return (PreprocessorAnnotation) elem.getPayload().getAnnotations().get(Activator.PLUGIN_ID);
		}
		return null;
	}

	@Override
	protected String[] getFieldNames() {
		return new String[] { "SymbolTable" };
	}

	@Override
	protected Object getFieldValue(final String field) {
		switch (field) {
		case "SymbolTable":
			return mSymbolTable;
		default:
			return null;
		}
	}

}
