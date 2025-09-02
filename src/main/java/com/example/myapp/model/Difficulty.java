package com.example.myapp.model;

public enum Difficulty {
	BEGINNER("beginner", "初級", 1), NOVICE("novice", "中級", 5), EXPERT("expert", "上級", 10);
	
	private String sql;
	private String name;
	private int lv;

	private Difficulty(String sql, String name, int lv) {
		this.sql = sql;
		this.name = name;
		this.lv = lv;
	}
	
	public String getSql() {
		return sql;
	}
	
	public String getName() {
		return name;
	}

	public int getLv() {
		return lv;
	}
}
