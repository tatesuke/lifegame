package com.tatesuke.lifegame.cell;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.tatesuke.lifegame.cell.Cell.State;

public class CellTest {

	/**
	 * ������Ԃł͎���ł���B
	 */
	@Test
	public void ������Ԃ̃e�X�g() {
		Cell cell = new Cell();
		assertThat(cell.getState(), is(State.DEAD));
	}
	
	/**
	 * setAlive�Ő����𑀍�ł��邱�ƁB
	 */
	@Test
	public void setAlive�̃e�X�g() {
		Cell cell = new Cell();
		
		cell.setState(State.ALIVE);
		assertThat(cell.getState(), is(State.ALIVE));
		
		cell.setState(State.DEAD);
		assertThat(cell.getState(), is(State.DEAD));
	}
	
	/**
	 * �㉺���E�΂߂��ׂđ��݂Ɍ����ł��邱�ƁB
	 */
	@Test
	public void setNeighbor�̃e�X�g() {
		// ��
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();		
		cell1.setNeighbor(Cell.UPPER, cell2);
		assertThat(cell1.getNeighbor(Cell.UPPER), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.BOTTOM), is(sameInstance(cell1)));
		
		// �E��
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.UPPER_RIGHT, cell2);
		assertThat(cell1.getNeighbor(Cell.UPPER_RIGHT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.BOTTOM_LEFT), is(sameInstance(cell1)));
		
		// �E
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.RIGHT, cell2);
		assertThat(cell1.getNeighbor(Cell.RIGHT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.LEFT), is(sameInstance(cell1)));
		
		// �E��
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.BOTTOM_RIGHT, cell2);
		assertThat(cell1.getNeighbor(Cell.BOTTOM_RIGHT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.UPPER_LEFT), is(sameInstance(cell1)));
		
		// ��
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.BOTTOM, cell2);
		assertThat(cell1.getNeighbor(Cell.BOTTOM), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.UPPER), is(sameInstance(cell1)));
		
		// ����
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.BOTTOM_LEFT, cell2);
		assertThat(cell1.getNeighbor(Cell.BOTTOM_LEFT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.UPPER_RIGHT), is(sameInstance(cell1)));
		
		// ��
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.LEFT, cell2);
		assertThat(cell1.getNeighbor(Cell.LEFT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.RIGHT), is(sameInstance(cell1)));
		
		// ����
		cell1 = new Cell();
		cell2 = new Cell();		
		cell1.setNeighbor(Cell.UPPER_LEFT, cell2);
		assertThat(cell1.getNeighbor(Cell.UPPER_LEFT), is(sameInstance(cell2)));
		assertThat(cell2.getNeighbor(Cell.BOTTOM_RIGHT), is(sameInstance(cell1)));
	}
	
	/**
	 * ����ł���Z���ɗאڂ��鐶�����Z�������傤��3����΁A���̐��オ�a������B
	 */
	@Test
	public void �a��() {
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
	 * �����Ă���Z���ɗאڂ��鐶�����Z����2��3�Ȃ�΁A���̐���ł���������B
	 */
	@Test
	public void �����̃e�X�g_�א�2��() {
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
	 * �����Ă���Z���ɗאڂ��鐶�����Z����2��3�Ȃ�΁A���̐���ł���������B
	 */
	@Test
	public void �����̃e�X�g_�א�3��() {
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
	 * �����Ă���Z���ɗאڂ��鐶�����Z����1�ȉ��Ȃ�΁A�ߑa�ɂ�莀�ł���B
	 */
	@Test
	public void �ߑa�̃e�X�g() {
		Cell cell00 = new Cell();
		Cell cell11 = new Cell();
		
		cell00.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(Cell.UPPER_LEFT, cell00);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.DEAD));
	}
	
	/**
	 * �����Ă���Z���ɗאڂ��鐶�����Z����4�ȏ�Ȃ�΁A�ߖ��ɂ�莀�ł���B
	 */
	@Test
	public void �ߖ��̃e�X�g() {
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
	 * ���̐���̏�Ԃ����f����邩�e�X�g
	 */
	@Test
	public void updateState�̃e�X�g() {
		Cell cell = new Cell();
		
		assertThat(cell.getState(), is(not(State.ALIVE)));
		cell.setNextState(State.ALIVE);
		cell.updateState();
		assertThat(cell.getState(), is(State.ALIVE));
	}
}
