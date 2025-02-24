package tkhr.weapons;

import tkhr.Battle;
import tkhr.Player;
import tkhr.battleScreen.itemPanel.ItemInterface;
import tkhr.items.Item;

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
		Player player = battle.getPlayer();
		int damage = player.getATK();
		battle.getMonster().calcDamageResult(damage, (player.amountPlayerCondition("研ぎ石状態") > 0), battle);
	}

	public void weekAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK() - 1;
		battle.getMonster().calcDamageResult(damage, (player.amountPlayerCondition("研ぎ石状態") > 0), battle);
	}

	public void criticalAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = (int) (player.getATK() * 1.5);
		player.setCritical(-1);
		battle.getMonster().calcDamageResult(damage, (player.amountPlayerCondition("研ぎ石状態") > 0), battle);
	}

	public void defence(Battle battle) {
		Player player = battle.getPlayer();
		int a = player.getMAXHP() / 10;
		int b = player.amountPlayerCondition("防御強化状態");
		player.healResult(a*(int) (Math.pow(2, b)));
	}

	public void tension(Battle battle) {
		battle.getPlayer().setTension(25);
	}

	@Override
	protected String useResult(Battle battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		itemInterface.removeItem(i);
		return battle.getMonster().calcDamageResult(ATK,true,battle);
	}
}
