package com.example.myapp.repository.debug;

public class ShopFloorData extends TKHRData {
	private int floor;

	public ShopFloorData(int id, int floor) {
		super(id);
		this.floor = floor;
	}

	public int getFloor() {
		return floor;
	}

}
