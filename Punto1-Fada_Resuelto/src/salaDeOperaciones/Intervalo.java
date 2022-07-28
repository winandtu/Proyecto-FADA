package salaDeOperaciones;

public class Intervalo {
	
	public int tiempoComienzo, tiempoFinal;
	public String nombre;
	
	public Intervalo(String nombre, int tiempoComienzo,int tiempoFinal) {
		this.nombre = nombre;
		this.tiempoComienzo = tiempoComienzo;
		this.tiempoFinal = tiempoFinal;
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getTiempoComienzo() {
		return tiempoComienzo;
	}

	public void setTiempoComienzo(int tiempoComienzo) {
		this.tiempoComienzo = tiempoComienzo;
	}

	public int getTiempoFinal() {
		return tiempoFinal;
	}

	public void setTiempoFinal(int tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}

}
