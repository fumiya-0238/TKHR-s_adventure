package tkhr.weapons;

import tkhr.Battle;
import tkhr.items.強チャージ;

public class チャージャーEX extends Weapon {
	public チャージャーEX() {
		super("チャージャーEX", 90, "ためると気合が50%上がり、強チャージを1個手に入れる。", 7);
	}

	public void tension(Battle battle) {
		battle.getPlayer().setTension(50);
		battle.setItem(new 強チャージ());
	}
}
