package run;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.ControllerAdmin;

public class Run {

	public static void main(String[] args) {
		ControllerAdmin controller;
		try {
			controller = new ControllerAdmin();
			controller.run();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}