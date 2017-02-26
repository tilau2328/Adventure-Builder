package pt.ulisboa.tecnico.softeng.activity.domain;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;
import pt.ulisboa.tecnico.softeng.activity.utils.ParamName;
import pt.ulisboa.tecnico.softeng.activity.utils.ValidationUtils;

public class ActivityOffer {
	private final LocalDate begin;
	private final LocalDate end;
	private final int capacity;
	private final Set<Booking> bookings = new HashSet<>();

	public ActivityOffer(Activity activity, LocalDate begin, LocalDate end) {
		validateArgs(activity, begin, end);
		this.begin = begin;
		this.end = end;
		this.capacity = activity.getCapacity();
		
		activity.addOffer(this);
	}

	private void validateArgs(Activity activity, LocalDate begin, LocalDate end){
		ValidationUtils.validateArgument(activity, ParamName.ACTIVITY);
		ValidationUtils.validateArgument(begin, ParamName.BEGIN);
		ValidationUtils.validateArgument(end, ParamName.END);
		validateDates(begin, end);
	}
	
	private void validateDates(LocalDate begin, LocalDate end){
		if(end.isBefore(begin)){
			throw new ActivityException();
		}
	}
	
	public LocalDate getBegin() {
		return this.begin;
	}

	public LocalDate getEnd() {
		return this.end;
	}

	public int getNumberOfBookings() {
		return this.bookings.size();
	}

	public void addBooking(Booking booking) {
		this.bookings.add(booking);
	}

	public boolean available(LocalDate begin, LocalDate end) {
		return hasVacancy() && matchDate(begin, end);
	}

	public boolean matchDate(LocalDate begin, LocalDate end) {
		return begin.equals(getBegin()) && end.equals(getEnd());
	}

	public boolean hasVacancy() {
		return this.capacity > getNumberOfBookings();
	}

}
