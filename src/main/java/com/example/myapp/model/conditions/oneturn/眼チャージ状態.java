package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 眼チャージ状態 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターエフェクト, "2"));
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター隠れ, ""));
		living.plusAttackMulti(2);
		setInfinity();
		amount = 1;
	}
	
	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos) {
		ActionInfo info = infos.get(0);
		if (!info.getPenetrate()) {
			battle.conditionMessage(living, name);
			info.setNumber((int) (infos.get(0).getNumber() / Math.pow(2, amount)));
		}
	}
	
	@Override
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		living.plusAttackMulti(0.5);
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターエフェクト削除, ""));
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター表示, ""));
	}
}
