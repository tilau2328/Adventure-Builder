package pt.ulisboa.tecnico.softeng.activity.utils;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;
import pt.ulisboa.tecnico.softeng.activity.utils.ParamName;

public class ValidationUtils {
	static public void validateArgument(String arg, ParamName name){
		if(arg == null){
			throw new ActivityException();
		}
		
		if(StringUtils.EMPTY_STRING.equals(arg.trim())){
			throw new ActivityException();
		}
	}
	
	static public void validateArgument(Object arg, ParamName name){
		if(arg == null){
			throw new ActivityException();
		}
	}
	
	static public void validateArgument(int arg, ParamName name){
		if(arg <= 0){
			throw new ActivityException();
		}
	}
}
