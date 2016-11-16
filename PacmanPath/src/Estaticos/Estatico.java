package Estaticos;
import java.awt.Graphics;
import java.awt.Image;

import MapaBuscador.Position;
import Util.*;

public abstract class Estatico extends Personaje {

	protected Position pos;
	protected Image imagen;
	
public abstract void draw(Graphics g);
	
}
