package Personaje;

import Estados.*;
import MapaBuscador.*;
import Util.Fantasma;
import Util.Id;

public class Blinky extends Fantasma { 

	
	//heredan getters y setters de fantasma
	
public Blinky(){
	this.ID=Id.BLINKY;
	this.nombre="Blinky"; 
	this.modo= Mode.PERSECUCION;
	this.pos = new Position (11 , 13); //inicializar bien la ubicacion del fantasma		
}

//mover esta en fantasma (controla que movimiento hara)



public void estaPersecucion(Pacman pac, Blinky blin){
	int xpac = pac.getPos().getX();
	int ypac =pac.getPos().getY();
	int xpos=this.pos.getX();
	int ypos=this.pos.getY();
	Path caminoB = PathFinder.findPath(xpos,ypos, xpac, ypac);
	if (caminoB == null){
		System.out.println("No hay camino posible");
	
		}
			else 
					System.out.print("Posicion actual:");
					System.out.println("(" + caminoB.getStep(0).getX() + ","+ caminoB.getStep(0).getY() + ")");
					this.setPos(new Position (caminoB.getStep(1).getX(), caminoB.getStep(1).getY())); //actualiza la posicion del fantasma a su posicion siguiente en el arreglo		
					
}



public void estaDispercion(){
	Path caminoA = null;
	Path caminoB = null;
	Path caminoC = null;
	Path caminoD = null;
	
	
		if (((this.pos.getX() <5) && (this.pos.getY() < 24) && (this.pos.getY() >= 21)) ){
			caminoB = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 1, 24);
			this.moverDis(caminoB);
		}else
		
			if ((this.pos.getX() >= 1) && (this.pos.getX()<5) && (this.pos.getY() >= 24)){
				caminoC = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5, 27);
				this.moverDis(caminoC);
			}else
				if ((this.pos.getX() == 5) && (this.pos.getY() <= 26) &&(this.pos.getY() >21) ){
					caminoD = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5, 21);
					this.moverDis(caminoD);
				}else
					if ((this.pos.getX() != 5) &&(this.pos.getY() != 21)){ 
						caminoA = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5 ,21 );
						this.moverDis(caminoA);
						}
}

private void moverDis(Path camino){
	if (camino == null){
		System.out.println("No hay camino posible");
		}
	else{
					System.out.print("Posicion actual:");
					System.out.println("(" + camino.getStep(0).getX() + ","+ camino.getStep(0).getY() + ")");
					this.setPos(new Position (camino.getStep(1).getX(), camino.getStep(1).getY()));
}
}

public void setPosInicial(){
	this.pos = new Position (14,11); //Cuando muere Blinky se lo vuelve a ubicar en su posicion que sera dentro de la casa a la izquierda
}

public void cambioEstado(boolean asus, Map mapaCol) {//el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
		if (asus==true) {
			this.modo= Mode.ASUSTADO;
			this.caminoAsus(mapaCol);
		}
		if (asus==false) this.modo= Mode.PERSECUCION; //se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
	//preguntar como pasa blinky a el estado Dispercion
		}
	
}


