package Project.Parsers;

import Project.Entity.Gem;
import Project.Entity.Fund;
import Project.Paths.Paths;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public final class JAXB {
    static Logger log = Logger.getLogger(DOMParser.class.getName());

    private JAXB() {
    }

    public static void marshall(String filePath, Fund pav1) {
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Fund.class, Gem.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, Paths.XSD_FILE_PATH);
            jaxbMarshaller.marshal(pav1, file);
        } catch (JAXBException e) {
            log.error(e);
        }
    }
}