package com.example.myapp.repository.book;

import java.util.List;

public class PictureBook {
	private List<MonsterPage> monsterPages;
	private List<ActionPage> actionPages;
	private List<ConditionPage> conditionPages;
	private List<ItemPage> itemPages;
	private List<WeaponPage> weaponPages;

	public List<MonsterPage> getMonsterPages() {
		return monsterPages;
	}

	public List<ActionPage> getActionPages() {
		return actionPages;
	}

	public List<ConditionPage> getConditionPages() {
		return conditionPages;
	}

	public List<ItemPage> getItemPages() {
		return itemPages;
	}

	public List<WeaponPage> getWeaponPages() {
		return weaponPages;
	}

	public void setMonsterPages(List<MonsterPage> monsterPages) {
		this.monsterPages = monsterPages;
	}

	public void setActionPages(List<ActionPage> actionPages) {
		this.actionPages = actionPages;
	}

	public void setConditionPages(List<ConditionPage> conditionPages) {
		this.conditionPages = conditionPages;
	}

	public void setItemPages(List<ItemPage> itemPages) {
		this.itemPages = itemPages;
	}

	public void setWeaponPages(List<WeaponPage> weaponPages) {
		this.weaponPages = weaponPages;
	}

}
