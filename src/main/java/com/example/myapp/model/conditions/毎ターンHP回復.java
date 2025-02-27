package com.example.myapp.model.conditions;

public class 毎ターンHP回復 extends Condition{
	public 毎ターンHP回復() {
        perpetuity=true;
	}
	public String getName() {
		return "毎ターンHP"+getAmount()+"回復";
	}

    public void newCondition(){
        setTurn(1);
    }
	public CreateCondition getCondition(){
		return CreateCondition.HP_UP;
	}
}