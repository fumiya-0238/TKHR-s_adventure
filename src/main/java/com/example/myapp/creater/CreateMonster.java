package com.example.myapp.creater;

import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.monsters.M28TNKR;
import com.example.myapp.model.monsters.M06とんびくん;
import com.example.myapp.model.monsters.M36アサルトライノ;
import com.example.myapp.model.monsters.M22アロエ太郎;
import com.example.myapp.model.monsters.M08イングラス;
import com.example.myapp.model.monsters.M18エンシェントカニ;
import com.example.myapp.model.monsters.M07オクトスター;
import com.example.myapp.model.monsters.M23カプリッチャ;
import com.example.myapp.model.monsters.M03クソウサギ;
import com.example.myapp.model.monsters.M13クソデスウサギ;
import com.example.myapp.model.monsters.M25バトルフェアリー;
import com.example.myapp.model.monsters.M37コカトリス;
import com.example.myapp.model.monsters.M20ゴーレム;
import com.example.myapp.model.monsters.M01スライム;
import com.example.myapp.model.monsters.M29スライムジュエル;
import com.example.myapp.model.monsters.M31タッコウ;
import com.example.myapp.model.monsters.M15ダンスワーム;
import com.example.myapp.model.monsters.M24ダークハンド;
import com.example.myapp.model.monsters.M14ツララハリネズミ;
import com.example.myapp.model.monsters.M30ツリザメ;
import com.example.myapp.model.monsters.M17デスマッチデビル;
import com.example.myapp.model.monsters.M32ドクロマシン;
import com.example.myapp.model.monsters.M19ドッペルゲンガー;
import com.example.myapp.model.monsters.M05ネペント;
import com.example.myapp.model.monsters.M09バタフライヤー;
import com.example.myapp.model.monsters.M12パンドラシェル;
import com.example.myapp.model.monsters.M10ポイズーノ;
import com.example.myapp.model.monsters.M33マッスルペンギン;
import com.example.myapp.model.monsters.M21ランタンオバケ;
import com.example.myapp.model.monsters.M38ヴァンパイアシスター;
import com.example.myapp.model.monsters.M11体育の妖精;
import com.example.myapp.model.monsters.M39土偶大魔神;
import com.example.myapp.model.monsters.M26寿命かじり虫;
import com.example.myapp.model.monsters.M27忍びトカゲ;
import com.example.myapp.model.monsters.M34暴走培養;
import com.example.myapp.model.monsters.M04生首培養;
import com.example.myapp.model.monsters.M16眼魔神;
import com.example.myapp.model.monsters.M35紅葉ネペント;
import com.example.myapp.model.monsters.M02草太郎;
import com.example.myapp.repository.book.MonsterPage;

public enum CreateMonster {
	INSTANCE;

	private List<MonsterPage> mps;

	public void setPictureBook(List<MonsterPage> mps) {
		this.mps = mps;
	}

	public Monster create(int id,int lv, int number) {
		Monster monster;
		switch (id) {
		case 1:
			monster = new M01スライム();
			break;
		case 2:
			monster = new M02草太郎();
			break;
		case 3:
			monster = new M03クソウサギ();
			break;
		case 4:
			monster = new M04生首培養();
			break;
		case 5:
			monster = new M05ネペント();
			break;
		case 6:
			monster = new M06とんびくん();
			break;
		case 7:
			monster = new M07オクトスター();
			break;
		case 8:
			monster = new M08イングラス();
			break;
		case 9:
			monster = new M09バタフライヤー();
			break;
		case 10:
			monster = new M10ポイズーノ();
			break;
		case 11:
			monster = new M11体育の妖精();
			break;
		case 12:
			monster = new M12パンドラシェル();
			break;
		case 13:
			monster = new M13クソデスウサギ();
			break;
		case 14:
			monster = new M14ツララハリネズミ();
			break;
		case 15:
			monster = new M15ダンスワーム();
			break;
		case 16:
			monster = new M16眼魔神();
			break;
		case 17:
			monster = new M17デスマッチデビル();
			break;
		case 18:
			monster = new M18エンシェントカニ();
			break;
		case 19:
			monster = new M19ドッペルゲンガー();
			break;
		case 20:
			monster = new M20ゴーレム();
			break;
		case 21:
			monster = new M21ランタンオバケ();
			break;
		case 22:
			monster = new M22アロエ太郎();
			break;
		case 23:
			monster = new M23カプリッチャ();
			break;
		case 24:
			monster = new M24ダークハンド();
			break;
		case 25:
			monster = new M25バトルフェアリー();
			break;
		case 26:
			monster = new M26寿命かじり虫();
			break;
		case 27:
			monster = new M27忍びトカゲ();
			break;
		case 28:
			monster = new M28TNKR();
			break;
		case 29:
			monster = new M29スライムジュエル();
			break;
		case 30:
			monster = new M30ツリザメ();
			break;
		case 31:
			monster = new M31タッコウ();
			break;
		case 32:
			monster = new M32ドクロマシン();
			break;
		case 33:
			monster = new M33マッスルペンギン();
			break;
		case 34:
			monster = new M34暴走培養();
			break;
		case 35:
			monster = new M35紅葉ネペント();
			break;
		case 36:
			monster = new M36アサルトライノ();
			break;
		case 37:
			monster = new M37コカトリス();
			break;
		case 38:
			monster = new M38ヴァンパイアシスター();
			break;
		case 39:
			monster = new M39土偶大魔神();
			break;
		default:
			monster = new M01スライム();
		}
		monster.setLv(lv);
		monster.setNumber(number);
		monster.setId(mps.get(id - 1).getId());
		return monster;
	}

	public void setStatus(Monster monster) {
		MonsterPage mp = mps.get(monster.getId() - 1);
		monster.setStatus(mp.getName(), mp.getHp(), mp.getOverHp(), mp.getAttack(), mp.getExp(),
				mp.getGold(), mp.getTurn());
		monster.reset();
	}
}
