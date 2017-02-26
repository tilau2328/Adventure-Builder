package pt.ulisboa.tecnico.softeng.bank.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.bank.exception.BankException;

public class ClientContructorMethodTest {
	private Bank bank;
	private String name;
	
	@Before
	public void setUp() {
		this.bank = new Bank("Money", "BK01");
		this.name = "António";
	}

	@Test
	public void success() {
		Client client = new Client(this.bank, this.name);

		Assert.assertEquals(this.name, client.getName());
		Assert.assertTrue(client.getID().length() >= 1);
		Assert.assertTrue(this.bank.hasClient(client));
	}

	@Test
	public void failure_invalid_bank() {
		try {
			new Client(null, this.name);
			Assert.fail();
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_name() {
		try {
			new Client(this.bank, null);
			Assert.fail();
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		Bank.banks.clear();
	}

}
