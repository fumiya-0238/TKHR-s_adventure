package com.example.myapp.creater;

import java.util.List;

import com.example.myapp.model.services.S01HP10回復;
import com.example.myapp.model.services.S02HP30回復;
import com.example.myapp.model.services.HP全回復;
import com.example.myapp.model.services.ServiceItem;
import com.example.myapp.model.services.S15アイテムをリセット;
import com.example.myapp.model.services.S09アイテム欄5;
import com.example.myapp.model.services.S16ステータスをリセット;
import com.example.myapp.model.services.S14ブースト10アップ;
import com.example.myapp.model.services.S07ブースト増加量2UP;
import com.example.myapp.model.services.S12レベル1アップ;
import com.example.myapp.model.services.S13レベル1ダウン;
import com.example.myapp.model.services.S04初級武器攻撃力8UP;
import com.example.myapp.model.services.S06強攻撃回数1UP;
import com.example.myapp.model.services.S05攻撃力1UP;
import com.example.myapp.model.services.S04最大HP5UP;
import com.example.myapp.model.services.S08根性状態になる;
import com.example.myapp.model.services.S10武器融合;
import com.example.myapp.repository.book.ServicePage;

public enum CreateService {
	INSTANCE;

	private List<ServicePage> sps;

	public void setPictureBook(List<ServicePage> sps) {
		this.sps = sps;
	}

	public ServiceItem create(int id) {
		ServiceItem service;
		ServicePage sp = sps.get(id - 1);
		switch (id) {
		case 1:
			service = new S01HP10回復();
			break;
		case 2:
			service = new S02HP30回復();
			break;
		case 3:
			service = new HP全回復();
			break;
		case 4:
			service = new S04最大HP5UP();
			break;
		case 5:
			service = new S05攻撃力1UP();
			break;
		case 6:
			service = new S06強攻撃回数1UP();
			break;
		case 7:
			service = new S07ブースト増加量2UP();
			break;
		case 8:
			service = new S08根性状態になる();
			break;
		case 9:
			service = new S09アイテム欄5();
			break;
		case 10:
			service = new S10武器融合();
			break;
		case 11:
			service = new S04初級武器攻撃力8UP();
			break;
		case 12:
			service = new S12レベル1アップ();
			break;
		case 13:
			service = new S13レベル1ダウン();
			break;
		case 14:
			service = new S14ブースト10アップ();
			break;
		case 15:
			service = new S15アイテムをリセット();
			break;
		case 16:
			service = new S16ステータスをリセット();
			break;	
		default:
			service = new S01HP10回復();
		}
		service.setStatus(sp.getId(), sp.getName(), sp.getPrice());
		return service;
	}
}
