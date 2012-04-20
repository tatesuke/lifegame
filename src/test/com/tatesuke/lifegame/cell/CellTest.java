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
		CellImpl cell = new CellImpl();
		assertThat(cell.getState(), is(State.DEAD));
	}
	
	/**
	 * setAlive�Ő����𑀍�ł��邱�ƁB
	 */
	@Test
	public void setAlive�̃e�X�g() {
		CellImpl cell = new CellImpl();
		
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
		
		// �E
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.RIGHT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.RIGHT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.LEFT), is(sameInstance((Cell)cell1)));
		
		// �E��
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.BOTTOM_RIGHT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.BOTTOM_RIGHT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.UPPER_LEFT), is(sameInstance((Cell)cell1)));
		
		// ��
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.BOTTOM, cell2);
		assertThat(cell1.getNeighbor(CellImpl.BOTTOM), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.UPPER), is(sameInstance((Cell)cell1)));
		
		// ����
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.BOTTOM_LEFT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.BOTTOM_LEFT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.UPPER_RIGHT), is(sameInstance((Cell)cell1)));
		
		// ��
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.LEFT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.LEFT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.RIGHT), is(sameInstance((Cell)cell1)));
		
		// ����
		cell1 = new CellImpl();
		cell2 = new CellImpl();		
		cell1.setNeighbor(CellImpl.UPPER_LEFT, cell2);
		assertThat(cell1.getNeighbor(CellImpl.UPPER_LEFT), is(sameInstance((Cell)cell2)));
		assertThat(cell2.getNeighbor(CellImpl.BOTTOM_RIGHT), is(sameInstance((Cell)cell1)));
	}
	
	/**
	 * ����ł���Z���ɗאڂ��鐶�����Z�������傤��3����΁A���̐��オ�a������B
	 */
	@Test
	public void �a��() {
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
	 * �����Ă���Z���ɗאڂ��鐶�����Z����2��3�Ȃ�΁A���̐���ł���������B
	 */
	@Test
	public void �����̃e�X�g_�א�2��() {
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
	 * �����Ă���Z���ɗאڂ��鐶�����Z����2��3�Ȃ�΁A���̐���ł���������B
	 */
	@Test
	public void �����̃e�X�g_�א�3��() {
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
	 * �����Ă���Z���ɗאڂ��鐶�����Z����1�ȉ��Ȃ�΁A�ߑa�ɂ�莀�ł���B
	 */
	@Test
	public void �ߑa�̃e�X�g() {
		CellImpl cell00 = new CellImpl();
		CellImpl cell11 = new CellImpl();
		
		cell00.setState(State.ALIVE);
		cell11.setState(State.ALIVE);
		
		cell11.setNeighbor(CellImpl.UPPER_LEFT, cell00);
		
		cell11.evalNextState();
		assertThat(cell11.getNextState(), is(State.DEAD));
	}
	
	/**
	 * �����Ă���Z���ɗאڂ��鐶�����Z����4�ȏ�Ȃ�΁A�ߖ��ɂ�莀�ł���B
	 */
	@Test
	public void �ߖ��̃e�X�g() {
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
	 * ���̐���̏�Ԃ����f����邩�e�X�g
	 */
	@Test
	public void updateState�̃e�X�g() {
		CellImpl cell = new CellImpl();
		
		assertThat(cell.getState(), is(not(State.ALIVE)));
		cell.setNextState(State.ALIVE);
		cell.updateState();
		assertThat(cell.getState(), is(State.ALIVE));
	}
}
