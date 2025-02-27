package com.example.myapp.model.conditions;

public class 防御強化状態 extends Condition{
	public 防御強化状態() {
		name="防御強化状態";
		duplication=true;
	}
	public void newCondition(){
		setTurn(1);
	}
	public CreateCondition getCondition(){
		return CreateCondition.DEFENCE_BUFF;
	}
}
