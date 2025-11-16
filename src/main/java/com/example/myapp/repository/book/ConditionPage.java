package com.example.myapp.repository.book;

public class ConditionPage extends BookPage {
	private boolean duplication;

	public ConditionPage(int id, String name, boolean duplication) {
		super(id, name);
		defaultPageName("condition");
		this.duplication = duplication;
	}

	public ConditionPage(ConditionPage page) {
		super(page);
		setPageName(page.getPageName());
		duplication = page.isDuplication();
	}

	public boolean isDuplication() {
		return duplication;
	}

}
