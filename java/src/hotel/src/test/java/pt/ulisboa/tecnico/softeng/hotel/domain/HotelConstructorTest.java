package pt.ulisboa.tecnico.softeng.hotel.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;

public class HotelConstructorTest {
	private String code;
	private String name;
	
	@Before
	public void setUp() {
		this.code = "XPTO123";
		this.name = "Londres";
	}

	@Test
	public void success() {
		Hotel hotel = new Hotel(this.code, this.name);

		Assert.assertEquals(this.name, hotel.getName());
		Assert.assertTrue(hotel.getCode().length() == Hotel.CODE_SIZE);
		Assert.assertEquals(0, hotel.getNumberOfRooms());
		Assert.assertEquals(1, Hotel.hotels.size());
	}

	@Test
	public void failure_invalid_code() {
		try {
			new Hotel(null, this.name);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}	
	}
	
	@Test
	public void failure_invalid_code_size() {
		try {
			new Hotel("XPTO12", this.name);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_code_existing() {
		new Hotel(this.code, this.name);
		try {
			new Hotel(this.code, this.name);
			Assert.fail();
		} catch(HotelException e){
			//Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void failure_invalid_name() {
		try {
			new Hotel(this.code, null);
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
