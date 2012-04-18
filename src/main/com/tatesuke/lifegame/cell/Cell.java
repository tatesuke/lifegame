package com.tatesuke.lifegame.cell;

public interface Cell {
	
	public static final int UPPER = 0;
	public static final int UPPER_RIGHT = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM_RIGHT = 3;
	public static final int BOTTOM = 4;
	public static final int BOTTOM_LEFT = 5;
	public static final int LEFT = 6;
	public static final int UPPER_LEFT = 7;
	
	public enum State {ALIVE, DEAD}

	State getState();

	void updateState();

	void evalNextState();

	Cell getNeighbor(int direction);
	
}
