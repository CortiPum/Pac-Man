package Personaje;


import MapaBuscador.*;
import MapaN.*;
import Util.Dinamico;
import Util.Direccion;
import Util.Fantasma;
import Util.Id;
import Estados.*;

public class Pacman extends Dinamico {

	
	private int puntaje;
	private int vidas;
	private Direccion dir;  //esto ser� para la segunda entrega
	private Mode estado;
	private Path camino;  //esto solo para la primera entrega.
	
	
	
public Pacman(int x, int y, int x1, int y1){
	this.ID=Id.PACMAN;
	this.puntaje=0;
	this.dir = Direccion.Oeste; //se utilizara en la segunda entrega
	this.estado = Mode.NORMAL; //setea modo.
	this.vidas=3;
	this.crearPath(x, y, x1, y1);
	this.pos = new Position (x, y); //valores iniciales.
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

}


