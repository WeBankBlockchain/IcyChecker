/*
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
package de.uni_freiburg.informatik.ultimate.ultimatetest.suites.traceabstraction;

import java.util.Collection;

import de.uni_freiburg.informatik.ultimate.test.UltimateTestCase;

/**
 * @author heizmann@informatik.uni-freiburg.de
 *
 */
public class TermcompMemsafetyTests extends AbstractTraceAbstractionTestSuite {
	
	
	private static final String[] mUltimateRepository = {
//		"examples/termination/termcomp2015/C/AProVE_memory_alloca/svcomp_openbsd_cstrcat_alloca.c",
//		"examples/termination/termcomp2015/C/AProVE_memory_alloca/svcomp_openbsd_cstrcmp_alloca.c",
//		"examples/termination/termcomp2015/C/AProVE_memory_unsafe/svcomp_lis_unsafe.c",
		
//		"examples/termination/termcomp2015/C/SV-COMP_Mixed_Categories/rekcba_aso_false-unreach-call.1.M1.c",
//		"examples/termination/termcomp2015/C/SV-COMP_Mixed_Categories/rekcba_ctmfalse-unreach-call.2.c",
//		
		"examples/termination/termcomp2016/C/",
//		"examples/termination/termcomp2015/C_Integer/Stroeder_15",
//		"examples/termination/termcomp2015/C_Integer/Ton_Chanh_15",
	};
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getTimeout() {
		return 60 * 1000;
	}
	
	
	/**
	 * List of path to setting files. 
	 * Ultimate will run on each program with each setting that is defined here.
	 * The path are defined relative to the folder "trunk/examples/settings/",
	 * because we assume that all settings files are in this folder.
	 * 
	 */
	private static final String[] mSettings = {
		"buchiAutomizer/termcomp2016.epf"
//		"buchiAutomizer/termcomp2015_Tests.epf",
//		"buchiAutomizer/termcomp2015_Tests_NoMinimization.epf",
//		"buchiAutomizer/termcomp2015_Tests_iZ3.epf",
	};
	
	
	private static final String[] mCToolchains = {
//		"AutomizerAndBuchiAutomizerCWithBlockEncoding.xml",
		"AutomizerCInlineWithBlockEncoding.xml",
//		"BuchiAutomizerCInlineWithBlockEncoding.xml",
	};

	
	
	

	@Override
	public Collection<UltimateTestCase> createTestCases() {
		for (final String setting : mSettings) {
			for (final String toolchain : mCToolchains) {
				addTestCase(toolchain, setting, mUltimateRepository, 
						new String[] {".c", ".i"});
			}
		}
		return super.createTestCases();
	}
	
}
