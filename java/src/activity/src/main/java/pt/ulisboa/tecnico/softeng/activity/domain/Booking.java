package pt.ulisboa.tecnico.softeng.activity.domain;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;
import pt.ulisboa.tecnico.softeng.activity.utils.ParamName;
import pt.ulisboa.tecnico.softeng.activity.utils.ValidationUtils;

public class Booking {
	private static int counter = 0;

	private final String reference;

	public Booking(ActivityProvider provider, ActivityOffer offer) {
		validateArgs(provider, offer);
		this.reference = provider.getCode() + Integer.toString(++Booking.counter);
		offer.addBooking(this);
	}

	private void validateArgs(ActivityProvider provider, ActivityOffer offer){
		ValidationUtils.validateArgument(provider, ParamName.PROVIDER);
		ValidationUtils.validateArgument(offer, ParamName.OFFER);
		validateVacancy(offer);
	}
	
	private void validateVacancy(ActivityOffer offer){
		if(!offer.hasVacancy()){
			throw new ActivityException();
		}
	}
	
	public String getReference() {
		return this.reference;
	}
}
