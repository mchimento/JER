package xml;

import java.util.LinkedList;
import java.util.List;

public class Result {

	private final List<OldExpr> oldExprs = new LinkedList<OldExpr>();
	   
	public void addOldExpr(OldExpr proof) {
      if (proof != null) {
    	  oldExprs.add(proof);
      }
	}
	   
	public OldExpr[] getOldExpr() {
	    return oldExprs.toArray(new OldExpr[oldExprs.size()]);
	}	
}
