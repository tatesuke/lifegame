package com.tatesuke.lifegame.cell;

public class Cell {
	
	public static final int UPPER = 0;
	public static final int UPPER_RIGHT = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM_RIGHT = 3;
	public static final int BOTTOM = 4;
	public static final int BOTTOM_LEFT = 5;
	public static final int LEFT = 6;
	public static final int UPPER_LEFT = 7;
	
	public enum State {ALIVE, DEAD};
	
	private State currentState;
	private State nextState;
	private Cell[] neighbors;
	
	public Cell() {
		currentState = State.DEAD;
		nextState = null;
		neighbors = new Cell[8];
	}
	
	public State getState() {
		return currentState;
	}
	
	public void updateState() {
		if (nextState == null) {
			throw new IllegalStateException();
		}
		currentState = nextState;
		nextState = null;
	}
	
	public void evalNextState() {
		int aliveCount = countNeighborAliveCells();
		
		if (currentState == State.DEAD) {
			if (aliveCount == 3) {
				nextState = State.ALIVE;
			} else {
				nextState = State.DEAD;
			}
		} else if (currentState == State.ALIVE) {
			if ((2 <= aliveCount) && (aliveCount <= 3)) {
				nextState = State.ALIVE;
			} else {
				nextState = State.DEAD;
			}
		} else {
			throw new RuntimeException();
		}
	}
	
	private int countNeighborAliveCells() {
		int count = 0;
		for (Cell neighbor : neighbors) {
			if ((neighbor != null) && (neighbor.getState() == State.ALIVE)) {
				count++;
			}
		}
		return count;
	}

	public void setNeighbor(int direction, Cell cell) {
		if ((direction < 0) || (8 < direction)) {
			throw new IllegalArgumentException();
		}
		
		if (neighbors[direction] != cell) {
			neighbors[direction] = cell;
			cell.setNeighbor((direction + 4) % 8, this);
		}
	}

	public void setState(State state) {
		if (state == null) {
			throw new IllegalArgumentException();
		}
		this.currentState = state;
	}

	Cell getNeighbor(int direction) {
		return neighbors[direction];
	}

	State getNextState() {
		return nextState;
	}

	void setNextState(State nextState) {
		this.nextState = nextState;
	}
	
}
