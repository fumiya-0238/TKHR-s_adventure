package com.example.myapp.model.weapons;

import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.model.items.Item;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public abstract class Weapon extends Item {
	private int ATK;

	public Weapon(String name, int price, String text, int ATK) {
		super(name, price);
		String info = name + " 攻撃力 " + ATK + "" + text;
		setText(info);
		this.ATK = ATK;
	}

	public int getATK() {
		return ATK;
	}

	public void attack(Battle battle, ActionInfo info) {
		Player player = battle.getPlayer();
		info.setAttackIsTrue();
		info.setDamage(player.getATK());
		info.setPenetrate(player.amountCondition(CreateCondition.PENETRATE) > 0);
	}

	public void weekAttack(Battle battle, ActionInfo info) {
		Player player = battle.getPlayer();
		info.setAttackIsTrue();
		info.setDamage(player.getATK() - 1);
		info.setPenetrate(player.amountCondition(CreateCondition.PENETRATE) > 0);
	}

	public void criticalAttack(Battle battle, ActionInfo info) {
		Player player = battle.getPlayer();
		player.setCritical(-1);
		info.setAttackIsTrue();
		info.setDamage((int) (player.getATK() * 1.5));
		info.setPenetrate(player.amountCondition(CreateCondition.PENETRATE) > 0);
	}

	public void defence(Battle battle, ActionInfo info) {
		Player player = battle.getPlayer();
		int a = player.getMAXHP() / 10;
		int b = player.amountCondition(CreateCondition.DEFENCE_BUFF);
		player.healResult(a * (int) (Math.pow(2, b)));
	}

	public void tension(Battle battle, ActionInfo info) {
		battle.getPlayer().setTension(25);
	}

	@Override
	protected void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		ActionInfo info = new ActionInfo();
		info.setAttackIsTrue();
		info.setDamage(ATK);
		info.setPenetrate(true);
		battle.getMonster().calcDamageResult(battle, info);
	}
}
