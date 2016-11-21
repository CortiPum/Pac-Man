package EstadoJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Controlador.Mouse;
import Controlador.Teclado;
import MapaBuscador.Map;
import MapaN.MapaGeneral;
import Personaje.Blinky;
import Personaje.Clyde;
import Personaje.Inky;
import Personaje.Pacman;
import Personaje.Pinky;
import Util.Ranking;
import Util.Tiempo;

/**
 * Esta clase modela el  estado del juego "Jugar".
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */

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
	public static long tiem;
	private boolean aux;
	
public Jugar(){
	
	aux = true;
	tiempo = new Tiempo(300000);
	blinky = new Blinky();
    clyde = new Clyde();
    inky = new Inky();
    pinky = new Pinky();
    pacman = new Pacman(23, 13);
    map = new Map();
    mapa = new MapaGeneral();
    tiem = System.currentTimeMillis();
}
/**
 * El juego se resetea en caso de terminar la partida (pulsando el boton exit) o cuando se hace un reset desde la pausa.
 */
public void restart(){  
	aux=true;
	tiempo = new Tiempo(300000);
	blinky.reset();
	clyde.reset();
	inky.reset();
	pinky.reset();
	pacman.reset();
    mapa.reset();
    tiem = System.currentTimeMillis();
}

/**
 * Dibuja cuantas vidas le quedan al pacman.
 */
@Override
public void draw (Graphics2D g){
	
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
	
	//dibuja el puntaje actual del pacman
	Integer pun = pacman.getPuntaje();
	String puntos = pun.toString();
	g.setColor(Color.WHITE);
	g.drawString(puntos, 23,23);
	//dibuja el mapa
	mapa.draw(g);
	//dibuja los personajes
	pacman.draw(g);
	blinky.draw(g);
	pinky.draw(g);
	inky.draw(g);
	clyde.draw(g);
	
	
	//dibuja mensajes en pantalla en caso de ganar o perder.
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
	
	//dibuja el tiempo
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
/**
 * Actualiza el estado del juego de acuerdo a lo que suceda.
 */
@Override
public void refresh() {
	Mouse.refresh();
	if ((Teclado.pause))
		ArregloEstados.cambiarEstado(3);
	else{
		if ((pacman.puedeJugar()) && (pacman.getCantObjetosComidos() != mapa.objTotal()) && ((tiempo.tiempoCero())) ){
			blinky.refresh(pacman, map);
			clyde.refresh(pacman, map, mapa);
			inky.refresh(pacman, map);
			pinky.refresh(pacman, map);
			pacman.refresh(map, mapa,blinky, inky, clyde, pinky);
			tiempo.refresh();
		}else{
			if ( aux ){
				Ranking.getRanking().guardarPuntaje(pacman.getPuntaje(), tiempo.getTiempo());
				aux=false;
			}
			ArregloEstados.cambiarEstado(0);
			this.restart();
			}
	}
	}
}

