package pt.ulisboa.tecnico.softeng.hotel.domain;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;

public class BookingConstructorTest {
	private Hotel hotel;
	private LocalDate arrival;
	private LocalDate departure;
	
	@Before
	public void setUp() {
		this.hotel = new Hotel("XPTO123", "Londres");
		this.arrival = new LocalDate(2016, 12, 19);
		this.departure = new LocalDate(2016, 12, 21);
	}
	
	@Test
	public void success() {
		Booking booking = new Booking(hotel, arrival, departure);

		Assert.assertTrue(booking.getReference().startsWith(hotel.getCode()));
		Assert.assertTrue(booking.getReference().length() > Hotel.CODE_SIZE);
		Assert.assertEquals(arrival, booking.getArrival());
		Assert.assertEquals(departure, booking.getDeparture());
	}

	@Test
	public void failure_invalid_hotel() {
		try {
			new Booking(null, this.arrival, this.departure);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_arrival() {
		try {
			new Booking(this.hotel, null, this.departure);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_departure() {
		try {
			new Booking(this.hotel, this.arrival, null);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_date_diff() {
		try {
			new Booking(this.hotel, this.arrival, new LocalDate(2016, 12, 10));
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		Hotel.hotels.clear();
	}

}
