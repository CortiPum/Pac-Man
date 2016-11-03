package Ventanas;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MapaBuscador.*;

public class VentanaJugar extends JFrame{
	 
    private JLabel pac=null;
    private JLabel[][] escenario;
   
    private boolean abajo;
    private boolean arriba;
    private boolean derecha;
    private boolean izquierda;
   
   
   
    public VentanaJugar(){
        super("Pac-Man");
        JPanel panel = new JPanel();
        pac = new JLabel(new ImageIcon(getClass().getResource("/ZImagenes/pacman_aba_cerrado.gif")));
       
       
        panel.setBackground(Color.BLACK);
        pac.setFocusable(true);
        pac.addKeyListener(new ManejaEventosPorTeclado());
        //panel.add(pac);
       
        //subir
       
        Map mapa = new Map();
        escenario = new JLabel[mapa.getWidth()][mapa.getHeight()];
        for (int i =0; i< mapa.getWidth();i++){ //i seran las columnas
            for (int j=0; j<mapa.getHeight();j++){ //j las filas
                Position pos= new Position (i,j);
                switch (mapa.getObjectMap(i, j)){
               //prueba
                case 0: escenario[i][j] = new JLabel(new ImageIcon(getClass().getResource("/ZImagenes/black.gif")));
                        break;
                case 1:  escenario[i][j] = new JLabel (new ImageIcon(getClass().getResource("/ZImagenes/wall.gif")));
                        break;
               
                case 3: escenario[i][j] = new JLabel (new ImageIcon(getClass().getResource("/ZImagenes/pacdot.gif")));
                        break;
                       
                case 4: escenario[i][j] = new JLabel (new ImageIcon(getClass().getResource("/ZImagenes/powerpellet.gif")));
                        break;
                       
                case 5: escenario[i][j] = new JLabel(new ImageIcon(getClass().getResource("/ZImagenes/black.gif")));
                        break;
                       
                       
                }
                escenario[23][13] = pac;
                panel.add(escenario[i][j]);
            }
        }
       
        abajo=false;
        arriba=false;
        derecha=false;
        izquierda=false;
       
       
       
        pac.setLocation(250,250);
       
       
       
        this.setPreferredSize(new Dimension(800, 900));
        this.getContentPane().add(panel);
        this.pack();
        this.setVisible(true);
       
       
    }
   
class ManejaEventosPorTeclado extends KeyAdapter{
   
    @Override
    public void keyPressed (KeyEvent evt){
        int ckey = evt.getKeyCode();
       
        if(ckey == evt.VK_UP){
            pac.setLocation(new Point((int)pac.getX(), (int)pac.getY()-10));
            if(arriba==false){
                pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_arr_cerrado.gif"))));
                arriba =true;
            }
            else{
                pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_arr_abierto.gif"))));
                arriba=false;
            }
        }
        if (ckey == evt.VK_DOWN){
            pac.setLocation(new Point((int)pac.getX(), (int)pac.getY()+10));
            if(abajo==false){
                pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_aba_cerrado.gif"))));
                abajo =true;
            }else
            {
                pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_abajo_abierto.gif"))));
                abajo =false;
            }
           
        }
        if (ckey == evt.VK_LEFT){
            pac.setLocation(new Point((int)pac.getX()-10, (int)pac.getY()));
            if(izquierda==false){
                pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_izq_cerrado.gif"))));
                izquierda =true;
            }
            else{
                pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_izq_abierto.gif"))));
                izquierda=false;
            }
           
        }
        if (ckey == evt.VK_RIGHT){
            pac.setLocation(new Point((int)pac.getX()+10, (int)pac.getY()));
            if(derecha==false){
                pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_der_cerrado.gif"))));
                derecha =true;
            }
            else{
                pac.setIcon((new ImageIcon(getClass().getResource("/ZImagenes/pacman_der_abierto.gif"))));
                derecha =false;
            }
       
        }
    }
}
 
public static void main (String[] args){
    VentanaJugar jue = new VentanaJugar();
}
}