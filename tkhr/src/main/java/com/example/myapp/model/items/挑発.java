package com.example.myapp.model.items;

import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.Battle;

public class 挑発 extends Item {
    // 強攻撃を強制
    public 挑発() {
        super("挑発", 50);
        setText("次のターン強攻撃をさせる");
    }

    @Override
    public void use(Battle battle, int i) {
        // TODO 自動生成されたメソッド・スタブ
        battle.getMonster().plusCondition(CreateCondition.TAUNT);
    }
}
