package com.example.myapp.model.items;

//import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.Battle;

public class バリア extends Item {
    public バリア() {
        super("バリア", 20);
        setText("HPを10回復します");
        unique = true;
    }

    @Override
    public void use(Battle battle, int i) {
        // battle.getPlayer().plusCondition(CreateCondition.B);
    }
}