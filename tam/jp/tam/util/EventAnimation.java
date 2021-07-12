package jp.tam.util;

import jp.tam.swing.JGamePanel;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;

public class EventAnimation implements Runnable {

	private final static int R = 0x00d20000; 
	private final static int G = 0x0000b400;
	private final static int B = 0x0000008c;
	
	public final static int RGB = R | G | B;
	
	private int[][] matrix_anim_shit;
	private int[][] matrix_anim_mood;
	private int[][] matrix_anim_feed;
	private int[][] matrix_anim_zzz;
	
	private List<Pixel> shit_pixel_list;
	private List<Pixel> mood_pixel_list;
	private List<Pixel> feed_pixel_list;
	private List<Pixel> zzz_pixel_list;
	
	private EventGenerator eventGenerator;
	
	private JGamePanel jGamePNL;
	
	private volatile boolean isAllowShowAnimation = false;
	
	public EventAnimation(EventGenerator eventGenerator, JGamePanel jGamePNL) {
		
		this.eventGenerator = eventGenerator;
		this.jGamePNL = jGamePNL;
		
		shit_pixel_list = new ArrayList<>();
		mood_pixel_list = new ArrayList<>();
		feed_pixel_list = new ArrayList<>();
		zzz_pixel_list = new ArrayList<>();
		
		matrix_anim_shit = new int[][] 
				{{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
				 
		matrix_anim_mood = new int[][] 
				{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
				 
		matrix_anim_feed = new int[][] 
				{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
				
		matrix_anim_zzz = new int[][] 
				{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
				
		for(int i = 0; i < matrix_anim_feed.length; i++) {
			
			for(int j = 0; j < matrix_anim_feed[0].length; j++) {
				
				if(matrix_anim_feed[i][j] == 1) {
					
					feed_pixel_list.add(new Pixel(j, i, 1));
				}
				if(matrix_anim_zzz[i][j] == 1) {
								
					zzz_pixel_list.add(new Pixel(j, i, 1));
				}
				if(matrix_anim_zzz[i][j] == 2) {
					
					zzz_pixel_list.add(new Pixel(j, i, 2));
				}
				if(matrix_anim_shit[i][j] == 1) {
					
					shit_pixel_list.add(new Pixel(j, i, 1));
				}
				if(matrix_anim_shit[i][j] == 2) {
					
					shit_pixel_list.add(new Pixel(j, i, 2));
				}
				if(matrix_anim_mood[i][j] == 1) {
					
					mood_pixel_list.add(new Pixel(j, i, 1));
				}
			}
		}
	}
	
	public boolean isAllowShowAnimation() {
		return isAllowShowAnimation;
	}

	public void setAllowShowAnimation() {
		
		isAllowShowAnimation = true;
	}

	public EventGenerator getEventGenerator() {
		
		return eventGenerator;
	}
	
	public JGamePanel getJGamePanel() {
		
		return jGamePNL;
	}
	
	@Override
	public void run() {
		
		if(isAllowShowAnimation) {
			
			switch(eventGenerator.getAnimationCode()) { //switch(eventGenerator.getCurrentEventCode())
			
			case EventGenerator.FEED_EVENT:
				
				getAnimFeed();
				break;
			case EventGenerator.SHIT_EVENT:
				
				getAnimShit();
				break;
			case EventGenerator.MOOD_EVENT:
				
				getAnimMood();
				break;
			case EventGenerator.ZZZ_EVENT:
				
				getAnimZzz();
				break;
			}
		}
	}

	private void getAnimShit() {
		
		int[][] matrix_anim_shit_copy = new int[matrix_anim_shit.length][matrix_anim_shit[0].length];
		
		for(int i = 0; i < matrix_anim_shit_copy.length; i++) {
			
			for(int j = 0; j < matrix_anim_shit_copy[0].length; j++) {
				
				matrix_anim_shit_copy[i][j] = matrix_anim_shit[i][j];
			}
		}
		
		List<Pixel> shit_pixel_list_copy = new ArrayList<>();
		
		List<Pixel> pixel_color_type_1_list = new ArrayList<>();
		List<Pixel> pixel_color_type_2_list = new ArrayList<>();
		
		List<Pixel> pixel_to_remove_list = new ArrayList<>();
		
		shit_pixel_list_copy.addAll(shit_pixel_list);
		
		int rigth_pixel = 0;
		
		while(rigth_pixel++ <= matrix_anim_shit[0].length) {
						
			for(Pixel pixel : shit_pixel_list_copy) {
				
				if(pixel.num_paint == 2) {
					
					pixel.x++;
					pixel_color_type_2_list.add(pixel);
					
				} else if(pixel.num_paint == 1) {
					
					pixel_color_type_1_list.add(pixel);
				}
			}
			
			for(Pixel p1 : pixel_color_type_1_list) {
				
				for(Pixel p2 : pixel_color_type_2_list) {
					
					if(p1.x == p2.x && p1.y == p2.y) {
						
						pixel_to_remove_list.add(p1);
					}
				}
			}
			
			if(pixel_to_remove_list.size() != 0) {
			
				shit_pixel_list_copy.removeAll(pixel_to_remove_list);
			}
			
			//изменить матрицу
			
			for(int i = 0; i < matrix_anim_shit.length; i++) {
				
				for(int j = 0; j < matrix_anim_shit[0].length; j++) {
					
					matrix_anim_shit[i][j] = 0;
					
					for(Pixel pixel : shit_pixel_list_copy) {
						
						if(pixel.x == j && pixel.y == i && pixel.num_paint == 1) {
							
							matrix_anim_shit[i][j] = 1;
						} else if(pixel.x == j && pixel.y == i && pixel.num_paint == 2) {
							
							matrix_anim_shit[i][j] = 2;
						} 
					}
				}
			}
			
			EventQueue.invokeLater(() -> {
				
				jGamePNL.repaint();
			});
			
			try {
				
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException exc) {
				
				exc.printStackTrace();
			}
			
			pixel_to_remove_list.removeAll(pixel_to_remove_list);
			pixel_color_type_1_list.removeAll(pixel_color_type_1_list);
			pixel_color_type_2_list.removeAll(pixel_color_type_2_list);
		}
		
		matrix_anim_shit = matrix_anim_shit_copy;
		
		shit_pixel_list.removeAll(shit_pixel_list);
		
		for(int i = 0; i < matrix_anim_shit.length; i++) {
			
			for(int j = 0; j < matrix_anim_shit[0].length; j++) {
				
				if(matrix_anim_shit[i][j] == 1) {
					
					shit_pixel_list.add(new Pixel(j, i, 1));
				}
				if(matrix_anim_shit[i][j] == 2) {
					
					shit_pixel_list.add(new Pixel(j, i, 2));
				}
			}
		}
		
		isAllowShowAnimation = false;
		
		eventGenerator.setAllowGenerateEvent();
		
		synchronized(eventGenerator) {
			
			eventGenerator.notifyAll();
		}
		
		EventQueue.invokeLater(() -> {
			
			jGamePNL.repaint();
		});
	}
	
	private void getAnimMood() {
		
		int timeout = 3;
		
		while(timeout-- != 0) {
			
			EventQueue.invokeLater(() -> {
				
				jGamePNL.repaint();
			});
			
			try {
				
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch(InterruptedException exc) {
				
				exc.printStackTrace();
			}
		}
		
		isAllowShowAnimation = false;
		
		eventGenerator.setAllowGenerateEvent();
		
		synchronized(eventGenerator) {
			
			eventGenerator.notifyAll();
		}
		
		EventQueue.invokeLater(() -> {
			
			jGamePNL.repaint();
		});
	}
	
	private void getAnimFeed() {
		
		int[][] matrix_anim_feed_copy = new int[matrix_anim_feed.length][matrix_anim_feed[0].length];
		
		for(int i = 0; i < matrix_anim_feed_copy.length; i++) {
			
			for(int j = 0; j < matrix_anim_feed_copy[0].length; j++) {
				
				matrix_anim_feed_copy[i][j] = matrix_anim_feed[i][j];
			}
		}
		
		List<Pixel> feed_pixel_list_copy = new ArrayList<>();
		
		feed_pixel_list_copy.addAll(feed_pixel_list);
		
		int timout = 5;
		
		EventQueue.invokeLater(() -> {
			
			jGamePNL.repaint();
		});
		
		try {
			
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch(InterruptedException exc) {
			
			exc.printStackTrace();
		}
		
		while(timout-- != 1) {
			
			int c = (int) (feed_pixel_list.size() / timout);
			
			for(int i = 0; i < c; i++) {

				if(timout != 0) {
					
					int rnd = (int) (Math.random() * ((feed_pixel_list.size() - 1) + 1));
					feed_pixel_list.remove(rnd);
				} 
			}
			
			for(int i = 0; i < matrix_anim_feed.length; i++) {
				
				for(int j = 0; j < matrix_anim_feed[0].length; j++) {
					
					matrix_anim_feed[i][j] = 0;
					
					for(Pixel pixel : feed_pixel_list) {
						
						if(pixel.x == j && pixel.y == i && pixel.num_paint == 1) {
							
							matrix_anim_feed[i][j] = 1;
						} 
					}
				}
			}
			
			EventQueue.invokeLater(() -> {
				
				jGamePNL.repaint();
			});
			
			try {
				
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch(InterruptedException exc) {
				
				exc.printStackTrace();
			}
		}
		
		for(int i = 0; i < matrix_anim_feed_copy.length; i++) {
			
			for(int j = 0; j < matrix_anim_feed_copy[0].length; j++) {
				
				matrix_anim_feed[i][j] = matrix_anim_feed_copy[i][j];
			}
		}
		
		feed_pixel_list.removeAll(feed_pixel_list);
		
		feed_pixel_list.addAll(feed_pixel_list_copy);
		
		isAllowShowAnimation = false;
		
		eventGenerator.setAllowGenerateEvent();
		
		synchronized(eventGenerator) {
			
			eventGenerator.notifyAll();
		}
		
		EventQueue.invokeLater(() -> {
			
			jGamePNL.repaint();
		});
	}
	
	private void getAnimZzz() {
		
		int timout = 3;
		
		int[][] matrix_anim_zzz_copy = new int[matrix_anim_zzz.length][matrix_anim_zzz[0].length];
		
		for(int i = 0; i < matrix_anim_zzz_copy.length; i++) {
			
			for(int j = 0; j < matrix_anim_zzz_copy[0].length; j++) {
				
				matrix_anim_zzz_copy[i][j] = matrix_anim_zzz[i][j];
			}
		}
		
		List<Pixel> zzz_pixel_list_copy = new ArrayList<>();
		
		List<Pixel> pixel_color_type_1_list = new ArrayList<>();
		List<Pixel> pixel_color_type_2_list = new ArrayList<>();
		
		for(Pixel pixel : zzz_pixel_list) {
			
			if(pixel.num_paint == 1) {
				
				pixel_color_type_1_list.add(pixel);
			} else if(pixel.num_paint == 2) {
				
				pixel_color_type_2_list.add(pixel);
			}
		}
		
		zzz_pixel_list_copy.addAll(zzz_pixel_list);
		
		zzz_pixel_list.removeAll(zzz_pixel_list);
		
		while(timout-- != 0) {
			
			for(int i = 0; i < matrix_anim_zzz.length; i++) {
				
				for(int j = 0; j < matrix_anim_zzz[0].length; j++) {
					
					matrix_anim_zzz[i][j] = 0;
					
					for(Pixel pixel : zzz_pixel_list) {
						
						if(pixel.x == j && pixel.y == i && pixel.num_paint == 1) {
							
							matrix_anim_zzz[i][j] = 1;
						} 
						if(pixel.x == j && pixel.y == i && pixel.num_paint == 2) {
							
							matrix_anim_zzz[i][j] = 2;
						}
					}
				}
			}
			
			EventQueue.invokeLater(() -> {
				
				jGamePNL.repaint();
			});
			
			try {
				
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch(InterruptedException exc) {
				
				exc.printStackTrace();
			}
			
			if(timout == 2) {
				
				zzz_pixel_list.addAll(pixel_color_type_1_list);
			} else if(timout == 1) {
				
				zzz_pixel_list.addAll(pixel_color_type_2_list);
			}
		}
		
		zzz_pixel_list.removeAll(zzz_pixel_list);
		
		zzz_pixel_list.addAll(zzz_pixel_list_copy);
		
		isAllowShowAnimation = false;
		
		eventGenerator.setAllowGenerateEvent();
		
		synchronized(eventGenerator) {
			
			eventGenerator.notifyAll();
		}
		
		EventQueue.invokeLater(() -> {
			
			jGamePNL.repaint();
		});
	}
	
	public void paint(Graphics g) {
		
		switch(eventGenerator.getAnimationCode()) {
		
		case EventGenerator.FEED_EVENT:
			
			for(int y = 0, l = 15; y < matrix_anim_feed.length; y += 1, l += 15) {
				
				for(int x = 0, k = 15; x < matrix_anim_feed[0].length; x += 1, k += 15) {
					
					if(matrix_anim_feed[y][x] == 1) {
						
						g.setColor(new Color(RGB));
						g.fillRect(k + 2, l + 2, 15 - 2, 15 - 2);
						
					} 
				}
			}
			
			break;
			
		case EventGenerator.MOOD_EVENT:
			
			for(int y = 0, l = 15; y < matrix_anim_mood.length; y += 1, l += 15) {
				
				for(int x = 0, k = 15; x < matrix_anim_mood[0].length; x += 1, k += 15) {
					
					if(matrix_anim_mood[y][x] == 1) {
						
						g.setColor(new Color(RGB));
						g.fillRect(k + 2, l + 2, 15 - 2, 15 - 2);
						
					} 
				}
			}
			
			break;
			
		case EventGenerator.SHIT_EVENT:
			
			for(int y = 0, l = 15; y < matrix_anim_shit.length; y += 1, l += 15) {
				
				for(int x = 0, k = 15; x < matrix_anim_shit[0].length; x += 1, k += 15) {
					
					if(matrix_anim_shit[y][x] == 1 || matrix_anim_shit[y][x] == 2) {
						
						g.setColor(new Color(RGB));
						g.fillRect(k + 2, l + 2, 15 - 2, 15 - 2);
						
					} 
				}
			}
			
			break;
		
		case EventGenerator.ZZZ_EVENT:
			
			for(int y = 0, l = 15; y < matrix_anim_zzz.length; y += 1, l += 15) {
				
				for(int x = 0, k = 15; x < matrix_anim_zzz[0].length; x += 1, k += 15) {
					
					if(matrix_anim_zzz[y][x] == 1 || matrix_anim_zzz[y][x] == 2) {
						
						g.setColor(new Color(RGB));
						g.fillRect(k + 2, l + 2, 15 - 2, 15 - 2);
						
					} 
				}
			}
			
			break;
		}
	}
	
	public class Pixel {
		
		private int x;
		private int y;
		private int num_paint;
		
		public Pixel(int x, int y, int num_paint) {
			
			this.x = x;
			this.y = y;
			this.num_paint = num_paint;
		}
		
		public int getX() {
			
			return x;
		}
		
		public int getY() {
			
			return y;
		}
		
		public int getNumberPaint() {
			
			return num_paint;
		}
	}
}
