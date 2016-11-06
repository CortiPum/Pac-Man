package MapaN;
import MapaBuscador.*;
import Util.CargaImagen;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Estaticos.*;
public class MapaGeneral {
	
	private Celda tablero[][]; 
	protected Image[] iconos;
	protected Image iconoActual;
	

	public MapaGeneral(){
		Map mapa= new Map ();
		iconos = new Image[7];
		inicializarImagen();
		tablero = new Celda[mapa.getWidth()][mapa.getHeight()]; 
		for (int i =0; i< mapa.getWidth();i++){ //i seran las filas
			for (int j=0; j<mapa.getHeight();j++){ //j columnas
				Position pos= new Position (i,j);
				switch (mapa.getObjectMap(i, j)){
				
				case 0: Vacio empty = new Vacio(i,j);
						tablero[i][j] = new Celda (pos, empty);
						break;
				case 1: Pared wall = new Pared(i,j);
						tablero[i][j] = new Celda(pos, wall);				
						break;
				
				case 3: Bola ball = new Bola(i,j);
						tablero[i][j] = new Celda (pos,ball);
						break;
						
				case 4: PowerBall power = new PowerBall(i,j);
						tablero[i][j] = new Celda (pos, power);
						break;
						
				case 5: Tunel tun = new Tunel (i,j);
						tablero[i][j] = new Celda (pos, tun);
						break;
						
						
				}
			}
		}
		
	}
	
public void inicializarImagen(){  //carga las imagenes en un booleano de imagenes (llama a la clase carga imagen)
	CargaImagen car = new CargaImagen();
		
	this.iconos[0] = car.carga("ZImagenes/wall.gif");
	this.iconos[1] = car.carga("ZImagenes/pacdot.gif");
	this.iconos[2] = car.carga("ZImagenes/black.gif");
	this.iconos[3] = car.carga("ZImagenes/powerpellet.gif");
	this.iconos[4] = car.carga("ZImagenes/azul.gif");
}
	

public Celda[][] getTablero() {
	return tablero;
}

public void setTablero(Celda[][] tablero) {
	this.tablero = tablero;
}
	
public Celda getCelda(Position pos){
	int x = pos.getX();
	int y = pos.getY();
	return tablero[x][y];
}
	
public int getPuntajeTotal(){ //devuelve la cantidad de puntos que puede ganar un jugador en el mapa.
	Map mapa = new Map();
	int total=0;
	for (int i =0; i< mapa.getHeight();i++){
		for (int j=0; j<mapa.getWidth();j++){
			if (mapa.getObjectMap(i, j) == 3)
				total=total+10;	//pacdots
				if (mapa.getObjectMap(i, j)== 4)
					total = total+50 ;//powerball
				}
		}
		return total;
}	
	
public void draw (Graphics g){ //ver abajo
	Map map = new Map();
	for (int x = 0; x < map.getWidth(); x++){
    	for (int y = 0; y < map.getHeight(); y++){
    		Position pos = new Position (x,y);
    		this.getCelda(pos).getContenido().draw(g);
    }
    	}
	}

}


/*el metodo draw va a recorrer el mapa de objetos ya creado. En el for, agarra cada celda,
 *  y va preguntando por el objeto que esta contenido en esa celda, para despues
 *  llamar al draw de ese objeto, ejemplo, si en la celda 0, 0 hay una pared
 */