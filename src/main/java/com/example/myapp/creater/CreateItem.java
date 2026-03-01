package com.example.myapp.creater;

import java.util.List;

import com.example.myapp.model.items.I01薬草;
import com.example.myapp.model.items.I02火の玉;
import com.example.myapp.model.items.I03とどめ玉;
import com.example.myapp.model.items.I04強チャージ;
import com.example.myapp.model.items.I05防御強化;
import com.example.myapp.model.items.I06気合ため;
import com.example.myapp.model.items.I07ブーストアップ;
import com.example.myapp.model.items.I08回復封じ;
import com.example.myapp.model.items.I09ダメージ草;
import com.example.myapp.model.items.I10バーサーク;
import com.example.myapp.model.items.I11アロエ草;
import com.example.myapp.model.items.I12タイムアッパー;
import com.example.myapp.model.items.I13真珠;
import com.example.myapp.model.items.I14薬草詰め合わせ;
import com.example.myapp.model.items.I15上級火の玉;
import com.example.myapp.model.items.I16研ぎ石;
import com.example.myapp.model.items.I17ダメージ共有;
import com.example.myapp.model.items.I18カウンター;
import com.example.myapp.model.items.I19ゴールドチャンス;
import com.example.myapp.model.items.I20挑発;
import com.example.myapp.model.items.I21ダークマター;
import com.example.myapp.model.items.I22光の玉;
import com.example.myapp.model.items.I23超ダメージ草;
import com.example.myapp.model.items.I24バトルゴング;
import com.example.myapp.model.items.I25バリア;
import com.example.myapp.model.items.I26気合サブスク;
import com.example.myapp.model.items.I27トゲトゲゾーン;
import com.example.myapp.model.items.I28気合ビート;
import com.example.myapp.model.items.I29小さな勇気;
import com.example.myapp.model.items.I30体力育成指導書;
import com.example.myapp.model.items.I31ブーストMAX;
import com.example.myapp.model.items.I32伝説のハンマー;
import com.example.myapp.model.items.I33いつでもショップ;
import com.example.myapp.model.items.I34コピーソード;
import com.example.myapp.model.items.I35クレイジーカード;
import com.example.myapp.model.items.I36生命の風;
import com.example.myapp.model.items.Item;
import com.example.myapp.repository.book.ItemPage;
import com.example.myapp.repository.shop.ShopItem;

public enum CreateItem {
	INSTANCE;

	private List<ItemPage> ips;

	public void setPictureBook(List<ItemPage> ips) {
		this.ips = ips;
	}

	public Item create(int id) {
		ItemPage ip = ips.get(id - 1);
		Item item;
		switch (id) {
		case 1:
			item = new I01薬草();
			break;
		case 2:
			item = new I02火の玉();
			break;
		case 3:
			item = new I03とどめ玉();
			break;
		case 4:
			item = new I04強チャージ();
			break;
		case 5:
			item = new I05防御強化();
			break;
		case 6:
			item = new I06気合ため();
			break;
		case 7:
			item = new I07ブーストアップ();
			break;
		case 8:
			item = new I08回復封じ();
			break;
		case 9:
			item = new I09ダメージ草();
			break;
		case 10:
			item = new I10バーサーク();
			break;
		case 11:
			item = new I11アロエ草();
			break;
		case 12:
			item = new I12タイムアッパー();
			break;
		case 13:
			item = new I13真珠();
			break;
		case 14:
			item = new I14薬草詰め合わせ();
			break;
		case 15:
			item = new I15上級火の玉();
			break;
		case 16:
			item = new I16研ぎ石();
			break;
		case 17:
			item = new I17ダメージ共有();
			break;
		case 18:
			item = new I18カウンター();
			break;
		case 19:
			item = new I19ゴールドチャンス();
			break;
		case 20:
			item = new I20挑発();
			break;
		case 21:
			item = new I21ダークマター();
			break;
		case 22:
			item = new I22光の玉();
			break;
		case 23:
			item = new I23超ダメージ草();
			break;
		case 24:
			item = new I24バトルゴング();
			break;
		case 25:
			item = new I25バリア();
			break;
		case 26:
			item = new I26気合サブスク();
			break;
		case 27:
			item = new I27トゲトゲゾーン();
			break;
		case 28:
			item = new I28気合ビート();
			break;
		case 29:
			item = new I29小さな勇気();
			break;
		case 30:
			item = new I30体力育成指導書();
			break;
		case 31:
			item = new I31ブーストMAX();
			break;
		case 32:
			item = new I32伝説のハンマー();
			break;
		case 33:
			item = new I33いつでもショップ();
			break;
		case 34:
			item = new I34コピーソード();
			break;
		case 35:
			item = new I35クレイジーカード();
			break;
		case 36:
			item = new I36生命の風();
			break;
		default:
			item = new I02火の玉();
		}
		item.setStatus(ip.getId(), ip.getName(), ip.getPrice());
		return item;
	}

	public ShopItem createStatus(int id, int stock) {
		ItemPage ip = ips.get(id - 1);
		ShopItem item = new ShopItem(id, ip.getId(), ip.getName(), ip.getFurigana(), ip.getPrice(), stock);
		return item;
	}
}