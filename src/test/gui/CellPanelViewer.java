package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.tatesuke.lifegame.cell.Cell;
import com.tatesuke.lifegame.cell.CellBuilder;
import com.tatesuke.lifegame.cell.Cell.State;
import com.tatesuke.lifegame.cell.CellImpl;
import com.tatesuke.lifegame.gui.CellPanel;
import com.tatesuke.lifegame.manager.GameManager;

@SuppressWarnings("serial")
public class CellPanelViewer extends JFrame {

	private CellPanel cellPanel;
	
	public CellPanelViewer() {
		GameManager mockManager = new GameManager() {
			@Override
			public Cell[][] getCell() {
				CellImpl[][] cell = new CellBuilder().buildCellGrid(10, 10);
				cell[1][1].setState(State.ALIVE);
				return cell;
			}
		};
		
		cellPanel = new CellPanel(mockManager);
		
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
