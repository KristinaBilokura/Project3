package Project.Parsers;

import Project.Entity.Fund;
import Project.Entity.Gem;
import Project.Entity.Preciousness;
import Project.Entity.VisualParameters;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SAXParser extends DefaultHandler {
    static Logger log = Logger.getLogger(SAXParser.class.getName());

    private SAXParser() {
    }

    private Fund fund;
    private List<Gem> gems = new ArrayList<>();
    private Gem gem;
    private VisualParameters visualParameters;
    private String thisElement;

    public static Fund parse(File xmlAsString) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser sp = null;
        try {
            sp = spf.newSAXParser();
        } catch (ParserConfigurationException e) {
            log.error(e);
        } catch (SAXException e) {
            log.error(e);
        }
        SAXParser handler = new SAXParser();
        try {
            sp.parse(xmlAsString, handler);
        } catch (SAXException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
        return handler.fund;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
        if (thisElement.equals("gem")) {
            gem = new Gem();
            visualParameters = new VisualParameters();
            gem.setId(new String(attributes.getValue("id")));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("name")) {
            gem.setName(new String(ch, start, length));
        }
        if (thisElement.equals("preciousness")) {
            if (new String(ch, start, length).equals("PRECIOUS"))
                gem.setPreciousness(Preciousness.PRECIOUS);
            else if (new String(ch, start, length).equals("SEMIPRECIOUS"))
                gem.setPreciousness(Preciousness.SEMIPRECIOUS);
        }
        if (thisElement.equals("origin")) {
            gem.setOrigin(new String(ch, start, length));
        }
        if (thisElement.equals("color")) {
            visualParameters.setColor(new String(ch, start, length));
        }

        if (thisElement.equals("transparency")) {
            visualParameters.setTransparency(new Integer(new String(ch, start, length)));
        }
        if (thisElement.equals("gemCutting")) {
            visualParameters.setGemCutting(new Integer(new String(ch, start, length)));
        }
        if (thisElement.equals("value")) {
            gem.setValue(new Double(new String(ch, start, length)));
            gem.setVisualParameters(visualParameters);
            gems.add(gem);
        }
    }
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }
    @Override
    public void endDocument() {
        fund = new Fund(gems);
    }

}
