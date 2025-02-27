package com.example.myapp.model.conditions;

public class ゴールドチャンス状態 extends Condition{
    public ゴールドチャンス状態() {
		name="ゴールドチャンス状態";
	}
    public void newCondition(){
        setTurn(1);
    }
    public CreateCondition getCondition(){
        return CreateCondition.GOLDCHANCE;
    }
}
