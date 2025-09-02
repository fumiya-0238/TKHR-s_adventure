package com.example.myapp.model.conditions.oneturn;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;

public class バーサーク状態 extends Condition {
	private int playerAttack;
	
	public void receiveDamage(Living living,ActionInfo info) {
		if(amount==0) {
			return;
		}
		int percent = living.getHP()*100/living.getMAXHP();
		if(percent<=20) {
		//living.setAttack(attack.);
		}
	}
}
