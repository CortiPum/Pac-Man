package MapaN;
import MapaBuscador.*;
import Util.Id;
import Util.Personaje;
import Estaticos.*;


public class Celda {

	private Position pos;
	private Personaje contenido;
	
	
public Celda(Position pos, Personaje contenido){
	this.pos=pos;
	this.contenido=contenido;
}
	//metodos
	
public Position getPosicion (){
	return pos;
}

public Personaje getContenido(){
	return contenido;
}

public void setPosicion(Position pos){
	this.pos= pos;
}

public void setContenido(Personaje contenido){
	this.contenido=contenido;
}
	//metodos para preguntar que hay en la celda

public boolean hayBola(){
	return (contenido.getID()==Id.BOLA);
}

public boolean hayPared(){
	return (contenido.getID()==Id.PARED);
}

public boolean hayVacio(){
	return (contenido.getID()==Id.VACIO);
}

public boolean hayPowerBall(){
	return (contenido.getID()==Id.POWERBALL);
}

public boolean hayTunel(){
	return (contenido.getID()==Id.TUNEL);
}


//genera vacio cuando el pacman come una pelotita de poder o un powerpellet
public void generoVacio(){
	Vacio vac = new Vacio(this.pos.getX(), this.pos.getY());
	this.setContenido(vac);
}


}

