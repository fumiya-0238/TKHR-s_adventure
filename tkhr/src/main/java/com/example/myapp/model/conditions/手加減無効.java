package com.example.myapp.model.conditions;
public class 手加減無効 extends Condition{
	public 手加減無効() {
		name="手加減無効";
        perpetuity=true;
	}
    
    public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.WEEK_INVALID;
    }
}