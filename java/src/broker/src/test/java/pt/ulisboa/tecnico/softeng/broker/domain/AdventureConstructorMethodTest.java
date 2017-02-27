package pt.ulisboa.tecnico.softeng.broker.domain;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.broker.exception.BrokerException;

public class AdventureConstructorMethodTest {
	private Broker broker;
	private LocalDate begin;
	private LocalDate end;
	
	@Before
	public void setUp() {
		this.broker = new Broker("BR01", "eXtremeADVENTURE");
		this.begin = new LocalDate(2016, 12, 19);
		this.end = new LocalDate(2016, 12, 21);
	}

	@Test
	public void success() {
		Adventure adventure = new Adventure(this.broker, this.begin, this.end, 20, "BK011234567", 300);

		Assert.assertEquals(this.broker, adventure.getBroker());
		Assert.assertEquals(this.begin, adventure.getBegin());
		Assert.assertEquals(this.end, adventure.getEnd());
		Assert.assertEquals(20, adventure.getAge());
		Assert.assertEquals("BK011234567", adventure.getIBAN());
		Assert.assertEquals(300, adventure.getAmount());
		Assert.assertTrue(this.broker.hasAdventure(adventure));

		Assert.assertNull(adventure.getBankPayment());
		Assert.assertNull(adventure.getActivityBooking());
		Assert.assertNull(adventure.getRoomBooking());
	}

	@Test
	public void failure_invalid_broker() {
		try {
			new Adventure(null, this.begin, this.end, 20, "BK011234567", 300);
			Assert.fail();
		} catch(BrokerException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_begin() {
		try {
			new Adventure(this.broker, null, this.end, 20, "BK011234567", 300);
			Assert.fail();
		} catch(BrokerException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_end() {
		try {
			new Adventure(this.broker, this.begin, null, 20, "BK011234567", 300);
			Assert.fail();
		} catch(BrokerException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_date_diff() {
		try {
			new Adventure(this.broker, this.begin, this.begin.minusDays(1), 20, "BK011234567", 0);
			Assert.fail();
		} catch(BrokerException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_age() {
		try {
			new Adventure(this.broker, this.begin, this.end, -1, "BK011234567", 300);
			Assert.fail();
		} catch(BrokerException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_iban() {
		try {
			new Adventure(this.broker, this.begin, this.end, 20, null, 300);
			Assert.fail();
		} catch(BrokerException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_amount() {
		try {
			new Adventure(this.broker, this.begin, this.end, 20, "BK011234567", 0);
			Assert.fail();
		} catch(BrokerException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		Broker.brokers.clear();
	}

}
