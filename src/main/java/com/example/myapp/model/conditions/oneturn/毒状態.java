package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 毒状態 extends Condition {

	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		turn = 2;
		living.plusAttackPlus(-lv);
		battle.conditionMessage(living, name);
		if (living instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.毒を受けた, ""));
		}
	}
	
	@Override
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		living.plusAttackPlus(+lv);
		if (living instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.毒が回復, ""));
		}
	}

	public void setName() {
		setName("毒LV" + getAmount());
	}

}
