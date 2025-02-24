package tkhr.weapons;

import tkhr.Battle;
import tkhr.Player;
import tkhr.items.光の玉;

public class ライトブリンガー extends Weapon {
	public ライトブリンガー() {
		super("ライトブリンガー", 90, "防御をするとアイテム欄の1番下のアイテムが光の玉に変わる", 6);
	}
	
	@Override
	public void defence(Battle battle) {
		super.defence(battle);
		Player player = battle.getPlayer();
		int size=player.getItems().size();
		if(size>0) {
		battle.removeItem(size-1);
		player.setItem(new 光の玉());
		}
	}
}
