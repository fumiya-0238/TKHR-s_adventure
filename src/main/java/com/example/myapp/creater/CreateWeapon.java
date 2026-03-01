package com.example.myapp.creater;

import java.util.List;

import com.example.myapp.model.weapons.W01効果無し武器;
import com.example.myapp.model.weapons.W05骨の槍;
import com.example.myapp.model.weapons.W06入魂剣;
import com.example.myapp.model.weapons.W07強強剣;
import com.example.myapp.model.weapons.W09鉄の槍;
import com.example.myapp.model.weapons.W11貫通剣;
import com.example.myapp.model.weapons.W12火炎の剣;
import com.example.myapp.model.weapons.W13天使の剣;
import com.example.myapp.model.weapons.W14ブースティア;
import com.example.myapp.model.weapons.W15チャージャーEX;
import com.example.myapp.model.weapons.W16VIPの剣;
import com.example.myapp.model.weapons.W17エナジーブレイブ;
import com.example.myapp.model.weapons.W18アクセラレイバー;
import com.example.myapp.model.weapons.W19会心アックス;
import com.example.myapp.model.weapons.W20ディレイソード;
import com.example.myapp.model.weapons.W21ライトブリンガー;
import com.example.myapp.model.weapons.W22ダイヤモンドの槍;
import com.example.myapp.model.weapons.W23タイタンブレード;
import com.example.myapp.model.weapons.W24世界樹の剣;
import com.example.myapp.model.weapons.W25死神の鎌;
import com.example.myapp.model.weapons.W26アイテムフォース;
import com.example.myapp.model.weapons.W27エクスカリバー;
import com.example.myapp.model.weapons.W28デュランダル;
import com.example.myapp.model.weapons.W30デザイアの剣;
import com.example.myapp.model.weapons.W31勇者の剣X;
import com.example.myapp.model.weapons.W32キングスブレイド;
import com.example.myapp.model.weapons.W33デバッギン;
import com.example.myapp.model.weapons.W34超炎の剣;
import com.example.myapp.model.weapons.W35天聖の剣;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.book.WeaponPage;
import com.example.myapp.repository.shop.ShopWeapon;

public enum CreateWeapon {
	INSTANCE;

	private List<WeaponPage> wps;

	public void setPictureBook(List<WeaponPage> wps) {
		this.wps = wps;
	}

	public Weapon create(int id) {
		WeaponPage wp = wps.get(id - 1);
		Weapon weapon;
		switch (id) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 8:
		case 10:
		case 29:
			weapon = new W01効果無し武器();
			break;
		case 5:
			weapon = new W05骨の槍();
			break;
		case 6:
			weapon = new W06入魂剣();
			break;
		case 7:
			weapon = new W07強強剣();
			break;
		case 9:
			weapon = new W09鉄の槍();
			break;
		case 11:
			weapon = new W11貫通剣();
			break;
		case 12:
			weapon = new W12火炎の剣();
			break;
		case 13:
			weapon = new W13天使の剣();
			break;
		case 14:
			weapon = new W14ブースティア();
			break;
		case 15:
			weapon = new W15チャージャーEX();
			break;
		case 16:
			weapon = new W16VIPの剣();
			break;
		case 17:
			weapon = new W17エナジーブレイブ();
			break;
		case 18:
			weapon = new W18アクセラレイバー();
			break;
		case 19:
			weapon = new W19会心アックス();
			break;
		case 20:
			weapon = new W20ディレイソード();
			break;
		case 21:
			weapon = new W21ライトブリンガー();
			break;
		case 22:
			weapon = new W22ダイヤモンドの槍();
			break;
		case 23:
			weapon = new W23タイタンブレード();
			break;
		case 24:
			weapon = new W24世界樹の剣();
			break;
		case 25:
			weapon = new W25死神の鎌();
			break;
		case 26:
			weapon = new W26アイテムフォース();
			break;
		case 27:
			weapon = new W27エクスカリバー();
			break;
		case 28:
			weapon = new W28デュランダル();
			break;
		case 30:
			weapon = new W30デザイアの剣();
			break;
		case 31:
			weapon = new W31勇者の剣X();
			break;
		case 32:
			weapon = new W32キングスブレイド();
			break;
		case 33:
			weapon = new W33デバッギン();
			break;
		case 34:
			weapon = new W34超炎の剣();
			break;
		case 35:
			weapon = new W35天聖の剣();
			break;
		default:
			weapon = new W01効果無し武器();
		}
		weapon.setStatus(wp.getId(), wp.getName(), wp.getPrice(), wp.getAttack());
		return weapon;
	}

	public ShopWeapon createStatus(int id, int stock) {
		WeaponPage wp = wps.get(id - 1);
		ShopWeapon weapon = new ShopWeapon(id, wp.getId(), wp.getName(), wp.getAttack(), wp.getFurigana(),
				wp.getPrice(), stock);
		return weapon;
	}
}
