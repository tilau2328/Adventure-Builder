package pt.ulisboa.tecnico.softeng.activity.domain.exception;

public class ActivityException extends RuntimeException {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -5948613136097454103L;

	public ActivityException() {
		super();
	}

	public ActivityException(String message) {
		super(message);
	}
}
