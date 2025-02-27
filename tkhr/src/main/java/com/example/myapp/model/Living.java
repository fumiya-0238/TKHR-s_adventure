package com.example.myapp.model;

import java.util.List;
import java.util.ArrayList;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.conditions.CreateCondition;

public abstract class Living {
	protected int HP;
	protected int MAXHP;
	protected int ATK;
	protected List<Condition> conditions;

	public Living() {
		conditions = new ArrayList<Condition>();
	}

	public void plusCondition(CreateCondition c) {
		for (Condition condition : conditions) {
			if (condition.getCondition() == c) {
				condition.plusAmount();
				return;
			}
		}
		conditions.add(c.getCondition());
	}

	public int amountCondition(CreateCondition c) {
		for (Condition condition : conditions) {
			if (condition.getCondition() == c) {
				return condition.getAmount();
			}
		}
		return 0;
	}

	public int getHP() {
		return HP;
	}

	public int getMAXHP() {
		return MAXHP;
	}

	public int getATK() {
		return ATK;
	}
}
