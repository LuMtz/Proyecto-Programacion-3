package biblioteca;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class JFrameReporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//atributo de sistemaBibliotecario
	private SistemaBiblioteca sistema;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SistemaBiblioteca sistemaT = new SistemaBiblioteca();		//creamos la instancia de forma local
					JFrameReporte frame = new JFrameReporte(sistemaT);
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
	public JFrameReporte(SistemaBiblioteca sistemaExistente) {
		this.sistema= sistemaExistente;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //dispose para que no se cierre el menu
		setBounds(100, 100, 830, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReporteGeneral = new JLabel("Reporte General");
		lblReporteGeneral.setForeground(Color.WHITE);
		lblReporteGeneral.setFont(new Font("Felix Titling", Font.PLAIN, 40));
		lblReporteGeneral.setBounds(212, 10, 392, 95);
		contentPane.add(lblReporteGeneral);
		
		JTextArea textAreaReporte = new JTextArea();
		textAreaReporte.setFont(new Font("Arial", Font.PLAIN, 24));
		contentPane.add(textAreaReporte);
		textAreaReporte.setEditable(false); 	//para que el usuario no modifique el reporte
		
		JScrollPane scrollPaneReporte = new JScrollPane(textAreaReporte);
		scrollPaneReporte.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneReporte.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneReporte.setBounds(107, 110, 602, 350);
		contentPane.add(scrollPaneReporte);
		scrollPaneReporte.setViewportView(textAreaReporte);
		
		generarReporte(textAreaReporte);
	}

	private void generarReporte(JTextArea textAreaReporte) {
		// TODO Auto-generated method stub
		textAreaReporte.setText(""); 	//limpiar area de texto por si acaso
		
		textAreaReporte.append("=== REPORTES GENERALES DE LA BIBLIOTECA ===\n\n");
		
		textAreaReporte.append("----- USUARIOS REGISTRADOS -----\n");
		if (sistema.getUsuarios().isEmpty()) {
			textAreaReporte.append("No hay usuarios registrados\n");
		} 
		else {
			for (Usuario u: sistema.getUsuarios()) {
				textAreaReporte.append(u.toString() + "\n"); 	//es como presionar Enter
			}
		}
		textAreaReporte.append("\n----- INVENTARIO DE MATERIALES ---\n");
		if (sistema.getMateriales().isEmpty()) {
			textAreaReporte.append("No hay materiales en el inventario\n");
		} else {
			for (Material m: sistema.getMateriales()) {
				textAreaReporte.append(m.toString() + "\n"); 
			}
		}
		textAreaReporte.append("\n----- HISTORIAL DE PRESTAMOS -----\n");
		if (sistema.getPrestamos().isEmpty()) {
			textAreaReporte.append("No hay prestamos registrados en el sistema\n");
		} else {
			for (Prestamo p: sistema.getPrestamos()) {
				textAreaReporte.append("ID: " + p.getIdPrestamo() + 
				" | Usuario: " + p.getIdUsuario() +
				" | Material: " + p.getIdMaterial() + 
				" | Estado: " + p.getEstado());
			}
		}
		textAreaReporte.setCaretPosition(0);
	}
}
	




