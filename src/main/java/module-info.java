module spdf {
	
	requires java.xml.bind;	// JAXB
    requires javax.jws;		// JAX-WS
    requires java.xml.ws;	// JAX-WS annotations for JAXB
    
	exports spdf;
}