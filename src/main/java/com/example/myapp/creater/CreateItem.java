package com.example.myapp.creater;

import com.example.myapp.model.items.Item;
import com.example.myapp.model.items.とどめ玉;
import com.example.myapp.model.items.アロエ草;
import com.example.myapp.model.items.カウンター;
import com.example.myapp.model.items.ゴールドチャンス;
import com.example.myapp.model.items.タイムアッパー;
import com.example.myapp.model.items.ダメージ共有;
import com.example.myapp.model.items.ダメージ草;
import com.example.myapp.model.items.ダークマター;
import com.example.myapp.model.items.トゲトゲゾーン;
import com.example.myapp.model.items.バトルゴング;
import com.example.myapp.model.items.バリア;
import com.example.myapp.model.items.バーサーク;
import com.example.myapp.model.items.ブレイクモード;
import com.example.myapp.model.items.ブーストMAX;
import com.example.myapp.model.items.ブーストアップ;
import com.example.myapp.model.items.上級火の玉;
import com.example.myapp.model.items.伝説のハンマー;
import com.example.myapp.model.items.光の玉;
import com.example.myapp.model.items.命の薬;
import com.example.myapp.model.items.回復封じ;
import com.example.myapp.model.items.小さな勇気;
import com.example.myapp.model.items.強チャージ;
import com.example.myapp.model.items.挑発;
import com.example.myapp.model.items.気合いサブスク;
import com.example.myapp.model.items.気合ため;
import com.example.myapp.model.items.火の玉;
import com.example.myapp.model.items.真珠;
import com.example.myapp.model.items.研ぎ石;
import com.example.myapp.model.items.薬草;
import com.example.myapp.model.items.薬草詰め合わせ;
import com.example.myapp.model.items.超ダメージ草;
import com.example.myapp.model.items.防御強化;
import com.example.myapp.repository.book.ItemPage;
import com.example.myapp.repository.book.PictureBook;

public enum CreateItem {
	INSTANCE;

	private PictureBook pb;

	public void setPictureBook(PictureBook pb) {
		this.pb = pb;
	}

	public Item create(int id) {
		ItemPage ip = pb.getItemPages().get(id - 1);
		Item item;
		switch (id) {
		case 1:
			item = new 薬草();
			break;
		case 2:
			item = new 火の玉();
			break;
		case 3:
			item = new とどめ玉();
			break;
		case 4:
			item = new 強チャージ();
			break;
		case 5:
			item = new 防御強化();
			break;
		case 6:
			item = new ダメージ草();
			break;
		case 7:
			item = new バーサーク();
			break;
		case 8:
			item = new 挑発();
			break;
		case 9:
			item = new アロエ草();
			break;
		case 10:
			item = new 真珠();
			break;
		case 11:
			item = new 薬草詰め合わせ();
			break;
		case 12:
			item = new 上級火の玉();
			break;
		case 13:
			item = new 気合ため();
			break;
		case 14:
			item = new 研ぎ石();
			break;
		case 15:
			item = new ダメージ共有();
			break;
		case 16:
			item = new カウンター();
			break;
		case 17:
			item = new 回復封じ();
			break;
		case 18:
			item = new ブーストアップ();
			break;
		case 19:
			item = new ゴールドチャンス();
			break;
		case 20:
			item = new ダークマター();
			break;
		case 21:
			item = new 光の玉();
			break;
		case 22:
			item = new 超ダメージ草();
			break;
		case 23:
			item = new バトルゴング();
			break;
		case 24:
			item = new バリア();
			break;
		case 25:
			item = new タイムアッパー();
			break;
		case 26:
			item = new 気合いサブスク();
			break;
		case 27:
			item = new トゲトゲゾーン();
			break;
		case 28:
			item = new ブレイクモード();
			break;
		case 29:
			item = new 小さな勇気();
			break;
		case 30:
			item = new 命の薬();
			break;
		case 31:
			item = new ブーストMAX();
			break;
		case 32:
			item = new 伝説のハンマー();
			break;
		default:
			item = new 火の玉();
		}
		item.setStatus(ip.getId(), ip.getName(), ip.getPrice());
		return item;
	}
}