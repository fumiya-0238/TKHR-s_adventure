package com.example.myapp.creater;

import java.util.List;

import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.conditions.infinity.いつでもショップ;
import com.example.myapp.model.conditions.infinity.ためる強化;
import com.example.myapp.model.conditions.infinity.アイテムヒール;
import com.example.myapp.model.conditions.infinity.アサルトブースター;
import com.example.myapp.model.conditions.infinity.アサルトペネトレイト;
import com.example.myapp.model.conditions.infinity.ダークアーマー;
import com.example.myapp.model.conditions.infinity.ツララボディ;
import com.example.myapp.model.conditions.infinity.ディレイモード;
import com.example.myapp.model.conditions.infinity.トゲトゲゾーン状態;
import com.example.myapp.model.conditions.infinity.ヒールチャージ;
import com.example.myapp.model.conditions.infinity.ブーストUP強化;
import com.example.myapp.model.conditions.infinity.ボーナスゲット;
import com.example.myapp.model.conditions.infinity.ボーナスバリア;
import com.example.myapp.model.conditions.infinity.ヴァンパイアの魂;
import com.example.myapp.model.conditions.infinity.中の人;
import com.example.myapp.model.conditions.infinity.偶数攻撃;
import com.example.myapp.model.conditions.infinity.先制攻撃;
import com.example.myapp.model.conditions.infinity.初級武器強化;
import com.example.myapp.model.conditions.infinity.古代の結界;
import com.example.myapp.model.conditions.infinity.回復の胞子;
import com.example.myapp.model.conditions.infinity.執念;
import com.example.myapp.model.conditions.infinity.天聖の加護;
import com.example.myapp.model.conditions.infinity.強い執念;
import com.example.myapp.model.conditions.infinity.強攻撃強化;
import com.example.myapp.model.conditions.infinity.強攻撃無効;
import com.example.myapp.model.conditions.infinity.手加減無効;
import com.example.myapp.model.conditions.infinity.擬態の術状態;
import com.example.myapp.model.conditions.infinity.暴走スイッチ;
import com.example.myapp.model.conditions.infinity.根性;
import com.example.myapp.model.conditions.infinity.毎ターンHP回復;
import com.example.myapp.model.conditions.infinity.毎ターンHP減少;
import com.example.myapp.model.conditions.infinity.気まぐれ;
import com.example.myapp.model.conditions.infinity.草こもり;
import com.example.myapp.model.conditions.oneturn.VIP客;
import com.example.myapp.model.conditions.oneturn.アタックモード;
import com.example.myapp.model.conditions.oneturn.アロエ栽培;
import com.example.myapp.model.conditions.oneturn.イグニッション;
import com.example.myapp.model.conditions.oneturn.カウンター状態;
import com.example.myapp.model.conditions.oneturn.コピーソード状態;
import com.example.myapp.model.conditions.oneturn.ゴールドチャンス状態;
import com.example.myapp.model.conditions.oneturn.スライム状態;
import com.example.myapp.model.conditions.oneturn.ダミー;
import com.example.myapp.model.conditions.oneturn.ダメージ共有状態;
import com.example.myapp.model.conditions.oneturn.ネペントの消化液;
import com.example.myapp.model.conditions.oneturn.バリア状態;
import com.example.myapp.model.conditions.oneturn.バーサーク状態;
import com.example.myapp.model.conditions.oneturn.ボーナスターン保持;
import com.example.myapp.model.conditions.oneturn.ランタンフレイム;
import com.example.myapp.model.conditions.oneturn.回復封じ状態;
import com.example.myapp.model.conditions.oneturn.天聖の休息;
import com.example.myapp.model.conditions.oneturn.小さな勇気状態;
import com.example.myapp.model.conditions.oneturn.挑発状態;
import com.example.myapp.model.conditions.oneturn.攻撃力2倍;
import com.example.myapp.model.conditions.oneturn.死神の呪い;
import com.example.myapp.model.conditions.oneturn.毒状態;
import com.example.myapp.model.conditions.oneturn.気合ビート;
import com.example.myapp.model.conditions.oneturn.眼チャージ状態;
import com.example.myapp.model.conditions.oneturn.被ダメージ2倍;
import com.example.myapp.model.conditions.oneturn.貫通;
import com.example.myapp.model.conditions.oneturn.防御強化状態;
import com.example.myapp.model.conditions.oneturn.防御状態;
import com.example.myapp.repository.book.ConditionPage;

public enum CreateCondition {
	INSTANCE;

	private List<ConditionPage> cps;

	public void setPictureBook(List<ConditionPage> cps) {
		this.cps = cps;
	}

	public Condition create(ConditionEnum num) {
		int id = num.getId();
		ConditionPage cp = cps.get(id - 1);
		Condition condition;
		switch (id) {
		case 1:
			condition = new 防御状態();
			break;
		case 2:
			condition = new 防御強化状態();
			break;
		case 3:
			condition = new 挑発状態();
			break;
		case 4:
			condition = new バーサーク状態();
			break;
		case 5:
			condition = new 貫通();
			break;
		case 6:
			condition = new ボーナスゲット();
			break;
		case 7:
			condition = new ダメージ共有状態();
			break;
		case 8:
			condition = new カウンター状態();
			break;
		case 9:
			condition = new ゴールドチャンス状態();
			break;
		case 10:
			condition = new VIP客();
			break;
		case 11:
			condition = new バリア状態();
			break;
		case 12:
			condition = new トゲトゲゾーン状態();
			break;
		case 13:
			condition = new 小さな勇気状態();
			break;
		case 14:
			condition = new 初級武器強化();
			break;
		case 15:
			condition = new 執念();
			break;
		case 16:
			condition = new 暴走スイッチ();
			break;
		case 17:
			condition = new 手加減無効();
			break;
		case 18:
			condition = new 強攻撃無効();
			break;
		case 19:
			condition = new アロエ栽培();
			break;
		case 20:
			condition = new 偶数攻撃();
			break;
		case 21:
			condition = new 毒状態();
			break;
		case 22:
			condition = new 強い執念();
			break;
		case 23:
			condition = new 眼チャージ状態();
			break;
		case 24:
			condition = new 回復の胞子();
			break;
		case 25:
			condition = new ランタンフレイム();
			break;
		case 26:
			condition = new 気まぐれ();
			break;
		case 27:
			condition = new ダークアーマー();
			break;
		case 28:
			condition = new 擬態の術状態();
			break;
		case 29:
			condition = new スライム状態();
			break;
		case 30:
			condition = new ネペントの消化液();
			break;
		case 31:
			condition = new アサルトブースター();
			break;
		case 32:
			condition = new アサルトペネトレイト();
			break;
		case 33:
			condition = new ヴァンパイアの魂();
			break;
		case 34:
			condition = new 古代の結界();
			break;
		case 35:
			condition = new アタックモード();
			break;
		case 36:
			condition = new 毎ターンHP回復();
			break;
		case 37:
			condition = new 毎ターンHP減少();
			break;
		case 38:
			condition = new ディレイモード();
			break;
		case 39:
			condition = new 死神の呪い();
			break;
		case 40:
			condition = new 根性();
			break;
		case 41:
			condition = new ツララボディ();
			break;
		case 42:
			condition = new 気合ビート();
			break;
		case 43:
			condition = new ためる強化();
			break;
		case 44:
			condition = new 強攻撃強化();
			break;
		case 45:
			condition = new 先制攻撃();
			break;
		case 47:
			condition = new 回復封じ状態();
			break;
		case 48:
			condition = new ボーナスターン保持();
			break;
		case 49:
			condition = new ブーストUP強化();
			break;
		case 50:
			condition = new 攻撃力2倍();
			break;
		case 51:
			condition = new ヒールチャージ();
			break;
		case 52:
			condition = new アイテムヒール();
			break;
		case 53:
			condition = new 草こもり();
			break;
		case 54:
			condition = new いつでもショップ();
			break;
		case 55:
			condition = new ボーナスバリア();
			break;
		case 56:
			condition = new 中の人();
			break;
		case 58:
			condition = new イグニッション();
			break;
		case 59:
			condition = new 天聖の加護();
			break;
		case 60:
			condition = new 天聖の休息();
			break;
		case 61:
			condition = new コピーソード状態();
			break;
		case 62:
			condition = new 被ダメージ2倍();
			break;		
		default:
			condition = new ダミー();
		}
		condition.setStatus(cp.getId(), cp.getName(), cp.isDuplication());
		return condition;
	}

	public Condition create(ConditionEnum num, int[] status) {
		Condition condition = create(num);
		condition.setLevel(status);
		return condition;
	}
}
