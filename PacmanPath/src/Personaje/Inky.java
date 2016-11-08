package Personaje;

import java.awt.Graphics;
import java.awt.Image;

import Estados.*;

import MapaBuscador.*;
import Util.CargaImagen;
import Util.Fantasma;
import Util.Id;

public class Inky extends Fantasma {

	//color
	//heredan getters y setters de Fantasma

	
public Inky(){
	this.ID=Id.INKY;
	this.iconos = new Image[8];
	this.inicializarImagen();
	this.iconoActual = iconos[0];
	this.nombre="Inky";
	this.modo=Mode.INACTIVO; //estara inactivo hasta que el pacman coma 30 puntos
	this.pos = new Position (14,15); 
}
	
public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
			
	this.iconos[0] = car.carga("ZImagenes/skyblue1.gif");
	this.iconos[1] = car.carga("ZImagenes/skyblue2.gif");
	this.iconos[2] = car.carga("ZImagenes/skyblue3.gif");
	this.iconos[3] = car.carga("ZImagenes/skyblue4.gif");
	this.iconos[4] = car.carga("ZImagenes/skyblue5.gif");
	this.iconos[5] = car.carga("ZImagenes/skyblue6.gif");
	this.iconos[6] = car.carga("ZImagenes/skyblue7.gif");
	this.iconos[7] = car.carga("ZImagenes/skyblue8.gif");
		}


public void cambioEstado(boolean asus, Map mapaBuscador){ //el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
	if ((asus==true) && ((this.modo == Mode.PERSECUCION) || (this.modo == Mode.DISPERCION))){
		this.caminoAsus(mapaBuscador);
		this.modo= Mode.ASUSTADO;
	}
	if ((asus==false) && (this.modo == Mode.ASUSTADO)) {
		this.modo= Mode.PERSECUCION; //se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
	}
	//preguntar como pasa blinky a el estado Dispercion
	}

/**
 * copiar este de abajo
 */
public void estaPersecucion(Pacman pac, Fantasma blin){
	Path pacCam= pac.getcamino();
	if(pacCam.getLength()!=0){ //ver abajo de todo
		int ypaso; int xpaso;
		if(pacCam.getLength()>=2){
			xpaso =pacCam.getStep(1).getX();
			ypaso =pacCam.getStep(1).getY();
		}else{
			xpaso = pacCam.getStep(pacCam.getLength()-1).getX();
			ypaso = pacCam.getStep(pacCam.getLength()-1).getY();
		}
			int xblin = blin.getPos().getX();
			int yblin = blin.getPos().getY();
			int xvec = (xpaso-xblin);
			int yvec = (ypaso-yblin);
			Position vectorD = new Position ((2*xvec),(2*yvec));
			Path vector = PathFinder.findPath(this.pos.getY(), this.pos.getX(), this.pos.getY()+vectorD.getY(), this.pos.getX()+vectorD.getX());
			if (vector == null){
				//System.out.println("No hay camino posible");
				System.out.print("Posicion actual:");
				Path cam2 = PathFinder.findPath(this.getPos().getY(), this.getPos().getX(), xpaso, ypaso);
				System.out.println("(" + cam2.getStep(0).getX() + ","+ cam2.getStep(0).getY() + ")");
				this.setPos(new Position (cam2.getStep(1).getX(), cam2.getStep(1).getY())); 
			}else{
			System.out.print("Posicion actual:");
			System.out.println("(" + vector.getStep(0).getX() + ","+ vector.getStep(0).getY() + ")");
			this.setPos(new Position (vector.getStep(1).getY(), vector.getStep(1).getX())); //actualiza la posicion del fantasma a su posicion siguiente en el arreglo
			}	
	}
	if (pacCam.getLength()==0){
		Path caminoPi2= PathFinder.findPath(pac.getPos().getX(),pac.getPos().getY(),this.pos.getX(),this.pos.getY());
		System.out.print("Posicion actual:");
		System.out.println("(" + caminoPi2.getStep(0).getX() + ","+ caminoPi2.getStep(0).getY() + ")");
		this.setPos(new Position (caminoPi2.getStep(1).getX(), caminoPi2.getStep(1).getY())); //actualiza la posicion del fantasma a su posicion siguiente en el arreglo
	}
}


public void estaDispercion(){
	Path caminoA = null;
	Path caminoB = null;
	Path caminoC = null;
	Path caminoD = null;
		
		
		if (((this.pos.getX() >= 23) && (this.pos.getX() <27) && (this.pos.getY() >= 21)) ){
			caminoB = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 27, 26);
			this.moverDis(caminoB);
		}else
			
			if ((this.pos.getX() >= 27)&& (this.pos.getY() <= 26)&&(this.pos.getY() >15)){
				caminoC = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 28, 15);
				this.moverDis(caminoC);
			}else
				if ((this.pos.getX() <= 28) && (this.pos.getX() > 23) && (this.pos.getY() >=15) &&(this.pos.getY()<20) ){
					caminoD = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 23, 20);
					this.moverDis(caminoD);
				}else { 
					
						caminoA = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 23 ,21 );
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
	this.pos = new Position (14,15); 
}

@Override
public void draw(Graphics g) {
	g.drawImage(iconoActual, this.pos.getY()*23+8, this.pos.getX()*23+30, null);
}


}
