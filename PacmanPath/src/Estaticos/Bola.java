package Estaticos;

import Util.*;

public class Bola extends Estatico{

	
	
	//atributos
	private final int puntos =10;
	
	
	
	//metodos
	public Bola(){
		this.ID=Id.BOLA;
	}
	
	public int getPuntos (){
		return puntos;
	}
	
	//hereda el metodo getId de Personaje
	
}
