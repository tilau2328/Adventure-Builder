package pt.ulisboa.tecnico.softeng.hotel.domain;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.hotel.domain.Room.Type;
import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;

public class RoomReserveMethodTest {
	private Room room;
	private LocalDate arrival;
	private LocalDate departure;
	
	@Before
	public void setUp() {
		Hotel hotel = new Hotel("XPTO123", "Lisboa");
		this.room = new Room(hotel, "01", Type.SINGLE);
		this.arrival = new LocalDate(2016, 12, 19);
		this.departure = new LocalDate(2016, 12, 24);
	}

	@Test
	public void success() {
		Booking booking = this.room.reserve(Type.SINGLE, arrival, departure);

		Assert.assertTrue(booking.getReference().length() > 0);
		Assert.assertEquals(this.arrival, booking.getArrival());
		Assert.assertEquals(this.departure, booking.getDeparture());
	}

	@Test
	public void failure_invalid_type() {
		try {
			this.room.reserve(Type.DOUBLE, arrival, departure);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_not_free() {
		this.room.reserve(Type.SINGLE, arrival, departure);
		try {
			this.room.reserve(Type.SINGLE, arrival, departure);
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
