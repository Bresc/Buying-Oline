package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

	public void writeUser(ArrayList<User> users) throws TransformerException, ParserConfigurationException{
		DocumentBuilder docBuilder;
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("list.user");
		doc.appendChild(rootElement);
		for (User user : users) {
			Element elementUser = doc.createElement("user");
			rootElement.appendChild(elementUser);

			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(String.valueOf(user.getId())));
			elementUser.appendChild(id);

			Element nombre = doc.createElement("name");
			nombre.appendChild(doc.createTextNode(user.getName()));
			elementUser.appendChild(nombre);

			Element address = doc.createElement("address");
			address.appendChild(doc.createTextNode(user.getAddress()));
			elementUser.appendChild(address);

			Element password = doc.createElement("password");
			password.appendChild(doc.createTextNode(user.getPassword()));
			elementUser.appendChild(password);

			Element sourceImg = doc.createElement("sourceImg");
			sourceImg.appendChild(doc.createTextNode(user.getSourceImg()));
			elementUser.appendChild(sourceImg);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("src/data/users.xml"));
			transformer.transform(source, result);
			System.out.println("File saved!");
		}
	}
}