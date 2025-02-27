package com.example.myapp.model.conditions;

public class トゲトゲ extends Condition{
    public トゲトゲ() {
		name="トゲトゲ";
        duplication=true;
        perpetuity=true;
	}
    
    public void newCondition(){
        setTurn(1);
    }

    public CreateCondition getCondition(){
        return CreateCondition.THORN;
    }
}
