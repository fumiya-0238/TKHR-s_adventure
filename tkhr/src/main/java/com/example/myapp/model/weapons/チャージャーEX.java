package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;
import com.example.myapp.model.items.強チャージ;

public class チャージャーEX extends Weapon {
	public チャージャーEX() {
		super("チャージャーEX", 90, "ためると気合が50%上がり、強チャージを1個手に入れる。", 7);
	}

	public void tension(GameService battle) {
		battle.getPlayer().setTension(50);
		battle.getPlayer().setItem(new 強チャージ());
	}
}
