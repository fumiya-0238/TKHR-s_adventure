package com.example.myapp.model.conditions.infinity;

import com.example.myapp.model.conditions.Condition;

public class ボーナスゲット extends Condition {
	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 1;
	}

}
