package com.tatesuke.lifegame.cell;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.tatesuke.lifegame.cell.Cell.State;

public class CellTest {

	/**
	 * 初期状態では死んでいる。
	 */
	@Test
	public void 初期状態のテスト() {
		Cell cell = new Cell();
		assertThat(cell.getState(), is(State.DEAD));
	}
	
	/**
	 * setAliveで生死を操作できること。
	 */
	@Test
	public void setAliveのテスト() {
		Cell cell = new Cell();
		
		cell.setState(State.ALIVE);
		assertThat(cell.getState(), is(State.ALIVE));
		
		cell.setState(State.DEAD);
		assertThat(cell.getState(), is(State.DEAD));
	}
	
	/**
	 * 上下左右斜めすべて相互に結合できること。
	 */
	@Test
	public void setNeighborのテスト() {
		// 上
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();		
		cell1.setNeighbor(Cell.UPPER, cell2);
		assertThat(cell1.getNeighbor(Cell.UPPER), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.BOTTOM), is(sameInstance(cell1)));
		
		// 右上
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.UPPER_RIGHT, cell2);
		assertThat(cell1.getNeighbor(Cell.UPPER_RIGHT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.BOTTOM_LEFT), is(sameInstance(cell1)));
		
		// 右
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.RIGHT, cell2);
		assertThat(cell1.getNeighbor(Cell.RIGHT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.LEFT), is(sameInstance(cell1)));
		
		// 右下
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.BOTTOM_RIGHT, cell2);
		assertThat(cell1.getNeighbor(Cell.BOTTOM_RIGHT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.UPPER_LEFT), is(sameInstance(cell1)));
		
		// 下
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.BOTTOM, cell2);
		assertThat(cell1.getNeighbor(Cell.BOTTOM), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.UPPER), is(sameInstance(cell1)));
		
		// 左下
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.BOTTOM_LEFT, cell2);
		assertThat(cell1.getNeighbor(Cell.BOTTOM_LEFT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.UPPER_RIGHT), is(sameInstance(cell1)));
		
		// 左
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.LEFT, cell2);
		assertThat(cell1.getNeighbor(Cell.LEFT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.RIGHT), is(sameInstance(cell1)));
		
		// 左上
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.UPPER_LEFT, cell2);
		assertThat(cell1.getNeighbor(Cell.UPPER_LEFT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.BOTTOM_RIGHT), is(sameInstance(cell1)));
	}
	
	/**
	 * 死んでいるセルに隣接する生きたセルがちょうど3つあれば、次の世代が誕生する。
	 */
	@Test
	public void 誕生() {
		Cell cell00 = new Cell();
		Cell cell01 = new Cell();
		Cell cell02 = new Cell();
		Cell cell11 = new Cell();
		
		cell00.setState(State.ALIVE);
		cell01.setState(State.ALIVE);
		cell02.setState(State.ALIVE);
		cell11.setState(State.DEAD);
		
		cell11.setNeighbor(Cell.UPPER_LEFT, cell00);
		cell11.setNeighbor(Cell.UPPER, cell01);
		cell11.setNeighbor(Cell.UPPER_RIGHT, cell02);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.ALIVE));
	}
	
	/**
	 * 生きているセルに隣接する生きたセルが2つか3つならば、次の世代でも生存する。
	 */
	@Test
	public void 生存のテスト_隣接2つ() {
		Cell cell00 = new Cell();
		Cell cell01 = new Cell();
		Cell cell11 = new Cell();
		
		cell00.setState(State.ALIVE);
		cell01.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(Cell.UPPER_LEFT, cell00);
		cell11.setNeighbor(Cell.UPPER, cell01);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.ALIVE));
	}
	
	/**
	 * 生きているセルに隣接する生きたセルが2つか3つならば、次の世代でも生存する。
	 */
	@Test
	public void 生存のテスト_隣接3つ() {
		Cell cell00 = new Cell();
		Cell cell01 = new Cell();
		Cell cell02 = new Cell();
		Cell cell11 = new Cell();
		
		cell00.setState(State.ALIVE);
		cell01.setState(State.ALIVE);
		cell02.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(Cell.UPPER_LEFT, cell00);
		cell11.setNeighbor(Cell.UPPER, cell01);
		cell11.setNeighbor(Cell.UPPER_RIGHT, cell02);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.ALIVE));
	}
	
	/**
	 * 生きているセルに隣接する生きたセルが1つ以下ならば、過疎により死滅する。
	 */
	@Test
	public void 過疎のテスト() {
		Cell cell00 = new Cell();
		Cell cell11 = new Cell();
		
		cell00.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(Cell.UPPER_LEFT, cell00);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.DEAD));
	}
	
	/**
	 * 生きているセルに隣接する生きたセルが4つ以上ならば、過密により死滅する。
	 */
	@Test
	public void 過密のテスト() {
		Cell cell00 = new Cell();
		Cell cell01 = new Cell();
		Cell cell02 = new Cell();
		Cell cell10 = new Cell();
		Cell cell11 = new Cell();
		
		cell00.setState(State.ALIVE);
		cell01.setState(State.ALIVE);
		cell02.setState(State.ALIVE);
		cell10.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(Cell.UPPER_LEFT, cell00);
		cell11.setNeighbor(Cell.UPPER, cell01);
		cell11.setNeighbor(Cell.UPPER_RIGHT, cell02);
		cell11.setNeighbor(Cell.LEFT, cell10);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.DEAD));
	}
	
	/**
	 * 次の世代の状態が反映されるかテスト
	 */
	@Test
	public void updateStateのテスト() {
		Cell cell = new Cell();
		
		assertThat(cell.getState(), is(not(State.ALIVE)));
		cell.setNextState(State.ALIVE);
		cell.updateState();
		assertThat(cell.getState(), is(State.ALIVE));
	}
}
