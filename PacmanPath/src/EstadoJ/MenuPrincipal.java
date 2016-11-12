package EstadoJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JButton;

import Controlador.Mouse;
import Personaje.*;
import Util.Animacion;
import Util.CargaImagen;
import Ventanas.Juego;


public class MenuPrincipal implements EstadoJuego{

	private Animacion[] imagenes;
	
	public Rectangle playButton = new Rectangle (250, 250, 170, 50);
	public Rectangle scoreButton = new Rectangle(250, 300, 170, 50);
	public Rectangle helpButton= new Rectangle (250, 350, 170, 50);
	public Rectangle configButton= new Rectangle(250, 400, 170, 50);
	public Rectangle quitButton = new Rectangle (250, 450, 170, 50) ;
	
	public Font font;
	public Font font2;
	
public MenuPrincipal(){
	font = new Font("arial",Font.ROMAN_BASELINE, 25);
	font2 = new Font("arial", Font.BOLD, 50);
	imagenes = new Animacion[5];
	this.cargarAnimacion();
	
}

public void cargarAnimacion(){
	CargaImagen car = new CargaImagen();
	Image[] aux = new Image[2];
	Image[] aux2 = new Image[2];
	Image[] aux3 = new Image[2];
	Image[] aux4 = new Image[2];
	Image[] aux5 = new Image[2];
	
	aux[0] = car.carga("ZImagenes/red3.gif");
	aux[1] = car.carga("ZImagenes/red4.gif");
	
	
	imagenes[0] = new Animacion(aux);
	
	aux2[0] = car.carga("ZImagenes/pink3.gif");
	aux2[1] = car.carga("ZImagenes/pink4.gif");
	
	
	imagenes[1] = new Animacion(aux2);
	
	aux3[0]= car.carga("ZImagenes/skyblue3.gif");
	aux3[1] = car.carga("ZImagenes/skyblue4.gif");
	
	imagenes[2] = new Animacion(aux3);
	
	
	aux4[0] = car.carga("ZImagenes/orange3.gif");
	aux4[1] = car.carga("ZImagenes/orange4.gif");
	
	imagenes[3]= new Animacion(aux4);

	aux5[0] = car.carga("ZImagenes/pacman_der_abierto.gif");
	aux5[1] = car.carga("ZImagenes/pacman_der_cerrado.gif");
	
	imagenes[4] = new Animacion(aux5);
	
}

@Override
public void draw(Graphics2D g) {
	g.fillRect(0, 0, 800, 900);	
	
	g.setFont(font2);
	g.setColor(Color.YELLOW);
	g.drawString("Pac-man", 230, 100);
	
	
	g.drawImage(imagenes[0].getImagenActual(), 170, 150, 40,40, null);
	g.drawImage(imagenes[1].getImagenActual(), 245, 150, 40,40, null);
	g.drawImage(imagenes[2].getImagenActual(), 320, 150, 40,40, null);
	g.drawImage(imagenes[3].getImagenActual(), 395, 150, 40,40, null);
	g.drawImage(imagenes[4].getImagenActual(), 470, 150, 40,40, null);

	
	
	g.setFont(font);
	g.setColor(Color.BLUE);
	g.drawString("PLAY", playButton.x+8, playButton.y+30);
	
	g.draw(playButton);
	
	g.drawString("HIGHSCORES", scoreButton.x+8 , scoreButton.y+30);
	g.draw(scoreButton);
	
	g.drawString("CONFIG", configButton.x+8, configButton.y+30);
	g.draw(configButton);
	
	g.drawString("RULES", helpButton.x+8, helpButton.y+30);
	g.draw(helpButton);
	
	g.drawString("QUIT",quitButton.x+8, quitButton.y +30);
	g.draw(quitButton);
	
	g.setColor(Color.WHITE);
	g.drawString("Hecho por :", 100, 650);
}

@Override
public void refresh() {
	imagenes[0].refresh();
	imagenes[1].refresh();
	imagenes[2].refresh();
	imagenes[3].refresh();
	imagenes[4].refresh();
	if(Mouse.clickIzquierdo){
		if (playButton.contains(Mouse.getPointer())){
			ArregloEstados.cambiarEstado(1);
		}
		if (scoreButton.contains(Mouse.getPointer())){
			
		}
		if (helpButton.contains(Mouse.getPointer())){
			ArregloEstados.cambiarEstado(2);
		}
		if(quitButton.contains(Mouse.getPointer())){
			Juego.jugando=false;
		}
		if(configButton.contains(Mouse.getPointer())){
			
		}
	}
}

}
