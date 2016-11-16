package EstadoJ;

import java.awt.Graphics2D;


public class ArregloEstados implements EstadoJuego {

	public static EstadoJuego[] estados;
	public static EstadoJuego estadoActual;
	
public ArregloEstados(){
	estados = new EstadoJuego[5];
	inicializarEstado();
	inicializarEstadoActual();
}

public void inicializarEstado(){
	estados[0] = new MenuPrincipal();
	estados[1] = Jugar.getJugar();
	estados[2] = new Reglas();
	estados[3] = new MenuPausa();
	estados[4] = new Rank();
	
}

public EstadoJuego getEstadoActual(){
	return estadoActual;
}


public void inicializarEstadoActual(){
	estadoActual = estados[0];
}

public static void cambiarEstado(int i){
	estadoActual= estados[i];
}


@Override
public void refresh() {
	estadoActual.refresh();
}

@Override
public void draw(Graphics2D g) {
	estadoActual.draw(g);
}
}

/*
 * es un arreglo que dentro tiene los 5 estados posibles del juego
 * el de jugar, el de la pausa, el del menu principal, el de reglas y el del ranking
 */
