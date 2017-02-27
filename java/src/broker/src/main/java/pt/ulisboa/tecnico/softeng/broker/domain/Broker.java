package pt.ulisboa.tecnico.softeng.broker.domain;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ulisboa.tecnico.softeng.broker.exception.BrokerException;
import pt.ulisboa.tecnico.softeng.broker.utils.ParamName;
import pt.ulisboa.tecnico.softeng.broker.utils.ValidationUtils;

public class Broker {
	private static Logger logger = LoggerFactory.getLogger(Broker.class);

	public static Set<Broker> brokers = new HashSet<>();

	private final String code;
	private final String name;
	private final Set<Adventure> adventures = new HashSet<>();

	public Broker(String code, String name) {
		validateArgs(code, name);
		this.code = code;
		this.name = name;

		Broker.brokers.add(this);
	}

	private void validateArgs(String code, String name){
		ValidationUtils.validateArgument(code, ParamName.CODE);
		ValidationUtils.validateArgument(name, ParamName.NAME);
		checkCode(code);
	}
	
	private void checkCode(String code) {
		for (Broker broker : Broker.brokers) {
			if (broker.getCode().equals(code)) {
				throw new BrokerException();
			}
		}
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public int getNumberOfAdventures() {
		return this.adventures.size();
	}

	public void addAdventure(Adventure adventure) {
		this.adventures.add(adventure);
	}

	public boolean hasAdventure(Adventure adventure) {
		return this.adventures.contains(adventure);
	}

}
