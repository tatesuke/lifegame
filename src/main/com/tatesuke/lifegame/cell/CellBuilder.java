package com.tatesuke.lifegame.cell;

public class CellBuilder {

	public Cell[][] buildCellGrid(int rowSize, int columnSize) {
		if ((rowSize <= 0) || (columnSize <= 0)) {
			throw new IllegalArgumentException();
		}
		
		Cell[][] grid = new Cell[rowSize][columnSize];
		
		for (int row = 0; row < rowSize; row++) {
			for (int column = 0; column < columnSize; column++) {
				grid[row][column] = new Cell();
				if (0 < row) {
					grid[row][column].setNeighbor(Cell.UPPER, grid[row - 1][column]);
				}
				if (0 < column) {
					grid[row][column].setNeighbor(Cell.LEFT, grid[row][column - 1]);
				}
				if ((0 < row) && (0 < column)) {
					grid[row][column].setNeighbor(Cell.UPPER_LEFT, grid[row - 1][column - 1]);
				}
				if ((0 < row) && ((column + 1) < columnSize)){
					grid[row][column].setNeighbor(Cell.UPPER_RIGHT, grid[row - 1][column + 1]);
				}
			}
		}
		
		return grid;
	}
	
}
