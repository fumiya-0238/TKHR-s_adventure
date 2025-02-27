package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public class 初級武器強化セット extends Item {
    public 初級武器強化セット() {
        super("初級武器強化セット", 1);
        setText("初級で販売されている武器の攻撃力を8上げる");
    }

    @Override
    protected void use(Battle battle, int i) {
        // TODO Auto-generated method stub
    }

}
