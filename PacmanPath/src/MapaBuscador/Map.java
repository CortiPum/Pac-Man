package MapaBuscador;
import java.util.Random;
/**
 * Esta clase modela los mapas del juego.
 * //El mapa agregado va a servir para instanciar el Mapa general con objetos.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */
public class Map {	

	private int mapWidth=31; //se refiere a las filas
	private int mapHeight=28; // se refiere a las columnas
	public final int CELL_SIZE=1;
	private byte NewMapObject[][] =
	{  //0 1 2 3 4 5 6 7 8 9 101112131415161718192021222324252627
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, //0   
		{1,3,3,3,3,3,3,3,3,3,3,3,3,1,1,3,3,3,3,3,3,3,3,3,3,3,3,1}, //1  //1 pared (colision)
		{1,3,1,1,1,1,3,1,1,1,1,1,3,1,1,3,1,1,1,1,1,3,1,1,1,1,3,1}, //2  //0 vacio (no colision)
		{1,4,1,0,0,1,3,1,0,0,0,1,3,1,1,3,1,0,0,0,1,3,1,0,0,1,4,1}, //3  //3 bola (no colision
		{1,3,1,1,1,1,3,1,1,1,1,1,3,1,1,3,1,1,1,1,1,3,1,1,1,1,3,1}, //4  //4 PowerUp (no colision)
		{1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1}, //5  //5 Tunel (no colision)
		{1,3,1,1,1,1,3,1,1,3,1,1,1,1,1,1,1,1,3,1,1,3,1,1,1,1,3,1}, //6
		{1,3,1,1,1,1,3,1,1,3,1,1,1,1,1,1,1,1,3,1,1,3,1,1,1,1,3,1}, //7  //preguntar si el mapa esta bien hecho en esta fila (ver figura del enunciado) Segun el mapa dado por la catedra, donde va 6 iria un espacio en blanco, segun el mapa dado en el enunciado donde va el 6 iria una pared
		{1,3,3,3,3,3,3,1,1,3,3,3,3,1,1,3,3,3,3,1,1,3,3,3,3,3,3,1}, //8
		{1,1,1,1,1,1,3,1,1,1,1,1,0,1,1,0,1,1,1,1,1,3,1,1,1,1,1,1}, //9
		{1,0,0,0,0,1,3,1,1,1,1,1,0,1,1,0,1,1,1,1,1,3,1,0,0,0,0,1}, //10
		{1,0,0,0,0,1,3,1,1,0,0,0,0,0,0,0,0,0,0,1,1,3,1,0,0,0,0,1}, //11
		{1,0,0,0,0,1,3,1,1,0,1,1,1,0,0,1,1,1,0,1,1,3,1,0,0,0,0,1}, //12
		{1,1,1,1,1,1,3,1,1,0,1,0,0,0,0,0,0,1,0,1,1,3,1,1,1,1,1,1}, //13  //este mapa NO va a ser utilizado para recorrer, solo para iniciar el mapa general (no necesario para la practica 1 :( )
		{5,0,0,0,0,0,3,0,0,0,1,0,0,0,0,0,0,1,0,0,0,3,0,0,0,0,0,5}, //14
		{1,1,1,1,1,1,3,1,1,0,1,0,0,0,0,0,0,1,0,1,1,3,1,1,1,1,1,1}, //15
		{1,0,0,0,0,1,3,1,1,0,1,1,1,1,1,1,1,1,0,1,1,3,1,0,0,0,0,1}, //16
		{1,0,0,0,0,1,3,1,1,0,0,0,0,0,0,0,0,0,0,1,1,3,1,0,0,0,0,1}, //17
		{1,0,0,0,0,1,3,1,1,0,1,1,1,1,1,1,1,1,0,1,1,3,1,0,0,0,0,1}, //18
		{1,1,1,1,1,1,3,1,1,0,1,1,1,1,1,1,1,1,0,1,1,3,1,1,1,1,1,1}, //19
		{1,3,3,3,3,3,3,3,3,3,3,3,3,1,1,3,3,3,3,3,3,3,3,3,3,3,3,1}, //20
		{1,3,1,1,1,1,3,1,1,1,1,1,3,1,1,3,1,1,1,1,1,3,1,1,1,1,3,1}, //21
		{1,3,1,1,1,1,3,1,1,1,1,1,3,1,1,3,1,1,1,1,1,3,1,1,1,1,3,1}, //22
		{1,4,3,3,1,1,3,3,3,3,3,3,3,0,3,3,3,3,3,3,3,3,1,1,3,3,4,1}, //23
		{1,1,1,3,1,1,3,1,1,3,1,1,1,1,1,1,1,1,3,1,1,3,1,1,3,1,1,1}, //24
		{1,1,1,3,1,1,3,1,1,3,1,1,1,1,1,1,1,1,3,1,1,3,1,1,3,1,1,1}, //25
		{1,3,3,3,3,3,3,1,1,3,3,3,3,1,1,3,3,3,3,1,1,3,3,3,3,3,3,1}, //26
		{1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1}, //27
		{1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1}, //28
		{1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1}, //29
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, //30
		};
	/**
	 * Unicamente para encontrar caminos.
	 */
	private byte collideMap[][]=
{   //0 1 2 3 4 5 6 7 8 9 101112131415161718192021222324252627 y  x
	 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, //0
	 {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1}, //1
	 {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1}, //2
	 {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1}, //3
	 {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1}, //4
	 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}, //5
	 {1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1}, //6
	 {1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1}, //7
	 {1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1}, //8
	 {1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1}, //9  
	 {1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1}, //10
	 {1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1}, //11
	 {1,1,1,1,1,1,0,1,1,0,1,1,1,0,0,1,1,1,0,1,1,0,1,1,1,1,1,1}, //12
	 {1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,1,1,1,1,1}, //13
	 {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0}, //14  
	 {1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,1,1,1,1,1}, //15
	 {1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1}, //16
	 {1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1}, //17
	 {1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1}, //18
	 {1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1}, //19
	 {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1}, //20
	 {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1}, //21
	 {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1}, //22
	 {1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1}, //23
	 {1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1}, //24
	 {1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1}, //25
	 {1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1}, //26
	 {1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1}, //27 
	 {1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1}, //28
	 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}, //29
	 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, //30
	};


	public int getWidth() {
		return mapWidth;
	}
	
	public int getHeight() {
		return mapHeight;
	}
	
	public byte[][] getCollidableMap() {
		return collideMap;
	}
	/**
	 * Cambia un 0 por un t(0 o 1) en el mapa.
	 * 
	 */
	public boolean addCollidable(int x, int y, byte t) {   
		if(x < 0 || y < 0 || x >= mapWidth || y >= mapHeight)
			return false;
		if(collideMap[x][y] > 0)
			return false;
		collideMap[x][y] = t;
		return true;
	}
	/**
	 * Devuelve si en la posición (x,y) hay un 1 o un 0.
	 */
	public byte getCollidable(int x, int y) { 
		if(x < 0 || y < 0 || x >= mapWidth || y >= mapHeight)
			return -1;
		return collideMap[x][y];
	}
	
	public int findDistance(Position start, Position end) { 
		return (int)Math.sqrt(Math.pow(Math.abs(start.getX()-end.getX()), 2) + Math.pow(Math.abs(start.getY()-end.getY()), 2));
	}
	/**
	 *Devuelve true si en la posión (x,y)hay un 0 (si esta vacio).
	 */
	public boolean isEmpty(int x, int y) {  
		if(x < 0 || y < 0 || x >= mapWidth || y >= mapHeight)
			return false;
		
		if(getCollidable(x, y) != 0)
			return false;
		
		return true;
	}
	/**
	 * Devuelve true si puede moverse.
	 */
	public boolean canMove(int x, int y) { 
		if(x < 0 || y < 0 || x >= mapWidth || y >= mapHeight)
			return false;
		if(getCollidable(x, y) != 0)
			return false;
		return true;
	}

	public float getCost(int sx, int sy, int tx, int ty) { 
		
		return 1;
	}

	//agregado por nosotros
	/**
	 * Devuelve si en la posición x, y, hay un 1 o un 0.
	 */
	public byte getObjectMap(int x, int y) { 
		if(x < 0 || y < 0 || x >= mapWidth || y >= mapHeight)
			return -1;
		return NewMapObject[x][y];
	}
	
	//agregado por nosotros
	/**
	 *Este método pedirá un entero random para fila (entre 0 y 29) y un entero random para columna (entre 0 y 26).
	 *Si el numero random para la fila no es 1 o 29, seguira pidiendo numeros.
	 *Si el numero random para la columna no es 1 o 26 seguira pidiendo numeros.
	 *El metodo devuelve entonces una de las siguientes combinaciones:
	 *(1,1) (1,26), (29,1),(29,26) que corresponden a las esquinas.
	 *
	 */
	public Position getEsquinaRandom(){ 
		Random r = new Random();		
		int fil= (int) (r.nextDouble() * 30);			
		Random r2 = new Random();		
		int col=(int) (r2.nextDouble() * 27);			
		while ((fil != 29)&&(fil!=1)){	// (1,1) (1,26), (29,1),(29,26) que corresponden a las esquinas
			fil = (int) (r.nextDouble() * 30);
			//System.out.println(fil);
		}
		while ((col != 26)&&(col != 1)){
			col = (int) (r2.nextDouble() * 27);
			//System.out.println(col);
		}
		Position esqRandom = new Position (fil, col);
		return esqRandom;
		
	}
}
