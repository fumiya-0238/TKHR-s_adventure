package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 擬態の術状態 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		if (living instanceof Monster) {
			Monster monster = (Monster) living;
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター隠れ, String.valueOf(monster.getNumber())));
		}
	}

	public void attack(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		conditionEnd = true;
	}

	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		nowInfo.setNumber(0);
		nowInfo.addMessages("状態発動:<" + name + ">", living);
	}

	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		if (living instanceof Monster) {
			Monster monster = (Monster) living;
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター再表示, String.valueOf(monster.getNumber())));
		}
	}
}
