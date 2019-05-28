package spdf;

/** An immutable Font object */
public class PDFFont extends PDFDict {
	
	final String localName, baseName;
	
	protected PDFFont(PDF m) {
		this(m, "F1", "Helvetica");
		
	}

	PDFFont(PDF m, String localName, String baseName) {
		super(m);
		this.localName = localName;
		this.baseName = baseName;
		dict.put("Type", "/Font");
		dict.put("Subtype", "/Type1");
		dict.put("Name", "/" + localName);
		dict.put("BaseFont", "/" + baseName);
		dict.put("Encoding", "/MacRomanEncoding");
	}
	
	public String getLocalName() {
		return localName;
	}

	public String getBaseName() {
		return baseName;
	}
}
