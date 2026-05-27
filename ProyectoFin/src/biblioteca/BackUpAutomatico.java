package biblioteca;

public class BackUpAutomatico implements Runnable{
	private SistemaBiblioteca sistema;
	private boolean activo;
	private int intSegundos;
	
	public BackUpAutomatico(SistemaBiblioteca sistema, int intSegundos) {
		this.sistema = sistema;
		this.intSegundos = intSegundos;
		this.activo = true;
	}
	
	//Metodo para detener el hilo de forma segura para cuando se cierre el programa
	public void detener() {
		this.activo = false;
	}
	
	@Override
	public void run() {
		System.out.println("Backup Automatico iniciado...");
		while (activo) {
			try {
				//El hilo se detiene durante la cantidad de segundos especificada
				Thread.sleep(intSegundos * 1000L);
				
				//El hilo despierta y hace el respaldo
				sistema.exportarReporte();
				System.out.println("Backup automatico realizado con exito");
			}catch (InterruptedException e) {
				System.err.println("El backup automatico fue interrumpido");
			}catch(Exception e) {
				System.err.println("Error al generar el backup: " + e.getMessage());
			}
		}
	}
}
