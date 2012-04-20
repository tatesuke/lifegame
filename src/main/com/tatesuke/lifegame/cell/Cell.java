package com.tatesuke.lifegame.cell;

public interface Cell {
	
	public enum State {ALIVE, DEAD}

	State getState();

	void updateState();

	void evalNextState();

}
