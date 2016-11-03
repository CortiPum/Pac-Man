package Util;
import MapaBuscador.*;

public abstract class Dinamico extends Personaje {

	
	protected Position pos;
	
	
	//Movimientos
	
	public Position getPos(){
		return this.pos;
	}
	
	public void setPos(Position pos){
		this.pos=pos;
	}
}
