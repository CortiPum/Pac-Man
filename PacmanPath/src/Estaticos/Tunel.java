package Estaticos;

import Personaje.*;
import Util.CargaImagen;
import Util.Identificador;

import java.awt.Graphics;

import MapaBuscador.*;
/**
 * Esta clase modela un tunel.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */
public class Tunel extends Estatico {

	//hereda Image imagen
	//hereda Position pos
	//hereda el metodo getId de Personaje

/**
 *Crea un Tunel asignandole un ID y la posiciona en el mapa. 
 */
public Tunel(int x, int y){
	this.ID=Identificador.TUNEL;
	this.pos = new Position (y,x);
	this.inicializarImagen();
}
	
/**
 * Carga la imagen del Tunel.	
 */
public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
	this.imagen = car.carga("ZImagenes/black.gif");
}
	
/**Método para teletransportarse.
 * 
 * @param pac
 * @param pos
 */
public void teletransporte(Pacman pac, Position pos){ 
	pac.transport(pos);
}

/**
 *Para dibujar la imagen, toma la posición actual del objeto, la multiplica por 23
 *  tanto en x, como y, dado que las imagenes tienen ese tamaño, y se suma 8 en x para que
 *  empiece en el borde izquierdo, y en y se le suma 30, para que empiece desde el borde superior 
 */	
@Override
public void draw(Graphics g) {
	g.drawImage(this.imagen, this.pos.getX()*23+8, this.pos.getY()*23+30, null);
	
}

}

