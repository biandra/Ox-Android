package com.globallogic.ox.app.converter;

import java.util.ArrayList;
import java.util.List;
import com.globallogic.ox.domain.Cell;
import com.globallogic.ox.domain.Stage;
import com.globallogic.ox.domain.StageInTable;
import com.globallogic.ox.domain.Table;
import com.globallogic.ox.domain.TableRow;

public class TablesConverter {
	
	private static final int COLUMNSxTABLE = 2;
	private int rowPosition;

	public List<Table> convert(List<Stage> stages) {
		
		Stage stage = firstStage(stages);
		
		List<StageInTable> stagesInTable = new ArrayList<StageInTable>();
		rowPosition = 0;
		int initialColumnPosition = 0;
		buildStageInTable(stagesInTable,stages, stage, initialColumnPosition);
		
		return buildTables(stagesInTable, getMaxRowPositionStageInTable(stagesInTable), getMaxColumnPositionStageInTable(stagesInTable));
		
	}

	private List<Table> buildTables(List<StageInTable> stagesInTable, int maxRoxPosition, int maxColumnPosition) {
		List<Table> tables = new ArrayList<Table>();
		int numberOfTables = (int) Math.ceil((double)maxColumnPosition / (double)COLUMNSxTABLE);
		
		maxColumnPosition = numberOfTables * COLUMNSxTABLE;
		
		for (int i = 0; i < numberOfTables; i++) {
			Table table = new Table();
			ArrayList<TableRow> column = new ArrayList<TableRow>();
			for (int x = 0; x < maxRoxPosition; x++) {
				List<Cell> cells = new ArrayList<Cell>();
				for (int y = 0; y < maxColumnPosition; y++) {
					Cell cell = new Cell();
					cell.setStage(getCellInPosition(x+1, y+1, stagesInTable));
					cells.add(cell);
				}
				column.add(new TableRow(cells));
			}
			table.setColumn(column);
			tables.add(table);
		}
		
		return tables;
	}

	private Stage getCellInPosition(int roxPosition, int columnPosition, List<StageInTable> stagesInTable) {
		for(StageInTable stageInTable : stagesInTable){
			if ((stageInTable.getRowPosition() == roxPosition) &&( stageInTable.getColumnPosition() == columnPosition)) {
				return stageInTable.getStage();
			}
		}
		return null;
	}

	private int getMaxColumnPositionStageInTable(List<StageInTable> stagesInTable) {
		int maxColumnPosition = 0;
		for(StageInTable stageInTable : stagesInTable){
			if (stageInTable.getColumnPosition() > maxColumnPosition) {
				maxColumnPosition = stageInTable.getColumnPosition();
			}
		}
		return maxColumnPosition;
	}
	
	private int getMaxRowPositionStageInTable(List<StageInTable> stagesInTable) {
		int maxRoxPosition = 0;
		for(StageInTable stageInTable : stagesInTable){
			if (stageInTable.getRowPosition() > maxRoxPosition) {
				maxRoxPosition = stageInTable.getRowPosition();
			}
		}
		return maxRoxPosition;
	}

	private void buildStageInTable(List<StageInTable> stagesInTable, List<Stage> stages, Stage stage, int columnPosition) {
		List<Integer> idSons = stage.getNext();
		if (idSons != null) {
			StageInTable stageInTable = new StageInTable(stage, rowPosition+1, columnPosition+1);
			stagesInTable.add(stageInTable);
			
			columnPosition++;
			for (int i = 0; i < idSons.size(); i++) {
				Stage stageSon = searchStage(stages, idSons.get(i));
				if (i != 0) {//if is not first son
					rowPosition++;
				}
				buildStageInTable(stagesInTable, stages, stageSon, columnPosition);
			}
		}
	}

	private Stage searchStage(List<Stage> stages, Integer idSon) {
		for(Stage stage : stages){
			if (stage.getId() == idSon) {
				return stage;
			}
		}
		return null;
	}

	private Stage firstStage(List<Stage> stages) {
		for(Stage stage : stages){
			if (stage.getPrevious() == null) {
				return stage;
			}
		}
		return null;
	}
}
