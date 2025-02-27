package com.example.myapp.model.conditions;

public class デスマッチ extends Condition{
    public デスマッチ() {
		name="デスマッチ";
        duplication=true;
	}
    public void newCondition(){
        setTurn(1);
    }
    public CreateCondition getCondition(){
        return CreateCondition.DEATH_MATCH;
    }
}
