package com.example.myapp.repository.debug;

public class ConditionData extends TKHRData{
	private String name;
	private boolean duplication;
	private String text;

	public ConditionData(int id, String name, boolean duplication,String text) {
		super(id);
		this.name = name;
		this.duplication = duplication;
		this.text = text;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isDuplication() {
		return duplication;
	}


	public String getText() {
		return text;
	}

}
