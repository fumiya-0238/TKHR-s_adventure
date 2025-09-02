package com.example.myapp.creater;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.monsters.TNKR;
import com.example.myapp.model.monsters.とんびくん;
import com.example.myapp.model.monsters.アサルトライノ;
import com.example.myapp.model.monsters.アロエ太郎;
import com.example.myapp.model.monsters.イングラス;
import com.example.myapp.model.monsters.エンシェントカニ;
import com.example.myapp.model.monsters.オクトスター;
import com.example.myapp.model.monsters.カプリッチャ;
import com.example.myapp.model.monsters.クソウサギ;
import com.example.myapp.model.monsters.クソデスウサギ;
import com.example.myapp.model.monsters.クマガエル;
import com.example.myapp.model.monsters.コカトリス;
import com.example.myapp.model.monsters.ゴーレム;
import com.example.myapp.model.monsters.スライム;
import com.example.myapp.model.monsters.スライムジュエル;
import com.example.myapp.model.monsters.タッコウ;
import com.example.myapp.model.monsters.ダンスワーム;
import com.example.myapp.model.monsters.ダークハンド;
import com.example.myapp.model.monsters.ツララハリネズミ;
import com.example.myapp.model.monsters.ツリザメ;
import com.example.myapp.model.monsters.デスマッチデビル;
import com.example.myapp.model.monsters.ドクロマシン;
import com.example.myapp.model.monsters.ドッペルゲンガー;
import com.example.myapp.model.monsters.ネペント;
import com.example.myapp.model.monsters.バタフライヤー;
import com.example.myapp.model.monsters.パンドラシェル;
import com.example.myapp.model.monsters.ポイズーノ;
import com.example.myapp.model.monsters.マッスルペンギン;
import com.example.myapp.model.monsters.ランタンオバケ;
import com.example.myapp.model.monsters.体育の妖精;
import com.example.myapp.model.monsters.土偶大魔神;
import com.example.myapp.model.monsters.寿命かじり虫;
import com.example.myapp.model.monsters.忍びトカゲ;
import com.example.myapp.model.monsters.暴走培養;
import com.example.myapp.model.monsters.生首培養;
import com.example.myapp.model.monsters.眼魔神;
import com.example.myapp.model.monsters.紅葉ネペント;
import com.example.myapp.model.monsters.草太郎;
import com.example.myapp.model.monsters.血欲の修道女;
import com.example.myapp.repository.book.MonsterPage;
import com.example.myapp.repository.book.PictureBook;

public enum CreateMonster {
	INSTANCE;

	private PictureBook pb;

	public void setPictureBook(PictureBook pb) {
		this.pb = pb;
	}

	public Monster create(int id) {
		MonsterPage mp = pb.getMonsterPages().get(id - 1);
		Monster monster;
		switch (id) {
		case 1:
			monster = new スライム();
			break;
		case 2:
			monster = new 草太郎();
			break;
		case 3:
			monster = new クソウサギ();
			break;
		case 4:
			monster = new 生首培養();
			break;
		case 5:
			monster = new ネペント();
			break;
		case 6:
			monster = new とんびくん();
			break;
		case 7:
			monster = new オクトスター();
			break;
		case 8:
			monster = new イングラス();
			break;
		case 9:
			monster = new バタフライヤー();
			break;
		case 10:
			monster = new ポイズーノ();
			break;
		case 11:
			monster = new 体育の妖精();
			break;
		case 12:
			monster = new パンドラシェル();
			break;
		case 13:
			monster = new クソデスウサギ();
			break;
		case 14:
			monster = new ツララハリネズミ();
			break;
		case 15:
			monster = new ダンスワーム();
			break;
		case 16:
			monster = new 眼魔神();
			break;
		case 17:
			monster = new デスマッチデビル();
			break;
		case 18:
			monster = new エンシェントカニ();
			break;
		case 19:
			monster = new ドッペルゲンガー();
			break;
		case 20:
			monster = new ゴーレム();
			break;
		case 21:
			monster = new ランタンオバケ();
			break;
		case 22:
			monster = new アロエ太郎();
			break;
		case 23:
			monster = new カプリッチャ();
			break;
		case 24:
			monster = new ダークハンド();
			break;
		case 25:
			monster = new クマガエル();
			break;
		case 26:
			monster = new 寿命かじり虫();
			break;
		case 27:
			monster = new 忍びトカゲ();
			break;
		case 28:
			monster = new TNKR();
			break;
		case 29:
			monster = new スライムジュエル();
			break;
		case 30:
			monster = new ツリザメ();
			break;
		case 31:
			monster = new タッコウ();
			break;
		case 32:
			monster = new ドクロマシン();
			break;
		case 33:
			monster = new マッスルペンギン();
			break;
		case 34:
			monster = new 暴走培養();
			break;
		case 35:
			monster = new 紅葉ネペント();
			break;
		case 36:
			monster = new アサルトライノ();
			break;
		case 37:
			monster = new コカトリス();
			break;
		case 38:
			monster = new 血欲の修道女();
			break;
		case 39:
			monster = new 土偶大魔神();
			break;
		default:
			monster = new スライム();
		}
		monster.setStatus(mp.getId(), mp.getName(), mp.getHp(), mp.getOverHp(), mp.getAttack(), mp.getExp(),
				mp.getGold(), mp.getTurn());
		monster.reset();
		return monster;
	}
}
