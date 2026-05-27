package biblioteca;

public class Revista extends Material{
	private static final long serialVersionUID = 1L;

	private int numEdicion;
	
	public Revista (String id, String titulo, int numEdicion) {
		super(id, titulo);
		this.numEdicion = numEdicion;
	}

	public int getNumEdicion() {
		return numEdicion;
	}
	
	public void setNumEdicion(int numEdicion) {
		this.numEdicion = numEdicion;
	}
	
	@Override
	public int getDiasPrestamo() {
		return 5;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Edicion no. " + numEdicion;
	}
}
