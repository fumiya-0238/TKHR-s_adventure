package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ImagesRepository;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 眼チャージ状態 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		String number = String.valueOf(((Monster) living).getNumber());
		Battle.addLogs(
				new ScreenChange(ScreenEnum.モンスターエフェクト, ImagesRepository.INSTANCE.getMonsterEffect(2) + "#" + number));
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター隠れ, number));
		living.plusAttackMulti(2);
		turn = 2;
		amount = 1;
	}
	
	
	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (!nowInfo.getPenetrate()) {
			battle.conditionMessage(living, name);
			nowInfo.setNumber((int) (nowInfo.getNumber() / Math.pow(2, amount)));
		}
	}

	@Override
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		String number = String.valueOf(((Monster) living).getNumber());
		living.plusAttackMulti(0.5);
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターエフェクト削除, number));
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター再表示, number));
	}
}
