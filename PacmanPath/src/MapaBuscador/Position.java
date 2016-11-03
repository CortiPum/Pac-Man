package MapaBuscador;

public class Position { //clase de posicion
	private int positionX;
	private int positionY;
	
	
	public int getX() {
		return positionX;
	}
	
	public int getY() {
		return positionY;
	}
	
	public Position(int x, int y) {
		positionX = x;
		positionY = y;
	}

	@Override
	public boolean equals(Object pos){
		Position aux = (Position) pos;
		if ((aux.getX()==this.getX()) && (aux.getY()==this.getY()))
			return true;
			else 
				return false;
		
	}
}
