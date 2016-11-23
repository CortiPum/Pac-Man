package Excepciones;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Esta clase modela una excepsión.
 * @author Cortizas Tomás ; Peraza Orlando.
 *
 */
public class ExcepcionDeArchivo extends Exception {
	public static final long serialVersionUID = 700L;
	private int longitud;
	public int getLongitud(){
		return longitud;
	}
	
	public ExcepcionDeArchivo(int num){
		longitud= num;
	}
}
