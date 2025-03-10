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

import Exceptions.NumeroInvalidoException;
import Exceptions.ParametroNuloException;
import Exceptions.VehiculoDuplicadoException;
import principal.Agencia;

import java.text.NumberFormat;

public class AddMoto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField placaField;
    private JTextField marcaField;
    private JTextField modeloField;
    private JFormattedTextField anioField;
    private JFormattedTextField cilindrajeField;
    private Agencia agencia = new Agencia();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddMoto frame = new AddMoto();
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
    public AddMoto() {
        setResizable(false); //para que no puedan modificar el tamaño de la pantalla
        setFont(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 644, 464);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //labels o cajas de texto simple
        JLabel addMotoLabel = new JLabel("Añadir Moto");
        addMotoLabel.setBounds(286, 11, 89, 14);
        contentPane.add(addMotoLabel);

        JLabel placaLabel = new JLabel("Placa:");
        placaLabel.setBounds(118, 53, 46, 14);
        contentPane.add(placaLabel);

        JLabel marcaLabel = new JLabel("Marca:");
        marcaLabel.setBounds(118, 101, 46, 14);
        contentPane.add(marcaLabel);

        JLabel modeloLabel = new JLabel("Modelo:");
        modeloLabel.setBounds(118, 162, 46, 14);
        contentPane.add(modeloLabel);

        JLabel anioLabel = new JLabel("Año:");
        anioLabel.setBounds(118, 208, 46, 14);
        contentPane.add(anioLabel);

        JLabel cilindradaLabel = new JLabel("Cilindraje:");
        cilindradaLabel.setBounds(118, 261, 61, 14);
        contentPane.add(cilindradaLabel);
        
        //fields, son campos donde el usuario ingresa datos
        placaField = new JTextField();
        placaField.setBounds(189, 50, 272, 20);
        contentPane.add(placaField);
        placaField.setColumns(10);

        marcaField = new JTextField();
        marcaField.setColumns(10);
        marcaField.setBounds(189, 98, 272, 20);
        contentPane.add(marcaField);

        modeloField = new JTextField();
        modeloField.setColumns(10);
        modeloField.setBounds(189, 159, 272, 20);
        contentPane.add(modeloField);

        // Verificar entradas de año mayor a 2000 y cilindraje mayor a 50
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0); 
        numberFormatter.setAllowsInvalid(true); 
        numberFormatter.setCommitsOnValidEdit(false); 

        anioField = new JFormattedTextField(numberFormatter);
        anioField.setColumns(10);
        anioField.setBounds(189, 205, 272, 20);
        contentPane.add(anioField);

        cilindrajeField = new JFormattedTextField(numberFormatter);
        cilindrajeField.setColumns(10);
        cilindrajeField.setBounds(189, 258, 272, 20);
        contentPane.add(cilindrajeField);
        
        //boton para volver a la interfaz principal
        JButton inicio = new JButton("Inicio");
        inicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Concesionario inicio = new Concesionario();
                inicio.setVisible(true);
                AddMoto.this.dispose();
            }
        });
        inicio.setBounds(0, 7, 89, 23);
        contentPane.add(inicio);
        
        //boton agregar
        JButton agregarBtn = new JButton("Añadir");
        agregarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText().trim();
                String marca = marcaField.getText().trim();
                String modelo = modeloField.getText().trim();
                Integer anio = null;
                Integer cilindraje = null;
                
                try {
                    anio = (Integer) anioField.getValue();
                } catch (ClassCastException ex) {
                    // Si no se puede convertir el valor a Integer, ignora el valor
                }

                try {
                    cilindraje = (Integer) cilindrajeField.getValue();
                } catch (ClassCastException ex) {
                    // Si no se puede convertir el valor a Integer, ignora el valor
                }

                if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anio == null || cilindraje == null) {
                    JOptionPane.showMessageDialog(AddMoto.this, "Error: todos los campos deben estar llenos.");
                } else {
                    if (anio <= 2000) {
                        JOptionPane.showMessageDialog(AddMoto.this, "Error: el año debe ser mayor a 2000.");
                    } else if (cilindraje <= 50) {
                        JOptionPane.showMessageDialog(AddMoto.this, "Error: el cilindraje debe ser mayor a 50.");
                    } else {
                        try {
                            // Llamar al método agregarMotocicleta
                            agencia.agregarMotocicleta(placa, marca, modelo, anio, cilindraje);
                            JOptionPane.showMessageDialog(AddMoto.this, "Motocicleta registrada con éxito!");
                            Concesionario inicio = new Concesionario();
                            inicio.setVisible(true);
                            AddMoto.this.dispose();
                        } catch (VehiculoDuplicadoException ex) {
                            JOptionPane.showMessageDialog(AddMoto.this, "Error: Vehículo ya existe.");
                        } catch (ParametroNuloException ex) {
                            JOptionPane.showMessageDialog(AddMoto.this, "Error: los parámetros no pueden ser nulos.");
                        } catch (NumeroInvalidoException ex) {
                            JOptionPane.showMessageDialog(AddMoto.this, "Error: los números ingresados son inválidos.");
                        }
                    }
                }
            }
        });

        agregarBtn.setBounds(286, 321, 89, 23);
        contentPane.add(agregarBtn);
    }
}
