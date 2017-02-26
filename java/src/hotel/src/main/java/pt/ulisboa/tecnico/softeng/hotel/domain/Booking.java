package pt.ulisboa.tecnico.softeng.hotel.domain;

import org.joda.time.LocalDate;

import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;
import pt.ulisboa.tecnico.softeng.hotel.utils.ParamName;
import pt.ulisboa.tecnico.softeng.hotel.utils.ValidationUtils;

public class Booking {
	private static int counter = 0;

	private final String reference;
	private final LocalDate arrival;
	private final LocalDate departure;

	public Booking(Hotel hotel, LocalDate arrival, LocalDate departure) {
		validateArgs(hotel, arrival, departure);
		this.reference = hotel.getCode() + Integer.toString(++Booking.counter);
		this.arrival = arrival;
		this.departure = departure;
	}

	private void validateArgs(Hotel hotel, LocalDate arrival, LocalDate departure){
		ValidationUtils.validateArgument(hotel, ParamName.HOTEL);
		ValidationUtils.validateArgument(arrival, ParamName.ARRIVAL);
		ValidationUtils.validateArgument(departure, ParamName.DEPARTURE);
		validateDateDiff(arrival, departure);
	}
	
	private void validateDateDiff(LocalDate arrival, LocalDate departure){
		if(departure.isBefore(arrival)){
			throw new HotelException();
		}
	}
	
	public String getReference() {
		return this.reference;
	}

	public LocalDate getArrival() {
		return this.arrival;
	}

	public LocalDate getDeparture() {
		return this.departure;
	}

	public boolean conflict(LocalDate arrival, LocalDate departure) {
		if ((this.arrival.isBefore(arrival) 
		  || this.arrival.equals(arrival)) 
		  && this.departure.isAfter(arrival)) {
			return true;
		}

		if (this.arrival.isBefore(departure) 
		&& (this.departure.isAfter(departure) 
		||  this.departure.equals(departure))) {
			return true;
		}

		if ((this.arrival.isAfter(arrival) 
		 ||  this.arrival.equals(arrival)) 
		 && (this.departure.isBefore(departure)
		 ||  this.departure.equals(departure))) {
			return true;
		}

		return false;
	}

}
