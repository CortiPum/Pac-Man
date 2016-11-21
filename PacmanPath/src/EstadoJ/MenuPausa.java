package EstadoJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Controlador.Mouse;
/**
 * Esta clase modela el estado pausa del juego.
 * Cuando se presione Esc dentro del juego, se entrará al menu de pausa, donde podra elegir entre
 * seguir jugando, resetear su partida o volver al menu principal.
 * @author Cortizas Tomás ; Peraza Orlando.
 * @version 2.0
 */
public class MenuPausa implements EstadoJuego {

	private Rectangle exit= new Rectangle( 225, 350, 200, 50);
	private Rectangle continueButton = new Rectangle (225, 300, 200, 50);
	private Rectangle restart = new Rectangle (225, 400, 200, 50);
	
/**
 * Se sobrescribe al método draw, con el cual se dibujarán en pantalla las distintas opciones en el menu de pausa.	
 */
@Override
public void draw(Graphics2D g) {
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, 800, 900);
	g.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50));
	g.setColor(Color.RED);
	g.drawString("PAUSE", 260, 100);
	g.setFont(new Font("arial", Font.BOLD, 35));
	g.drawString("EXIT", exit.x+8, exit.y+35);
	g.draw(exit);
	g.drawString("CONTINUE", continueButton.x+8, continueButton.y+35);
	g.draw(continueButton);
	g.drawString("RESTART", restart.x+8, restart.y+35);
	g.draw(restart);	
}
/**
 * Cambia al estado Jugar.
 */
public void reset(){
	Jugar.getJugar().restart();
	ArregloEstados.cambiarEstado(1);
}
/**
 * Según donde se haga click, se resetea la partida, volvemos al juego o nos dirige al menu principal.
 */
@Override
public void refresh() {	
	if(Mouse.clickIzquierdo){
		Mouse.refresh();
		if(exit.contains(Mouse.getPointer())){
			reset();
			ArregloEstados.cambiarEstado(0);		
		}
			else{
				if(continueButton.contains(Mouse.getPointer()))
					ArregloEstados.cambiarEstado(1);
				else{
					if(restart.contains(Mouse.getPointer())){
						reset();
						}
					}
			}
		}
}

}

