package Ventanas;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LoadImagePac {

	private String[] imgFileName= {"imagenes/pacman_aba_cerrado", "imagenes/pacman_abajo_abierto",
			"imagenes/pacman_arr_abierto","imagenes/pacman_arr_cerrado", "imagenes/pacman_der_abierto",
			"imagenes/pacman_der_cerrado","imagenes/pacman_izq_abierto", "imagenes/pacman_izq_cerrado"};
	private ImageIcon[] img;
	private String imageF = "imagenes/pacman_aba_cerrado";
	
	
public LoadImagePac(){
	URL[] imgURL=null;
	for (int i = 0; i<= imgFileName.length;i++){
		imgURL[i]= getClass().getClassLoader().getResource(imgFileName[i]);
		if (imgURL[i] ==null){
			System.err.print("No se pudo cargar imagen");
	}}//else{
	//	try{
		//	img[i] = ImageIO.read(imgURL[i]); 
		//}catch (IOException ex){
		//	ex.printStackTrace();
		//}
	//}
		
		//}
		}

public ImageIcon getImg(int i){
	return img[i];
}
	}
	


	

