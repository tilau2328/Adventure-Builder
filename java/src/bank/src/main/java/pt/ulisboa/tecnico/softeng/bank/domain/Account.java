package pt.ulisboa.tecnico.softeng.bank.domain;

import pt.ulisboa.tecnico.softeng.bank.exception.BankException;
import pt.ulisboa.tecnico.softeng.bank.utils.ParamName;
import pt.ulisboa.tecnico.softeng.bank.utils.ValidationUtils;

public class Account {
	private static int counter = 0;

	private final Bank bank;
	private final String IBAN;
	private final Client client;
	private int balance;

	public Account(Bank bank, Client client) {
		validateArgs(bank, client);
		this.bank = bank;
		this.IBAN = bank.getCode() + Integer.toString(++Account.counter);
		this.client = client;
		this.balance = 0;

		bank.addAccount(this);
	}

	private void validateArgs(Bank bank, Client client){
		ValidationUtils.validateArgument(bank, ParamName.BANK);
		ValidationUtils.validateArgument(client, ParamName.CLIENT);
		ValidationUtils.validateArgument(client, bank);
	}
	
	Bank getBank() {
		return this.bank;
	}

	public String getIBAN() {
		return this.IBAN;
	}

	public Client getClient() {
		return this.client;
	}

	public int getBalance() {
		return this.balance;
	}

	public String deposit(int amount) {
		this.balance = this.balance + amount;

		Operation operation = new Operation(Operation.Type.DEPOSIT, this, amount);
		return operation.getReference();
	}

	public String withdraw(int amount) {
		ValidationUtils.validateArgument(amount, ParamName.AMOUNT);
		if (amount > this.balance) {
			throw new BankException();
		}

		this.balance = this.balance - amount;

		return new Operation(Operation.Type.WITHDRAW, this, amount).getReference();
	}

}
