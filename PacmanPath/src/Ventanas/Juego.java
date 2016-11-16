package Ventanas;
import java.awt.Graphics;

public class Juego implements Runnable {
	 
	public static boolean jugando;
	private Thread thread;
	private Ventana ventana;
    private SuperficieDibujo superficie;
    public static long time;
 
 
 public Juego(){   
   superficie = new SuperficieDibujo();
   ventana = new Ventana("Pac-Man", superficie);
  }
   
 public void stop(){
	try{
		thread.join();
		jugando = false;
	}catch(Exception e){
		e.printStackTrace();
	}
}
 
public void start(){
	thread = new Thread(this);
	jugando = true;
	thread.start();
}
   
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

public static void main (String[] args){
    Juego jue = new Juego();
    jue.start();
}


}