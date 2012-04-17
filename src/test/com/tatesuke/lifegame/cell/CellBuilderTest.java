package com.tatesuke.lifegame.cell;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CellBuilderTest {

	/**
	 * 指定されたサイズの配列が返されることの確認
	 */
	@Test
	public void サイズ指定のテスト() {
		Cell[][] cell = new CellBuilder().buildCellGrid(2, 3);
		assertThat(cell.length, is(2));
		assertThat(cell[0].length, is(3));
	}
	
	/**
	 * しっかりと上下左右斜め連結されているかテスト
	 */
	@Test
	public void 連結のテスト() {
		/* 3 * 3のCellを準備して、真ん中のセルが近傍のセルと正しくつながっているかをテスト */
		Cell[][] cell = new CellBuilder().buildCellGrid(3, 3);
		assertThat(cell[1][1].getNeighbor(Cell.UPPER), is(sameInstance(cell[0][1])));
		assertThat(cell[1][1].getNeighbor(Cell.UPPER_RIGHT), is(sameInstance(cell[0][2])));
		assertThat(cell[1][1].getNeighbor(Cell.RIGHT), is(sameInstance(cell[1][2])));
		assertThat(cell[1][1].getNeighbor(Cell.BOTTOM_RIGHT), is(sameInstance(cell[2][2])));
		assertThat(cell[1][1].getNeighbor(Cell.BOTTOM), is(sameInstance(cell[2][1])));
		assertThat(cell[1][1].getNeighbor(Cell.BOTTOM_LEFT), is(sameInstance(cell[2][0])));
		assertThat(cell[1][1].getNeighbor(Cell.LEFT), is(sameInstance(cell[1][0])));
		assertThat(cell[1][1].getNeighbor(Cell.UPPER_LEFT), is(sameInstance(cell[0][0])));
	}
	
}
