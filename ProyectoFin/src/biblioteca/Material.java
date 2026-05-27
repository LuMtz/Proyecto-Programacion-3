package biblioteca;

import java.io.Serializable;

public abstract class Material implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String titulo;
	private boolean disponible;
	
	public Material (String id, String titulo) {
		this.id = id;
		this.titulo = titulo;
		this.disponible = true;
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	//cada material decide por cuantos dias se presta
	public abstract int getDiasPrestamo();
	
	@Override
	public String toString() {
		return "ID: " + id + "|Titulo: " + titulo + "| Estado: " + (disponible ? "Disponible" : "PRestado");
	}
}
