package Util;

import java.awt.Image;

public class Animacion {
	
	private Image[] imagenes;
	private int imagenActual;

public Animacion(Image[] imagenes){
	this.imagenes=imagenes;
	imagenActual=0;
}

public Image[] getImagenes(){
	return imagenes;
}

public int getIndex(){
	return imagenActual;
}

public Image getImagenActual(){
	return (imagenes[imagenActual]);
}

public Image getImagenIndex(int i){
	return (imagenes[i]);
}

public void refresh(){
	imagenActual++;
	if(imagenActual == imagenes.length){
		imagenActual=0;
	}
}
	
}
