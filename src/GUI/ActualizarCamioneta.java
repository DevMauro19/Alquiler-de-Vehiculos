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

import Exceptions.VehiculoNoEncontradoException;
import principal.Agencia;

public class ActualizarCamioneta extends JFrame {
	//atributos
    private static final long serialVersionUID = 1L;/*importa una interfaz y hace una 
    conversión de un objeto en un flujo de bytes para guardar o enviar a otro sistema*/
    
    private JPanel contentPane;
    private Agencia agencia = new Agencia(); //crear una instancia de agencia para poder hacer llamado a sus metodos
    
    
    private JTextField placaActualField;
    private JTextField nuevaPlacaField;
    private JTextField marcaField;
    private JTextField modeloField;
    private JFormattedTextField anioField;
    private JFormattedTextField puertasField;
    private JTextField tipoMotorField;
    private JFormattedTextField capacidadCargaField;

    //metodo main
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ActualizarCamioneta frame = new ActualizarCamioneta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //constructor
    public ActualizarCamioneta() {
        setResizable(false);
        setFont(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel tituloLabel = new JLabel("Actualizar Camioneta");
        tituloLabel.setBounds(140, 10, 200, 20);
        contentPane.add(tituloLabel);

        // Placa actual
        JLabel placaActualLabel = new JLabel("Placa Actual:");
        placaActualLabel.setBounds(50, 50, 100, 20);
        contentPane.add(placaActualLabel);

        placaActualField = new JTextField();
        placaActualField.setBounds(180, 50, 200, 20);
        contentPane.add(placaActualField);

        // Nueva Placa
        JLabel nuevaPlacaLabel = new JLabel("Nueva Placa:");
        nuevaPlacaLabel.setBounds(50, 90, 100, 20);
        contentPane.add(nuevaPlacaLabel);

        nuevaPlacaField = new JTextField();
        nuevaPlacaField.setBounds(180, 90, 200, 20);
        contentPane.add(nuevaPlacaField);

        // Marca
        JLabel marcaLabel = new JLabel("Marca:");
        marcaLabel.setBounds(50, 130, 100, 20);
        contentPane.add(marcaLabel);

        marcaField = new JTextField();
        marcaField.setBounds(180, 130, 200, 20);
        contentPane.add(marcaField);

        // Modelo
        JLabel modeloLabel = new JLabel("Modelo:");
        modeloLabel.setBounds(50, 170, 100, 20);
        contentPane.add(modeloLabel);

        modeloField = new JTextField();
        modeloField.setBounds(180, 170, 200, 20);
        contentPane.add(modeloField);

        // Año
        JLabel anioLabel = new JLabel("Año:");
        anioLabel.setBounds(50, 210, 100, 20);
        contentPane.add(anioLabel);

        anioField = new JFormattedTextField();
        anioField.setBounds(180, 210, 200, 20);
        contentPane.add(anioField);

        // Número de puertas
        JLabel puertasLabel = new JLabel("N° Puertas:");
        puertasLabel.setBounds(50, 250, 100, 20);
        contentPane.add(puertasLabel);

        puertasField = new JFormattedTextField();
        puertasField.setBounds(180, 250, 200, 20);
        contentPane.add(puertasField);

        // Tipo de motor
        JLabel tipoMotorLabel = new JLabel("Tipo de Motor:");
        tipoMotorLabel.setBounds(50, 290, 100, 20);
        contentPane.add(tipoMotorLabel);

        tipoMotorField = new JTextField();
        tipoMotorField.setBounds(180, 290, 200, 20);
        contentPane.add(tipoMotorField);

        // Capacidad de carga
        JLabel capacidadCargaLabel = new JLabel("Capacidad Carga:");
        capacidadCargaLabel.setBounds(50, 330, 120, 20);
        contentPane.add(capacidadCargaLabel);

        capacidadCargaField = new JFormattedTextField();
        capacidadCargaField.setBounds(180, 330, 200, 20);
        contentPane.add(capacidadCargaField);

        // Botón para actualizar camioneta
        JButton actualizarBtn = new JButton("Actualizar Camioneta");
        actualizarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placaActual = placaActualField.getText().trim();
                String nuevaPlaca = nuevaPlacaField.getText().trim();
                String marca = marcaField.getText().trim();
                String modelo = modeloField.getText().trim();
                String tipoMotor = tipoMotorField.getText().trim();
                Integer anio = null;
                Integer puertas = null;
                Double capacidadCarga = null;

                // Intentar obtener valores numéricos
                try {
                    anio = Integer.parseInt(anioField.getText().trim());
                    puertas = Integer.parseInt(puertasField.getText().trim());
                    capacidadCarga = Double.parseDouble(capacidadCargaField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ActualizarCamioneta.this, "Error: Año, Puertas y Capacidad de Carga deben ser numéricos.");
                    return;
                }

                // Validación de campos vacíos
                if (placaActual.isEmpty() || nuevaPlaca.isEmpty() || marca.isEmpty() || modelo.isEmpty() || tipoMotor.isEmpty() || anio == null || puertas == null || capacidadCarga == null) {
                    JOptionPane.showMessageDialog(ActualizarCamioneta.this, "Error: Todos los campos deben estar llenos.");
                    return;
                }

                try {
                    // Llamar al método de actualización en Agencia
                    agencia.actualizarCamioneta(placaActual, nuevaPlaca, marca, modelo, anio, puertas, tipoMotor, capacidadCarga);
                    JOptionPane.showMessageDialog(ActualizarCamioneta.this, "Camioneta actualizada con éxito!");

                } catch (VehiculoNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(ActualizarCamioneta.this, "Error: Camioneta no encontrada.");
                }
            }
        });
        actualizarBtn.setBounds(120, 370, 200, 30);
        contentPane.add(actualizarBtn);

        // Botón para volver al inicio
        JButton inicioBtn = new JButton("Volver");
        inicioBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actualizar volver = new Actualizar();
                volver.setVisible(true);
                ActualizarCamioneta.this.dispose();
            }
        });
        inicioBtn.setBounds(10, 10, 80, 25);
        contentPane.add(inicioBtn);
    }
}