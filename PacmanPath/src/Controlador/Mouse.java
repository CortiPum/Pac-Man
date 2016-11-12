package Controlador;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{

	public static boolean clickIzquierdo;
	private static int x;
	private static int y;
	
public Mouse(){
	clickIzquierdo = false;
}

public void mouseClicked(MouseEvent e){
	x=e.getX();
	y=e.getY();
	clickIzquierdo = true;
}

public static void refresh() {
	if (clickIzquierdo) {		
		clickIzquierdo = !clickIzquierdo;
	}
}

public static Point getPointer(){
	return new Point(x,y);
}

}
