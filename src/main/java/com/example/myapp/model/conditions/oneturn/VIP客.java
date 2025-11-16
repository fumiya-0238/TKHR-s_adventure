package com.example.myapp.model.conditions.oneturn;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;

public class VIP客 extends Condition {
	@Override
	public void newCondition(Living living) {
		setInfinity();
		amount = 1;
		((Player) living).plusPriceMulti(50);
	}

	@Override
	public void removeCondition(Living living) {
		((Player) living).plusPriceMulti(200);
	}
}
