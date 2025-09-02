package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;

public class ActionInfo {
	private Living living;
    private boolean attackIs;//0 1攻撃 2回復
    private int damage;
    private boolean penetrate;
    private List<String> messages;
    public ActionInfo() {
    	messages = new ArrayList<>();
    }
    public void setAttackIs(boolean attackIs) {
        this.attackIs = attackIs;
    }
    
    public void setLiving(Living living) {
        this.living = living;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPenetrate(boolean penetrate) {
        this.penetrate = penetrate;
    }
    
    public void addMessages(String message){
		messages.add(message);
    }
    
    public Living getLiving() {
        return living;
    }
    
    public boolean getAttackIs() {
        return attackIs;
    }

    public int getDamage() {
        return damage;
    }

    public boolean getPenetrate() {
        return penetrate;
    }
    
    public List<String> getMessages(){
    	return messages;
    }
}