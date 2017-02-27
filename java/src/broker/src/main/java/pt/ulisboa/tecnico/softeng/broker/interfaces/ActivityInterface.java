package pt.ulisboa.tecnico.softeng.broker.interfaces;

import org.joda.time.LocalDate;

import pt.ulisboa.tecnico.softeng.activity.domain.ActivityProvider;
import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;
import pt.ulisboa.tecnico.softeng.broker.exception.BrokerException;

public class ActivityInterface {
	public static String reserveActivity(LocalDate begin, LocalDate end, int age) {
		try {
			return ActivityProvider.reserveActivity(begin, end, age);
		} catch(ActivityException e){
			throw new BrokerException(e.getMessage());
		}
	}
}
