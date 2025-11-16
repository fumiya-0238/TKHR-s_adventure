package com.example.myapp.repository.debug;

public class ShopServiceData extends TKHRData {
	private int serviceId;
	private int stock;

	public ShopServiceData(int id, int serviceId, int stock) {
		super(id);
		this.serviceId = serviceId;
		this.stock = stock;
	}

	public int getServiceId() {
		return serviceId;
	}

	public int getStock() {
		return stock;
	}
}
