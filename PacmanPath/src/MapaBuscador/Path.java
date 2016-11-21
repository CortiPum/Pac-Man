package MapaBuscador;
import java.util.ArrayList;

/**
 * Esta clase modela un camino.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */
public class Path {
	private ArrayList<Step> steps;
/**
 * Crea un arreglo de pasos.	
 */
	public Path() {
		steps = new ArrayList<Step>();
	}
/**
 *
 * @return Devuelve la longitud del camino.
 */
	public int getLength() {
		return steps.size();
	}
	/**
	 * 
	 * @param index
	 * @return Devuelve la posición del paso.
	 */
	public Step getStep(int index) {
		return (Step) steps.get(index);
	}
	/**
	 * 
	 * @param index
	 * @return Devuelve la componente x de la posición del paso.
	 */
	public int getX(int index) {
		return getStep(index).x;
	}
	/**
	 * 
	 * @param index
	 * @return Devuelve la componente y de la posición del paso.
	 */
	public int getY(int index) {
		return getStep(index).y;
	}
	/**
	 * Agrega un paso al arreglo de pasos.
	 * @param x
	 * @param y
	 */
	public void appendStep(int x, int y) {
		steps.add(new Step(x,y));
	}
	/**
	 * Agrega un paso al principio del arreglo.
	 * @param x
	 * @param y
	 */
	public void prependStep(int x, int y) {
		steps.add(0, new Step(x, y));
	}
	/**
	 * @param x
	 * @param y
	 * @return Devuelve true si el paso ingresado como parametro se encuentra en el arreglo de pasos.
	 */
	public boolean contains(int x, int y) {
		return steps.contains(new Step(x,y));
	}
	
	/**
	 * 
	 * @return Retorna un paso.
	 */
	public ArrayList<Step> getSteps(){
		return steps;
	}
	/**
	 * Quita un paso del arreglo de pasos.
	 * @param i
	 * @return Devuelve true en caso de poder eliminar el paso.
	 */
	public Step removeStep(int i){
		return (steps.remove(i));
	
	}
	/**
	 * Modela un paso.
	 * @author Cortizas Tomás ; Peraza Orlando.
	 *@version 2.0
	 */
	public class Step {
		private int x;
		private int y;
		
		public Step(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		
		public int hashCode() {
			return x*y;
		}

		public boolean equals(Object other) {
			if (other instanceof Step) {
				Step o = (Step) other;
				return (o.x == x) && (o.y == y);
			}
			return false;
		}
	}
}
