package biblioteca;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class MainBiblioteca {
	private static final Scanner scanner = new Scanner (System.in);
	private static SistemaBiblioteca sistema;
	private static Autenticacion autenticacion;
	private static BackUpAutomatico backup;
	private static Thread procesoSecundario;
	/*
	/*Controlamos el ciclio de incio de sesión. Si las credenciales no son correctas, no permite avanzar*/
	private static void ejecutarLogin() {
		boolean acceso = false;
		while (!acceso) {
			System.out.println("\n--------------------INICIO DE SESION--------------------");
			System.out.println("Usuario: ");
			String user = scanner.nextLine();
			System.out.println("Contraseña: ");
			String pass = scanner.nextLine();
			
			try {
				//Validacion logica con excepcion personalizada
				acceso = autenticacion.login(user, pass);
				System.out.println("\nAcceso concedido, bienvenido.");
			}catch (LoginException e) {
				System.out.println("Error, acceso denegado" + e.getMessage());
				System.out.println("Desea intentarlo de nuevo? (S/N): ");
				String opcion = scanner.nextLine();
				if (!opcion.equalsIgnoreCase("S")) {
					System.out.print("Cerrando aplicacion...");
					System.exit(0);
				}
			}
		}
	}
	
	//desplegamos el menu interactivo
	private static void ejecutarMenu() {
		int opc=0;
		do {
			System.out.println("\n========== MENU BIBLIOTECA ==========");
			System.out.println("1. Registrar usuario");
			System.out.println("2. Agregar un libro");
			System.out.println("3. Agregar una revista");
			System.out.println("4. Realizar un prestamo");
			System.out.println("5. Devolver material");
			System.out.println("6. Mostrar reporte");
			System.out.println("7. Exportar Respaldo (.txt)");
			System.out.println("8. Guardar y Salir");
			System.out.println("Seleccione una opcion: ");
			
			try {
				opc = Integer.parseInt(scanner.nextLine());
				switch (opc) {
				case 1:
					menuRegistrarUsuario();
					break;
				case 2: 
					menuAgregarLibro();
					break;
				case 3:
					menuAgregarRevista();
					break;
				case 4:
					menuRealizarPrestamo();
					break;
				case 5: 
					menuDevolverMaterial();
					break;
				case 6:
					menuMostrarReporte();
					break;
				case 7:
					sistema.exportarReporte();
					System.out.println("Respaldo generado con exito!");
					break;
				case 8:
					System.out.println("\nFinalizando procesos...");
					//Se detiene el hilo de respaldo de forma segura
					backup.detener();
					System.out.println("Datos guardados correctamente!");
					break;
				default: 
					System.out.println("Opcion invalida, intente de nuevo.");
				}
			}catch(NumberFormatException e) {
				System.out.println("Ingrese un numero valido");
			}catch (Exception e) {
				System.out.println("Error del sistema" + e.getMessage());
			}
		}while (opc != 8);
	}
	
	//metodos auxiliares
	private static void menuRegistrarUsuario() {
		System.out.println("\n--- REGISTRO DE USUARIO ---");
		System.out.println("Matricula: ");
		String mat = scanner.nextLine();
		System.out.println("Nombre completo: ");
		String nom = scanner.nextLine();
		
		Usuario nuevo = new Usuario (mat, nom);
		sistema.registrarUsuario(nuevo);
		System.out.println("Usuario registrado y respaldado.");
	}
	
	private static void menuAgregarLibro() {		//ocupamos leer:
		System.out.println("ID / Codigo: ");		//id/codigo
		String id = scanner.nextLine();			
		System.out.println("Titulo: ");				//Titulo
		String titulo = scanner.nextLine();
		System.out.println("Autor: ");				//Autor
		String aut = scanner.nextLine();	
		
		Libro libro = new Libro (id, titulo, aut);	//hacer un objeto de tipo Libro y agregar los datos leidos
		sistema.registrarMAterial(libro);			//mandamos a la clase sistema (en el metodo de registarMAterial) el objeto de libro
		System.out.println("Libro agregado exitosamente");		//mensaje de confirmacion (se agrego correctamente el libro)
	}
	
	private static void menuAgregarRevista() {
		System.out.println("\n --- AGREGAR NUEVA REVISTA --- ");
		System.out.println("ID / Codigo: ");
		String id = scanner.nextLine();
		System.out.println("Titulo: ");
		String titulo = scanner.nextLine();
		System.out.println("Numero de edicion: ");
		int numEd = Integer.parseInt(scanner.nextLine());
		Revista revista = new Revista (id, titulo, numEd);
		sistema.registrarMAterial(revista);
		System.out.println("Revista agregada exitosamente!.");
	}
	
	private static void menuRealizarPrestamo() {
		System.out.println("\n --- REALIZAR PRESTAMO --- ");
		System.out.println("Matricula del Usuario: ");
		String mat = scanner.nextLine();
		System.out.println("ID del material solicitado: ");
		String idMat = scanner.nextLine();
		
		try {
			sistema.realizarPrestamo(mat, idMat);
			System.out.println("Prestamo procesado de forma exitosa.");
		}catch (Exception e) {
			System.out.println("Operacion cancelada" + e.getMessage());
		}
	}
	
	private static void menuDevolverMaterial () {
		System.out.println("\n --- DEVOLUCION DE MATERIAL ---");
		System.out.println("Ingrese el ID del prestamo: ");
		int idPrestamo = Integer.parseInt(scanner.nextLine());
		
		try {
			sistema.devolverMaterial(idPrestamo);
			System.out.println("Material recibdo.");
		}catch (Exception e) {
			System.out.println("Operacion cancelada" + e.getMessage());
		}
	}
	
	private static void menuMostrarReporte() {
		System.out.println("\n === REPORTES GENERALES DE LA BIBLIOTECA ===");
		System.out.println("\n ----- USUARIOS REGISTRADOS -----");
		if (sistema.getUsuarios().isEmpty()) {
			System.out.println("No hay usuarios registrados");
		}
		for(Usuario u: sistema.getUsuarios()) {
			System.out.println(u);
		}
		
		System.out.println("\n--- INVENTARIO DE MATERIALES ---");
		if(sistema.getMateriales().isEmpty()) {
			System.out.println("No hay materiales en el inventario");
		}
		for (Material m : sistema.getMateriales()) {
			System.out.println(m);
		}
		
		System.out.println("\n--- HISTORIAL DE PRESTAMOS ---");
		if(sistema.getPrestamos().isEmpty()) {
			System.out.println("No hay materiales registrados en el sistema");
		}
		for (Prestamo p: sistema.getPrestamos()) {
			System.out.println("ID: " + p.getIdPrestamo() + "Usuario: " + p.getIdUsuario() + "Material: "+ p.getIdMaterial() + "Estado: " + p.getEstado());
		}
	}
	
	public static void main(String[] args) {
		//Inicializar el sistema
		sistema = new SistemaBiblioteca();
		autenticacion = new Autenticacion();
		
		System.out.println("SISTEMA DE GESTION DE BIBLIOTECA");
		//ejecutarLogin();
		//Fase donde se inicializa la concurrencia
		//Se inicializa el hilo para que haga un respaldo cada 60 segundos
		backup = new BackUpAutomatico (sistema, 60);
		procesoSecundario = new Thread (backup);
		procesoSecundario.start();
		
		ejecutarMenu();
	}
}
