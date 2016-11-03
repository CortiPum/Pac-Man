package MapaN;
import MapaBuscador.*;
import Estaticos.*;
public class MapaGeneral {
	
	private Celda tablero[][]; 

	public MapaGeneral(){
		Map mapa= new Map ();
		tablero = new Celda[mapa.getWidth()][mapa.getHeight()]; 
		//preguntar si se puede modificar el Map dado por la catedra para asignar valores al mapa general
		for (int i =0; i< mapa.getWidth();i++){ //i seran las columnas
			for (int j=0; j<mapa.getHeight();j++){ //j las filas
				Position pos= new Position (i,j);
				switch (mapa.getObjectMap(i, j)){
				
				case 0: Vacio empty = new Vacio();
						tablero[i][j] = new Celda (pos, empty);
						break;
				case 1: Pared wall = new Pared();
						tablero[i][j] = new Celda(pos, wall); 
						break;
				
				case 3: Bola ball = new Bola();
						tablero[i][j] = new Celda (pos,ball);
						break;
						
				case 4: PowerBall power = new PowerBall();
						tablero[i][j] = new Celda (pos, power);
						break;
						
				case 5: Tunel tun = new Tunel ();
						tablero[i][j] = new Celda (pos, tun);
						break;
						
						
				}
			}
		}
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
	
	public int getPuntajeTotal(){
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
	

}
