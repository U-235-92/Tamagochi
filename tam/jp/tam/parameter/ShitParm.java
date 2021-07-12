package jp.tam.parameter;

import java.awt.Color;
import java.awt.Graphics;

public class ShitParm extends Parameter {

	private static ShitParm shitParm;
	
	private ShitParm() {
		
		super();
		
		pixel_matrix = new int[][]
				{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	}
	
	public static ShitParm getParameter() {
		
		if(shitParm == null) {
			
			//1
//			counter++;
//			shitParm = new ShitParm(counter);
			//2
//			shitParm = new ShitParm(EIDMode.SHIT_MODE);
			//3
			shitParm = new ShitParm();
		}
		return shitParm;
	}
	
	@Override
	public void paint(Graphics g, Color color) {
		// TODO Auto-generated method stub
		g.setColor(color);
		
		for(int y = 0, l = 0; y < pixel_matrix.length; y += 1, l += 5) {
			
			for(int x = 0, k = 5; x < pixel_matrix[0].length; x += 1, k += 5) {
				
				if(pixel_matrix[y][x] == 1) {
					
					g.fillRect(k + 1, l + 1, 5 - 1, 5 - 1);
					
				} else if(pixel_matrix[y][x] == 2) {
					
					
				} 
			}
		}
	}

}
