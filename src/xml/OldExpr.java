package xml;

import java.util.LinkedList;
import java.util.List;

public class OldExpr {	
	   private final List<OExpr> oexprs = new LinkedList<OExpr>();
	   
	   private final String contractId;

	   private final String classInf;
	   
	   private final String methodName;
	   
	   private final String target;
	   
	   private final String path;
	   
	   public OldExpr(String contractId, String classInf, String target,String path,String methodName) {
	      this.contractId = contractId;
	      this.classInf = classInf;
	      this.methodName = methodName;
	      this.target = target;
	      this.path = path;
	   }

	   public void addPath(OExpr path) {
	      if (path != null) {
	    	  oexprs.add(path);
	      }
	   }
	   
	   public OExpr[] getOexprs() {
	      return oexprs.toArray(new OExpr[oexprs.size()]);
	   }

	   public String getContractId() {
	      return contractId;
	   }

	   public String getClassInf() {
	      return classInf;
	   }

	   public String getTarget() {
	      return target;
	   }

	   public String getMethodName() {
	      return methodName;
	   }
	   
	   public String getPath() {
		  return path;   
	   }	   
}
