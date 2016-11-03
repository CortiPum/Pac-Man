package Test;

import MapaBuscador.*;
import Personaje.*;
import MapaN.*;
import Estaticos.*;
import Estados.*;



 //solo para probar algunas cosas, no es entregable
public class TestPacman {

	public TestPacman() {
		// TODO Auto-generated method stub
		
			//creo mapa general
			MapaGeneral mapa = new MapaGeneral();
			Map mapaCol = new Map();
		
		
			//selecciono donde comenzara el pacman (x,y) y donde finalizara (x1,y1)
		
			int x = 23;
			int y = 13;
			int x1 = 23;
			int y1 = 1;  // bolita de poder
		 
			//creo pacman (crea el camino en el constructor)
			Pacman pac = new Pacman(x, y, x1,y1);
			
			//creo fantasmas
			Blinky blin = new Blinky();
			Pinky pin = new Pinky();
			Clyde cly = new Clyde(); 
			Inky ink = new Inky(); 
			
			int pod=0; //este pod sirve para controlar los 20 pasos en el estado poder
			
			// devuelve 2170 (217 bolitas, falta ver puntos bola de poder)System.out.println(mapa.getPuntajeTotal());
			
			//movimiento Pacman
			int longcam = pac.getcamino().getLength();
			Position actual = new Position (1,1); //creo una posicion
			
			for (int i =0; i <= longcam -1 ;i++){
			
				//mueve el pacman
				System.out.println("-----Turno Pacman-----");
				actual=pac.movimiento();  
				
				//mover fantasmas
				
				System.out.println("-----Turno Blinky-----");
				blin.mover(pac, mapaCol, blin); 
				
				
				System.out.println("-----Turno Pinky-----");
				pin.mover(pac, mapaCol, blin);
				
				
				if(pac.getPuntaje()>=30){
					//Inky sale
					ink.setModo(Mode.PERSECUCION); //le cambia el estado
					System.out.println("-----Turno Inky-----");
					ink.mover(pac, mapaCol, blin);
				}
				if (pac.getPuntaje() >= (mapa.getPuntajeTotal()*3)/4){
					//sale clyde
					cly.setModo(Mode.PERSECUCION);
					cly.cambioEstado(false, pac, mapaCol);
					System.out.println("-----Turno Clyde-----");
					cly.mover(pac, mapaCol, blin);
				}
				//termina movimiento fantasmas
			
			
				if (mapa.getCelda(actual).hayBola()) {  
					pac.comer(actual, mapa); //acá dentro genero vacio
					}
				if (mapa.getCelda(actual).hayTunel()){
					Tunel tun = new Tunel();
					tun.teletransporte(pac, actual);
				}
				
				if (mapa.getCelda(actual).hayPowerBall()){
					pac.cambioEstado(true); //lo deja en estadopoder
					blin.cambioEstado(true, mapaCol); //lo deja en estado asustado
					pin.cambioEstado(true,mapaCol); //lo deja en estado asustado
					ink.cambioEstado(true,mapaCol); //lo deja en estado asustado, si no esta inactivo
					cly.cambioEstado(true, pac,mapaCol); //lo deja en estado asustado, si no esta inactivo
					
				}
				
				
				if (pac.getEstado()== Mode.ESTADOPODER){ //se controlan los pasos en el estado que come fantasmas.
					pod++;
					if(pod==20) {
						pac.cambioEstado(false);
						blin.cambioEstado(false,mapaCol);
						pin.cambioEstado(false,mapaCol);
						ink.cambioEstado(false,mapaCol);
						cly.cambioEstado(false, pac,mapaCol);
						pod =0;
						
					}
					
				}
			
				if (mapa.getCelda(actual).hayFantasma()){
					if(pac.getEstado()==Mode.NORMAL){ 
						pac.morir(ink, pin, cly, blin);
					}//los pasa como parametros para resetear su posicion
					else{
						//ver como manejar multliplicador
						if(mapa.getCelda(actual).hayFantasmaBlinky()) pac.comerFantasma(blin, x1);
						if(mapa.getCelda(actual).hayFantasmaClyde()) pac.comerFantasma(cly, x1);
						if(mapa.getCelda(actual).hayFantasmaInky())  pac.comerFantasma(ink, x1);
						if(mapa.getCelda(actual).hayFantasmaPinky()) pac.comerFantasma(pin, x1);
					}
						
					}
				
			}
	}
			
					
				}
	


