package biblioteca;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.security.auth.login.LoginException;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.net.URL;



public class InterfazLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField contrasena;
	private JTextField textUsuario;
	private JLabel lblUsuario;
	private JButton btnCrearCuenta;
	private JButton btnIniciarSesion;
	
	private Autenticacion sistemaAuth;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnSalir;
	private JTextField textNombre;
	
	/**
	* Launch the application.
	*/

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
		public void run() {
		try {
			InterfazLogin frame = new InterfazLogin();
			frame.setTitle("SISTEMA DE BIBLIOTECA");
			frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				}
			}
		});
	}
	
	/**
	* Create the frame.
	*/
	private void cambiarIcono() {
		//me asuste,crei q iba a perder todo el progreso
		//se iba a apagar por no tener pila
		try {
			URL urlIcono = getClass().getResource("chilly.jpg");
			if (urlIcono != null) {
				ImageIcon miIcono = new ImageIcon(urlIcono);
				setIconImage(miIcono.getImage());
			}else {
				System.out.println("Error: No se encontro la imagen");
			}
		}catch(Exception e) {
			System.out.println("Ocurri un error alcargar la imagen");
		}
	}
	
	
	public InterfazLogin() {
		
		sistemaAuth= new Autenticacion();
		cambiarIcono();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 811, 606);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		contrasena = new JPasswordField();
		contrasena.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contrasena.setBounds(336, 321, 204, 38);
		contentPane.add(contrasena);
		
		
		JLabel lblNewLabel = new JLabel("Sistema bibliotecario");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Felix Titling", Font.BOLD, 40));
		lblNewLabel.setBounds(150, 26, 496, 66);
		
		contentPane.add(lblNewLabel);
		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textUsuario.setBounds(336, 254, 204, 38);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNombre.setColumns(10);
		textNombre.setBounds(336, 182, 204, 38);
		contentPane.add(textNombre);
		
		
		JLabel lbContrasena = new JLabel("Contraseña:");
		lbContrasena.setForeground(Color.WHITE);
		lbContrasena.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lbContrasena.setBounds(176, 321, 150, 38);
		contentPane.add(lbContrasena);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblUsuario.setBounds(226, 252, 100, 38);
		contentPane.add(lblUsuario);
		
		btnCrearCuenta = new JButton("Crear cuenta");
		btnCrearCuenta.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnCrearCuenta.setForeground(new Color(0,0,64));
		btnCrearCuenta.setBackground(new Color(255, 255, 255));
		btnCrearCuenta.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				
				//crear cuenta con datos ingresados
				String usuarioInput = textUsuario.getText(); 											//obtener el nombre del usuario
				String passwordInput = new String (contrasena.getPassword());							//obtener la contraseña del usuario
				String nombreInput = textNombre.getText();
				
				if (usuarioInput.trim().isEmpty() || passwordInput.trim().isEmpty() || nombreInput.trim().isEmpty()) {
					JOptionPane.showMessageDialog(InterfazLogin.this, "Debes llenar ambos campos para registrarte", "Campos vacios", JOptionPane.WARNING_MESSAGE);
																										//validar que los campos tengan informacion
				} else {
					
					String matriculaGenerada = sistemaAuth.registrarUsuarios(usuarioInput, passwordInput);
					//guardamos usuario en SistemaBiblioteca para distintas funciones como prestamos
					SistemaBiblioteca sistema = new SistemaBiblioteca();
					Usuario nuevo = new Usuario(matriculaGenerada, nombreInput);
					sistema.registrarUsuario(nuevo);								
				
					JOptionPane.showMessageDialog(InterfazLogin.this, "Usuario " + usuarioInput + " registrado con exito. "+ "\nTu matricula es: "+matriculaGenerada, "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
				
					textUsuario.setText("");																//limpiar la zona de escritura (Usuario)
					contrasena.setText("");																	//limpiar la zona de escritura (Contraseña)
					textNombre.setText("");
					mainGrafico mainVentana = new mainGrafico(matriculaGenerada);
					mainVentana.setVisible(true);
					dispose();
			}
		}
	});
		btnCrearCuenta.setBounds(151, 423, 175, 46);
		contentPane.add(btnCrearCuenta);
		
		
		
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBackground(new Color(255, 255, 255));
		btnIniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usuarioInpunt = textUsuario.getText();
				String passwordInput= new String(contrasena.getPassword());
				
				try {
					boolean exito = sistemaAuth.login(usuarioInpunt, passwordInput);
					
					if (exito) {
						String matricula = sistemaAuth.getMatriculaPorUsername(usuarioInpunt);
						
						JOptionPane.showMessageDialog(InterfazLogin.this,"Bienvenido alSistema Biblioteca "+ usuarioInpunt + "\nTu matricula es: "+matricula+ ",login correcto");
						//agregar ventana de menu
						/*VentanaMenu menu = new VentanaMenu();
						 * menu.setVisible(true);
						 * dispose();		para cerrar la ventana del login
						 */
						mainGrafico mainVentana = new mainGrafico(matricula);
						mainVentana.setVisible(true);
						dispose();
					}
				} catch (LoginException error) {
					JOptionPane.showMessageDialog(InterfazLogin.this, error.getMessage(), "Error de autenticación", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnIniciarSesion.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnIniciarSesion.setForeground(new Color(0,0,64));
		btnIniciarSesion.setBounds(491, 423, 175, 46);
		btnIniciarSesion.setOpaque(true);
		contentPane.add(btnIniciarSesion);
		
		
		
		//=====================================DISEÑO======================================================================================================
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 107, 797, 5);
		lblNewLabel_1.setOpaque(true);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBounds(0, 10, 797, 5);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Se necesita crear/tener un usuario para poder acceder a las funciones.");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Felix Titling", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(226, 123, 425, 23);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("NOTA:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Felix Titling", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(180, 127, 48, 14);
		contentPane.add(lblNewLabel_4);
		
		btnSalir = new JButton("Salir\r\n");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setOpaque(true);
		btnSalir.setForeground(new Color(0, 0, 64));
		btnSalir.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(333, 503, 150, 38);
		contentPane.add(btnSalir);
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblNombre.setBounds(226, 182, 100, 38);
		contentPane.add(lblNombre);
	}
} 
//vamos agenerar la matricula automaticamente y vamos a pedir el nombre aqui en el login
