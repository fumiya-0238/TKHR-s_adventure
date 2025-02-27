package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public class ブーストアップ extends Item {
    public ブーストアップ() {
        super("ブーストアップ", 10);
        setText("HPを10回復します");
    }

    @Override
    public void use(Battle battle, int i) {
        // TODO 自動生成されたメソッド・スタブ
        // battle.getPlayer().healResult(10);
    }
}
