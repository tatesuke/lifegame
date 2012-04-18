package com.tatesuke.lifegame.gui;

import javax.swing.JPanel;

import com.tatesuke.lifegame.manager.GameManager;
import com.tatesuke.lifegame.manager.GameObserver;

public class AppPanel extends JPanel implements GameObserver { 

	private static final long serialVersionUID = 1L;

	private CellPanel cellPanel;
	
	public AppPanel(GameManager manager) {
		cellPanel = new CellPanel(manager);
		cellPanel.setCell(manager.getCell());
		add(cellPanel);
	}

	@Override
	public void onGenerationChanged() {
		System.out.println("hoge");
		cellPanel.repaint();
	}
	
}
