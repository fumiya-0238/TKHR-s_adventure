package com.example.myapp.creater;

import com.example.myapp.model.weapons.VIPの剣;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.model.weapons.アイテムフォース;
import com.example.myapp.model.weapons.アクセラレイバー;
import com.example.myapp.model.weapons.エクスカリバー;
import com.example.myapp.model.weapons.エナジーブレイブ;
import com.example.myapp.model.weapons.キングスブレイド;
import com.example.myapp.model.weapons.タイタンブレード;
import com.example.myapp.model.weapons.ダイヤモンドの槍;
import com.example.myapp.model.weapons.チャージャーEX;
import com.example.myapp.model.weapons.ディレイソード;
import com.example.myapp.model.weapons.デザイアの剣;
import com.example.myapp.model.weapons.デバッギン;
import com.example.myapp.model.weapons.デュランダル;
import com.example.myapp.model.weapons.ブースティア;
import com.example.myapp.model.weapons.マスターシンプル;
import com.example.myapp.model.weapons.ライトブリンガー;
import com.example.myapp.model.weapons.世界樹の剣;
import com.example.myapp.model.weapons.入魂剣;
import com.example.myapp.model.weapons.勇者の剣X;
import com.example.myapp.model.weapons.天使の剣;
import com.example.myapp.model.weapons.強強剣;
import com.example.myapp.model.weapons.打製石器;
import com.example.myapp.model.weapons.木の枝;
import com.example.myapp.model.weapons.死神の鎌;
import com.example.myapp.model.weapons.火炎の剣;
import com.example.myapp.model.weapons.石の剣;
import com.example.myapp.model.weapons.素手;
import com.example.myapp.model.weapons.貫通剣;
import com.example.myapp.model.weapons.鉄の剣;
import com.example.myapp.model.weapons.鉄の槍;
import com.example.myapp.model.weapons.鋼の剣;
import com.example.myapp.model.weapons.骨の槍;
import com.example.myapp.repository.book.PictureBook;
import com.example.myapp.repository.book.WeaponPage;

public enum CreateWeapon {
	INSTANCE;

	private PictureBook pb;

	public void setPictureBook(PictureBook pb) {
		this.pb = pb;
	}

	public Weapon create(int id) {
		WeaponPage wp = pb.getWeaponPages().get(id - 1);
		Weapon weapon;
		switch (id) {
		case 1:
			weapon = new 素手();
			break;
		case 2:
			weapon = new 木の枝();
			break;
		case 3:
			weapon = new 打製石器();
			break;
		case 4:
			weapon = new 石の剣();
			break;
		case 5:
			weapon = new 鉄の剣();
			break;
		case 6:
			weapon = new 骨の槍();
			break;
		case 7:
			weapon = new 鉄の槍();
			break;
		case 8:
			weapon = new 入魂剣();
			break;
		case 9:
			weapon = new 強強剣();
			break;
		case 10:
			weapon = new 鋼の剣();
			break;
		case 11:
			weapon = new 貫通剣();
			break;
		case 12:
			weapon = new 火炎の剣();
			break;
		case 13:
			weapon = new 天使の剣();
			break;
		case 14:
			weapon = new ブースティア();
			break;
		case 15:
			weapon = new チャージャーEX();
			break;
		case 16:
			weapon = new VIPの剣();
			break;
		case 17:
			weapon = new エナジーブレイブ();
			break;
		case 18:
			weapon = new アクセラレイバー();
			break;
		case 19:
			weapon = new ディレイソード();
			break;
		case 20:
			weapon = new ライトブリンガー();
			break;
		case 21:
			weapon = new ダイヤモンドの槍();
			break;
		case 22:
			weapon = new タイタンブレード();
			break;
		case 23:
			weapon = new 世界樹の剣();
			break;
		case 24:
			weapon = new 死神の鎌();
			break;
		case 25:
			weapon = new アイテムフォース();
			break;
		case 26:
			weapon = new エクスカリバー();
			break;
		case 27:
			weapon = new デュランダル();
			break;
		case 28:
			weapon = new マスターシンプル();
			break;
		case 29:
			weapon = new デザイアの剣();
			break;
		case 30:
			weapon = new 勇者の剣X();
			break;
		case 31:
			weapon = new キングスブレイド();
			break;
		case 32:
			weapon = new デバッギン();
			break;
		default:
			weapon = new 素手();
		}
		weapon.setStatus(wp.getId(), wp.getName(), wp.getPrice(), wp.getAttack());
		return weapon;
	}
}
