package com.tatesuke.lifegame.cell;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CellBuilderTest {

	/**
	 * �w�肳�ꂽ�T�C�Y�̔z�񂪕Ԃ���邱�Ƃ̊m�F
	 */
	@Test
	public void �T�C�Y�w��̃e�X�g() {
		Cell[][] cell = new CellBuilder().buildCellGrid(2, 3);
		assertThat(cell.length, is(2));
		assertThat(cell[0].length, is(3));
	}
	
	/**
	 * ��������Ə㉺���E�΂ߘA������Ă��邩�e�X�g
	 */
	@Test
	public void �A���̃e�X�g() {
		/* 3 * 3��Cell���������āA�^�񒆂̃Z�����ߖT�̃Z���Ɛ������Ȃ����Ă��邩���e�X�g */
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
