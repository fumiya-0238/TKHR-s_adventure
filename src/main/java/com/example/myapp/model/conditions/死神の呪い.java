package com.example.myapp.model.conditions;

public class 死神の呪い extends Condition{
	public 死神の呪い() {
		name="死神の呪い";
	}

	public void newCondition(){
        setTurn(13);
    }

    public CreateCondition getCondition(){
        return CreateCondition.CURSE;
    }
}
