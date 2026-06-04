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
import java.awt.SystemColor;



public class InterfazLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField contrasena;
	private JTextField textUsuario;
	private JLabel lblUsuario;
	private JButton btnCrearCuenta;
	private JButton btnIniciarSesion;
	
	private Autenticacion sistemaAuth;
	
	/**
	* Launch the application.
	*/

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
		public void run() {
		try {
			InterfazLogin frame = new InterfazLogin();
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
	
	public InterfazLogin() {
		
		sistemaAuth= new Autenticacion();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 606);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		contrasena = new JPasswordField();
		contrasena.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contrasena.setBounds(361, 304, 204, 38);
		contentPane.add(contrasena);
		
		
		JLabel lblNewLabel = new JLabel("Sistema bibliotecario");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Felix Titling", Font.PLAIN, 40));
		lblNewLabel.setBounds(159, 66, 481, 95);
		
		contentPane.add(lblNewLabel);
		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textUsuario.setBounds(361, 198, 204, 38);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		
		JLabel lbContrasena = new JLabel("Contraseña");
		lbContrasena.setForeground(Color.WHITE);
		lbContrasena.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		lbContrasena.setBounds(195, 305, 120, 38);
		contentPane.add(lbContrasena);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		lblUsuario.setBounds(231, 197, 84, 38);
		contentPane.add(lblUsuario);
		
		btnCrearCuenta = new JButton("Crear cuenta");
		btnCrearCuenta.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCrearCuenta.setForeground(Color.WHITE);
		btnCrearCuenta.setBackground(new Color(64, 128, 128));
		btnCrearCuenta.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				String usuarioInput = textUsuario.getText();
				String passwordInput = new String (contrasena.getPassword());
				
				if (usuarioInput.trim().isEmpty() || passwordInput.trim().isEmpty()) {
					JOptionPane.showMessageDialog(InterfazLogin.this, "Debes llenar ambos campos para registrarte", "Campos vacios", JOptionPane.WARNING_MESSAGE);
				} else {
				
				sistemaAuth.registrarUsuarios(usuarioInput, passwordInput);
				
				JOptionPane.showMessageDialog(InterfazLogin.this, "Usuario " + usuarioInput + " registrado con exito", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
				
				textUsuario.setText("");
				contrasena.setText("");
			}
		}
	});
		btnCrearCuenta.setBounds(195, 426, 120, 27);
		contentPane.add(btnCrearCuenta);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBackground(new Color(64, 128, 128));
		btnIniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usuarioInpunt = textUsuario.getText();
				String passwordInput= new String(contrasena.getPassword());
				
				try {
					boolean exito = sistemaAuth.login(usuarioInpunt, passwordInput);
					
					if (exito) {
						JOptionPane.showMessageDialog(InterfazLogin.this, "Bienvenido al Sistema Bibliotecario " + usuarioInpunt + ", login correcto");
						
						//agregar ventana de menu
						/*VentanaMenu menu = new VentanaMenu();
						 * menu.setVisible(true);
						 * dispose();		para cerrar la ventana del login
						 */
					}
				} catch (LoginException error) {
					JOptionPane.showMessageDialog(InterfazLogin.this, error.getMessage(), "Error de autenticación", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIniciarSesion.setFont(new Font("Rockwell", Font.PLAIN, 20));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBounds(397, 413, 168, 46);
		contentPane.add(btnIniciarSesion);
	}
} 

