package com.globallogic.ox.domain;

public class StageInTable {
	
	private Stage stage;
	private int rowPosition;
	private int columnPosition;

	public StageInTable(Stage stage, int rowPosition, int columnPosition) {
		this.stage = stage;
		this.rowPosition = rowPosition;
		this.columnPosition = columnPosition;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public int getRowPosition() {
		return rowPosition;
	}
	
	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}
	
	public int getColumnPosition() {
		return columnPosition;
	}
	
	public void setColumnPosition(int columnPosition) {
		this.columnPosition = columnPosition;
	}
}
