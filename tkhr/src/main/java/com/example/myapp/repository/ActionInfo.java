package com.example.myapp.repository;

public class ActionInfo {
    private int damage;
    private int action;
    private boolean penetrate;

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setPenetrate(boolean penetrate) {
        this.penetrate = penetrate;
    }

    public int getDamage() {
        return damage;
    }

    public int getAction() {
        return action;
    }

    public boolean getPenetrate() {
        return penetrate;
    }
}
