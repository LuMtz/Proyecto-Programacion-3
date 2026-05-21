package biblioteca;

public class Libro extends Material{
	private String autor;
	
	public Libro (String id, String titulo, String autor) {
		super (id,titulo);
		this.autor = autor;
	}

	@Override
	public int getDiasPrestamo() {
		return 15;
	}
}
