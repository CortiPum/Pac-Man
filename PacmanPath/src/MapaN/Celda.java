package MapaN;
import MapaBuscador.*;
import Util.Identificador;
import Util.Personaje;
import Estaticos.*;

/**
 * Esta clase modela una Celda.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */

public class Celda {

	private Position pos;
	private Personaje contenido;

/**
 *Crea una celda asignandole un contenido de tipo personaje y una posicion en el mapa. 
*/
public Celda(Position pos, Personaje contenido){
	this.pos=pos;
	this.contenido=contenido;
}

/**
 * retorna la posicion de la celda
 */
public Position getPosicion (){
	return pos;
}
/**
 * retorna el contenido de la celda
 */
public Personaje getContenido(){
	return contenido;
}

/**
 * setea la posicion de la celda
 */

public void setPosicion(Position pos){
	this.pos= pos;
}
/**
 * setea el contenido en de la celda
 */
public void setContenido(Personaje contenido){
	this.contenido=contenido;
}
	//metodos para preguntar que hay en la celda


/**
 * pregunta si el contenido de la celda es una bola
 */
public boolean hayBola(){
	return (contenido.getID()==Identificador.BOLA);
}

/**
 * pregunta si el contenido de la celda es una pared.
 */
public boolean hayPared(){
	return (contenido.getID()==Identificador.PARED);
}

/**
 * pregunta si el contenido de la celda hay vacio.
 */
public boolean hayVacio(){
	return (contenido.getID()==Identificador.VACIO);
}

/**
 * pregunta si el contenido de la celda e suna powerpellet.
 */
public boolean hayPowerBall(){
	return (contenido.getID()==Identificador.POWERBALL);
}

/**
 * pregunta si el contenido de la celda es un tunel.
 */
public boolean hayTunel(){
	return (contenido.getID()==Identificador.TUNEL);
}


//genera vacio cuando el pacman come una pelotita de poder o un powerpellet

/**
 * setea vacio en la celda de la posicion pasada por parametro, solo entrara cuando el pacman come bola de poder, o powerpellet, para generar vacio, donde antes hubo un objeto.
 */
public void generoVacio(){
	Vacio vac = new Vacio(this.pos.getX(), this.pos.getY());
	this.setContenido(vac);
}


}

