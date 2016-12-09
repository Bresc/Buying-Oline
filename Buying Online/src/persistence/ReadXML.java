package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
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

	private static final String TYPE_SHOP = "shops";
	private static final String TYPE_USER = "users";
	private static final String TYPE_PRODUCT = "products";
	private static final String TYPE_ASSIGNED_PRODUCTS = "assignedProducts";
	private static final String NODE_PRODUCT = "list.product";
	private static final String NODE_USER = "list.user";
	private static final String NODE_SHOP = "list.shop";
	private static final String NODE_ASSIGNED_LIST = "assignedList";
	private static final String TAG_NAME_USER = "user";
	private static final String TAG_NAME_LAST_ID = "lastId";
	private static final String TAG_NAME_SHOP = "shop";
	private static final String TAG_NAME_PRODUCT = "product";
	private static final String TAG_NAME_ASSIGNED_PRODUCT = "assignedProduct";
	private static final String TAG_NAME_ID = "id";
	private static final String TAG_NAME_NAME = "name";
	private static final String TAG_NAME_PRICE = "price";
	private static final String TAG_NAME_SRC_IMG = "srcImg";
	private static final String TAG_NAME_ADDRESS = "address";
	private static final String TAG_NAME_PASSWORD = "password";

	// Metodos para leer archivos
	public static ArrayList<User> readUser() throws ParserConfigurationException, SAXException, IOException {
		ArrayList<User> userList = new ArrayList<>();
		Document document = readAnythingDocument(TYPE_USER);
		NodeList nodeList = document.getElementsByTagName(TAG_NAME_USER);
		for (int i = 0; i < nodeList.getLength(); i++) {
			userList.add(getUser((Element) nodeList.item(i)));
		}
		return userList;
	}

	public static ArrayList<Shop> readShop() throws ParserConfigurationException, SAXException, IOException {
		ArrayList<Shop> shopList = new ArrayList<>();
		Document document = readAnythingDocument(TYPE_SHOP);
		NodeList nodeList = document.getElementsByTagName(TAG_NAME_SHOP);
		for (int i = 0; i < nodeList.getLength(); i++) {
			shopList.add(getShop((Element) nodeList.item(i)));
		}
		return shopList;
	}

	public static ArrayList<Product> readProduct() throws ParserConfigurationException, SAXException, IOException {
		ArrayList<Product> productList = new ArrayList<>();
		Document document = readAnythingDocument(TYPE_PRODUCT);
		NodeList nodeList = document.getElementsByTagName(TAG_NAME_PRODUCT);
		for (int i = 0; i < nodeList.getLength(); i++) {
			productList.add(getProduct((Element) nodeList.item(i)));
		}
		return productList;
	}

	public static ArrayList<AssignmentProductShop> readAsigmentProducts()
			throws ParserConfigurationException, SAXException, IOException {
		ArrayList<AssignmentProductShop> assignmentProductShops = new ArrayList<>();
		Document document = readAnythingDocument(TYPE_ASSIGNED_PRODUCTS);
		NodeList nodeList = document.getElementsByTagName(TAG_NAME_ASSIGNED_PRODUCT);
		for (int i = 0; i < nodeList.getLength(); i++) {
			assignmentProductShops.add(new AssignmentProductShop(
					getProduct((Element) ((Element) nodeList.item(i)).getElementsByTagName(TAG_NAME_PRODUCT).item(0)),
					getShop((Element) ((Element) nodeList.item(i)).getElementsByTagName(TAG_NAME_SHOP).item(0))));
		}
		return assignmentProductShops;
	}

	private static Document readAnythingDocument(String type)
			throws ParserConfigurationException, SAXException, IOException {
		File file = new File("src/data/" + type + ".xml");
		DocumentBuilder builder = (DocumentBuilderFactory.newInstance()).newDocumentBuilder();
		Document document = builder.parse(file);
		document.getDocumentElement().normalize();
		return document;
	}

	// Metodos para escribir archivos
	public static void writeUser(ArrayList<User> users) throws TransformerException, ParserConfigurationException {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(NODE_USER);
		doc.appendChild(rootElement);
		for (User user : users) {
			rootElement.appendChild(writeUserElement(doc, user));
		}
		writeAnythingDocumentInXML(doc, TYPE_USER);
	}

	public static void writeShop(ArrayList<Shop> shops) throws TransformerException, ParserConfigurationException {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(NODE_SHOP);
		doc.appendChild(rootElement);
		Element lastId = doc.createElement(TAG_NAME_LAST_ID);
		int last = 0;
		for (Shop shop : shops) {
			rootElement.appendChild(writeShopElement(doc, shop));
			last = shop.getId();
		}
		lastId.appendChild(doc.createTextNode(String.valueOf(last)));
		rootElement.appendChild(lastId);
		writeAnythingDocumentInXML(doc, TYPE_SHOP);
	}

	public static void writeProduct(ArrayList<Product> products)
			throws TransformerException, ParserConfigurationException {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(NODE_PRODUCT);
		doc.appendChild(rootElement);
		Element lastId = doc.createElement(TAG_NAME_LAST_ID);
		int last = 0;
		for (Product product : products) {
			rootElement.appendChild(writeProductElement(doc, product));
			last = product.getId();
		}
		lastId.appendChild(doc.createTextNode(String.valueOf(last)));
		rootElement.appendChild(lastId);
		writeAnythingDocumentInXML(doc, TYPE_PRODUCT);
	}

	public void writeAssigmentProduct(ArrayList<AssignmentProductShop> assignmentProductShopList)
			throws TransformerException, ParserConfigurationException {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(NODE_ASSIGNED_LIST);
		doc.appendChild(rootElement);
		for (AssignmentProductShop assignmentProductShop : assignmentProductShopList) {
			Element elementAssigment = doc.createElement(TAG_NAME_ASSIGNED_PRODUCT);
			rootElement.appendChild(elementAssigment);

			Element id = doc.createElement(TAG_NAME_ID);
			id.appendChild(doc.createTextNode(String.valueOf(assignmentProductShop.getId())));
			elementAssigment.appendChild(id);

			Element product = writeProductElement(doc, assignmentProductShop.getProduct());
			elementAssigment.appendChild(product);

			Element shop = writeShopElement(doc, assignmentProductShop.getShop());
			elementAssigment.appendChild(shop);
		}
		writeAnythingDocumentInXML(doc, TYPE_ASSIGNED_PRODUCTS);
	}

	private static void writeAnythingDocumentInXML(Document doc, String type)
			throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/data/" + type + ".xml"));
		transformer.transform(source, result);
	}

	// metodos usados en la escritura para optimizacion
	public static Element writeProductElement(Document doc, Product product) {
		Element elementProduct = doc.createElement(TAG_NAME_PRODUCT);

		Element id = doc.createElement(TAG_NAME_ID);
		id.appendChild(doc.createTextNode(String.valueOf(product.getId())));
		elementProduct.appendChild(id);

		Element nombre = doc.createElement(TAG_NAME_NAME);
		nombre.appendChild(doc.createTextNode(product.getName()));
		elementProduct.appendChild(nombre);

		Element price = doc.createElement(TAG_NAME_PRICE);
		price.appendChild(doc.createTextNode(String.valueOf(product.getPrice())));
		elementProduct.appendChild(price);

		Element sourceImg = doc.createElement(TAG_NAME_SRC_IMG);
		sourceImg.appendChild(doc.createTextNode(product.getSrcImg()));
		elementProduct.appendChild(sourceImg);
		return elementProduct;
	}

	public static Element writeShopElement(Document doc, Shop shop) {
		Element elementShop = doc.createElement(TAG_NAME_SHOP);

		Element id = doc.createElement(TAG_NAME_ID);
		id.appendChild(doc.createTextNode(String.valueOf(shop.getId())));
		elementShop.appendChild(id);

		Element nombre = doc.createElement(TAG_NAME_NAME);
		nombre.appendChild(doc.createTextNode(shop.getName()));
		elementShop.appendChild(nombre);

		Element sourceImg = doc.createElement(TAG_NAME_SRC_IMG);
		sourceImg.appendChild(doc.createTextNode(shop.getSrcImg()));
		elementShop.appendChild(sourceImg);
		return elementShop;
	}

	public static Element writeUserElement(Document doc, User user) {
		Element elementUser = doc.createElement(TAG_NAME_USER);

		Element id = doc.createElement(TAG_NAME_ID);
		id.appendChild(doc.createTextNode(String.valueOf(user.getId())));
		elementUser.appendChild(id);

		Element nombre = doc.createElement(TAG_NAME_NAME);
		nombre.appendChild(doc.createTextNode(user.getName()));
		elementUser.appendChild(nombre);

		Element address = doc.createElement(TAG_NAME_ADDRESS);
		address.appendChild(doc.createTextNode(user.getAddress()));
		elementUser.appendChild(address);

		Element password = doc.createElement(TAG_NAME_PASSWORD);
		password.appendChild(doc.createTextNode(user.getPassword()));
		elementUser.appendChild(password);

		Element sourceImg = doc.createElement(TAG_NAME_SRC_IMG);
		sourceImg.appendChild(doc.createTextNode(user.getSourceImg()));
		elementUser.appendChild(sourceImg);
		return elementUser;
	}

	// Metodos para devolver las entidades
	public static Product getProduct(Element product) {
		return new Product(Integer.parseInt((product.getElementsByTagName(TAG_NAME_ID).item(0)).getTextContent()),
				(product.getElementsByTagName(TAG_NAME_NAME).item(0)).getTextContent(),
				Double.parseDouble((product.getElementsByTagName(TAG_NAME_PRICE).item(0)).getTextContent()),
				(product.getElementsByTagName(TAG_NAME_SRC_IMG).item(0)).getTextContent());
	}

	public static Shop getShop(Element shop) {
		return new Shop(Integer.parseInt((shop.getElementsByTagName(TAG_NAME_ID).item(0)).getTextContent()),
				(shop.getElementsByTagName(TAG_NAME_NAME).item(0)).getTextContent(),
				(shop.getElementsByTagName(TAG_NAME_SRC_IMG).item(0)).getTextContent());
	}

	public static User getUser(Element user) {
		return new User((user.getElementsByTagName(TAG_NAME_NAME).item(0)).getTextContent(),
				(user.getElementsByTagName(TAG_NAME_ADDRESS).item(0)).getTextContent(),
				(user.getElementsByTagName(TAG_NAME_PASSWORD).item(0)).getTextContent(),
				(user.getElementsByTagName(TAG_NAME_SRC_IMG).item(0)).getTextContent());
	}

	public static int getAcutalID(String nameDocument, String type)
			throws ParserConfigurationException, SAXException, IOException {
		NodeList principalNode = readAnythingDocument(nameDocument).getElementsByTagName(type);
		return Integer
				.parseInt(((Element) ((Element) principalNode.item(0)).getElementsByTagName(TAG_NAME_LAST_ID).item(0))
						.getTextContent());
	}
}