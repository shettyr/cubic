package com.rk.cubic.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DictionaryXMLParser {
	
	

	public static List<String> parse(String xmlStr) {
		List<String> result = new ArrayList<String>();
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xmlStr));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();

			Logger.getLogger("Cubic").info("root element " + doc.getDocumentElement().getNodeName());
			NodeList defList = doc.getElementsByTagName("def");
			if (defList.getLength() < 1) return result;
			NodeList nodes = defList.item(0).getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE  && node.getNodeName().equalsIgnoreCase("dt")) {
					Element element = (Element) node;
					Logger.getLogger("Cubic").info(element.getTextContent());
					result.add(element.getTextContent());
					
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}


}
