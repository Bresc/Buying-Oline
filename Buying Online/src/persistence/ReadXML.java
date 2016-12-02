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

import models.entities.Product;
import models.entities.Shop;
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
	
	public ArrayList<Shop> readShop() throws ParserConfigurationException, SAXException, IOException{
		ArrayList<Shop> shopList = new ArrayList<>();
		File file = new File("src/data/shops.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(file);
		document.getDocumentElement().normalize();

		NodeList nodeList = document.getElementsByTagName("shop");
		for (int i = 0; i < nodeList.getLength(); i++) {
			shopList.add(new Shop(((((Element)nodeList.item(i)).getElementsByTagName("name")).item(0)).getTextContent()
					, ((((Element)nodeList.item(i)).getElementsByTagName("srcImg")).item(0)).getTextContent()
					));
		}
		return shopList;
	}

	public ArrayList<Product> readProduct() throws ParserConfigurationException, SAXException, IOException{
		ArrayList<Product> productList = new ArrayList<>();
		File file = new File("src/data/products.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(file);
		document.getDocumentElement().normalize();

		NodeList nodeList = document.getElementsByTagName("product");
		for (int i = 0; i < nodeList.getLength(); i++) {
			productList.add(new Product(((((Element)nodeList.item(i)).getElementsByTagName("name")).item(0)).getTextContent()
					, Double.parseDouble(((((Element)nodeList.item(i)).getElementsByTagName("price")).item(0)).getTextContent())
					, ((((Element)nodeList.item(i)).getElementsByTagName("srcImg")).item(0)).getTextContent()
					));
		}
		return productList;
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
		}
	}
	
	public void writeShop(ArrayList<Shop> shops) throws TransformerException, ParserConfigurationException{
		DocumentBuilder docBuilder;
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("list.shop");
		doc.appendChild(rootElement);
		for (Shop shop : shops) {
			Element elementUser = doc.createElement("shop");
			rootElement.appendChild(elementUser);

			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(String.valueOf(shop.getId())));
			elementUser.appendChild(id);

			Element nombre = doc.createElement("name");
			nombre.appendChild(doc.createTextNode(shop.getName()));
			elementUser.appendChild(nombre);

			Element sourceImg = doc.createElement("srcImg");
			sourceImg.appendChild(doc.createTextNode(shop.getSrcImg()));
			elementUser.appendChild(sourceImg);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("src/data/shops.xml"));
			transformer.transform(source, result);
		}
	}
	
	public void writeProduct(ArrayList<Product> products) throws TransformerException, ParserConfigurationException{
		DocumentBuilder docBuilder;
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("list.product");
		doc.appendChild(rootElement);
		for (Product product : products) {
			Element elementUser = doc.createElement("product");
			rootElement.appendChild(elementUser);

			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(String.valueOf(product.getId())));
			elementUser.appendChild(id);

			Element nombre = doc.createElement("name");
			nombre.appendChild(doc.createTextNode(product.getName()));
			elementUser.appendChild(nombre);

			Element price = doc.createElement("price");
			price.appendChild(doc.createTextNode(String.valueOf(product.getPrice())));
			elementUser.appendChild(price);

			Element sourceImg = doc.createElement("srcImg");
			sourceImg.appendChild(doc.createTextNode(product.getSrcImg()));
			elementUser.appendChild(sourceImg);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("src/data/products.xml"));
			transformer.transform(source, result);
		}
	}
	
}