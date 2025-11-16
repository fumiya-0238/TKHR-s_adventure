package com.example.myapp.repository.debug;

public class DungeonMonsterData extends TKHRData {
	private int monsterId;
	private int lv;

	public DungeonMonsterData(int id, int monsterId, int lv) {
		super(id);
		this.monsterId = monsterId;
		this.lv = lv;
	}

	public int getMonsterId() {
		return monsterId;
	}
	
	public int getLV() {
		return lv;
	}
}
