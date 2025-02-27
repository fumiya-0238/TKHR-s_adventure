package com.example.myapp.model.conditions;

public class 強執念 extends Condition{
	public 強執念() {
		name="強い執念";
        perpetuity=true;
	}

	public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.TENACITY_PLUS;
    }
}