package com.example.myapp.model.services;

import com.example.myapp.model.Player;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class S13レベル1ダウン extends ServiceItem {
	@Override
	public void buy(Battle battle, int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		Player player = battle.getPlayer();
		if(player.getLv() == 1) {
			return;
		}
		player.setEXP(0);
		player.levelDown();
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーLV, String.valueOf(player.getLv())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーHP, String.valueOf(player.getHP())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー最大HP, String.valueOf(player.getMAXHP())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー攻撃力, String.valueOf(player.getFinalAttack())));
		int sum = 0;
		for (Weapon weapon : player.getWeapons()) {
			sum += weapon.getAttack();
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器攻撃力, String.valueOf(sum)));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー経験値, String.valueOf(player.getEXP())));
	}
}
