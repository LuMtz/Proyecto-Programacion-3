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
import java.awt.event.ActionEvent;

public class mainGrafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGrafico frame = new mainGrafico();
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
	public mainGrafico() {
		setTitle("SISTEMA DE BIBLIOTECA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 572); //
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//DISEÑO===========================================================================================================================
		//buscar recurso dentro de la carpeta de biblioteca (d.png)
		java.net.URL url = getClass().getResource("/biblioteca/d.png");
		if(url == null) {
		    System.out.println("NO SE ENCONTRO LA IMAGEN");
		    return;
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
		lblImagen.setBounds(20, 20, 460, 108);
		contentPane.add(lblImagen);
		
		JLabel lblNewLabel = new JLabel("SISTEMA DE BIBLIOTECA");
		lblNewLabel.setBounds(67, 140, 352, 47);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setBackground(new Color(202, 208, 251));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		//=================================================================DISEÑO===========================================================================
		
		
		//_____ACCIONES______
		JButton JFRegisUs = new JButton("Registro Usuario");
		JFRegisUs.setBounds(173, 198, 136, 22);
		JFRegisUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameRegUsuario segundaVentana = new JFrameRegUsuario();
				segundaVentana.setVisible(true);
				contentPane.setBackground(Color.WHITE);
			}
		});
		contentPane.add(JFRegisUs);
		
		
		JButton JFAgLib= new JButton("Agregar Libro");
		JFAgLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameAgLib terceraVentana = new JFrameAgLib();
				terceraVentana.setVisible(true);
				contentPane.setBackground(Color.WHITE);
			}
		});
		JFAgLib.setBounds(47, 317, 131, 22);
		contentPane.add(JFAgLib);
		
		
		JButton JFAgRev = new JButton("Agregar Revista");
		JFAgRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameAgRevista cuartaVentana = new JFrameAgRevista();
				cuartaVentana.setVisible(true);
				contentPane.setBackground(Color.WHITE);
			}
		});
		JFAgRev.setBounds(47, 361, 131, 22);
		contentPane.add(JFAgRev);
		
		
		JButton JFPres = new JButton("Realizar Prestamos");
		JFPres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFramePrestamos quintaVentana = new JFramePrestamos();
				quintaVentana.setVisible(true);
				contentPane.setBackground(Color.WHITE);
			}
		});
		JFPres.setBounds(305, 317, 150, 22);
		contentPane.add(JFPres);
		
		
		JButton JFDevMat = new JButton("Devolver Material");
		JFDevMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameDevMaterial sextaVentana = new JFrameDevMaterial();
				sextaVentana.setVisible(true);
				contentPane.setBackground(Color.WHITE);
			}
		});
		JFDevMat.setBounds(305, 361, 150, 22);
		contentPane.add(JFDevMat);
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(182, 482, 127, 22);
		contentPane.add(btnSalir);
		
		
		JButton btnNewButton_5 = new JButton("Mostrar Reporte");
		btnNewButton_5.setBounds(166, 441, 157, 22);
		contentPane.add(btnNewButton_5);
		contentPane.setBackground(Color.WHITE);
		
		
		
		//DISEÑO===========================================================================================================================
		JLabel lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setBounds(0, 265, 501, 2);
		lblNewLabel_1.setOpaque(true);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\r\n");
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1.setBounds(0, 400, 501, 2);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("\r\n");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.BLACK);
		lblNewLabel_2.setBounds(241, 266, 5, 136);
		
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("REGISTROS");
		lblNewLabel_3.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBackground(new Color(213, 213, 255));
		lblNewLabel_3.setBounds(72, 284, 82, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("PRESTAMOS");
		lblNewLabel_3_1.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setOpaque(true);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblNewLabel_3_1.setBackground(new Color(213, 213, 255));
		lblNewLabel_3_1.setBounds(337, 284, 82, 22);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4 = new JLabel("NOTA:");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(79, 234, 48, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Se necesita crear/tener un usuario para poder acceder a las funciones.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(126, 231, 365, 23);
		contentPane.add(lblNewLabel_5);
		
	}
}