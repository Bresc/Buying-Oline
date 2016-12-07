package run;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.ControllerAdmin;
import controller.ControllerShop;
import controller.ControllerUser;
import controller.GeneralActions;
import controller.GeneralController;
import models.entities.User;

public class Run {

	public static void main(String[] args) {
//		ControllerAdmin controller;
//		ControllerShop controllerShop;
//		try {
//			controller = new ControllerAdmin();
//			new ControllerAdmin();
//			controllerShop = new ControllerShop();
//			new ControllerUser();
//			controllerShop.run();
//		} catch (ParserConfigurationException | SAXException | IOException e) {
//			
//			e.printStackTrace();
//		}
		new GeneralController();
	}
}