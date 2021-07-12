package jp.tam.swing;

import java.awt.Dimension;

import javax.swing.JPanel;

import jp.tam.util.Constructor;

public class JFramePanel extends JPanel {

	private static final long serialVersionUID = -3624432055257468430L;

	public JFramePanel() {
		
		setName(getClass().getSimpleName());
		
		setPreferredSize(new Dimension(Constructor.SCREEN_WIDTH, Constructor.SCREEN_HEIGHT));
	}
}
