package com.example.myapp.model.conditions;

public class 気合サブスク状態 extends Condition{
	public 気合サブスク状態() {
		name="気合サブスク状態";
		duplication=true;
        perpetuity=true;
	}
    
    public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.TENTION_SUB;
    }
}
