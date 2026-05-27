package biblioteca;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idPrestamo;
	private String idUsuario;
	private String idMaterial;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	private String estado;
	
	public Prestamo(int idPrestamo, String idUsuario, String idMaterial, LocalDate fechaPrestamo,
			LocalDate fechaDevolucion) {
		this.idPrestamo = idPrestamo;
		this.idUsuario = idUsuario;
		this.idMaterial = idMaterial;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.estado = "Activo";
	}
	
	public int getIdPrestamo() {
		return idPrestamo;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}
	
	public String getIdMaterial() {
		return idMaterial;
	}
	
	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}
	
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public boolean isActivo () {
		return this.estado.equals("Activo");
	}
	
	public void marcarDevuelto () {
		this.estado = "Devuelto";
	}
	
	public int calcularDiasRetraso (LocalDate fechaActual) {
		if (isActivo() && fechaActual.isAfter(fechaDevolucion)) {
			//calcula automaticamente la diferencia exacta en dias 
			return (int) ChronoUnit.DAYS.between(fechaDevolucion, fechaActual);
		}
		return 0;
	}
 
	public double calcularMulta (LocalDate fechaActual) {
		int diasRetraso = calcularDiasRetraso (fechaActual);
		return diasRetraso * 20.0;
	}
	
	@Override
	public String toString() {
		return "========================================\n" +
			   "			TICKET PRESTAMO				\n" +
			   "========================================\n" +
			   "ID Prestamo:		" + idPrestamo + "\n" +
			   "Matricula Usuario:		" + idUsuario + "\n" +
			   "ID Material:		" + idMaterial + "\n" +
			   "Fecha Salida:		" + fechaPrestamo + "\n" +
			   "Fecha Vencimiento:		" + fechaDevolucion + "\n" +
			   "Estado Actual:		" + estado + "\n" +
			   "========================================";
	}
	
}
	
