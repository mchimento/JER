package xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Deque;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Reader {

	public static Result load(File file) throws Exception {
	      if (file != null && file.isFile()) {
	         return load(new FileInputStream(file));
	      }
	      else {
	         return null;
	      }
	}
	   
	public static Result load(String text) throws Exception {
	   if (text != null) {
	      return load(new ByteArrayInputStream(text.getBytes()));
	   }
	   else {
	      return null;
	   }
	}
	  
	public static Result load(InputStream in) throws Exception {
	   if (in != null) {
	      try {
	         // Parse XML document
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         factory.setNamespaceAware(true);
	         SAXParser saxParser = factory.newSAXParser();
	         InfoSAXHandler handler = new InfoSAXHandler();
	         saxParser.parse(in, handler);
	         // Return result
	         return handler.getResult();
	      }
	      finally {
	         in.close();
	      }
	   }
	   else {
	      return null;
	   }
	}
	   
	/**
	 * @author Martin Hentschel
	 */
	private static class InfoSAXHandler extends DefaultHandler {
       private Result result;
	      
     /**
	  * The parent hierarchy filled by {@link #startElement(String, String, String, Attributes)}
	  * and emptied by {@link #endElement(String, String, String)}.
	  */
	private final Deque<Object> parentStack = new LinkedList<Object>();
	      
	      /**
	       * The currently treated {@link OExpr}.
	       */
	      private OExpr currentPath;

	      /**
	       * {@inheritDoc}
	       */
	      @Override
	      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	         if (Writer.TAG_RESULT.equals(qName)) {
	            assertIsTrue(result == null, "Result is already defined.");
	            result = new Result();
	            parentStack.addFirst(result);
	         }
	         else if (Writer.TAG_CONTRACT.equals(qName)) {
	            Object parent = parentStack.peekFirst();
	            assertIsTrue(parent == result, parent + " is not the same as " + result);
	            OldExpr proof = new OldExpr(getContractId(attributes), 
	            		                    getClassInf(attributes),
	                                        getTarget(attributes),
	                                        getPath(attributes),
	                                        getMethodName(attributes));
	            result.addOldExpr(proof);
	            parentStack.addFirst(proof);
	         }
	         else if (Writer.TAG_OLD_EXPR.equals(qName)) {
	            Object parent = parentStack.peekFirst();
	            assertIsTrue(parent instanceof OldExpr, parent + " is not a OldExpr.");
	            OExpr path = new OExpr(getExpr(attributes), 
	            		               getType(attributes));
	            ((OldExpr) parent).addPath(path);
	            currentPath = path;
	            parentStack.addFirst(path);
	         }
	         else {
	            assertIsTrue(false, "Unsupported tag \"" + qName + "\".");
	         }
	      }

	      protected void assertIsTrue(boolean condition, String message) throws SAXException {
	         if (!condition) {
	            throw new SAXException(message);
	         }
	      }

	      /**
	       * {@inheritDoc}
	       */
	      @Override
	      public void endElement(String uri, String localName, String qName) throws SAXException {
	         if (!parentStack.isEmpty()) {
	            parentStack.removeFirst();
	         }
	      }

	      protected String getContractId(Attributes attributes) {
	         return attributes.getValue(Writer.ATTRIBUTE_CONTRACT_ID);
	      }

	      protected String getClassInf(Attributes attributes) {
	         return attributes.getValue(Writer.ATTRIBUTE_CLASSINF );
	      }
	      
	      protected String getTarget(Attributes attributes) {
	         return attributes.getValue(Writer.ATTRIBUTE_TARGET);
	      }   

	      protected String getPath(Attributes attributes) {
	         return attributes.getValue(Writer.ATTRIBUTE_PATH);
	      }
	      
	      protected String getMethodName(Attributes attributes) {
		     return attributes.getValue(Writer.ATTRIBUTE_METHOD_NAME);
		  }

	      protected String getExpr(Attributes attributes) {
	         return attributes.getValue(Writer.ATTRIBUTE_EXPR);
	      }

	      protected String getType(Attributes attributes) {
	         return attributes.getValue(Writer.ATTRIBUTE_TYPE);
	      }
	      
	      public Result getResult() {
	         return result;
	      }
	   }	
}
