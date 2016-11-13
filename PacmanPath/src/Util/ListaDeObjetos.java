package Util;

import java.awt.Graphics;
import java.util.ArrayList;

import MapaN.MapaGeneral;
import Personaje.Pacman;

public class ListaDeObjetos {

	private ArrayList<Personaje> obj;
	private Pacman pacman;
	private Fantasma fantasma;
	private MapaGeneral mapaGeneral;
	
	public ListaDeObjetos(Pacman pacman)
	{
		obj = new ArrayList<Personaje>();
		this.pacman = pacman;
	}
	
	
	public void draw(Graphics g){
		for (int i = 0; i < obj.size(); i++){
			obj.get(i).draw(g);
		}
		
	}
	
	public void refresh(){
		for (int i = 0; i < obj.size(); i++){
			if (obj.get(i) instanceof Fantasma){
				Fantasma fantasma = (Fantasma) obj.get(i);
				//fantasma.refresh(pacman, fantasma);
			}
			if (obj.get(i) instanceof Pacman){
				Pacman pacman = (Pacman)obj.get(i);
				//pacman.refresh();
			}
		}
	}
	
	
	public void agregarObjeto(Personaje obj){
		this.obj.add(obj);
	}
	
	public void eliminarObjeto(Personaje obj){
		this.obj.remove(obj);
	}
	
	
}
