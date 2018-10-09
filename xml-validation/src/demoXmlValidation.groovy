// {trans|Java}

// Solution:

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
import org.xml.sax.SAXParseException

def factory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI)
def validate = { schemaURL, docURL ->
    try {
        factory.newSchema(schemaURL.toURL()).newValidator().validate(new StreamSource(docURL))
        true
    } catch (SAXParseException e) {
        false
    }
}

// Test:

def schemaLoc = "http://venus.eas.asu.edu/WSRepository/xml/Courses.xsd"
def docLoc = "http://venus.eas.asu.edu/WSRepository/xml/Courses.xml"
println "Document is ${validate(schemaLoc, docLoc)? 'valid' : 'invalid'}"
