package com.tatesuke.lifegame.manager;

import com.tatesuke.lifegame.cell.Cell;
import com.tatesuke.lifegame.cell.Cell.State;
import com.tatesuke.lifegame.cell.CellBuilder;
import com.tatesuke.lifegame.cell.CellImpl;

public class GameManager {

	private long interval = 1000L;
	private GameObserver observer;
	private int rowSize;
	private int columnSize;
	private CellImpl[][] cell;
	private int generationNumber;
	private boolean isRunning;

	public GameManager() {
		reset(10, 10);
	}

	public void setObserver(GameObserver observer) {
		this.observer = observer;
	}

	public void start() {
		if (!isRunning()) {
			isRunning = true;
			new Thread(getGameThread()).start();
		}
	}

	public void stop() {
		isRunning = false;
		notifyObserver();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public int getGenerationNumber() {
		return generationNumber;
	}

	private Runnable getGameThread() {
		return new Runnable() {
			@Override
			public void run() {
				while (isRunning) {
					int aliveCount = updateCells();
					if (aliveCount == 0) {
						stop();
						break;
					}
					generationNumber++;
					notifyObserver();
					try {
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	private int updateCells() {
		int aliveCount = 0;
		synchronized (cell) {
			for (int row = 0; row < cell.length; row++) {
				for (int column = 0; column < cell[row].length; column++) {
					if (cell[row][column].getState() == State.ALIVE) {
						aliveCount++;
					}
					cell[row][column].evalNextState();
				}
			}
			for (int row = 0; row < cell.length; row++) {
				for (int column = 0; column < cell[row].length; column++) {
					cell[row][column].updateState();
				}
			}
		}
		return aliveCount;
	}

	private void notifyObserver() {
		if (observer != null) {
			observer.onGenerationChanged();
		}
	}

	public void toggleCell(int row, int column) {
		synchronized (cell) {
			State state = cell[row][column].getState();
			switch (state) {
			case ALIVE:
				cell[row][column].setState(State.DEAD);
				break;
			case DEAD:
				cell[row][column].setState(State.ALIVE);
				break;
			default:
				throw new RuntimeException();
			}
			if (observer != null) {
				observer.onGenerationChanged();
			}
		}
	}

	public Cell[][] getCell() {
		return cell;
	}

	public int getRowSize() {
		return rowSize;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void reset(int rowSize, int columnSize) {
		if ((rowSize <= 0) || (columnSize <= 0)) {
			throw new IllegalArgumentException();
		}

		stop();
		generationNumber = 0;
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		cell = new CellBuilder().buildCellGrid(rowSize, columnSize);
		notifyObserver();
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		if (interval <= 0L) {
			throw new IllegalArgumentException();
		}
		this.interval = interval;
	}

}
