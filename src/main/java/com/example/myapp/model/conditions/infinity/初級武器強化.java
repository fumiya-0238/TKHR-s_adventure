package com.example.myapp.model.conditions.infinity;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.service.GameService;

public class 初級武器強化 extends Condition {
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		amount = 1;
		if (living instanceof Player) {
			Player player = (Player) living;
			equip(player);
		}
	}

	public void plusAmount(Living living) {
		amount++;
		if (living instanceof Player) {
			Player player = (Player) living;
			equip(player);
		}
	}

	public void equip(Player player) {
		Weapon weapon = player.getWeapons().get(0);
		System.out.println(GameService.beginnerWeapons);
		if (GameService.beginnerWeapons.contains(weapon.getId())) {
			weapon.setPlusAttack(amount * 8);
			player.updateWeaponAttack();
		}
	}
}
