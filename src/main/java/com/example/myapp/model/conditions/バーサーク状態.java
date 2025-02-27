package com.example.myapp.model.conditions;

public class バーサーク状態 extends Condition{
	public バーサーク状態() {
		name="バーサーク状態";
	}
    public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.BERSERK;
    }
}
