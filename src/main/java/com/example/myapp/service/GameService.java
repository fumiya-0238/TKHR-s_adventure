package com.example.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.creater.CreateMonster;
import com.example.myapp.model.Difficulty;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.items.Item;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.Dungeon;
import com.example.myapp.repository.ImagesRepository;
import com.example.myapp.repository.SQLGetter;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;
import com.example.myapp.repository.ShopItem;
import com.example.myapp.repository.ShopWeapon;
import com.example.myapp.repository.book.ActionPage;
import com.example.myapp.repository.book.BookPage;
import com.example.myapp.repository.book.ConditionPage;
import com.example.myapp.repository.book.ItemPage;
import com.example.myapp.repository.book.PictureBook;
import com.example.myapp.repository.book.WeaponPage;

@Service
public class GameService {
	private Battle battle;
	private SQLGetter sql;
	private int sumTurn;
	private JdbcTemplate jdbcTemplate;
	private PictureBook pictureBook;
	private int speed;

	public GameService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void init() {
		battle = new Battle();
		Dungeon dungeon = new Dungeon();
		battle.setDungeon(dungeon);
		pictureBook = new PictureBook();
		sql = new SQLGetter();
		sql.init(jdbcTemplate, pictureBook, dungeon);
		battle.setPictureBook(pictureBook);
		sql.relation(jdbcTemplate, pictureBook);
	}

	public void setDifficulty(Difficulty difficulty) {
		if (Objects.isNull(battle)) {
			init();
		}
		battle.setDifficulty(difficulty);

	}

	public void goFirstFloor() {
		sql.goFirstFloor(jdbcTemplate, battle.getDifficulty(), battle.getDungeon(), pictureBook);
		battle.setFloor(1);
		setSumTurn(1);
		battle.getPlayer().setLV(battle.getDifficulty().getLv());
		battle.playerReset();
		battle.setMonster(CreateMonster.INSTANCE.create(battle.getDungeon().getMonsters().get(battle.getFloor() - 1)));
		battle.battleStart();
	}

	public void setSumTurn(int sumTurn) {
		this.sumTurn = sumTurn;
		Battle.addLogs(new ScreenChange(ScreenEnum.合計ターン, String.valueOf(sumTurn)));
	}

	public void nextFloor() {
		battle.setFloor(battle.getFloor() + 1);
		battle.setMonster(CreateMonster.INSTANCE.create(battle.getDungeon().getMonsters().get(battle.getFloor() - 1)));
		battle.battleStart();
	}

	public void playerTurn(int action) {
		if (action == 3 && battle.getPlayer().getCritical() == 0) {
			return;
		}
		battle.playerTurn(action);
		setSumTurn(sumTurn + 1);
	}

	public boolean playerBoost() {
		if (battle.getPlayer().getBoost() == 100) {
			battle.getPlayer().toggleBoost();
		}
		return battle.getPlayer().getBoostOn();
	}

	/*
		public void monsterTurn() {
			battle.monsterTurn();
		}
	*/
	public void useItem(int i) {
		battle.useItem(i);
	}

	public void returnTitle() {

	}

	public String getMonsterImage() {
		return ImagesRepository.INSTANCE.getMonsterImage(battle.getMonster().getID(), 205);
	}

	/*
		public Map<String, String> makeMap() {
			Map<String, String> map = new HashMap<>();
			makePlayerMap(map);
			makeMonsterMap(map);
			map.put("Difficulty", difficulty.getName());
			map.put("Floor", String.valueOf(floor));
			map.put("SumTurn", String.valueOf(sumTurn));
			String game;
			if (battle.getPlayer().getHP() == 0) {
				game = "gameover";
			} else if (battle.getMonster().getHP() == 0) {
				game = beatMonster();
			} else {
				game = "turn";
			}
			map.put("Game", game);//0 そのまま 1:勝利 2:店 3:敗北
			return map;
		}
	
		private void makePlayerMap(Map<String, String> map) {
			Player player = battle.getPlayer();
			map.put("LV", String.valueOf(player.getLV()));
			map.put("HP", String.valueOf(player.getHP()));
			map.put("MAXHP", String.valueOf(player.getMAXHP()));
			map.put("ATK", String.valueOf(player.getAttack()));
			makeWeaponMap(map);
			map.put("Tension", String.valueOf(player.getTension()));
			map.put("EXP", String.valueOf(player.getEXP()));
			map.put("Gold", String.valueOf(player.getGold()));
			map.put("Critical", String.valueOf(player.getCritical()));
			map.put("Boost", String.valueOf(player.getBoost()));
			map.put("SumEXP", String.valueOf(player.getsumEXP()));
			makeItemsMap(map);
			List<String> messages = battle.getMessages();
			int size = messages.size();
			for (int i = 0; i < size; i++) {
				String message = messages.get(i);
				map.put("MessageText" + i, message);
			}
			map.put("MessageSize", String.valueOf(size));
		}
	
		private void makeWeaponMap(Map<String, String> map) {
			Weapon weapon = battle.getPlayer().getWeapons().get(0);
			map.put("WeaponName", String.valueOf(weapon.getName()));
			map.put("WeaponATK", String.valueOf(weapon.getAttack()));
			map.put("WeaponText", String.valueOf(weapon.getText()));
			if (battle.getPlayer().getWeapons().size() > 1) {
				Weapon subWeapon = battle.getPlayer().getWeapons().get(1);
				map.put("SubWeaponName", String.valueOf(subWeapon.getName()));
			} else {
				map.put("SubWeaponName", "");
			}
		}
	
		private void makeItemsMap(Map<String, String> map) {
			List<Item> items = battle.getPlayer().getItems();
			int size = items.size();
			for (int i = 0; i < size; i++) {
				Item item = items.get(i);
				map.put("ItemName" + i, String.valueOf(item.getName()));
				map.put("ItemText" + i, String.valueOf(item.getText()));
				map.put("ItemActive" + i, String.valueOf(item.getActive()));
			}
			map.put("ItemSize", String.valueOf(size));
		}
	
		private void makeMonsterMap(Map<String, String> map) {
			Monster monster = battle.getMonster();
			map.put("MonsterName", String.valueOf(monster.getName()));
			map.put("MonsterHP", String.valueOf(monster.getHP()));
			map.put("MonsterOverHP", String.valueOf(monster.getOverHP()));
			map.put("MonsterATK", String.valueOf(monster.getAttack()));
			map.put("MonsterEXP", String.valueOf(monster.getEXP()));
			map.put("MonsterGold", String.valueOf(monster.getGold()));
			map.put("MonsterTurn", String.valueOf(monster.getTurn()));
		}
	
	
	
	
		public Map<String, String> showItem() {
			Map<String, String> map = new HashMap<>();
			int itemSize = dungeon.getItems().size();
			for (int i = 0; i < itemSize; i++) {
				ShopItem si = dungeon.getItems().get(i);
				map.put("ShopItemName" + i, si.getName());
				map.put("ItemPrice" + i, String.valueOf(si.getPrice()));
				map.put("ItemStock" + i, si.getStock());
			}
			map.put("ShopItemSize", String.valueOf(itemSize));
			return map;
		}
	
		public Map<String, String> makeWeaponInfo() {
			Map<String, String> map = new HashMap<>();
			pictureBook.getWeaponPages();
			int weaponSize = dungeon.getWeapons().size();
			for (int i = 0; i < weaponSize; i++) {
				ShopWeapon sw = dungeon.getWeapons().get(i);
				map.put("WeaponName" + i, sw.getName());
				map.put("WeaponPrice" + i, String.valueOf(sw.getPrice()));
				map.put("WeaponAttack" + i, String.valueOf(sw.getAttack()));
				map.put("WeaponStock" + i, sw.getStock());
			}
			map.put("WeaponSize", String.valueOf(weaponSize));
			return map;
		}
	
	*/
	public Map<String, String> makeShop() {
		Map<String, String> map = new HashMap<>();
		Dungeon dungeon = battle.getDungeon();
		map.putAll(showItem());
		int weaponSize = dungeon.getWeapons().size();
		for (int i = 0; i < weaponSize; i++) {
			ShopWeapon sw = dungeon.getWeapons().get(i);
			map.put("WeaponName" + i, sw.getName());
			map.put("WeaponPrice" + i, String.valueOf(sw.getPrice()));
			map.put("WeaponAttack" + i, String.valueOf(sw.getAttack()));
			map.put("WeaponStock" + i, sw.getStock());
		}
		map.put("WeaponSize", String.valueOf(weaponSize));
		return map;
	}

	public Map<String, String> showItem() {
		Map<String, String> map = new HashMap<>();
		Dungeon dungeon = battle.getDungeon();
		int itemSize = dungeon.getItems().size();
		for (int i = 0; i < itemSize; i++) {
			ShopItem si = dungeon.getItems().get(i);
			map.put("ShopItemName" + i, si.getName());
			map.put("ItemPrice" + i, String.valueOf(si.getPrice()));
			map.put("ItemStock" + i, si.getStock());
		}
		map.put("ShopItemSize", String.valueOf(itemSize));
		return map;
	}

	public void sortItem(String sort) {
		battle.getDungeon().sortItem(sort);
	}

	public void buyItem(String index) {
		Player player = battle.getPlayer();
		Item item = CreateItem.INSTANCE.create(battle.getDungeon().getItems().get(Integer.parseInt(index)).getId());
		int price = item.getPrice();
		if (price <= player.getGold()) {
			player.setItem(item);
			player.plusGold(-item.getPrice());
		}
	}

	public List<String> makeList() {
		List<String> list = new ArrayList<>();
		for (int i = 0, l = Battle.getLogs().size(); i < l; i++) {
			ScreenChange sc = Battle.getLogs().get(i);
			list.add(sc.getScreenEnum().getLogName() + "/" + sc.getStatus());
		}

		Battle.clearLogs();
		return list;
	}

	public Map<String, String> makeConditionMap() {
		Map<String, String> map = new HashMap<>();
		List<Condition> playerConditions = battle.getPlayer().getConditions();
		int playerConditionSize = playerConditions.size();
		for (int i = 0; i < playerConditionSize; i++) {
			Condition c = playerConditions.get(i);
			map.put("PlayerConditionName" + i, c.getName());
			map.put("PlayerConditionAmount" + i, String.valueOf(c.getAmount()));
			map.put("PlayerConditionTurn" + i, c.getTurn());
		}
		map.put("PlayerConditionSize", String.valueOf(playerConditionSize));
		return map;
	}

	public List<String> makeSubPageInfo(String page, int i) {
		List<String> info = new ArrayList<>();
		String[] arrays;
		String pageKind;
		int pageId;
		switch (page) {
		case "myWeapon":
			pageKind = "weapon";
			int weaponId = battle.getPlayer().getWeapons().get(0).getId() - 1;
			int subSize;
			if (Objects.isNull(pictureBook.getWeaponPages().get(weaponId).getSubPage())) {
				subSize = 0;
			} else {
				subSize = pictureBook.getWeaponPages().get(weaponId).getSubPage().size();
			}
			if (i < subSize) {
				pageId = weaponId;
			} else {
				pageId = battle.getPlayer().getWeapons().get(1).getId() - 1;
				i -= subSize;
			}
			break;
		default:
			arrays = page.split("/");
			pageKind = arrays[0];
			pageId = Integer.parseInt(arrays[1]) - 1;
		}

		BookPage subBookPage;
		switch (pageKind) {
		case "item":
			subBookPage = pictureBook.getItemPages().get(pageId).getSubPage().get(i);
			break;
		case "weapon":
			subBookPage = pictureBook.getWeaponPages().get(pageId).getSubPage().get(i);
			break;
		case "action":
			subBookPage = pictureBook.getActionPages().get(pageId).getSubPage().get(i);
			break;
		case "condition":
			subBookPage = pictureBook.getConditionPages().get(pageId).getSubPage().get(i);
			break;
		case "monster":
			subBookPage = pictureBook.getMonsterPages().get(pageId).getSubPage().get(i);
			break;
		default:
			subBookPage = pictureBook.getItemPages().get(pageId).getSubPage().get(i);
		}
		if (subBookPage instanceof ItemPage) {
			makeItemInfo(info, (ItemPage) subBookPage);
		} else if (subBookPage instanceof WeaponPage) {
			makeWeaponInfo(info, (WeaponPage) subBookPage);
		} else if (subBookPage instanceof ActionPage) {
			makeActionInfo(info, (ActionPage) subBookPage);
		} else if (subBookPage instanceof ConditionPage) {
			makeConditionInfo(info, (ConditionPage) subBookPage);
		}
		return info;
	}

	private void makeItemInfo(List<String> info, ItemPage itemPage) {
		info.add("item/" + itemPage.getId());
		info.add(itemPage.getName());//"<div id=\"itemName\">"+ 
		info.add(String.valueOf(itemPage.getPrice()));//"<div id=\"itemPrice\">"
		List<String> texts = itemPage.getTexts();
		makeInfoText(info, texts);
	}

	private void makeWeaponInfo(List<String> info, WeaponPage weaponPage) {
		info.add("weapon/" + weaponPage.getId());
		info.add(weaponPage.getName());
		info.add(String.valueOf(weaponPage.getAttack()));
		info.add(String.valueOf(weaponPage.getPrice()));
		List<String> texts = weaponPage.getTexts();
		makeInfoText(info, texts);
	}

	private void makeActionInfo(List<String> info, ActionPage actionPage) {
		info.add("action/" + actionPage.getId());
		info.add(actionPage.getName());
		if (actionPage.isAttackIs()) {
			info.add("攻撃");
		} else {
			info.add("特殊");
		}
		List<String> texts = actionPage.getTexts();
		makeInfoText(info, texts);
	}

	private void makeConditionInfo(List<String> info, ConditionPage conditionPage) {
		info.add("condition/" + conditionPage.getId());
		info.add(conditionPage.getName());
		if (conditionPage.isDuplication()) {
			info.add("可");
		} else {
			info.add("不可");
		}
		List<String> texts = conditionPage.getTexts();
		makeInfoText(info, texts);
	}

	private void makeInfoText(List<String> info, List<String> texts) {
		String regex = "<[^>]+>";
		Pattern pattern = Pattern.compile(regex);
		for (String text : texts) {
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				String matched = matcher.group(); // 一致した文字列
				String matchedResult = matched.substring(1, matched.length() - 1);
				info.add(matchedResult + "/infoLink");
			} else {
				String[] arrays = text.split("　");
				for (int i = 0, l = arrays.length-1; i <= l; i++) {
					char lastChar = text.charAt(text.length() - 1);
					System.out.println(lastChar);
					if (i == l && lastChar !='　') {
						info.add(arrays[i]);
					} else {
						info.add(arrays[i] + "/br");
					}
				}
			}
		}
		System.out.println(info);
	}

	public List<String> makePlayerItemInfo(int i) {
		List<String> info = new ArrayList<>();
		int id = battle.getPlayer().getItems().get(i).getId();
		makeItemInfo(info, pictureBook.getItemPages().get(id - 1));
		return info;
	}

	private void myWeaponInfo(List<String> info, WeaponPage weaponPage) {
		info.add(weaponPage.getName());
		info.add(String.valueOf(weaponPage.getAttack()));
		List<String> texts = weaponPage.getTexts();
		makeInfoText(info, texts);
	}

	public List<String> makeMyWeaponInfo() {
		List<String> info = new ArrayList<>();
		for (int i = 0, l = battle.getPlayer().getWeapons().size(); i < l; i++) {
			info.add("weapon" + i);
			int id = battle.getPlayer().getWeapons().get(i).getId();
			myWeaponInfo(info, pictureBook.getWeaponPages().get(id - 1));
		}
		return info;
	}
}