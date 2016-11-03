package Util;
import Estados.Mode;

import MapaBuscador.*;
import Personaje.*;


public abstract class Fantasma extends Dinamico {

	
	protected Mode  modo;
	protected String nombre;
	protected Path caminoAsus;
	
	
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

public void mover (Pacman pac, Map mapa, Blinky blin){
	if (this.modo == Mode.PERSECUCION) estaPersecucion(pac, blin);
	if (this.modo == Mode.DISPERCION) estaDispercion();
	if (this.modo == Mode.ASUSTADO)  estaAsustado();
}

public  abstract void setPosInicial();

//fin getters y setters

public abstract void estaPersecucion(Pacman pac, Blinky blin );

public abstract void estaDispercion();

public void caminoAsus(Map mapaBuscador){
	Position esqRan = mapaBuscador.getEsquinaRandom();
	this.caminoAsus= PathFinder.findPath(this.pos.getX(),this.pos.getY(), esqRan.getX(), esqRan.getY());
}

public void estaAsustado(){
	if (this.caminoAsus == null){
		System.out.println("No hay camino posible");
	
		}
			else 
					System.out.print("Posicion actual:");
					System.out.println("(" + this.caminoAsus.getStep(0).getX() + ","+ this.caminoAsus.getStep(0).getY() + ")");
					this.setPos(new Position (caminoAsus.getStep(1).getX(), caminoAsus.getStep(1).getY()));
}


//hereda getter y setter de dinamico (getPos)
			
	
}
