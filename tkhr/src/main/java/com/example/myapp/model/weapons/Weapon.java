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

	public void attack(Battle battle) {
		battle.getInfo().setDamage(battle.getPlayer().getATK());
	}

	public void weekAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK() - 1;
		battle.getMonster().calcDamageResult(damage,
				(player.amountCondition(CreateCondition.PENETRATE) > 0), battle);
	}

	public void criticalAttack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = (int) (player.getATK() * 1.5);
		player.setCritical(-1);
		battle.getMonster().calcDamageResult(damage,
				(player.amountCondition(CreateCondition.PENETRATE) > 0), battle);
	}

	public void defence(GameService battle) {
		Player player = battle.getPlayer();
		int a = player.getMAXHP() / 10;
		int b = player.amountCondition(CreateCondition.DEFENCE_BUFF);
		player.healResult(a * (int) (Math.pow(2, b)));
	}

	public void tension(GameService battle) {
		battle.getPlayer().setTension(25);
	}

	@Override
	protected String useResult(GameService battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		return battle.getMonster().calcDamageResult(ATK, true, battle);
	}
}
