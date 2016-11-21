package Personaje;

import java.awt.Graphics;
import java.awt.Image;
import Estados.*;
import MapaBuscador.*;
import Util.Animacion;
import Util.CargaImagen;
import Util.Fantasma;
import Util.Identificador;
/** Esta clase modela un fantasma Blinky.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class Blinky extends Fantasma { 

	private long tiem;
	private boolean auxiliarTiempo;
	//hereda setters y getters de fantasma
	
	
/**
 * Genera un fantasma Blinky. 	
 */
public Blinky(){
	this.ID=Identificador.BLINKY;
	this.asus=false;
	this.auxiliarTiempo=false;
	this.inicializarImagen();
	this.imagenActual=iconos[0].getImagenIndex(0);
	this.nombre="Blinky"; 
	this.modo= Mode.DISPERCION;
	this.pos = new Position (11 , 13);
	this.tiem = System.currentTimeMillis();
}

//mover esta en fantasma (controla que movimiento hara)
/**
 * Resetea las instancias del fantasma.
 */
public void reset(){
	this.auxiliarTiempo=false;
	this.asus=false;
	this.tiem = System.currentTimeMillis();
	this.imagenActual=iconos[0].getImagenIndex(0);
	this.modo= Mode.DISPERCION;
	this.pos = new Position (11 , 13); 	
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

/**
 * 
 * @return Devuelve el arreglo que contiene las animaciones.
 */
public Animacion [] getIconos(){
	return (this.iconos);
}

/**
 * Método que representa el moviemiento cuando el fantasma se encuentra en modo persecución.
 */
public void estaPersecucion(Pacman pac, Fantasma blin){
	int xpac = pac.getPos().getX();
	int ypac =pac.getPos().getY();
	int xpos=this.pos.getX();
	int ypos=this.pos.getY();
	Path caminoB = PathFinder.findPath(xpos,ypos, xpac, ypac);
	this.moverDis(caminoB);

}
/**
 * Método que representa el moviemiento cuando el fantasma se encuentra en modo disperción.
 */
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
	if (this.modo == Mode.ASUSTADO)
		this.modo = Mode.PERSECUCION;
	this.pos = new Position (14,11); //Cuando muere Blinky se lo vuelve a ubicar en su posicion que sera dentro de la casa a la izquierda
}
/**
 * Cambia a persecución en caso de que se pase un false y a asustado en caso de que se pase un true.
 * @param asus
 * @param mapaCol
 */
public void cambioEstado(boolean asus, Map mapaCol) {//el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
	if (asus) {
		this.modo= Mode.ASUSTADO;
		this.caminoAsus(mapaCol);
	}
	if (!asus){
		this.modo= Mode.PERSECUCION; //se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
	}
}
/**
 * Dibuja al fantasma.
 */
@Override
public void draw(Graphics g) {
	imagenActual = iconos[iconoActual].getImagenActual();
	iconos[iconoActual].refresh();
	g.drawImage(imagenActual, this.pos.getY()*23+8, this.pos.getX()*23+30, null);
}
/**
 * Controla el fantasma.
 * @param pac
 * @param map
 */
public void refresh(Pacman pac, Map map){
	if(System.currentTimeMillis()-pac.getTiem()>1000){
	if(this.auxiliarTiempo==false){
		this.tiem=System.currentTimeMillis();
		this.auxiliarTiempo=true;
	}
	
	this.mover(pac, this);
	if ((pac.getEstado() == Mode.ESTADOPODER)&&(!this.asus)){
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
 * Dependiendo el tiempo controla el estado.
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
	if ((System.currentTimeMillis()-this.tiem >= 27000) &&(System.currentTimeMillis()-this.tiem<28000)&&(this.modo != Mode.ASUSTADO)){
		this.setModo(Mode.DISPERCION);
	}
	if ((System.currentTimeMillis()-this.tiem >= 54000) &&(System.currentTimeMillis()-this.tiem<55000)&&(this.modo != Mode.ASUSTADO)){
		this.setModo(Mode.DISPERCION);
	}
	if ((System.currentTimeMillis()-this.tiem >= 79000) &&(System.currentTimeMillis()-this.tiem<80000)&&(this.modo != Mode.ASUSTADO)){
		this.setModo(Mode.DISPERCION);
	}
}	
}

