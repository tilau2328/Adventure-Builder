package pt.ulisboa.tecnico.softeng.bank.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ulisboa.tecnico.softeng.bank.domain.Operation.Type;
import pt.ulisboa.tecnico.softeng.bank.exception.BankException;

public class OperationConstructorMethodTest {
	private final Logger logger = LoggerFactory.getLogger(OperationConstructorMethodTest.class);

	private Bank bank;
	private Account account;

	@Before
	public void setUp() {
		this.bank = new Bank("Money", "BK01");
		Client client = new Client(this.bank, "António");
		this.account = new Account(this.bank, client);
	}

	@Test
	public void success() {
		Operation operation = new Operation(Type.DEPOSIT, this.account, 1000);

		Assert.assertTrue(operation.getReference().startsWith(this.bank.getCode()));
		Assert.assertTrue(operation.getReference().length() > Bank.CODE_SIZE);
		Assert.assertEquals(Type.DEPOSIT, operation.getType());
		Assert.assertEquals(this.account, operation.getAccount());
		Assert.assertEquals(1000, operation.getValue());
		Assert.assertTrue(operation.getTime() != null);
		Assert.assertEquals(operation, this.bank.getOperation(operation.getReference()));
	}

	@Test
	public void failure_invalid_type() {
		try {
			new Operation(null, this.account, 1000);
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
			return;
		}
		Assert.fail();
	}
	
	@Test
	public void failure_invalid_account() {
		try {
			new Operation(Type.DEPOSIT, null, 1000);
		} catch(BankException e){
			//Assert.assertEquals("", e.getMessage());
			return;
		}
		Assert.fail();
	}
	
	@Test
	public void failure_invalid_value() {
		try {
			new Operation(Type.DEPOSIT, this.account, -1);
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
