package com.example.myapp.repository.book;

public class ActionPage extends BookPage {
	private boolean attackIs;

	public ActionPage(int id, String name, boolean attackIs) {
		super(id, name);
		this.attackIs = attackIs;
	}

	public boolean isAttackIs() {
		return attackIs;
	}

}
