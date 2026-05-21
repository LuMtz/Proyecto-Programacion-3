package biblioteca;

import java.io.Serializable;

public class UsuarioLogin implements Serializable{
	private static final long serialVersionUID = 1L; //importante para la serialización
	private String username;
	private String password;

	public UsuarioLogin (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername () {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
