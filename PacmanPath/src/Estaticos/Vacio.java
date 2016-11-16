package Estaticos;

import java.awt.Graphics;

import MapaBuscador.Position;
import Util.CargaImagen;
import Util.Id;

public class Vacio extends Estatico {

	//hereda Image imagen
	//hereda Position pos
	//hereda el metodo getId de Personaje
	
public Vacio(int x, int y){
	this.ID=Id.VACIO;
	this.pos = new Position (y,x);
	this.inicializarImagen();
}

public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
	this.imagen = car.carga("ZImagenes/black.gif");
}
	

@Override
public void draw(Graphics g) {
	g.drawImage(this.imagen, this.pos.getX()*23+8, this.pos.getY()*23+30, null);
}
	
}
/*para dibujar la imagen, agarra la posicion actual del objeto, la multiplica por 23
 *  tanto en x, como y, dado que las imagenes tienen ese tama�o, y se suma 8 en x para que
 *  empieze en el borde izquierdo, y en y se le suma 30, para que empiece desde el borde superior
 */
