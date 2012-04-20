package com.tatesuke.lifegame.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.tatesuke.lifegame.manager.GameManager;
import com.tatesuke.lifegame.manager.GameObserver;

public class AppPanel extends JPanel implements GameObserver { 

	private static final long serialVersionUID = 1L;

	private CellPanel cellPanel;
	private ControlPanel controlPanel;
	
	public AppPanel() {
		GameManager manager = new GameManager();
		manager.setObserver(this);
		
		setLayout(new BorderLayout());
		cellPanel = new CellPanel(manager);
		add(cellPanel, BorderLayout.CENTER);
		
		controlPanel = new ControlPanel(manager);
		add(controlPanel, BorderLayout.SOUTH);
	}

	@Override
	public void onGenerationChanged() {
		cellPanel.update();
		controlPanel.update();
	}
	
}
