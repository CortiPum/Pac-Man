package EstadoJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Controlador.Mouse;

public class Reglas implements EstadoJuego {

	private Rectangle back= new Rectangle( 25, 50, 100, 50);
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 800);
		g.setColor(Color.BLUE);
		g.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		g.setColor(Color.RED);
		g.drawString("BACK", back.x+8, back.y+35);
		
		g.draw(back);
		
		g.setFont(new Font("Tahoma", Font.BOLD, 50));
		g.setColor(Color.yellow);
		String rulesTitulo= "             Reglas";
		g.drawString(rulesTitulo, 25, 75);
		
		g.setColor(Color.blue);
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		String rules = "Un jugador controla el Pac-Man,";
		g.drawString(rules,25, 150);
		
		rules = " que se mueve a través de un laberinto";
		g.drawString(rules,25, 200);
		
		rules = "Comiendo lo que se conoce como pac-puntos.";
		g.drawString(rules,25,250);
		
		rules = "Esquiva los fantasmas que cruces en tu camino.";
		g.drawString(rules,25,300);
		
		rules = "Ganaras una vez que comas todos los pacdots y powerpellets.";
		g.drawString(rules,25,350);
		
		rules = "Si un fantasma toca al Pac-Man, éste último pierde una vida.";
		g.drawString(rules,25,400);
		
		rules =  "El número inicial de vidas del Pac-Man es tres.";
		g.drawString(rules, 25, 450);
		
	}
		
	@Override
	public void refresh() {
		if (Mouse.clickIzquierdo){
			if(back.contains(Mouse.getPointer())){
				ArregloEstados.cambiarEstado(0);
			}
		}
		
	}

}
