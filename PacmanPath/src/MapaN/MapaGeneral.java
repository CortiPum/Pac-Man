package MapaN;
import MapaBuscador.*;
import Util.CargaImagen;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Estaticos.*;

/** Esta clase modela el mapa del juego.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/

public class MapaGeneral {
	
	private Celda tablero[][]; 
	protected Image[] iconos;
	private int contadorObj;
/**
 * Genera un mapa general dependiendo el contenido de la clase map.
 */
	public MapaGeneral(){
		Map mapa= new Map ();
		contadorObj=0;
		iconos = new Image[7];
		tablero = new Celda[mapa.getWidth()][mapa.getHeight()]; 
		for (int i =0; i< mapa.getWidth();i++){ //i seran las filas
			for (int j=0; j<mapa.getHeight();j++){ //j columnas
				Position pos= new Position (i,j);
				switch (mapa.getObjectMap(i, j)){
				
				case 0: Vacio empty = new Vacio(i,j);
						tablero[i][j] = new Celda (pos, empty);
						break;
				case 1: Pared wall = new Pared(i,j);
						tablero[i][j] = new Celda(pos, wall);				
						break;
				
				case 3: Bola ball = new Bola(i,j);
						tablero[i][j] = new Celda (pos,ball);
						contadorObj++;
						break;
						
				case 4: PowerBall power = new PowerBall(i,j);
						tablero[i][j] = new Celda (pos, power);
						contadorObj++;
						break;
						
				case 5: Tunel tun = new Tunel (i,j);
						tablero[i][j] = new Celda (pos, tun);
						break;
						
						
				}
			}
		}
		
	}
	
/**
 * Resetea el mapa, generando todo de nuevo.
 */
public void reset(){
	Map mapa= new Map ();
	contadorObj=0;
	iconos = new Image[7];
	tablero = new Celda[mapa.getWidth()][mapa.getHeight()]; 
	for (int i =0; i< mapa.getWidth();i++){ //i seran las filas
		for (int j=0; j<mapa.getHeight();j++){ //j columnas
			Position pos= new Position (i,j);
			switch (mapa.getObjectMap(i, j)){
			
			case 0: Vacio empty = new Vacio(i,j);
					tablero[i][j] = new Celda (pos, empty);
					break;
			case 1: Pared wall = new Pared(i,j);
					tablero[i][j] = new Celda(pos, wall);				
					break;
			
			case 3: Bola ball = new Bola(i,j);
					tablero[i][j] = new Celda (pos,ball);
					contadorObj++;
					break;
					
			case 4: PowerBall power = new PowerBall(i,j);
					tablero[i][j] = new Celda (pos, power);
					contadorObj++;
					break;
					
			case 5: Tunel tun = new Tunel (i,j);
					tablero[i][j] = new Celda (pos, tun);
					break;
					
					
			}
		}
	}
}
/**
 * 
 * @return Retorna la cantidad de bolas y PowerPellets.
 */
public int objTotal(){
	return (this.contadorObj);
}
	
/**
 * @return Retorna la matriz.
 */
public Celda[][] getTablero() {
	return tablero;
}
/**
 * Setea una matriz.
 * @param tablero
 */
public void setTablero(Celda[][] tablero) {
	this.tablero = tablero;
}
/**
 * 	
 * @param pos
 * @return Dada una posición devuelve la celda que hace referencia a esa posición en el mapaGeneral.
 */
public Celda getCelda(Position pos){
	int x = pos.getX();
	int y = pos.getY();
	return tablero[x][y];
}
/**
 * 	
 * @return Devuelve la cantidad de puntos que puede ganar un jugador en el mapa. En base a esta cantidad se calculan porcentajes, los cuales se utilizan para la entrada de algunos fantasmas al juego.
 */
public int getPuntajeTotal(){ 
	Map mapa = new Map();
	int total=0;
	for (int i =0; i< mapa.getHeight();i++){
		for (int j=0; j<mapa.getWidth();j++){
			if (mapa.getObjectMap(i, j) == 3)
				total=total+10;	//pacdots
				if (mapa.getObjectMap(i, j)== 4)
					total = total+50 ;//powerball
				}
		}
		return total;
}	
/**
 * A través de este método son llamados los distintos métodos draw de los distintos objetos que extiendan de Personaje. Dado que cada celda tenemos un objeto de tipo personaje.
 * @param g
 */
public void draw (Graphics g){ 
	Map map = new Map();
	for (int x = 0; x < map.getWidth(); x++){
    	for (int y = 0; y < map.getHeight(); y++){
    		Position pos = new Position (x,y);
    		this.getCelda(pos).getContenido().draw(g);
    }
    	}
	}

}


/*el metodo draw va a recorrer el mapa de objetos ya creado. En el for, agarra cada celda,
 *  y va preguntando por el objeto que esta contenido en esa celda, para despues
 *  llamar al draw de ese objeto, ejemplo, si en la celda 0, 0 hay una pared
 */