package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class 光の玉 extends Item {
	public 光の玉() {
		super("光の玉", 1);
		setText("敵に7ダメージ 強攻撃回数+1");
	}

		@Override
		public String useResult(Battle battle, int i,ItemInterface itemInterface) {
			// TODO 自動生成されたメソッド・スタブ
			itemInterface.removeItem(i);
			battle.getPlayer().setCritical(1);
			return battle.getMonster().calcDamageResult(7,true,battle);
		}
	}
