package salaDeOperaciones;

public class Interval {
	public int starTime, endTime;
	public String nombre;
	
	public Interval(String nombre, int starTime,int endTime) {
		this.nombre = nombre;
		this.starTime = starTime;
		this.endTime = endTime;
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getStartTime() {
		return starTime;
	}

	public void setStarTime(int starTime) {
		this.starTime = starTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

}
