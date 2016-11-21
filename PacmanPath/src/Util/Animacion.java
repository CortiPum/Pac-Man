package Util;

import java.awt.Image;
/** Esta clase modela la animación.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/
public class Animacion {
	
	private Image[] imagenes;
	private int imagenActual;
/**
 * Crea la animación, que es un arreglo de imagenes y setea la primer imagen de dicho arreglo.
 * @param imagenes
 */
public Animacion(Image[] imagenes){
	this.imagenes=imagenes;
	imagenActual=0;
}
/**
 * 
 * @return Devuelve el arreglo de imagenes.
 */
public Image[] getImagenes(){
	return imagenes;
}

/**
 * 
 * @return Retorna el indice de la imagen actual.
 */
public int getIndex(){
	return imagenActual;
}

/**
 * 
 * @return Retorna la imagen actual.
 */
public Image getImagenActual(){
	return (imagenes[imagenActual]);
}

/**
 * 
 * @param i
 * @return Retorna una imagen de acuerdo a un indice recibido.
 */
public Image getImagenIndex(int i){
	return (imagenes[i]);
}

/**
 * Recorre el arreglo, cuando llega al final de éste comienza de nuevo.
 */
public void refresh(){
	imagenActual++;
	if(imagenActual == imagenes.length){
		imagenActual=0;
	}
}
	
}
