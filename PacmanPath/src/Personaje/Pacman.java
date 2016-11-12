package Personaje;


import MapaBuscador.*;
import MapaN.*;
import Util.Animacion;
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
import Estaticos.Tunel;

public class Pacman extends Dinamico {
	
	private int puntaje;
	private int vidas;
	private Direccion dir;  
	private Mode estado;
	private int contadorPasos;
	private int contadorMuerteFantasma;
	private boolean[] muerteFantasma;
	private int cantObjComidos;
	
	
public Pacman(int x, int y){
	super();
	this.ID=Id.PACMAN;
	this.cantObjComidos=0;
	this.contadorPasos=0;
	this.muerteFantasma = new boolean[4];
	this.inicializarMuerteFantasma();
	this.inicializarImagenes();
	this.puntaje=0;
	this.dir = Direccion.Este; 
	this.estado = Mode.NORMAL; //setea modo.
	this.vidas=3;
	this.pos = new Position (x, y); //valores iniciales.
	
}

public void reset(){
	this.cantObjComidos=0;
	this.contadorPasos=0;
	this.puntaje=0;
	this.dir = Direccion.Este; 
	this.estado = Mode.NORMAL; //setea modo.
	this.vidas=3;
	this.inicializarMuerteFantasma();
	this.pos= new Position (23,14);
}

public void inicializarMuerteFantasma(){
	for (int i =0; i<4;i++)
		this.muerteFantasma[i]=false;
}

public Animacion[] getIconos(){
	return (this.iconos);
}

private void inicializarImagenes(){
	CargaImagen car = new CargaImagen();
	Image[] aux = new Image[2];
	Image[] aux2 = new Image[2];
	Image[] aux3 = new Image[2];
	Image[] aux4 = new Image[2];
	
	aux[0] = car.carga("ZImagenes/pacman_aba_cerrado.gif");
	aux[1] = car.carga("ZImagenes/pacman_abajo_abierto.gif");
	
	
	iconos[0] = new Animacion(aux);
	
	aux2[0] = car.carga("ZImagenes/pacman_arr_cerrado.gif");
	aux2[1] = car.carga("ZImagenes/pacman_arr_abierto.gif");
	
	
	iconos[1] = new Animacion(aux2);
	
	aux3[0]= car.carga("ZImagenes/pacman_der_cerrado.gif");
	aux3[1] = car.carga("ZImagenes/pacman_der_abierto.gif");
	
	iconos[2] = new Animacion(aux3);
	
	
	aux4[0] = car.carga("ZImagenes/pacman_izq_cerrado.gif");
	aux4[1] = car.carga("ZImagenes/pacman_izq_abierto.gif");
	
	iconos[3]= new Animacion(aux4);
}

public Image getImg(){
	return (iconos[2].getImagenIndex(1));
}
//getters y setters
public int getPuntaje() {
	return puntaje;
}

public int getCantObjetosComidos(){
	return (this.cantObjComidos);
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

public void setDir (Direccion dir){
	this.dir = dir;
}

public Direccion getDir (){
	return (this.dir);
}

public int getContador(){
	return contadorPasos;
}

public void setContador(int x){
	this.contadorPasos = x;
}

//el siguiente metodo solo sucede cuando el pacman entra a una celda donde hay una bolita
//Lo que hace es sumarle 10 puntos y poner el mapa de objetos un vacio en esa ubicación
public void comer (Position actual, MapaGeneral mapa){
	this.puntaje= this.puntaje+10;
	this.cantObjComidos++;
	//System.out.println("Puntaje actual:"+this.puntaje);
	mapa.getCelda(actual).generoVacio();
}

//entrara solo si colisiona con un fantasma y no esta en el estado poder.
public void morir (Inky ink, Pinky pin, Clyde cly, Blinky blin){
	this.setVidas(this.vidas-1);
	this.dir = Direccion.Este;
	Position pos = new Position (23,14); 
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
	Position pos1 = new Position(14,0);
	Position pos2 = new Position(14,27);
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
		this.estado= Mode.ESTADOPODER;
	}
	if (poder == false){
		this.estado = Mode.NORMAL;
	}
}
public void comerPoder (MapaGeneral mapG, Position actual){
	this.puntaje = this.puntaje+50;
	this.contadorPasos=0;
	this.cantObjComidos++;
	mapG.getCelda(actual).generoVacio();
	this.cambioEstado(true);
}

public void mover(Map mapa){
	if ((Teclado.abajo) &&(mapa.canMove(this.pos.getX()+1, this.pos.getY()))){
		this.dir = Direccion.Sur;
		}
	
	if ((Teclado.arriba) && (mapa.canMove(this.pos.getX()-1, this.pos.getY()))){
		this.dir = Direccion.Norte;
		}
	
	if ((Teclado.izquierda) && (mapa.canMove(this.pos.getX(), this.pos.getY()-1))){
		this.dir = Direccion.Este;
		}
	
	if ((Teclado.derecha) && (mapa.canMove(this.pos.getX(), this.pos.getY()+1))){
		this.dir = Direccion.Oeste;
	}
	
	switch (this.dir){
	
		case Oeste :
			if (mapa.canMove(this.pos.getX(), this.pos.getY()+1)){
				//imagenActual = iconos[2].getImagenActual();
				iconoActual =2;
				this.pos.setPositionY(pos.getY()+1);
			}
			break;
		case Este : 
			if(mapa.canMove(this.pos.getX(), this.pos.getY()-1)){
				//imagenActual = iconos[3].getImagenActual();
				iconoActual =3;
				this.pos.setPositionY(pos.getY()-1);
			}
			break;
		case Sur : 
			if (mapa.canMove(this.pos.getX()+1, this.pos.getY())) {
				//imagenActual = iconos[0].getImagenActual();
				iconoActual=0;
				this.pos.setPositionX(pos.getX() + 1 );
			}
			break;
		case Norte: 
			if (mapa.canMove(this.pos.getX()-1, this.pos.getY())){
				//imagenActual =iconos[1].getImagenActual();
				iconoActual=1;
				this.pos.setPositionX(pos.getX()-1);
			}
			break;
	}
	//iconos[iconoActual].refresh();
}

public void hayColision(Blinky blin, Inky ink, Clyde cly, Pinky pin, MapaGeneral mapG){
	if(mapG.getCelda(this.pos).hayBola()){
		this.comer(this.pos, mapG);
	}
	if (mapG.getCelda(this.pos).hayPowerBall()){
		this.comerPoder(mapG, this.pos);
	}
	if ((this.pos.equals(blin.getPos())) || (this.pos.equals(ink.getPos()))|| (this.pos.equals(cly.getPos())) || (this.pos.equals(pin.getPos()))){
		if (this.getEstado() == Mode.NORMAL){
			this.morir(ink, pin, cly, blin);
		}
	}
	if (this.getEstado() == Mode.ESTADOPODER){
		
		contadorPasos++;
		int multiplicador = this.recorroArregloMuerte();
		if (this.pos.equals(blin.getPos())){
			this.comerFantasma(blin, multiplicador+1);
			this.muerteFantasma[0]= true;
		}
		if (this.pos.equals(pin.getPos())){
			this.comerFantasma(pin,multiplicador+ 1);
			this.muerteFantasma[1]= true;
		}
		if (this.pos.equals(ink.getPos())){
			this.comerFantasma(ink,multiplicador+ 1);
			this.muerteFantasma[2]= true;
		}
		if (this.pos.equals(cly.getPos())){
			this.comerFantasma(cly,multiplicador+ 1);
			this.muerteFantasma[3]= true;
		}
		}

}

public int recorroArregloMuerte(){
	int cont=0;
	for (int i=0; i<this.muerteFantasma.length;i++){
		if (this.muerteFantasma[i])
			cont++;
	}
	return cont;
}

@Override
public void draw (Graphics g){
	
	g.drawImage(imagenActual ,  8+23*this.getPos().getY(),30+ this.getPos().getX()*23, null);
}

public void refresh(Map mapa, MapaGeneral mapG, Blinky blin, Inky ink, Clyde cly, Pinky pin) {
	
	
	iconos[iconoActual].refresh();
	this.hayColision(blin, ink, cly, pin, mapG);
	
	if (mapG.getCelda(this.pos).hayTunel()){
		Tunel tun = new Tunel(this.pos.getX(), this.pos.getY());
		tun.teletransporte(this, this.pos);	
	}
	this.mover(mapa);
	this.hayColision(blin, ink, cly, pin, mapG);
	
	if (contadorPasos == 81){
		this.inicializarMuerteFantasma();
		this.cambioEstado(false);
		contadorPasos=0;
	}
	
	imagenActual = iconos[iconoActual].getImagenActual();
	
	
	
	}
}



