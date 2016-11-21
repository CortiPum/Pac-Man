package Ventanas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import Controlador.Mouse;
import Controlador.Teclado;
import EstadoJ.ArregloEstados;
/** Esta clase modela la superficie donde se va a dibujar todo.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class SuperficieDibujo extends Canvas{
	
	private ArregloEstados estadoJuego;
	private Teclado teclado;
	private Mouse mouse;
/**
 * Crea la superficie.
 */
public SuperficieDibujo(){
	teclado = new Teclado();
	mouse = new Mouse();
	this.addMouseListener(mouse);
	this.addKeyListener(teclado);
	this.setFocusable(true);
	this.estadoJuego = new ArregloEstados();
    this.setIgnoreRepaint(false);
    
}

/**
 * Dibuja los distintos estados del juego.
 */
public void draw(){
	
	BufferStrategy buffer = this.getBufferStrategy();
	if (buffer == null){
		this.createBufferStrategy(3);  //es cola de imagenes en espera, atiende siempre al primero
		return;
	}
	
	Graphics2D g = (Graphics2D)buffer.getDrawGraphics() ;
	g.setColor(Color.black);
	g.fillRect(0, 0, 900, 800);
	
	estadoJuego.draw(g);

	Toolkit.getDefaultToolkit().sync(); //cordina el monitor con el dibujo (da compatibilidad, dibuja cuando refresca)
	g.dispose();
	buffer.show();
}

/**
 * Actualiza el estado actual en el que se encuentra el juego.
 */
public void refresh(){
	estadoJuego.refresh();
	teclado.refresh();

	}
}


