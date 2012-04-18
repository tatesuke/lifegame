package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.tatesuke.lifegame.cell.CellBuilder;
import com.tatesuke.lifegame.cell.Cell.State;
import com.tatesuke.lifegame.cell.CellImpl;
import com.tatesuke.lifegame.gui.CellPanel;

@SuppressWarnings("serial")
public class CellPanelViewer extends JFrame {

	private CellPanel cellPanel;
	
	public CellPanelViewer() {
		CellImpl[][] cell = new CellBuilder().buildCellGrid(10, 10);
		cell[1][1].setState(State.ALIVE);
		
		cellPanel = new CellPanel(null);
		cellPanel.setCell(cell);
		
		this.add(cellPanel);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CellPanelViewer().setVisible(true);
			}
		});
	}
	
}
