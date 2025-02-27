package com.example.myapp.model.conditions;
public class 毎ターンHP減少 extends Condition{
	public 毎ターンHP減少() {
        perpetuity=true;
	}
	public String getName() {
		return "毎ターンHP"+getAmount()+"減少";
	}

    public void newCondition(){
        setTurn(1);
    }
	public CreateCondition getCondition(){
		return CreateCondition.HP_DOWN;
	}
}