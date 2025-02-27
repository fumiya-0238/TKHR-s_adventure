package com.example.myapp.model.monsters.actions;

import com.example.myapp.repository.Battle;
/*import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.model.monsters.actions.AttackActionList;*/

public enum AssistActionList {
	INSTANCE;

	public void heal(Battle battle, int heal) {
		battle.getMonster().setHeal(heal);
		// messagePanel.addMessage(monster.getName()+"の攻撃。"+heal+"回復した。");
	}
}
