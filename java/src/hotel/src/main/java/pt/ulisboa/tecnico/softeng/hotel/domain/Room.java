package pt.ulisboa.tecnico.softeng.hotel.domain;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;
import pt.ulisboa.tecnico.softeng.hotel.utils.ParamName;
import pt.ulisboa.tecnico.softeng.hotel.utils.ValidationUtils;

public class Room {
	//private static Logger logger = LoggerFactory.getLogger(Room.class);

	public static enum Type {
		SINGLE, DOUBLE
	}

	private final Hotel hotel;
	private final String number;
	private final Type type;
	private final Set<Booking> bookings = new HashSet<>();

	public Room(Hotel hotel, String number, Type type) {
		validateArgs(hotel, number, type);
		this.hotel = hotel;
		this.number = number;
		this.type = type;

		this.hotel.addRoom(this);
	}

	private void validateArgs(Hotel hotel, String number, Type type){
		ValidationUtils.validateArgument(hotel, ParamName.HOTEL);
		ValidationUtils.validateArgument(number, ParamName.NUMBER);
		validateRoom(hotel, number);
		ValidationUtils.validateArgument(type, ParamName.TYPE);
	}
	
	private void validateRoom(Hotel hotel, String number){
		try {
			Integer.parseInt(number);
		} catch(NumberFormatException e) {
			throw new HotelException();
		}
		if(hotel.hasRoom(number)){
			throw new HotelException();
		}
	}
	
	public Hotel getHotel() {
		return this.hotel;
	}

	public String getNumber() {
		return this.number;
	}

	public Type getType() {
		return this.type;
	}

	public boolean isFree(Type type, LocalDate arrival, LocalDate departure) {
		if (!type.equals(this.type)) {
			return false;
		}

		for (Booking booking : this.bookings) {
			if (booking.conflict(arrival, departure)) {
				return false;
			}
		}
		
		return true;
	}

	public Booking reserve(Type type, LocalDate arrival, LocalDate departure) {
		if (!isFree(type, arrival, departure)) {
			throw new HotelException();
		}

		Booking booking = new Booking(this.hotel, arrival, departure);
		this.bookings.add(booking);

		return booking;
	}

}
