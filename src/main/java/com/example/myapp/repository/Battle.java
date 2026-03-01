package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateItem;
import com.example.myapp.creater.CreateMonster;
import com.example.myapp.model.Difficulty;
import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.dungeons.Dungeon;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.book.BookPage;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Battle {
	private Player player;
	private List<Monster> monsters;
	private List<Monster> deathMonsters;
	private int target;
	private int monsterDamage;
	private List<String> messageBatchs;
	private List<String> messages;
	// private List<String> windowMessages;
	private int turn;
	private static List<ScreenChange> logs;
	private Difficulty difficulty;
	private int floor;
	private Dungeon dungeon;
	private CommonEffect commonEffect;
	private List<Item> removeItem;
	private int fusionPrice;
	private List<Boolean> commandClickFlags;
	private boolean itemClickFlag;

	private boolean fusionMode;
	private boolean betweenShop;
	private boolean goNextFloor;
	private boolean betweenTurn;
	private int sumTurn;
	private int finalExp;
	private int finalGold;
	private String monsterName;
	private boolean monsterLive;
	// private List<String> pageNames;
	private List<BookPage> pages;
	private final String requestId = UUID.randomUUID().toString();
	private final long createdAt = System.currentTimeMillis();

	public void init() {
		player = new Player();
		monsters = new ArrayList<>();
		deathMonsters = new ArrayList<>();
		messages = new ArrayList<>();
		messageBatchs = new ArrayList<>();
		commandClickFlags = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			commandClickFlags.add(true);
		}
		// dungeon = new Dungeon();
		logs = new ArrayList<>();
		commonEffect = new CommonEffect();
		removeItem = new ArrayList<>();
		pages = new ArrayList<>();
	}

	public void playerReset() {
		player.resetStatus();
	}

	public void goFirstFloor() {
		setSumTurn(1);
		setFloor(1);
		String attack = "攻撃力と同じダメージを与える";
		String weekAttack = "攻撃力-1のダメージを与える(気合の影響を受けない)";
		String criticalAttack = "攻撃力の1.5倍のダメージを与える";
		String defence = "このターン、受けるダメージを0にし、HPを20%回復する";
		String tameru = "気合が25%上昇する。攻撃のダメージが気合%分上昇する";
		String boost = "ブーストゲージが100%のとき、コマンドを切り替える";
		String commandText = attack + "#" + weekAttack + "#" + criticalAttack + "#" + defence + "#" + tameru + "#"
				+ boost;
		Battle.addLogs(new ScreenChange(ScreenEnum.コマンドテキスト, commandText));
		player.setLv(difficulty.getLv());
		playerReset();
		dungeon.goDungeon(player);
		;
		player.showItem();
		battleStart();
	}

	public void battleStart() {
		Iterator<Condition> iterator = player.getConditions().iterator();
		while (iterator.hasNext()) {
			Condition condition = iterator.next();
			if (!condition.nonDeleteIs()) {
				condition.removeCondition(player);
				iterator.remove();
			}
		}
		itemClickFlag = true;
		fusionMode = false;
		betweenShop = false;
		betweenTurn = false;
		goNextFloor = false;
		monsterLive = true;
		turn = 0;
		target = 0;
		finalExp = 0;
		finalGold = 0;
		monsterName = "";
		monsters.clear();
		dungeon.encountMonsters(monsters, floor);
		if (difficulty.getId() == 5) {
			monsters.get(0).eternalCondition(ConditionEnum.ゴールドチャンス);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0, l = monsters.size(); i < l; i++) {
			sb.append(i);
			sb.append("#");
			if (1 < l) {
				sb.append(ImagesRepository.INSTANCE.getMonsterImage(monsters.get(i).getId(), 135));
			} else {
				sb.append(ImagesRepository.INSTANCE.getMonsterImage(monsters.get(i).getId(), 205));
			}
			if (i < l - 1) {
				sb.append("&");
			}
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター表示, sb.toString()));
		for (Monster monster : monsters) {
			CreateMonster.INSTANCE.setStatus(monster);
		}
		if (1 < monsters.size()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ターゲットモンスター, String.valueOf(0)));
		}

		messages.clear();
		String message;
		if (monsters.size() == 1) {
			message = monsters.get(0).getName() + "が現れた";
		} else {
			message = monsters.get(0).getName() + "と" + monsters.get(1).getName() + "が現れた";
		}
		messages.add(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		List<ActionInfo> infos = new ArrayList<>();

		for (Weapon weapon : player.getWeapons()) {
			weapon.battleStart(this, infos);
		}
		for (Condition condition : player.getConditions()) {
			condition.battleStart(this, player, infos);
		}
		for (Monster monster : monsters) {
			for (Condition condition : monster.getConditions()) {
				condition.battleStart(this, monster, infos);
			}
		}
		player.resetCritical();
		turnStart();
	}

	public void turnStart() {
		Battle.addLogs(new ScreenChange(ScreenEnum.ターンスタート, ""));
		turn++;
		player.resetItemUse();
		List<ActionInfo> infos = new ArrayList<>();
		ActionInfo turnInfo = new ActionInfo();
		messages.add("<div class = \"logTurn\">〜" + turn + "ターン目〜</div>");
		infos.add(turnInfo);
		for (Weapon weapon : player.getWeapons()) {
			weapon.turnStart(this, infos);
		}
		for (Condition condition : player.getConditions()) {
			condition.turnStart(this, player, infos);
		}
		for (Monster monster : monsters) {
			if (monster.isDeath()) {
				continue;
			}
			for (Condition condition : monster.getConditions()) {
				condition.turnStart(this, monster, infos);
			}
		}
		for (Item item : player.getItems()) {
			item.turnStart(this, infos);
		}

		clearItemList();
	}

	public void turnEnd(List<ActionInfo> infos) {
		if (0 < player.getItems().size()) {
			if (player.getItems().get(0).getId() == 35) {
				Battle.addLogs(new ScreenChange(ScreenEnum.待機, "300"));
				player.useItem(this, 0, infos);
			}
		}
		for (Weapon weapon : player.getWeapons()) {
			weapon.turnEnd(this, infos);
		}
		for (Condition condition : player.getConditions()) {
			condition.turnEnd(this, player, infos);
		}
		player.getConditions().removeIf(condition -> condition.isConditionEnd());
		for (Monster monster : monsters) {
			if (monster.isDeath()) {
				continue;
			}
			for (Condition condition : monster.getConditions()) {
				condition.turnEnd(this, monster, infos);
			}
		}
		// messageBatch();
		for (Monster monster : monsters) {
			if (monster.isDeath()) {
				continue;
			}
			if (!monster.isDeath() && monster.amountCondition(ConditionEnum.ボーナスターン保持) != 1) {
				monster.plusTurn(-1);
			}
			monster.getConditions().removeIf(condition -> condition.isConditionEnd());
		}
		commonAction(infos);
	}

	public void playerTurn(int action) {
		if (!commandClickFlags.get(action - 1)) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return;
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.コマンドOK, ""));
		Battle.addLogs(new ScreenChange(ScreenEnum.コマンドクリック, ""));
		falseCommandClickFlags();
		itemClickFlag = false;
		betweenTurn = true;
		List<ActionInfo> infos = new ArrayList<>();
		player.setAction(action);
		Battle.addLogs(new ScreenChange(ScreenEnum.コカトリスイッチ削除, ""));
		switch (action) {
		case 1:
			player.attack(this, infos, ActionTypeEnum.プレイヤー通常攻撃, monsters.get(target));
			break;
		case 2:
			player.weekAttack(this, infos);
			break;
		case 3:
			player.criticalAttack(this, infos);
			break;
		case 4:
			player.defence(this, infos);
			break;
		case 5:
			player.tension(this, infos);
			break;
		}
		debugMessages(1);
		if (monsterLive) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
			for (Monster monster : monsters) {
				Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ削除, String.valueOf(monster.getNumber())));
			}
		}
		monsterTurn(infos);
		debugMessages(2);
		if (monsterLive) {
			turnEnd(infos);
		}
		debugMessages(3);
		commonAction(infos);
		debugMessages(4);
		Battle.addLogs(new ScreenChange(ScreenEnum.待機, "200"));

		messageBatch();
		debugMessages(5);
		player.setAction(0);
		if (monsterLive) {
			turnStart();
		}
		debugMessages(6);
		setSumTurn(sumTurn + 1);
	}

	public boolean monsterLiveIs() {
		return monsterLive;
	}

	public void monsterLiveCheck() {
		for (Monster monster : monsters) {
			if (!monster.isDeath()) {
				monsterLive = true;
				return;
			}
		}
		monsterLive = false;
		return;
	}

	public void useItem(int i) {
		if (!itemClickFlag) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return;
		}
		// falseCommandClickFlags();
		itemClickFlag = false;
		betweenTurn = true;
		List<ActionInfo> infos = new ArrayList<>();
		player.useItem(this, i, infos);
		Battle.addLogs(new ScreenChange(ScreenEnum.ターンスタート, ""));
	}

	public void addRemoveItemList(Item item) {
		removeItem.add(item);
	}

	public void clearItemList() {
		for (Item item : removeItem) {
			player.removeItem(item);
		}
		removeItem.clear();
	}

	public void beatMonster(Monster monster) {
		int exp = monster.getEXP();
		int gold = monster.getGold();
		boolean turn = monster.getTurn() > 0;
		boolean over = monster.getOverHP() == 0;
		double bonus = 1.0;

		int b = 0;
		if (turn && over) {
			b = 2;
		} else if (turn || over) {
			b = 1;
		}
		b += monster.amountCondition(ConditionEnum.ボーナスゲット);
		b += player.amountCondition(ConditionEnum.スライム状態);

		if (2 <= b) {
			bonus = 1.5;
			b = 2;
		} else if (b == 1) {
			bonus = 1.3;
		}
		exp = (int) (exp * bonus);
		gold = (int) (gold * bonus);
		finalExp += exp;
		finalGold += gold;
		// monsters.indexOf(monster;)
		// monsters.remove(monster);
		deathMonsters.add(monster);

		if (!Objects.equals(monsterName, "")) {
			monsterName = monster.getName();
		} else {
			monsterName = monster.getName() + "たち";
		}
		if (!monsterLive) {
			clearFloor();
		} else {
			while (true) {
				target++;
				if (monsters.size() <= target) {
					target = 0;
				}
				if (!monsters.get(target).isDeath()) {
					break;
				}
			}
			Battle.addLogs(new ScreenChange(ScreenEnum.待機, String.valueOf(300)));
			Battle.addLogs(new ScreenChange(ScreenEnum.ターゲットモンスター, String.valueOf(target)));
		}
	}

	public void beatMonsterA(Monster monster) {
		int exp = monster.getEXP();
		int gold = monster.getGold();
		boolean turn = monster.getTurn() > 0;
		boolean over = monster.getOverHP() == 0;
		double bonus = 1.0;

		int b = 0;
		if (turn && over) {
			b = 2;
		} else if (turn || over) {
			b = 1;
		}
		b += monster.amountCondition(ConditionEnum.ボーナスゲット);
		b += player.amountCondition(ConditionEnum.スライム状態);

		if (2 <= b) {
			bonus = 1.5;
			b = 2;
		} else if (b == 1) {
			bonus = 1.3;
		}
		exp = (int) (exp * bonus);
		gold = (int) (gold * bonus);
		player.setEXPGold(exp, gold);
		deathMonsters.add(monster);
		if (!monsterLive) {
			clearFloor();
		} else {
			while (true) {
				target++;
				if (monsters.size() <= target) {
					target = 0;
				}
				if (!monsters.get(target).isDeath()) {
					break;
				}
			}
			Battle.addLogs(new ScreenChange(ScreenEnum.待機, String.valueOf(300)));
			Battle.addLogs(new ScreenChange(ScreenEnum.ターゲットモンスター, String.valueOf(target)));
		}
	
	}

	public void clearFloor() {
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター全滅, monsterName + "#" + String.valueOf(finalGold)));
		Battle.addLogs(new ScreenChange(ScreenEnum.リザルト, String.valueOf(finalExp) + "#" + String.valueOf(finalGold)));
		if (player.setEXPGold(finalExp, finalGold)) {
			Battle.addLogs(new ScreenChange(ScreenEnum.レベルアップメッセージ, String.valueOf(player.getLv())));
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
		nextFloorCheck();
	}

	public void nextFloorCheck() {
		goNextFloor = true;
		if (dungeon.getMonsters().size() == 0) {
			Battle.addLogs(new ScreenChange(ScreenEnum.クリア, ""));
		}
		if (floor == dungeon.getShopFloor()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
			dungeon.floorRemove();
		} else if (0 < player.amountCondition(ConditionEnum.いつでもショップ)) {
			Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.次の階, ""));
		}
	}

	public void monsterTurn(List<ActionInfo> infos) {
		for (Monster monster : monsters) {
			if (monster.isDeath()) {
				continue;
			}
			monster.actions(this);
			for (Condition condition : monster.getConditions()) {
				condition.monsterAction(this, monster);
			}
			if (Objects.nonNull(monster.getAction())) {
				monster.getAction().actionEffect(this, monster, infos);
			}
		}
	}

	public void commonAction(List<ActionInfo> infos) {
		while (infos.size() > 0) {
			ActionInfo info = infos.get(0);
			if (Objects.isNull(info.getActionType())) {
				info.setActionType(ActionTypeEnum.無し);
			}
			infos.remove(info);
			if (info.getActionType() == ActionTypeEnum.ダメージ || info.getActionType() == ActionTypeEnum.プレイヤー通常攻撃
					|| info.getActionType() == ActionTypeEnum.プレイヤー手加減
					|| info.getActionType() == ActionTypeEnum.プレイヤー強攻撃
					|| info.getActionType() == ActionTypeEnum.モンスターの攻撃 || info.getActionType() == ActionTypeEnum.カウンター
					|| info.getActionType() == ActionTypeEnum.火の玉) {
				info.getReceiver().calcDamage(this, infos, info);
			} else if (info.getActionType() == ActionTypeEnum.回復) {
				info.getReceiver().calcHeal(this, infos, info);
			} else if (info.getActionType() == ActionTypeEnum.テンション) {
				player.plusTension(this, infos, info);
			}
			messageBatchs.addAll(info.getMessages());
		}
	}

	public void conditionMessage(Living living, String name) {
		if (living instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーコンディションメッセージ, name));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスターコンディションメッセージ, name));
		}
	}

	public void rightSwitchItem() {
		player.rightSwitchItem();
	}

	public void leftSwitchItem() {
		player.leftSwitchItem();
	}

	public List<Monster> getMonsters() {
		return monsters;
	}
	// public void setMonster(Monster monster) {
	// this.monster = monster;
	// }

	public Player getPlayer() {
		return player;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public Monster getTargetMonster() {
		return monsters.get(target);
	}

	public int getMonsterDamage() {
		return monsterDamage;
	}

	public void addMessageBatch(List<ActionInfo> infos) {
		for (ActionInfo info : infos) {
			messageBatchs.addAll(info.getMessages());
		}
	}

	public void messageBatch() {
		messages.addAll(messageBatchs);
		messageBatchs.clear();
	}

	public List<String> getMessages() {
		return messages;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public int getFloor() {
		return floor;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
		Battle.addLogs(new ScreenChange(ScreenEnum.難易度, difficulty.getLogicalName()));
	}

	public void setFloor(int floor) {
		this.floor = floor;
		Battle.addLogs(new ScreenChange(ScreenEnum.階数, String.valueOf(floor)));
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public static void addLogs(ScreenChange sc) {
		logs.add(sc);
	}

	public static List<ScreenChange> getLogs() {
		return logs;
	}

	public static void clearLogs() {
		logs.clear();
	}

	public CommonEffect getCommonEffect() {
		return commonEffect;
	}

	public int getFusionPrice() {
		return fusionPrice;
	}

	public void setFusionPrice(int fusionPrice) {
		this.fusionPrice = fusionPrice;
	}

	public boolean isItemClickFlag() {
		return itemClickFlag;
	}

	public boolean isFusionMode() {
		return fusionMode;
	}

	public boolean isBetweenShop() {
		return betweenShop;
	}

	public boolean isGoNextFloor() {
		return goNextFloor;
	}

	public void setCommonEffect(CommonEffect commonEffect) {
		this.commonEffect = commonEffect;
	}

	public void resetCommandClickFlags() {
		for (int i = 0; i < 5; i++) {
			commandClickFlags.set(i, player.getCommandClickFlags().get(i));
		}
	}

	public void falseCommandClickFlags() {
		for (int i = 0; i < 5; i++) {
			commandClickFlags.set(i, false);
		}
		itemClickFlag = false;
	}

	public void setItemClickFlag(boolean itemClickFlag) {
		this.itemClickFlag = itemClickFlag;
	}

	public void setFusionMode(boolean fusionMode) {
		this.fusionMode = fusionMode;
	}

	public void setBetweenShop(boolean betweenShop) {
		this.betweenShop = betweenShop;
	}

	public void setGoNextFloor(boolean goNextFloor) {
		this.goNextFloor = goNextFloor;
	}

	public boolean isBetweenTurn() {
		return betweenTurn;
	}

	public void setBetweenTurn(boolean betweenTurn) {
		this.betweenTurn = betweenTurn;
	}

	public void setSumTurn(int sumTurn) {
		this.sumTurn = sumTurn;
		Battle.addLogs(new ScreenChange(ScreenEnum.合計ターン, String.valueOf(sumTurn)));
	}

	public List<BookPage> getPages() {
		return pages;
	}

	public void setPages(List<BookPage> pages) {
		this.pages = pages;
	}

	public BookPage getLastPage() {
		return pages.get(pages.size() - 1);
	}

	public void removeLastPage() {
		pages.remove(pages.size() - 1);
	}

	public void pageAdd(BookPage page) {
		pages.add(page);
	}

	public void debugMessages(int n) {
		System.out.println("メッセージ番号: " + n);
		for (String message : messageBatchs) {
			System.out.println(message);
		}
		System.out.println("メッセージ終了番号: " + n);
	}

	public void debugPages(String method) {
		System.out.println("ページメソッド: " + method);
		for (BookPage page : pages) {
			System.out.println(page.getName());
		}
		System.out.println("ページ終了メソッド: " + method);

	}
}