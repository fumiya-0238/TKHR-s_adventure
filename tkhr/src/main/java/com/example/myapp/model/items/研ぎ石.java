package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public class 研ぎ石 extends Item {
    public 研ぎ石() {
        super("薬草", 10);
        setText("HPを10回復します");
    }

    @Override
    public String useResult(Battle battle, int i) {
        // TODO 自動生成されたメソッド・スタブ
        return battle.getPlayer().healResult(10);
    }
}