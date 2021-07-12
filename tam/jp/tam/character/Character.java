package jp.tam.character;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import jp.tam.swing.JGamePanel;
import jp.tam.util.EventAnimation;
import jp.tam.util.EventGenerator;

public abstract class Character implements Runnable {
	
	private final static int R = 0x00d20000; 
	private final static int G = 0x0000b400;
	private final static int B = 0x0000008c;
	
	public final static int RGB = R | G | B;

	protected int[][] pixel_matrix_default;
	protected int[][] pixel_matrix_zzz;
	protected int[][] pixel_matrix_feed;
	protected int[][] pixel_matrix_shit;
	protected int[][] pixel_matrix_mood;
	protected int[][] pixel_matrix_death;
	
	protected int right_point, left_point, width_character;
	
	protected JGamePanel jGamePNL;
	
	protected EventGenerator eventGenerator;
	
	protected EventAnimation eventAnimation;
	
	protected Character(JGamePanel jGamePNL, EventGenerator eventGenerator, EventAnimation eventAnimation) {
		
		this.jGamePNL = jGamePNL;
		this.eventGenerator = eventGenerator;
		this.eventAnimation = eventAnimation;
	}
	
	public JGamePanel getJGamePanel() {
		
		return jGamePNL;
	}

	public EventGenerator getEventGenerator() {
		
		return eventGenerator;
	}
	
	public EventAnimation getEventAnimation() {
		
		return eventAnimation;
	}
	
	protected void updateScreen() {
		
		EventQueue.invokeLater(() -> {
			
			//repaint game panel
			jGamePNL.repaint();
		});
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch(InterruptedException exc) {
			
			exc.printStackTrace();
		}
	}
	
	protected void move() {
		
		int right_border = pixel_matrix_default[0].length - 1;
		int left_border = 0;
		
		boolean isToLeft = true;		
		
		while(true) {
			
			try {
				
				while(eventGenerator.getCurrentEventCode() != EventGenerator.DEFAULT_EVENT && eventAnimation.isAllowShowAnimation()) {
					
					synchronized(this) {
						
						wait();
					}
				}
			} catch(InterruptedException exc) {
				
				exc.printStackTrace();
			}
			
			if(isToLeft) {
				
				if(left_point != left_border + 1) {
					
					left_point--;
					
					for(int i = 0; i < pixel_matrix_default.length; i++) {
						
						int start = 0, end = 0;
						
						boolean isStart = false;
						
						for(int j = pixel_matrix_default[0].length - 1; j >= 0; j--) {
							
							if(!isStart) {
								
								if(pixel_matrix_default[i][j] == 1) {
									
									start = j;
									isStart = true;
								}	
							} else {
								
								if(pixel_matrix_default[i][j] == 0) {
									
									end = j;
									int tmp = pixel_matrix_default[i][start];
									pixel_matrix_default[i][start] = pixel_matrix_default[i][end];
									pixel_matrix_default[i][end] = tmp;
									isStart = false; start = end = 0;
								}	
							}
						}
					}
					
					updateScreen();
				} else {
					
					left_point--;
					
					for(int i = 0; i < pixel_matrix_default.length; i++) {
						
						int start = 0, end = 0;
						
						boolean isStart = false;
						
						for(int j = pixel_matrix_default[0].length - 1; j >= 0; j--) {
							
							if(!isStart) {
								
								if(pixel_matrix_default[i][j] == 1) {
									
									start = j;
									isStart = true;
								}	
							} else {
								
								if(pixel_matrix_default[i][j] == 0) {
									
									end = j;
									int tmp = pixel_matrix_default[i][start];
									pixel_matrix_default[i][start] = pixel_matrix_default[i][end];
									pixel_matrix_default[i][end] = tmp;
									isStart = false; start = end = 0;
								}	
							}
						}
					}
					
					updateScreen();
					
					isToLeft = false;
					right_point = width_character + 1;
				}
			} else {
				
				if(right_point != right_border - 1) {
					
					right_point++;
					
					for(int i = 0; i < pixel_matrix_default.length; i++) {
						
						int start = 0, end = 0;
						
						boolean isStart = false;
						
						for(int j = 0; j < pixel_matrix_default[0].length; j++) {
							
							if(!isStart) {
								
								if(pixel_matrix_default[i][j] == 1) {
									start = j;
									isStart = true;
								}	
							} else {
								
								if(pixel_matrix_default[i][j] == 0) {
									end = j;
									int tmp = pixel_matrix_default[i][end];
									pixel_matrix_default[i][end] = pixel_matrix_default[i][start];
									pixel_matrix_default[i][start] = tmp;
									isStart = false; start = end = 0;
								}	
							}
						}
					}
					
					updateScreen();
				} else {
					
					right_point++;
					
					for(int i = 0; i < pixel_matrix_default.length; i++) {
						
						int start = 0, end = 0;
						
						boolean isStart = false;
						
						for(int j = 0; j < pixel_matrix_default[0].length; j++) {
							
							if(!isStart) {
								
								if(pixel_matrix_default[i][j] == 1) {
									start = j;
									isStart = true;
								}	
							} else {
								
								if(pixel_matrix_default[i][j] == 0) {
									end = j;
									int tmp = pixel_matrix_default[i][end];
									pixel_matrix_default[i][end] = pixel_matrix_default[i][start];
									pixel_matrix_default[i][start] = tmp;
									isStart = false; start = end = 0;
								}	
							}
						}
					}
					
					updateScreen();
					
					isToLeft = true;
					left_point = right_border - width_character - 1;
				}
			}
		}
	}
	
	public abstract void paint(Graphics g);
	public abstract void run();
}
