package Project.HTML;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransIntoHtml {

    public  void convert(){

        try {
            TransformerFactory tFactory=TransformerFactory.newInstance();
            Source xsl=new StreamSource("fund.xsl");
            Source xml=new StreamSource("Fund.xml");

            String outputFileName="FundHTML.html";

            OutputStream htmlFile=new FileOutputStream(outputFileName);
            Transformer trasform=tFactory.newTransformer(xsl);
            trasform.transform(xml, new StreamResult(htmlFile));
            System.out.println ("Done!");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e)
        {
            e.printStackTrace();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
    }
}

