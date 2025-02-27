package com.example.myapp.model.items;

import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.Battle;

public class ゴールドチャレンジ extends Item {

	public ゴールドチャレンジ() {
		super("ゴールドチャレンジ", 50);
		setText("この与えたダメージ分のゴールドがモンスターのゴールドに追加される");
	}

	@Override
	protected void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getMonster().plusCondition(CreateCondition.GOLDCHANCE);
	}

}
