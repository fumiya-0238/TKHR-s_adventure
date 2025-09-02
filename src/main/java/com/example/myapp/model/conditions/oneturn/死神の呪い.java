package com.example.myapp.model.conditions.oneturn;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.Battle;

public class 死神の呪い extends Condition {
	@Override
	public void newCondition(Battle battle, Living living) {
		setTurn("13");
	}

	@Override
	public void turnEnd(Battle battle, Living living) {
		int turn = Integer.parseInt(getTurn());
		System.out.println(turn);
		if (turn > 0) {
			turn--;
			if (turn == 0) {
				living.setDamage(living.getHP());
			}
		}
		setTurn(String.valueOf(turn));
	}
}
