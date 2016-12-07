package models.exceptions;

public class ErrorOrderNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorOrderNotFound() {
		super("Error: order not found");
	}
}