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

import com.example.myapp.creater.CreateDungeon;
import com.example.myapp.creater.CreateItem;
import com.example.myapp.creater.CreateService;
import com.example.myapp.creater.CreateWeapon;
import com.example.myapp.model.Difficulty;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.dungeons.Dungeon;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.services.ServiceItem;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ImagesRepository;
import com.example.myapp.repository.SQLGetter;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;
import com.example.myapp.repository.book.ActionPage;
import com.example.myapp.repository.book.BookPage;
import com.example.myapp.repository.book.ConditionListPage;
import com.example.myapp.repository.book.ConditionPage;
import com.example.myapp.repository.book.ItemPage;
import com.example.myapp.repository.book.MonsterPage;
import com.example.myapp.repository.book.MyWeaponPage;
import com.example.myapp.repository.book.PictureBook;
import com.example.myapp.repository.book.ServicePage;
import com.example.myapp.repository.book.WeaponPage;
import com.example.myapp.repository.shop.ShopItem;
import com.example.myapp.repository.shop.ShopService;
import com.example.myapp.repository.shop.ShopWeapon;

@Service
public class GameService {
	private final Battle battle;
	private SQLGetter sql;
	private final JdbcTemplate jdbcTemplate;
	private PictureBook pictureBook;
	private List<Difficulty> difficulties;
	private final boolean debugPermission = true;
	private boolean debugMode;
	public static List<Integer> beginnerWeapons;

	public GameService(JdbcTemplate jdbcTemplate) {
		this.battle = new Battle();
		this.jdbcTemplate = jdbcTemplate;
		difficulties = new ArrayList<>();
		pictureBook = new PictureBook();
		beginnerWeapons = new ArrayList<>();
		sql = new SQLGetter();
		sql.init(difficulties, jdbcTemplate, pictureBook);
		int beginnerWeaponSize = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM beginner_weapon", Integer.class);
		for (int i = 1; i <= beginnerWeaponSize; i++) {
			beginnerWeapons.add(jdbcTemplate.queryForObject("SELECT weapon_id FROM beginner_weapon WHERE id = ?",
					Integer.class, i));
		}
		sql.relation(jdbcTemplate, pictureBook);
	}

	public void init() {
		battle.init();
		StringBuilder sb = new StringBuilder();
		for (int i = 3, l = difficulties.size(); i < l; i++) {
			sb.append(difficulties.get(i).getLogicalName());
			sb.append("#");
			sb.append(difficulties.get(i).getPhysicalName());
			if (i < l - 1) {
				sb.append("&");
			}
		}

		Battle.addLogs(new ScreenChange(ScreenEnum.ダンジョン一覧, sb.toString()));
		sb = new StringBuilder();
		for (int i = 0, l = difficulties.size(); i < l; i++) {
			sb.append(difficulties.get(i).getShortLogicalName());
			sb.append("#");
			sb.append(difficulties.get(i).getPhysicalName());
			if (i < l - 1) {
				sb.append("&");
			}
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.デバッグダンジョンリスト, sb.toString()));
	}

	public void setDifficulty(int difficulty) {
		battle.setDifficulty(difficulties.get(difficulty - 1));
	}

	public void goFirstFloor(int difficulty) {
		Battle.addLogs(new ScreenChange(ScreenEnum.ダンジョンスタート, ""));
		setDifficulty(difficulty);
		battle.setDungeon(CreateDungeon.INSTANCE.create(difficulty));
		sql.goFirstFloor(jdbcTemplate, battle.getDifficulty(), battle.getDungeon(), pictureBook);
		battle.goFirstFloor();
	}

	public void nextFloor() {
		if (battle.getPlayer().getPriceMulti() == 0) {
			getDebugData();
			return;
		}
		if (!battle.isGoNextFloor()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return;
		}
		battle.setGoNextFloor(false);
		battle.setBetweenShop(false);
		battle.setFloor(battle.getFloor() + 1);
		// battle.addMonster(CreateMonster.INSTANCE.create(battle.getDungeon().getMonsters().get(battle.getFloor()
		// - 1)));
		battle.battleStart();
	}

	public List<String> playerTurn(int action) {
		if (action == 3 && battle.getPlayer().getCritical() == 0) {
			Battle.addLogs(new ScreenChange(ScreenEnum.お金が足りない, ""));
			return makeList();
		}
		battle.playerTurn(action);
		return makeList();
	}

	public List<String> playerBoost() {
		if (battle.getPlayer().getBoost() == 100) {
			battle.getPlayer().toggleBoost();
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.お金が足りない, ""));
		}
		String attack;
		String weekAttack;
		String criticalAttack;
		String defence;
		String tameru;
		String boost;
		if (battle.getPlayer().getBoostOn()) {
			attack = "敵に攻撃力の1.5倍のダメージを与え、このバトルの間、貫通状態になる";
			weekAttack = "気合の効果の影響を受け、気合が減らない";
			criticalAttack = "敵に攻撃力の2倍のダメージを与える";
			defence = "このターン、受けるダメージを0にし、HPを100%回復する";
			tameru = "気合が100%上昇する。攻撃のダメージが気合%分上昇する";
			boost = "ブーストゲージが100%のとき、コマンドを切り替える";
			Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオン, ""));
		} else {
			attack = "攻撃力と同じダメージを与える";
			weekAttack = "攻撃力-1のダメージを与える(気合の影響を受けない)";
			criticalAttack = "攻撃力の1.5倍のダメージを与える";
			defence = "このターン、受けるダメージを0にし、HPを20%回復する";
			tameru = "気合が25%上昇する。攻撃のダメージが気合%分上昇する";
			boost = "ブーストゲージが100%のとき、コマンドを切り替える";
			Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
		}
		String commandText = attack + "#" + weekAttack + "#" + criticalAttack + "#" + defence + "#" + tameru + "#"
				+ boost;
		Battle.addLogs(new ScreenChange(ScreenEnum.コマンドテキスト, commandText));
		return makeList();
	}

	public List<String> useItem(int i) {
		if (!battle.isItemClickFlag()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return makeList();
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.コマンドクリック, ""));
		battle.useItem(i);
		return makeList();
	}

	public List<String> targetMonster(int i) {
		if (battle.getMonsters().size() < 2) {
			return makeList();
		}
		battle.setTarget(i);
		Battle.addLogs(new ScreenChange(ScreenEnum.ターゲットモンスター, String.valueOf(i)));
		return makeList();
	}

	public void resetCommandClickFlags() {
		battle.resetCommandClickFlags();
		battle.setItemClickFlag(true);
		battle.setBetweenTurn(false);
	}

	/*
	 * public String getMonsterImage() { return
	 * ImagesRepository.INSTANCE.getMonsterImage(battle.getMonster().getId(), 205);
	 * }
	 */

	public String getSampleMonsterImage(int liIndex, int listIndex) {
		String difficultySql = difficulties.get(listIndex).getPhysicalName();
		int monsterId = jdbcTemplate.queryForObject("SELECT monster_id FROM " + difficultySql + "_monster WHERE id = ?",
				Integer.class, liIndex + 1);
		return ImagesRepository.INSTANCE.getMonsterImage(monsterId, 98);
	}

	public List<String> getBattleLog() {
		return battle.getMessages();
	}

	public Map<String, List<String>> makeShop() {
		battle.setBetweenShop(true);
		Map<String, List<String>> map = new HashMap<>();
		battle.getDungeon().makeShowItems();
		battle.getDungeon().makeShowWeapons();
		map.put("shopItem", makeShopItem());
		map.put("shopWeapon", makeShopWeapon());
		map.put("shopService", makeShopService());
		return map;
	}

	public List<String> makeShopItem() {
		List<String> list = new ArrayList<>();
		Dungeon dungeon = battle.getDungeon();
		for (ShopItem si : dungeon.getShowItems()) {
			list.add(si.getName());
			list.add(String.valueOf((int) (si.getPrice() * battle.getPlayer().getPriceMulti()
					* battle.getPlayer().getPriceMultiItem())));
			list.add(getInfinity(si.getStock()));
		}
		return list;
	}

	public List<String> makeShopWeapon() {
		List<String> list = new ArrayList<>();
		Dungeon dungeon = battle.getDungeon();
		for (ShopWeapon sw : dungeon.getWeapons()) {
			list.add(sw.getName());
			list.add(String.valueOf((int) (sw.getPrice() * battle.getPlayer().getPriceMulti())));
			list.add(String.valueOf(sw.getAttack()));
			list.add(getInfinity(sw.getStock()));
		}
		return list;
	}

	public List<String> makeShopService() {
		List<String> list = new ArrayList<>();
		Dungeon dungeon = battle.getDungeon();
		for (ShopService ss : dungeon.getServices()) {
			list.add(ss.getName());
			list.add(String.valueOf((int) (ss.getPrice() * battle.getPlayer().getPriceMulti())));
			list.add(getInfinity(ss.getStock()));
		}
		return list;
	}

	public List<String> sortItem(String sort) {
		battle.getDungeon().sortItem(sort);
		return makeShopItem();
	}

	public List<String> sortWeapon(String sort) {
		battle.getDungeon().sortWeapon(sort);
		return makeShopWeapon();
	}

	public Map<String, List<String>> buyItem(int index) {
		Map<String, List<String>> map = new HashMap<>();
		if (!battle.isBetweenShop()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return map;
		}
		Player player = battle.getPlayer();
		ShopItem sItem = battle.getDungeon().getShowItems().get(index);
		Item item = CreateItem.INSTANCE.create(sItem.getId());
		int price = (int) (sItem.getPrice() * battle.getPlayer().getPriceMulti()
				* battle.getPlayer().getPriceMultiItem());
		if (price <= player.getGold() && player.getItems().size() < player.getMaxItem()) {
			player.addItem(item);
			player.plusGold(-price);
			if (sItem.plusStock(-1)) {
				battle.getDungeon().getShowItems().remove(index);
			}
			Battle.addLogs(new ScreenChange(ScreenEnum.買う, String.valueOf(price)));
			map.put("itemList", makeShopItem());
			map.put("playerStatus", makeList());
			return map;
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.お金が足りない, ""));
			return map;
		}
	}

	public Map<String, List<String>> buyWeapon(int index) {
		Map<String, List<String>> map = new HashMap<>();
		if (!battle.isBetweenShop()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return map;
		}
		if (battle.isFusionMode()) {
			return fusionWeapon(map, index);
		}
		Player player = battle.getPlayer();
		ShopWeapon sWeapon = battle.getDungeon().getWeapons().get(index);
		Weapon weapon = CreateWeapon.INSTANCE.create(sWeapon.getId());
		int price = (int) (sWeapon.getPrice() * battle.getPlayer().getPriceMulti());
		if (price <= player.getGold()) {
			player.equip(weapon);
			player.plusGold(-price);
			Battle.addLogs(new ScreenChange(ScreenEnum.買う, String.valueOf(price)));
			map.put("itemList", makeShopItem());
			map.put("weaponList", makeShopWeapon());
			map.put("serviceList", makeShopService());
			map.put("playerStatus", makeList());
			return map;
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.お金が足りない, ""));
			return map;
		}
	}

	public Map<String, List<String>> fusionWeapon(Map<String, List<String>> map, int index) {
		if (!battle.isBetweenShop()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return map;
		}
		Player player = battle.getPlayer();
		ShopWeapon sWeapon = battle.getDungeon().getWeapons().get(index);
		Weapon weapon = CreateWeapon.INSTANCE.create(sWeapon.getId());
		int price = (int) (sWeapon.getPrice() * battle.getPlayer().getPriceMulti());
		int playerWeaponId = player.getWeapons().get(0).getId();
		if (price <= player.getGold() && playerWeaponId != 1 && weapon.getId() != playerWeaponId) {
			player.equipSubWeapon(weapon);
			player.plusGold(-price);
			player.plusPriceMulti(1 / 1.5);
			Battle.addLogs(new ScreenChange(ScreenEnum.買う, String.valueOf(price)));
			Battle.addLogs(new ScreenChange(ScreenEnum.融合解除, ""));
			battle.setFusionPrice(0);
			battle.setFusionMode(false);
			map.put("itemList", makeShopItem());
			map.put("weaponList", makeShopWeapon());
			map.put("serviceList", makeShopService());
			map.put("playerStatus", makeList());
			return map;
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.お金が足りない, ""));
			return map;
		}
	}

	public void cancelFusion() {
		if (!battle.isFusionMode()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return;
		}
		Player player = battle.getPlayer();
		int price = battle.getFusionPrice();
		player.plusGold(price);
		battle.getPlayer().plusPriceMulti(1 / 1.5);
		Battle.addLogs(new ScreenChange(ScreenEnum.お金ゲット, String.valueOf(price)));
		Battle.addLogs(new ScreenChange(ScreenEnum.融合解除, ""));
		battle.setFusionMode(false);
		battle.setFusionPrice(0);
	}

	public boolean buyService(int index) {
		if (!battle.isBetweenShop()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return false;
		}
		Player player = battle.getPlayer();
		ShopService sService = battle.getDungeon().getServices().get(index);
		ServiceItem service = CreateService.INSTANCE.create(sService.getId());
		int price = (int) (sService.getPrice() * battle.getPlayer().getPriceMulti());
		if (price <= player.getGold()) {
			service.buy(battle, price);
			return true;
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.お金が足りない, ""));
			return false;
		}
	}

	public List<String> makeList() {
		List<String> list = new ArrayList<>();
		List<ScreenChange> logs = Battle.getLogs();
		for (int i = 0, l = logs.size(); i < l; i++) {
			ScreenChange sc = logs.get(i);
			list.add(sc.getScreenEnum().getLogName() + "$" + sc.getStatus());
		}
		Battle.clearLogs();
		return list;
	}

	public List<String> makeSubPageInfo(int clickPoint, int selectedIndex) {
		battle.debugPages("makeSubPageInfo");
		List<String> info = new ArrayList<>();
		int pageId = 0;
		int lv = 0;
		BookPage page = battle.getLastPage();
		switch (page.getPageName()) {
		case "myWeapon":
			int weaponId = battle.getPlayer().getWeapons().get(0).getId() - 1;
			int subSize;
			if (Objects.isNull(pictureBook.getWeaponPages().get(weaponId).getSubPages())) {
				subSize = 0;
			} else {
				subSize = pictureBook.getWeaponPages().get(weaponId).getSubPages().size();
			}
			if (clickPoint < subSize) {
				pageId = weaponId;
			} else {
				pageId = battle.getPlayer().getWeapons().get(1).getId() - 1;
				clickPoint -= subSize;
			}
			page = pictureBook.getWeaponPages().get(pageId);
			break;
		case "conditionConfirm":
			Condition condition;
			if (selectedIndex == 0) {
				condition = battle.getPlayer().getConditions().get(clickPoint);
				pageId = condition.getId() - 1;
			} else {
				condition = battle.getMonsters().get(selectedIndex - 1).getConditions().get(clickPoint);
				pageId = condition.getId() - 1;
			}
			lv = condition.getLv();
			// page = pictureBook.getConditionPages().get(pageId);
			break;
		}

		BookPage subBookPage;
		if (page.getPageName().equals("conditionConfirm")) {
			subBookPage = pictureBook.getConditionPages().get(pageId);
		} else {
			subBookPage = page.getSubPages().get(clickPoint);
		}
		if (0 < lv) {
			subBookPage.replacePageN(String.valueOf(lv));
		}
		String superPageN = page.getPageN();

		if (subBookPage instanceof ItemPage) {
			ItemPage itemPage = new ItemPage((ItemPage) subBookPage);
			replaceN(superPageN, clickPoint, itemPage);
			makeItemInfo(info, itemPage);
		} else if (subBookPage instanceof WeaponPage) {
			WeaponPage weaponPage = new WeaponPage((WeaponPage) subBookPage);
			replaceN(superPageN, clickPoint, weaponPage);
			makeWeaponInfo(info, weaponPage);
		} else if (subBookPage instanceof ActionPage) {
			ActionPage actionPage = new ActionPage((ActionPage) subBookPage);
			replaceN(superPageN, clickPoint, actionPage);
			makeActionInfo(info, actionPage);
		} else if (subBookPage instanceof ConditionPage) {
			ConditionPage conditionPage = new ConditionPage((ConditionPage) subBookPage);
			replaceN(superPageN, clickPoint, conditionPage);
			makeConditionInfo(info, conditionPage);
		} else {
			WeaponPage weaponPage = new WeaponPage((WeaponPage) subBookPage);
			replaceN(superPageN, clickPoint, weaponPage);
			makeWeaponInfo(info, weaponPage);
		}

		return info;
	}

	private void replaceN(String superPageN, int clickPoint, BookPage subBookPage) {
		if (Objects.nonNull(superPageN)) {
			String[] arrays2 = superPageN.split("/");
			if (clickPoint == Integer.parseInt(arrays2[0])) {
				subBookPage.setPageN(subBookPage.getNText().split("/")[0] + "/" + arrays2[1]);
			}
		}
	}

	public void backPage() {
		battle.debugPages("backPage");
		battle.removeLastPage();
	}

	public boolean closePage() {
		battle.debugPages("closePage");
		if (battle.isBetweenTurn()) {
			battle.falseCommandClickFlags();
			battle.setItemClickFlag(false);
		} else {
			battle.resetCommandClickFlags();
			battle.setItemClickFlag(true);
		}

		battle.getPages().clear();
		return !battle.isBetweenTurn();
	}

	private void makeItemInfo(List<String> info, ItemPage itemPage) {
		info.add("item/" + itemPage.getId());
		info.add(setNameN(itemPage.getName(), itemPage));// "<div id=\"itemName\">"+
		info.add(String.valueOf(itemPage.getPrice()));// "<div id=\"itemPrice\">"
		makeInfoText(info, itemPage);
		battle.pageAdd(itemPage);
	}

	private void makeWeaponInfo(List<String> info, WeaponPage weaponPage) {
		info.add("weapon/" + weaponPage.getId());
		info.add(setNameN(weaponPage.getName(), weaponPage));
		info.add(String.valueOf(weaponPage.getAttack()));
		info.add(String.valueOf(weaponPage.getPrice()));
		makeInfoText(info, weaponPage);
		battle.pageAdd(weaponPage);
	}

	private void makeActionInfo(List<String> info, ActionPage actionPage) {
		info.add("action/" + actionPage.getId());
		info.add(setNameN(actionPage.getName(), actionPage));
		if (actionPage.isAttackIs()) {
			info.add("攻撃");
		} else {
			info.add("特殊");
		}
		makeInfoText(info, actionPage);
		battle.pageAdd(actionPage);
	}

	private void makeMonsterInfo(List<String> info, MonsterPage monsterPage) {
		info.add("monster/" + monsterPage.getId());
		info.add(setNameN(monsterPage.getName(), monsterPage));
		info.add(String.valueOf(monsterPage.getHp()));
		info.add(String.valueOf(monsterPage.getOverHp()));
		info.add(String.valueOf(monsterPage.getAttack()));
		info.add(String.valueOf(monsterPage.getExp()));
		info.add(String.valueOf(monsterPage.getGold()));
		info.add(String.valueOf(monsterPage.getTurn()));
		makeInfoText(info, monsterPage);
		battle.pageAdd(monsterPage);
	}

	private void makeConditionInfo(List<String> info, ConditionPage conditionPage) {
		info.add("condition/" + conditionPage.getId());
		info.add(setNameN(conditionPage.getName(), conditionPage));
		if (conditionPage.isDuplication()) {
			info.add("可");
		} else {
			info.add("不可");
		}
		makeInfoText(info, conditionPage);
		battle.pageAdd(conditionPage);
	}

	private void makeInfoText(List<String> info, BookPage bookPage) {
		List<String> texts = bookPage.getTexts();
		for (int i = 0, l = texts.size(); i < l; i++) {
			String text = texts.get(i);
			if (text.equals("")) {
				info.add("");
				return;
			}
			String regex = "<[^>]+>";
			if (text.contains("<n>")) {
				String[] arrays = bookPage.getPageN().split("/");
				text = text.replace("<n>", arrays[1]);
			}

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				String matched = matcher.group(); // 一致した文字列
				String matchedResult = matched.substring(1, matched.length() - 1);
				info.add(matchedResult + "/infoLink");
			} else {
				String[] arrays = text.split("　");
				for (int j = 0, s = arrays.length - 1; j <= s; j++) {
					char lastChar = text.charAt(text.length() - 1);
					if (j == s && lastChar != '　') {
						info.add(arrays[j]);
					} else {
						info.add(arrays[j] + "/br");
					}
				}
			}
		}
	}

	private String setNameN(String text, BookPage bookPage) {
		if (Objects.nonNull(bookPage.getPageN())) {
			text = text.replace("<n>", bookPage.getNText().split("/")[1]);
		}
		return text;
	}

	public List<String> makePlayerItemInfo(int i) {
		if (battle.getPages().size() != 0) {
			closePage();
		}
		battle.debugPages("makePlayerItemInfo");
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		List<String> info = new ArrayList<>();
		int id = battle.getPlayer().getItems().get(i).getId();
		makeItemInfo(info, pictureBook.getItemPages().get(id - 1));
		return info;
	}

	public List<String> makeShopItemInfo(int i) {
		if (battle.getPages().size() != 0) {
			closePage();
		}
		battle.debugPages("makeShopItemInfo");
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		List<String> info = new ArrayList<>();
		int id = battle.getDungeon().getShowItems().get(i).getId();
		makeItemInfo(info, pictureBook.getItemPages().get(id - 1));
		return info;
	}

	public Map<String, List<String>> makeConditionConfirm() {
		if (battle.getPages().size() != 0) {
			closePage();
		}
		battle.debugPages("makeConditionConfirm");
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		Map<String, List<String>> map = new HashMap<>();

		List<String> playerConditions = new ArrayList<>();
		for (Condition c : battle.getPlayer().getConditions()) {
			playerConditions.add(c.getName());
			playerConditions.add(String.valueOf(c.getAmount()) + "/" + c.duplicationIs());
			playerConditions.add(getInfinity(c.getTurn()));
		}
		map.put("playerConditions", playerConditions);

		List<Monster> monsters = battle.getMonsters();
		for (int i = 0, l = monsters.size(); i < l; i++) {
			List<String> monsterConditions = new ArrayList<>();
			Monster monster = monsters.get(i);
			monsterConditions.add(monster.getName());
			for (Condition c : monster.getConditions()) {
				monsterConditions.add(c.getName());
				monsterConditions.add(String.valueOf(c.getAmount()) + "/" + c.duplicationIs());
				monsterConditions.add(getInfinity(c.getTurn()));
			}
			map.put("monsterConditions" + i, monsterConditions);
		}

		battle.pageAdd(new ConditionListPage());
		return map;
	}

	public List<String> makeMyWeaponInfo() {
		if (battle.getPages().size() != 0) {
			closePage();
		}
		battle.debugPages("makeMyWeaponInfo");
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		List<String> info = new ArrayList<>();
		for (int i = 0, l = battle.getPlayer().getWeapons().size(); i < l; i++) {
			info.add("weapon" + i);
			int id = battle.getPlayer().getWeapons().get(i).getId();
			myWeaponInfo(info, pictureBook.getWeaponPages().get(id - 1));
		}
		battle.pageAdd(new MyWeaponPage());
		return info;
	}

	private void myWeaponInfo(List<String> info, WeaponPage weaponPage) {
		info.add(setNameN(weaponPage.getName(), weaponPage));
		info.add(String.valueOf(weaponPage.getAttack()));
		makeInfoText(info, weaponPage);

	}

	public List<String> makeShopWeaponInfo(int i) {
		if (battle.getPages().size() != 0) {
			closePage();
		}
		battle.debugPages("makeShopWeaponInfo");
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		List<String> info = new ArrayList<>();
		int id = battle.getDungeon().getWeapons().get(i).getId();
		makeWeaponInfo(info, pictureBook.getWeaponPages().get(id - 1));
		return info;
	}

	public List<String> makeBattleMonsterInfo(int index) {
		if (battle.getPages().size() != 0) {
			closePage();
		}
		battle.debugPages("makeBattleMonsterInfo");
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		List<String> info = new ArrayList<>();
		int id = battle.getMonsters().get(index).getId();
		makeMonsterInfo(info, new MonsterPage(pictureBook.getMonsterPages().get(id - 1)));
		return info;
	}

	public String getInfinity(int n) {
		if (n == -1 || n == 0) {
			return "∞";
		} else {
			return String.valueOf(n);
		}
	}

	public void rightSwitchItem() {
		battle.rightSwitchItem();
	}

	public void leftSwitchItem() {
		battle.leftSwitchItem();
	}

	public void getDebugData() {
		StringBuilder sb = new StringBuilder();
		for (int j = 0, l = difficulties.size(); j < l; j++) {
			Difficulty difficulty = difficulties.get(j);
			String difficultySql = difficulty.getPhysicalName();
			String difficultyName = difficulty.getLogicalName();
			// List<Integer> shopFloors = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM
			// " + difficultySql + "_monster",
			// ArrayList<>());
			int monsters = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + difficultySql + "_monster",
					Integer.class);
			for (int i = 1; i <= monsters; i++) {
				sb.append(difficultyName + i + "階");
				// if()
				if (i < monsters) {
					sb.append("#");
				}
			}
			if (j < l) {
				sb.append("%");
			}
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.デバッグダンジョン, sb.toString()));
		// jdbcTemplate
	}

	public void debugInit() {
		battle.setDungeon(CreateDungeon.INSTANCE.create(0));
		Player player = battle.getPlayer();
		player.setLv(1);
		player.resetStatus();
		player.zeroDebugPrice();
		// String difficultySql = "all";
		List<ShopItem> itemInfo = new ArrayList<>();
		List<ShopWeapon> weaponInfo = new ArrayList<>();
		List<ShopService> serviceInfo = new ArrayList<>();
		int items = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM items", Integer.class);
		int weapons = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM weapons", Integer.class);
		int services = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM services", Integer.class);
		for (int i = 1; i <= items; i++) {
			int itemId = i;
			// = jdbcTemplate.queryForObject(
			// "SELECT item_id FROM " + difficultySql + "_item WHERE id = ?", Integer.class,
			// i);
			int itemStock = 0;
			// jdbcTemplate.queryForObject(
			// "SELECT stock FROM " + difficultySql + "_item WHERE id = ?", Integer.class,
			// i);
			ItemPage ip = pictureBook.getItemPages().get(itemId - 1);
			ShopItem shopItem = new ShopItem(i, ip.getId(), ip.getName(), ip.getFurigana(), ip.getPrice(), itemStock);
			itemInfo.add(shopItem);
		}

		for (int i = 1; i <= weapons; i++) {
			int weaponId = i;
			/*
			 * jdbcTemplate.queryForObject( "SELECT weapon_id FROM " + difficultySql +
			 * "_weapon WHERE id = ?", Integer.class, i);
			 */
			int weaponStock = 0;
			/*
			 * jdbcTemplate.queryForObject( "SELECT stock FROM " + difficultySql +
			 * "_weapon WHERE id = ?", Integer.class, i);
			 */
			WeaponPage wp = pictureBook.getWeaponPages().get(weaponId - 1);
			ShopWeapon shopWeapon = new ShopWeapon(i, wp.getId(), wp.getName(), wp.getAttack(), wp.getFurigana(),
					wp.getPrice(), weaponStock);
			weaponInfo.add(shopWeapon);
		}

		for (int i = 1; i <= services; i++) {
			int serviceId = i;
			/*
			 * jdbcTemplate.queryForObject( "SELECT service_id FROM " + difficultySql +
			 * "_service WHERE id = ?", Integer.class, i);
			 */
			int serviceStock = 0;
			/*
			 * jdbcTemplate.queryForObject( "SELECT stock FROM " + difficultySql +
			 * "_service WHERE id = ?", Integer.class, i);
			 */

			ServicePage sp = pictureBook.getServicePages().get(serviceId - 1);
			ShopService shopService = new ShopService(i, sp.getId(), sp.getName(), sp.getPrice(), serviceStock);
			serviceInfo.add(shopService);
		}
		battle.getDungeon().setStatus(null, null, weaponInfo, itemInfo, serviceInfo, null);
		debugPlayer();
	}

	public void debugPlayer() {
		Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
		Battle.addLogs(new ScreenChange(ScreenEnum.デバッグプレイヤー, ""));
		Player player = battle.getPlayer();
		battle.setBetweenTurn(true);
		battle.falseCommandClickFlags();
		player.showItem();
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器, player.getWeapons().get(0).getName()));
		if (1 < battle.getPlayer().getWeapons().size()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーサブ武器, player.getWeapons().get(1).getName()));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーサブ武器, ""));
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー強攻撃, String.valueOf(player.getCritical())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーテンション, String.valueOf(player.getTension())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー経験値, String.valueOf(player.getEXP())));
		// Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー合計経験値,
		// String.valueOf(sumExp)));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーお金, String.valueOf(player.getGold())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーブースト, String.valueOf(player.getBoost())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーHP, String.valueOf(player.getHP())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー最大HP, String.valueOf(player.getMAXHP())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー最大HP, String.valueOf(player.getMAXHP())));
	}

	public void goDebugFloor(int floor, int difficulty) {
		Battle.addLogs(new ScreenChange(ScreenEnum.デバッグエンド, ""));
		Battle.addLogs(new ScreenChange(ScreenEnum.ダンジョンスタート, ""));
		System.out.println(difficulty + 1);
		setDifficulty(difficulty + 1);
		battle.setDungeon(CreateDungeon.INSTANCE.create(difficulty + 1));
		sql.goFirstFloor(jdbcTemplate, battle.getDifficulty(), battle.getDungeon(), pictureBook);
		battle.setSumTurn(1);
		battle.setFloor(floor + 1);
		battle.getDungeon().removeMonster(floor);
		battle.getDungeon().goDungeon(battle.getPlayer());
		String attack = "攻撃力と同じダメージを与える";
		String weekAttack = "攻撃力-1のダメージを与える(気合の影響を受けない)";
		String criticalAttack = "攻撃力の1.5倍のダメージを与える";
		String defence = "このターン、受けるダメージを0にし、HPを20%回復する";
		String tameru = "気合が25%上昇する。攻撃のダメージが気合%分上昇する";
		String boost = "ブーストゲージが100%のとき、コマンドを切り替える";
		String commandText = attack + "#" + weekAttack + "#" + criticalAttack + "#" + defence + "#" + tameru + "#"
				+ boost;
		Battle.addLogs(new ScreenChange(ScreenEnum.コマンドテキスト, commandText));
		while (true) {
			if (battle.getDungeon().getShopFloor() == 0) {
				break;
			} else if (battle.getDungeon().getShopFloor() <= floor) {
				battle.getDungeon().floorRemove();
			} else {
				break;
			}
		}
		battle.getPlayer().resetDebugPrice();
		battle.battleStart();
	}

	public List<Difficulty> getDifficulties() {
		return difficulties;
	}

	public void changePictureBook(String tableName, int columnId, int id, String after) {
		switch (tableName) {
		case "monsters":
			if (id < pictureBook.getMonsterPages().size()) {
				pictureBook.getMonsterPages().get(id).setParam(columnId, after);
			}
			break;
		case "actions":
			if (id < pictureBook.getActionPages().size()) {
				pictureBook.getActionPages().get(id).setParam(columnId, after);
			}
			break;
		case "conditions":
			if (id < pictureBook.getConditionPages().size()) {
				pictureBook.getConditionPages().get(id).setParam(columnId, after);
			}
			break;
		case "itemPages":
			if (id < pictureBook.getItemPages().size()) {
				pictureBook.getItemPages().get(id).setParam(columnId, after);
			}
			break;
		case "weaponPages":
			if (id < pictureBook.getWeaponPages().size()) {
				pictureBook.getWeaponPages().get(id).setParam(columnId, after);
			}
			break;
		case "servicePages":
			if (id < pictureBook.getServicePages().size()) {
				pictureBook.getServicePages().get(id).setParam(columnId, after);
			}
			break;
		}
	}

}