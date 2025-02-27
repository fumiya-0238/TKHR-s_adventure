package com.example.myapp.model.conditions;

public class 防御状態 extends Condition{
	public 防御状態() {
		name="防御状態";
	}
	public void newCondition(){
		setTurn(1);
	}
	public CreateCondition getCondition(){
		return CreateCondition.DEFENCE;
	}
}