package Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/** Esta clase modela el tiempo.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class Tiempo {

			
	private boolean reset = false;
	private long ms;
	private int second;
	private int minute;
/**
 * Recibe un tiempo en milisegundos y lo traduce a minutos y segundos.		
 * @param initTime
 */
public Tiempo(long initTime){
	ms = initTime;
	minute = ((int) (ms/1000))/ 60;
	second = ((int) (ms/1000)) % 60;
}

/**
 * Inicializa en cero y cuenta.
 */
public void refresh() {
	countDown();
	if(second <= 0 && minute <= 0) {
		minute = 0;
		second =0 ;
	}			
}
		
/**
 * Cuenta el tiempo.
 */
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

/**
 * REsetea el tiempo.
 */
public void reset() {
	this.ms =  180000;
	minute = ((int) (ms/1000))/ 60;
	second = ((int) (ms/1000)) % 60;
}

/**
 * 
 * @return Retorna false en el caso de que el tiempo se termine y true en el caso de que no se termine el tiempo.
 */
public boolean tiempoCero(){
	return ((minute >= 0)&& (second>=0));
}
/**
 * 
 * @return Retorna un tiempo.
 */
public String getTiempo(){
	String min = Long.toString(4-this.minute);
	String sec = Long.toString(60-this.second);
	return (min+":"+sec);
}

/**
 * Dibuja el contador que refleja el tiempo.
 * @param g
 */
public void draw(Graphics2D g) {
	g.setColor(Color.WHITE);
	g.setFont(new Font("Bold", Font.BOLD, 30));
	
	g.drawString(Integer.toString(minute) + ":" + Integer.toString(second) , 330 - 20, 23);
}
}