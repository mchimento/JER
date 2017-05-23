package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.key_project.util.java.ArrayUtil;
import org.key_project.util.java.IOUtil;
import org.key_project.util.java.XMLUtil;

public class Writer {
	
	   public static final String TAG_RESULT = "result";
	   public static final String ATTRIBUTE_CONTRACT_ID = "Id";
	   public static final String ATTRIBUTE_CLASSINF = "class";
	   public static final String TAG_OLD_EXPR = "oldExpr";
	   public static final String TAG_CONTRACT = "contract";
	   public static final String ATTRIBUTE_METHOD_NAME = "method";
	   public static final String ATTRIBUTE_PATH = "path";
	   public static final String ATTRIBUTE_EXPR = "expr";
	   public static final String ATTRIBUTE_TYPE = "type";
	   public static final String ATTRIBUTE_TARGET = "target";

	   public static void write(Result result, File file) throws IOException {
	      if (file != null && result != null) {
	         IOUtil.writeTo(new FileOutputStream(file), toXML(result, IOUtil.DEFAULT_CHARSET.displayName()));
	      }
	   }
	   
	   public static String toXML(Result result, String encoding) {
	      if (result != null) {
	         StringBuffer sb = new StringBuffer();
	         XMLUtil.appendXmlHeader(encoding, sb);
	         apendResult(0, result, sb);
	         return sb.toString();
	      }
	      else {
	         return null;
	      }
	   }

	   private static void apendResult(int level, Result result, StringBuffer sb) {
	      Map<String, String> attributes = new LinkedHashMap<String, String>();
	      XMLUtil.appendStartTag(level, TAG_RESULT, attributes, sb);
	      for (OldExpr proof : result.getOldExpr()) {
	         appendProof(level + 1, proof, sb);
	      }
	      XMLUtil.appendEndTag(level, TAG_RESULT, sb);
	   }

	   private static void appendProof(int level, OldExpr proof, StringBuffer sb) {
	      Map<String, String> attributes = new LinkedHashMap<String, String>();
	      attributes.put(ATTRIBUTE_CONTRACT_ID, proof.getContractId());
	      attributes.put(ATTRIBUTE_CLASSINF, proof.getClassInf());	      
	      attributes.put(ATTRIBUTE_PATH , proof.getPath());
	      attributes.put(ATTRIBUTE_TARGET, proof.getTarget());
	      attributes.put(ATTRIBUTE_METHOD_NAME, proof.getMethodName());
	      XMLUtil.appendStartTag(level, TAG_CONTRACT, attributes, sb);
	      for (OExpr path : proof.getOexprs()) {
	         appendPath(level + 1, path, sb);
	      }
	      XMLUtil.appendEndTag(level, TAG_CONTRACT, sb);
	   }

	   private static void appendPath(int level, OExpr path, StringBuffer sb) {
	      Map<String, String> attributes = new LinkedHashMap<String, String>();
	      attributes.put(ATTRIBUTE_EXPR, path.getExpr());
	      attributes.put(ATTRIBUTE_TYPE, path.getType() + "");
	      XMLUtil.appendStartTag(level, TAG_OLD_EXPR, attributes, sb);
	      XMLUtil.appendEndTag(level, TAG_OLD_EXPR, sb);
	   }
}
