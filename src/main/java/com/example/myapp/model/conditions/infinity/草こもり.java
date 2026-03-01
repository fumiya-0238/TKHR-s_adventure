package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class 草こもり extends Condition{
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 1;
	}
	
	@Override
	public void calcDamagePlus(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (nowInfo.getActionType() == ActionTypeEnum.火の玉) {
			nowInfo.addMessages("状態発動:<" + name + ">", living);
			battle.conditionMessage(living, name);
			nowInfo.setNumber(30);
			living.setGold(living.getGold() + nowInfo.getFinalNumber());
			amount = 0;
		}
	}
	
	@Override
	public void hpZero(Battle battle, Living living, List<ActionInfo> infos) {
		if(amount == 1) {
		battle.getPlayer().addItem(CreateItem.INSTANCE.create(1));
		}
	}
}
