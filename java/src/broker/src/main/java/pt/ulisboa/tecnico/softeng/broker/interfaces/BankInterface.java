package pt.ulisboa.tecnico.softeng.broker.interfaces;

import pt.ulisboa.tecnico.softeng.bank.domain.Bank;

public class BankInterface {
	public static String processPayment(String IBAN, int amount) {
		return Bank.processPayment(IBAN, amount);
	}
}
