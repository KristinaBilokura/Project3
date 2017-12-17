package Project.FileReader;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public final class XMLValidationViaXSD {
    static Logger log = Logger.getLogger(XMLValidationViaXSD.class.getName());

    private XMLValidationViaXSD() {
    }
    public static boolean validate(String pathXml, String pathXsd) {
        try {
            File xml = new File(pathXml);
            File xsd = new File(pathXsd);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(pathXsd));
            Validator validator = schema.newValidator();
            try {
                validator.validate(new StreamSource(pathXml));
            } catch (IOException e) {
                log.error(e);
            }
            return true;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        }
    }

}