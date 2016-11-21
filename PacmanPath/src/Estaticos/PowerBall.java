package Estaticos;

import java.awt.Graphics;

import MapaBuscador.Position;
import Util.CargaImagen;
import Util.Identificador;
/**
 * Esta clase modela una PowerBall.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */

public class PowerBall extends Estatico {

	
	private final int PUNTOS =50;
	//hereda Image imagen
	//hereda Position pos
	//hereda el metodo getId de Personaje
	//hereda el metodo getId de Personaje
	
	
/**
 *Crea una PowerBall asignandole un ID y la posiciona en el mapa. 
 */
public PowerBall(int x, int y){
	this.pos = new Position (y,x);
	this.ID=Identificador.POWERBALL;
	this.inicializarImagen();
}
/**
 * Carga la imagen de la PowerBall.	
 */
public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
	this.imagen = car.carga("ZImagenes/powerpellet.gif");
}
/**
 * 
 * @return Devuelve los puntos que suma comer la PowerBall.
 */	
public int getPuntos(){
	return this.PUNTOS;
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
