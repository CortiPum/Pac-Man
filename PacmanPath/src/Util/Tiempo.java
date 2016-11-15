package Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Tiempo {

			
		private boolean reset = false;
		private long ms;
		private int second;
		private int minute;
		
		public Tiempo(long initTime){
			ms = initTime;
			minute = ((int) (ms/1000))/ 60;
			second = ((int) (ms/1000)) % 60;
		}
		
		public void refresh() {
			countDown();
			if(second <= 0 && minute <= 0) {
				minute = 0;
				second =0 ;
			}
			
			
		}
		
		private void countDown(){
			if (System.currentTimeMillis() - ms > 1000){
				second--;
				ms = System.currentTimeMillis();
				if (second == -1){
					minute--;
					second = 59;
				}
			}
		}	
		
		public void reset() {
			this.ms =  180000;
			minute = ((int) (ms/1000))/ 60;
			second = ((int) (ms/1000)) % 60;
		}
		
		public boolean tiempoCero(){
			return ((minute >= 0)&& (second>=0));
		}
		
		public String getTiempo(){
			String min = Long.toString(4-this.minute);
			String sec = Long.toString(60-this.second);
			return (min+":"+sec);
		}
		
		public void draw(Graphics2D g) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Bold", Font.BOLD, 30));
			
			g.drawString(Integer.toString(minute) + ":" + Integer.toString(second) , 330 - 20, 23);
		}
	}