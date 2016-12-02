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

import models.entities.AssignmentProductShop;
import models.entities.Product;
import models.entities.Shop;
import models.entities.User;

public class ReadXML {

	// Metodos para leer archivos
	public ArrayList<User> readUser() throws ParserConfigurationException, SAXException, IOException{
		ArrayList<User> userList = new ArrayList<>();
		File file = new File("src/data/users.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(file);
		document.getDocumentElement().normalize();

		NodeList nodeList = document.getElementsByTagName("user");
		for (int i = 0; i < nodeList.getLength(); i++) {
			userList.add(getUser((Element)nodeList.item(i)));
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
			shopList.add(getShop((Element)nodeList.item(i)));
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
			productList.add(getProduct((Element)nodeList.item(i)));
		}
		return productList;
	}

	public ArrayList<AssignmentProductShop> readAsigmentProducts() throws ParserConfigurationException, SAXException, IOException{
		ArrayList<AssignmentProductShop> assignmentProductShops = new ArrayList<>();
		File file = new File("src/data/assignedProducts.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(file);
		document.getDocumentElement().normalize();

		NodeList nodeList = document.getElementsByTagName("assignedProduct");
		for (int i = 0; i < nodeList.getLength(); i++) {
			assignmentProductShops.add(new AssignmentProductShop(getProduct((Element) ((Element) nodeList.item(i)).getElementsByTagName("product").item(0)),
					getShop((Element) ((Element) nodeList.item(i)).getElementsByTagName("shop").item(0))));
		}
		return assignmentProductShops;
	}

	//Metodos para escribir archivos
	public void writeUser(ArrayList<User> users) throws TransformerException, ParserConfigurationException{
		DocumentBuilder docBuilder;
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("list.user");
		doc.appendChild(rootElement);
		for (User user : users) {
			rootElement.appendChild(writeUserElement(doc, user));
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/data/users.xml"));
		transformer.transform(source, result);
	}

	public void writeShop(ArrayList<Shop> shops) throws TransformerException, ParserConfigurationException{
		DocumentBuilder docBuilder;
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("list.shop");
		doc.appendChild(rootElement);
		for (Shop shop : shops) {
			rootElement.appendChild(writeShopElement(doc, shop));
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/data/shops.xml"));
		transformer.transform(source, result);
	}

	public void writeProduct(ArrayList<Product> products) throws TransformerException, ParserConfigurationException{
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("list.product");
		doc.appendChild(rootElement);
		for (Product product : products) {
			rootElement.appendChild(writeProductElement(doc, product));
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/data/products.xml"));
		transformer.transform(source, result);
	}

	public void writeAssigmentProduct(ArrayList<AssignmentProductShop> assignmentProductShopList) throws TransformerException, ParserConfigurationException{
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("assignedList");
		doc.appendChild(rootElement);
		for (AssignmentProductShop assignmentProductShop : assignmentProductShopList) {
			Element elementAssigment = doc.createElement("assignedProduct");
			rootElement.appendChild(elementAssigment);
			
			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(String.valueOf(assignmentProductShop.getId())));
			elementAssigment.appendChild(id);
			
			Element product = writeProductElement(doc, assignmentProductShop.getProduct());
			elementAssigment.appendChild(product);
			
			Element shop = writeShopElement(doc, assignmentProductShop.getShop());
			elementAssigment.appendChild(shop);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/data/assignedProducts.xml"));
		transformer.transform(source, result);
	}
	//metodos usados en la escritura para optimizacion
	public Element writeProductElement(Document doc, Product product){
		Element elementProduct = doc.createElement("product");

		Element id = doc.createElement("id");
		id.appendChild(doc.createTextNode(String.valueOf(product.getId())));
		elementProduct.appendChild(id);

		Element nombre = doc.createElement("name");
		nombre.appendChild(doc.createTextNode(product.getName()));
		elementProduct.appendChild(nombre);

		Element price = doc.createElement("price");
		price.appendChild(doc.createTextNode(String.valueOf(product.getPrice())));
		elementProduct.appendChild(price);

		Element sourceImg = doc.createElement("srcImg");
		sourceImg.appendChild(doc.createTextNode(product.getSrcImg()));
		elementProduct.appendChild(sourceImg);
		return elementProduct;
	}

	public Element writeShopElement(Document doc, Shop shop){
		Element elementShop = doc.createElement("shop");

		Element id = doc.createElement("id");
		id.appendChild(doc.createTextNode(String.valueOf(shop.getId())));
		elementShop.appendChild(id);

		Element nombre = doc.createElement("name");
		nombre.appendChild(doc.createTextNode(shop.getName()));
		elementShop.appendChild(nombre);

		Element sourceImg = doc.createElement("srcImg");
		sourceImg.appendChild(doc.createTextNode(shop.getSrcImg()));
		elementShop.appendChild(sourceImg);
		return elementShop;
	}
	
	public Element writeUserElement(Document doc, User user){
		Element elementUser = doc.createElement("user");

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
		return elementUser;
	}

	//Metodos para devolver las entidades
	public Product getProduct(Element product){
		return new Product((product.getElementsByTagName("name").item(0)).getTextContent(),
				Double.parseDouble((product.getElementsByTagName("price").item(0)).getTextContent()),
				(product.getElementsByTagName("srcImg").item(0)).getTextContent());
	}

	public Shop getShop(Element shop){
		return new Shop((shop.getElementsByTagName("name").item(0)).getTextContent(),
				(shop.getElementsByTagName("srcImg").item(0)).getTextContent());
	}

	public User getUser(Element user){
		return new User((user.getElementsByTagName("name").item(0)).getTextContent(),
				(user.getElementsByTagName("address").item(0)).getTextContent(),
				(user.getElementsByTagName("password").item(0)).getTextContent(), 
				(user.getElementsByTagName("sourceImg").item(0)).getTextContent());
	}
}