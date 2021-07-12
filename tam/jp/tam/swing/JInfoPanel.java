package jp.tam.swing;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import jp.tam.util.Constructor;

public class JInfoPanel extends JPanel {

	private static final long serialVersionUID = 8239786719072393245L;
	
	public JInfoPanel() {
		
		setLayout(new GridLayout(1, 4));
		
		setPreferredSize(new Dimension(Constructor.SCREEN_WIDTH, Constructor.SCREEN_HEIGHT - 320));
	}
}
