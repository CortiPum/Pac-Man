package Util;

import java.io.Serializable;
/** Esta clase modela un jugador. Utilizado para almacenar al jugador en ranking.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class Usuario implements Comparable<Usuario> {

	public String nombre;
	public int puntos;
	public String tiempo;

/**
 * Genera un jugador.
 * @param nombre
 * @param puntos
 * @param tiempo
 */
public Usuario(String nombre, int puntos, String tiempo){
	this.nombre = nombre;
	this.puntos = puntos;
	this.tiempo = tiempo;
}
	
/**
 * 
 * @return Retorna el nombre de un jugador.
 */
public String getNombre() {
	return nombre;
}

/**
 * Setea un nombre al jugador.
 * @param nombre
 */
public void setNombre(String nombre) {
	this.nombre = nombre;
}

/**
 * 
 * @return Retorna los puntos de un jugador.
 */
public int getPuntos() {
	return puntos;
}

/**
 * Setea los puntos de un jugador.
 * @param puntos
 */
public void setPuntos(int puntos) {
	this.puntos = puntos;
}

/**
 * 
 * @return Retorna el tiempo que realizó el jugador.
 */
public String getTiempo() {
	return tiempo;
}

/**
 * Setea un tiempo a un jugador.
 * @param tiempo
 */
public void setTiempo(String tiempo) {
	this.tiempo = tiempo;
}
/**
 *Se sobrescribe el método para comparar por puntaje.
 */
@Override
public int compareTo(Usuario o) {
	return (o.getPuntos()-this.puntos);
}


	
	
}
