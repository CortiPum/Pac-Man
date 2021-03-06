package Util;
import java.awt.Image;

import MapaBuscador.*;
/** Esta clase modela un objeto con movimiento.
* @author Cortizas Tom�s ; Peraza Orlando.
* @version 2.0
*/

public abstract class Dinamico extends Personaje {

	
	protected Animacion[] iconos;
	protected int iconoActual;
	protected Image imagenActual;
	protected Position pos;
	
/**
 * Crea un arreglo de imagenes.	
 */
public Dinamico(){
	iconos = new Animacion[9];
}

/**
 * 	
 * @return Retorna una posici�n.
 */
public Position getPos(){
	return this.pos;
}
	
/**
 * Setea una posici�n.
 * @param pos
 */
public void setPos(Position pos){
	this.pos=pos;
}
	
/**
 * Setea la componente x de la posici�n.
 * @param x
 */
public void setX(int x){
	pos.setPositionX(x);
}

/**
 * Setea la componente y de la posici�n.
 * @param y
 */
public void setY(int y){
	pos.setPositionY(y);
}
	
	
}
