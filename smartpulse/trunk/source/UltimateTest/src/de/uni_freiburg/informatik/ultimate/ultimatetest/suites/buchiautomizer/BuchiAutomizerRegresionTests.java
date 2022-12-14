/*
 * Copyright (C) 2015 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2015 Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 *
 * This file is part of the ULTIMATE Test Library.
 *
 * The ULTIMATE Test Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE Test Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE Test Library. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE Test Library, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE Test Library grant you additional permission
 * to convey the resulting work.
 */
/**
 *
 */
package de.uni_freiburg.informatik.ultimate.ultimatetest.suites.buchiautomizer;

import java.util.Collection;

import de.uni_freiburg.informatik.ultimate.test.UltimateTestCase;
import de.uni_freiburg.informatik.ultimate.test.util.DirectoryFileEndingsPair;

/**
 * @author heizmann@informatik.uni-freiburg.de
 *
 */
public class BuchiAutomizerRegresionTests extends AbstractBuchiAutomizerTestSuite {

//	private static final int FILES_PER_DIR_LIMIT = Integer.MAX_VALUE;
	private static final int FILES_PER_DIR_LIMIT = 50;

	//@formatter:off
	private static final DirectoryFileEndingsPair[] SVCOMP_EXAMPLES = {

	};



	private static final String[] ULTIMATE_EXAMPLES = {
		"examples/lassos/regression/",
//		"examples/lassos/arrays",
//		"examples/termination/svcomp-sorted/success/",
//		"examples/programs/quantifier",
//		"examples/programs/recursive/regression",
//		"examples/programs/toy"
//		"examples/programs/termination/regression/",
//		"examples/termination/cooperatingT2/difficult/solved",
//		"examples/termination/cooperatingT2",
//		"examples/termination/Brainfuck/nonterminating",
//		"examples/termination/Brainfuck-terminating",
	};

	/**
	 * List of path to setting files.
	 * Ultimate will run on each program with each setting that is defined here.
	 * The path are defined relative to the folder "trunk/examples/settings/",
	 * because we assume that all settings files are in this folder.
	 *
	 */
	private static final String[] SETTINGS = {
//		"buchiAutomizer/ForwardPredicatesNonlinearLbeNotasimpOldMapElim.epf",
		"buchiAutomizer/ForwardPredicatesNonlinearLbeNotasimpNewMapElim-1.epf",
//		"buchiAutomizer/ForwardPredicatesNonlinearLbeNotasimpNewMapElim-2.epf",
//		"buchiAutomizer/ForwardPredicatesNonlinearLbeNotasimpNewMapElim-3.epf",
//		"buchiAutomizer/ForwardPredicatesNonlinearLbeNotasimpNewMapElim-4.epf",
	};


	private static final String[] BPL_TOOLCHAINS = {
		"BuchiAutomizerBpl.xml",
//		"BuchiAutomizerBplWithBlockEncoding.xml",
//		"BuchiAutomizerBplInlineWithBlockEncoding.xml",
	};

	private static final String[] C_TOOLCHAINS = {
		"BuchiAutomizerC.xml",
//		"BuchiAutomizerCWithBlockEncoding.xml",
//		"BuchiAutomizerCInlineWithBlockEncoCding.xml",
//		"BuchiAutomizerCInline.xml",
	};

	//@formatter:on

	@Override
	public long getTimeout() {
		return 10 * 1000;
	}

	@Override
	public Collection<UltimateTestCase> createTestCases() {
		for (final String setting : SETTINGS) {
			for (final String toolchain : C_TOOLCHAINS) {
				addTestCase(toolchain, setting, SVCOMP_EXAMPLES);
			}
		}

		for (final String setting : SETTINGS) {
			for (final String toolchain : BPL_TOOLCHAINS) {
				addTestCase(toolchain, setting, ULTIMATE_EXAMPLES, new String[] { ".bpl" });
			}
		}
		for (final String setting : SETTINGS) {
			for (final String toolchain : C_TOOLCHAINS) {
				addTestCase(toolchain, setting, ULTIMATE_EXAMPLES, new String[] { ".c", ".i" });
			}
		}
		return super.createTestCases();
	}
}
