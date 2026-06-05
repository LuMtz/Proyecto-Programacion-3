package biblioteca;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMatricula;
	private JTextField textNombre;
	private SistemaBiblioteca sistema;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameUsuario frame = new JFrameUsuario();
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
	public JFrameUsuario(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 606);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.sistema = new SistemaBiblioteca();
		
		JLabel lblRegistroDeUsuario = new JLabel("REGISTRO DE USUARIO");
		lblRegistroDeUsuario.setVerticalAlignment(SwingConstants.CENTER);
		lblRegistroDeUsuario.setOpaque(true);
		lblRegistroDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeUsuario.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblRegistroDeUsuario.setBackground(Color.WHITE);
		lblRegistroDeUsuario.setBounds(180, 34, 426, 69);
		contentPane.add(lblRegistroDeUsuario);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1.setBounds(0, 11, 797, 5);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1.setBounds(0, 126, 797, 5);
		contentPane.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("");
		lblNewLabel_1_2_1_1_2.setOpaque(true);
		lblNewLabel_1_2_1_1_2.setBounds(0, 553, 797, 5);
		contentPane.add(lblNewLabel_1_2_1_1_2);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1_1.setBounds(10, 126, 6, 462);
		contentPane.add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1_1_1.setBounds(782, 126, 6, 462);
		contentPane.add(lblNewLabel_1_2_1_1_1_1_1);
		
		JLabel lblMatriculaDeUsuario = new JLabel("Matricula de Usuario:");
		lblMatriculaDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatriculaDeUsuario.setForeground(Color.WHITE);
		lblMatriculaDeUsuario.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblMatriculaDeUsuario.setBounds(170, 181, 267, 38);
		contentPane.add(lblMatriculaDeUsuario);
		
		textMatricula = new JTextField();
		textMatricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textMatricula.setColumns(10);
		textMatricula.setBounds(447, 181, 204, 38);
		contentPane.add(textMatricula);
		
		JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
		lblNombreCompleto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreCompleto.setForeground(Color.WHITE);
		lblNombreCompleto.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblNombreCompleto.setBounds(170, 262, 267, 38);
		contentPane.add(lblNombreCompleto);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNombre.setColumns(10);
		textNombre.setBounds(447, 262, 204, 38);
		contentPane.add(textNombre);
		
		JButton btnRegistrarUser = new JButton("Registrar User");
		btnRegistrarUser.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String mat = textMatricula.getText();
		        String nom = textNombre.getText();
		        
		        if (mat.trim().isEmpty() || nom.trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null,"Asegurate de ingresar datos correctamente.","Error",JOptionPane.ERROR_MESSAGE);
		            return;
		        } else {
		            // Utilizamos un else para hacer lo siguiente en dado caso que los campos de llenado de informacion no esten vacios
		            Usuario nuevo = new Usuario(mat, nom);
		            sistema.registrarUsuario(nuevo);
		        
		            JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente."); // Mensaje que indica que se agrego correctamente el usuario
		        
		            // Limpiar campos para un posible nuevo agregado
		            textMatricula.setText("");
		            textNombre.setText("");
		        }
		    }
		});
		btnRegistrarUser.setOpaque(true);
		btnRegistrarUser.setForeground(new Color(0, 0, 64));
		btnRegistrarUser.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnRegistrarUser.setBackground(Color.WHITE);
		btnRegistrarUser.setBounds(463, 347, 176, 46);
		contentPane.add(btnRegistrarUser);
		
		JButton btnNewButton = new JButton("Regresar A Menu Principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGrafico mainVentana = new mainGrafico();
				mainVentana.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnNewButton.setBounds(246, 465, 302, 44);
		contentPane.add(btnNewButton);

	}

}
