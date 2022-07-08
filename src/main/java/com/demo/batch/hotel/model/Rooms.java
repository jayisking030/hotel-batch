package com.demo.batch.hotel.model;


public class Rooms {
	
	private int roomId;
	
	private String description;
	
	
	private int hotelId;
	
	

	public Rooms(int roomId, String description, int hotelId) {
		this.roomId = roomId;
		this.description = description;
		this.hotelId = hotelId;
	}



	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	
}
