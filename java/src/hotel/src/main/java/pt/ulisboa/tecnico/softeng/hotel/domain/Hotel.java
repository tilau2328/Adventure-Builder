package pt.ulisboa.tecnico.softeng.hotel.domain;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import pt.ulisboa.tecnico.softeng.hotel.utils.ParamName;
import pt.ulisboa.tecnico.softeng.hotel.utils.ValidationUtils;
import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;

public class Hotel {
	public static Set<Hotel> hotels = new HashSet<>();

	static final int CODE_SIZE = 7;

	private final String code;
	private final String name;
	private final Set<Room> rooms = new HashSet<>();

	public Hotel(String code, String name) {
		validateArgs(code, name);

		this.code = code;
		this.name = name;
		Hotel.hotels.add(this);
	}

	private void validateArgs(String code, String name){
		ValidationUtils.validateArgument(name, ParamName.NAME);
		ValidationUtils.validateArgument(code, ParamName.CODE);
		checkCode(code);
	}
	
	private void checkCode(String code) {
		if (code.length() != Hotel.CODE_SIZE) {
			throw new HotelException("Invalid Hotel Code Size");
		}
		for(Hotel hotel : Hotel.hotels){
			if(hotel.getCode().equals(code)){
				throw new HotelException("Hotel Code Aready In Use");
			}
		}
	}

	public Room hasVacancy(Room.Type type, LocalDate arrival, LocalDate departure) {
		for (Room room : this.rooms) {
			if (room.isFree(type, arrival, departure)) {
				return room;
			}
		}
		return null;
	}

	public boolean hasRoom(String number) {
		for (Room room : this.rooms) {
			if (room.getNumber().equals(number)) {
				return true;
			}
		}
		return false;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public void addRoom(Room room) {
		this.rooms.add(room);
	}

	public int getNumberOfRooms() {
		return this.rooms.size();
	}

	public static String reserveHotel(Room.Type type, LocalDate arrival, LocalDate departure) {
		for (Hotel hotel : Hotel.hotels) {
			Room room = hotel.hasVacancy(type, arrival, departure);
			if (room != null) {
				return room.reserve(type, arrival, departure).getReference();
			}
		}
		return null;
	}

}
