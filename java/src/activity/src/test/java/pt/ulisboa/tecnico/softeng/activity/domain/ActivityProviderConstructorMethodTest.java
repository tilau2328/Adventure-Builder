package pt.ulisboa.tecnico.softeng.activity.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;

public class ActivityProviderConstructorMethodTest {
	private String code;
	private String name;
	
	@Before
	public void setUp() {
		this.code = "XtremX";
		this.name = "Adventure++";
	}	

	@Test
	public void success() {
		ActivityProvider provider = new ActivityProvider("XtremX", "Adventure++");

		Assert.assertEquals("Adventure++", provider.getName());
		Assert.assertEquals(ActivityProvider.CODE_SIZE, provider.getCode().length());
		Assert.assertEquals(1, ActivityProvider.providers.size());
		Assert.assertEquals(0, provider.getNumberOfActivities());
	}

	@Test
	public void failure_invalid_code() {
		try {
			new ActivityProvider(null, this.name);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_code_size_minus() {
		try {
			new ActivityProvider("Xtrem", this.name);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_code_size_plus() {
		try {
			new ActivityProvider("XXXtremX", this.name);
			Assert.fail();
		} catch(ActivityException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_name() {
		try {
			new ActivityProvider(this.code, null);
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
