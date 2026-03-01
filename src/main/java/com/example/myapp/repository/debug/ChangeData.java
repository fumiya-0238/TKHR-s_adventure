package com.example.myapp.repository.debug;

public class ChangeData {
	private String changeType;
	private String tableName;
	private String id;
	private String columnName;
	private String beforeValue;
	private String afterValue;
	private String createdAt;

	public ChangeData(String changeType, String tableName, String id, String columnName, String beforeValue,
			String afterValue, String createdAt) {
		this.changeType = changeType;
		this.tableName = tableName;
		this.id = id;
		this.columnName = columnName;
		this.beforeValue = beforeValue;
		this.afterValue = afterValue;
		this.createdAt = createdAt;
	}

	public String getChangeType() {
		return changeType;
	}

	public String getTableName() {
		return tableName;
	}

	public String getId() {
		return id;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getBeforeValue() {
		return beforeValue;
	}

	public String getAfterValue() {
		return afterValue;
	}

	public String getCreatedAt() {
		return createdAt;
	}
	
}
