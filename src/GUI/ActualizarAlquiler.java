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

public class ActualizarAlquiler extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Agencia agencia = new Agencia();
    //JFormattedText usado para poder pasar String a int, double entre otros.
    private JFormattedTextField numeroAlquilerField; 
    private JTextField nuevaPlacaField;
    private JTextField idClienteField;
    private JFormattedTextField fechaEntregaField;
    private JFormattedTextField fechaDevolucionField;
    private JFormattedTextField costoTotalBaseField;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ActualizarAlquiler frame = new ActualizarAlquiler();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ActualizarAlquiler() {
        setResizable(false);
        setFont(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel tituloLabel = new JLabel("Actualizar Alquiler");
        tituloLabel.setBounds(160, 10, 150, 20);
        contentPane.add(tituloLabel);

        // Número de Alquiler actual
        JLabel numeroAlquilerLabel = new JLabel("N° Alquiler:");
        numeroAlquilerLabel.setBounds(50, 50, 100, 20);
        contentPane.add(numeroAlquilerLabel);

        numeroAlquilerField = new JFormattedTextField();
        numeroAlquilerField.setBounds(180, 50, 200, 20);
        contentPane.add(numeroAlquilerField);

        // Nueva Placa del Vehículo
        JLabel nuevaPlacaLabel = new JLabel("Nueva Placa:");
        nuevaPlacaLabel.setBounds(50, 90, 100, 20);
        contentPane.add(nuevaPlacaLabel);

        nuevaPlacaField = new JTextField();
        nuevaPlacaField.setBounds(180, 90, 200, 20);
        contentPane.add(nuevaPlacaField);

        // ID Cliente
        JLabel idClienteLabel = new JLabel("ID Cliente:");
        idClienteLabel.setBounds(50, 130, 100, 20);
        contentPane.add(idClienteLabel);

        idClienteField = new JTextField();
        idClienteField.setBounds(180, 130, 200, 20);
        contentPane.add(idClienteField);

        // Fecha de Entrega
        JLabel fechaEntregaLabel = new JLabel("Fecha Entrega:");
        fechaEntregaLabel.setBounds(50, 170, 100, 20);
        contentPane.add(fechaEntregaLabel);

        fechaEntregaField = new JFormattedTextField(dateFormat);
        fechaEntregaField.setBounds(180, 170, 200, 20);
        contentPane.add(fechaEntregaField);

        // Fecha de Devolución
        JLabel fechaDevolucionLabel = new JLabel("Fecha Devolución:");
        fechaDevolucionLabel.setBounds(50, 210, 120, 20);
        contentPane.add(fechaDevolucionLabel);

        fechaDevolucionField = new JFormattedTextField(dateFormat);
        fechaDevolucionField.setBounds(180, 210, 200, 20);
        contentPane.add(fechaDevolucionField);

        // Costo Total Base
        JLabel costoTotalBaseLabel = new JLabel("Costo base x dia:");
        costoTotalBaseLabel.setBounds(50, 250, 120, 20);
        contentPane.add(costoTotalBaseLabel);

        costoTotalBaseField = new JFormattedTextField();
        costoTotalBaseField.setBounds(180, 250, 200, 20);
        contentPane.add(costoTotalBaseField);

        // Botón para actualizar alquiler
        JButton actualizarBtn = new JButton("Actualizar Alquiler");
        actualizarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer numeroAlquiler = null;
                String nuevaPlaca = nuevaPlacaField.getText().trim();
                String idCliente = idClienteField.getText().trim();
                Date fechaEntrega = null;
                Date fechaDevolucion = null;
                Double costoTotalBase = null;

                try {
                	//transformación de los datos
                    numeroAlquiler = Integer.parseInt(numeroAlquilerField.getText().trim());
                    costoTotalBase = Double.parseDouble(costoTotalBaseField.getText().trim());

                    fechaEntrega = dateFormat.parse(fechaEntregaField.getText().trim());
                    fechaDevolucion = dateFormat.parse(fechaDevolucionField.getText().trim());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ActualizarAlquiler.this, "Error: Ingrese los datos en formato válido.");
                    return;
                }

                // Validación de campos vacíos
                if (numeroAlquiler == null || nuevaPlaca.isEmpty() || idCliente.isEmpty() || fechaEntrega == null || fechaDevolucion == null || costoTotalBase == null) {
                    JOptionPane.showMessageDialog(ActualizarAlquiler.this, "Error: Todos los campos deben estar llenos.");
                    return;
                }

                try {
                    // Llamar al método de actualización en Agencia
                    agencia.actualizarAlquiler(numeroAlquiler, nuevaPlaca, idCliente, fechaEntrega, fechaDevolucion, costoTotalBase);
                    JOptionPane.showMessageDialog(ActualizarAlquiler.this, "Alquiler actualizado con éxito!");

                } catch (AlquilerNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(ActualizarAlquiler.this, "Error: Alquiler no encontrado.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ActualizarAlquiler.this, "Error inesperado: " + ex.getMessage());
                }

            }
        });
        
        actualizarBtn.setBounds(140, 290, 180, 30);
        contentPane.add(actualizarBtn);

        // Botón para volver al inicio
        JButton volverBtn	 = new JButton("Volver");
        volverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actualizar volver = new Actualizar(); //crear un ventana de actualizar
                volver.setVisible(true);//hace visible la ventana
                ActualizarAlquiler.this.dispose(); //quita la ventana de ActualizarAlquiler
            }
        });
        volverBtn.setBounds(10, 10, 80, 25);
        contentPane.add(volverBtn);
    }
}