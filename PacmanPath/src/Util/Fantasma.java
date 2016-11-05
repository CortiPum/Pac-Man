package Util;
import Estados.Mode;

import MapaBuscador.*;
import Personaje.*;


public abstract class Fantasma extends Dinamico {

	
	protected Mode  modo;
	protected String nombre;
	protected Path caminoAsus;
	
public Fantasma(){
	ID = Id.FANTASMA;
}
	
//Inicio getters y setters
	
public String getNombre(){
	return this.nombre;
}

public Mode getModo(){
	return this.modo;
}

public void setNombre (String name){
	this.nombre= name;
}

public void setModo (Mode modo){
	this.modo = modo;
}

public void mover (Pacman pac, Fantasma fantasma){
	if (this.modo == Mode.PERSECUCION) estaPersecucion(pac, fantasma);
	if (this.modo == Mode.DISPERCION) estaDispercion();
	if (this.modo == Mode.ASUSTADO)  estaAsustado();
}

public  abstract void setPosInicial();

//fin getters y setters

public abstract void estaPersecucion(Pacman pac, Fantasma fantasma );

public abstract void estaDispercion();

public void caminoAsus(Map mapaBuscador){
	Position esqRan = mapaBuscador.getEsquinaRandom();
	System.out.println(esqRan.getX());
	System.out.println(esqRan.getY());
	this.caminoAsus= PathFinder.findPath(this.pos.getX(),this.pos.getY(), esqRan.getX(), esqRan.getY());
}

public void estaAsustado(){
	if (this.caminoAsus == null){
		System.out.println("No hay camino posible");
	
		}
	if (this.caminoAsus.getSteps().isEmpty()){
		System.out.println("Quedo Vacio");
	}
	else {
					System.out.print("Posicion actual:");
					System.out.println("(" + this.caminoAsus.getStep(0).getX() + ","+ this.caminoAsus.getStep(0).getY() + ")");
					this.setPos(new Position (caminoAsus.getStep(0).getX(), caminoAsus.getStep(0).getY()));
					this.caminoAsus.removeStep(0);
}
}

//hereda getter y setter de dinamico (getPos)
public void refresh(Pacman pac, Fantasma fantasma){
	this.mover(pac, fantasma);
}
	
}
