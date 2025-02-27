package com.example.myapp.model.conditions;

public class カウンター extends Condition{
    public カウンター() {
		name="カウンター";
        duplication=true;
	}
    public void newCondition(){
        setTurn(1);
    }
    public CreateCondition getCondition(){
        return CreateCondition.COUNTER;
    }
}
