package biblioteca;

public class ExceptionLogin extends Exception{
	private static final long serialVersionUID = 1L;
	 
	public ExceptionLogin (String mensaje) {
		super (mensaje);
	}
}
