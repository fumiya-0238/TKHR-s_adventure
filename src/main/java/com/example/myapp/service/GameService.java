package com.example.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.creater.CreateMonster;
import com.example.myapp.creater.CreateService;
import com.example.myapp.creater.CreateWeapon;
import com.example.myapp.model.Difficulty;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.services.ServiceItem;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.Dungeon;
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
	private int speed;

	public GameService(JdbcTemplate jdbcTemplate) {
		this.battle = new Battle();
		this.jdbcTemplate = jdbcTemplate;
	}

	public void init() {
		
		difficulties = new ArrayList<>();
		pictureBook = new PictureBook();
		sql = new SQLGetter();
		sql.init(difficulties, jdbcTemplate, pictureBook);
		sql.relation(jdbcTemplate, pictureBook);
	}

	public void setDifficulty(int difficulty) {
		//if (Objects.isNull(battle)) {
		//	init();
		//}
		battle.setDifficulty(difficulties.get(difficulty - 1));

	}

	public void goFirstFloor(int difficulty) {
		Battle.addLogs(new ScreenChange(ScreenEnum.ダンジョンスタート, ""));
		setDifficulty(difficulty);
		sql.goFirstFloor(jdbcTemplate, battle.getDifficulty(), battle.getDungeon(), pictureBook);
		battle.goFirstFloor();
	}

	public List<String> nextFloor() {
		if (!battle.isGoNextFloor()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.リターン, ""));
			return makeList();
		}
		battle.setGoNextFloor(false);
		battle.setBetweenShop(false);
		Iterator<Condition> iterator = battle.getPlayer().getConditions().iterator();
		while (iterator.hasNext()) {
			Condition condition = iterator.next();
			if (!condition.nonDeleteIs()) {
				iterator.remove();
			}
		}
		battle.setFloor(battle.getFloor() + 1);
		battle.setMonster(CreateMonster.INSTANCE.create(battle.getDungeon().getMonsters().get(battle.getFloor() - 1)));
		battle.battleStart();
		return makeList();
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

	public void resetCommandClickFlags() {
		battle.resetCommandClickFlags();
		battle.setItemClickFlag(true);
		battle.setBetweenTurn(false);
	}

	public String getMonsterImage() {
		return ImagesRepository.INSTANCE.getMonsterImage(battle.getMonster().getId(), 205);
	}

	public List<String> getBattleLog() {
		return battle.getMessages();
	}

	public Map<String, List<String>> makeShop() {
		battle.setBetweenShop(true);
		Map<String, List<String>> map = new HashMap<>();
		map.put("shopItem", makeShopItem());
		map.put("shopWeapon", makeShopWeapon());
		map.put("shopService", makeShopService());
		return map;
	}

	public List<String> makeShopItem() {
		List<String> list = new ArrayList<>();
		Dungeon dungeon = battle.getDungeon();
		for (ShopItem si : dungeon.getItems()) {
			list.add(si.getName());
			list.add(String.valueOf((int) (si.getPrice() * battle.getPlayer().getPriceMulti())));
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
		ShopItem sItem = battle.getDungeon().getItems().get(index);
		Item item = CreateItem.INSTANCE.create(sItem.getId());
		int price = (int) (sItem.getPrice() * battle.getPlayer().getPriceMulti());
		if (price <= player.getGold() && player.getItems().size() < player.getMaxItem()) {
			player.setItem(item);
			player.plusGold(-price);
			if (sItem.plusStock(-1)) {
				battle.getDungeon().getItems().remove(index);
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
			list.add(sc.getScreenEnum().getLogName() + "/" + sc.getStatus());
		}
		Battle.clearLogs();
		return list;
	}

	public List<String> makeSubPageInfo(int clickPoint, boolean conditionToggle) {
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
			if (conditionToggle) {
				condition = battle.getPlayer().getConditions().get(clickPoint);
				pageId = condition.getId() - 1;
			} else {
				condition = battle.getMonster().getConditions().get(clickPoint);
				pageId = condition.getId() - 1;
			}
			lv = condition.getLV();
			//page = pictureBook.getConditionPages().get(pageId);
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
		battle.removeLastPage();
	}

	public boolean closePage() {
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
		info.add(setNameN(itemPage.getName(), itemPage));//"<div id=\"itemName\">"+ 
		info.add(String.valueOf(itemPage.getPrice()));//"<div id=\"itemPrice\">"
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

	/*private String setTextN(String text, BookPage bookPage) {
		String s;
		bookPage.getPageN().contains("<n>");
		String[] arrays = s.split("/");
		text = text.replace("<n>", arrays[1]);
		return text;
	}*/

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
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		List<String> info = new ArrayList<>();
		int id = battle.getDungeon().getItems().get(i).getId();
		makeItemInfo(info, pictureBook.getItemPages().get(id - 1));
		return info;
	}

	public Map<String, List<String>> makeConditionMap() {
		if (battle.getPages().size() != 0) {
			closePage();
		}
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

		List<String> monsterConditions = new ArrayList<>();
		for (Condition c : battle.getMonster().getConditions()) {
			monsterConditions.add(c.getName());
			monsterConditions.add(String.valueOf(c.getAmount()) + "/" + c.duplicationIs());
			monsterConditions.add(getInfinity(c.getTurn()));
		}
		map.put("monsterConditions", monsterConditions);

		battle.pageAdd(new ConditionListPage());
		return map;
	}

	public List<String> makeMyWeaponInfo() {
		if (battle.getPages().size() != 0) {
			closePage();
		}
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
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		List<String> info = new ArrayList<>();
		int id = battle.getDungeon().getWeapons().get(i).getId();
		makeWeaponInfo(info, pictureBook.getWeaponPages().get(id - 1));
		return info;
	}

	public List<String> makeBattleMonsterInfo() {
		if (battle.getPages().size() != 0) {
			closePage();
		}
		battle.falseCommandClickFlags();
		battle.setItemClickFlag(false);
		List<String> info = new ArrayList<>();
		int id = battle.getMonster().getId();
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

	public void getDebugData() {
		StringBuilder sb = new StringBuilder();
		for (int j = 0, l = difficulties.size(); j < l; j++) {
			Difficulty difficulty = difficulties.get(j);
			String difficultySql = difficulty.getPhysicalName();
			String difficultyName = difficulty.getLogicalName();
			int monsters = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + difficultySql + "_monster",
					Integer.class);
			for (int i = 1; i <= monsters; i++) {
				sb.append(difficultyName + i + "階");
				if (i < monsters) {
					sb.append("#");
				}
			}
			if (j < l) {
				sb.append("%");
			}
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.デバッグルーム, sb.toString()));
		//jdbcTemplate
	}
}