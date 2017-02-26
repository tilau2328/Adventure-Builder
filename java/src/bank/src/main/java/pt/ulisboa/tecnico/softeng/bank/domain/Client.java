package pt.ulisboa.tecnico.softeng.bank.domain;

import pt.ulisboa.tecnico.softeng.bank.utils.ParamName;
import pt.ulisboa.tecnico.softeng.bank.utils.ValidationUtils;

public class Client {
	private static int counter = 0;

	private final String name;
	private final String ID;

	public Client(Bank bank, String name) {
		validateArgs(bank, name);
		this.ID = Integer.toString(++Client.counter);
		this.name = name;
		bank.addClient(this);
	}
	
	private void validateArgs(Bank bank, String name){
		ValidationUtils.validateArgument(name, ParamName.NAME);
		ValidationUtils.validateArgument(bank, ParamName.BANK);
	}

	public String getName() {
		return this.name;
	}

	public String getID() {
		return this.ID;
	}

}
