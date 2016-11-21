package Controlador;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
*Esta clase sirve para manejar los eventos del mouse. Se fija cuando se hace click (la variable se llama click izquierdo, pero 
*en realidad pone en true ese booleano cuando sucede algún evento de Mouseclicked (sea click derecho o izquierdo).
*@autor Cortizas Tomás ; Peraza Orlando.
*@version 2.0
*/
public class Mouse extends MouseAdapter{

	public static boolean clickIzquierdo;
	private static int x;
	private static int y;
	
/**
 * Inicializa la variable booleana en false	
 */
public Mouse(){
	clickIzquierdo = false;
}

/**
 * Cuando se hace un click, se guarda la posición en la que se hizo el click y se pone la variable booleana en true.
 */

public void mouseClicked(MouseEvent e){
	x=e.getX();
	y=e.getY();
	clickIzquierdo = true;
}

/**
 * Se refresca la variable booleana, en case de estar en true, se pone en false.
 */

public static void refresh() {
	if (clickIzquierdo) {		
		clickIzquierdo = !clickIzquierdo;
	}
}

/**
 * Se retorna el punto donde fue hecho el click.
 */

public static Point getPointer(){
	return new Point(x,y);
}

}


