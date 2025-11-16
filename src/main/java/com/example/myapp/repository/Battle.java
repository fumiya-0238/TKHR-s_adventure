package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateMonster;
import com.example.myapp.model.Difficulty;
import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.book.BookPage;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Battle {
	private Player player;
	private Monster monster;
	private int monsterDamage;
	private List<String> messageBatchs;
	private List<String> messages;
	//private List<String> windowMessages;
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
	//private List<String> pageNames;
	private List<BookPage> pages;
	private final String requestId = UUID.randomUUID().toString();
	private final long createdAt = System.currentTimeMillis();

	public Battle() {
		player = new Player();
		messages = new ArrayList<>();
		messageBatchs = new ArrayList<>();
		commandClickFlags = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			commandClickFlags.add(true);
		}
		dungeon = new Dungeon();
		logs = new ArrayList<>();
		commonEffect = new CommonEffect();
		removeItem = new ArrayList<>();
		pages = new ArrayList<>();
	}

	public void playerReset() {
		player.resetStatus();
	}

	public void setPlayerLV(Difficulty difficulty) {
		player.setLV(difficulty.getLv());
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

		player.setLV(difficulty.getLv());
		playerReset();
		setMonster(CreateMonster.INSTANCE.create(dungeon.getMonsters().get(floor - 1)));
		battleStart();
	}

	public void battleStart() {
		//resetCommandClickFlags();
		itemClickFlag = true;
		fusionMode = false;
		betweenShop = false;
		betweenTurn = false;
		goNextFloor = false;
		turn = 0;
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター表示, ""));
		messages.clear();
		String message = monster.getName() + "が現れた";
		messages.add(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		List<ActionInfo> infos = new ArrayList<>();

		for (Weapon weapon : player.getWeapons()) {
			weapon.battleStart(this, infos);
		}
		for (Condition condition : player.getConditions()) {
			condition.battleStart(this, player, infos);
		}
		for (Condition condition : monster.getConditions()) {
			condition.battleStart(this, monster, infos);
		}
		player.resetCritical();
		/*		for (ActionInfo info : infos) {
					messages.addAll(info.getMessages());
				}*/
		turnStart();
	}

	public void turnStart() {
		Battle.addLogs(new ScreenChange(ScreenEnum.ターンスタート, ""));
		turn++;
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
		for (Condition condition : monster.getConditions()) {
			condition.turnStart(this, monster, infos);
		}
		for (Item item : player.getItems()) {
			item.turnStart(this, infos);
		}

		clearItemList();
		/*for (ActionInfo info : infos) {
			messages.addAll(info.getMessages());
		}*/
	}

	public void turnEnd(List<ActionInfo> infos) {
		for (Weapon weapon : player.getWeapons()) {
			weapon.turnEnd(this, infos);
		}
		for (Condition condition : player.getConditions()) {
			condition.turnEnd(this, player, infos);
		}
		player.getConditions().removeIf(condition -> condition.getTurn() == 0);
		for (Condition condition : monster.getConditions()) {
			condition.turnEnd(this, monster, infos);
		}
		messageBatch();
		monster.getConditions().removeIf(condition -> condition.getTurn() == 0);
		if (!monster.deathIs()) {
			monster.plusTurn(-1);
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
			player.attack(this, infos);
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
		if (!monster.deathIs()) {
			
			messageBatch();
			Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ削除, ""));
			monsterTurn(infos);
			turnEnd(infos);
		}

		/*	for (ActionInfo info : infos) {
				messages.addAll(info.getMessages());
			}*/
		player.setAction(0);
		if (!monster.deathIs()) {
			turnStart();
		}
		setSumTurn(sumTurn + 1);
	}

	public void useItem(int i) {
		falseCommandClickFlags();
		itemClickFlag = false;
		betweenTurn = true;
		List<ActionInfo> infos = new ArrayList<>();
		//infos.add(new ActionInfo());
		Item item = player.getItems().get(i);
		ActionInfo info = new ActionInfo();
		String message = "プレイヤーは" + item.getName() + "を使った";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		infos.add(info);
		item.use(this, infos);
		if (item.getActive()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.アクティブアイテム, String.valueOf(i)));
		} else {
			player.removeItem(i);
		}

		for (Condition condition : player.getConditions()) {
			condition.useItem(this, player, infos);
		}
		commonAction(infos);
		addMessageBatch(infos);
		messageBatch();
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

	public void nextFloorCheck() {
		goNextFloor = true;
		if (floor == dungeon.getMonsters().size()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.クリア, ""));
		}
		if (0 < player.amountCondition(ConditionEnum.いつでもショップ)) {
			Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
		} else if (floor == dungeon.getShopFloor()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
			dungeon.floorRemove();
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.次の階, ""));
		}
	}

	public void monsterTurn(List<ActionInfo> infos) {
		monster.actions(this);
		for (Condition condition : monster.getConditions()) {
			condition.monsterAction(this);
		}
		monster.getAction().actionEffect(this, infos);
	}

	public void commonAction(List<ActionInfo> infos) {
		while (infos.size() > 0) {
			ActionInfo info = infos.get(0);
			if (info.getActionType() == 1 || info.getActionType() == 4 || info.getActionType() == 5) {
				info.getReceiver().calcDamage(this, infos);
			} else if (info.getActionType() == 2) {
				info.getReceiver().calcHeal(this, infos);
			} else if (info.getActionType() == 3) {
				player.plusTension(this, infos);
			}
			messageBatchs.addAll(info.getMessages());
		}
	}
	//processFlag = true;
	public ActionInfo searchAction() {
		while (infos.size() > 0) {
		infos.get(0).processFlagIsTrue();
	}
	}
		
	public void conditionMessage(Living living, String name) {
		if (living instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーコンディションメッセージ, name));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスターコンディションメッセージ, name));
		}
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public Player getPlayer() {
		return player;
	}

	public Monster getMonster() {
		return monster;
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
}