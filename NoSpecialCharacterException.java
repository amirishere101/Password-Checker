
public class NoSpecialCharacterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSpecialCharacterException() {
		super("The password must contain at least one special character");
	}

	public NoSpecialCharacterException(String message) {
		super(message);
	}
}
