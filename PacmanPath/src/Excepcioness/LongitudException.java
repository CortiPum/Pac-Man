package Excepcioness;

abstract class LongitudException extends Exception{
	//public static final long serialVersionUID = 700L;
	private int longitud;
	
	public LongitudException(int longitud){
		this.longitud = longitud;
	}
	public int getLongitud(){
		return longitud;
	
	}
}
