
package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import Exceptions.ClienteNoEncontradoException;
import Exceptions.FechaInvalidaException;
import Exceptions.VehiculoNoDisponibleException;
import Exceptions.VehiculoNoEncontradoException;
import principal.Agencia;

import java.awt.*;

public class AlquilarFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	private JTextField placaField;
	private JTextField idClienteField;
	private JFormattedTextField fechaEntregaField, fechaDevolucionField;
	private Agencia agencia = new Agencia();
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlquilarFrame frame = new AlquilarFrame();
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
	public AlquilarFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 601, 488);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton incioBtn = new JButton("Volver");
        incioBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Alquiler inicio = new Alquiler();
                inicio.setVisible(true);
                AlquilarFrame.this.dispose();
            }
        });
        incioBtn.setBounds(10, 11, 89, 23);
        contentPane.add(incioBtn);
        
        JLabel alquilarLbl = new JLabel("Alquilar");
        alquilarLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
        alquilarLbl.setBounds(250, 15, 99, 29);
        contentPane.add(alquilarLbl);
        
        JLabel placaLbl = new JLabel("Placa:");
        placaLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        placaLbl.setBounds(76, 113, 67, 23);
        contentPane.add(placaLbl);
        
        JLabel clienteLbl = new JLabel("Id cliente:");
        clienteLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        clienteLbl.setBounds(76, 160, 122, 36);
        contentPane.add(clienteLbl);
        
        JLabel fechaElbl = new JLabel("Fecha entrega:");
        fechaElbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        fechaElbl.setBounds(76, 229, 122, 23);
        contentPane.add(fechaElbl);
        
        JLabel fechaDlbl = new JLabel("Fecha devolucion:");
        fechaDlbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        fechaDlbl.setBounds(76, 273, 122, 61);
        contentPane.add(fechaDlbl);
        
        placaField = new JTextField();
        placaField.setBounds(250, 113, 240, 23);
        contentPane.add(placaField);
        placaField.setColumns(10);
        
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0); 
        numberFormatter.setAllowsInvalid(true); 
        numberFormatter.setCommitsOnValidEdit(false); 
        
        idClienteField = new JFormattedTextField();
        idClienteField.setBounds(250, 167, 240, 23);
        contentPane.add(idClienteField);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); 
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        DefaultFormatterFactory dateFormatterFactory = new DefaultFormatterFactory(dateFormatter);
        
        fechaEntregaField = new JFormattedTextField(dateFormatterFactory);
        fechaEntregaField.setBounds(250, 233, 240, 23);
        contentPane.add(fechaEntregaField);
        
        
        fechaDevolucionField = new JFormattedTextField(dateFormatterFactory);
        fechaDevolucionField.setBounds(250, 294, 240, 23);
        contentPane.add(fechaDevolucionField);
       
        
        JButton btnAlquilar = new JButton("Alquilar");
        btnAlquilar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String placa = placaField.getText().trim(); 
        		String idCliente = idClienteField.getText().trim();
        		Date fechaEntrega = null;
        		Date fechaDevolucion = null;
        		//agregar validación de id no mayor a 10 y minimo 8
        		try {
        			fechaEntrega = (Date)fechaEntregaField.getValue();
        		} catch (ClassCastException e1) {
        			JOptionPane.showMessageDialog(AlquilarFrame.this, e1.getMessage());
				}
        		try {
        			fechaDevolucion = (Date)fechaDevolucionField.getValue();
        		} catch (ClassCastException e1) {
        			JOptionPane.showMessageDialog(AlquilarFrame.this, e1.getMessage());
				}
        		
        		if(placa.isEmpty() || idCliente.isEmpty() || fechaEntrega ==  null || fechaDevolucion == null) {
        			JOptionPane.showMessageDialog(AlquilarFrame.this, "Error: todos los campos deben estar llenos y en el formato correcto.");
        		} else if (fechaEntrega.after(fechaDevolucion)) {
                    JOptionPane.showMessageDialog(AlquilarFrame.this, "Error: la fecha de entrega no puede ser posterior a la fecha de devolución.");
                } else {
                	try {
                		double costo = agencia.realizarAlquiler(placa, idCliente, fechaEntrega, fechaDevolucion);
                        JOptionPane.showMessageDialog(AlquilarFrame.this, 
                        "Alquiler registrado con éxito. El costo total del alquiler es: $" + costo);
						Concesionario inicio = new Concesionario();
                        inicio.setVisible(true);
                        AlquilarFrame.this.dispose();
					} catch (VehiculoNoEncontradoException | ClienteNoEncontradoException | VehiculoNoDisponibleException | FechaInvalidaException e1) {
						
						JOptionPane.showMessageDialog(AlquilarFrame.this, "Error: " + e1.getMessage());
					}
                    
                }
        		
        	}
        });
        btnAlquilar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAlquilar.setBounds(203, 361, 146, 21);
        contentPane.add(btnAlquilar);
        
	}
}