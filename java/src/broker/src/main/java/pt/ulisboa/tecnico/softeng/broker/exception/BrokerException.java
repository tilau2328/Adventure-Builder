package pt.ulisboa.tecnico.softeng.broker.exception;

public class BrokerException extends RuntimeException {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 3664623359758491859L;

	public BrokerException() {
		super();
	}

	public BrokerException(String message) {
		super(message);
	}
}
