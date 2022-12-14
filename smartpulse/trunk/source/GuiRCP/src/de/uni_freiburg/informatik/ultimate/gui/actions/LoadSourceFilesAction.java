/*
 * Copyright (C) 2008-2015 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 *
 * This file is part of the ULTIMATE DebugGUI plug-in.
 *
 * The ULTIMATE DebugGUI plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE DebugGUI plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE DebugGUI plug-in. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE DebugGUI plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE DebugGUI plug-in grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.gui.actions;

import java.io.File;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import de.uni_freiburg.informatik.ultimate.core.coreplugin.toolchain.BasicToolchainJob;
import de.uni_freiburg.informatik.ultimate.core.lib.toolchain.RunDefinition;
import de.uni_freiburg.informatik.ultimate.core.model.ICore;
import de.uni_freiburg.informatik.ultimate.core.model.services.ILogger;
import de.uni_freiburg.informatik.ultimate.gui.GuiController;
import de.uni_freiburg.informatik.ultimate.gui.GuiToolchainJob;
import de.uni_freiburg.informatik.ultimate.gui.interfaces.IImageKeys;

/**
 * The action that tells the core what files to open
 *
 * may be do modification to listen for a response..
 *
 *
 * @author dietsch
 *
 */
public class LoadSourceFilesAction extends RunToolchainAction implements IWorkbenchAction {

	private static final String DIALOG_NAME = "Open Source ... ";

	/**
	 *
	 * @param window
	 *            the contexwindow
	 * @param ist
	 *            the steerablecore that will take the command
	 */
	public LoadSourceFilesAction(final IWorkbenchWindow window, final ICore<RunDefinition> icore,
			final GuiController controller, final ILogger logger) {
		super(logger, window, icore, controller, LoadSourceFilesAction.class.getName(), DIALOG_NAME,
				IImageKeys.LOADSOURCEFILES);
	}

	/**
	 * the action opens a file dialog then passes the files for opening to the core
	 */
	@Override
	public final void run() {
		final File[] fp = getInputFilesFromUser(DIALOG_NAME);
		if (fp == null || fp.length <= 0) {
			return;
		}
		final BasicToolchainJob tcj = new GuiToolchainJob("Processing Toolchain", mCore, mController, fp, mLogger);
		tcj.schedule();
	}

}
