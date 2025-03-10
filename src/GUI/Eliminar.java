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
import javax.swing.text.NumberFormatter;

import Exceptions.AlquilerNoEncontradoException;
import Exceptions.ClienteNoEncontradoException;
import Exceptions.NumeroInvalidoException;
import Exceptions.ParametroNuloException;
import Exceptions.VehiculoDuplicadoException;
import Exceptions.VehiculoNoEncontradoException;
import principal.Agencia;
import java.text.NumberFormat;

public class Eliminar extends JFrame{

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Agencia agencia = new Agencia();
    private JTextField placaField;
    private JTextField idClienteField;
    private JFormattedTextField numeroAlquilerField;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Eliminar frame = new Eliminar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Eliminar() {
        setResizable(false); //para que no puedan modificar el tamaño de la pantalla
        setFont(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 644, 464);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel eliminarVehiculoLabel = new JLabel("Eliminar Vehiculo");
        eliminarVehiculoLabel.setBounds(280, 10, 99, 14);
        contentPane.add(eliminarVehiculoLabel);

        JLabel placaLabel = new JLabel("Placa:");
        placaLabel.setBounds(90, 55, 46, 14);
        contentPane.add(placaLabel);
        
        //boton eliminar Vehivulo 100
        JLabel eliminarClienteLabel = new JLabel("Eliminar Cliente:");
        eliminarClienteLabel.setBounds(280, 145, 99, 14);
        contentPane.add(eliminarClienteLabel);

        JLabel idLabel = new JLabel("Id:");
        idLabel.setBounds(90, 190, 46, 14);
        contentPane.add(idLabel);
        //boton eliminar Cliente 235

        JLabel eliminarAlquilerLabel = new JLabel("Eliminar Alquiler");
        eliminarAlquilerLabel.setBounds(280, 280, 99, 14);
        contentPane.add(eliminarAlquilerLabel);

        JLabel nAlquilerLabel = new JLabel("Numero Alquiler:");
        nAlquilerLabel.setBounds(90, 325, 99, 14);
        contentPane.add(nAlquilerLabel);
        //boton eliminar Alquiler 370
        
        //fields, son campos donde el usuario ingresa datos
        placaField = new JTextField();
        placaField.setBounds(189, 55, 272, 20);
        contentPane.add(placaField);
        placaField.setColumns(10);

        idClienteField = new JTextField();
        idClienteField.setColumns(10);
        idClienteField.setBounds(189, 190, 272, 20);
        contentPane.add(idClienteField);

        numeroAlquilerField =  new JFormattedTextField();
        numeroAlquilerField.setColumns(10);
        numeroAlquilerField.setBounds(189, 325, 272, 20);
        contentPane.add(numeroAlquilerField);

        
        //boton para volver a la interfaz principal
        JButton inicio = new JButton("Inicio");
        inicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Concesionario inicio = new Concesionario();
                inicio.setVisible(true);
                Eliminar.this.dispose();
            }
        });
        inicio.setBounds(0, 7, 89, 23);
        contentPane.add(inicio);
        
        //boton eliminar vehiculo
        JButton agregarBtnEliminarVehiculo = new JButton("Eliminar Vehiculo");
        agregarBtnEliminarVehiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText().trim();              
                if (placa.isEmpty()) {
                    JOptionPane.showMessageDialog(Eliminar.this, "Error: Debe llenar el campo de la placa.");
                    return; 
                }
                try {
                    agencia.eliminarVehiculo(placa);
                    JOptionPane.showMessageDialog(Eliminar.this, "Vehículo eliminado con éxito!");


                } catch (VehiculoNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(Eliminar.this, "Error: Vehículo no encontrado.");
                }
            }
        });
        
        JButton agregarBtnEliminarCliente = new JButton("Eliminar Cliente");
        agregarBtnEliminarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idClienteField.getText().trim();              
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(Eliminar.this, "Error: Debe llenar el campo del id.");
                    return; 
                }
                try {
                    agencia.eliminarCliente(id);
                    JOptionPane.showMessageDialog(Eliminar.this, "Cliente eliminado con éxito!");


                } catch (ClienteNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(Eliminar.this, "Error: Cliente no encontrado.");
                }
            }
        });
        
        JButton agregarBtnEliminarAlquiler = new JButton("Eliminar Alquiler");
        agregarBtnEliminarAlquiler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer nAlquilerCliente = null;
                try {
                    nAlquilerCliente = (Integer) numeroAlquilerField.getValue();
                } catch (ClassCastException ex) {
                    // Si no se puede convertir el valor a Integer, ignora el valor
                }
                if (nAlquilerCliente==null) {
                    JOptionPane.showMessageDialog(Eliminar.this, "Error: Debe llenar el campo del numero de alquiler.");
                    return; 
                }
                try {
                    agencia.eliminarAlquiler(nAlquilerCliente);
                    JOptionPane.showMessageDialog(Eliminar.this, "Vehículo eliminado con éxito!");

                } catch (AlquilerNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(Eliminar.this, "Error: Alquiler no encontrado.");
                }
            }
        });

        // Posicionamiento del botón
        agregarBtnEliminarVehiculo.setBounds(260, 100, 140, 14);
        contentPane.add(agregarBtnEliminarVehiculo);
        
        agregarBtnEliminarCliente.setBounds(260, 235,140, 14);
        contentPane.add(agregarBtnEliminarCliente);
        
        agregarBtnEliminarAlquiler.setBounds(260, 370, 140, 14);
        contentPane.add(agregarBtnEliminarAlquiler);
        
}

}