package com.example.myapp.model.conditions;

public class 執念 extends Condition{
	public 執念() {
		name="執念";
        perpetuity=true;
	}

	public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.TENACITY;
    }

}
