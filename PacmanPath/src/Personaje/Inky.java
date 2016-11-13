package Personaje;

import java.awt.Graphics;
import java.awt.Image;

import Estados.*;

import MapaBuscador.*;
import MapaN.MapaGeneral;
import Util.Animacion;
import Util.CargaImagen;
import Util.Direccion;
import Util.Fantasma;
import Util.Id;

public class Inky extends Fantasma {

	//heredan getters y setters de Fantasma
	private boolean aux;
	
public Inky(){
	this.ID=Id.INKY;
	this.aux=false;
	this.asus=false;
	this.inicializarImagen();
	this.iconoActual = 0;
	this.imagenActual = iconos[0].getImagenIndex(0);
	this.nombre="Inky";
	this.modo=Mode.INACTIVO; //estara inactivo hasta que el pacman coma 30 puntos
	this.pos = new Position (14,15); 
}
public void reset(){
	this.asus=false;
	this.aux=false;
	this.iconoActual = 0;
	this.imagenActual = iconos[0].getImagenIndex(0);
	this.nombre="Inky";
	this.modo=Mode.INACTIVO; //estara inactivo hasta que el pacman coma 30 puntos
	this.pos = new Position (14,15); 
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

public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
	Image[] aux = new Image[2];
	Image[] aux2 = new Image[2];
	Image[] aux3 = new Image[2];
	Image[] aux4 = new Image[2];
	Image[] aux5 = new Image[1];
	
	aux[0] = car.carga("ZImagenes/skyblue1.gif");
	aux[1] = car.carga("ZImagenes/skyblue2.gif");

	iconos[0]= new Animacion(aux);

	aux2[0] = car.carga("ZImagenes/skyblue3.gif");
	aux2[1] = car.carga("ZImagenes/skyblue4.gif");

	iconos[1] = new Animacion(aux2);

	aux3[0]= car.carga("ZImagenes/skyblue5.gif");
	aux3[1] = car.carga("ZImagenes/skyblue6.gif");

	iconos[2] = new Animacion(aux3);


	aux4[0] = car.carga("ZImagenes/skyblue7.gif");
	aux4[1] = car.carga("ZImagenes/skyblue8.gif");

	iconos[3]= new Animacion(aux4);
	
	aux5[0] = car.carga("ZImagenes/azul.gif");
	iconos[4]= new Animacion(aux5);
	}


public Animacion [] getIconos(){
	return (this.iconos);
}

public void cambioEstado(boolean asus, Map mapaBuscador){ //el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
	if ((asus) && ((this.modo == Mode.PERSECUCION) || (this.modo == Mode.DISPERCION))){
		this.caminoAsus(mapaBuscador);
		this.modo= Mode.ASUSTADO;
	}
	if ((asus==false) && (this.modo == Mode.ASUSTADO)) {
		this.modo= Mode.DISPERCION;
	//	this.iconoActual=this.iconos[0];//se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
	}
	
	}

public void estaPersecucion(Pacman pac, Fantasma blin){
	int ypaso=1; int xpaso=1;
	Direccion dir = pac.getDir();
	switch (dir){
		
		case Oeste:
			ypaso =pac.getPos().getY()+2;
			xpaso =pac.getPos().getX();
			break;
		
		case Este:
			ypaso =pac.getPos().getY()-2;
			xpaso =pac.getPos().getX();
			break;
			
		case Sur:
			ypaso =pac.getPos().getY();
			xpaso =pac.getPos().getX()+2;
			break;
		
		case Norte:
			ypaso =pac.getPos().getY();
			xpaso =pac.getPos().getX()-2;
			break;
	}
		int xblin = blin.getPos().getX();
		int yblin = blin.getPos().getY();
		int xvec = Math.abs(xpaso-xblin);
		int yvec = Math.abs(ypaso-yblin);
		Position vectorD = new Position ((2*xvec),(2*yvec));
		Path vector = PathFinder.findPath(this.pos.getX(), this.pos.getY(),vectorD.getX(),vectorD.getY());
		int distanciaX = Math.abs(this.pos.getX()-pac.getPos().getX());
		int distanciaY = Math.abs(this.pos.getY()-pac.getPos().getY());
		int distancia = distanciaX+distanciaY;
			if ((vector != null)&&(distancia>3)){
				if(vector.getLength()!=0){
					this.moverDis(vector);
				}
			}else{
				Path cam2 = PathFinder.findPath(this.getPos().getX(), this.getPos().getY(), pac.getPos().getX(), pac.getPos().getY());
				this.moverDis(cam2); 
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
	this.pos = new Position (14,15); 
	this.modo= Mode.DISPERCION;
}

@Override
public void draw(Graphics g) {
	//esto en el refresh
		imagenActual = iconos[iconoActual].getImagenActual();
		iconos[iconoActual].refresh();
		//
	g.drawImage(imagenActual, this.pos.getY()*23+8, this.pos.getX()*23+30, null);
}

public void refresh(Pacman pac, Map map){
	this.mover(pac, this);
	if((pac.getPuntaje()>30)&&(aux==false)){
		this.setModo(Mode.DISPERCION);
		aux=true;
	}
	int contadorPasos=0;
	if ((pac.getEstado() == Mode.ESTADOPODER)&&(this.asus==false)){
		this.cambioEstado(true,map);
		this.asus=true;
	}
	if (this.modo==Mode.ASUSTADO)
		this.iconoActual=4;
	if(pac.getEstado() == Mode.NORMAL){
		this.asus=false;
	}
	if ((pac.getEstado() == Mode.NORMAL)&&(this.getModo()==Mode.ASUSTADO)){
		this.cambioEstado(false,map);
		contadorPasos = 0;
		this.asus=false;
	}
}
}

