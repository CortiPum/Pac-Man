package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import MapaBuscador.*;
import MapaN.MapaGeneral;
import Personaje.*;
import Personaje.Pacman;


public class Juego implements Runnable {
	 
	private boolean jugando;
	private Thread thread;
	private Ventana ventana;
    private SuperficieDibujo superficie;
    
    //private TestPacman testPacman;
 
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
		superficie.refresh();
		superficie.draw();
		try {
			thread.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public static void main (String[] args){
    Juego jue = new Juego();
    jue.start();
}


}