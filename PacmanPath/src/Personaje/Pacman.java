package Personaje;


import MapaBuscador.*;
import MapaN.*;
import Util.CargaImagen;
import Util.Dinamico;
import Util.Direccion;
import Util.Fantasma;
import Util.Id;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controlador.Teclado;
import Estados.*;

public class Pacman extends Dinamico {
	
	private int puntaje;
	private int vidas;
	private Direccion dir;  //esto ser� para la segunda entrega
	private Mode estado;
	private Path camino;  //esto solo para la primera entrega.
	
	private Teclado teclado;
	
public Pacman(int x, int y){
	teclado = new Teclado();
	
	 
	
	this.ID=Id.PACMAN;
	this.iconos = new Image[8];
	this.inicializarImagenes();
	this.iconoActual=iconos[0];
	this.puntaje=0;
	this.dir = Direccion.Oeste; //se utilizara en la segunda entrega
	this.estado = Mode.NORMAL; //setea modo.
	this.vidas=3;
	this.pos = new Position (x, y); //valores iniciales.
}
	

private void inicializarImagenes(){
	CargaImagen car = new CargaImagen();
	
	this.iconos[0] = car.carga("ZImagenes/pacman_aba_cerrado.gif");
	this.iconos[1] = car.carga("ZImagenes/pacman_abajo_abierto.gif");
	this.iconos[2] = car.carga("ZImagenes/pacman_arr_cerrado.gif");
	this.iconos[3] = car.carga("ZImagenes/pacman_arr_abierto.gif");
	this.iconos[4] = car.carga("ZImagenes/pacman_der_cerrado.gif");
	this.iconos[5] = car.carga("ZImagenes/pacman_der_abierto.gif");
	this.iconos[6] = car.carga("ZImagenes/pacman_izq_cerrado.gif");
	this.iconos[7] = car.carga("ZImagenes/pacman_izq_abierto.gif");
}

//getters y setters
public int getPuntaje() {
	return puntaje;
}


public void setPuntaje(int puntaje) {
	this.puntaje = puntaje;
}


public int getVidas() {
	return vidas;
}


public void setVidas(int vidas) {
	this.vidas = vidas;
}


//hereda el metodo getId de Personaje

public Mode getEstado(){
	return this.estado;
}

public void setEstado(Mode estado){
	this.estado=estado;
}

public Path getcamino(){ 
	return this.camino;
}

//metodo de movimiento para la primera entrega

// este metodo creara un camino dada una pocision inicial (x,y) y una posicion final (x1,y1) a traves del metodo findPath
private void crearPath(int x, int y, int x1, int y1){ 
	
	this.camino=PathFinder.findPath(x, y, x1 , y1);
				
	}


// Este metodo utiliza el Path (arreglo de Steps), imprimiendo el primer valor del arreglo, y luego lo borra.
public Position movimiento (){
	if (this.camino == null){
		System.out.println("No hay camino posible");
		return null;
		}
			else 
					System.out.print("Posicion actual:");
					System.out.println("(" + camino.getStep(0).getX() + ","+ camino.getStep(0).getY() + ")");
					Position pos1= new Position(camino.getStep(0).getX(), camino.getStep(0).getY());
					this.camino.removeStep(0);
					return pos1;

}


//el siguiente metodo solo sucede cuando el pacman entra a una celda donde hay una bolita
//Lo que hace es sumarle 10 puntos y poner el mapa de objetos un vacio en esa ubicación
public void comer (Position actual, MapaGeneral mapa){
	this.puntaje= this.puntaje+10;
	System.out.println("Puntaje actual:"+this.puntaje);
	mapa.getCelda(actual).generoVacio();
}

//entrara solo si colisiona con un fantasma y no esta en el estado poder.
public void morir (Inky ink, Pinky pin, Clyde cly, Blinky blin){
	this.setVidas(this.vidas-1);
	Position pos = new Position (23,11); 
	this.setPos(pos); //posicion donde comienza
	ink.setPosInicial();
	pin.setPosInicial();
	cly.setPosInicial();
	blin.setPosInicial();
	
	
}

//si sus vidas es mayor a 0 puede jugar
public boolean puedeJugar(){
	if (this.getVidas() > 0) return true;
	else return false;
}

//este metodo es llamado por un metodo en la clase Tunel, dependiendo a que tunel entro, saldra por el siguiente
public void transport (Position pos){
	Position pos1 = new Position(0,14);
	Position pos2 = new Position(27,14);
	if (pos.equals(pos1))
		this.setPos(pos2);
		else 
			this.setPos(pos1);

	}

public void comerFantasma(Fantasma fan, int x){ //reveer (x es el multiplicador)
	this.setPuntaje(this.puntaje+(x*400));
	fan.setPosInicial();
}

public void cambioEstado (boolean poder){
	if (poder == true) {
		this.puntaje = this.puntaje+50;
		System.out.println("Comio powerball, puntaje actual: "+this.getPuntaje());
		this.estado= Mode.ESTADOPODER;
	}
	if (poder == false){
		this.estado = Mode.NORMAL;
	}
}

public void nuevoMover(){
	
}


public void paint(Graphics g) {
	
}

@Override
public void draw (Graphics g){
	
	g.drawImage(iconoActual ,  8+23*this.getPos().getY(),30+ this.getPos().getX()*23, null);
}

public void refresh() {
	if (Teclado.abajo){
		int dy = 23;
		iconoActual = iconos[0];
		this.pos.setPositionY(pos.getY() + dy);
		System.out.println(pos.getY());
		this.dir = Direccion.Sur;
	}
	if (Teclado.arriba){
		int dy =23;
		iconoActual = iconos[2];
		this.pos.setPositionY(pos.getY()-dy);
		this.dir = Direccion.Norte;
	}
	if (Teclado.izquierda){
		int dx = 23;
		iconoActual = iconos[4];
		this.pos.setPositionX(pos.getX()-dx);
		this.dir = Direccion.Este;
	}
	if (Teclado.derecha){
		int dx = 23;
		iconoActual = iconos[6];
		this.pos.setPositionX(pos.getX()+dx);
		this.dir = Direccion.Oeste;
	}
}



}


