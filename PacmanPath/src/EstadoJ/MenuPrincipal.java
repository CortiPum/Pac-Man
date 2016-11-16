package EstadoJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Controlador.Mouse;
import Util.Animacion;
import Util.CargaImagen;
import Ventanas.Juego;



public class MenuPrincipal implements EstadoJuego{

	private Animacion[] imagenes;
	
	public String x;
	public static int x1=1;
	
	private Rectangle playButton = new Rectangle (250, 250, 180, 50);
	private Rectangle scoreButton = new Rectangle(250, 300, 180, 50);
	private Rectangle helpButton= new Rectangle (250, 350, 180, 50);
	private Rectangle configButton= new Rectangle(250, 400, 180, 50);
	private Rectangle quitButton = new Rectangle (250, 450, 180, 50) ;
	
	private Font font;
	private Font font2;
	
public MenuPrincipal(){
	font = new Font("arial",Font.ROMAN_BASELINE, 25);
	font2 = new Font("arial", Font.BOLD, 50);
	imagenes = new Animacion[5];
	this.cargarAnimacion();
	
}

public void config(){
	
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

private void drawFrame() {
	
	final JFrame frame = new JFrame("Selector de usuarios a mostrar en el ranking");
	JPanel panel = new JPanel();
	JButton button = new JButton("Choose");
	String[] ar = new String[4];
	ar[0]= "1";
	ar[1]= "5";
	ar[2]= "10";
	ar[3]= "15";
	final JComboBox combo = new JComboBox(ar);
	
	frame.setSize(300, 90);
	frame.setVisible(true);
	
	panel.setBackground(Color.BLACK);
	panel.add(button, BorderLayout.EAST);
	panel.add(combo, BorderLayout.SOUTH);
	
	frame.add(panel);
	
	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	
	button.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			x = (String)combo.getSelectedItem();
			x1= Integer.parseInt(x);
			frame.setVisible(false);
		}
		
	});
	
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
	g.drawString("Hecho por :", 60, 690);
}

@Override
public void refresh() {
	imagenes[0].refresh();
	imagenes[1].refresh();
	imagenes[2].refresh();
	imagenes[3].refresh();
	imagenes[4].refresh();
	
	if(Mouse.clickIzquierdo){
		Mouse.refresh();
		if (playButton.contains(Mouse.getPointer())){
			ArregloEstados.cambiarEstado(1);
		}else{
			if (scoreButton.contains(Mouse.getPointer())){
				ArregloEstados.cambiarEstado(4);
			}else{
				if (helpButton.contains(Mouse.getPointer())){
					ArregloEstados.cambiarEstado(2);
				}else{
					if(quitButton.contains(Mouse.getPointer())){
						Juego.jugando=false;
					}else{
						if(configButton.contains(Mouse.getPointer())){
							drawFrame();
						}
					}
				}
			}
		
			}
		}
	}
}

/*
 * Menu principal del juego, es el estado inicial. De este estado, dependiendo de la accion
 * se podra pasar al estado juego, al reglas, ranking. Tambien se podra salir del juego y en
 * el boton configuracion aparecera una ventana con un selector para mostrar cuantos usuarios deben
 * mostrarse en el ranking
 */
