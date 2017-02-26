package pt.ulisboa.tecnico.softeng.bank.exception;

public class BankException extends RuntimeException {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 3198352811146797903L;

	public BankException() {
		super();
	}

	public BankException(String message) {
		super(message);
	}
}
