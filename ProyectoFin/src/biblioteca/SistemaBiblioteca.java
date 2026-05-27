package biblioteca;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaBiblioteca {
	private ArrayList<Usuario> usuarios;
	private ArrayList<Material> materiales;
	private ArrayList<Prestamo> prestamos;
	
	private final String FILE_USUARIOS = "usuarios.dat";
	private final String FILE_MATERIALES = "materiales.dat";
	private final String FILE_PRESTAMOS = "prestamos.dat";
	
	public SistemaBiblioteca() {
		this.usuarios = new ArrayList<>();
		this.materiales = new ArrayList<>();
		this.prestamos = new ArrayList<>();
		
		cargarDatosBinarios();
	}
	
	public void registrarUsuario(Usuario nuevoUsuario) {
		usuarios.add(nuevoUsuario);
		guardarDatosBinarios();
	}
	
	public void registrarMAterial(Material nuevoMaterial) {
		materiales.add(nuevoMaterial);
		guardarDatosBinarios();
	}
	
	public void realizarPrestamo(String matricula, String idMaterial) throws Exception{
		Usuario user = buscarUsuario(matricula);
		Material mat = buscarMaterial(idMaterial);
		
		if (user == null) throw new Exception ("Error, el usuario no existe.");
		if (mat == null) throw new Exception ("Error, el material no existe.");
		if (!user.prestamoDisponible()) throw new Exception ("Error, el usuario rebasó el limite de prestamos o está bloqueado.");
		if (!mat.isDisponible())throw new Exception ("Error, el material ya se prestó");
		
		int idGenerado = prestamos.size()+ 1;
		LocalDate hoy = LocalDate.now();
		LocalDate vencimiento = hoy.plusDays(mat.getDiasPrestamo()); //Calcula la fecha de vencimiento sumando la fecha actual
		
		Prestamo nuevoPrestamo = new Prestamo (idGenerado, matricula, idMaterial, hoy, vencimiento);
		
		prestamos.add(nuevoPrestamo);
		user.agregarPrestamo();
		mat.setDisponible(false);
		
		guardarDatosBinarios();
	}
	
	public void devolverMaterial(int idPrestamo) throws Exception{
		Prestamo prestamo = null;
		for (Prestamo p : prestamos) {
			if (p.getIdPrestamo() == idPrestamo && p.isActivo()) {
				prestamo = p;
				break;
			}
		}
		if (prestamo == null) throw new Exception ("Error, prestamo no encontrado");
		
		LocalDate hoy = LocalDate.now();
		double multa = prestamo.calcularMulta(hoy);
		
		if (multa > 0) {
			System.out.println("Entrega con retraso. El usuario debe una multa de: $" + multa);
		}
		
		Usuario user = buscarUsuario (prestamo.getIdUsuario());
		Material mat = buscarMaterial (prestamo.getIdMaterial());
		
		prestamo.marcarDevuelto();
		if (user != null) user.devolverPrestamo();
		if (mat != null) mat.setDisponible(true);
		
		guardarDatosBinarios();
	}
	
	private Usuario buscarUsuario (String matricula) {
		for (Usuario u : usuarios) {
			if (u.getMatricula().equals(matricula)) return u;
		}
		return null;
	}
	
	private Material buscarMaterial(String idMaterial) {
		for (Material m : materiales) {
			if (m.getId().equals(idMaterial)) return m;
		}
		return null;
	}
	
	//Aqui guardamos el estado actual de los ArrayList en los archivos binarios
	private void guardarDatosBinarios() {
		try {
			ObjectOutputStream dU = new ObjectOutputStream (new FileOutputStream (FILE_USUARIOS));
			dU.writeObject(usuarios);
			dU.close();
			
			ObjectOutputStream dM = new ObjectOutputStream (new FileOutputStream (FILE_MATERIALES));
			dM.writeObject(materiales);
			dM.close();
			
			ObjectOutputStream dP = new ObjectOutputStream (new FileOutputStream (FILE_PRESTAMOS));
			dP.writeObject(prestamos);
			dP.close();
		}catch (IOException e) {
			System.err.println("Error al guardar los datos binarios " + e.getMessage());
		}
	}
	
	//Aqui cargamos los datos de los archivos binarios hacia la memoria
	@SuppressWarnings("unchecked")
	//INdica al compilador que ignore las advertencias relacionadas con conversiones sin comprobación.
	/*Se usa normalmente cuando se trabaja con colecciones genricas y se realiza cast sin poder garantizar el tipo
	 * en tiempo de compilación. Esta anotacion evita que aparezca esa advertencia, aunque se debe asegurar antes de que el casteo sea seguro*/
	
	private void cargarDatosBinarios () {
		try {
			File fU = new File(FILE_USUARIOS);
			if (fU.exists()) {
				ObjectInputStream oU= new ObjectInputStream (new FileInputStream (fU));
				usuarios = (ArrayList<Usuario>) oU.readObject();
				oU.close();
			}
			
			File fM = new File (FILE_MATERIALES);
			if (fM.exists()) {
				ObjectInputStream oM = new ObjectInputStream (new FileInputStream(fM));
				materiales = (ArrayList<Material>) oM.readObject();
				oM.close();
			}
			
			File fP = new File(FILE_PRESTAMOS);
			if (fP.exists()) {
				ObjectInputStream oP = new ObjectInputStream (new FileInputStream(fP));
				prestamos = (ArrayList<Prestamo>) oP.readObject();
				oP.close();
			}
		}catch (Exception e) {
			System.err.println("Error al cargar los datos binarios" + e.getMessage());
		}
	}
	
	/*Aqui exportamos el historial completo de los prestamos a un archivo de texto*/
	
	public void exportarReporte() throws Exception{
		File archivo = new File("Reporte_Historial.txt");
		BufferedWriter bW = new BufferedWriter (new FileWriter(archivo));
		
		bW.write("=== REPORTE DE HISTORIAL DE PRESTAMOS ===\n");
		bW.write("Fecha de generacion: " + LocalDate.now() + "\n\n");
		
		for (Prestamo p : prestamos) {
			bW.write(p.toString() + "\n");
		}
		
		bW.close();
	}
	
	public ArrayList<Usuario> getUsuarios(){return usuarios;}
	public ArrayList<Material> getMateriales(){return materiales;}
	public ArrayList<Prestamo> getPrestamos(){return prestamos;}
}