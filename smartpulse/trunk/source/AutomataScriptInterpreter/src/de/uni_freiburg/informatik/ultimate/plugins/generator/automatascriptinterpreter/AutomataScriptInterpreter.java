/*
 * Copyright (C) 2013-2015 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2013-2015 Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 * 
 * This file is part of the ULTIMATE AutomataScriptInterpreter plug-in.
 * 
 * The ULTIMATE AutomataScriptInterpreter plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The ULTIMATE AutomataScriptInterpreter plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE AutomataScriptInterpreter plug-in. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE AutomataScriptInterpreter plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE AutomataScriptInterpreter plug-in grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.plugins.generator.automatascriptinterpreter;

import java.util.Collections;
import java.util.List;

import de.uni_freiburg.informatik.ultimate.core.model.IGenerator;
import de.uni_freiburg.informatik.ultimate.core.model.models.IElement;
import de.uni_freiburg.informatik.ultimate.core.model.models.ModelType;
import de.uni_freiburg.informatik.ultimate.core.model.observers.IObserver;
import de.uni_freiburg.informatik.ultimate.core.model.preferences.IPreferenceInitializer;
import de.uni_freiburg.informatik.ultimate.core.model.services.IToolchainStorage;
import de.uni_freiburg.informatik.ultimate.core.model.services.IUltimateServiceProvider;
import de.uni_freiburg.informatik.ultimate.plugins.generator.automatascriptinterpreter.preferences.PreferenceInitializer;

/**
 * Main class of Plug-In AutomataScriptInterpreter
 * <p>
 * TODO: refine comments
 */
public class AutomataScriptInterpreter implements IGenerator {
	
	private AutomataScriptInterpreterObserver mObserver;
	private ModelType mInputDefinition;
	private IUltimateServiceProvider mServices;
	
	@Override
	public String getPluginName() {
		return Activator.PLUGIN_NAME;
	}
	
	@Override
	public String getPluginID() {
		return Activator.PLUGIN_ID;
	}
	
	@Override
	public void init() {
	}
	
	@Override
	public ModelQuery getModelQuery() {
		return ModelQuery.LAST;
	}
	
	@Override
	public List<String> getDesiredToolIds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setInputDefinition(final ModelType graphType) {
		mInputDefinition = graphType;
	}
	
	@Override
	public List<IObserver> getObservers() {
		mObserver = new AutomataScriptInterpreterObserver(mServices);
		return Collections.singletonList((IObserver) mObserver);
	}
	
	@Override
	public ModelType getOutputDefinition() {
		/*
		 * TODO This generated method body only assumes a standard case. Adapt
		 * it if necessary. Otherwise remove this todo-tag.
		 */
		return new ModelType(Activator.PLUGIN_ID, mInputDefinition.getType(), mInputDefinition.getFileNames());
	}
	
	@Override
	public IElement getModel() {
		return mObserver.getUltimateModelOfLastPrintedAutomaton();
	}
	
	@Override
	public boolean isGuiRequired() {
		return false;
	}
	
	@Override
	public IPreferenceInitializer getPreferences() {
		return new PreferenceInitializer();
	}
	
	@Override
	public void setToolchainStorage(final IToolchainStorage services) {
		
	}
	
	@Override
	public void setServices(final IUltimateServiceProvider services) {
		mServices = services;
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}
}
