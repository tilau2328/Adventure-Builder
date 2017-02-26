package pt.ulisboa.tecnico.softeng.activity.domain;

import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActivityProviderFindOfferMethodTest {
	private ActivityProvider provider;
	private ActivityOffer offer;
	private LocalDate begin;
	private LocalDate end;
	
	@Before
	public void setUp() {
		this.provider = new ActivityProvider("XtremX", "ExtremeAdventure");
		Activity activity = new Activity(this.provider, "Bush Walking", 18, 80, 25);

		this.begin = new LocalDate(2016, 12, 19);
		this.end = new LocalDate(2016, 12, 21);
		this.offer = new ActivityOffer(activity, begin, end);
	}

	@Test
	public void success() {
		Set<ActivityOffer> offers = this.provider.findOffer(this.begin, this.end, 40);

		Assert.assertEquals(1, offers.size());
		Assert.assertTrue(offers.contains(this.offer));
	}

	@Test
	public void failure_wrong_begin() {
		Set<ActivityOffer> offers = this.provider.findOffer(new LocalDate(2016, 12, 20), this.end, 40);

		Assert.assertEquals(0, offers.size());
	}
	
	@Test
	public void failure_wrong_end() {
		Set<ActivityOffer> offers = this.provider.findOffer(this.begin, new LocalDate(2016, 12, 20), 40);

		Assert.assertEquals(0, offers.size());
	}
	
	@Test
	public void failure_invalid_age() {
		Set<ActivityOffer> offers = this.provider.findOffer(this.begin, this.end, 0);

		Assert.assertEquals(0, offers.size());
	}
	
	@After
	public void tearDown() {
		ActivityProvider.providers.clear();
	}

}
