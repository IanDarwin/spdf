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
	protected final Map<String,String> dict = new HashMap<>();

	/**
	 * Construct a PDFDict. Since most dictionaries should be immutable,
	 * the subclass constructor should initialize the contents of "dict".
	 * @param p The PDF object for this application
	 */
	PDFDict(PDF p) {
		super(p);
	}

	/**
	 * Retrieve a single value from the dictionary
	 * @param k The key
	 * @return The value for K, as per the general contract of java.util.Map.get()
	 */
	public String get(String k) {
		return dict.get(k);
	}

	/**
	 * Associate a single value with a given key. The default implementation
	 * throws UnsupportedOperationException as it is assumed that most
	 * dictionaries are immutable, but this method is not marked final as
	 * some dictionaries need to be mutable (e.g., the PagesObject's Count
	 * value isn't known until we start writing the file).
	 * @param k The key
	 * @param v The value
	 * @return The value, as per the general contract of java.util.Map.put()
	 */
	public String put(String k, String v) {
		throw new UnsupportedOperationException("Immutable dictionary " + getClass().getSimpleName());
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
