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
/** Esta clase modela un fantasma Inky.
* @author Cortizas Tom�s ; Peraza Orlando.
* @version 2.0
*/
public class Inky extends Fantasma {

	//heredan getters y setters de Fantasma
	private boolean aux;
	private long tiem;
/**
 * Genera un fantasma Inky. 	
 */		
public Inky(){
	this.ID=Identificador.INKY;
	this.aux=false;
	this.asus=false;
	this.inicializarImagen();
	this.iconoActual = 0;
	this.imagenActual = iconos[0].getImagenIndex(0);
	this.nombre="Inky";
	this.modo=Mode.INACTIVO; //estara inactivo hasta que el pacman coma 30 puntos
	this.pos = new Position (14,15); 
}
/**
 * Resetea las instancias del fantasma.
 */
public void reset(){
	this.asus=false;
	this.aux=false;
	this.iconoActual = 0;
	this.imagenActual = iconos[0].getImagenIndex(0);
	this.nombre="Inky";
	this.modo=Mode.INACTIVO; //estara inactivo hasta que el pacman coma 30 puntos
	this.pos = new Position (14,15); 
}
	
/**
 * Este m�todo configura la animaci�n que debe hacer dependiendo la direcci�n del movimiento.
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

/**
 * 
 * @return Devuelve el arreglo que contiene las animaciones.
 */
public Animacion [] getIconos(){
	return (this.iconos);
}
/**
 * Cambia a persecuci�n en caso de que se pase un false y a asustado en caso de que se pase un true.
 * @param asus
 * @param mapaCol
 */
public void cambioEstado(boolean asus, Map mapaBuscador){ //el metodo de comer powerball devuelve un booleano, cqso contrario se pasara un false
	if ((asus) && ((this.modo == Mode.PERSECUCION) || (this.modo == Mode.DISPERCION))){
		this.caminoAsus(mapaBuscador);
		this.modo= Mode.ASUSTADO;
	}
	if ((asus==false) && (this.modo == Mode.ASUSTADO)) {
		this.modo= Mode.PERSECUCION;
	//se pasa false cuando se acaba el estado poder y vuelve a ponerlo en Persecucion
	}
	
	}

/**
 * M�todo que representa el moviemiento cuando el fantasma se encuentra en modo persecuci�n.
 */
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

/**
 * M�todo que representa el moviemiento cuando el fantasma se encuentra en modo disperci�n.
 */
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
 * Setea la posici�n inicial. En el caso de que muera.
 */
public void setPosInicial(){
	this.pos = new Position (14,15); 
	if (this.modo == Mode.ASUSTADO)
		this.modo = Mode.PERSECUCION;
}
/**
 * Dibuja el fantasma.
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
 * @param mapCol
 */
public void refresh(Pacman pac, Map map){
	if(System.currentTimeMillis()-pac.getTiem()>1000){
	this.mover(pac, this);
	if((pac.getPuntaje()>30)&&(aux==false)){
		this.setModo(Mode.DISPERCION);
		this.tiem= System.currentTimeMillis();
		aux=true;
	}
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

