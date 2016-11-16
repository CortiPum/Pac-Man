package EstadoJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Controlador.Mouse;

public class MenuPausa implements EstadoJuego {

	private Rectangle exit= new Rectangle( 225, 350, 200, 50);
	private Rectangle continueButton = new Rectangle (225, 300, 200, 50);
	private Rectangle restart = new Rectangle (225, 400, 200, 50);
	
	
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

public void reset(){
	Jugar.getJugar().restart();
	ArregloEstados.cambiarEstado(1);
}
	
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
/*
 * Cuando se aprete escape dentro del juego, se entrara al menu de pausa, donde podra elegir
 * si seguir jjugando, si resetear su partida o si volver al menu principal.
 * */
