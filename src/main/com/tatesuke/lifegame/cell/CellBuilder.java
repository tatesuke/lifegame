package com.tatesuke.lifegame.cell;

public class CellBuilder {

	public CellImpl[][] buildCellGrid(int rowSize, int columnSize) {
		if ((rowSize <= 0) || (columnSize <= 0)) {
			throw new IllegalArgumentException();
		}
		
		CellImpl[][] grid = new CellImpl[rowSize][columnSize];
		
		for (int row = 0; row < rowSize; row++) {
			for (int column = 0; column < columnSize; column++) {
				grid[row][column] = new CellImpl();
				if (0 < row) {
					grid[row][column].setNeighbor(CellImpl.UPPER, grid[row - 1][column]);
				}
				if (0 < column) {
					grid[row][column].setNeighbor(CellImpl.LEFT, grid[row][column - 1]);
				}
				if ((0 < row) && (0 < column)) {
					grid[row][column].setNeighbor(CellImpl.UPPER_LEFT, grid[row - 1][column - 1]);
				}
				if ((0 < row) && ((column + 1) < columnSize)){
					grid[row][column].setNeighbor(CellImpl.UPPER_RIGHT, grid[row - 1][column + 1]);
				}
			}
		}
		
		return grid;
	}
	
}
