package Excepciones;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Esta clase modela una excepsi�n.
 * @author Cortizas Tom�s ; Peraza Orlando.
 *
 */
public class ExcepcionDeNombre extends Exception {
	public static final long serialVersionUID = 700L;
	private int longitud;
	public int getLongitud(){
		return longitud;
	}
	
	public ExcepcionDeNombre(int num){
		longitud= num;
	}
}
