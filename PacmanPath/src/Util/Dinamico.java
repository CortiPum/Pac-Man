package Util;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import MapaBuscador.*;

public abstract class Dinamico extends Personaje {

	
	protected Image[] iconos;
	protected Image iconoActual;
	
	protected Position pos;
	
	
	//Movimientos
	
	public Position getPos(){
		return this.pos;
	}
	
	public void setPos(Position pos){
		this.pos=pos;
	}
	
	public void setX(int x){
		pos.setPositionX(x);
	}
	
	public void setY(int y){
		pos.setPositionY(y);
	}
	
	
}
