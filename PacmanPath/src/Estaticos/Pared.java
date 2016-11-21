package Estaticos;

import java.awt.Graphics;

import MapaBuscador.Position;
import Util.CargaImagen;
import Util.Identificador;

/**
 * Esta clase modela una pared.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */
public class Pared extends Estatico {

	//hereda Image imagen
	//hereda Position pos
	//hereda el metodo getId de Personaje
	

/**
 *Crea una pared asignandole un ID y la posiciona en el mapa. 
 */
public Pared(int x, int y){
	this.ID=Identificador.PARED;
	this.pos = new Position (y,x);
	this.inicializarImagen();
	}

/**
 * Carga la imagen de la pared.
 */
public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
	this.imagen = car.carga("ZImagenes/wall.gif");
}
/**
 *Para dibujar la imagen, toma la posición actual del objeto, la multiplica por 23
 *  tanto en x, como y, dado que las imagenes tienen ese tamaño, y se suma 8 en x para que
 *  empiece en el borde izquierdo, y en y se le suma 30, para que empiece desde el borde superior 
 */
@Override
public void draw(Graphics g) {
	g.drawImage(this.imagen,this.pos.getX()*23+8, this.pos.getY()*23+30, null);	
	}

	

}

