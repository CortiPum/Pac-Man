package Personaje;

import java.awt.Graphics;
import java.awt.Image;
import Estados.*;
import MapaBuscador.*;
import MapaN.MapaGeneral;
import Util.Animacion;
import Util.CargaImagen;
import Util.Fantasma;
import Util.Id;

public class Blinky extends Fantasma { 

	//hereda setters y getters de fantasma
	
	
	
public Blinky(){
	this.ID=Id.BLINKY;
	this.asus=false;
	this.inicializarImagen();
	this.imagenActual=iconos[0].getImagenIndex(0);
	this.nombre="Blinky"; 
	this.modo= Mode.DISPERCION;
	this.pos = new Position (11 , 13); 	
}

//mover esta en fantasma (controla que movimiento hara)
public void reset(){
	this.asus=false;
	this.imagenActual=iconos[0].getImagenIndex(0);
	this.modo= Mode.DISPERCION;
	this.pos = new Position (11 , 13); 	
}

public void inicializarImagen(){
CargaImagen car = new CargaImagen();

Image[] aux = new Image[2];
Image[] aux2 = new Image[2];
Image[] aux3 = new Image[2];
Image[] aux4 = new Image[2];
Image[] aux5 = new Image[1];

aux[0] = car.carga("ZImagenes/red1.gif");
aux[1] = car.carga("ZImagenes/red2.gif");

iconos[0]= new Animacion(aux);

aux2[0] = car.carga("ZImagenes/red3.gif");
aux2[1] = car.carga("ZImagenes/red4.gif");

iconos[1] = new Animacion(aux2);

aux3[0]= car.carga("ZImagenes/red5.gif");
aux3[1] = car.carga("ZImagenes/red6.gif");

iconos[2] = new Animacion(aux3);


aux4[0] = car.carga("ZImagenes/red7.gif");
aux4[1] = car.carga("ZImagenes/red8.gif");

iconos[3]= new Animacion(aux4);

aux5[0] = car.carga("ZImagenes/azul.gif");

iconos[4]= new Animacion(aux5);
}


public Animacion [] getIconos(){
return (this.iconos);
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
						caminoA = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 4 ,21 );
						this.moverDis(caminoA);
				}
}

public void imagenActual(Path camino){
	if (camino.getStep(1).getX() > this.pos.getX()){
		iconoActual= 0;
		
	}else{
			if(camino.getStep(1).getX()<this.pos.getX()){
				iconoActual = 3;
				
			}else{
				if(camino.getStep(1).getY()>this.pos.getY()){
					iconoActual =1;
					
				}else{
					iconoActual =2;
					
				}
			}
	}
}

private void moverDis(Path camino){
	if (camino == null){
		//System.out.println("No hay camino posible");
		}
	else{
					//System.out.print("Posicion actual:");
					//System.out.println("(" + camino.getStep(0).getX() + ","+ camino.getStep(0).getY() + ")");
					this.imagenActual(camino);
					this.setPos(new Position (camino.getStep(1).getX(), camino.getStep(1).getY()));
}
}


public void setPosInicial(){
	this.modo=Mode.DISPERCION;
	this.pos = new Position (14,11); //Cuando muere Blinky se lo vuelve a ubicar en su posicion que sera dentro de la casa a la izquierda
}

public void cambioEstado(boolean asus, Map mapaCol) {//el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
		if (asus) {
			this.modo= Mode.ASUSTADO;
			this.caminoAsus(mapaCol);
		}
		if (!asus){
			//this.iconoActual=this.iconos[0];
			this.modo= Mode.DISPERCION; //se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
		}

		}

@Override
public void draw(Graphics g) {
	//esto va en el refresh
	imagenActual = iconos[iconoActual].getImagenActual();
	iconos[iconoActual].refresh();
	//
	g.drawImage(imagenActual, this.pos.getY()*23+8, this.pos.getX()*23+30, null);
}

public void refresh(Pacman pac, Map map){
	this.mover(pac, this);
	int contadorPasos=0;
	if ((pac.getEstado() == Mode.ESTADOPODER)&&(this.asus==false)){
		this.cambioEstado(true, map);
		this.asus=true;
	}
	if (this.modo==Mode.ASUSTADO)
		this.iconoActual=4;
	if(pac.getEstado() == Mode.NORMAL){
		this.asus=false;
	}
	if ((pac.getEstado() == Mode.NORMAL)&&(this.getModo()==Mode.ASUSTADO)){
		this.cambioEstado(false, map);
		contadorPasos = 0;
		this.asus=false;
	}
/*	if ((System.currentTimeMillis() == 7000)||(System.currentTimeMillis()==34000)||(System.currentTimeMillis()==59000)||(System.currentTimeMillis()>84000)){ //arreglar
		this.setModo(Mode.PERSECUCION);
	}
	if ((System.currentTimeMillis() == 27000)||(System.currentTimeMillis()==54000)||(System.currentTimeMillis()==79000)){
		this.setModo(Mode.DISPERCION);
		
	}*/
}

	
}


