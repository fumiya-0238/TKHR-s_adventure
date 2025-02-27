package com.example.myapp.model.conditions;


public abstract class Condition {
    private int turn;
    private int amount;
	protected String name;
	protected boolean duplication;
	protected boolean perpetuity;
	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount=amount;
	}
	public void plusAmount() {
        if(duplication)
		amount++;
	}

    public void setTurn(int turn) {
		if(duplication){
		this.turn = turn;
		}
	}

	public void turnEnd() {
        if(turn>0&&!perpetuity){
		turn--;
        if(turn==0){
            amount=0;
        }
        }
	}

	public void remove(){
		amount=0;
		turn=0;
	}

    public abstract void newCondition();
	public abstract CreateCondition getCondition();
}
