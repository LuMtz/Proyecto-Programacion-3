package biblioteca;

import java.io.*;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class Autenticacion {
	private static final String FILE_NAME = "usuarios_sistema.dat";
	private ArrayList<UsuarioLogin> listaUsuarios;
	
	public Autenticacion() {
		this.listaUsuarios = new ArrayList<>();
		cargarUsuarios();
		//Si el archivo esta vacio, se crea un administrador por defecto
		
	}
	//creamos la funcion que generara la matricula
	private String generarMatricula() {
		int siguiente = listaUsuarios.size() +1;
		return String.format("LIB-%04d",siguiente);
	}
	
	@SuppressWarnings("unchecked")
	private void cargarUsuarios() {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			return;
		}
		try(ObjectInputStream a = new ObjectInputStream (new FileInputStream (file))){
			listaUsuarios = (ArrayList<UsuarioLogin>) a.readObject();
		}catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado, se creará automáticamente");
		}catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al cargar los usuarios del sistema: " + e.getMessage());
		}
	}
	
	private void guardarUsuarios() {
		try (ObjectOutputStream b = new ObjectOutputStream(new FileOutputStream (FILE_NAME))){
			b.writeObject(listaUsuarios);
		}catch (IOException e) {
			System.err.println("Error al guardar los usuarios: " + e.getMessage());
		}
	}
	
	
	public String registrarUsuarios(String username, String password) {
		String matricula = generarMatricula();
		listaUsuarios.add(new UsuarioLogin (username, password, matricula));
		guardarUsuarios();
		return matricula;
	}
	
	//creamos una funcion que retorne la matricula por el username
	public String getMatriculaPorUsername(String username) {

		for (UsuarioLogin u: listaUsuarios) {
			if (u.getUsername().equals(username)) {
					return u.getMatricula();
			}
		}
		return null;
	}
	
	public boolean login(String username, String password) throws LoginException{
		if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			throw new LoginException ("Porfavor, ingrese todos los datos al formulario y llene todos los campos");
		}
		
		for (UsuarioLogin c: listaUsuarios) {
			if (c.getUsername().equals(username)) {
				if(c.getPassword().equals(password)) {
					return true;
				}else {
					throw new LoginException ("Contraseña incorrecta para el usuario: " + username);
				}
			}
		}
		throw new LoginException ("El usuario '" + username + "' no se encuentra registrado");
	}
	
}
