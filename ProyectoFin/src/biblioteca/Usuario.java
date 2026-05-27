package biblioteca;

import java.io.Serializable;

public class Usuario extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String status;
	private int prestamos;
	
	public Usuario (String matricula, String nombre) {
		super(nombre);
		this.matricula= matricula;
		this.status = "Activo";
		this.prestamos =0;
	}
	
	public String getMatricula () {
		return matricula;
	}
	
	public String getStatus () {
		return status;
	}
	public int getPrestamos () {
		return prestamos;
	}
	
	public boolean prestamoDisponible () {
		return (status.equals("Activo") && prestamos <3);
	}
	
	public void agregarPrestamo() {
		if (prestamos <3) {
			prestamos++;
		}
	}
	
	public void devolverPrestamo() {
		if (prestamos > 0) {
			prestamos --;
		}
	}
	
	public void bloquearUsuario() {
		this.status = "Bloqueado";
	}
	
	public void activarUsuario() {
		this.status = "Activo";
	}
	
	@Override
	public String toString () {
		return "Matricula: " + matricula + ", Nombre: " + nombre + ", Status: " + status + ", Préstamos Actuales: " + prestamos;
	}
}
