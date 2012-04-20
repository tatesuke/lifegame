package com.tatesuke.lifegame.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AppFrame extends JFrame  {
	
	private static final long serialVersionUID = 1L;

	private AppPanel appPanel;
	
	public AppFrame() {
		setLayout(new BorderLayout());
		appPanel = new AppPanel();
		this.add(appPanel, BorderLayout.CENTER);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void launch() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {
		new AppFrame().launch();
	}

}
