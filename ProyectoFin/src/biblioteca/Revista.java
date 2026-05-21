package biblioteca;

public class Revista extends Material{
	private int numEdicion;
	
	public Revista (String id, String titulo, int numEdicion) {
		super(id, titulo);
		this.numEdicion = numEdicion;
	}

	@Override
	public int getDiasPrestamo() {
		return 5;
	}
}
