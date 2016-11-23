package Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Arrays;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Excepciones.ExcepcionDeArchivo;


/**Esta clase modela el ranking.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class Ranking implements Serializable {

	
	private BufferedReader buffer;
	private File archivo;
	private Usuario[] userPos;
	private JFrame frame;
	private JTextField userName;
	private int puntajeActual;
	private String tiempoActual;
	private static Ranking ranking = new Ranking();

/**
 * Crea el ranking.
 */
public Ranking(){
	userPos = new Usuario[21];
	archivo = new File ("src/puntaje.txt");
	for (int i =0; i<userPos.length;i++){
		Usuario usuario = new Usuario("",0,"5:00");
		userPos[i]=usuario;
	}
	
	}
 
/**
 * 
 * @return Devuelve el arreglo de usuarios con altos puntajes.
 */
public Usuario[] getUserPos(){
	this.leer();
	return userPos;
}

/**
 * 
 * @return Retorna el ranking.
 */
public static Ranking getRanking(){
	return ranking;
}

/**
 * Agrega un usuario al ranking.
 * @param nombre
 * @param puntos
 * @param tiempo
 */
public void agregar(String nombre, Integer puntos, String tiempo){
	this.leer();
	if(puntos > userPos[userPos.length-1].getPuntos()){
			Usuario userAux= new Usuario (nombre, puntos, tiempo);
			userPos[userPos.length-1]= userAux;
			Arrays.sort(userPos);
	}

}


/**
 * Escribe en el archivo de rankings.
 */
public void rank(){
	File archivo = new File ("src/puntaje.txt");
	Writer writer = null;
	String str = "";
	
	try{
		if (archivo.exists()){
			writer= new FileWriter("src/puntaje.txt");
			for (int i = 0; i<userPos.length;i++){
				if(userPos[i].getPuntos()!=0)
					str = str+userPos[i].getNombre()+","+userPos[i].getPuntos()+","+userPos[i].getTiempo()+'\n';
			}
			writer.write(str);
			writer.close();
		}else{
			writer=new FileWriter("src/puntaje.txt");
			writer.close();
		}	
		
	}catch(IOException e){
		e.printStackTrace();
		
	}
}


/**
 * Lee desde el archivo ranking.
 */
public void leer(){
	
	  String fileName = "src/puntaje.txt";

      String line = null;

      try {
          FileReader fileReader = new FileReader(fileName);

          BufferedReader bufferedReader = new BufferedReader(fileReader);
         
          int i = 0;
          while((line = bufferedReader.readLine()) != null) {
          	
  			String[] result = line.split(",");
  			userPos[i].setNombre(result[0]);
  			userPos[i].setPuntos(Integer.parseInt(result[1]));
  			userPos[i].setTiempo(result[2]);
  			i++;
  			
          }     	
          bufferedReader.close();         
      }
      catch(FileNotFoundException ex) {
          System.out.println("Unable to open file '" +  fileName + "'");                
      }
      catch(IOException ex) {
          System.out.println("Error reading file '"  + fileName + "'");                  
      }
}

/**
 * Genera una ventana de dialogo en la cual se va a igresar en nombre del jugador, pera que se guarde su puntaje en el archivo de ranking.
 * @param puntos
 * @param tiempo
 */
public void guardarPuntaje(int puntos, String tiempo){
	frame = new JFrame();
	JPanel newPanel = new JPanel();
	
	JLabel label = new JLabel("Nombre:");
	label.setForeground(Color.WHITE);
	
	userName = new JTextField(20);
	
	
	 
	JButton btn = new JButton("Accept");
	newPanel.add(label, BorderLayout.NORTH);
	newPanel.add(userName, BorderLayout.CENTER);
	newPanel.setVisible(true);
	
	newPanel.add(btn,BorderLayout.SOUTH);
	newPanel.setBackground(Color.BLACK);

	
	frame.add(newPanel, BorderLayout.CENTER);
	frame.setVisible(true);
	frame.setSize(380, 110);
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	puntajeActual = puntos;
	tiempoActual = tiempo;
	btn.addActionListener(new ActionListener() {
		/**
		 * Una vez ingresado el nombre en el textField, en base a dicho evento se almacena en el archivo dicho puntaje. 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// pone el ultimo score con el nombre indicado
			if(userName.getText().length()>20)
				try {
					throw new ExcepcionDeArchivo(userName.getText().length());
				} catch (ExcepcionDeArchivo e1) {
					//newPanel.add(label, BorderLayout.NORTH);
					JFrame ventana = new JFrame();
					ventana.setSize(new Dimension (200, 175));
					JLabel labelError = new JLabel("Su nombre es demasiado largo!");
					ventana.add(labelError, BorderLayout.CENTER);
					//ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ventana.setResizable(false);
					ventana.setVisible(true);
					ventana.setTitle("Error");
					//e1.printStackTrace();
				} 
			agregar(userName.getText(), puntajeActual, tiempoActual);
			frame.setVisible(false);
			rank();
		}
	});
}
}


