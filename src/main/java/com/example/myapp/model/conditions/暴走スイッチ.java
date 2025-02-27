package com.example.myapp.model.conditions;

public class 暴走スイッチ extends Condition{
	public 暴走スイッチ() {
        name = "暴走スイッチ";
        perpetuity=true;
	}

    public void newCondition(){
        setTurn(1);
    }
	public CreateCondition getCondition(){
		return CreateCondition.CONTROL_SWITCH;
	}
}