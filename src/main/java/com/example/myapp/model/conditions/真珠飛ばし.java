package com.example.myapp.model.conditions;

public class 真珠飛ばし extends Condition{
	public 真珠飛ばし() {
		name="真珠飛ばし";
	}
    
    public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.PEARL_ATTACK;
    }
}