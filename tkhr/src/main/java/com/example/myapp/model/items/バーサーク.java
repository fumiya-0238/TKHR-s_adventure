package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.BattleInterface;
import tkhr.battleScreen.itemPanel.ItemInterface;
import tkhr.battleScreen.messagePanel.MessagePanel;

public class バーサーク extends Item{
	public バーサーク() {
		super("バーサーク",20);
		setText("HPを10回復します");
		unique=true;
	}
	public void use(GameService battle,BattleInterface bi){
		//battle.getPlayer().setHP(10);
		bi.noWaitMessage(getName()+"を使った！");
	}
	@Override
	public void use(GameService battle, int i, MessagePanel message, ItemInterface iface) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
