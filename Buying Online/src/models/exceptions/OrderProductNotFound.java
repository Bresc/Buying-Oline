package models.exceptions;

public class OrderProductNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderProductNotFound() {
		super("Error, not fount product");
	}
}