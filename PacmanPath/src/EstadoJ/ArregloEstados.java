package EstadoJ;

import java.awt.Graphics;
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
