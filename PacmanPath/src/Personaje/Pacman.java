package Personaje;


import MapaBuscador.*;
import MapaN.*;
import Util.Animacion;
import Util.CargaImagen;
import Util.Dinamico;
import Util.Direccion;
import Util.Fantasma;
import Util.Identificador;

import java.awt.Graphics;
import java.awt.Image;
import Controlador.Teclado;
import Estados.*;
import Estaticos.Tunel;
/**
 * Esta clase modela al pacman.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */
public class Pacman extends Dinamico {
	
	private int puntaje;
	private int vidas;
	private Direccion dir;  
	private Mode estado;
	private int contadorPasos;
	private boolean[] muerteFantasma;
	private int cantObjComidos;
	
	private long tiem;
	
	
/**
 * Genera al pacman.
 * @param x
 * @param y
 */
	
public Pacman(int x, int y){
	super();
	this.tiem=0;
	this.ID=Identificador.PACMAN;
	this.cantObjComidos=0;
	this.contadorPasos=0;
	this.muerteFantasma = new boolean[5];
	this.inicializarMuerteFantasma();
	this.inicializarImagenes();
	this.puntaje=0;
	this.dir = Direccion.Este; 
	this.estado = Mode.NORMAL; //setea modo.
	this.vidas=3;
	this.pos = new Position (x, y); //valores iniciales.
	
}
/**
 * Resetea los valores del pacman.
 */
public void reset(){
	this.cantObjComidos=0;
	this.contadorPasos=0;
	this.tiem=0;
	this.puntaje=0;
	this.dir = Direccion.Este; 
	this.estado = Mode.NORMAL; //setea modo.
	this.vidas=3;
	this.inicializarMuerteFantasma();
	this.pos= new Position (23,14);
}
/**
 * Este método se utiliza para inicilizar un arreglo, que luego se va a usar para multiplicar los puntos del pacman cuando come un fantasma.
 */
public void inicializarMuerteFantasma(){
	for (int i =0; i<4;i++)
		this.muerteFantasma[i]=false;
}
/**
 * Retorna un arreglo de animación.
 * @return
 */
public Animacion[] getIconos(){
	return (this.iconos);
}
/**
 * Inicializa los arreglos de imagenes del pacman, los que luego se van a utilizar para las animaciones.
 */
private void inicializarImagenes(){
	CargaImagen car = new CargaImagen();
	Image[] aux = new Image[2];
	Image[] aux2 = new Image[2];
	Image[] aux3 = new Image[2];
	Image[] aux4 = new Image[2];
	Image[] aux5 = new Image[10];
	
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
	
	aux5[0] = car.carga("ZImagenes/muerte1.png");
	aux5[1] = car.carga("ZImagenes/muerte2.png");
	aux5[2] = car.carga("ZImagenes/muerte3.png");
	aux5[3] = car.carga("ZImagenes/muerte4.png");
	aux5[4] = car.carga("ZImagenes/muerte5.png");
	aux5[5] = car.carga("ZImagenes/muerte6.png");
	aux5[6] = car.carga("ZImagenes/muerte7.png");
	aux5[7] = car.carga("ZImagenes/muerte8.png");
	aux5[8] = car.carga("ZImagenes/muerte9.png");
	aux5[9] = car.carga("ZImagenes/muerte10.png");
	
	iconos[4] = new Animacion(aux5);
}
/**
 * 
 * @return Devuelve la imagen en particular.
 */
public Image getImg(){
	return (iconos[2].getImagenIndex(1));
}
//getters y setters
/**
 * 
 * @return Devuelve los puntos actuales.
 */
public int getPuntaje() {
	return puntaje;
}


/**
 * 
 * @return Devuelve la cantidad de puntos que lleva acumulados.
 */
public int getCantObjetosComidos(){
	return (this.cantObjComidos);
}
/**
 * Setea una cantidad de puntos dados.
 * @param puntaje
 */
public void setPuntaje(int puntaje) {
	this.puntaje = puntaje;
}

/**
 * 
 * @return Retorna la cantidad de vidas.
 */
public int getVidas() {
	return vidas;
}

/**
 * Setea una cantidad de vidas.
 * @param vidas
 */
public void setVidas(int vidas) {
	this.vidas = vidas;
}


//hereda el metodo getId de Personaje
/**
 * 
 * @return REtorna el estado actual.
 */
public Mode getEstado(){
	return this.estado;
}
 /**
  * Setea un estado.
  * @param estado
  */
public void setEstado(Mode estado){
	this.estado=estado;
}

/**
 * Setea una dirección.
 * @param dir
 */
public void setDir (Direccion dir){
	this.dir = dir;
}

/**
 * 
 * @return Retorna una dirección.
 */
public Direccion getDir (){
	return (this.dir);
}

/**
 * 
 * @return Retorna un contador de pasos.
 */
public int getContador(){
	return contadorPasos;
}
/**
 * Setea un valor entero al contador de pasos.
 * @param x
 */
public void setContador(int x){
	this.contadorPasos = x;
}

/**
 * El siguiente método solo sucede cuando el pacman entra a una celda donde hay una bolita.
 *Lo que hace es sumarle 10 puntos y poner el mapa de objetos un vacio en esa ubicación.
 * @param actual
 * @param mapa
 */

public void comer (Position actual, MapaGeneral mapa){
	this.puntaje= this.puntaje+10;
	this.cantObjComidos++;
	mapa.getCelda(actual).generoVacio();
}

/**
 * Entrará solo si colisiona con un fantasma y no esta en el estado poder. 
 * Muere y comienza de nuevo con una vida menos.
 * @param ink
 * @param pin
 * @param cly
 * @param blin
 */
public void morir (Inky ink, Pinky pin, Clyde cly, Blinky blin){
	this.setVidas(this.vidas-1);
	this.dir = Direccion.Este;
	this.tiem = System.currentTimeMillis();
	Position pos = new Position (23,14); 
	this.setPos(pos); //posicion donde comienza
	ink.setPosInicial();
	pin.setPosInicial();
	cly.setPosInicial();
	blin.setPosInicial();
	
	
}

/**
 * Sirve para evaluar si puede jugar de acuerdo a su cantidad de vidas. Si es mayor a 0 puede jugar.
 * @return Retorna true si puede jugar, false sino puede jugar.
 */
public boolean puedeJugar(){
	if (this.getVidas() > 0) return true;
	else return false;
}

/**
 * Este método es llamado por un método en la clase Tunel, dependiendo a que tunel entro, saldra por el siguiente
 * @param pos
 */
public void transport (Position pos){
	Position pos1 = new Position(14,0);
	Position pos2 = new Position(14,27);
	if (pos.equals(pos1))
		this.setPos(pos2);
		else 
			this.setPos(pos1);

	}
/**
 * Come un fantasma y dependiendo de cuantos lleve comidos en ese momento va a multiplicar sus puntos.
 * @param fan
 * @param x
 */
public void comerFantasma(Fantasma fan, int x){
	this.setPuntaje(this.puntaje+(x*200));
	fan.setPosInicial();
}
/**
 * Cambia de estado de acuerdo si come una bola de poder.
 * @param poder
 */
public void cambioEstado (boolean poder){
	if (poder == true) {
		this.estado= Mode.ESTADOPODER;
	}
	if (poder == false){
		this.estado = Mode.NORMAL;
	}
}
/**
 * Método para comer una bola de poder y sumar dicho puntaje. 
 * @param mapG
 * @param actual
 */
public void comerPoder (MapaGeneral mapG, Position actual){
	this.puntaje = this.puntaje+50;
	this.contadorPasos=0;
	this.cantObjComidos++;
	mapG.getCelda(actual).generoVacio();
	this.cambioEstado(true);
}
/**
 * Método que va a hacer que se mueva de acuerdo a la tecla con la que se le de la dirección.
 * @param mapa
 */
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
				iconoActual =2;
				this.pos.setPositionY(pos.getY()+1);
			}
			break;
		case Este : 
			if(mapa.canMove(this.pos.getX(), this.pos.getY()-1)){
				iconoActual =3;
				this.pos.setPositionY(pos.getY()-1);
			}
			break;
		case Sur : 
			if (mapa.canMove(this.pos.getX()+1, this.pos.getY())) {
				iconoActual=0;
				this.pos.setPositionX(pos.getX() + 1 );
			}
			break;
		case Norte: 
			if (mapa.canMove(this.pos.getX()-1, this.pos.getY())){
				iconoActual=1;
				this.pos.setPositionX(pos.getX()-1);
			}
			break;
	}
}
/**
 * Controla las colisiones. Controla que hacer de acuerdo con lo que choque.
 * @param blin
 * @param ink
 * @param cly
 * @param pin
 * @param mapG
 */
public void hayColision(Blinky blin, Inky ink, Clyde cly, Pinky pin, MapaGeneral mapG){
	if(mapG.getCelda(this.pos).hayBola()){
		this.comer(this.pos, mapG);
	}
	if (mapG.getCelda(this.pos).hayPowerBall()){
		this.comerPoder(mapG, this.pos);
	}
	if ((this.pos.equals(blin.getPos()))){
		if (blin.getModo()!=Mode.ASUSTADO){
			this.morir(ink, pin, cly, blin);
		}
	}
	if(this.pos.equals(ink.getPos())){
		if (ink.getModo()!=Mode.ASUSTADO){
			this.morir(ink, pin, cly, blin);
		}
	}
	if(this.pos.equals(pin.getPos())){
		if (pin.getModo()!=Mode.ASUSTADO){
			this.morir(ink, pin, cly, blin);
		}
	}
	if(this.pos.equals(cly.getPos())){
		if (cly.getModo()!=Mode.ASUSTADO){
			this.morir(ink, pin, cly, blin);
		}
	}
	if (this.getEstado() == Mode.ESTADOPODER){
		
		contadorPasos++;
		int multiplicador = this.recorroArregloMuerte();
		if ((this.pos.equals(blin.getPos())) && (blin.getModo()==Mode.ASUSTADO)){
			this.comerFantasma(blin, multiplicador+1);
			this.muerteFantasma[0]= true;
		}
		if ((this.pos.equals(pin.getPos()))&&(pin.getModo()==Mode.ASUSTADO)){
			this.comerFantasma(pin,multiplicador+ 1);
			this.muerteFantasma[1]= true;
		}
		if ((this.pos.equals(ink.getPos()))&&(ink.getModo()==Mode.ASUSTADO)){
			this.comerFantasma(ink,multiplicador+ 1);
			this.muerteFantasma[2]= true;
		}
		if ((this.pos.equals(cly.getPos()))&&(cly.getModo()==Mode.ASUSTADO)){
			this.comerFantasma(cly,multiplicador+ 1);
			this.muerteFantasma[3]= true;
		}
		}

}
/**
 * Sirve para controlar el multiplicador de puntos cuando se comen fantasmas.
 * @return 
 */
public int recorroArregloMuerte(){
	int cont=0;
	for (int i=0; i<this.muerteFantasma.length;i++){
		if (this.muerteFantasma[i])
			cont++;
	}
	return cont;
}

/**
 * Dibuja el pacman.
 */
@Override
public void draw (Graphics g){
	
	g.drawImage(imagenActual ,  8+23*this.getPos().getY(),30+ this.getPos().getX()*23, null);
}

/**
 * Controla el movimiento, la animación, las colisiones y los estados.
 * @param mapa
 * @param mapG
 * @param blin
 * @param ink
 * @param cly
 * @param pin
 */
public void refresh(Map mapa, MapaGeneral mapG, Blinky blin, Inky ink, Clyde cly, Pinky pin) {
	if(System.currentTimeMillis()-this.tiem >1000){
		
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
/**
 * M{etodo utilizado para controlar un pequeño stop de 1 segundo, producido cuando muere el pacman.
 * @return
 */
public long getTiem(){
	return this.tiem;
}

}

