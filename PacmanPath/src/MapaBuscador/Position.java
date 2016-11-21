package MapaBuscador;
/**
 * Esta clase modela una posición.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */
public class Position { 
	private int positionX;
	private int positionY;
	
	/**
	 * 
	 * @return Retorna la componente x de la posición.
	 */
	public int getX() {
		return positionX;
	}
	/**
	 * 
	 * @return Retorna la componente y de la posición.
	 */
	public int getY() {
		return positionY;
	}
	/**
	 * Inicializa la posición.
	 */
	public Position(int x, int y) {
		positionX = x;
		positionY = y;
	}
	/**
	 * Setea la componente x una posición.
	 * @param x
	 */
	public void setPositionX(int x){
		this.positionX = x;
	}
	/**
	 * Setea la componente y una posición.
	 * @param y
	 */
	public void setPositionY(int y){
		this.positionY = y;
	}
	/**
	 * Se sobrescribe el método equals para comparar posiciones.
	 */
	@Override
	public boolean equals(Object pos){
		Position aux = (Position) pos;
		if ((aux.getX()==this.getX()) && (aux.getY()==this.getY()))
			return true;
			else 
				return false;
		
	}
}
