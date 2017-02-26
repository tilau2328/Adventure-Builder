package pt.ulisboa.tecnico.softeng.bank.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.bank.exception.BankException;
import pt.ulisboa.tecnico.softeng.bank.utils.StringUtils;

public class BankGetAccountMethodTest {
	Bank bank;
	Client client;

	@Before
	public void setUp() {
		this.bank = new Bank("Money", "BK01");
		this.client = new Client(this.bank, "António");
	}

	@Test
	public void success() {
		Account account = new Account(this.bank, this.client);

		Account result = this.bank.getAccount(account.getIBAN());

		Assert.assertEquals(account, result);
	}

	@Test
	public void failure_invalid_argument() {
		try {
			this.bank.getAccount(StringUtils.EMPTY_STRING);
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
			return;
		}
	}
	
	@Test
	public void failure_inexistent_account() {
		try {
			this.bank.getAccount(StringUtils.DEFAULT_ACCOUNT);
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
			return;
		}
	}
	
	@After
	public void tearDown() {
		Bank.banks.clear();
	}

}
