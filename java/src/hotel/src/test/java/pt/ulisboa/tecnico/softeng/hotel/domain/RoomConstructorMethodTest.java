package pt.ulisboa.tecnico.softeng.hotel.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.hotel.domain.Room.Type;
import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;

public class RoomConstructorMethodTest {
	private Hotel hotel;

	@Before
	public void setUp() {
		this.hotel = new Hotel("XPTO123", "Lisboa");
	}

	@Test
	public void success() {
		Room room = new Room(this.hotel, "01", Type.DOUBLE);

		Assert.assertEquals(this.hotel, room.getHotel());
		Assert.assertEquals("01", room.getNumber());
		Assert.assertEquals(Type.DOUBLE, room.getType());
		Assert.assertEquals(1, this.hotel.getNumberOfRooms());
	}

	@Test
	public void failure_invalid_hotel() {
		try {
			new Room(null, "01", Type.DOUBLE);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}	
	}
	
	@Test
	public void failure_invalid_number() {
		try {
			new Room(this.hotel, null, Type.DOUBLE);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}	
	}
	
	@Test
	public void failure_number_not_integer() {
		try {
			new Room(this.hotel, "IO", Type.DOUBLE);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}	
	}
	
	@Test
	public void failure_invalid_type() {
		try {
			new Room(this.hotel, "01", null);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}	
	}
	
	@After
	public void tearDown() {
		Hotel.hotels.clear();
	}

}
