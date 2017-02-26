package pt.ulisboa.tecnico.softeng.bank.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.ulisboa.tecnico.softeng.bank.exception.BankException;
import pt.ulisboa.tecnico.softeng.bank.utils.ParamName;
import pt.ulisboa.tecnico.softeng.bank.utils.ValidationUtils;

public class Bank {
	public static Set<Bank> banks = new HashSet<>();

	public static final int CODE_SIZE = 4;
	
	private final String name;
	private final String code;
	private final Set<Account> accounts = new HashSet<>();
	private final Set<Client> clients = new HashSet<>();
	private final List<Operation> log = new ArrayList<>();

	public Bank(String name, String code) {
		validateArgs(name, code);
		this.name = name;
		this.code = code;

		Bank.banks.add(this);
	}

	private void validateArgs(String name, String code){
		ValidationUtils.validateArgument(name, ParamName.NAME);
		ValidationUtils.validateArgument(code, ParamName.CODE);
		checkCode(code);
	}
	
	private void checkCode(String code) {
		if (code.length() != Bank.CODE_SIZE) {
			throw new BankException("Invalid Code Size");
		}
		for(Bank bank : Bank.banks){
			if(bank.getCode().equals(code)){
				throw new BankException("Code Aready In Use");
			}
		}
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public int getNumberOfAccounts() {
		return this.accounts.size();
	}

	public int getNumberOfClients() {
		return this.clients.size();
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public boolean hasClient(Client client) {
		return this.clients.contains(client);
	}

	public void addClient(Client client) {
		this.clients.add(client);
	}

	public void addLog(Operation operation) {
		this.log.add(operation);
	}

	public Account getAccount(String IBAN) {
		ValidationUtils.validateArgument(IBAN, ParamName.IBAN);
		for (Account account : this.accounts) {
			if (account.getIBAN().equals(IBAN)) {
				return account;
			}
		}
		throw new BankException();
	}

	public Operation getOperation(String reference) {
		for (Operation operation : this.log) {
			if (operation.getReference().equals(reference)) {
				return operation;
			}
		}
		return null;
	}

	public static String processPayment(String IBAN, int amount) {
		for (Bank bank : Bank.banks) {
			if (bank.getAccount(IBAN) != null) {
				return bank.getAccount(IBAN).withdraw(amount);
			}
		}
		return null;
	}

}
