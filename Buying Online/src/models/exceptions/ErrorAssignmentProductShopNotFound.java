package models.exceptions;

public class ErrorAssignmentProductShopNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorAssignmentProductShopNotFound() {
		super("Error: assignment not found");
	}
}