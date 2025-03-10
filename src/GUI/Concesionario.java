package GUI;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Agencia; /*importamos la clase agencia que 
contiene nuestra logica del programa*/

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class Concesionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Agencia agencia; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Concesionario frame = new Concesionario();
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
	public Concesionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(centerPanel);
		JLabel Titulo = new JLabel("Concesionario");
		GridBagConstraints gbc_Titulo = new GridBagConstraints();
		gbc_Titulo.gridx = 0;
		gbc_Titulo.gridy = 0;
		centerPanel.add(Titulo, gbc_Titulo);
		
		JButton Registrarse = new JButton("Registrarse");
		Registrarse.addActionListener(new ActionListener() {
			//donde debe ir la logica
			public void actionPerformed(ActionEvent e) {
				RegistroCliente cl= new RegistroCliente();
				
				cl.setVisible(true);
				Concesionario.this.dispose();
				
			}
		});
		contentPane.add(Registrarse);
		
		//boton agregar Moto
		JButton AgregarMoto = new JButton("Agregar Moto");
		AgregarMoto.addActionListener(new ActionListener() {
			//accion de agregar
			public void actionPerformed(ActionEvent e) {
				AddMoto m= new AddMoto();
				m.setVisible(true);
				Concesionario.this.dispose();
			}
		});
		contentPane.add(AgregarMoto);
		
		//boton agregar Carro
		JButton AgregarCarro = new JButton("Agregar Carro");
		AgregarCarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				AddCoche c= new AddCoche();
				
				c.setVisible(true);
				Concesionario.this.dispose();
			}
		});
		contentPane.add(AgregarCarro);
		
		//boton agregar Camioneta
		JButton AgregarCamioneta = new JButton("Agregar Camioneta");
		AgregarCamioneta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //donde debe ir la logica
				AddCamioneta cm=new AddCamioneta();
				cm.setVisible(true);
				Concesionario.this.dispose();
			}
		});
		contentPane.add(AgregarCamioneta);
		
		//boton Alquilar
		JButton Alquilarbtn = new JButton("Alquilar");
		Alquilarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //donde debe ir la logica
			Alquiler al= new Alquiler();
			al.setVisible(true);
			Concesionario.this.dispose();
			}
		});
		contentPane.add(Alquilarbtn);
		
		//boton eliminar
		JButton Eliminarbtn = new JButton("Eliminar");
		Eliminarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar E= new Eliminar();
				E.setVisible(true);
				Concesionario.this.dispose();
			}
		});
		contentPane.add(Eliminarbtn);
		
		//boton actualizar
		JButton Actualizar = new JButton("Actualizar");
		Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar ac = new Actualizar();
                ac.setVisible(true);
                Concesionario.this.dispose();
			}
		});
		contentPane.add(Actualizar);
		
		//boton Listar
		JButton Listar = new JButton("Listar");
		Listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar L= new Listar();
				L.setVisible(true);
				Concesionario.this.dispose();
			}
		});
		contentPane.add(Listar);
}
	}

