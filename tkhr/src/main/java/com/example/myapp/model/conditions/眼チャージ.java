package com.example.myapp.model.conditions;

public class 眼チャージ extends Condition{
	public 眼チャージ() {
		name="眼チャージ";
	}
    public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.EYE_CHARGE;
    }
}
