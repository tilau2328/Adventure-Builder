package pt.ulisboa.tecnico.softeng.broker.interfaces;

import pt.ulisboa.tecnico.softeng.bank.domain.Bank;
import pt.ulisboa.tecnico.softeng.bank.exception.BankException;
import pt.ulisboa.tecnico.softeng.broker.exception.BrokerException;

public class BankInterface {
	public static String processPayment(String IBAN, int amount) {
		try {
			return Bank.processPayment(IBAN, amount);
		} catch(BankException e){
			throw new BrokerException(e.getMessage());
		}
	}
}
