package pt.ulisboa.tecnico.softeng.activity.domain;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;
import pt.ulisboa.tecnico.softeng.activity.utils.ParamName;
import pt.ulisboa.tecnico.softeng.activity.utils.ValidationUtils;

public class Activity {
	private static int counter = 0;

	private final String name;
	private final String code;
	private final int minAge;
	private final int maxAge;
	private final int capacity;
	private final Set<ActivityOffer> offers = new HashSet<>();

	public Activity(ActivityProvider provider, String name, int minAge, int maxAge, int capacity) {
		validateArgs(provider, name, minAge, maxAge, capacity);
		this.code = provider.getCode() + Integer.toString(++Activity.counter);
		this.name = name;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.capacity = capacity;

		provider.addActivity(this);
	}

	private void validateArgs(ActivityProvider provider, String name, int minAge, int maxAge, int capacity){
		ValidationUtils.validateArgument(provider, ParamName.PROVIDER);
		ValidationUtils.validateArgument(name, ParamName.NAME);
		ValidationUtils.validateArgument(minAge, ParamName.MIN_AGE);
		validateMinAge(minAge);
		ValidationUtils.validateArgument(maxAge, ParamName.MAX_AGE);
		validateMaxAge(maxAge);
		validateAgeDiff(maxAge - minAge);
		ValidationUtils.validateArgument(capacity, ParamName.CAPACITY);
	}
	
	private void validateMinAge(int age){
		if(age < 18){
			throw new ActivityException();
		}
	}
	
	private void validateMaxAge(int age){
		if(age >= 100){
			throw new ActivityException();
		}
	}
	
	private void validateAgeDiff(int diff){
		if(diff < 0){
			throw new ActivityException();
		}
	}
	
	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public int getMinAge() {
		return this.minAge;
	}

	public int getMaxAge() {
		return this.maxAge;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public int getNumberOfOffers() {
		return this.offers.size();
	}

	public void addOffer(ActivityOffer offer) {
		this.offers.add(offer);
	}

	public Set<ActivityOffer> getOffers(LocalDate begin, LocalDate end, int age) {
		Set<ActivityOffer> result = new HashSet<>();
		for (ActivityOffer offer : this.offers) {
			if (matchAge(age) && offer.available(begin, end)) {
				result.add(offer);
			}
		}
		return result;
	}

	public boolean matchAge(int age) {
		return age >= this.minAge && age <= this.maxAge;
	}

}
