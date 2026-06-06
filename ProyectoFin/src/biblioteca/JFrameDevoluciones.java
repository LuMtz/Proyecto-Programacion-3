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
import java.time.LocalDate;

import javax.swing.JTextField;
import javax.swing.JButton;

public class JFrameDevoluciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textField_idMaterial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SistemaBiblioteca SistemaT = new SistemaBiblioteca();
					JFrameDevoluciones frame = new JFrameDevoluciones(SistemaT);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private SistemaBiblioteca sistema;

	/**
	 * Create the frame.
	 */
	public JFrameDevoluciones(SistemaBiblioteca sistemaExistente) {
		this.sistema= sistemaExistente;
		setTitle("Devoluciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 665, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setForeground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDevolucion = new JLabel("Devolución de material");
		lblDevolucion.setForeground(Color.WHITE);
		lblDevolucion.setFont(new Font("Felix Titling", Font.PLAIN, 30));
		lblDevolucion.setBounds(111, 22, 429, 95);
		contentPane.add(lblDevolucion);
		
		JLabel lblUsuario = new JLabel("ID del usuario:");
		lblUsuario.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setBounds(83, 146, 180, 33);
		contentPane.add(lblUsuario);
		
		JLabel lbl_ID = new JLabel("ID de material:");
		lbl_ID.setForeground(Color.WHITE);
		lbl_ID.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		lbl_ID.setBounds(83, 230, 180, 33);
		contentPane.add(lbl_ID);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(314, 148, 180, 33);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textField_idMaterial = new JTextField();
		textField_idMaterial.setColumns(10);
		textField_idMaterial.setBounds(314, 230, 180, 33);
		contentPane.add(textField_idMaterial);
		
		JButton btnDevolver = new JButton("Devolver material");
		btnDevolver.setBackground(new Color(64, 128, 128));
		btnDevolver.setForeground(new Color(255, 255, 255));
		btnDevolver.setFont(new Font("Rockwell", Font.PLAIN, 19));
		btnDevolver.setBounds(258, 322, 215, 53);
		contentPane.add(btnDevolver);
		
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
	}
}
