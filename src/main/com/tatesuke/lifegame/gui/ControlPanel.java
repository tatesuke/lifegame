package com.tatesuke.lifegame.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tatesuke.lifegame.manager.GameManager;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameManager manager;

	private JSpinner rowSpinner;
	private JSpinner columnSipinner;
	private JLabel generateLabel;
	private JButton startStopButton;
	private JSlider intervalSlider;
	
	public ControlPanel(GameManager manager) {
		if (manager == null) {
			throw new IllegalArgumentException();
		}
		
		this.manager = manager;
	
		initComponents();
	}

	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel firstPanel = new JPanel();
		generateLabel = new JLabel();
		firstPanel.add(generateLabel);
		
		startStopButton = new JButton();
		startStopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (manager.isRunning()) {
					manager.stop();
				} else {
					manager.start();
				}
			}
		});
		firstPanel.add(startStopButton);
		add(firstPanel);
		
		JPanel secondPanel = new JPanel();
		rowSpinner = new JSpinner(new SpinnerNumberModel(manager.getRowSize(), 1, null, 1));
		rowSpinner.setPreferredSize(new Dimension(55, rowSpinner.getPreferredSize().height));
		secondPanel.add(rowSpinner);
		secondPanel.add(new JLabel("行"));
		
		columnSipinner = new JSpinner(new SpinnerNumberModel(manager.getColumnSize(), 1, null, 1));
		columnSipinner.setPreferredSize(new Dimension(55, columnSipinner.getPreferredSize().height));
		secondPanel.add(columnSipinner);
		secondPanel.add(new JLabel("列"));
		
		JButton resetButton = new JButton("リセット&サイズ変更");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSize = (Integer)rowSpinner.getValue();
				int columnSize = (Integer)columnSipinner.getValue();
				manager.reset(rowSize, columnSize);
			}
		});
		secondPanel.add(resetButton);
		add(secondPanel);
		
		JPanel thirdPanel = new JPanel();
		thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.X_AXIS));
		intervalSlider = new JSlider(50, 2000, (int)manager.getInterval());
		intervalSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				manager.setInterval(intervalSlider.getValue());
			}
		});
		thirdPanel.add(intervalSlider);
		add(thirdPanel);
		
		update();
	}
	
	public void update() {
		generateLabel.setText(manager.getGenerationNumber() + "世代");
		
		if (manager.isRunning()) {
			startStopButton.setText("ストップ");
		} else {
			startStopButton.setText("スタート");
		}
	}
	
}
