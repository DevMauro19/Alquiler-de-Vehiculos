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

public class ActualizarMotocicleta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Agencia agencia = new Agencia();

    private JTextField placaActualField;
    private JTextField nuevaPlacaField;
    private JTextField marcaField;
    private JTextField modeloField;
    private JFormattedTextField anioField;
    private JFormattedTextField cilindradaField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ActualizarMotocicleta frame = new ActualizarMotocicleta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ActualizarMotocicleta() {
        setResizable(false);
        setFont(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel tituloLabel = new JLabel("Actualizar Motocicleta");
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

        // Cilindrada
        JLabel cilindradaLabel = new JLabel("Cilindrada:");
        cilindradaLabel.setBounds(50, 250, 100, 20);
        contentPane.add(cilindradaLabel);

        cilindradaField = new JFormattedTextField();
        cilindradaField.setBounds(180, 250, 200, 20);
        contentPane.add(cilindradaField);

        // Botón para actualizar motocicleta
        JButton actualizarBtn = new JButton("Actualizar Motocicleta");
        actualizarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placaActual = placaActualField.getText().trim();
                String nuevaPlaca = nuevaPlacaField.getText().trim();
                String marca = marcaField.getText().trim();
                String modelo = modeloField.getText().trim();
                Integer anio = null;
                Integer cilindrada = null;

                // Intentar obtener valores numéricos
                try {
                    anio = Integer.parseInt(anioField.getText().trim());
                    cilindrada = Integer.parseInt(cilindradaField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ActualizarMotocicleta.this, "Error: Año y Cilindrada deben ser numéricos.");
                    return;
                }

                // Validación de campos vacíos
                if (placaActual.isEmpty() || nuevaPlaca.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anio == null || cilindrada == null) {
                    JOptionPane.showMessageDialog(ActualizarMotocicleta.this, "Error: Todos los campos deben estar llenos.");
                    return;
                }

                try {
                    // Llamar al método de actualización en Agencia
                    agencia.actualizarMotocicleta(placaActual, nuevaPlaca, marca, modelo, anio, cilindrada);
                    JOptionPane.showMessageDialog(ActualizarMotocicleta.this, "Motocicleta actualizada con éxito!");

                } catch (VehiculoNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(ActualizarMotocicleta.this, "Error: Motocicleta no encontrada.");
                }
            }
        });
        actualizarBtn.setBounds(120, 280, 200, 30);
        contentPane.add(actualizarBtn);

        // Botón para volver al inicio
        JButton inicioBtn = new JButton("Volver");
        inicioBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actualizar volver = new Actualizar();
                volver.setVisible(true);
                ActualizarMotocicleta.this.dispose();
            }
        });
        inicioBtn.setBounds(10, 10, 80, 25);
        contentPane.add(inicioBtn);
    }
}
