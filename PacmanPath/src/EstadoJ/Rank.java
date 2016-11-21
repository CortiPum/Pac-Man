package EstadoJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Controlador.Mouse;
import Util.Ranking;
import Util.Usuario;
/**
*Esta clase modela el ranking de los usuarios.
*Tiene un boton back, para volver al menu principal.
*@author Cortizas Tomás ; Peraza Orlando.
*@version 2.0
*/
public class Rank implements EstadoJuego {

	private Rectangle back = new Rectangle ( 25, 50, 100, 50);
	private Usuario[] users;
	
/**
 * Este método según cual sea la posición que se seleccione, es la dimensión que se le va a asignar al arreglo que va a mostrar el ranking.	
 */
public Rank(){
	users = Ranking.getRanking().getUserPos();	
}
/**
 * Dibuja el menu.
 */
@Override
public void draw(Graphics2D g) {
	g.setColor(Color.black);
	g.fillRect(0, 0, 900, 800);
	g.setFont(new Font("Tahoma", Font.BOLD, 30));
	g.setColor(Color.RED);
	g.drawString("BACK", back.x+8, back.y+35);
	
	g.draw(back);
	
	g.setColor(Color.RED);
	g.setFont(new Font("Tahoma", Font.BOLD, 30));
	g.drawString("HIGHSCORE", 270, 50);
	
	g.setColor(Color.BLUE);
	g.setFont(new Font("Tahoma", Font.ITALIC, 20));
	for(int i =0 ; i<MenuPrincipal.x1;i++){
		g.drawString(i+1+"."+users[i].getNombre()+" "+users[i].getPuntos()+" "+users[i].getTiempo(), 100, (30*i+1)+200);
	}
	
}
/**
 * Si se hace click sobre el botón "back" no dirigirá al menu principal.
 */
@Override
public void refresh() {
	
	if (Mouse.clickIzquierdo){
		if(back.contains(Mouse.getPointer())){
			ArregloEstados.cambiarEstado(0);
		}
	
	}
}

}

