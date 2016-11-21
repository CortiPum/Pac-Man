package Estaticos;

import java.awt.Graphics;

import MapaBuscador.Position;
import Util.CargaImagen;
import Util.Identificador;
/**
 * Esta clase modela vacio.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */
public class Vacio extends Estatico {

	//hereda Image imagen
	//hereda Position pos
	//hereda el metodo getId de Personaje
/**
 *Crea vacio asignandole un ID y la posiciona en el mapa. 
 */
public Vacio(int x, int y){
	this.ID=Identificador.VACIO;
	this.pos = new Position (y,x);
	this.inicializarImagen();
}
/**
 * Carga la imagen del vacio.	
 */
public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
	this.imagen = car.carga("ZImagenes/black.gif");
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

