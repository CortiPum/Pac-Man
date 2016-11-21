package Personaje;

import java.awt.Graphics;
import java.awt.Image;

import Estados.*;
import MapaBuscador.*;
import Util.Animacion;
import Util.CargaImagen;
import Util.Direccion;
import Util.Fantasma;
import Util.Identificador;

/** Esta clase modela un fantasma Pinky.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class Pinky extends Fantasma{
	
	private long tiem;
	private boolean auxiliarTiempo;
	//heredan getters y setters de fantasma

/**
 * Genera un fantasma Pinky. 	
 */
public Pinky(){
	this.ID=Identificador.PINKY;
	this.tiem=System.currentTimeMillis();
	this.auxiliarTiempo=false;
	this.asus=false;
	this.inicializarImagen();
	this.iconoActual = 0;
	this.imagenActual = iconos[0].getImagenIndex(0);
	this.nombre= "Pinky";
	this.modo=Mode.DISPERCION;
	this.pos = new Position (14,12); 
}

/**
 * Resetea las instancias del fantasma.
 */
public void reset(){
	this.asus=false;
	this.auxiliarTiempo=false;
	this.tiem=System.currentTimeMillis();
	this.iconoActual = 0;
	this.imagenActual = iconos[0].getImagenIndex(0);
	this.nombre= "Pinky";
	this.modo=Mode.DISPERCION;
	this.pos = new Position (14,12); 
}
//metodo mover se encuentra en fantasma

/**
 * Este método configura la animación que debe hacer dependiendo la dirección del movimiento.
 * @param camino
 */
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

/**
 * Carga las imagenes cque conforman las animaciones de dicho fantasma.
 */
public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
	Image[] aux = new Image[2];
	Image[] aux2 = new Image[2];
	Image[] aux3 = new Image[2];
	Image[] aux4 = new Image[2];
	Image[] aux5 = new Image[1];

	aux[0] = car.carga("ZImagenes/pink1.gif");
	aux[1] = car.carga("ZImagenes/pink2.gif");

	iconos[0]= new Animacion(aux);

	aux2[0] = car.carga("ZImagenes/pink3.gif");
	aux2[1] = car.carga("ZImagenes/pink4.gif");

	iconos[1] = new Animacion(aux2);

	aux3[0]= car.carga("ZImagenes/pink5.gif");
	aux3[1] = car.carga("ZImagenes/pink6.gif");

	iconos[2] = new Animacion(aux3);


	aux4[0] = car.carga("ZImagenes/pink7.gif");
	aux4[1] = car.carga("ZImagenes/pink8.gif");

	iconos[3]= new Animacion(aux4);

	aux5[0]= car.carga("ZImagenes/azul.gif");

	iconos[4] = new Animacion(aux5);
}
/**
 * 
 * @return Devuelve el arreglo que contiene las animaciones.
 */
public Animacion[] getIconos(){
	return (this.iconos);
}

/**
 * Método que representa el moviemiento cuando el fantasma se encuentra en modo persecución.
 */
public void estaPersecucion(Pacman pac, Fantasma blin){
	// Se mueve a al cuarto paso prï¿½ximo del recorrido del Pacman.
	int ypaso=1; int xpaso=1;
	Direccion dir = pac.getDir();
	switch (dir){
		
		case Oeste:
			ypaso =pac.getPos().getY()+4;
			xpaso =pac.getPos().getX();
			break;
		
		case Este:
			ypaso =pac.getPos().getY()-4;
			xpaso =pac.getPos().getX();
			break;
			
		case Sur:
			ypaso =pac.getPos().getY();
			xpaso =pac.getPos().getX()+4;
			break;
		
		case Norte:
			ypaso =pac.getPos().getY();
			xpaso =pac.getPos().getX()-4;
			break;
	}
		Path caminoPinky = PathFinder.findPath(this.pos.getX(), this.pos.getY(), xpaso, ypaso);
		int distanciaX = Math.abs(pac.getPos().getX()-this.pos.getX());
		int distanciaY = Math.abs(pac.getPos().getY()-this.pos.getY());
		int distanciaNeta = distanciaX+distanciaY;
		if ((caminoPinky != null) && (distanciaNeta>5)){
			this.moverDis(caminoPinky);
			
		}else{
			Path caminoPinky2 = PathFinder.findPath(this.pos.getX(), this.pos.getY(), pac.getPos().getX(), pac.getPos().getY());
			this.moverDis(caminoPinky2);
		
		}
		}


/**
 * Método que representa el moviemiento cuando el fantasma se encuentra en modo disperción.
 */

public void estaDispercion(){
	Path caminoA = null;
	Path caminoB = null;
	Path caminoC = null;
	Path caminoD = null;
	
	
		if (((this.pos.getX() <=4) && (this.pos.getY() <= 6) && (this.pos.getY()>3))){
			caminoB = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 1, 3);
			this.moverDis(caminoB);
		}else
		
			if ((this.pos.getX() >= 1) && (this.pos.getX()<5) && (this.pos.getY() <= 3)){
				caminoC = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5, 1);
				this.moverDis(caminoC);
			}else
				if ((this.pos.getX() == 5) && (this.pos.getY() >= 2) &&(this.pos.getY() <6) ){
					caminoD = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 5, 6);
					this.moverDis(caminoD);
				}else{
						caminoA = PathFinder.findPath(this.pos.getX(), this.pos.getY(), 4 ,6 );
						this.moverDis(caminoA);
						}
}
/**
 * Hace un paso en el camino recibido.
 * @param camino
 */
private void moverDis(Path camino){
	if (camino == null){
			
		}
	else{
		this.imagenActual(camino);
		this.setPos(new Position (camino.getStep(1).getX(), camino.getStep(1).getY()));
	}
}


/**
 * Setea la posición inicial. En el caso de que muera.
 */
public void setPosInicial(){
	this.pos = new Position (14,12); 
	if (this.modo == Mode.ASUSTADO)
		this.modo = Mode.PERSECUCION;
}
/**
 * Cambia a persecución en caso de que se pase un false y a asustado en caso de que se pase un true.
 * @param asus
 * @param mapaCol
 */
public void cambioEstado(boolean asus, Map mapaCol) {
	 //el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
		if (asus) {
			this.modo= Mode.ASUSTADO;
			this.caminoAsus(mapaCol);
		}
		if (!asus){
			this.modo= Mode.PERSECUCION; 
			//se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
		}
		
	
}

/**
 * Dibuja el fantasma.
 */

@Override
public void draw(Graphics g) {
	imagenActual = iconos[iconoActual].getImagenActual();
	iconos[iconoActual].refresh();
	g.drawImage(this.imagenActual,this.pos.getY()*23+8, this.pos.getX()*23+30, null);
	
}
/**
 * Controla el fantasma.
 * @param pac
 * @param map
 * @param mapCol
 */
public void refresh(Pacman pac, Map map){
	if(System.currentTimeMillis()-pac.getTiem()>1000){
	if(this.auxiliarTiempo==false){
		this.tiem=System.currentTimeMillis();
		this.auxiliarTiempo=true;
	}
	this.mover(pac, this);
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
		this.asus=false;
	}
	this.controlaEstado();
	}
}

/**
 * Controla el estado dependiendo del tiempo.
 * @param pac
 */
public void controlaEstado(){
	if ((System.currentTimeMillis()-this.tiem >=7000)&& (System.currentTimeMillis()-this.tiem <8000) && (this.modo !=Mode.ASUSTADO)){
		this.setModo(Mode.PERSECUCION);
	}
	if ((System.currentTimeMillis()-this.tiem >= 34000) &&(System.currentTimeMillis()-this.tiem<35000)&&(this.modo != Mode.ASUSTADO)){
		this.setModo(Mode.PERSECUCION);
	}
	if ((System.currentTimeMillis()-this.tiem >= 59000) &&(System.currentTimeMillis()-this.tiem<60000)&&(this.modo != Mode.ASUSTADO)){
		this.setModo(Mode.PERSECUCION);
	}
	if ((System.currentTimeMillis()-this.tiem >= 84000)&&(this.modo != Mode.ASUSTADO)){
		this.setModo(Mode.PERSECUCION);
	}

}
}
//Comentario viejo.
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
