package Ventanas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import Controlador.Teclado;
import Personaje.*;

import MapaBuscador.Map;
import MapaN.MapaGeneral;

public class SuperficieDibujo extends Canvas{

	private Pacman pacman;
	private Blinky blinky;
	private Pinky pinky;
	private Inky inky;
	private Clyde clyde;
	
	private Teclado teclado;
	
	private Map map = new Map();
	private MapaGeneral mapa = new MapaGeneral();
	

public SuperficieDibujo(){
	teclado = new Teclado();
	this.addKeyListener(teclado);
	this.setFocusable(true);
	
	blinky = new Blinky();
    clyde = new Clyde();
    inky = new Inky();
    pinky = new Pinky();
    pacman = new Pacman(23, 13);
    this.setIgnoreRepaint(false);
    
   
}
	
public void draw(){
	
	BufferStrategy buffer = this.getBufferStrategy();
	if (buffer == null){
		this.createBufferStrategy(3);  //es cola de imagenes en espera, atiende siempre al primero
		return;
	}
	Graphics2D g = (Graphics2D)buffer.getDrawGraphics() ;
	g.fillRect(0, 0, 900, 800);
	mapa.draw(g);
	pacman.draw(g);
	blinky.draw(g);
	pinky.draw(g);
	inky.draw(g);
	clyde.draw(g);
	//blinky.mover(pacman, blinky);
	//blinky.estaDispercion();
	//inky.estaDispercion();
	//clyde.estaDispercion();
	//pinky.estaDispercion();
	//blinky.cambioEstado(true, map);
	Toolkit.getDefaultToolkit().sync(); //cordina el monitor con el dibujo (da compatibilidad, dibuja cuando refresca)
	g.dispose();
	buffer.show();
}

public void refresh(){
	
	if (pacman.puedeJugar()){
	
	//clyde.estaPersecucion(pacman, blinky);
	//blinky.estaPersecucion(pacman, blinky);
	//pinky.estaPersecucion(pacman, blinky);
	//inky.estaPersecucion(pacman, blinky);
	//blinky.estaDispercion();
	//inky.estaDispercion();
	//clyde.estaDispercion();
	//pinky.estaDispercion();
	teclado.refresh();
	pacman.refresh(map, mapa,blinky, inky, clyde, pinky);
	}
}
}

