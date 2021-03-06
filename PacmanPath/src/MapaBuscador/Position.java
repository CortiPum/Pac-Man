package MapaBuscador;
/**
 * Esta clase modela una posici�n.
 * @author Cortizas Tom�s ; Peraza Orlando.
 * @version 2.0
 */
public class Position { 
	private int positionX;
	private int positionY;
	
	/**
	 * 
	 * @return Retorna la componente x de la posici�n.
	 */
	public int getX() {
		return positionX;
	}
	/**
	 * 
	 * @return Retorna la componente y de la posici�n.
	 */
	public int getY() {
		return positionY;
	}
	/**
	 * Inicializa la posici�n.
	 */
	public Position(int x, int y) {
		positionX = x;
		positionY = y;
	}
	/**
	 * Setea la componente x una posici�n.
	 * @param x
	 */
	public void setPositionX(int x){
		this.positionX = x;
	}
	/**
	 * Setea la componente y una posici�n.
	 * @param y
	 */
	public void setPositionY(int y){
		this.positionY = y;
	}
	/**
	 * Se sobrescribe el m�todo equals para comparar posiciones.
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
