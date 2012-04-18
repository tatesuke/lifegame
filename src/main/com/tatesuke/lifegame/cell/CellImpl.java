package com.tatesuke.lifegame.cell;

public class CellImpl implements Cell {
	
	private State currentState;
	private State nextState;
	private Cell[] neighbors;
	
	public CellImpl() {
		currentState = State.DEAD;
		nextState = null;
		neighbors = new Cell[8];
	}
	
	@Override
	public State getState() {
		return currentState;
	}
	
	@Override
	public void updateState() {
		if (nextState == null) {
			throw new IllegalStateException();
		}
		currentState = nextState;
		nextState = null;
	}
	
	@Override
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

	public void setNeighbor(int direction, CellImpl cell) {
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

	@Override
	public Cell getNeighbor(int direction) {
		return neighbors[direction];
	}

	State getNextState() {
		return nextState;
	}

	void setNextState(State nextState) {
		this.nextState = nextState;
	}
}
