package spdf;

import java.util.*;

/** A PDFDict is a PDFObject that is all, or mostly, a Dictionary.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public abstract class PDFDict extends PDFObject {
	/** The Dictionary is a HashTable. Put the keys without a 
	 * leading slash, since they always have it. Values can
	 * be /names, (strings), or whatever.
	 */
	protected Map<String,String> dict;

	PDFDict(PDF m) {
		super(m);
		dict = new HashMap<String,String>();
	}

	/** Write the object to the Output Writer. The default implementation
	 * of this method in PDFDict just calls startObj, printDict, and endObj.
	 */
	protected void print() {
		startObj();
		printDict();
		endObj();
	}

	protected void startObj() {
		// Record the starting position of this Obj in the xref table
		master.addXref();

		// Print out e.g., "42 0 obj"
		master.print(master.currObj++);
	 	master.print(" 0 obj");
		master.println();
	}

	protected void endObj() {
		master.println("endobj");
	}

	protected void printDict() {
		master.println("<<");
		for (String k : dict.keySet()) {
			String v = dict.get(k);
			master.print("\t/");
			master.print(k);
			master.print(" ");
			master.print(v);
			master.println();
		}
		master.println(">>");
	}
}
