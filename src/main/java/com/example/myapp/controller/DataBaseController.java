package com.example.myapp.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.example.myapp.model.Difficulty;
import com.example.myapp.repository.debug.ActionData;
import com.example.myapp.repository.debug.ChangeData;
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
import com.example.myapp.service.GameService;

@RestController
public class DataBaseController {
	private final GameService service;
	private final JdbcTemplate jdbcTemplate;
	private List<String> tableNames;
	private List<List<String>> columnNames;
	private final Map<String, String> columnTypes = new HashMap<>();
	{
		columnTypes.putAll(Map.of("id", "INTEGER", "name", "VARCHAR", "hp", "INTEGER", "over_hp", "INTEGER", "attack",
				"INTEGER", "exp", "INTEGER", "gold", "INTEGER", "turn", "INTEGER", "text", "VARCHAR", "attack_is",
				"BOOLEAN"));
		columnTypes.putAll(Map.of("duplication", "BOOLEAN", "furigana", "VARCHAR", "price", "INTEGER", "monster_id",
				"INTEGER", "item_id", "INTEGER", "stock", "INTEGER", "weapon_id", "INTEGER", "service_id", "INTEGER",
				"monster_lv", "INTEGER", "floor", "INTEGER"));
	}

	@Autowired
	public DataBaseController(GameService service, JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.service = service;
		tableNames = new ArrayList<>();
		tableNames.add("monsters");
		tableNames.add("actions");
		tableNames.add("conditions");
		tableNames.add("items");
		tableNames.add("weapons");
		tableNames.add("services");
		columnNames = new ArrayList<>();
		columnNames.add(Arrays.asList("id", "name", "hp", "over_hp", "attack", "exp", "gold", "turn", "text"));
		columnNames.add(Arrays.asList("id", "name", "attack_is", "text"));
		columnNames.add(Arrays.asList("id", "name", "duplication", "text"));
		columnNames.add(Arrays.asList("id", "name", "furigana", "price", "text"));
		columnNames.add(Arrays.asList("id", "name", "furigana", "price", "attack", "text"));
		columnNames.add(Arrays.asList("id", "name", "price", "text"));
	}

	@GetMapping("/getDataBase")
	public Map<String, List<TKHRData>> getDataBase() {
		Map<String, List<TKHRData>> map = new HashMap<>();
		List<Difficulty> difficulties = service.getDifficulties();

		for (int i = 0, l = difficulties.size(); i < l; i++) {
			String phyName = difficulties.get(i).getPhysicalName();
			tableNames.add(phyName + "_monster");
			tableNames.add(phyName + "_shopfloors");
			tableNames.add(phyName + "_item");
			tableNames.add(phyName + "_weapon");
			tableNames.add(phyName + "_service");
			columnNames.add(Arrays.asList("id", "monster_id", "monster_lv"));
			columnNames.add(Arrays.asList("id", "floor"));
			columnNames.add(Arrays.asList("id", "item_id", "stock"));
			columnNames.add(Arrays.asList("id", "weapon_id", "stock"));
			columnNames.add(Arrays.asList("id", "service_id", "stock"));
			List<TKHRData> monsters = jdbcTemplate.query("SELECT * FROM " + phyName + "_monster",
					(rs, rowNum) -> new DungeonMonsterData(rs.getInt("id"), rs.getInt("monster_id"),
							rs.getInt("monster_lv")));
			List<TKHRData> shopFloors = jdbcTemplate.query("SELECT * FROM " + phyName + "_shopfloors",
					(rs, rowNum) -> new ShopFloorData(rs.getInt("id"), rs.getInt("floor")));
			List<TKHRData> items = jdbcTemplate.query("SELECT * FROM " + phyName + "_item",
					(rs, rowNum) -> new ShopItemData(rs.getInt("id"), rs.getInt("item_id"), rs.getInt("stock")));

			List<TKHRData> weapons = jdbcTemplate.query("SELECT * FROM " + phyName + "_weapon",
					(rs, rowNum) -> new ShopWeaponData(rs.getInt("id"), rs.getInt("weapon_id"), rs.getInt("stock")));

			List<TKHRData> services = jdbcTemplate.query("SELECT * FROM " + phyName + "_service",
					(rs, rowNum) -> new ShopServiceData(rs.getInt("id"), rs.getInt("service_id"), rs.getInt("stock")));

			map.put(phyName + "Monster", monsters);
			map.put(phyName + "Item", items);
			map.put(phyName + "Weapon", weapons);
			map.put(phyName + "Service", services);
			map.put(phyName + "ShopFloor", shopFloors);
		}

		List<TKHRData> md = jdbcTemplate.query("SELECT * FROM monsters",
				(rs, rowNum) -> new MonsterData(rs.getInt("id"), rs.getString("name"), rs.getInt("hp"),
						rs.getInt("over_hp"), rs.getInt("attack"), rs.getInt("exp"), rs.getInt("gold"),
						rs.getInt("turn"), rs.getString("text")));

		List<TKHRData> ad = jdbcTemplate.query("SELECT * FROM actions", (rs, rowNum) -> new ActionData(rs.getInt("id"),
				rs.getString("name"), rs.getBoolean("attack_is"), rs.getString("text")));

		List<TKHRData> id = jdbcTemplate.query("SELECT * FROM items", (rs, rowNum) -> new ItemData(rs.getInt("id"),
				rs.getString("name"), rs.getString("furigana"), rs.getInt("price"), rs.getString("text")));

		List<TKHRData> wd = jdbcTemplate.query("SELECT * FROM weapons",
				(rs, rowNum) -> new WeaponData(rs.getInt("id"), rs.getString("name"), rs.getString("furigana"),
						rs.getInt("price"), rs.getInt("attack"), rs.getString("text")));

		List<TKHRData> sd = jdbcTemplate.query("SELECT * FROM services",
				(rs, rowNum) -> new ServiceData(rs.getInt("id"), rs.getString("name"), rs.getInt("price"),
						rs.getString("text")));

		List<TKHRData> cd = jdbcTemplate.query("SELECT * FROM conditions",
				(rs, rowNum) -> new ConditionData(rs.getInt("id"), rs.getString("name"), rs.getBoolean("duplication"),
						rs.getString("text")));

		map.put("monster", md);
		map.put("action", ad);
		map.put("condition", cd);
		map.put("weapon", wd);
		map.put("item", id);
		map.put("service", sd);
		return map;
	}

	@PostMapping("/updateDataBase")
	public ChangeData updateDataBase(@RequestBody Map<String, Object> request) {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM change_database", Integer.class);
		if (1000 <= count) {
			return new ChangeData("更新不可", "", "", "", "", "", "");
		}

		int tableNumber = (int) request.get("activeTable");
		int where = (int) request.get("row");
		int columnId = (int) request.get("column");
		String value = (String) request.get("value");
		String tableName = tableNames.get(tableNumber);
		String columnName = columnNames.get(tableNumber).get(columnId);
		String before = jdbcTemplate.queryForObject("SELECT " + columnName + " FROM " + tableName + " WHERE id = ?",
				String.class, where + 1);
		LocalDateTime createdAt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = createdAt.format(formatter);
		String changeSQL;
		String columnType = columnTypes.get(columnName);
		if (columnType.equals("INTEGER") && !value.matches("\\d+")) {
			String changeType = "更新失敗";
			changeSQL = "INSERT INTO change_database VALUES('" + changeType + "','" + tableName + "','" + (where + 1)
					+ "','" + columnName + "','" + before + "','" + value + "','" + createdAt + "')";
			jdbcTemplate.update(changeSQL);
			return new ChangeData(changeType, tableName, String.valueOf(where + 1), columnName, before, value,
					formatted);
		}

		if (columnType.equals("VARCHAR")) {
			String changeType = "更新失敗";
			String charLength = "SELECT CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
					+ tableName + "' AND COLUMN_NAME = '" + columnName + "'";
			int n = jdbcTemplate.queryForObject(charLength, Integer.class);
			if (n < value.length()) {
				changeSQL = "INSERT INTO change_database VALUES('" + changeType + "','" + tableName + "','"
						+ (where + 1) + "','" + columnName + "','" + before + "','文字数制限','" + createdAt + "')";
				return new ChangeData(changeType, tableName, String.valueOf(where + 1), columnName, before, "文字数制限",
						formatted);
			}
		}

		if (columnType.equals("BOOLEAN")) {
			if (value.equals("true")) {
				value = "1";
			} else {
				value = "0";
			}
		}

		String changeType = "更新";
		String sql = String.format("UPDATE %s SET %s = ? WHERE id = ?", tableName, columnName);
		jdbcTemplate.update(sql, value, where + 1);
		if (tableNumber < 6) {
			service.changePictureBook(tableName, columnId, where, value);
		}
		if (columnType.equals("BOOLEAN")) {
			if (value.equals("1")) {
				value = "true";
			} else {
				value = "false";
			}
		}
		changeSQL = "INSERT INTO change_database VALUES('" + changeType + "','" + tableName + "','" + (where + 1)
				+ "','" + columnName + "','" + before + "','" + value + "','" + createdAt + "')";
		jdbcTemplate.update(changeSQL);
		return new ChangeData(changeType, tableName, String.valueOf(where + 1), columnName, before, value, formatted);
	}

	@PostMapping("/deleteRow")
	public ChangeData deleteRow(@RequestBody Map<String, Object> request) {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM change_database", Integer.class);
		if (1000 <= count) {
			return new ChangeData("更新不可", "", "", "", "", "", "");
		}
		int tableNumber = (int) request.get("activeTable");
		int where = (int) request.get("row");
		String tableName = tableNames.get(tableNumber);
		String sql = String.format("DELETE FROM %s WHERE id = ?", tableName);
		jdbcTemplate.update(sql, where + 1);
		sql = String.format("UPDATE %s SET id = id - 1 WHERE id > ?", tableName);
		jdbcTemplate.update(sql, where + 1);
		LocalDateTime createdAt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = createdAt.format(formatter);
		String changeType = "削除";
		String changeSQL = "INSERT INTO change_database VALUES('" + changeType + "','" + tableName + "','" + (where + 1)
				+ "','削除','削除','削除','" + createdAt + "')";
		jdbcTemplate.update(changeSQL);
		return new ChangeData(changeType, tableName, String.valueOf(where + 1), "削除", "削除", "削除", formatted);
	}

	@PostMapping("/addRow")
	public ChangeData addRow(@RequestBody Map<String, Object> request) {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM change_database", Integer.class);
		if (1000 <= count) {
			return new ChangeData("更新不可", "", "", "", "", "", "");
		}
		int tableNumber = (int) request.get("activeTable");
		int where = (int) request.get("id");
		String tableName = tableNames.get(tableNumber);
		StringBuilder sb = new StringBuilder();
		sb.append("'" + where + "'");
		for (int i = 1, l = columnNames.get(tableNumber).size(); i < l; i++) {
			sb.append(",NULL");
		}
		String sql = "INSERT INTO " + tableName + " VALUES (" + sb.toString() + ")";
		jdbcTemplate.update(sql);
		LocalDateTime createdAt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = createdAt.format(formatter);
		String changeType = "追加";
		String changeSQL = "INSERT INTO change_database VALUES('" + changeType + "','" + tableName + "','" + where
				+ "','追加','追加','追加','" + createdAt + "')";
		jdbcTemplate.update(changeSQL);
		return new ChangeData(changeType, tableName, String.valueOf(where), "追加", "追加", "追加", formatted);
	}

	@PostMapping("/moveUpRow")
	@Transactional
	public ChangeData moveUpRow(@RequestBody Map<String, Object> request) {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM change_database", Integer.class);
		if (1000 <= count) {
			return new ChangeData("更新不可", "", "", "", "", "", "");
		}
		// 1. リクエストからidとtableIndexを取得
		int where = (int) request.get("rowIndex");
		int tableNumber = (int) request.get("activeTable");
		String tableName = tableNames.get(tableNumber);
		// 2. テーブル名を取得
		// String targetTable = tableNames.get(tableIndex);

		// 3. ID以外のカラムを取得
		List<String> columns = new ArrayList<>();
		for (String col : columnNames.get(tableNumber)) {
			if (!"id".equalsIgnoreCase(col)) {
				columns.add(col);
			}
		}
		LocalDateTime createdAt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = createdAt.format(formatter);
		String changeType = "上移動";
		String changeSQL = "INSERT INTO change_database VALUES('" + changeType + "','" + tableName + "','" + where
				+ "','上移動','上移動','上移動','" + createdAt + "')";
		// 4. UPDATEクエリ構築（MySQL向け）
		String setClauses = columns.stream().map(c -> String.format("t1.%s = t2.%s, t2.%s = t1.%s", c, c, c, c))
				.collect(Collectors.joining(", "));
		String updateSql = String.format("UPDATE %s t1 JOIN %s t2 ON t1.id = ? AND t2.id = ? SET %s", tableName,
				tableName, setClauses);

		jdbcTemplate.update(updateSql, where, where - 1);
		jdbcTemplate.update(changeSQL);
		return new ChangeData(changeType, tableName, String.valueOf(where), "上移動", "上移動", "上移動", formatted);
	}

	@PostMapping("/moveDownRow")
	@Transactional
	public ChangeData moveDownRow(@RequestBody Map<String, Object> request) {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM change_database", Integer.class);
		if (1000 <= count) {
			return new ChangeData("更新不可", "", "", "", "", "", "");
		}
		int where = (int) request.get("rowIndex");
		int tableNumber = (int) request.get("activeTable");
		String tableName = tableNames.get(tableNumber);

		List<String> columns = new ArrayList<>();
		for (String col : columnNames.get(tableNumber)) {
			if (!"id".equalsIgnoreCase(col)) {
				columns.add(col);
			}
		}
		LocalDateTime createdAt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = createdAt.format(formatter);
		String changeType = "下移動";
		String changeSQL = "INSERT INTO change_database VALUES('" + changeType + "','" + tableName + "','" + where
				+ "','下移動','下移動','下移動','" + createdAt + "')";
		// 4. UPDATEクエリ構築（MySQL向け）
		String setClauses = columns.stream().map(c -> String.format("t1.%s = t2.%s, t2.%s = t1.%s", c, c, c, c))
				.collect(Collectors.joining(", "));
		String updateSql = String.format("UPDATE %s t1 JOIN %s t2 ON t1.id = ? AND t2.id = ? SET %s", tableName,
				tableName, setClauses);
		jdbcTemplate.update(updateSql, where, where + 1);
		jdbcTemplate.update(changeSQL);
		return new ChangeData(changeType, tableName, String.valueOf(where), "下移動", "下移動", "下移動", formatted);
	}

	@PostMapping("/downloadSQL")
	public List<String> downloadSQL(@RequestBody String request) {
		int activeIndex = Integer.parseInt(request);
		// 2. テーブル名を取得
		List<String> sqlFile = new ArrayList<>();
		String table = tableNames.get(activeIndex);
		List<String> columnName = columnNames.get(activeIndex);
		sqlFile.add(table);
		sqlFile.add("DROP TABLE IF EXISTS " + table + ";");
		sqlFile.add("CREATE TABLE " + table + "(");
		sqlFile.add("id INTEGER PRIMARY KEY,");
		for (int i = 1, l = columnName.size(); i < l; i++) {
			String column = columnName.get(i);
			String type = columnTypes.get(column);
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
				if (columnTypes.get(columnName.get(i)).equals("VARCHAR")) {
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

	@GetMapping("/getChangeDataBase")
	public List<ChangeData> getChangeDataBase() {
		List<ChangeData> changeDatas = jdbcTemplate.query("SELECT * FROM change_database",
				(rs, rowNum) -> new ChangeData(rs.getString("change_type"), rs.getString("table_name"),
						rs.getString("id"), rs.getString("column_name"), rs.getString("before_value"),
						rs.getString("after_value"), rs.getString("created_at")));
		return changeDatas;
	}
}
