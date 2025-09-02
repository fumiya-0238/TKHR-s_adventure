package com.example.myapp.repository;

public class ScreenChange {
	private ScreenEnum screenEnum;
	private String status;

	public ScreenChange(ScreenEnum e, String status) {
		screenEnum = e;
		this.status = status;
	}

	public ScreenEnum getScreenEnum() {
		return screenEnum;
	}

	public String getStatus() {
		return status;
	}
	/*
		public void setScreenEnum(ScreenEnum screenEnum) {
			this.screenEnum = screenEnum;
		}
	*/
}
