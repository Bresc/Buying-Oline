package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import models.entities.User;

public class ReadXML {

	public ArrayList<User> readUser() throws ParserConfigurationException, SAXException, IOException{
		ArrayList<User> userList = new ArrayList<>();
		File file = new File("src/data/users.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.parse(file);
		document.getDocumentElement().normalize();
		
		NodeList nodeList = document.getElementsByTagName("user");
		for (int i = 0; i < nodeList.getLength(); i++) {
			userList.add(new User(((((Element)nodeList.item(i)).getElementsByTagName("name")).item(0)).getTextContent()
					, ((((Element)nodeList.item(i)).getElementsByTagName("address")).item(0)).getTextContent()
					, ((((Element)nodeList.item(i)).getElementsByTagName("password")).item(0)).getTextContent()
					, ((((Element)nodeList.item(i)).getElementsByTagName("sourceImg")).item(0)).getTextContent()
					));
		}
		return userList;
	}
}