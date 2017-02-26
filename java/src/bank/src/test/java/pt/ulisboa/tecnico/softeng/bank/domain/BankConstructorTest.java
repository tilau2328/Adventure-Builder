package pt.ulisboa.tecnico.softeng.bank.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.bank.exception.BankException;

public class BankConstructorTest {
	private String name;
	private String code;
	
	@Before
	public void setUp() {
		this.name = "Money";
		this.code = "BK01";
	}

	@Test
	public void success() {
		Bank bank = new Bank(this.name, this.code);

		Assert.assertEquals(this.name, bank.getName());
		Assert.assertEquals(this.code, bank.getCode());
		Assert.assertEquals(1, Bank.banks.size());
		Assert.assertEquals(0, bank.getNumberOfAccounts());
		Assert.assertEquals(0, bank.getNumberOfClients());
	}

	@Test
	public void failure_invalid_name() {
		try {
			new Bank(null, this.code);
			Assert.fail();
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_code() {
		try {
			new Bank(this.name, null);
			Assert.fail();
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_code_size() {
		try {
			new Bank(this.name, "BK0");
			Assert.fail();
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_code_existing() {
		new Bank(this.name, this.code);
		try {
			new Bank(this.name, this.code);
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
