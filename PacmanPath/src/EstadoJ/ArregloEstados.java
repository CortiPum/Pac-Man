package EstadoJ;

import java.awt.Graphics2D;
/** 
 * Esta clase representa los 5 estados posibles del juego a traves de un arreglo de estados. 
 * En el arreglo se almacenan los distintos estados: jugar, pausa, menu principal, reglas y ranking.
 * @author Cortizas Tomás ; Peraza Orlando. 
 * @version 2.0
 */


public class ArregloEstados implements EstadoJuego {

	public static EstadoJuego[] estados;
	public static EstadoJuego estadoActual;
/**
 * Crea el arreglo de 5 estados.	
 */
public ArregloEstados(){
	estados = new EstadoJuego[5];
	inicializarEstado();
	inicializarEstadoActual();
}

/**
 * Al arreglo de estados en cada posición lo inicializa con su respectivo estado.	
 */
public void inicializarEstado(){
	estados[0] = new MenuPrincipal();
	estados[1] = Jugar.getJugar();
	estados[2] = new Reglas();
	estados[3] = new MenuPausa();
	estados[4] = new Rank();
	
}

/**
 * Devuelve el estado actual.	
 */
public EstadoJuego getEstadoActual(){
	return estadoActual;
}

/**
 * Inicializa el estado actual.	
 */
public void inicializarEstadoActual(){
	estadoActual = estados[0];
}

/**
 * Cambia el estado actual por el que se le indique.
 */
public static void cambiarEstado(int i){
	estadoActual= estados[i];
}

/**
 * Actualiza el estado actual.	
 */
@Override
public void refresh() {
	estadoActual.refresh();
}
/**
 * Dibuja el estado actual.	
 */
@Override
public void draw(Graphics2D g) {
	estadoActual.draw(g);
}
}

