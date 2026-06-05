package biblioteca;

import javax.swing.JOptionPane;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;



public class JFrameAgLib extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textId;
	private JTextField textTitulo;
	private JTextField textAutor;
	
	//crear variable global para el sistema
	private SistemaBiblioteca sistema;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameAgLib frame = new JFrameAgLib();
					frame.setTitle("Agregar Libros");
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
	public JFrameAgLib() {
		sistema = new SistemaBiblioteca();
		
		getContentPane().setBackground(new Color(0,0,64));
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Regresar A Menu Principal");
		btnNewButton.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGrafico mainVentana = new mainGrafico();
				mainVentana.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(253, 472, 302, 44);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setOpaque(true);
		lblNewLabel_1_2.setBounds(0, 10, 797, 5);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblZonaParaagregarLibros = new JLabel("REGISTRO DE LIBROS");
		lblZonaParaagregarLibros.setVerticalAlignment(SwingConstants.CENTER);
		lblZonaParaagregarLibros.setOpaque(true);
		lblZonaParaagregarLibros.setHorizontalAlignment(SwingConstants.CENTER);
		lblZonaParaagregarLibros.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblZonaParaagregarLibros.setBackground(Color.WHITE);
		lblZonaParaagregarLibros.setBounds(196, 26, 426, 69);
		getContentPane().add(lblZonaParaagregarLibros);
		
		JLabel lblIdCodigo = new JLabel("ID / Codigo:");
		lblIdCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdCodigo.setForeground(Color.WHITE);
		lblIdCodigo.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblIdCodigo.setBounds(239, 160, 142, 38);
		getContentPane().add(lblIdCodigo);
		
		textId = new JTextField();
		textId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textId.setColumns(10);
		textId.setBounds(391, 162, 204, 38);
		getContentPane().add(textId);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblTitulo.setBounds(239, 232, 142, 38);
		getContentPane().add(lblTitulo);
		
		textTitulo = new JTextField();
		textTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));		//una disculpa, se me  perdio lagrabacion donde hice la oterfaz grafica
																	//pero en si, solo fue el acomodo de los JTextFiel usados,botones, lables y en general solamente diseño grafico
		textTitulo.setColumns(10);									//Mostrare como quedo y seguimoscon la parte de logica
		textTitulo.setBounds(391, 234, 204, 38);
		getContentPane().add(textTitulo);
		
		textAutor = new JTextField();
		textAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textAutor.setColumns(10);
		textAutor.setBounds(391, 312, 204, 38);
		getContentPane().add(textAutor);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("");
		lblNewLabel_1_2_1.setOpaque(true);
		lblNewLabel_1_2_1.setBounds(0, 107, 797, 5);
		getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1.setBounds(10, 107, 6, 462);
		getContentPane().add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1.setOpaque(true);
		lblNewLabel_1_2_1_1_1.setBounds(782, 107, 6, 462);
		getContentPane().add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("");
		lblNewLabel_1_2_1_2.setOpaque(true);
		lblNewLabel_1_2_1_2.setBounds(0, 553, 797, 5);
		getContentPane().add(lblNewLabel_1_2_1_2);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//extraer datos y guardarlos
				String id = textId.getText();
				String titulo = textTitulo.getText();
				String aut = textAutor.getText();
				
				//hacenos una validacion para evitar que se agregen campos vacios
				if (id.trim().isEmpty() || titulo.trim().isEmpty() || aut.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Asegurate de ingresar datos correctamente.","Erorr",JOptionPane.ERROR_MESSAGE);
				}else {
				
				Libro libro = new Libro(id, titulo, aut);
				sistema.registrarMAterial(libro);
				
				JOptionPane.showMessageDialog(null, "Libro agregado correctamente."); //Mensaje que indica que se agrego correctamente el libro
				
				textId.setText("");
				textTitulo.setText("");
				textAutor.setText("");//limpiar campos paraun posible nuevo agrgegado de libro
				}
			}
		});
		
		
		
		btnAgregar.setOpaque(true);
		btnAgregar.setForeground(new Color(0, 0, 64));
		btnAgregar.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.setBounds(419, 377, 148, 46);
		getContentPane().add(btnAgregar);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setForeground(Color.WHITE);
		lblAutor.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblAutor.setBounds(239, 312, 142, 38);
		getContentPane().add(lblAutor);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 606); //
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
}
