package com.tatesuke.lifegame.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.tatesuke.lifegame.manager.GameManager;

public class AppFrame extends JFrame  {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new AppFrame().launch();
	}

	private AppPanel appPanel;
	private GameManager manager;
	
	public AppFrame() {
		setLayout(new BorderLayout());
		manager = new GameManager();
		appPanel = new AppPanel(manager);
		this.add(appPanel, BorderLayout.CENTER);
		pack();
		manager.setObserver(appPanel);
	}
	
	private void launch() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setVisible(true);
			}
		});
		manager.start();
	}

}
