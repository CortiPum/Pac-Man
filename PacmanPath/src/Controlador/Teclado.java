package Controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Esta clase se utiliza para controlar los eventos del teclado.
 * @author Cortizas Tom�s ; Peraza Orlando.
 *@version 2.0
 */
public class Teclado extends KeyAdapter{

	private final int cantTeclas = 256; 
	private boolean[] teclas;

	public static boolean arriba;
	public static boolean abajo;
	public static boolean derecha;
	public static boolean izquierda;
	public static boolean pause;
/**
 * Se crea el arreglo teclas con un tama�o de 256.
 */

public Teclado() {
	teclas = new boolean[cantTeclas]; 
}
		
/**
 * Este m�todo actualiza el estado de las variables que representan a las en base a los eventos ocurridos en el teclado.
 */
		
public void refresh() {
	pause = teclas[KeyEvent.VK_ESCAPE];
	arriba = teclas[KeyEvent.VK_UP];
	abajo = teclas[KeyEvent.VK_DOWN];
	derecha = teclas[KeyEvent.VK_RIGHT];
	izquierda = teclas[KeyEvent.VK_LEFT];
}
/**
 * Seg�n cual sea la tecla presionada en el teclado, pone en true el en arreglo en la posici�n que haga referencia a dicha tecla.
 */	
@Override
public void keyPressed(KeyEvent e) {
	teclas[e.getKeyCode()] = true;
	e.consume();                           // que hace e.consume()???
}

/**
 * Seg�n cual sea la tecla que se libera en el teclado, pone en false el en arreglo en la posici�n que haga referencia a dicha tecla.
 */
	
@Override
public void keyReleased(KeyEvent e) {
	teclas[e.getKeyCode()] = false;
	e.consume();
}


}

/* se crea un arreglo de booleanos, el cual se pondra true cierta ubicacion, 
 * solo cuando la tecla es presionada. Cuando se suelta, cambia el valor a false
 */