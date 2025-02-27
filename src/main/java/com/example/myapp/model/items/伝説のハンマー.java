package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public class 伝説のハンマー extends Item {
    public 伝説のハンマー() {
        super("伝説のハンマー", 500);
        setText("デュランダルとエクスカリバーを同時に装備すると合体させる");
    }

    @Override
    protected void use(Battle battle, int i) {
        // TODO Auto-generated method stub
    }
}
