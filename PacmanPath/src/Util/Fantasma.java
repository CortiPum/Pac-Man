package Util;
import Estados.Mode;

import MapaBuscador.*;
import Personaje.*;
/** Esta clase modela un objeto fantasma.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/

public abstract class Fantasma extends Dinamico {

	protected boolean asus;
	protected Mode  modo;
	protected String nombre;
	protected Path caminoAsus;
	
/**
 * Genera un fantasma.
 */
public Fantasma(){
	ID = Identificador.FANTASMA;
}
	
//Inicio getters y setters
/**
 * 	
 * @return Devuelve el nombre del fantasma.
 */
public String getNombre(){
	return this.nombre;
}

/**
 * 
 * @return REtorna el modo en el que se encuentra el fantasma.
 */
public Mode getModo(){
	return this.modo;
}

/**
 * Setea un nombre al fantasma.
 * @param name
 */
public void setNombre (String name){
	this.nombre= name;
}

/**
 * Setea un modo al fantasma.
 * @param modo
 */
public void setModo (Mode modo){
	this.modo = modo;
}

/**
 * Dependiendo en el modo en el que este se va a mover de una u otra manera.
 * @param pac
 * @param fantasma
 */
public void mover (Pacman pac, Fantasma fantasma){
	if (this.modo == Mode.PERSECUCION) estaPersecucion(pac, fantasma);
	if (this.modo == Mode.DISPERCION) estaDispercion();
	if (this.modo == Mode.ASUSTADO)  estaAsustado();
}

/**
 * Método abstracto que heredarán los hijos y deberan implementarlo.
 */
public  abstract void setPosInicial();

//fin getters y setters

/**
 * Método abstracto que heredarán los hijos y deberan implementarlo.
 * @param pac
 * @param fantasma
 */
public abstract void estaPersecucion(Pacman pac, Fantasma fantasma );

/**
 * Método abstracto que heredarán los hijos y deberan implementarlo.
 */
public abstract void estaDispercion();

/**
 * Crea el camino en caso de estar asustado.
 * @param mapaBuscador
 */
public void caminoAsus(Map mapaBuscador){
	Position esqRan = mapaBuscador.getEsquinaRandom();
	this.caminoAsus= PathFinder.findPath(this.pos.getX(),this.pos.getY(), esqRan.getX(), esqRan.getY());
}

/**
 * Recorre el camino asustado.
 */
public void estaAsustado(){
	if (this.caminoAsus == null){

		}else{
	if (this.caminoAsus.getSteps().isEmpty()){
		
	}
	else {
		this.setPos(new Position (caminoAsus.getStep(0).getX(), caminoAsus.getStep(0).getY()));
		this.caminoAsus.removeStep(0);
	}
}

}

//hereda getter y setter de dinamico (getPos)
}
