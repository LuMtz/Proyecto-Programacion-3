package biblioteca;

public class Libro extends Material{
	private static final long serialVersionUID = 1L;
	
	private String autor;
	
	public Libro (String id, String titulo, String autor) {
		super (id,titulo);
		this.autor = autor;
	}

	public String getautor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	@Override
	public int getDiasPrestamo() {
		return 15;
	}
	
	@Override
	public String toString() {
		//Llamamos al texto base y solo le sumamos el autor
		return super.toString() + "Autor: " + autor;
	}
}
