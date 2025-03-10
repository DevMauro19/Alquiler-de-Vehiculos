package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import java.util.Date;

import Exceptions.AlquilerNoEncontradoException;
import principal.Agencia;

public class Listar extends JFrame{
	 private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private Agencia agencia = new Agencia();



	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    Listar frame = new Listar();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	    public Listar() {
	        setResizable(false);
	        setFont(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 450, 400);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        // Botón para volver al inicio
	        JButton inicioBtn = new JButton("Inicio");
	        inicioBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                Concesionario inicio = new Concesionario();
	                inicio.setVisible(true);
	                Listar.this.dispose();
	            }
	        });
	        inicioBtn.setBounds(10, 10, 80, 25);
	        contentPane.add(inicioBtn);
	        
	        // Botón para ListarVehiculos
	        JButton ListarVBtn = new JButton("Listar vehiculos");
	        ListarVBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                ListarVehiculos inicio = new ListarVehiculos();
	                inicio.setVisible(true);
	                Listar.this.dispose();
	            }
	        });
	        ListarVBtn.setBounds(100, 100, 250, 40);
	        contentPane.add(ListarVBtn);
	        
	        // Botón para ListarAlquileres
	        JButton ListarABtn = new JButton("Listar alquileres");
	        ListarABtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                ListarAlquileres inicio = new ListarAlquileres();
	                inicio.setVisible(true);
	                Listar.this.dispose();
	            }
	        });
	        ListarABtn.setBounds(100, 150, 250, 40);
	        contentPane.add(ListarABtn);
	        
	        // Botón para ListarClientes
	        JButton ListarCBtn = new JButton("Listar clientes");
	        ListarCBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                ListarClientes inicio = new ListarClientes();
	                inicio.setVisible(true);
	                Listar.this.dispose();
	            }
	        });
	        ListarCBtn.setBounds(100, 200, 250, 40);
	        contentPane.add(ListarCBtn);
	        
}
}