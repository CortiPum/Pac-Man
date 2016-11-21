package MapaBuscador;
/**
 * Esta clase evalua si nos podemos mover a la posición indicada..
 * @author Cátedra de la materia.
 * @version 1.0
 */
public class AStarHeuristic  {

	public float getCost(Map map,  int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy))); 
		return result;
	}

}
