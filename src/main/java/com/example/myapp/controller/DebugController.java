package com.example.myapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.repository.debug.ActionData;
import com.example.myapp.repository.debug.ConditionData;
import com.example.myapp.repository.debug.DungeonMonsterData;
import com.example.myapp.repository.debug.ItemData;
import com.example.myapp.repository.debug.MonsterData;
import com.example.myapp.repository.debug.ServiceData;
import com.example.myapp.repository.debug.ShopFloorData;
import com.example.myapp.repository.debug.ShopItemData;
import com.example.myapp.repository.debug.ShopServiceData;
import com.example.myapp.repository.debug.ShopWeaponData;
import com.example.myapp.repository.debug.TKHRData;
import com.example.myapp.repository.debug.WeaponData;

@RestController
public class DebugController {
	private final JdbcTemplate jdbcTemplate;
	private final String[] tableName = {
			"monsters", "actions", "conditions", "items", "weapons", "services",
			"beginner_monster", "beginner_shopfloor", "beginner_item", "beginner_weapon", "beginner_service",
			"novice_monster", "novice_shopfloor", "novice_item", "novice_weapon", "novice_service",
			"expert_monster", "expert_shopfloor", "expert_item", "expert_weapon", "expert_service",
			"shortage_monster", "shortage_shopfloor", "shortage_item", "shortage_weapon", "shortage_service",
			"inflation_monster", "inflation_shopfloor", "inflation_item", "inflation_weapon", "inflation_service",
			"convenience_monster", "convenience_shopfloor", "convenience_item", "convenience_weapon",
			"convenience_service"
	};
	private final String[][] columnName = {
			{ "id", "name", "hp", "over_hp", "attack", "exp", "gold", "turn", "text" },
			{ "id", "name", "attack_is", "text" },
			{ "id", "name", "duplication", "text" },
			{ "id", "name", "furigana", "price", "text" },
			{ "id", "name", "furigana", "price", "attack", "text" },
			{ "id", "name", "price", "text" },
			{ "id", "monster_id", "monster_lv" },
			{ "id", "floor" },
			{ "id", "item_id", "stock" },
			{ "id", "weapon_id", "stock" },
			{ "id", "service_id", "stock" },
			{ "id", "monster_id", "monster_lv" },
			{ "id", "floor" },
			{ "id", "item_id", "stock" },
			{ "id", "weapon_id", "stock" },
			{ "id", "service_id", "stock" },
			{ "id", "monster_id", "monster_lv" },
			{ "id", "floor" },
			{ "id", "item_id", "stock" },
			{ "id", "weapon_id", "stock" },
			{ "id", "service_id", "stock" },
			{ "id", "monster_id", "monster_lv" },
			{ "id", "floor" },
			{ "id", "item_id", "stock" },
			{ "id", "weapon_id", "stock" },
			{ "id", "service_id", "stock" },
			{ "id", "monster_id", "monster_lv" },
			{ "id", "floor" },
			{ "id", "item_id", "stock" },
			{ "id", "weapon_id", "stock" },
			{ "id", "service_id", "stock" },
			{ "id", "monster_id", "monster_lv" },
			{ "id", "floor" },
			{ "id", "item_id", "stock" },
			{ "id", "weapon_id", "stock" },
			{ "id", "service_id", "stock" }
	};
	private final Map<String, String> columnType = new HashMap<>();
	{
		columnType.putAll(Map.of(
				"id", "INTEGER",
				"name", "VARCHAR",
				"hp", "INTEGER",
				"over_hp", "INTEGER",
				"attack", "INTEGER",
				"exp", "INTEGER",
				"gold", "INTEGER",
				"turn", "INTEGER",
				"text", "VARCHAR",
				"attack_is", "BOOLEAN"));
		columnType.putAll(Map.of(
				"duplication", "BOOLEAN",
				"furigana", "VARCHAR",
				"price", "INTEGER",
				"monster_id", "INTEGER",
				"item_id", "INTEGER",
				"stock", "INTEGER",
				"weapon_id", "INTEGER",
				"service_id", "INTEGER",
				"monster_lv", "INTEGER",
				"floor", "INTEGER"));
	}

	/*
	{
	    columnType.putAll(Map.of(
	        "id", Integer.class,
	        "name", String.class,
	        "hp", Integer.class,
	        "over_hp", Integer.class,
	        "attack", Integer.class,
	        "exp", Integer.class,
	        "gold", Integer.class,
	        "turn", Integer.class,
	        "text", String.class,
	        "attack_is", Boolean.class
	    ));
	    columnType.putAll(Map.of(
	        "duplication", Boolean.class,
	        "furigana", String.class,
	        "price", Integer.class,
	        "monster_id", Integer.class,
	        "item_id", Integer.class,
	        "stock", Integer.class,
	        "weapon_id", Integer.class,
	        "service_id", Integer.class
	    ));
	}
	*/
	@Autowired
	public DebugController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping("/getDataBase")
	public Map<String, List<TKHRData>> getDataBase() {
		Map<String, List<TKHRData>> map = new HashMap<>();
		List<TKHRData> md = jdbcTemplate.query("SELECT * FROM monsters",
				(rs, rowNum) -> new MonsterData(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("hp"),
						rs.getInt("over_hp"),
						rs.getInt("attack"),
						rs.getInt("exp"),
						rs.getInt("gold"),
						rs.getInt("turn"),
						rs.getString("text")));

		List<TKHRData> ad = jdbcTemplate.query("SELECT * FROM actions",
				(rs, rowNum) -> new ActionData(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getBoolean("attack_is"),
						rs.getString("text")));

		List<TKHRData> id = jdbcTemplate.query("SELECT * FROM items",
				(rs, rowNum) -> new ItemData(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("furigana"),
						rs.getInt("price"),
						rs.getString("text")));

		List<TKHRData> wd = jdbcTemplate.query("SELECT * FROM weapons",
				(rs, rowNum) -> new WeaponData(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("furigana"),
						rs.getInt("price"),
						rs.getInt("attack"),
						rs.getString("text")));

		List<TKHRData> sd = jdbcTemplate.query("SELECT * FROM services",
				(rs, rowNum) -> new ServiceData(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("text")));

		List<TKHRData> cd = jdbcTemplate.query("SELECT * FROM conditions",
				(rs, rowNum) -> new ConditionData(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getBoolean("duplication"),
						rs.getString("text")));

		List<TKHRData> beginnerItem = jdbcTemplate.query("SELECT * FROM beginner_item",
				(rs, rowNum) -> new ShopItemData(
						rs.getInt("id"),
						rs.getInt("item_id"),
						rs.getInt("stock")));

		List<TKHRData> noviceItem = jdbcTemplate.query("SELECT * FROM novice_item",
				(rs, rowNum) -> new ShopItemData(
						rs.getInt("id"),
						rs.getInt("item_id"),
						rs.getInt("stock")));

		List<TKHRData> expertItem = jdbcTemplate.query("SELECT * FROM expert_item",
				(rs, rowNum) -> new ShopItemData(
						rs.getInt("id"),
						rs.getInt("item_id"),
						rs.getInt("stock")));

		List<TKHRData> beginnerMonster = jdbcTemplate.query("SELECT * FROM beginner_monster",
				(rs, rowNum) -> new DungeonMonsterData(
						rs.getInt("id"),
						rs.getInt("monster_id"),
						rs.getInt("monster_lv")));

		List<TKHRData> noviceMonster = jdbcTemplate.query("SELECT * FROM novice_monster",
				(rs, rowNum) -> new DungeonMonsterData(
						rs.getInt("id"),
						rs.getInt("monster_id"),
						rs.getInt("monster_lv")));

		List<TKHRData> expertMonster = jdbcTemplate.query("SELECT * FROM expert_monster",
				(rs, rowNum) -> new DungeonMonsterData(
						rs.getInt("id"),
						rs.getInt("monster_id"),
						rs.getInt("monster_lv")));

		List<TKHRData> beginnerWeapon = jdbcTemplate.query("SELECT * FROM beginner_weapon",
				(rs, rowNum) -> new ShopWeaponData(
						rs.getInt("id"),
						rs.getInt("weapon_id"),
						rs.getInt("stock")));

		List<TKHRData> noviceWeapon = jdbcTemplate.query("SELECT * FROM novice_weapon",
				(rs, rowNum) -> new ShopWeaponData(
						rs.getInt("id"),
						rs.getInt("weapon_id"),
						rs.getInt("stock")));

		List<TKHRData> expertWeapon = jdbcTemplate.query("SELECT * FROM expert_weapon",
				(rs, rowNum) -> new ShopWeaponData(
						rs.getInt("id"),
						rs.getInt("weapon_id"),
						rs.getInt("stock")));

		List<TKHRData> beginnerService = jdbcTemplate.query("SELECT * FROM beginner_service",
				(rs, rowNum) -> new ShopServiceData(
						rs.getInt("id"),
						rs.getInt("service_id"),
						rs.getInt("stock")));

		List<TKHRData> noviceService = jdbcTemplate.query("SELECT * FROM novice_service",
				(rs, rowNum) -> new ShopServiceData(
						rs.getInt("id"),
						rs.getInt("service_id"),
						rs.getInt("stock")));

		List<TKHRData> expertService = jdbcTemplate.query("SELECT * FROM expert_service",
				(rs, rowNum) -> new ShopServiceData(
						rs.getInt("id"),
						rs.getInt("service_id"),
						rs.getInt("stock")));

		List<TKHRData> shortageMonster = jdbcTemplate.query("SELECT * FROM shortage_monster",
				(rs, rowNum) -> new DungeonMonsterData(
						rs.getInt("id"),
						rs.getInt("monster_id"),
						rs.getInt("monster_lv")));

		List<TKHRData> shortageItem = jdbcTemplate.query("SELECT * FROM shortage_item",
				(rs, rowNum) -> new ShopItemData(
						rs.getInt("id"),
						rs.getInt("item_id"),
						rs.getInt("stock")));

		List<TKHRData> shortageWeapon = jdbcTemplate.query("SELECT * FROM shortage_weapon",
				(rs, rowNum) -> new ShopWeaponData(
						rs.getInt("id"),
						rs.getInt("weapon_id"),
						rs.getInt("stock")));

		List<TKHRData> shortageService = jdbcTemplate.query("SELECT * FROM shortage_service",
				(rs, rowNum) -> new ShopServiceData(
						rs.getInt("id"),
						rs.getInt("service_id"),
						rs.getInt("stock")));
		List<TKHRData> beginnerShopFloor = jdbcTemplate.query("SELECT * FROM beginner_shopfloors",
				(rs, rowNum) -> new ShopFloorData(
						rs.getInt("id"),
						rs.getInt("floor")));

		List<TKHRData> noviceShopFloor = jdbcTemplate.query("SELECT * FROM novice_shopfloors",
				(rs, rowNum) -> new ShopFloorData(
						rs.getInt("id"),
						rs.getInt("floor")));

		List<TKHRData> expertShopFloor = jdbcTemplate.query("SELECT * FROM expert_shopfloors",
				(rs, rowNum) -> new ShopFloorData(
						rs.getInt("id"),
						rs.getInt("floor")));

		List<TKHRData> shortageShopFloor = jdbcTemplate.query("SELECT * FROM shortage_shopfloors",
				(rs, rowNum) -> new ShopFloorData(
						rs.getInt("id"),
						rs.getInt("floor")));

		map.put("monster", md);
		map.put("action", ad);
		map.put("condition", cd);
		map.put("weapon", wd);
		map.put("item", id);
		map.put("service", sd);
		map.put("beginnerMonster", beginnerMonster);
		map.put("noviceMonster", noviceMonster);
		map.put("expertMonster", expertMonster);
		map.put("beginnerItem", beginnerItem);
		map.put("noviceItem", noviceItem);
		map.put("expertItem", expertItem);
		map.put("beginnerWeapon", beginnerWeapon);
		map.put("noviceWeapon", noviceWeapon);
		map.put("expertWeapon", expertWeapon);
		map.put("beginnerService", beginnerService);
		map.put("noviceService", noviceService);
		map.put("expertService", expertService);
		map.put("expertMonster", expertMonster);
		map.put("shortageMonster", shortageMonster);
		map.put("shortageItem", shortageItem);
		map.put("shortageWeapon", shortageWeapon);
		map.put("shortageService", shortageService);
		map.put("shortageShopFloor", shortageShopFloor);
		map.put("beginnerShopFloor", beginnerShopFloor);
		map.put("noviceShopFloor", noviceShopFloor);
		map.put("expertShopFloor", expertShopFloor);
		return map;
	}

	@PostMapping("/updateDataBase")
	public void updateDataBase(@RequestBody Map<String, Object> request) {
		int id = (int) request.get("index");
		int where = (int) request.get("row");
		int name = (int) request.get("column");
		String value = (String) request.get("value");
		if (columnType.get(columnName[id][name]).equals("BOOLEAN")) {
			if (value.equals("true")) {
				value = "1";
			} else {
				value = "0";
			}
		}
		String sql = String.format("UPDATE %s SET %s = ? WHERE id = ?", tableName[id], columnName[id][name]);
		jdbcTemplate.update(sql, value, where + 1);
		//	System.out.println("UPDATE " + tableName[id] + " SET " + columnName[id][name] + " = " + value + " WHERE id = "
		//			+ (where + 1));
	}

	@PostMapping("/deleteRow")
	public void deleteRow(@RequestBody Map<String, Object> request) {
		int id = (int) request.get("index");
		int where = (int) request.get("row");
		String sql = String.format("DELETE FROM %s WHERE id = ?", tableName[id]);
		jdbcTemplate.update(sql, where + 1);
		sql = String.format("UPDATE %s SET id = id - 1 WHERE id > ?", tableName[id]);
		jdbcTemplate.update(sql, where + 1);
		//System.out.println("DELETE FROM " + tableName[id] + " WHERE id = " + (where + 1));
	}

	@PostMapping("/addRow")
	public void addRow(@RequestBody Map<String, Object> request) {
		int index = (int) request.get("index");
		int id = (int) request.get("id");
		StringBuilder sb = new StringBuilder();
		sb.append("'" + id + "'");
		for (int i = 1, l = columnName[index].length; i < l; i++) {
			sb.append(",NULL");
		}
		String sql = "INSERT INTO " + tableName[index] + " VALUES (" + sb.toString() + ")";
		jdbcTemplate.update(sql);
	}

	@PostMapping("/moveUpRow")
	@Transactional
	public void moveUpRow(@RequestBody Map<String, Object> request) {
		// 1. リクエストからidとtableIndexを取得
		int id = (int) request.get("rowIndex");
		int tableIndex = (int) request.get("activeIndex");

		// 2. テーブル名を取得
		String targetTable = tableName[tableIndex];

		// 3. ID以外のカラムを取得
		List<String> columns = new ArrayList<>();
		for (String col : columnName[tableIndex]) {
			if (!"id".equalsIgnoreCase(col)) {
				columns.add(col);
			}
		}

		// 4. UPDATEクエリ構築（MySQL向け）
		String setClauses = columns.stream()
				.map(c -> String.format("t1.%s = t2.%s, t2.%s = t1.%s", c, c, c, c))
				.collect(Collectors.joining(", "));
		String updateSql = String.format(
				"UPDATE %s t1 JOIN %s t2 ON t1.id = ? AND t2.id = ? SET %s",
				targetTable, targetTable, setClauses);

		jdbcTemplate.update(updateSql, id, id - 1);
	}

	@PostMapping("/moveDownRow")
	@Transactional
	public void moveDownRow(@RequestBody Map<String, Object> request) {
		// 1. リクエストからidとtableIndexを取得
		int id = (int) request.get("rowIndex");
		int tableIndex = (int) request.get("activeIndex");

		// 2. テーブル名を取得
		String targetTable = tableName[tableIndex];

		// 3. ID以外のカラムを取得
		List<String> columns = new ArrayList<>();
		for (String col : columnName[tableIndex]) {
			if (!"id".equalsIgnoreCase(col)) {
				columns.add(col);
			}
		}

		// 4. UPDATEクエリ構築（MySQL向け）
		String setClauses = columns.stream()
				.map(c -> String.format("t1.%s = t2.%s, t2.%s = t1.%s", c, c, c, c))
				.collect(Collectors.joining(", "));
		String updateSql = String.format(
				"UPDATE %s t1 JOIN %s t2 ON t1.id = ? AND t2.id = ? SET %s",
				targetTable, targetTable, setClauses);
		jdbcTemplate.update(updateSql, id, id + 1);
	}

	@PostMapping("/downloadSQL")
	public List<String> downloadSQL(@RequestBody String request) {
		int activeIndex = Integer.parseInt(request);
		// 2. テーブル名を取得
		List<String> sqlFile = new ArrayList<>();
		String table = tableName[activeIndex];
		sqlFile.add(table);
		sqlFile.add("DROP TABLE IF EXISTS " + table + ";");
		sqlFile.add("CREATE TABLE " + table + "(");
		sqlFile.add("id INTEGER PRIMARY KEY,");
		for (int i = 1, l = columnName[activeIndex].length; i < l; i++) {
			String column = columnName[activeIndex][i];
			String type = columnType.get(columnName[activeIndex][i]);
			if (type.equals("VARCHAR")) {
				String charLength = "SELECT CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
						+ table + "' AND COLUMN_NAME = '" + column + "'";
				int n = jdbcTemplate.queryForObject(charLength, Integer.class);
				if (i < l - 1) {
					sqlFile.add(column + " VARCHAR(" + n + "),");
				} else {
					sqlFile.add(column + " VARCHAR(" + n + ")");
				}
			} else {
				if (i < l - 1) {
				sqlFile.add(column + " " + type + ",");
				} else {
					sqlFile.add(column + " " + type);
				}
			}
		}
		sqlFile.add(");");
		sqlFile.add("INSERT INTO " + table + " VALUES");

		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM " + table);
		for (int j = 0, jl = rows.size(); j < jl; j++) {
			Map<String, Object> row = rows.get(j);
			Object value = row.values();
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			Collection<Object> values = (Collection<Object>) value;
			ArrayList<Object> valueList = new ArrayList<>(values);
			for (int i = 0; i < valueList.size(); i++) {
				Object item = valueList.get(i);
				if (columnType.get(columnName[activeIndex][i]).equals("VARCHAR")) {
					sb.append("'" + item + "'");
				} else {
					sb.append(item);
				}
				if (i < valueList.size() - 1) {
					sb.append(",");
				}
			}
			sb.append(")");
			if (j == jl - 1) {
				sb.append(";");
			} else {
				sb.append(",");
			}
			sqlFile.add(sb.toString());
		}
		return sqlFile;
	}
}
