package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class ゴールドチャレンジ extends SystemItem{
	
	public ゴールドチャレンジ() {
		super("ゴールドチャレンジ", 50);
		setText("この与えたダメージ分のゴールドがモンスターのゴールドに追加される");
	}
	@Override
	protected String useResult(GameService battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		return "";
	}

}
