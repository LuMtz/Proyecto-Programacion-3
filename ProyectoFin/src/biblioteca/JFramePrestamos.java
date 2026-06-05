package biblioteca;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JFramePrestamos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMatricula;
	private JTextField textidMaterial;
	private SistemaBiblioteca sistema;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                
	                SistemaBiblioteca sistemaPrincipal = new SistemaBiblioteca();
	                
	                
	                JFramePrestamos frame = new JFramePrestamos(sistemaPrincipal);
	                
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
	public JFramePrestamos(SistemaBiblioteca sistemaPrincipal) {
		this.sistema = sistemaPrincipal;
		getContentPane().setBackground(new Color(0,0,64));
		getContentPane().setLayout(null);
		
		JLabel lblPrestamos = new JLabel("Prestamos");
		lblPrestamos.setVerticalAlignment(SwingConstants.CENTER);
		lblPrestamos.setOpaque(true);
		lblPrestamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrestamos.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblPrestamos.setBackground(Color.WHITE);
		lblPrestamos.setBounds(189, 25, 426, 69);
		getContentPane().add(lblPrestamos);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1.setBounds(0, 9, 797, 5);
		getContentPane().add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1.setBounds(0, 114, 797, 5);
		getContentPane().add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("");
		lblNewLabel_1_2_1_1_2.setOpaque(true);
		lblNewLabel_1_2_1_1_2.setBounds(0, 553, 797, 5);
		getContentPane().add(lblNewLabel_1_2_1_1_2);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1_1.setBounds(10, 119, 6, 462);
		getContentPane().add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_2 = new JLabel("");
		lblNewLabel_1_2_1_1_1_2.setOpaque(true);
		lblNewLabel_1_2_1_1_1_2.setBounds(782, 119, 6, 462);
		getContentPane().add(lblNewLabel_1_2_1_1_1_2);
		
		JButton btnNewButton = new JButton("Regresar A Menu Principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGrafico mainVentana = new mainGrafico();
				mainVentana.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnNewButton.setBounds(259, 477, 302, 44);
		getContentPane().add(btnNewButton);
		
		JLabel lblMatriculaDeUsuario = new JLabel("Matricula de Usuario:");
		lblMatriculaDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatriculaDeUsuario.setForeground(Color.WHITE);
		lblMatriculaDeUsuario.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblMatriculaDeUsuario.setBounds(165, 171, 267, 38);
		getContentPane().add(lblMatriculaDeUsuario);
		
		textMatricula = new JTextField();
		textMatricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textMatricula.setColumns(10);
		textMatricula.setBounds(442, 171, 204, 38);
		getContentPane().add(textMatricula);
		
		JLabel lblIdDelMaterial = new JLabel("ID del material solicitado:");
		lblIdDelMaterial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdDelMaterial.setForeground(Color.WHITE);
		lblIdDelMaterial.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblIdDelMaterial.setBounds(102, 250, 330, 38);
		getContentPane().add(lblIdDelMaterial);
		
		textidMaterial = new JTextField();
		textidMaterial.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textidMaterial.setColumns(10);
		textidMaterial.setBounds(442, 250, 204, 38);
		getContentPane().add(textidMaterial);
		
		JButton btnSolicitar = new JButton("Solicitar");
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mat = textMatricula.getText();
				String idMat = textidMaterial.getText();
				if (mat.trim().isEmpty() || idMat.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Asegurate de ingresar datos correctamente.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					try {
					//utilizamos un else para hacer losiguiente en dado caso que los camposdellenado de informaciooon no esten vacios
					sistema.realizarPrestamo(mat,idMat);
					//mostrarmensaje si todo se guardo bien
					JOptionPane.showMessageDialog(null, "Material prestado correctamente."); //Mensaje que indica que se agrego correctamente el libro
				
					textMatricula.setText("");
					textidMaterial.setText("");
					//limpiar campos paraun posible nuevo agrgegado de libro
					}catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Operacion cancelada\n"+ex.getMessage(),"Error en Prestamo", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnSolicitar.setOpaque(true);
		btnSolicitar.setForeground(new Color(0, 0, 64));
		btnSolicitar.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnSolicitar.setBackground(Color.WHITE);
		btnSolicitar.setBounds(479, 330, 148, 46);
		getContentPane().add(btnSolicitar);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 606); //
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

}
