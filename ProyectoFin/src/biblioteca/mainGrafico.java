package biblioteca;

import java.awt.EventQueue;
import java.awt.Image; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

public class mainGrafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SistemaBiblioteca sistema;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGrafico frame = new mainGrafico("SIN SESION");
					frame.setTitle("Menu Principal");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void cambiarIcono() {
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
	public mainGrafico(String matricula) {
		this.sistema = new SistemaBiblioteca();
		cambiarIcono();
		setTitle("SISTEMA DE BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 606);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//DISEÑO===========================================================================================================================
		//buscar recurso dentro de la carpeta de biblioteca (d.png)
		java.net.URL url = getClass().getResource("/biblioteca/d.png");
		if(url == null) {
		    System.out.println("NO SE ENCONTRO LA IMAGEN");
		   // return;
		}

		ImageIcon iconoOriginal = new ImageIcon(url);
		Image imagen = iconoOriginal.getImage();
		
		// definir el ancho que queremos para la imagen
		int anchoDeseado = 460;
		
		// obtener el tamaño original para calcular la proporcion
		int anchoOriginal = imagen.getWidth(null);
		int altoOriginal = imagen.getHeight(null);
		
		// calcular el alto proporcional
		int altoDeseado = 200; // valor de seguridad por defecto
		if (anchoOriginal > 0) {
			altoDeseado = (int) ((double) altoOriginal / anchoOriginal * anchoDeseado);
		}
		
		// escalar la imagen usando el ancho y alto que calculamos
		Image imagenEscalada = imagen.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
		contentPane.setLayout(null);
		
		// colocar la imagen en el JLabel
		JLabel lblImagen = new JLabel(new ImageIcon(imagenEscalada));
		lblImagen.setBounds(96, 85, 587, 108);
		contentPane.add(lblImagen);
		
		JLabel lblNewLabel = new JLabel("MENU PRINCIPAL");
		lblNewLabel.setBounds(156, 27, 464, 47);
		lblNewLabel.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		
		JButton JFAgLib= new JButton("Agregar Libro");
		JFAgLib.setFont(new Font("Felix Titling", Font.BOLD, 15));
		JFAgLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ahora,en todos los enlaces a otras ventanas tenemos que mandar la matricula desde el mainGrafico
				JFrameAgLib terceraVentana = new JFrameAgLib(matricula);
				terceraVentana.setVisible(true);
				dispose();
			}
		});
		JFAgLib.setBounds(86, 311, 185, 33);
		contentPane.add(JFAgLib);
		
		
		JButton JFAgRev = new JButton("Agregar Revista");
		JFAgRev.setFont(new Font("Felix Titling", Font.BOLD, 15));
		JFAgRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameAgRevista cuartaVentana = new JFrameAgRevista(matricula);
				cuartaVentana.setVisible(true);
				dispose();
			}
		});
		JFAgRev.setBounds(86, 381, 185, 33);
		contentPane.add(JFAgRev);
		
		
		JButton JFPres = new JButton("Realizar Prestamos");
		JFPres.setFont(new Font("Felix Titling", Font.BOLD, 12));
		JFPres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFramePrestamos quintaVentana = new JFramePrestamos(sistema,matricula);
				quintaVentana.setVisible(true);
				dispose();
			}
		});
		JFPres.setBounds(523, 308, 185, 38);
		contentPane.add(JFPres);
		
		
		JButton JFDevMat = new JButton("Devolver Material");
		JFDevMat.setFont(new Font("Felix Titling", Font.BOLD, 13));
		JFDevMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameDevoluciones sextaVentana = new JFrameDevoluciones(sistema,matricula);
				sextaVentana.setVisible(true);
				dispose();
			}
		});
		JFDevMat.setBounds(523, 381, 185, 33);
		contentPane.add(JFDevMat);
		
		
		JButton btnSalir = new JButton("Cerrar Sesion");
		btnSalir.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazLogin login = new InterfazLogin();
				login.setVisible(true);
				dispose();
				//System.exit(0);
			}
		});
		btnSalir.setBounds(308, 523, 166, 22);
		contentPane.add(btnSalir);
		
		
		JButton btnNewButton_5 = new JButton("Mostrar Reporte");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameReporte reporte = new JFrameReporte(sistema,matricula);
				reporte.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setFont(new Font("Felix Titling", Font.BOLD, 15));
		btnNewButton_5.setBounds(294, 472, 197, 33);
		contentPane.add(btnNewButton_5);
		contentPane.setBackground(new Color(0, 0, 64));
		
		JLabel lblNewLabel_3 = new JLabel("REGISTROS");
		lblNewLabel_3.setFont(new Font("Felix Titling", Font.BOLD, 11));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(139, 269, 82, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("PRESTAMOS");
		lblNewLabel_3_1.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setOpaque(true);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Felix Titling", Font.BOLD, 11));
		lblNewLabel_3_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_3_1.setBounds(565, 269, 90, 23);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(0, 233, 797, 5);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setOpaque(true);
		lblNewLabel_1_2.setBounds(0, 11, 797, 5);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setBounds(0, 441, 797, 5);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("matricula de Usario:");
		lblNewLabel_3_2.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setOpaque(true);
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setFont(new Font("Felix Titling", Font.BOLD, 11));
		lblNewLabel_3_2.setBackground(Color.WHITE);
		lblNewLabel_3_2.setBounds(630, 96, 147, 36);
		contentPane.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("");
		lblNewLabel_3_2_1.setText(matricula);//en el label seescribe la matricula
		lblNewLabel_3_2_1.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2_1.setOpaque(true);
		lblNewLabel_3_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2_1.setFont(new Font("Felix Titling", Font.BOLD, 11));
		lblNewLabel_3_2_1.setBackground(Color.WHITE);
		lblNewLabel_3_2_1.setBounds(650, 143, 108, 33);
		contentPane.add(lblNewLabel_3_2_1);
		
	}
}

