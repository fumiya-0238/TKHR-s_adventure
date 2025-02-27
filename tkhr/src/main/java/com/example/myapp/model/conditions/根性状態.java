package com.example.myapp.model.conditions;

public class 根性状態 extends Condition {
	public 根性状態() {
		name = "根性状態";
        perpetuity=true;
	}
    public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.GUTS;
    }
}