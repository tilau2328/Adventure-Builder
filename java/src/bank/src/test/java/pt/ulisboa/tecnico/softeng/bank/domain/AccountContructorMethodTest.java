package pt.ulisboa.tecnico.softeng.bank.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.bank.exception.BankException;

public class AccountContructorMethodTest {
	private Bank bank;
	private Client client;

	@Before
	public void setUp() {
		this.bank = new Bank("Money", "BK01");
		this.client = new Client(this.bank, "António");
	}

	@Test
	public void success() {
		Account account = new Account(this.bank, this.client);

		Assert.assertEquals(this.bank, account.getBank());
		Assert.assertTrue(account.getIBAN().startsWith(this.bank.getCode()));
		Assert.assertEquals(this.client, account.getClient());
		Assert.assertEquals(0, account.getBalance());
		Assert.assertEquals(1, this.bank.getNumberOfAccounts());
		Assert.assertTrue(this.bank.hasClient(this.client));
	}

	@Test
	public void failure_invalid_bank() {
		this.bank = null;
		try {
			new Account(this.bank, this.client);
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
			return;
		}
		Assert.fail();
	}
	
	@Test
	public void failure_invalid_client() {
		this.client = null;
		try {
			new Account(this.bank, this.client);
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
			return;
		}
		Assert.fail();
	}
	
	@Test
	public void failure_invalid_client_bank() {
		this.client = new Client(new Bank("Cash", "BK02"), "António");
		try {
			new Account(this.bank, this.client);
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
			return;
		}
		Assert.fail();
	}
	
	@After
	public void tearDown() {
		Bank.banks.clear();
	}

}
