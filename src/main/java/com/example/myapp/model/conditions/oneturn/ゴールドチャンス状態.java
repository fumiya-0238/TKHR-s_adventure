package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ゴールドチャンス状態 extends Condition {
	@Override
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos) {
		living.setGold(living.getGold() + infos.get(0).getFinalNumber());
	}
}
