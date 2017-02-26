package pt.ulisboa.tecnico.softeng.bank.utils;

import pt.ulisboa.tecnico.softeng.bank.domain.Bank;
import pt.ulisboa.tecnico.softeng.bank.domain.Client;
import pt.ulisboa.tecnico.softeng.bank.exception.BankException;

public class ValidationUtils {
	static public void validateArgument(String arg, ParamName name){
		if(arg == null){
			throw new BankException();
		}
		
		if(StringUtils.EMPTY_STRING.equals(arg.trim())){
			throw new BankException();
		}
	}
	
	static public void validateArgument(Object arg, ParamName name){
		if(arg == null){
			throw new BankException();
		}
	}
	
	static public void validateArgument(int arg, ParamName name){
		if(arg <= 0){
			throw new BankException();
		}
	}
	
	static public void validateArgument(Client client, Bank bank){
		if(!bank.hasClient(client)){
			throw new BankException();
		}
	}
}
