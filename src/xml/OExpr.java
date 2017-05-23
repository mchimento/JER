package xml;

public class OExpr {
	   
	private final String expr;
	   
	private String type;	   	  

	public OExpr(String expr, String type) {
	   this.expr = expr;
	   this.type = type;	      
	}

	public String getExpr() {
	  return expr;
	}
	  
	public String getType() {
	   return type;
	} 
	   
	 public void setType(String t) {
	   type = t;
	 }
}
