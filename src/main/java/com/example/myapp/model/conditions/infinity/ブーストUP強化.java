package com.example.myapp.model.conditions.infinity;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;

public class ブーストUP強化 extends Condition {
	@Override
	public void newCondition(Living living) {
		setInfinity();
		((Player) living).plusMultiBoost(4);
	}

	@Override
	public void removeCondition(Living living) {
		((Player) living).plusMultiBoost(0.25);
	}

}
