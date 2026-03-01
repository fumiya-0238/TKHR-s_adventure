
package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class 回復の胞子 extends Condition {
	@Override
	public void newCondition(Living living) {
		setInfinity();
	}

	@Override
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (living.getHP() <= 0) {
			return;
		}

		if (nowInfo.getActionType() == ActionTypeEnum.プレイヤー通常攻撃 || nowInfo.getActionType() == ActionTypeEnum.プレイヤー手加減
				|| nowInfo.getActionType() == ActionTypeEnum.プレイヤー強攻撃 || nowInfo.getActionType() == ActionTypeEnum.ダメージ
				|| nowInfo.getActionType() == ActionTypeEnum.火の玉 || nowInfo.getActionType() == ActionTypeEnum.モンスターの攻撃) {
			battle.getCommonEffect().commonHeal(living, 5, infos);
			nowInfo.addMessages("状態発動:<" + name + ">", living);
			battle.conditionMessage(living, name);
		}
	}
}
