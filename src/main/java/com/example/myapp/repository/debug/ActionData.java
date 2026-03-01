package com.example.myapp.repository.debug;

public class ActionData extends TKHRData {
	private String name;
	private boolean attackIs;
	private String text;

	public ActionData(int id, String name, boolean attackIs, String text) {
		super(id);
		this.name = name;
		this.attackIs = attackIs;
		this.text = text;

	}

	public String getName() {
		return name;
	}

	public boolean isAttackIs() {
		return attackIs;
	}

	public String getText() {
		return text;
	}

}
