package Ventanas;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
/** Esta clase modela una ventana.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class Ventana extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	private final String title;

/**
 * Pone un titulo e inicializa la superficie de dubujo.
 * @param title
 * @param superficie
 */
public Ventana(String title, SuperficieDibujo superficie){
	this.title = title;
	inicializar(superficie);
}
	
/**
 * Configura los distintos valores de la ventana.
 * @param superficie
 */
private void inicializar(SuperficieDibujo superficie) {
	// Agrega el canvas y lo centra con la pantalla
	this.add(superficie);
	setSize(new Dimension(667, 800));
	
	// Deja de procesar al cerrar la ventana
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	// Inicia la ventana en el medio 
	//setLocationRelativeTo(null);
	setResizable(false);
	setVisible(true);
	setTitle(title);
	//setIconImage(Game.animations.getLife().getImage());
}

	
	
	
}
