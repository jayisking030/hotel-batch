package com.demo.batch.hotel.model;




public class Hotels {
	private int id;
	
	private String hotelName;
	
	private String description;
	
	private String cityCode;
	

	public Hotels(){
	}


	public int getId() {
		return id;
	}

	public Hotels(int id, String hotelName, String description, String cityCode) {
		this.id = id;
		this.hotelName = hotelName;
		this.description = description;
		this.cityCode = cityCode;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
