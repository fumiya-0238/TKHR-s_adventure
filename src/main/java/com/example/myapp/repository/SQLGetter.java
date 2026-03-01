package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.creater.CreateCondition;
import com.example.myapp.creater.CreateItem;
import com.example.myapp.creater.CreateMonster;
import com.example.myapp.creater.CreateService;
import com.example.myapp.creater.CreateText;
import com.example.myapp.creater.CreateWeapon;
import com.example.myapp.model.Difficulty;
import com.example.myapp.model.dungeons.Dungeon;
import com.example.myapp.repository.book.ActionPage;
import com.example.myapp.repository.book.ConditionPage;
import com.example.myapp.repository.book.ItemPage;
import com.example.myapp.repository.book.MonsterPage;
import com.example.myapp.repository.book.PictureBook;
import com.example.myapp.repository.book.ServicePage;
import com.example.myapp.repository.book.WeaponPage;
import com.example.myapp.repository.shop.ShopItem;
import com.example.myapp.repository.shop.ShopService;
import com.example.myapp.repository.shop.ShopWeapon;

public class SQLGetter {
	public void init(List<Difficulty> difficulties, JdbcTemplate jdbcTemplate, PictureBook pictureBook) {
		List<MonsterPage> mp = new ArrayList<>();
		List<ActionPage> ap = new ArrayList<>();
		List<ConditionPage> cp = new ArrayList<>();
		List<ItemPage> ip = new ArrayList<>();
		List<WeaponPage> wp = new ArrayList<>();
		List<ServicePage> sp = new ArrayList<>();
		int monsters = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM monsters", Integer.class);
		int items = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM items", Integer.class);
		int weapons = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM weapons", Integer.class);
		int conditions = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM conditions", Integer.class);
		int actions = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM actions", Integer.class);
		int services = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM services", Integer.class);
		int dif = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM difficulties", Integer.class);

		for (int i = 1; i <= monsters; i++) {
			mp.add(jdbcTemplate.queryForObject("SELECT * FROM monsters WHERE id = ?",
					(rs, rowNum) -> {
						MonsterPage m = new MonsterPage(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getInt("hp"),
								rs.getInt("over_hp"),
								rs.getInt("attack"),
								rs.getInt("exp"),
								rs.getInt("gold"),
								rs.getInt("turn"));
						return m;
					}, i));
		}
		pictureBook.setMonsterPages(mp);

		for (int i = 1; i <= actions; i++) {
			ap.add(jdbcTemplate.queryForObject("SELECT * FROM actions WHERE id = ?",
					(rs, rowNum) -> {
						ActionPage m = new ActionPage(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getBoolean("attack_is"));
						return m;
					}, i));
		}
		pictureBook.setActionPages(ap);

		for (int i = 1; i <= items; i++) {
			ip.add(jdbcTemplate.queryForObject("SELECT * FROM items WHERE id = ?",
					(rs, rowNum) -> {
						ItemPage m = new ItemPage(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("furigana"),
								rs.getInt("price"));
						return m;
					}, i));
		}
		pictureBook.setItemPages(ip);

		for (int i = 1; i <= weapons; i++) {
			wp.add(jdbcTemplate.queryForObject("SELECT * FROM weapons WHERE id = ?",
					(rs, rowNum) -> {
						WeaponPage m = new WeaponPage(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("furigana"),
								rs.getInt("attack"),
								rs.getInt("price"));
						return m;
					}, i));
		}
		pictureBook.setWeaponPages(wp);

		for (int i = 1; i <= conditions; i++) {
			cp.add(jdbcTemplate.queryForObject("SELECT * FROM conditions WHERE id = ?",
					(rs, rowNum) -> {
						ConditionPage m = new ConditionPage(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getBoolean("duplication"));
						return m;
					}, i));
		}
		pictureBook.setConditionPages(cp);

		for (int i = 1; i <= services; i++) {
			sp.add(jdbcTemplate.queryForObject("SELECT * FROM services WHERE id = ?",
					(rs, rowNum) -> {
						ServicePage m = new ServicePage(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getInt("price"));
						return m;
					}, i));
		}
		pictureBook.setServicePages(sp);
		CreateMonster.INSTANCE.setPictureBook(pictureBook.getMonsterPages());
		CreateAction.INSTANCE.setPictureBook(pictureBook.getActionPages());
		CreateItem.INSTANCE.setPictureBook(pictureBook.getItemPages());
		CreateWeapon.INSTANCE.setPictureBook(pictureBook.getWeaponPages());
		CreateCondition.INSTANCE.setPictureBook(pictureBook.getConditionPages());
		CreateService.INSTANCE.setPictureBook(pictureBook.getServicePages());
		CreateText.INSTANCE.setPictureBook(pictureBook);
		
		for (int i = 1; i <= dif; i++) {
			difficulties.add(jdbcTemplate.queryForObject("SELECT * FROM difficulties WHERE id = ?",
					(rs, rowNum) -> {
						Difficulty m = new Difficulty(
								rs.getInt("id"),
								rs.getString("name_physical"),
								rs.getString("name_logical"),
								rs.getString("name_logical_short"),
								rs.getInt("start_lv"));
						return m;
					}, i));
		}

	}

	public void goFirstFloor(JdbcTemplate jdbcTemplate, Difficulty difficulty, Dungeon dungeon,
			PictureBook pictureBook) {
		String difficultySql = difficulty.getPhysicalName();
		List<Integer> monsterIds = new ArrayList<>();
		List<Integer> monsterLv = new ArrayList<>();
		List<ShopItem> itemInfo = new ArrayList<>();
		List<ShopWeapon> weaponInfo = new ArrayList<>();
		List<ShopService> serviceInfo = new ArrayList<>();
		List<Integer> shopFloors = new ArrayList<>();
		int monsters = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + difficultySql + "_monster",
				Integer.class);
		int items = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + difficultySql + "_item", Integer.class);
		int weapons = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + difficultySql + "_weapon", Integer.class);
		int services = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + difficultySql + "_service", Integer.class);
		int sf = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + difficultySql + "_shopfloors", Integer.class);

		for (int i = 1; i <= monsters; i++) {
			monsterIds.add(jdbcTemplate.queryForObject(
					"SELECT monster_id FROM " + difficultySql + "_monster WHERE id = ?", Integer.class, i));
		}

		for (int i = 1; i <= monsters; i++) {
			monsterLv.add(jdbcTemplate.queryForObject(
					"SELECT monster_lv FROM " + difficultySql + "_monster WHERE id = ?", Integer.class, i));
		}

		for (int i = 1; i <= items; i++) {
			int itemId = jdbcTemplate.queryForObject(
					"SELECT item_id FROM " + difficultySql + "_item WHERE id = ?", Integer.class, i);
			int itemStock = jdbcTemplate.queryForObject(
					"SELECT stock FROM " + difficultySql + "_item WHERE id = ?", Integer.class, i);
			ItemPage ip = pictureBook.getItemPages().get(itemId - 1);
			ShopItem shopItem = new ShopItem(i, ip.getId(), ip.getName(), ip.getFurigana(), ip.getPrice(), itemStock);
			itemInfo.add(shopItem);
		}

		for (int i = 1; i <= weapons; i++) {
			int weaponId = jdbcTemplate.queryForObject(
					"SELECT weapon_id FROM " + difficultySql + "_weapon WHERE id = ?", Integer.class, i);
			int weaponStock = jdbcTemplate.queryForObject(
					"SELECT stock FROM " + difficultySql + "_weapon WHERE id = ?", Integer.class, i);
			WeaponPage wp = pictureBook.getWeaponPages().get(weaponId - 1);
			ShopWeapon shopWeapon = new ShopWeapon(i, wp.getId(), wp.getName(), wp.getAttack(), wp.getFurigana(),
					wp.getPrice(), weaponStock);
			weaponInfo.add(shopWeapon);
		}

		for (int i = 1; i <= services; i++) {
			int serviceId = jdbcTemplate.queryForObject(
					"SELECT service_id FROM " + difficultySql + "_service WHERE id = ?", Integer.class, i);
			int serviceStock = jdbcTemplate.queryForObject(
					"SELECT stock FROM " + difficultySql + "_service WHERE id = ?", Integer.class, i);

			ServicePage sp = pictureBook.getServicePages().get(serviceId - 1);
			ShopService shopService = new ShopService(i, sp.getId(), sp.getName(), sp.getPrice(), serviceStock);
			serviceInfo.add(shopService);
		}

		for (int i = 1; i <= sf; i++) {
			int shopFloor = jdbcTemplate.queryForObject(
					"SELECT floor FROM " + difficultySql + "_shopfloors WHERE id=?", Integer.class, i);
			shopFloors.add(shopFloor);
		}

		dungeon.setStatus(monsterIds, monsterLv, weaponInfo, itemInfo, serviceInfo, shopFloors);
	}

	public void relation(JdbcTemplate jdbcTemplate, PictureBook pictureBook) {
		for (int i = 1, l = pictureBook.getItemPages().size(); i <= l; i++) {
			String text = jdbcTemplate.queryForObject("SELECT text FROM items WHERE id = ?", String.class, i);
			CreateText.INSTANCE.macherFind(pictureBook.getItemPages().get(i - 1), text);
		}
		for (int i = 1, l = pictureBook.getWeaponPages().size(); i <= l; i++) {
			String text = jdbcTemplate.queryForObject("SELECT text FROM weapons WHERE id = ?", String.class, i);
			CreateText.INSTANCE.macherFind(pictureBook.getWeaponPages().get(i - 1), text);
		}
		for (int i = 1, l = pictureBook.getConditionPages().size(); i <= l; i++) {
			String text = jdbcTemplate.queryForObject("SELECT text FROM conditions WHERE id = ?", String.class, i);
			CreateText.INSTANCE.macherFind(pictureBook.getConditionPages().get(i - 1), text);
		}
		for (int i = 1, l = pictureBook.getActionPages().size(); i <= l; i++) {
			String text = jdbcTemplate.queryForObject("SELECT text FROM actions WHERE id = ?", String.class, i);
			CreateText.INSTANCE.macherFind(pictureBook.getActionPages().get(i - 1), text);
		}
		for (int i = 1, l = pictureBook.getActionPages().size(); i <= l; i++) {
			String text = jdbcTemplate.queryForObject("SELECT text FROM monsters WHERE id = ?", String.class, i);
			CreateText.INSTANCE.macherFind(pictureBook.getMonsterPages().get(i - 1), text);
		}
	}

}
