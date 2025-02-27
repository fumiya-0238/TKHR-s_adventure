package com.example.myapp.model.items;

public class 挑発 extends Item{
//強攻撃を強制
public 挑発() {
    super("挑発", 50);
    setText("HPを10回復します");
}

@Override
public String useResult(GameService battle, int i,ItemInterface itemInterface) {
    // TODO 自動生成されたメソッド・スタブ
    itemInterface.removeItem(i);
    return battle.getPlayer().healResult(10);
}
}
