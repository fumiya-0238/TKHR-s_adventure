package com.example.myapp.repository;

public class ActionInfo {
    private boolean attackIs;
    private int damage;
    private boolean penetrate;

    public ActionInfo(boolean attackIs, int damage, boolean penetrate) {
        this.attackIs = attackIs;
        this.damage = damage;
        this.penetrate = penetrate;
    }

    public ActionInfo() {
    }

    public void setAttackIsTrue() {
        attackIs = true;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPenetrate(boolean penetrate) {
        this.penetrate = penetrate;
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
}
