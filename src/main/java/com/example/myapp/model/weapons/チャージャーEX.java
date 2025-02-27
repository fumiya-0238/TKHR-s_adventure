package com.example.myapp.model.weapons;

import com.example.myapp.model.items.強チャージ;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class チャージャーEX extends Weapon {
	public チャージャーEX() {
		super("チャージャーEX", 90, "ためると気合が50%上がり、強チャージを1個手に入れる。", 7);
	}

	@Override
	public void tension(Battle battle, ActionInfo info) {
		battle.getPlayer().setTension(50);
		battle.getPlayer().setItem(new 強チャージ());
	}
}
