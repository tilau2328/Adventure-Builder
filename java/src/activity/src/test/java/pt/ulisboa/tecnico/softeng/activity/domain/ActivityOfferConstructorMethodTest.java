package pt.ulisboa.tecnico.softeng.activity.domain;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;

public class ActivityOfferConstructorMethodTest {
	//private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private Activity activity;
	private LocalDate begin;
	private LocalDate end;
	
	@Before
	public void setUp() {
		ActivityProvider provider = new ActivityProvider("XtremX", "ExtremeAdventure");
		this.activity = new Activity(provider, "Bush Walking", 18, 80, 25);
		this.begin = new LocalDate(2016, 12, 19);
		this.end = new LocalDate(2016, 12, 21);
	}

	@Test
	public void success() {
		ActivityOffer offer = new ActivityOffer(this.activity, this.begin, this.end);

		Assert.assertEquals(this.begin, offer.getBegin());
		Assert.assertEquals(this.end, offer.getEnd());
		Assert.assertEquals(1, this.activity.getNumberOfOffers());
		Assert.assertEquals(0, offer.getNumberOfBookings());
	}

	@Test
	public void failure_invalid_activity() {
		try {
			new ActivityOffer(null, this.begin, this.end);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_begin() {
		try {
			new ActivityOffer(this.activity, null, this.end);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_end() {
		try {
			new ActivityOffer(this.activity, this.begin, null);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	
	@Test
	public void failure_invalid_date_diff() {
		try {
			new ActivityOffer(this.activity, this.begin, new LocalDate(2016, 12, 18));
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
