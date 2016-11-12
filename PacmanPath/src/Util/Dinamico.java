package Util;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import MapaBuscador.*;

public abstract class Dinamico extends Personaje {

	
	protected Animacion[] iconos;
	protected int iconoActual;
	protected Image imagenActual;
	
	
	
	protected Position pos;
	
	public Dinamico(){
		iconos = new Animacion[4];
	}
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
