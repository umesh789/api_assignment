package utility;

import base.TestBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DataReaderManager extends TestBase {

    public static String readConfigXml(String parentAttrName) throws Exception {
        String Configdata = null;
        try {

            File fXmlFile = new File(g_appConfigXmlPath);
            if (g_appConfigXmlPath == null) {
                return null;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("PublicVariable");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    if (eElement.getAttribute("name").equalsIgnoreCase(parentAttrName.trim())) {
                        Configdata = eElement.getElementsByTagName("name").item(0).getTextContent();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //LogManager.fail("Unable to read app config file.. error.." + ExceptionUtils.getStackTrace(e));
        }

        if (Configdata == null) {

            System.out.println("Tag/Element is Empty >>> " + parentAttrName);
        }

        return Configdata.trim();
    }


}
