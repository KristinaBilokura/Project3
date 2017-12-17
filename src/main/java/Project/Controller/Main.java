package Project.Controller;

import Project.Entity.Fund;

import Project.FileReader.FileReader;
import Project.FileReader.XMLValidationViaXSD;
import Project.HTML.TransIntoHtml;
import Project.Parsers.DOMParser;
import Project.Parsers.JAXB;
import Project.Parsers.SAXParser;
import Project.Parsers.StAXParser;
import Project.Paths.Paths;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;


import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Scanner parser = new Scanner(System.in);
        TransIntoHtml transIntoHtml = new TransIntoHtml();
        File fundXML = new File(Paths.XML_FILEPATH);
        String xmlAsString = FileReader.readFile(Paths.XML_FILEPATH);

        boolean isValid = XMLValidationViaXSD.validate(Paths.XML_FILEPATH, Paths.XSD_FILEPATH);
        log.info("XML is validly? " + isValid);
        log.info("Which parser click 1(SAX) 2(DOM) 3(StAX)?");
       int i = parser.nextInt();
        switch (i){
            case 1 :Fund fundViaSAXParser = SAXParser.parse(fundXML);
                log.info("fundViaSAXParser: " + fundViaSAXParser);
                break;
            case 2 :Fund fundViaDOM = DOMParser.parse(fundXML);
                log.info("fundViaDOM: " + fundViaDOM);
                JAXB.marshall(Paths.JAXB_XML_FILEPATH, fundViaDOM);
                break;
            default:Fund fundViaStAX = StAXParser.parse(xmlAsString);
                log.info("fundViaStAX: " + fundViaStAX);
                StAXParser.createXML(Paths.StAX_XML_FILEPATH, fundViaStAX);
                break;
        }
        transIntoHtml.convert();
    }
}
