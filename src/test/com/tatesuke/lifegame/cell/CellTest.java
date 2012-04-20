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
		CellImpl cell = new CellImpl();
		assertThat(cell.getState(), is(State.DEAD));
	}
	
	/**
	 * setAliveで生死を操作できること。
	 */
	@Test
	public void setAliveのテスト() {
		CellImpl cell = new CellImpl();
		
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
		CellImpl cell1 = new CellImpl();
		CellImpl cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.UPPER, cell2);
		assertThat(cell1.getNeighbor(CellImpl.UPPER), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.BOTTOM), is(sameInstance((Cell)cell1)));
		
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.UPPER_RIGHT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.UPPER_RIGHT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.BOTTOM_LEFT), is(sameInstance((Cell)cell1)));
		
		// 右
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.RIGHT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.RIGHT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.LEFT), is(sameInstance((Cell)cell1)));
		
		// 右下
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.BOTTOM_RIGHT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.BOTTOM_RIGHT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.UPPER_LEFT), is(sameInstance((Cell)cell1)));
		
		// 下
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.BOTTOM, cell2);
		assertThat(cell1.getNeighbor(CellImpl.BOTTOM), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.UPPER), is(sameInstance((Cell)cell1)));
		
		// 左下
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.BOTTOM_LEFT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.BOTTOM_LEFT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.UPPER_RIGHT), is(sameInstance((Cell)cell1)));
		
		// 左
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.LEFT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.LEFT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.RIGHT), is(sameInstance((Cell)cell1)));
		
		// 左上
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.UPPER_LEFT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.UPPER_LEFT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.BOTTOM_RIGHT), is(sameInstance((Cell)cell1)));
	}
	
	/**
	 * 死んでいるセルに隣接する生きたセルがちょうど3つあれば、次の世代が誕生する。
	 */
	@Test
	public void 誕生() {
		CellImpl cell00 = new CellImpl();
		CellImpl cell01 = new CellImpl();
		CellImpl cell02 = new CellImpl();
		CellImpl cell11 = new CellImpl();
		
		cell00.setState(State.ALIVE);
		cell01.setState(State.ALIVE);
		cell02.setState(State.ALIVE);
		cell11.setState(State.DEAD);
		
		cell11.setNeighbor(CellImpl.UPPER_LEFT, cell00);
		cell11.setNeighbor(CellImpl.UPPER, cell01);
		cell11.setNeighbor(CellImpl.UPPER_RIGHT, cell02);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.ALIVE));
	}
	
	/**
	 * 生きているセルに隣接する生きたセルが2つか3つならば、次の世代でも生存する。
	 */
	@Test
	public void 生存のテスト_隣接2つ() {
		CellImpl cell00 = new CellImpl();
		CellImpl cell01 = new CellImpl();
		CellImpl cell11 = new CellImpl();
		
		cell00.setState(State.ALIVE);
		cell01.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(CellImpl.UPPER_LEFT, cell00);
		cell11.setNeighbor(CellImpl.UPPER, cell01);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.ALIVE));
	}
	
	/**
	 * 生きているセルに隣接する生きたセルが2つか3つならば、次の世代でも生存する。
	 */
	@Test
	public void 生存のテスト_隣接3つ() {
		CellImpl cell00 = new CellImpl();
		CellImpl cell01 = new CellImpl();
		CellImpl cell02 = new CellImpl();
		CellImpl cell11 = new CellImpl();
		
		cell00.setState(State.ALIVE);
		cell01.setState(State.ALIVE);
		cell02.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(CellImpl.UPPER_LEFT, cell00);
		cell11.setNeighbor(CellImpl.UPPER, cell01);
		cell11.setNeighbor(CellImpl.UPPER_RIGHT, cell02);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.ALIVE));
	}
	
	/**
	 * 生きているセルに隣接する生きたセルが1つ以下ならば、過疎により死滅する。
	 */
	@Test
	public void 過疎のテスト() {
		CellImpl cell00 = new CellImpl();
		CellImpl cell11 = new CellImpl();
		
		cell00.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(CellImpl.UPPER_LEFT, cell00);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.DEAD));
	}
	
	/**
	 * 生きているセルに隣接する生きたセルが4つ以上ならば、過密により死滅する。
	 */
	@Test
	public void 過密のテスト() {
		CellImpl cell00 = new CellImpl();
		CellImpl cell01 = new CellImpl();
		CellImpl cell02 = new CellImpl();
		CellImpl cell10 = new CellImpl();
		CellImpl cell11 = new CellImpl();
		
		cell00.setState(State.ALIVE);
		cell01.setState(State.ALIVE);
		cell02.setState(State.ALIVE);
		cell10.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(CellImpl.UPPER_LEFT, cell00);
		cell11.setNeighbor(CellImpl.UPPER, cell01);
		cell11.setNeighbor(CellImpl.UPPER_RIGHT, cell02);
		cell11.setNeighbor(CellImpl.LEFT, cell10);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.DEAD));
	}
	
	/**
	 * 次の世代の状態が反映されるかテスト
	 */
	@Test
	public void updateStateのテスト() {
		CellImpl cell = new CellImpl();
		
		assertThat(cell.getState(), is(not(State.ALIVE)));
		cell.setNextState(State.ALIVE);
		cell.updateState();
		assertThat(cell.getState(), is(State.ALIVE));
	}
}
