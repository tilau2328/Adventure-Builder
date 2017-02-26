package pt.ulisboa.tecnico.softeng.hotel.exception;

public class HotelException extends RuntimeException {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1742823080086758725L;

	public HotelException() {
		super();
	}

	public HotelException(String message) {
		super(message);
	}
}
