/*package controller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Node;
import java.io.File;


import java.io.File;

public class XmlController {
	
	public static void obtenerXmlTarifas() {
		
		try {
			File inputFile = new File("CargosBancarios.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Cargo");
			for (int f=0;f<nList.getLength();f++) {
				if (nList.item(f))
				System.out.println(nList.item(f).getNodeName());
				System.out.println(nList.item(f).getTextContent());
				
			}


		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
*/