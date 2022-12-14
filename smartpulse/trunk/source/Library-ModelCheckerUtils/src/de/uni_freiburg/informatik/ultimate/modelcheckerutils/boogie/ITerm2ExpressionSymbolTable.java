package de.uni_freiburg.informatik.ultimate.modelcheckerutils.boogie;

import java.util.Map;

import de.uni_freiburg.informatik.ultimate.boogie.DeclarationInformation;
import de.uni_freiburg.informatik.ultimate.core.model.models.ILocation;
import de.uni_freiburg.informatik.ultimate.logic.ApplicationTerm;
import de.uni_freiburg.informatik.ultimate.logic.TermVariable;
import de.uni_freiburg.informatik.ultimate.modelcheckerutils.cfg.variables.IProgramVar;

public interface ITerm2ExpressionSymbolTable {

	BoogieConst getProgramConst(ApplicationTerm term);

	IProgramVar getProgramVar(TermVariable term);

	Map<String, String> getSmtFunction2BoogieFunction();

	ILocation getLocation(IProgramVar pv);

	DeclarationInformation getDeclarationInformation(IProgramVar pv);

}
