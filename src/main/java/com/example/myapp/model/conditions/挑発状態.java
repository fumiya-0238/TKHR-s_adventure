package com.example.myapp.model.conditions;

public class 挑発状態 extends Condition{
	public 挑発状態() {
		name="挑発状態";

	}	
	public void newCondition(){
			setTurn(1);
		}
	
	public CreateCondition getCondition(){
	return CreateCondition.TAUNT;
	}
}
