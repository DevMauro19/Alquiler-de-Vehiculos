
package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Date;

import Exceptions.AlquilerNoEncontradoException;
import Exceptions.NumeroInvalidoException;
import Exceptions.ParametroNuloException;
import Exceptions.VehiculoDuplicadoException;
import Exceptions.VehiculoNoEncontradoException;
import principal.Agencia;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DevolverFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField placaField;
	private JTextField entregaTField;
	private JTextField entregaPField;
	private JTextField numAlquilerField;
	private Agencia agencia = new Agencia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DevolverFrame frame = new DevolverFrame();
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
	public DevolverFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton inicioBtn = new JButton("Inicio");
		inicioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Concesionario inicio= new Concesionario();
				inicio.setVisible(true);
				DevolverFrame.this.dispose();		
			}
		});
		inicioBtn.setBounds(10, 11, 89, 23);
		contentPane.add(inicioBtn);
		
		JLabel delvolverLabel = new JLabel("Devolver Alquiler");
		delvolverLabel.setBounds(287, 15, 104, 14);
		contentPane.add(delvolverLabel);
		
		JLabel placaLabel = new JLabel("Placa:");
		placaLabel.setBounds(128, 65, 46, 14);
		contentPane.add(placaLabel);
		
		JLabel tanqueLabel = new JLabel("Tanque LLeno:");
		tanqueLabel.setBounds(101, 105, 95, 14);
		contentPane.add(tanqueLabel);
		
		JLabel entregatLabel = new JLabel("Entrega Teórica: ");
		entregatLabel.setBounds(87, 145, 126, 14);
		contentPane.add(entregatLabel);
		
		JLabel entregapLabel = new JLabel("Entrega Práctica:");
		entregapLabel.setBounds(87, 185, 109, 14);
		contentPane.add(entregapLabel);
		
		JLabel numAlquilerLabel = new JLabel("N° Alquiler:");
		numAlquilerLabel.setBounds(101, 225, 79, 14);
		contentPane.add(numAlquilerLabel);
		
		placaField = new JTextField();
		placaField.setBounds(206, 62, 299, 20);
		contentPane.add(placaField);
		placaField.setColumns(10);
		
		entregaTField = new JTextField();
		entregaTField.setColumns(10);
		entregaTField.setBounds(206, 142, 299, 20);
		contentPane.add(entregaTField);
		
		entregaPField = new JTextField();
		entregaPField.setColumns(10);
		entregaPField.setBounds(206, 182, 299, 20);
		contentPane.add(entregaPField);
		
		numAlquilerField = new JTextField();
		numAlquilerField.setColumns(10);
		numAlquilerField.setBounds(206, 222, 299, 20);
		contentPane.add(numAlquilerField);
		
		JRadioButton tanque_true = new JRadioButton("Sí");
		tanque_true.setBounds(249, 101, 109, 23);
		contentPane.add(tanque_true);
		
		JRadioButton tanque_false = new JRadioButton("No");
		tanque_false.setBounds(396, 101, 109, 23);
		contentPane.add(tanque_false);
		
		//se crea un un objeto tanqueGroup para que solo se permita seleccionar una acción
		ButtonGroup tanqueGroup = new ButtonGroup();
		tanqueGroup.add(tanque_true);
		tanqueGroup.add(tanque_false);

		
		JButton devolverAlquilerBtn = new JButton("Devolver");
		devolverAlquilerBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String placa = placaField.getText().trim();
		            
		            // Obtener el valor del botón de radio seleccionado
		            boolean tanqueLleno = tanque_true.isSelected();

		            // conversión las fechas desde los campos de texto
		            Date entregaTeorica = new SimpleDateFormat("yyyy-MM-dd").parse(entregaTField.getText());
		            Date entregaPractica = new SimpleDateFormat("yyyy-MM-dd").parse(entregaPField.getText());

		            // Obtener el número del alquiler desde el campo de texto
		            int numAlquiler = Integer.parseInt(numAlquilerField.getText());

		            // Llamar al método devolverVehiculo desde Agencia
		            double excesoTotal = agencia.devolverVehiculo(placa, tanqueLleno, entregaTeorica, entregaPractica, numAlquiler);

		            
		            JOptionPane.showMessageDialog(null, "Exceso Total: $" + excesoTotal, "Devolución Exitosa", JOptionPane.INFORMATION_MESSAGE);
		        } catch (VehiculoNoEncontradoException ex) {
		            JOptionPane.showMessageDialog(null, "Error: Vehículo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (AlquilerNoEncontradoException ex) {
		            JOptionPane.showMessageDialog(null, "Error: Alquiler no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		devolverAlquilerBtn.setBounds(287, 278, 89, 23);
		contentPane.add(devolverAlquilerBtn);
	}
}