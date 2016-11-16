package EstadoJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import Controlador.Mouse;
import Util.Animacion;
import Util.CargaImagen;

public class Reglas implements EstadoJuego {

	private Rectangle back= new Rectangle( 25, 50, 100, 50);
	private Animacion[] imagenes;
	int x =0;
	
public Reglas(){
	imagenes = new Animacion[5];
	this.cargarAnimacion();
}

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
		
	g.drawImage(imagenes[0].getImagenActual(), x-160, 550, 30,30, null);
	g.drawImage(imagenes[1].getImagenActual(), x-120, 550, 30,30, null);
	g.drawImage(imagenes[2].getImagenActual(), x-80, 550, 30,30, null);
	g.drawImage(imagenes[3].getImagenActual(), x-40, 550, 30,30, null);
	g.drawImage(imagenes[4].getImagenActual(), x, 550, 30,30, null);
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
public void refresh() {
	x= x+15;
	if (x == 855)
		x=0;
	imagenes[0].refresh();
	imagenes[1].refresh();
	imagenes[2].refresh();
	imagenes[3].refresh();
	imagenes[4].refresh();
	if (Mouse.clickIzquierdo){
		if(back.contains(Mouse.getPointer())){
			ArregloEstados.cambiarEstado(0);
		}
	}
	
}

}
/*Este estado muestra por pantalla unos String con las reglas del juego. Además, al igual
*que el menu principal tiene una animacion y un boton para volver al menu principal
*/