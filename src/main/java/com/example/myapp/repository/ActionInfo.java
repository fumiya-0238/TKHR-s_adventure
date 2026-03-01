package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;

public class ActionInfo {
	private Living attacker;
	private Living receiver;
	private ActionTypeEnum actionType;
	private double number;
	private boolean penetrate;
	private List<String> messages;

	public ActionInfo() {
		messages = new ArrayList<>();
	}

	public void setActionType(ActionTypeEnum actionType) {
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

	public void addMessages(String message, Living living) {
		if (living instanceof Player) {
			messages.add("<div class = \"logPlayer\">" + message + "</div>");
		} else {
			messages.add("<div class = \"logMonster\">" + message + "</div>");
		}
	}

	public Living getAttacker() {
		return attacker;
	}

	public Living getReceiver() {
		return receiver;
	}

	public ActionTypeEnum getActionType() {
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

	/*
	 * processFlag = true; }
	 */

	/*
	 * public boolean processFlagIs() { return processFlag; }
	 */
}