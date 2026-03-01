package com.example.myapp.repository.book;

import com.example.myapp.creater.CreateText;

public class ActionPage extends BookPage {
	private boolean attackIs;

	public ActionPage(int id, String name, boolean attackIs) {
		super(id, name);
		defaultPageName("action");
		this.attackIs = attackIs;
	}

	public ActionPage(ActionPage page) {
		super(page);
		setPageName(page.getPageName());
		attackIs = page.isAttackIs();
	}

	public void setParam(int columnId, String after) {
		switch (columnId) {
		case 1:
			name = after;
			break;
		case 2:
			if (after.equals("1")) {
				attackIs = true;
			} else {
				attackIs = false;
			}
			break;
		case 3:
			CreateText.INSTANCE.macherFind(this, after);
			break;
		}
	}

	public boolean isAttackIs() {
		return attackIs;
	}

}
