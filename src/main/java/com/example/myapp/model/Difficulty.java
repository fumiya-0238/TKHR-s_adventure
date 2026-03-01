package com.example.myapp.model;

public class Difficulty {
	private int id;
	private String logicalName;
	private String shortLogicalName;
	private String physicalName;

	private int startLV;

	public Difficulty(int id, String physicalName, String logicalName, String shortLogicalName, int startLV) {
		this.logicalName = logicalName;
		this.physicalName = physicalName;
		this.shortLogicalName = shortLogicalName;
		this.startLV = startLV;
	}

	public int getId() {
		return id;
	}

	public String getLogicalName() {
		return logicalName;
	}

	public String getShortLogicalName() {
		return shortLogicalName;
	}

	public String getPhysicalName() {
		return physicalName;
	}

	public int getLv() {
		return startLV;
	}
}
