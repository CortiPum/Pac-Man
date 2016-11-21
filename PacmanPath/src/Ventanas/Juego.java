package Ventanas;
import java.awt.Graphics;

/** Esta clase modela el juego.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class Juego implements Runnable {
	 
	public static boolean jugando;
	private Thread thread;
	private Ventana ventana;
    private SuperficieDibujo superficie;
    public static long time;
 
 /**
  * Crea la superficie dibujo y la ventana.
  */
 public Juego(){   
   superficie = new SuperficieDibujo();
   ventana = new Ventana("Pac-Man", superficie);
  }
  
 /**
  * Método para cuando termina la ejecución del juego.
  */
 public void stop(){
	try{
		thread.join();
		jugando = false;
	}catch(Exception e){
		e.printStackTrace();
	}
}
 
/**
 * Se encarga de iniciar.
 */
public void start(){
	thread = new Thread(this);
	jugando = true;
	thread.start();
}
   
/**
 * Se sobrescribe el método run, que se ponga a correr el juego.
 */
@Override
public void run() {
	Graphics g = ventana.getGraphics();
	while (jugando){
		time = System.currentTimeMillis();
		superficie.refresh();
		superficie.draw();
		try {
			thread.sleep(125);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//this.stop;
	System.exit(1);
}

/**
 * Main del juego.
 * @param args
 */
public static void main (String[] args){
    Juego jue = new Juego();
    jue.start();
}


}