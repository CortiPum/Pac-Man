package Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ranking implements Serializable{
	
	private BufferedReader buffer;
	private File archivo;
	private Usuario[] userPos;
	private JFrame frame;
	private JTextField userName;
	private int puntajeActual;
	private String tiempoActual;
	private static Ranking ranking = new Ranking();
	
public Ranking(){
	userPos = new Usuario[21];
	archivo = new File ("src/puntaje.txt");
	for (int i =0; i<userPos.length;i++){
		Usuario usuario = new Usuario("",0,"5:00");
		userPos[i]=usuario;
	}
	
	}

public Usuario[] getUserPos(){
	this.leer();
	return userPos;
}

public static Ranking getRanking(){
	return ranking;
}

public void agregar(String nombre, Integer puntos, String tiempo){
	this.leer();
	if(puntos > userPos[userPos.length-1].getPuntos()){
			Usuario userAux= new Usuario (nombre, puntos, tiempo);
			userPos[userPos.length-1]= userAux;
			Arrays.sort(userPos);
	}

}

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
			//System.out.println(str);
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


public void guardarPuntaje(int puntos, String tiempo){
	frame = new JFrame();
	JPanel newPanel = new JPanel();
	
	JLabel label = new JLabel("Enter username:");
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
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// pone el ultimo score con el nombre indicado
			agregar(userName.getText(), puntajeActual, tiempoActual);
			frame.setVisible(false);
			rank();
		}
	});
	/*while ((userName.getText().compareTo("")==0)||(userName.getText().length()<2)){
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// pone el ultimo score con el nombre indicado
				agregar(userName.getText(), puntajeActual, tiempoActual);
				frame.setVisible(false);
				rank();
			}
		});
	}*/
}
}


/*

public void useFileWriter( ) {
	
    String fileName = "src/puntuacion.txt";
	
    String content = "";
    
    for(int i=0;i< puntos.length;i++){
    	content = content + nombre[i]+","+puntos[i]+"\n";
	}
  
	 Writer writer = null;
		
	try {
		writer = new FileWriter(fileName);
		writer.write(content);
		
	} catch (IOException e) {
		System.err.println("Error writing the file : ");
		e.printStackTrace();
	} finally {
		
	if (writer != null) {
		try {
			writer.close();
		}catch (IOException e) {
			System.err.println("Error closing the file : ");
			e.printStackTrace();
			}
		}
	}
}


	
public void leerUsuario(String nombre, int puntos, String tiempo){
	ObjectOutputStream salida = null;
	
	
	try{
		salida = new ObjectOutputStream ( new FileOutputStream ("C:/Users/corti/Desktop/ranking.dat"));
		salida.writeObject("Ranking");
		Usuario user = new Usuario (nombre, puntos, tiempo);
		//rank.add(user);
		//rank.sort(puntos);
		salida.writeObject(user);
	}catch(IOException e){
		e.printStackTrace();
		
	}
	
}

public void leerDesde () throws ClassNotFoundException{
	ObjectInputStream entrada = null;
	try{
		
		entrada = new ObjectInputStream( new FileInputStream("C:/Users/corti/Desktop/ranking.dat"));
		String texto = (String) entrada.readObject();
		Usuario user = (Usuario) entrada.readObject();
		
	}catch (IOException e){
		e.printStackTrace();
	}
}
}*/
