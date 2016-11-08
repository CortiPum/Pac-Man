package Personaje;

import java.awt.Graphics;
import java.awt.Image;
import Estados.*;
import MapaBuscador.*;
import MapaN.MapaGeneral;
import Util.CargaImagen;
import Util.Fantasma;
import Util.Id;

public class Blinky extends Fantasma { 

	
	
public Blinky(){
	this.ID=Id.BLINKY;
	this.iconos = new Image[7];
	this.inicializarImagen();
	this.iconoActual = iconos[0];
	this.nombre="Blinky"; 
	this.modo= Mode.DISPERCION;
	this.pos = new Position (11 , 13); 	
}

//mover esta en fantasma (controla que movimiento hara)

public void inicializarImagen(){
CargaImagen car = new CargaImagen();
	
	this.iconos[0] = car.carga("ZImagenes/red1.gif");
	this.iconos[1] = car.carga("ZImagenes/red2.gif");
	this.iconos[2] = car.carga("ZImagenes/red3.gif");
	this.iconos[3] = car.carga("ZImagenes/red4.gif");
	this.iconos[4] = car.carga("ZImagenes/red5.gif");
	this.iconos[5] = car.carga("ZImagenes/red6.gif");
	this.iconos[6] = car.carga("ZImagenes/red7.gif");
	//this.iconos[7] = car.carga("ZImagenes/red8.gif");
}

public void estaPersecucion(Pacman pac, Fantasma blin){
	int xpac = pac.getPos().getX();
	int ypac =pac.getPos().getY();
	int xpos=this.pos.getX();
	int ypos=this.pos.getY();
	Path caminoB = PathFinder.findPath(xpos,ypos, xpac, ypac);
	this.moverDis(caminoB);

}


public void estaDispercion(){
	Path caminoA = null;
	Path caminoB = null;
	Path caminoC = null;
	Path caminoD = null;
		if (((this.pos.getX() < 5) && (this.pos.getY() < 24) && (this.pos.getY() >= 21)) ){
			caminoB = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 1, 24);
			this.moverDis(caminoB);
		}else
		
			if ((this.pos.getX() >= 1) && (this.pos.getX()<5) && (this.pos.getY() >= 24)){
				caminoC = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5, 26);
				this.moverDis(caminoC);
			}else
				if ((this.pos.getX() == 5) && (this.pos.getY() <= 26) &&(this.pos.getY() >21) ){
					caminoD = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5, 21);
					this.moverDis(caminoD);
				}else{
					//if ((this.pos.getX() != 21) &&(this.pos.getY() != 5)){ 
						caminoA = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 4 ,21 );
						this.moverDis(caminoA);
						//}
				}
}

private void moverDis(Path camino){
	if (camino == null){
		System.out.println("No hay camino posible");
		}
	else{
					//System.out.print("Posicion actual:");
					//System.out.println("(" + camino.getStep(0).getX() + ","+ camino.getStep(0).getY() + ")");
					this.setPos(new Position (camino.getStep(1).getX(), camino.getStep(1).getY()));
}
}

public void setPosInicial(){
	this.pos = new Position (11,13); //Cuando muere Blinky se lo vuelve a ubicar en su posicion que sera dentro de la casa a la izquierda
}

public void cambioEstado(boolean asus, Map mapaCol) {//el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
		if (asus) {
			//this.modo = Mode.DISPERCION; (para prueba)
			this.modo= Mode.ASUSTADO;
			this.caminoAsus(mapaCol);
		}
		if (!asus) this.modo= Mode.PERSECUCION; //se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion

		}

@Override
public void draw(Graphics g) {
	g.drawImage(iconoActual, this.pos.getY()*23+8, this.pos.getX()*23+30, null);
}

public void refresh(Pacman pac, Map mapaCol){
	this.mover(pac, this);
	int contadorPasos=0;
	if (pac.getEstado() == Mode.ESTADOPODER){
		this.cambioEstado(true, mapaCol);
		contadorPasos++;
	}
	if (contadorPasos == 20){
		this.cambioEstado(false, mapaCol);
		contadorPasos = 0;
	}
	if ((System.currentTimeMillis() % 7000) == 0){ //arreglar
		this.setModo(Mode.PERSECUCION);
	}
}

	
}


