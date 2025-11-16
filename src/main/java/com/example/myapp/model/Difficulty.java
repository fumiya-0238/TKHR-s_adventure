package com.example.myapp.model;

public class Difficulty {
	private String logicalName;
	private String physicalName;
	private int startLV;

	public Difficulty(String physicalName, String logicalName, int startLV) {
		this.logicalName = logicalName;
		this.physicalName = physicalName;
		this.startLV = startLV;
	}

	public String getLogicalName() {
		return logicalName;
	}

	public String getPhysicalName() {
		return physicalName;
	}

	public int getLv() {
		return startLV;
	}
}
