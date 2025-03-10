package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Exceptions.ClienteDuplicadoException;
import Exceptions.NumeroInvalidoException;
import principal.Agencia;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;

public class RegistroCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreField;
	private JTextField idField;
	private JTextField telField;
	private JTextField emailField;
	private Agencia agencia=new Agencia();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroCliente frame = new RegistroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegistroCliente() {
		setResizable(false); //para que no pueda modificar el tamaño de la pantalla
		setFont(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//boton para volver al inicio
		JButton Inicio = new JButton("Inicio");
		Inicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Concesionario inicio= new Concesionario();
				
				inicio.setVisible(true);
				RegistroCliente.this.dispose();
			}
		});
		Inicio.setBounds(10, 11, 68, 24);
		contentPane.add(Inicio);
		
		//labels o textos sencillos
		JLabel nombreL = new JLabel("Nombre:");
		nombreL.setBounds(58, 56, 68, 14);
		contentPane.add(nombreL);
		
		JLabel registroL = new JLabel("Registro");
		registroL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registroL.setBounds(241, 7, 68, 24);
		contentPane.add(registroL);
		
		//campo de texto ingresado por el usuario
		nombreField = new JTextField();
		nombreField.setBounds(124, 53, 288, 20);
		contentPane.add(nombreField);
		nombreField.setColumns(10);
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setBounds(90, 91, 24, 14);
		contentPane.add(idLabel);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(124, 88, 288, 20);
		contentPane.add(idField);
		
		JLabel telLabel = new JLabel("Telefono:");
		telLabel.setBounds(63, 133, 57, 14);
		contentPane.add(telLabel);
		
		telField = new JTextField();
		telField.setBounds(124, 130, 288, 20);
		contentPane.add(telField);
		telField.setColumns(10);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(80, 172, 46, 14);
		contentPane.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setBounds(124, 169, 288, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		//boton de registrarse
		JButton registrarseBtn = new JButton("Registrarse");
		registrarseBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id = idField.getText().trim();
		        String nombre = nombreField.getText().trim();
		        String telefono = telField.getText().trim();
		        String email = emailField.getText().trim();

		        if (id.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) { // isEmpty es para saber si el String esta vacio
		            JOptionPane.showMessageDialog(RegistroCliente.this, "Error: todos los campos deben estar llenos.");
		        } else if (telefono.length() != 10) {
		            JOptionPane.showMessageDialog(RegistroCliente.this, "Error: el número de teléfono debe tener 10 dígitos.");
		        } else if (id.length() != 10) {
		            JOptionPane.showMessageDialog(RegistroCliente.this, "Error: el ID o cédula debe tener 10 dígitos.");
		        } else {
		            try {
		                agencia.registrarCliente(id, nombre, telefono, email);
		                JOptionPane.showMessageDialog(RegistroCliente.this, "Cliente registrado con éxito!");
		                Concesionario inicio = new Concesionario();
		                inicio.setVisible(true);
		                RegistroCliente.this.dispose();
		            } catch (ClienteDuplicadoException ex) {
		                JOptionPane.showMessageDialog(RegistroCliente.this, "Error: Cliente ya existe.");
		            } catch (NullPointerException f) {
		                JOptionPane.showMessageDialog(RegistroCliente.this, "Error: los campos no pueden estar vacíos.");
		            } catch (NumeroInvalidoException e1) {
						JOptionPane.showMessageDialog(RegistroCliente.this, "Error: numero invalido."); //revisión
					}
		        }
		    }
		});
		registrarseBtn.setBounds(220, 227, 110, 35);
		contentPane.add(registrarseBtn);
	}
}
