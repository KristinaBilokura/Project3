package Project.Parsers;


import Project.Entity.Gem;
import Project.Entity.Fund;
import Project.Entity.NameComparator;
import Project.Entity.VisualParameters;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DOMParser {
    static Logger log = Logger.getLogger(DOMParser.class.getName());
    private DOMParser() {
    }

    private static Element getChild(Element parent, String childName) {
        NodeList nlist = parent.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }

    private static String getChildValue(Element parent, String childName) {
        Element child = getChild(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }

    public static Fund parse(File xmlFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error(e);
        }
        Document document = null;
        try {
            document = builder.parse(xmlFile);
        } catch (SAXException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
        List<Gem> gemList = new ArrayList<>();
        Collections.sort(gemList,new NameComparator());
        NodeList gemsNodes = document.getDocumentElement().getElementsByTagName("gem");
        VisualParameters visualParameters;
        for (int i = 0; i < gemsNodes.getLength(); i++) {
            Gem gem = new Gem();
            Element gemElement = (Element) gemsNodes.item(i);
            gem.setId(gemElement.getAttribute("id"));
            gem.setName(getChildValue(gemElement, "name"));
            gem.setOrigin(getChildValue(gemElement, "origin"));
            gem.setName(getChildValue(gemElement, "preciousness"));
            gem.setValue(new Double(getChildValue(gemElement, "value")));

            visualParameters = new VisualParameters();
            Element visualParametersElement = getChild(gemElement, "visualParameters");
            visualParameters.setColor(getChildValue(visualParametersElement, "color"));
            visualParameters.setTransparency(new Integer(getChildValue(visualParametersElement, "transparency")));
            visualParameters.setGemCutting(new Integer(getChildValue(visualParametersElement, "gemCutting")));
            gem.setVisualParameters(visualParameters);
            gemList.add(gem);
        }
        return new Fund(gemList);
    }
}

