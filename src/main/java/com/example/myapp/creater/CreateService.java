package com.example.myapp.creater;

import com.example.myapp.model.services.HP10回復;
import com.example.myapp.model.services.HP30回復;
import com.example.myapp.model.services.HP全回復;
import com.example.myapp.model.services.ServiceItem;
import com.example.myapp.model.services.アイテム欄5;
import com.example.myapp.model.services.ブースト増加量4UP;
import com.example.myapp.model.services.初級武器攻撃力8UP;
import com.example.myapp.model.services.強攻撃回数1UP;
import com.example.myapp.model.services.攻撃力1UP;
import com.example.myapp.model.services.最大HP5UP;
import com.example.myapp.model.services.根性状態になる;
import com.example.myapp.model.services.武器融合;
import com.example.myapp.repository.book.PictureBook;
import com.example.myapp.repository.book.ServicePage;

public enum CreateService {
	INSTANCE;

	private PictureBook pb;

	public void setPictureBook(PictureBook pb) {
		this.pb = pb;
	}

	public ServiceItem create(int id) {
		ServiceItem service;
		ServicePage sp = pb.getServicePages().get(id - 1);
		switch (id) {
		case 1:
			service = new HP10回復();
			break;
		case 2:
			service = new HP30回復();
			break;
		case 3:
			service = new HP全回復();
			break;
		case 4:
			service = new 最大HP5UP();
			break;
		case 5:
			service = new 攻撃力1UP();
			break;
		case 6:
			service = new 強攻撃回数1UP();
			break;
		case 7:
			service = new ブースト増加量4UP();
			break;
		case 8:
			service = new 根性状態になる();
			break;
		case 9:
			service = new アイテム欄5();
			break;
		case 10:
			service = new 武器融合();
			break;
		case 11:
			service = new 初級武器攻撃力8UP();
			break;
		default:
			service = new HP10回復();
		}
		service.setStatus(sp.getId(), sp.getName(), sp.getPrice());
		return service;
	}
}
