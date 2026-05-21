package biblioteca;

import java.io.Serializable;

public class Persona implements Serializable{
	private static final long serialVersionUID =1L;
	
	protected String nombre;
	
	public Persona (String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNOmbre(String nombre) {
		this.nombre=nombre;
	}

}
