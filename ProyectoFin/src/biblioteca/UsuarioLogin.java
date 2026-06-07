package biblioteca;

import java.io.Serializable;

public class UsuarioLogin implements Serializable{
	private static final long serialVersionUID = 1L; //importante para la serialización
	private String username;
	private String password;
	//agregamos la matricula
	private String matricula;

	public UsuarioLogin (String username, String password, String matricula) {
		this.username = username;
		this.password = password;
		this.matricula = matricula;
		
	}
	
	public String getUsername () {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getMatricula() {
		return matricula;
	}
}
