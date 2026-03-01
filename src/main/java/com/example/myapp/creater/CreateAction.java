package com.example.myapp.creater;

import java.util.List;

import com.example.myapp.model.actions.Action;
import com.example.myapp.model.actions.A11ついばむ;
import com.example.myapp.model.actions.A27アイテムキャッチ;
import com.example.myapp.model.actions.A22アロエで回復;
import com.example.myapp.model.actions.A19クリスタルレーザー;
import com.example.myapp.model.actions.A32コカトリスの災い;
import com.example.myapp.model.actions.A26スライムの光;
import com.example.myapp.model.actions.A23ダークしばき;
import com.example.myapp.model.actions.A16ダークマタースラッシュ;
import com.example.myapp.model.actions.A15デスマッチ;
import com.example.myapp.model.actions.A34ブラッディストーム;
import com.example.myapp.model.actions.A18ブレイクパンチ;
import com.example.myapp.model.actions.A29マシンリペア;
import com.example.myapp.model.actions.A17マタードレイン;
import com.example.myapp.model.actions.A13一緒にダンス;
import com.example.myapp.model.actions.A05吸収攻撃;
import com.example.myapp.model.actions.A24寿命かじり;
import com.example.myapp.model.actions.A02強攻撃;
import com.example.myapp.model.actions.A04強貫通攻撃;
import com.example.myapp.model.actions.A08手加減攻撃;
import com.example.myapp.model.actions.A39捨て身攻撃;
import com.example.myapp.model.actions.A25擬態の術;
import com.example.myapp.model.actions.A01攻撃;
import com.example.myapp.model.actions.A35攻撃失敗;
import com.example.myapp.model.actions.A30暴走レーザー;
import com.example.myapp.model.actions.A07毒攻撃;
import com.example.myapp.model.actions.A31消化液;
import com.example.myapp.model.actions.A20火を灯す;
import com.example.myapp.model.actions.A21火炎地獄;
import com.example.myapp.model.actions.A12真珠飛ばし;
import com.example.myapp.model.actions.A14眼チャージ;
import com.example.myapp.model.actions.A28自爆;
import com.example.myapp.model.actions.A10草で回復;
import com.example.myapp.model.actions.A03貫通攻撃;
import com.example.myapp.model.actions.A33超コカトリスの災い;
import com.example.myapp.model.actions.A38追加攻撃;
import com.example.myapp.model.actions.A09防御;
import com.example.myapp.repository.book.ActionPage;

public enum CreateAction {
	INSTANCE;

	private List<ActionPage> aps;

	public void setPictureBook(List<ActionPage> aps) {
		this.aps = aps;
	}

	public Action create(int id) {
		Action action;
		ActionPage ap = aps.get(id - 1);
		switch (id) {
		case 1:
			action = new A01攻撃();
			break;
		case 2:
			action = new A02強攻撃();
			break;
		case 3:
			action = new A03貫通攻撃();
			break;
		case 4:
			action = new A04強貫通攻撃();
			break;
		case 5:
			action = new A05吸収攻撃();
			break;
		case 7:
			action = new A07毒攻撃();
			break;
		case 8:
			action = new A08手加減攻撃();
			break;
		case 9:
			action = new A09防御();
			break;
		case 10:
			action = new A10草で回復();
			break;
		case 11:
			action = new A11ついばむ();
			break;
		case 12:
			action = new A12真珠飛ばし();
			break;
		case 13:
			action = new A13一緒にダンス();
			break;
		case 14:
			action = new A14眼チャージ();
			break;
		case 15:
			action = new A15デスマッチ();
			break;
		case 16:
			action = new A16ダークマタースラッシュ();
			break;
		case 17:
			action = new A17マタードレイン();
			break;
		case 18:
			action = new A18ブレイクパンチ();
			break;
		case 19:
			action = new A19クリスタルレーザー();
			break;
		case 20:
			action = new A20火を灯す();
			break;
		case 21:
			action = new A21火炎地獄();
			break;
		case 22:
			action = new A22アロエで回復();
			break;
		case 23:
			action = new A23ダークしばき();
			break;
		case 24:
			action = new A24寿命かじり();
			break;
		case 25:
			action = new A25擬態の術();
			break;
		case 26:
			action = new A26スライムの光();
			break;
		case 27:
			action = new A27アイテムキャッチ();
			break;
		case 28:
			action = new A28自爆();
			break;
		case 29:
			action = new A29マシンリペア();
			break;
		case 30:
			action = new A30暴走レーザー();
			break;
		case 31:
			action = new A31消化液();
			break;
		case 32:
			action = new A32コカトリスの災い();
			break;
		case 33:
			action = new A33超コカトリスの災い();
			break;
		case 34:
			action = new A34ブラッディストーム();
			break;
		case 35:
			action = new A35攻撃失敗();
			break;
		case 38:
			action = new A38追加攻撃();
			break;
		case 39:
			action = new A39捨て身攻撃();
			break;	
		default:
			action = new A38追加攻撃();
		}
		action.setStatus(ap.getName(), ap.isAttackIs());
		return action;
	}

	public Action create(int id, int[] status) {
		Action action = create(id);
		action.setStatus(status);
		return action;
	}

}