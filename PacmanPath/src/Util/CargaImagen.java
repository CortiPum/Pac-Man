package Util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class CargaImagen {

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
