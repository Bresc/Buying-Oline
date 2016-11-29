package models.exceptions;

public class ErrorUserNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorUserNotFound() {
		super("Error, user not found");
	}
}