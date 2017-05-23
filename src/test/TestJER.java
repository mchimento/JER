package test;

import java.io.File;
import java.util.LinkedList;

import de.uka.ilkd.key.proof.init.ProofInputException;
import main.JavaExpressionReader;

public class TestJER {

	public static void test() throws ProofInputException {
		
		JavaExpressionReader jer = new JavaExpressionReader("/home/chimento/repos/JER/example/src/", 
                new LinkedList<File>(), new File("../jre"), "main.CMachine", "setCups");
        
        try {
            System.out.println("Expression Type: " + jer.getKeYJavaTypeForExpression("limit + foo.goo.goo()").getJavaType().getFullName());
        } catch (ProofInputException pie) {
            System.out.println(pie.line + ":" + pie.charPositionInLine);
            throw pie;
        } catch (RuntimeException e) {        	
        	System.out.println(e.toString());        	
        }
       
        jer = new JavaExpressionReader("/home/chimento/repos/JER/example/src/", 
                new LinkedList<File>(), new File("../jre"), "main.CMachine", "setCups");
        
        try {
            System.out.println("Expression Type: " + jer.getKeYJavaTypeForExpression("foo.goo").getJavaType().getFullName());
        } catch (ProofInputException pie) {
            System.out.println(pie.line + ":" + pie.charPositionInLine);
            throw pie;
        }
             
        /* Expected output in console:
         *  
         * Expression Type: int
         * Expression Type: main.Goo 
         */
	}
	
    public static void main(String[] args) throws ProofInputException {    	
    	test();
    }
}
