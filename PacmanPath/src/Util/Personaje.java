package Util;

import java.awt.Graphics;

public abstract class Personaje {

	protected Id ID;
	
public Id getID(){
	return this.ID;
	}
	
public abstract void draw(Graphics g);
	
}
	

	
