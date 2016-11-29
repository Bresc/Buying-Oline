package models.exceptions;

public class ErrorShopNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorShopNotFound() {
		super("Error, Shop not Found");
	}
}