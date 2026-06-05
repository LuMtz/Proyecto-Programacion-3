package biblioteca;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class JFrameAgRevista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textId;
	private JTextField textTitulo;
	private JTextField textEdicion;
	private SistemaBiblioteca sistema;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameAgRevista frame = new JFrameAgRevista();
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
	public JFrameAgRevista() {
		sistema = new SistemaBiblioteca();
		getContentPane().setBackground(new Color(0,0, 64));
		getContentPane().setLayout(null);
		
		JLabel lblRegistroDeRevistas = new JLabel("REGISTRO DE Revistas");
		lblRegistroDeRevistas.setVerticalAlignment(SwingConstants.CENTER);
		lblRegistroDeRevistas.setOpaque(true);
		lblRegistroDeRevistas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeRevistas.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblRegistroDeRevistas.setBackground(Color.WHITE);
		lblRegistroDeRevistas.setBounds(194, 33, 426, 69);
		getContentPane().add(lblRegistroDeRevistas);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("");
		lblNewLabel_1_2_1.setOpaque(true);
		lblNewLabel_1_2_1.setBounds(0, 127, 797, 5);
		getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1.setBounds(0, 11, 797, 5);
		getContentPane().add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("");
		lblNewLabel_1_2_1_2.setOpaque(true);
		lblNewLabel_1_2_1_2.setBounds(0, 553, 797, 5);
		getContentPane().add(lblNewLabel_1_2_1_2);
		
		JButton btnNewButton = new JButton("Regresar A Menu Principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGrafico mainVentana = new mainGrafico();
				mainVentana.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnNewButton.setBounds(261, 478, 302, 44);
		getContentPane().add(btnNewButton);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblTitulo.setBounds(271, 255, 142, 38);
		getContentPane().add(lblTitulo);
		
		JLabel lblIdCodigo = new JLabel("ID / Codigo:");
		lblIdCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdCodigo.setForeground(Color.WHITE);
		lblIdCodigo.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblIdCodigo.setBounds(271, 176, 142, 38);
		getContentPane().add(lblIdCodigo);
		
		textId = new JTextField();
		textId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textId.setColumns(10);
		textId.setBounds(423, 178, 204, 38);
		getContentPane().add(textId);
		
		textTitulo = new JTextField();
		textTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textTitulo.setColumns(10);
		textTitulo.setBounds(423, 257, 204, 38);
		getContentPane().add(textTitulo);
		
		JLabel lblAutor_1 = new JLabel("No. Edicion:");
		lblAutor_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor_1.setForeground(Color.WHITE);
		lblAutor_1.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblAutor_1.setBounds(271, 334, 142, 38);
		getContentPane().add(lblAutor_1);
		
		textEdicion = new JTextField();
		textEdicion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textEdicion.setColumns(10);
		textEdicion.setBounds(423, 336, 204, 38);
		getContentPane().add(textEdicion);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1.setBounds(10, 127, 6, 462);
		getContentPane().add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("");
		lblNewLabel_1_2_1_1_2.setOpaque(true);
		lblNewLabel_1_2_1_1_2.setBounds(782, 127, 6, 462);
		getContentPane().add(lblNewLabel_1_2_1_1_2);
		
		
		//LOGICA PARA INTEFAZ DE AGREGAR REVISTA
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {//Manejo de errores deingresarformato erroneo en edicion
				String id = textId.getText();
				String titulo = textTitulo.getText();
				String edicionTexto = textEdicion.getText();//recibimos loa datos
				//hacenos una validacion para evitar que se agregen campos vacios
				if (id.trim().isEmpty() || titulo.trim().isEmpty() || edicionTexto.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Asegurate de ingresar datos correctamente.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					//utilizamos un else para hacer losiguiente en dado caso que los camposdellenado de informaciooon no esten vacios
					int numEd = Integer.parseInt(edicionTexto);//se cambia el fomato leido en edicionTexto
					Revista revista = new Revista(id, titulo, numEd);
					sistema.registrarMAterial(revista);
				
					JOptionPane.showMessageDialog(null, "Revista agregada correctamente."); //Mensaje que indica que se agrego correctamente la revista
				
					textId.setText("");
					textTitulo.setText("");
					textEdicion.setText("");//limpiar campos paraun posible nuevo agrgegado de libro
				}
			}catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,"El numero de edicion debe de ser un valor numerico valido.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		});
		btnAgregar.setOpaque(true);
		btnAgregar.setForeground(new Color(0, 0, 64));
		btnAgregar.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.setBounds(457, 405, 148, 46);
		getContentPane().add(btnAgregar);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 606); //
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
}
