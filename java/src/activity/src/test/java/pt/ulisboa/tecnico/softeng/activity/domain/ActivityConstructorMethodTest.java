package pt.ulisboa.tecnico.softeng.activity.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;

public class ActivityConstructorMethodTest {
	private ActivityProvider provider;

	@Before
	public void setUp() {
		this.provider = new ActivityProvider("XtremX", "ExtremeAdventure");
	}

	@Test
	public void success() {
		Activity activity = new Activity(this.provider, "Bush Walking", 18, 80, 25);

		Assert.assertTrue(activity.getCode().startsWith(this.provider.getCode()));
		Assert.assertTrue(activity.getCode().length() > ActivityProvider.CODE_SIZE);
		Assert.assertEquals("Bush Walking", activity.getName());
		Assert.assertEquals(18, activity.getMinAge());
		Assert.assertEquals(80, activity.getMaxAge());
		Assert.assertEquals(25, activity.getCapacity());
		Assert.assertEquals(0, activity.getNumberOfOffers());
		Assert.assertEquals(1, this.provider.getNumberOfActivities());
	}

	@Test
	public void failure_invalid_provider() {
		try {
			new Activity(null, "Bush Walking", 18, 80, 25);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_name() {
		try {
			new Activity(this.provider, null, 18, 80, 25);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_min_age() {
		try {
			new Activity(this.provider, "Bush Walking", 17, 80, 25);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_max_age() {
		try {
			new Activity(this.provider, "Bush Walking", 18, 101, 25);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_age_diff() {
		try {
			new Activity(this.provider, "Bush Walking", 51, 50, 25);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_capacity() {
		try {
			new Activity(this.provider, "Bush Walking", 18, 80, 0);
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
