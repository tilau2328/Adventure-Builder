package pt.ulisboa.tecnico.softeng.activity.domain;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActivityOfferMatchDateMethodTest {
	private ActivityOffer offer;
	private LocalDate begin;
	private LocalDate end;
	
	@Before
	public void setUp() {
		ActivityProvider provider = new ActivityProvider("XtremX", "ExtremeAdventure");
		Activity activity = new Activity(provider, "Bush Walking", 18, 80, 3);

		this.begin = new LocalDate(2016, 12, 19);
		this.end = new LocalDate(2016, 12, 21);

		this.offer = new ActivityOffer(activity, this.begin, this.end);
	}

	@Test
	public void success() {
		Assert.assertTrue(this.offer.matchDate(this.begin, this.end));
	}

	@Test
	public void failure_wrong_begin() {
		Assert.assertFalse(this.offer.matchDate(this.begin.plusDays(1), this.end));
	}
	
	@Test
	public void failure_wrong_end() {
		Assert.assertFalse(this.offer.matchDate(this.begin, this.end.plusDays(1)));
	}
	
	@After
	public void tearDown() {
		ActivityProvider.providers.clear();
	}
}
