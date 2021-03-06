package Util;

import java.awt.Graphics;

/** Esta es una clase abstracta que modela un personaje.
* @author Cortizas Tom�s ; Peraza Orlando.
* @version 2.0
*/
public abstract class Personaje {

	protected Identificador ID;
/**
 * 	
 * @return Devuelve el identificador del personaje.
 */
public Identificador getID(){
	return this.ID;
	}
/**
 * M�todo abstracto que deben implementar los hijos.	
 * @param g
 */
public abstract void draw(Graphics g);
	
}
	

	
