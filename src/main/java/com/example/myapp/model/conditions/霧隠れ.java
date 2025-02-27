package com.example.myapp.model.conditions;

public class 霧隠れ extends Condition{
	public 霧隠れ() {
        name="霧隠れ";

	}

    public void newCondition(){
        setTurn(1);
    }
	public CreateCondition getCondition(){
		return CreateCondition.STEALTH;
	}
}
