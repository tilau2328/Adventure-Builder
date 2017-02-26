package pt.ulisboa.tecnico.softeng.hotel.utils;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;
import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;

public class ValidationUtils {
	static public void validateArgument(String arg, ParamName name){
		if(arg == null || arg.trim().length() == 0){
			throw new ActivityException();
		}
	}
	
	static public void validateArgument(Object arg, ParamName name){
		if(arg == null){
			throw new HotelException();
		}
	}
	
	static public void validateArgument(int arg, ParamName name){
		if(arg <= 0){
			throw new HotelException();
		}
	}
}
