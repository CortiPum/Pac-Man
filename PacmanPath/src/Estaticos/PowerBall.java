package Estaticos;

import Util.Id;


public class PowerBall extends Estatico {

	
	//atributos
	private final int puntos =50;
	
	//metodos
	public PowerBall(){
		this.ID=Id.POWERBALL;
	}
	public int getPuntos(){
		return this.puntos;
	}
	
	
	//hereda el metodo getId de Personaje
	
}
