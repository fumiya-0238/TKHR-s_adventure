package com.example.myapp.repository.book;

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
	
	public boolean isAttackIs() {
		return attackIs;
	}

}
