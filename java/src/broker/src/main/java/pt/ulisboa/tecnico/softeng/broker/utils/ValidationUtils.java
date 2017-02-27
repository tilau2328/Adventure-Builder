package pt.ulisboa.tecnico.softeng.broker.utils;

import pt.ulisboa.tecnico.softeng.broker.exception.BrokerException;
import pt.ulisboa.tecnico.softeng.broker.utils.ParamName;

public class ValidationUtils {
	static public void validateArgument(String arg, ParamName name){
		if(arg == null || arg.trim().length() == 0){
			throw new BrokerException();
		}
	}
	
	static public void validateArgument(Object arg, ParamName name){
		if(arg == null){
			throw new BrokerException();
		}
	}
	
	static public void validateArgument(int arg, ParamName name){
		if(arg <= 0){
			throw new BrokerException();
		}
	}
}
