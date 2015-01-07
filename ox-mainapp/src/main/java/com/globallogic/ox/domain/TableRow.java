package com.globallogic.ox.domain;

import java.util.List;

public class TableRow {
	
    private List<Cell> cell;
    
    public TableRow(List<Cell> cell) {
            this.setCell(cell);
    }

	public List<Cell> getCell() {
		return cell;
	}

	public void setCell(List<Cell> cell) {
		this.cell = cell;
	}
}