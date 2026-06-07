package biblioteca;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class JFrameDevoluciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textField_idMaterial;
	private SistemaBiblioteca sistema;
	private String matricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SistemaBiblioteca SistemaT = new SistemaBiblioteca();
					JFrameDevoluciones frame = new JFrameDevoluciones(SistemaT,"");
					frame.setTitle("Devoluciones de Materiales");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
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
	/**
	 * Create the frame.
	 */
	public JFrameDevoluciones(SistemaBiblioteca sistemaExistente,String matricula) {
		this.sistema= sistemaExistente;
		this.matricula = matricula;
		cambiarIcono();
		
		setTitle("Devoluciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 606);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setForeground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDevolucion = new JLabel("Devolución de material");
		lblDevolucion.setForeground(new Color(0, 0, 64));
		lblDevolucion.setFont(new Font("Felix Titling", Font.PLAIN, 30));
		lblDevolucion.setBounds(196, 39, 429, 64);
		lblDevolucion.setOpaque(true);
		contentPane.add(lblDevolucion);
		
		JLabel lblUsuario = new JLabel("ID del usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Felix Titling", Font.BOLD, 17));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setBounds(241, 174, 180, 33);
		contentPane.add(lblUsuario);
		
		JLabel lbl_ID = new JLabel("ID de material:");
		lbl_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ID.setForeground(Color.WHITE);
		lbl_ID.setFont(new Font("Felix Titling", Font.BOLD, 17));
		lbl_ID.setBounds(241, 245, 180, 33);
		contentPane.add(lbl_ID);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(443, 176, 180, 33);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textField_idMaterial = new JTextField();
		textField_idMaterial.setColumns(10);
		textField_idMaterial.setBounds(443, 247, 180, 33);
		contentPane.add(textField_idMaterial);
		
		JButton btnNewButton = new JButton("Regresar A Menu Principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGrafico mainVentana = new mainGrafico(matricula);
				mainVentana.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnNewButton.setBounds(254, 444, 302, 44);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("");
		lblNewLabel_1_2_1.setOpaque(true);
		lblNewLabel_1_2_1.setBounds(0, 114, 797, 5);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1.setBounds(0, 23, 797, 5);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1.setBounds(10, 118, 6, 462);
		contentPane.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1_1.setBounds(782, 118, 6, 462);
		contentPane.add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("");
		lblNewLabel_1_2_1_2.setOpaque(true);
		lblNewLabel_1_2_1_2.setBounds(0, 553, 797, 5);
		contentPane.add(lblNewLabel_1_2_1_2);
		
		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idUser= textFieldUsuario.getText().trim();	
				String idMat= textField_idMaterial.getText().trim();
				
				if (idUser.isEmpty() || idMat.isEmpty()) {
					JOptionPane.showMessageDialog(JFrameDevoluciones.this, "Por favor llene todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				boolean prestamoEncontrado= false;
				
				for (Prestamo p:sistema.getPrestamos()) {		//buscamos en la lista de prestamos
					if (p.isActivo() && p.getIdUsuario().equals(idUser) && p.getIdMaterial().equals(idMat)) {
						prestamoEncontrado = true;
						
						p.marcarDevuelto();		//cambia el estado a devuelto
						double multa= p.calcularMulta(LocalDate.now());		//calcula si hay retraso hoy
						for (Usuario u : sistema.getUsuarios()) {//buscamos el usuario
							u.devolverPrestamo();				 //actualizamos el conteo del usuario
							break;								 //para reducir el conteo
						}
						sistema.guardarCambios();
						//buscamos el material en el inventario para vlver a ponerlo disponible
						for (Material m: sistema.getMateriales()) {
							if (m.getId().equals(idMat)) {
								m.setDisponible(true); 		//Se libera el material
							}
					if (multa > 0) { 		//mostrar el resultado en pantalla
						JOptionPane.showMessageDialog(JFrameDevoluciones.this, "Material devuelto con retraso\nMulta a pagar: $" + multa, "Devolución con multa", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(JFrameDevoluciones.this, "Material devuelto a tiempo!", "Devolución exitosa", JOptionPane.INFORMATION_MESSAGE);
					}
					//limpiamos  las cajas de texto
					textFieldUsuario.setText("");
					textField_idMaterial.setText("");
					break; //se sale del ciclo porque ya se encontro el material y se devolvio	
						}
					}
				}
				
				if (!prestamoEncontrado) {
					JOptionPane.showMessageDialog(JFrameDevoluciones.this, "No se encontró un préstamo activo para ese usuario y material", "Préstamo no encontrado", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDevolver.setOpaque(true);
		btnDevolver.setForeground(new Color(0, 0, 64));
		btnDevolver.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnDevolver.setBackground(Color.WHITE);
		btnDevolver.setBounds(458, 325, 148, 46);
		contentPane.add(btnDevolver);
	}
}
