package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Actualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Actualizar frame = new Actualizar();
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
	public Actualizar() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton inicioBtn = new JButton("Inicio");
		inicioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Concesionario volver= new Concesionario();
				volver.setVisible(true);
				Actualizar.this.dispose();
			}
		});
		inicioBtn.setBounds(10, 11, 89, 23);
		contentPane.add(inicioBtn);
		
		JLabel actualizarLabel = new JLabel("Actualizar");
		actualizarLabel.setBounds(282, 13, 59, 19);
		contentPane.add(actualizarLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 49, 617, 374);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton alquilerBtn = new JButton("Actualizar Alquiler");
		alquilerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarAlquiler aq= new ActualizarAlquiler(); //crear un objeto de la clase AlctualizarAlquiler
				aq.setVisible(true);	//hacer que la ventana sea visible
				Actualizar.this.dispose(); //hacer que la ventanda desaparezca
			}
		});
		panel.add(alquilerBtn);
		
		JButton camionetaBtn = new JButton("Actualizar Camioneta");
		camionetaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarCamioneta cm= new ActualizarCamioneta();	//crear un objeto de la clase AlctualizarAlquiler
				cm.setVisible(true);	//hacer que la ventana sea visible
				Actualizar.this.dispose(); //hacer que la ventanda desaparezca
			}
		});
		panel.add(camionetaBtn);
		
		JButton clienteBtn = new JButton("Actualizar Cliente");
		clienteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarCliente cl= new ActualizarCliente(); //crear un objeto o ventana
				cl.setVisible(true);	//volver la ventana visible
				Actualizar.this.dispose(); //desaparecer la ventana
			}
		});
		panel.add(clienteBtn);
		
		JButton cocheBtn = new JButton("Actualizar Coche");
		cocheBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarCoche cl= new ActualizarCoche(); //crear un objeto
				cl.setVisible(true);//hacer que la ventana sea visible
				Actualizar.this.dispose(); //hacer que la ventanda desaparezca
			}
		});
		panel.add(cocheBtn);
		
		JButton motocicletaBtn = new JButton("Actualizar Moto");
		motocicletaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarMotocicleta m= new ActualizarMotocicleta(); //crea una ventana o un objeto
				m.setVisible(true);	//hacer que la ventana sea visible
				Actualizar.this.dispose(); //hacer que la ventana desaparezca
			}
		});
		panel.add(motocicletaBtn);
	}
}
