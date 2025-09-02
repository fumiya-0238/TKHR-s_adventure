package com.example.myapp.repository;

public enum ScreenEnum {
	モンスター名("SetMonsterName"),
	モンスターHP("SetMonsterHP"),
	モンスターオーバーHP("SetMonsterOverHP"),
	モンスター攻撃力("SetMonsterAttack"),
	モンスター経験値("SetMonsterEXP"),
	モンスターお金("SetMonsterGold"),
	モンスターダメージ("SetMonsterDamage"),
	ボーナスターン("SetMonsterTurn"),
	プレイヤーLV("SetPlayerLV"),
	プレイヤーHP("SetPlayerHP"),
	プレイヤー最大HP("SetPlayerMaxHP"),
	プレイヤー攻撃力("SetPlayerAttack"),
	プレイヤー武器攻撃力("SetPlayerWeaponAttack"),
	プレイヤー強攻撃("SetPlayerCritical"),
	プレイヤーテンション("SetPlayerTension"),
	プレイヤーブースト("SetPlayerBoost"),
	プレイヤー武器("SetPlayerWeapon"),
	プレイヤーサブ武器("SetPlayerSubWeapon"),
	プレイヤー経験値("SetPlayerEXP"),
	プレイヤー合計経験値("SetPlayerSumEXP"),
	プレイヤーお金("SetPlayerGold"),
	アイテム追加("AddItem"),
	アイテム削除("RemoveItem"),
	アクティブアイテム("ActiveItem"),
	難易度("SetDifficulty"),
	階数("SetSumFloor"),
	合計ターン("SetSumTurn"),
	次の階("NextFloor"),
	店("Shop"),
	ゲームオーバー("GameOver"),
	クリア("GameClear"),
	ストップ("Stop");
	private String logName;
	
	ScreenEnum(String logName){
		this.logName = logName;
	}
	
	public String getLogName() {
		return logName;
	}
}
