package EstadoJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Controlador.Teclado;
import MapaBuscador.Map;
import MapaN.MapaGeneral;
import Personaje.Blinky;
import Personaje.Clyde;
import Personaje.Inky;
import Personaje.Pacman;
import Personaje.Pinky;
import Util.Tiempo;

public class Jugar implements EstadoJuego {

	private Tiempo tiempo;
	private Pacman pacman;
	private Blinky blinky;
	private Pinky pinky;
	private Inky inky;
	private Clyde clyde;
	private Map map;
	private MapaGeneral mapa;
	private static Jugar jugar = new Jugar();
	
public Jugar(){
	
	tiempo = new Tiempo(300000);
	blinky = new Blinky();
    clyde = new Clyde();
    inky = new Inky();
    pinky = new Pinky();
    pacman = new Pacman(23, 13);
    map = new Map();
    mapa = new MapaGeneral();
    
  
}

public void restart(){
	tiempo.reset();
	blinky.reset();
	clyde.reset();
	inky.reset();
	pinky.reset();
	pacman.reset();
    mapa.reset();
}

@Override
public void draw (Graphics2D g){
	//dibuja cuantas vidas le quedan al pacman
	if (pacman.getVidas()==3){
		g.drawImage(pacman.getImg(),580, 0, null);
		g.drawImage(pacman.getImg(),603, 0, null);
		g.drawImage(pacman.getImg(),626, 0, null);
	}
	
	if (pacman.getVidas()==2){
		g.drawImage(pacman.getImg(),580, 0, null);
		g.drawImage(pacman.getImg(),603, 0, null);
	}
	
	if (pacman.getVidas()==1){
		g.drawImage(pacman.getImg(),580, 0, null);
	}
	
	g.setColor(Color.WHITE);
	g.setFont(new Font("Tahoma",Font.BOLD,30));
	
	//tiempo

	
	//dibuja el puntaje actual del pacman
	Integer pun = pacman.getPuntaje();
	String puntos = pun.toString();
	g.setColor(Color.WHITE);
	g.drawString(puntos, 23,23);
	//
		
	mapa.draw(g);
	
	
	
	pacman.draw(g);
	blinky.draw(g);
	pinky.draw(g);
	inky.draw(g);
	clyde.draw(g);
	
	g.setColor(Color.WHITE);
	g.setFont(new Font("Tahoma",Font.BOLD,20));
	if ((pacman.getVidas() == 0)|| (!(tiempo.tiempoCero()))){
		String perdiste = new String ("   You Lose");
		g.drawString(perdiste,12*23, 19*23 );
	}
	
	if(pacman.getCantObjetosComidos() == mapa.objTotal()){
		String ganaste = new String ("   You Win");
		g.drawString(ganaste,12*23, 19*23 );
	}
	
	//tiempo
	
	if(!(tiempo.tiempoCero())){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Bold", Font.BOLD, 30));
		g.drawString("0" + ":" + "0" , 330 - 20, 23);
	}else{
		tiempo.draw(g);
	}
}
public static Jugar getJugar(){
	return jugar;
}

@Override
public void refresh() {
	if ((Teclado.pause))
		ArregloEstados.cambiarEstado(3);
	else{
		if ((pacman.puedeJugar()) && (pacman.getCantObjetosComidos() != mapa.objTotal()) && ((tiempo.tiempoCero())) ){
			//clyde.estaPersecucion(pacman, blinky);
			//blinky.estaPersecucion(pacman, blinky);
			//pinky.estaPersecucion(pacman, blinky);
			//inky.estaPersecucion(pacman, blinky);
		
			blinky.estaDispercion();
			inky.estaDispercion();
			clyde.estaDispercion();
			pinky.estaDispercion();
			pacman.refresh(map, mapa,blinky, inky, clyde, pinky);
			tiempo.refresh();
	
}}
	
}
}
