package pt.ulisboa.tecnico.softeng.activity.domain;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;

public class BookingContructorMethodTest {
	private ActivityProvider provider;
	private ActivityOffer offer;

	@Before
	public void setUp() {
		this.provider = new ActivityProvider("XtremX", "ExtremeAdventure");
		Activity activity = new Activity(this.provider, "Bush Walking", 18, 80, 1);

		LocalDate begin = new LocalDate(2016, 12, 19);
		LocalDate end = new LocalDate(2016, 12, 21);
		this.offer = new ActivityOffer(activity, begin, end);
	}

	@Test
	public void success() {
		Booking booking = new Booking(this.provider, this.offer);

		Assert.assertTrue(booking.getReference().startsWith(this.provider.getCode()));
		Assert.assertTrue(booking.getReference().length() > ActivityProvider.CODE_SIZE);
		Assert.assertEquals(1, this.offer.getNumberOfBookings());
	}

	@Test
	public void failure_invalid_provider() {
		try {
			new Booking(null, this.offer);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_offer() {
		try {
			new Booking(this.provider, null);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_no_vacancy() {
		new Booking(this.provider, this.offer);
		try {
			new Booking(this.provider, this.offer);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		ActivityProvider.providers.clear();
	}

}
