package pt.ulisboa.tecnico.softeng.hotel.domain;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookingConflictMethodTest {
	private Booking booking;
	private LocalDate arrival;
	private LocalDate departure;
	
	@Before
	public void setUp() {
		this.arrival = new LocalDate(2016, 12, 19);
		this.departure = new LocalDate(2016, 12, 24);
		this.booking = new Booking(new Hotel("XPTO123", "Londres"), arrival, departure);
	}

	@Test
	public void noConflictBefore() {
		Assert.assertFalse(this.booking.conflict(this.arrival.minusDays(1), this.arrival));
	}

	@Test
	public void noConflictAfter() {
		Assert.assertFalse(this.booking.conflict(this.departure, this.departure.plusDays(1)));
	}

	@Test
	public void conflictBefore() {
		Assert.assertTrue(this.booking.conflict(this.arrival.minusDays(1), this.departure.minusDays(1)));
	}
	
	@Test
	public void conflictCenter() {
		Assert.assertTrue(this.booking.conflict(this.arrival.plusDays(1), this.departure.minusDays(1)));
	}
	
	@Test
	public void conflictAfter() {
		Assert.assertTrue(this.booking.conflict(this.arrival.plusDays(1), this.departure.plusDays(1)));
	}
	
	@Test
	public void conflictEquals() {
		Assert.assertTrue(this.booking.conflict(this.arrival, this.departure));
	}
	
	@After
	public void tearDown() {
		Hotel.hotels.clear();
	}

}
