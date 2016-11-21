package EstadoJ;

import java.awt.Graphics2D;

/**
*Interface de los Estados del juego.
*@author Cortizas Tomás ; Peraza Orlando.
*@version 2.0
*/
public interface EstadoJuego {

	void draw(Graphics2D g);
	void refresh();
}


