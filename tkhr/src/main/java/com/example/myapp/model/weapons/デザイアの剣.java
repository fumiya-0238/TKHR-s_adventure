package tkhr.weapons;

import tkhr.Battle;

public class デザイアの剣 extends Weapon {
	public デザイアの剣() {
		super("デザイアの剣", 100, "いずれかの攻撃をすると、アイテム欄の1番下の最後が消滅する", 17);
	}
	
	public void attack(Battle battle) {
		super.attack(battle);
		int size = battle.getPlayer().getItems().size();
		if(size>0) {
			battle.removeItem(size-1);
			}
	}

	public void weekAttack(Battle battle) {
		super.weekAttack(battle);
		int size = battle.getPlayer().getItems().size();
		if(size>0) {
			battle.removeItem(size-1);
			}
	}

	public void criticalAttack(Battle battle) {
		super.criticalAttack(battle);
		int size = battle.getPlayer().getItems().size();
		if(size>0) {
			battle.removeItem(size-1);
			}
	}
}
