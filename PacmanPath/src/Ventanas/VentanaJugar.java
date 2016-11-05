package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import MapaBuscador.*;
import MapaN.MapaGeneral;
import Personaje.*;
import Personaje.Pacman;


public class VentanaJugar extends JFrame{
	 
    private Pacman pacman;
    private Blinky blinky;
    private Inky inky;
    private Clyde clyde;
    private Pinky pinky;
    
    private JPanel panel;
    
    private Image image;
    
    //private TestPacman testPacman;
   
 
    
    private boolean abajo;
    private boolean arriba;
    private boolean derecha;
    private boolean izquierda;
   
   
   
    public VentanaJugar(){
        super("Pac-Man");
        
        blinky = new Blinky();
        clyde = new Clyde();
        inky = new Inky();
        pinky = new Pinky();
        
        pacman = new Pacman(23, 13);
        panel = new JPanel();
        
        String imgFileName = "ZImagenes/wall.gif";
        
        URL imgUrl = getClass().getClassLoader().getResource(imgFileName);
        
        if (imgUrl == null) {
        	System.err.println("No se encuentra imagen: " + imgFileName);
        } else {
        try {
        	image = ImageIO.read(imgUrl);  
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        } 
        
       
        
       // testPacman = new TestPacman();
        
        /*
        
        JPanel panel = new JPanel();
        pac = new JLabel(new ImageIcon(getClass().getResource("/ZImagenes/pacman_aba_cerrado.gif")));
       
    
        panel.setBackground(Color.BLACK);
        pac.setFocusable(true);
        pac.addKeyListener(new ManejaEventosPorTeclado());
        
        Map map = new Map();
        MapaGeneral mapa = new MapaGeneral();
        mapa.setEscenario();
        JLabel[][] t = mapa.getEscenario();
        t[23][13] =pac;
        for (int i =0; i< map.getWidth();i++){ //i seran las columnas
            for (int j=0; j<map.getHeight();j++){ //j las filas
            	panel.add(t[i][j]);
            }
            }
        
        
        abajo=false;
        arriba=false;
        derecha=false;
        izquierda=false;
       
       */
       
       // pac.setLocation(250,250);
       
       
       
        this.setPreferredSize(new Dimension(644, 713));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(661, 740);
        this.setIgnoreRepaint(false);
       
        
    }

    public void paint(Graphics g) {
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, 900, 800);
    	
    	Map map = new Map();
    	System.out.println(map.getWidth()+"  "+ map.getHeight());
    	MapaGeneral mapa = new MapaGeneral();
    	
    	mapa.draw(g);
    	for (int i =0; i<30;i++){
    	pacman.draw(g);
    	blinky.draw(g);
    	pinky.draw(g);
    	inky.draw(g);
    	clyde.draw(g);
    	blinky.mover(pacman, blinky);
    	}
    	//blinky.cambioEstado(true, map);
    	/*for (int j =0; j<50;j++){
    		blinky.mover(pacman , blinky);
    		blinky.draw(g);
    		//blinky.mover(pacman, blinky);
    	}
    	//inky.estaDispercion();
    	//clyde.estaDispercion();
    	//pinky.estaDispercion();
    	//g.fillRect(0, 0, 900, 800);
    	}
	//}
*/
    }

//	class ManejaEventosPorTeclado extends KeyAdapter{
//    	   
//        @Override
//        public void keyPressed (KeyEvent evt){
//            int ckey = evt.getKeyCode();
//           
//            if(ckey == evt.VK_UP){
//                pac.setLocation(new Point((int)pac.getX(), (int)pac.getY()-25));
//                if(arriba==false){
//                    pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_arr_cerrado.gif"))));
//                    arriba =true;
//                }
//                else{
//                    pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_arr_abierto.gif"))));
//                    arriba=false;
//                }
//            }
//            if (ckey == evt.VK_DOWN){
//                pac.setLocation(new Point((int)pac.getX(), (int)pac.getY()+25));
//                if(abajo==false){
//                    pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_aba_cerrado.gif"))));
//                    abajo =true;
//                }else
//                {
//                    pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_abajo_abierto.gif"))));
//                    abajo =false;
//                }
//               
//            }
//            if (ckey == evt.VK_LEFT){
//                pac.setLocation(new Point((int)pac.getX()-25, (int)pac.getY()));
//                if(izquierda==false){
//                    pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_izq_cerrado.gif"))));
//                    izquierda =true;
//                }
//                else{
//                    pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_izq_abierto.gif"))));
//                    izquierda=false;
//                }
//               
//            }
//            if (ckey == evt.VK_RIGHT){
//                pac.setLocation(new Point((int)pac.getX()+25, (int)pac.getY()));
//                if(derecha==false){
//                    pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_der_cerrado.gif"))));
//                    derecha =true;
//                }
//                else{
//                    pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_der_abierto.gif"))));
//                    derecha =false;
//                }
//           
//            }
//        }
//    }
 
public static void main (String[] args){
    VentanaJugar jue = new VentanaJugar();
}
}