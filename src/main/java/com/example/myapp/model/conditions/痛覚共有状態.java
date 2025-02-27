package com.example.myapp.model.conditions;

public class 痛覚共有状態 extends Condition{
	public 痛覚共有状態() {
		name="痛覚共有状態";
		duplication=true;
	}
	public void newCondition(){
		setTurn(1);
	}

public CreateCondition getCondition(){
return CreateCondition.SHARE_DAMAGE;
}
}
