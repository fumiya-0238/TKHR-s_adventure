package com.example.myapp.creater;

import com.example.myapp.model.actions.Action;
import com.example.myapp.model.actions.ついばむ;
import com.example.myapp.model.actions.アイテムキャッチ;
import com.example.myapp.model.actions.アロエで回復;
import com.example.myapp.model.actions.クリスタルレーザー;
import com.example.myapp.model.actions.コカトリスの災い;
import com.example.myapp.model.actions.スライムの光;
import com.example.myapp.model.actions.ダークしばき;
import com.example.myapp.model.actions.ダークマタースラッシュ;
import com.example.myapp.model.actions.デスマッチ;
import com.example.myapp.model.actions.ブラッディストーム;
import com.example.myapp.model.actions.ブレイクパンチ;
import com.example.myapp.model.actions.マシンリペア;
import com.example.myapp.model.actions.マタードレイン;
import com.example.myapp.model.actions.一緒にダンス;
import com.example.myapp.model.actions.吸収攻撃;
import com.example.myapp.model.actions.寿命かじり;
import com.example.myapp.model.actions.強攻撃;
import com.example.myapp.model.actions.強貫通攻撃;
import com.example.myapp.model.actions.手加減攻撃;
import com.example.myapp.model.actions.擬態の術;
import com.example.myapp.model.actions.攻撃;
import com.example.myapp.model.actions.攻撃失敗;
import com.example.myapp.model.actions.暴走レーザー;
import com.example.myapp.model.actions.毒攻撃;
import com.example.myapp.model.actions.消化液;
import com.example.myapp.model.actions.火を灯す;
import com.example.myapp.model.actions.火炎地獄;
import com.example.myapp.model.actions.真珠飛ばし;
import com.example.myapp.model.actions.眼チャージ;
import com.example.myapp.model.actions.自爆;
import com.example.myapp.model.actions.草で回復;
import com.example.myapp.model.actions.貫通攻撃;
import com.example.myapp.model.actions.超コカトリスの災い;
import com.example.myapp.model.actions.防御;
import com.example.myapp.repository.book.ActionPage;
import com.example.myapp.repository.book.PictureBook;

public enum CreateAction {
	INSTANCE;

	private PictureBook pb;

	public void setPictureBook(PictureBook pb) {
		this.pb = pb;
	}

	public Action create(int id) {
		Action action;
		ActionPage ap = pb.getActionPages().get(id - 1);
		switch (id) {
		case 1:
			action = new 攻撃();
			break;
		case 2:
			action = new 強攻撃();
			break;
		case 3:
			action = new 貫通攻撃();
			break;
		case 4:
			action = new 強貫通攻撃();
			break;
		case 5:
			action = new 吸収攻撃();
			break;
		case 7:
			action = new 毒攻撃();
			break;
		case 8:
			action = new 手加減攻撃();
			break;
		case 9:
			action = new 防御();
			break;
		case 10:
			action = new 草で回復();
			break;
		case 11:
			action = new ついばむ();
			break;
		case 12:
			action = new 真珠飛ばし();
			break;
		case 13:
			action = new 一緒にダンス();
			break;	
		case 14:
			action = new 眼チャージ();
			break;
		case 15:
			action = new デスマッチ();
			break;
		case 16:
			action = new ダークマタースラッシュ();
			break;
		case 17:
			action = new マタードレイン();
			break;	
		case 18:
			action = new ブレイクパンチ();
			break;
		case 19:
			action = new クリスタルレーザー();
			break;
		case 20:
			action = new 火を灯す();
			break;
		case 21:
			action = new 火炎地獄();
			break;
		case 22:
			action = new アロエで回復();
			break;
		case 23:
			action = new ダークしばき();
			break;
		case 24:
			action = new 寿命かじり();
			break;
		case 25:
			action = new 擬態の術();
			break;
		case 26:
			action = new スライムの光();
			break;
		case 27:
			action = new アイテムキャッチ();
			break;
		case 28:
			action = new 自爆();
			break;
		case 29:
			action = new マシンリペア();
			break;
		case 30:
			action = new 暴走レーザー();
			break;
		case 31:
			action = new 消化液();
			break;
		case 32:
			action = new コカトリスの災い();
			break;
		case 33:
			action = new 超コカトリスの災い();
			break;
		case 34:
			action = new ブラッディストーム();
			break;
		case 35:
			action = new 攻撃失敗();
			break;
		default:
			action = new 攻撃();
		}
		action.setStatus(ap.getName(), ap.isAttackIs());
		return action;
	}
	public Action create(int id,int[] status) {
		Action action = create(id); 
		action.setStatus(status);
		return action;
	}
	
}