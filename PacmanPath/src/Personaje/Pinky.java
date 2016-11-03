package Personaje;

import Estados.*;
import MapaBuscador.*;
import Util.Fantasma;
import Util.Id;


public class Pinky extends Fantasma{
	
	//color
	//heredan getters y setters de fantasma

	
public Pinky(){
	this.ID=Id.PINKY;
	this.nombre= "Pinky";
	this.modo=Mode.PERSECUCION;
	this.pos = new Position (14,12); 
}
	
//metodo mover se encuentra en fantasma


public void estaPersecucion(Pacman pac, Blinky blin){
	// Se mueve a al cuarto paso pr�ximo del recorrido del Pacman.
		Path caminoP = pac.getcamino();
		if(caminoP.getLength()!=0){ //ver abajo de todo
			int ypaso; int xpaso;
			if(caminoP.getLength()>=4){
				xpaso =caminoP.getStep(3).getX();
				ypaso =caminoP.getStep(3).getY();
			}else{
				xpaso = caminoP.getStep(caminoP.getLength()-1).getX();
				ypaso = caminoP.getStep(caminoP.getLength()-1).getY();
			}
				Path caminoPi = PathFinder.findPath(this.pos.getX(), this.pos.getY(), xpaso, ypaso);
				if (caminoPi == null){
					System.out.println("No hay camino posible");
				}
					else {
						System.out.print("Posicion actual:");
						System.out.println("(" + caminoPi.getStep(0).getX() + ","+ caminoPi.getStep(0).getY() + ")");
						this.setPos(new Position (caminoPi.getStep(1).getX(), caminoPi.getStep(1).getY())); //actualiza la posicion del fantasma a su posicion siguiente en el arreglo
					}
		}if (caminoP.getLength()==0){
			Path caminoPi2= PathFinder.findPath(this.pos.getX(),this.pos.getY(),pac.getPos().getX(),pac.getPos().getY());
			System.out.print("Posicion actual:");
			System.out.println("(" + caminoPi2.getStep(0).getX() + ","+ caminoPi2.getStep(0).getY() + ")");
			this.setPos(new Position (caminoPi2.getStep(1).getX(), caminoPi2.getStep(1).getY())); //actualiza la posicion del fantasma a su posicion siguiente en el arreglo
		}
		int xpac = pac.getPos().getX();
		int ypac = pac.getPos().getY();
		int cxpos = this.pos.getX();
		int cypos =this.pos.getY();
		if ((Math.abs(xpac-cxpos)< 4) && (Math.abs(ypac-cypos)<4)){
			int cas = Math.abs(xpac-cxpos) + Math.abs(ypac-cypos);
			if(cas<4) {
				Path caminoPi2= PathFinder.findPath(this.pos.getX(),this.pos.getY(),pac.getPos().getX(),pac.getPos().getY());
				System.out.print("Posicion actual:");
				System.out.println("(" + caminoPi2.getStep(0).getX() + ","+ caminoPi2.getStep(0).getY() + ")");
				this.setPos(new Position (caminoPi2.getStep(1).getX(), caminoPi2.getStep(1).getY())); 
			}
		}
}

	

public void estaDispercion(){
	Path caminoA = null;
	Path caminoB = null;
	Path caminoC = null;
	Path caminoD = null;
	
	
		if (((this.pos.getX() <=5) && (this.pos.getY() <= 6) && (this.pos.getY()>3))){
			caminoB = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 1, 3);
			this.moverDis(caminoB);
		}else
		
			if ((this.pos.getX() >= 1) && (this.pos.getX()<5) && (this.pos.getY() <= 3)){
				caminoC = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5, 1);
				this.moverDis(caminoC);
			}else
				if ((this.pos.getX() == 5) && (this.pos.getY() >= 1) &&(this.pos.getY() <6) ){
					caminoD = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5, 6);
					this.moverDis(caminoD);
				}else
					if ((this.pos.getX() != 5) &&(this.pos.getY() != 6)){ 
						caminoA = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5 ,6 );
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
	this.pos = new Position (14,12); //ubicar bien a Pinky
}

public void cambioEstado(boolean asus, Map mapaCol) {
	 //el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
		if (asus==true) {
			this.modo= Mode.ASUSTADO;
			this.caminoAsus(mapaCol);
		}
		if (asus==false) this.modo= Mode.PERSECUCION; //se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
		
	
}
/**
 * int xpac = pac.getPos().getX();
		int ypac = pac.getPos().getY();
		int cxpos = this.pos.getX();
		int cypos =this.pos.getY();
		if ((Math.abs(xpac-cxpos)< 8) && (Math.abs(ypac-cypos)<8)){
			int cas = Math.abs(xpac-cxpos) + Math.abs(ypac-cypos);
			if(cas<8) this.modo = Mode.DISPERCION;
		}
 */
}

/* el fantasma va a tomar el arreglo de pasos que tiene el pacman,
 *  de ahi tomara el 4to lugar para crear un camino desde la
 *   posicion del fantasma hacia ese cuarto lugar del arreglo de
 *    pasos del pacman. Por ende cuando el pacman esta llegando al final
 *    se queda sin poder entrar a una cuarta posicion, por eso preguntamos
 *    si la longitud del areglo de pasos del pacman es mayor a 4, en caso de
 *    ser menor a 4 va a tomar la ultima posicion del arreglo,
 *    y en caso de devolver un arreglo vacio va a crear un camino hacia su 
 *    posicion del pacman
 *    */
