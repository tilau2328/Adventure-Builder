package pt.ulisboa.tecnico.softeng.broker.interfaces;

import org.joda.time.LocalDate;

import pt.ulisboa.tecnico.softeng.broker.exception.BrokerException;
import pt.ulisboa.tecnico.softeng.hotel.domain.Hotel;
import pt.ulisboa.tecnico.softeng.hotel.domain.Room;
import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;

public class HotelInterface {
	public static String reserveHotel(Room.Type type, LocalDate arrival, LocalDate departure) {
		try {
			return Hotel.reserveHotel(type, arrival, departure);
		} catch(HotelException e){
			throw new BrokerException(e.getMessage());
		}
	}
}
