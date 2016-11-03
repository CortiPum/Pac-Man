package Estaticos;

import Personaje.*;
import Util.Id;
import MapaBuscador.*;

public class Tunel extends Estatico {

	
	
//metodos
public Tunel(){
	this.ID=Id.TUNEL;
}
	
	
//hereda el metodo getId de Personaje
	
//metodo para teletransportarse.
public void teletransporte(Pacman pac, Position pos){ //llamar a un metodo del pacman que genere el teletransporte
	pac.transport(pos);
}
}
