package com.example.myapp.model.conditions;

public class スライム状態 extends Condition{
    public スライム状態() {
		name="スライム状態";
	}
    public void newCondition(){
        setTurn(1);
    }
    public CreateCondition getCondition(){
        return CreateCondition.SLIME;
    }
}