package pt.ulisboa.tecnico.softeng.bank.domain;

import java.time.LocalDateTime;

import pt.ulisboa.tecnico.softeng.bank.utils.ParamName;
import pt.ulisboa.tecnico.softeng.bank.utils.ValidationUtils;

class Operation {
	public static enum Type {
		DEPOSIT, WITHDRAW
	};

	private static int counter = 0;

	private final String reference;
	private final Type type;
	private final Account account;
	private final int value;
	private final LocalDateTime time;

	public Operation(Type type, Account account, int value) {
		validateArgs(type, account, value);
		
		this.reference = account.getBank().getCode() + Integer.toString(++Operation.counter);
		this.type = type;
		this.account = account;
		this.value = value;
		this.time = LocalDateTime.now();

		account.getBank().addLog(this);
	}

	private void validateArgs(Type type, Account account, int value){
		ValidationUtils.validateArgument(type, ParamName.TYPE);
		ValidationUtils.validateArgument(account, ParamName.ACCOUNT);
		ValidationUtils.validateArgument(value, ParamName.VALUE);
	}
	
	public String getReference() {
		return this.reference;
	}

	public Type getType() {
		return this.type;
	}

	public Account getAccount() {
		return this.account;
	}

	public int getValue() {
		return this.value;
	}

	public LocalDateTime getTime() {
		return this.time;
	}

}
