package Util;

import java.io.Serializable;

public class Usuario implements Comparable<Usuario> {

	public String nombre;
	public int puntos;
	public String tiempo;
		
public Usuario(String nombre, int puntos, String tiempo){
	this.nombre = nombre;
	this.puntos = puntos;
	this.tiempo = tiempo;
}
	
public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public int getPuntos() {
	return puntos;
}

public void setPuntos(int puntos) {
	this.puntos = puntos;
}

public String getTiempo() {
	return tiempo;
}

public void setTiempo(String tiempo) {
	this.tiempo = tiempo;
}

@Override
public int compareTo(Usuario o) {
	return (o.getPuntos()-this.puntos);
}


	
	
}
