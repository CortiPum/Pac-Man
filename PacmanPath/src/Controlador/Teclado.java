package Controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Teclado extends KeyAdapter{

	private final int cantTeclas = 256; 
	private boolean[] teclas;

	public static boolean arriba;
	public static boolean abajo;
	public static boolean derecha;
	public static boolean izquierda;

	public Teclado() {
		teclas = new boolean[cantTeclas]; 
	}
		
		
	public void refresh() {
		arriba = teclas[KeyEvent.VK_UP];
		abajo = teclas[KeyEvent.VK_DOWN];
		derecha = teclas[KeyEvent.VK_RIGHT];
		izquierda = teclas[KeyEvent.VK_LEFT];
	}
		
		
	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
		e.consume();
	}


}

/* se crea un arreglo de booleanos, el cual se pondra true cierta ubicacion, 
 * solo cuando la tecla es presionada. Cuando se suelta, cambia el valor a false
 */