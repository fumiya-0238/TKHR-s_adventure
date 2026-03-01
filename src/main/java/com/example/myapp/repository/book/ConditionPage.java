package com.example.myapp.repository.book;

import com.example.myapp.creater.CreateText;

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
	
	public void setParam(int columnId, String after) {
		switch (columnId) {
		case 1:
			name = after;
			break;
		case 2:
			if (after.equals("1")) {
				duplication = true;
			} else {
				duplication = false;
			}
			break;
		case 3:
			CreateText.INSTANCE.macherFind(this, after);
			break;
		}
	}
	
	public boolean isDuplication() {
		return duplication;
	}

}
