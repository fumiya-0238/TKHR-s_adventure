package tkhr.monsters;

public enum CreateMonster {
	INSTANCE;
public Monster create(int ID) {
	if(ID==1)
		return new スライム(ID);
	if(ID==2)
		return new 草太郎(ID);
	if(ID==3)
		return new クソウサギ(ID);
	if(ID==4)
		return new 生首培養(ID);
	if(ID==5)
		return new ネペント(ID);
	if(ID==6)
		return new とんびくん(ID);
	/*オクトスター
	イングラス
	バタフライヤー
	ポイズーノ
	パンドラシェル
	クソデスウサギ
	ツララハリネズミ
	キノコダンスワーム
	眼魔神
	ステゴロデビル
	エンシェントカニ
	ドッペルゲンガー
	ゴーレム
	ランタンオバケ
	アロエ太郎
	カブリッチャ
	ダークハンド
	クマカエル
	寿命かじり虫
	忍びトカゲ
	TNKR
	スライムジュエル
	つりザメ
	タッコウ
	ドクロオバケ
	パワーペン筋
	暴走培養
	ネペントフォール
	アサルトライノ
	コカトリス
	永遠の修道女
	土偶大魔神*/
	else
	return new スライム(1);
}
}
