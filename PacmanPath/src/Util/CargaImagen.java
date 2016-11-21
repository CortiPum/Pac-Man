package Util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
/** Esta clase se utiliza para externalizar la carga de imagenes.
* @author Cortizas Tomás ; Peraza Orlando.
* @version 2.0
*/

public class CargaImagen {

/**
 * Este método carga la imagen.
 * @param nombreImg
 * @return Retorna una imagen.
 */
public Image carga(String nombreImg){
	
	String imgFileName = nombreImg;
	Image image=null;
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
    return image;
}
}
