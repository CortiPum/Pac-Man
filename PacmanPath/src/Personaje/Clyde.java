package Personaje;

import java.awt.Graphics;
import java.awt.Image;

import Estados.*;
import MapaBuscador.*;
import Util.CargaImagen;
import Util.Fantasma;
import Util.Id;

public class Clyde extends Fantasma {
	
	//color
	
	//heredan getters y setters de fantasma
	
public Clyde(){
	this.ID =Id.CLYDE;
	this.iconos = new Image[8];
	this.inicializarImagen();
	this.iconoActual= iconos[0];
	this.nombre ="Clyde";
	this.modo=Mode.INACTIVO;  //esta inactivo hasta que el pacman coma 3/4 del mapa
	this.pos = new Position (14,16); 
}
	

public void inicializarImagen(){
CargaImagen car = new CargaImagen();
	
	this.iconos[0] = car.carga("ZImagenes/orange1.gif");
	this.iconos[1] = car.carga("ZImagenes/orange2.gif");
	this.iconos[2] = car.carga("ZImagenes/orange3.gif");
	this.iconos[3] = car.carga("ZImagenes/orange4.gif");
	this.iconos[4] = car.carga("ZImagenes/orange5.gif");
	this.iconos[5] = car.carga("ZImagenes/orange6.gif");
	this.iconos[6] = car.carga("ZImagenes/orange7.gif");
	this.iconos[7] = car.carga("ZImagenes/orange8.gif");
}

//mismo codigo de Blinky, va a entrar solo si esta a mas de 8 casilleros 
public void estaPersecucion(Pacman pac, Fantasma blin){
		int xpac = pac.getPos().getX();
		int ypac =pac.getPos().getY();
		System.out.println(xpac); System.out.println(ypac);
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
	
	
		if (((this.pos.getX() >= 23) && (this.pos.getX() <27) && (this.pos.getY() <= 7)) ){
			caminoB = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 27, 1);
			this.moverDis(caminoB);
		}else
		
			if ((this.pos.getX() >= 27)&& (this.pos.getY() >= 1) &&(this.pos.getY() < 12)){
				caminoC = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 28, 12);
				this.moverDis(caminoC);
			}else
				if ((this.pos.getX() <= 28)&&(this.pos.getX() > 23) && (this.pos.getY() <= 12) &&(this.pos.getY() >7) ){
					caminoD = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 23, 7);
					this.moverDis(caminoD);
				}else{
					//if ((this.pos.getX() != 23) &&(this.pos.getY() != 7)){ 
						caminoA = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 23 ,7 );
						this.moverDis(caminoA);
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
	this.pos = new Position (14,16); 
}


public void cambioEstado(boolean asus, Pacman pac, Map mapaCol) {
	if ((asus==true) && ((this.modo == Mode.PERSECUCION) || (this.modo == Mode.DISPERCION))){
		this.caminoAsus(mapaCol);
		this.modo= Mode.ASUSTADO;
		}	
	if ((asus==false) &&(this.modo == Mode.ASUSTADO)) {
		this.modo= Mode.PERSECUCION; //se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
	}
	if (this.modo != Mode.ASUSTADO){
		int xpac = pac.getPos().getX();
		int ypac = pac.getPos().getY();
		int cxpos = this.pos.getX();
		int cypos =this.pos.getY();
		if ((Math.abs(xpac-cxpos)< 8) && (Math.abs(ypac-cypos)<8)){
			int cas = Math.abs(xpac-cxpos) + Math.abs(ypac-cypos);
			if(cas<8) this.modo = Mode.DISPERCION;
		}
	}
	}



@Override
public void draw(Graphics g) {
	g.drawImage(this.iconoActual, this.pos.getY()*23+8, this.pos.getX()*23+30,null);
	
}





	
}
