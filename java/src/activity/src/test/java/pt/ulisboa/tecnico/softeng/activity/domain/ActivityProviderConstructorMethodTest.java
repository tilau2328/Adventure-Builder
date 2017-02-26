package pt.ulisboa.tecnico.softeng.activity.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class ActivityProviderConstructorMethodTest {

	@Test
	public void success() {
		ActivityProvider provider = new ActivityProvider("XtremX", "Adventure++");

		Assert.assertEquals("Adventure++", provider.getName());
		Assert.assertEquals(ActivityProvider.CODE_SIZE, provider.getCode().length());
		Assert.assertEquals(1, ActivityProvider.providers.size());
		Assert.assertEquals(0, provider.getNumberOfActivities());
	}

	@After
	public void tearDown() {
		ActivityProvider.providers.clear();
	}

}
