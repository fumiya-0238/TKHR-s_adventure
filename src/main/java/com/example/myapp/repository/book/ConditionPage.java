package com.example.myapp.repository.book;

public class ConditionPage extends BookPage {
	private boolean duplication;

	public ConditionPage(int id, String name, boolean duplication) {
		super(id, name);
		this.duplication = duplication;
	}

	public boolean isDuplication() {
		return duplication;
	}

}
