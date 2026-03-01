package com.example.myapp.creater;

import com.example.myapp.model.dungeons.D00デバッグ;
import com.example.myapp.model.dungeons.D01初級;
import com.example.myapp.model.dungeons.D05インフレ王国;
import com.example.myapp.model.dungeons.D06便利な時代;
import com.example.myapp.model.dungeons.D07ツイン通り;
import com.example.myapp.model.dungeons.D08気まぐれショップ;
import com.example.myapp.model.dungeons.D09謎の大魔宮;
import com.example.myapp.model.dungeons.Dungeon;

public enum CreateDungeon {
	INSTANCE;

	public Dungeon create(int id) {
		Dungeon dungeon;
		switch (id) {
		case 1:
		case 2:
		case 3:
		case 4:
			dungeon = new D01初級();
			break;
		case 5:
			dungeon = new D05インフレ王国();
			break;
		case 6:
			dungeon = new D06便利な時代();
			break;
		case 7:
			dungeon = new D07ツイン通り();
			break;
		case 8:
			dungeon = new D08気まぐれショップ();
			break;
		case 9:
			dungeon = new D09謎の大魔宮();
			break;
		default:
			dungeon = new D00デバッグ();
		}
		return dungeon;
	}
}