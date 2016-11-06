package Ventanas;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	private final String title;
	
public Ventana(String title, SuperficieDibujo superficie){
	this.title = title;
	inicializar(superficie);
}
	

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
