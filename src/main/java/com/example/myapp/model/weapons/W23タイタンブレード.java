package com.example.myapp.model.weapons;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class W23タイタンブレード extends Weapon {
	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		titanDefence(battle, infos);
		return super.criticalAttack(battle, infos);
	}

	public void titanDefence(Battle battle, List<ActionInfo> infos) {
		Player player = battle.getPlayer();
		player.plusBoost(player.finalBoostPlus());
		List<ActionInfo> defenceInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		String message = "プレイヤーは防御をした";
		info.addMessages(message);
		//Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));

		player.plusCondition(battle, defenceInfos, ConditionEnum.防御);

		for (Weapon weapon : player.getWeapons()) {
			weapon.defence(battle, defenceInfos);
		}
		for (Condition condition : player.getConditions()) {
			condition.defence(battle, player, defenceInfos, info);
		}
		defenceInfos.add(0, info);
		infos.addAll(defenceInfos);
	}
}
