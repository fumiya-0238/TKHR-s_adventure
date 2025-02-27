package com.example.myapp.model.conditions;

public class 研ぎ石状態 extends Condition{
	public 研ぎ石状態() {
		name="研ぎ石状態";	
	}
	public void newCondition(){
        setTurn(1);
    }
	public CreateCondition getCondition(){
        return CreateCondition.TENTION_SUB;
    }
}
