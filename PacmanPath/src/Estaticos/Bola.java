package Estaticos;

import java.awt.Graphics;

import MapaBuscador.Position;
import Util.*;

public class Bola extends Estatico{

	private final int PUNTOS =10;
	//hereda Image imagen
	//hereda Position pos
	//hereda el metodo getId de Personaje
	
	
	
public Bola(int x, int y){
	this.ID=Id.BOLA;
	this.pos = new Position (y,x);
	this.inicializarImagen();
}
	
public void inicializarImagen(){
	CargaImagen car = new CargaImagen();
	this.imagen = car.carga("ZImagenes/pacdot.gif");
	}
	
public int getPuntos (){
	return PUNTOS;
}

@Override
public void refresh() {
	// TODO Auto-generated method stub
	}

@Override
public void draw(Graphics g) {
	g.drawImage(this.imagen, this.pos.getX()*23+8, this.pos.getY()*23+30, null);
		
}

}

/*para dibujar la imagen, agarra la posicion actual del objeto, la multiplica por 23
 *  tanto en x, como y, dado que las imagenes tienen ese tamaño, y se suma 8 en x para que
 *  empieze en el borde izquierdo, y en y se le suma 30, para que empiece desde el borde superior
 */