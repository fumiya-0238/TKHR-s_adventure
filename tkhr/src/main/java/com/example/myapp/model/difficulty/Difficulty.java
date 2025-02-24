package tkhr.difficulty;

import java.util.ArrayList;
import java.util.List;

import tkhr.items.Item;
import tkhr.items.とどめ玉;
import tkhr.items.アロエ草;
import tkhr.items.ゴールドチャレンジ;
import tkhr.items.タイムアッパー;
import tkhr.items.ダメージ草;
import tkhr.items.トゲトゲゾーン;
import tkhr.items.バリア;
import tkhr.items.バーサーク;
import tkhr.items.ブーストアップ;
import tkhr.items.メンヘラチェーン;
import tkhr.items.上級火の玉;
import tkhr.items.伝説のハンマー;
import tkhr.items.初級武器強化セット;
import tkhr.items.強チャージ;
import tkhr.items.気合ため;
import tkhr.items.気合サブスク;
import tkhr.items.火の玉;
import tkhr.items.研ぎ石;
import tkhr.items.薬草;
import tkhr.items.薬草詰め合わせ;
import tkhr.items.防御強化;
import tkhr.monsters.CreateMonster;
import tkhr.monsters.Monster;
import tkhr.weapons.SIMPLE_IS_BEST;
import tkhr.weapons.VIPの剣;
import tkhr.weapons.Weapon;
import tkhr.weapons.エクスカリバー;
import tkhr.weapons.ジェネシスリンク;
import tkhr.weapons.タイタンブレード;
import tkhr.weapons.ダイヤモンドの槍;
import tkhr.weapons.チャージャーEX;
import tkhr.weapons.ディレイソード;
import tkhr.weapons.デザイアの剣;
import tkhr.weapons.デバッギン;
import tkhr.weapons.デュランダル;
import tkhr.weapons.ブースティア;
import tkhr.weapons.ライトブリンガー;
import tkhr.weapons.世界樹の剣;
import tkhr.weapons.入魂剣;
import tkhr.weapons.勇者の剣X;
import tkhr.weapons.天使の剣;
import tkhr.weapons.強強剣;
import tkhr.weapons.打製石器;
import tkhr.weapons.木の枝;
import tkhr.weapons.死神の鎌;
import tkhr.weapons.火炎の剣;
import tkhr.weapons.石の剣;
import tkhr.weapons.貫通剣;
import tkhr.weapons.鉄の剣;
import tkhr.weapons.鉄の槍;
import tkhr.weapons.骨の槍;

public enum Difficulty {
	BEGINNER {
		@Override
		public int getLevel() {
			return 1;
		}

		@Override
		public Monster getMonster(int floor) {
			if (floor == 1)
				return cm.create(1);
			if (floor == 2 || floor == 3)
				return cm.create(2);
			if (floor == 4 || floor == 5)
				return cm.create(3);
			if (floor == 6 || floor == 7)
				return cm.create(4);
			if (floor == 8 || floor == 9)
				return cm.create(5);
			return cm.create(6);
		}

		@Override
		public List<Weapon> getWeapons(int n) {
			List<Weapon> weapon = new ArrayList<Weapon>();
			weapon.add(new 木の枝());
			weapon.add(new 打製石器());
			weapon.add(new 石の剣());
			weapon.add(new 鉄の剣());
			weapon.add(new 骨の槍());
			weapon.add(new 鉄の槍());
			weapon.add(new 強強剣());
			weapon.add(new 入魂剣());
			return weapon;
		}

		@Override
		public List<Item> getItems(int n) {
			List<Item> item = new ArrayList<Item>();
			item.add(new 薬草());
			item.add(new 火の玉());
			item.add(new とどめ玉());
			item.add(new 強チャージ());
			item.add(new 防御強化());
			item.add(new ダメージ草());
			item.add(new バーサーク());
			return item;
		}
	},
	NOVICE {
		@Override
		public int getLevel() {
			return 5;
		}

		@Override
		public Monster getMonster(int floor) {
			if (floor == 1 || floor == 2)
				return cm.create(7);
			if (floor == 3 || floor == 9 || floor == 18)
				return cm.create(8);
			if (floor == 4 || floor == 5)
				return cm.create(9);
			if (floor == 6)
				return cm.create(10);
			if (floor == 7 || floor == 17)
				return cm.create(11);
			if (floor == 8)
				return cm.create(12);
			if (floor == 10 || floor == 13)
				return cm.create(13);
			if (floor == 11)
				return cm.create(14);
			if (floor == 12)
				return cm.create(15);
			if (floor == 14)
				return cm.create(16);
			if (floor == 15)
				return cm.create(17);
			if (floor == 16)
				return cm.create(18);
			if (floor == 19)
				return cm.create(19);
			return cm.create(20);
		}

		@Override
		public List<Weapon> getWeapons(int n) {
			List<Weapon> weapon = new ArrayList<Weapon>();
			weapon.addAll(BEGINNER.getWeapons(0));
			weapon.add(new 貫通剣());
			//weapon.add(new ぼろ双剣());
			weapon.add(new 天使の剣());
			weapon.add(new 火炎の剣());
			weapon.add(new ブースティア());
			weapon.add(new チャージャーEX());
			weapon.add(new VIPの剣());
			weapon.add(new ディレイソード());
			weapon.add(new ライトブリンガー());
			return weapon;
		}

		@Override
		public List<Item> getItems(int n) {
			List<Item> item = new ArrayList<Item>();
			item.add(new アロエ草());
			item.add(new 薬草詰め合わせ());
			item.add(new 上級火の玉());
			item.add(new 気合ため());
			item.add(new メンヘラチェーン());
			item.add(new 研ぎ石());
			item.add(new ブーストアップ());
			item.add(new ゴールドチャレンジ());
			return item;
		}
	},
	EXPERT {
		@Override
		public int getLevel() {
			return 10;
		}

		@Override
		public Monster getMonster(int floor) {
			if (1 <= floor && floor <= 16)
				return cm.create(floor + 20);
			if (floor == 17)
				return cm.create(28);
			if (18 <= floor && floor <= 19)
				return cm.create(floor + 19);
			return cm.create(39);
		}

		@Override
		public List<Weapon> getWeapons(int n) {
			List<Weapon> weapon = new ArrayList<Weapon>();
			weapon.addAll(NOVICE.getWeapons(0));
			weapon.add(new ダイヤモンドの槍());
			weapon.add(new タイタンブレード());
			weapon.add(new 世界樹の剣());
			weapon.add(new 死神の鎌());
			weapon.add(new デュランダル());
			weapon.add(new エクスカリバー());
			weapon.add(new SIMPLE_IS_BEST());
			weapon.add(new デザイアの剣());
			weapon.add(new 勇者の剣X());
			return weapon;
		}

		@Override
		public List<Item> getItems(int n) {
			List<Item> item = new ArrayList<Item>();
			item.add(new バリア());
			item.add(new タイムアッパー());
			item.add(new 気合サブスク());
			item.add(new トゲトゲゾーン());
			item.add(new 初級武器強化セット());
			item.add(new 伝説のハンマー());
			return item;
		}
	},
	DEBUG {
		@Override
		public int getLevel() {
			return 1;
		}

		@Override
		public Monster getMonster(int floor) {
			// TODO 自動生成されたメソッド・スタブ
			return cm.create(1);
		}

		@Override
		public List<Weapon> getWeapons(int n) {
			// TODO 自動生成されたメソッド・スタブ
			List<Weapon> weapon = new ArrayList<Weapon>();
			weapon.addAll(EXPERT.getWeapons(0));
			weapon.add(new ジェネシスリンク());
			weapon.add(new デバッギン());
			return weapon;
		}

		@Override
		public List<Item> getItems(int n) {
			// TODO 自動生成されたメソッド・スタブ
			List<Item> item = new ArrayList<Item>();
			item.add(new 薬草());
			item.add(new 火の玉());
			item.add(new とどめ玉());
			item.add(new 強チャージ());
			item.add(new 防御強化());
			item.add(new ダメージ草());
			item.add(new バーサーク());
			return item;
		}
	};
	CreateMonster cm;
	private Difficulty() {
		cm = CreateMonster.INSTANCE;
	}
	public abstract int getLevel();

	public abstract Monster getMonster(int floor);

	public abstract List<Weapon> getWeapons(int n);

	public abstract List<Item> getItems(int n);
}
