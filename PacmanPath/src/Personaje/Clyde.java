package Personaje;

import java.awt.Graphics;
import java.awt.Image;
import Estados.*;
import MapaBuscador.*;
import MapaN.MapaGeneral;
import Util.Animacion;
import Util.CargaImagen;
import Util.Fantasma;
import Util.Identificador;
/** Esta clase modela un fantasma Clyde.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/

public class Clyde extends Fantasma {
	
	private boolean aux;
	//heredan getters y setters de fantasma
/**
 * Genera un fantasma Clyde. 	
 */	
public Clyde(){
	this.ID =Identificador.CLYDE;
	this.aux=false;
	this.asus=false;
	this.inicializarImagen();
	this.imagenActual = iconos[0].getImagenIndex(0);
	this.nombre ="Clyde";
	this.modo=Mode.INACTIVO;  //esta inactivo hasta que el pacman coma 3/4 del mapa
	this.pos = new Position (14,16); 
}
/**
 * Resetea las instancias del fantasma.
 */
public void reset(){
	this.aux=false;
	this.asus=false;
	this.imagenActual = iconos[0].getImagenIndex(0);
	this.modo=Mode.INACTIVO;  //esta inactivo hasta que el pacman coma 3/4 del mapa
	this.pos = new Position (14,16); 
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

	aux[0] = car.carga("ZImagenes/orange1.gif");
	aux[1] = car.carga("ZImagenes/orange2.gif");

	iconos[0]= new Animacion(aux);

	aux2[0] = car.carga("ZImagenes/orange3.gif");
	aux2[1] = car.carga("ZImagenes/orange4.gif");

	iconos[1] = new Animacion(aux2);

	aux3[0]= car.carga("ZImagenes/orange5.gif");
	aux3[1] = car.carga("ZImagenes/orange6.gif");

	iconos[2] = new Animacion(aux3);


	aux4[0] = car.carga("ZImagenes/orange7.gif");
	aux4[1] = car.carga("ZImagenes/orange8.gif");

	iconos[3]= new Animacion(aux4);

	aux5[0]= car.carga("ZImagenes/azul.gif");

	iconos[4]= new Animacion(aux5);
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
 * 
 * @return Devuelve el arreglo que contiene las animaciones.
 */
public Animacion [] getIconos(){
return (this.iconos);
}

/**
 * Método que representa el moviemiento cuando el fantasma se encuentra en modo persecución.
 */
//mismo codigo de Blinky, va a entrar solo si esta a mas de 8 casilleros 
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
	this.pos = new Position (14,16); 
	if (this.modo == Mode.ASUSTADO)
		this.modo = Mode.PERSECUCION;
}

/**
 * Cambia a persecución en caso de que se pase un false y a asustado en caso de que se pase un true.
 * @param asus
 * @param mapaCol
 */
public void cambioEstado(boolean asus, Map mapaCol) {
	if ((asus) && ((this.modo == Mode.PERSECUCION) || (this.modo == Mode.DISPERCION))){
		this.caminoAsus(mapaCol);
		this.modo= Mode.ASUSTADO;
		}	
	if ((asus==false) &&(this.modo == Mode.ASUSTADO)) {
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
	g.drawImage(this.imagenActual, this.pos.getY()*23+8, this.pos.getX()*23+30,null);
}
/**
 * Controla el fantasma.
 * @param pac
 * @param map
 * @param mapCol
 */
public void refresh(Pacman pac, Map map, MapaGeneral mapCol){
	if(System.currentTimeMillis()-pac.getTiem()>1000){
		this.mover(pac, this);
		if((pac.getPuntaje()>(3*mapCol.getPuntajeTotal())/4)&&(aux==false)){
			this.setModo(Mode.DISPERCION);
			aux=true;
		}
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
		this.controlaEstado(pac);
	}
}
/**
 * Controla el estado dependiendo del tiempo.
 * @param pac
 */
public void controlaEstado(Pacman pac){
	if ((this.modo != Mode.ASUSTADO)&&(this.modo != Mode.INACTIVO)){
		int xpac = pac.getPos().getX();
		int ypac = pac.getPos().getY();
		int cxpos = this.pos.getX();
		int cypos =this.pos.getY();
		if ((Math.abs(xpac-cxpos)< 8) && (Math.abs(ypac-cypos)<8)){
			int cas = Math.abs(xpac-cxpos) + Math.abs(ypac-cypos);
			if(cas<8) this.modo = Mode.DISPERCION;
		}else
			this.modo = Mode.PERSECUCION;
}
}
	
}
