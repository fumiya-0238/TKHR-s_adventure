package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;

public class ActionInfo {
	private Living attacker;
	private Living receiver;
	private int actionType;//0 1ダメージ 2回復 3テンション 4プレイヤーの攻撃、5モンスターの攻撃
	private double number;
	private boolean penetrate;
	private List<String> messages;
	private boolean processFlag;

	public ActionInfo() {
		messages = new ArrayList<>();
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public void setAttacker(Living attacker) {
		this.attacker = attacker;
	}

	public void setReceiver(Living receiver) {
		this.receiver = receiver;
	}

	public void setNumber(double number) {
		this.number = number;
		if (number < 0) {
			this.number = 0;
		}
	}

	public void setPenetrate(boolean penetrate) {
		this.penetrate = penetrate;
	}

	public void addMessages(String message) {
		messages.add(message);
	}

	public Living getAttacker() {
		return attacker;
	}

	public Living getReceiver() {
		return receiver;
	}

	public int getActionType() {
		return actionType;
	}

	public double getNumber() {
		return number;
	}

	public int getFinalNumber() {
		return (int) number;
	}

	public boolean getPenetrate() {
		return penetrate;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void processFlagIsTrue() {
		processFlag = true;
	}
	
	public boolean processFlagIs() {
		return processFlag;
	}
}