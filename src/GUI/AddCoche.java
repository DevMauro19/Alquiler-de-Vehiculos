package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import Exceptions.NumeroInvalidoException;
import Exceptions.ParametroNuloException;
import Exceptions.VehiculoDuplicadoException;
import principal.Agencia;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class AddCoche extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private Agencia agencia = new Agencia();
    private JTextField placaField;
    private JTextField marcaField;
    private JTextField modeloField;
    private JFormattedTextField anioField;
    private JRadioButton hibridoButton;
    private JRadioButton electricoButton;
    private ButtonGroup motorGroup;
    private JFormattedTextField puertasField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddCoche frame = new AddCoche();
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
    public AddCoche() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 601, 488);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton incioBtn = new JButton("Inicio");
        incioBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Concesionario inicio = new Concesionario();
                inicio.setVisible(true);
                AddCoche.this.dispose();
            }
        });
        incioBtn.setBounds(10, 11, 89, 23);
        contentPane.add(incioBtn);
        
        JLabel cocheLabel = new JLabel("Añadir carro");
        cocheLabel.setBounds(272, 15, 77, 14);
        contentPane.add(cocheLabel);
        
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
        
        JLabel motorLabel = new JLabel("Tipo Motor:");
        motorLabel.setBounds(114, 308, 66, 14);
        contentPane.add(motorLabel);
        
        placaField = new JTextField();
        placaField.setBounds(190, 47, 248, 20);
        contentPane.add(placaField);
        placaField.setColumns(10);
        
        marcaField = new JTextField();
        marcaField.setColumns(10);
        marcaField.setBounds(190, 97, 248, 20);
        contentPane.add(marcaField);
        
        modeloField = new JTextField();
        modeloField.setColumns(10);
        modeloField.setBounds(190, 147, 248, 20);
        contentPane.add(modeloField);
        
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0); 
        numberFormatter.setAllowsInvalid(true); 
        numberFormatter.setCommitsOnValidEdit(false); 
        
        anioField = new JFormattedTextField (numberFormatter);
        anioField.setColumns(10);
        anioField.setBounds(190, 197, 248, 20);
        contentPane.add(anioField);
        
        JLabel puertasLabel = new JLabel("N° Puertas:");
        puertasLabel.setBounds(118, 251, 62, 14);
        contentPane.add(puertasLabel);
        
        puertasField = new JFormattedTextField(numberFormatter);
        puertasField.setColumns(10);
        puertasField.setBounds(190, 248, 248, 20);
        contentPane.add(puertasField);

        hibridoButton = new JRadioButton("Híbrido");
        hibridoButton.setBounds(193, 304, 77, 23);
        contentPane.add(hibridoButton);

        electricoButton = new JRadioButton("Eléctrico");
        electricoButton.setBounds(272, 304, 77, 23);
        contentPane.add(electricoButton);

        motorGroup = new ButtonGroup();
        motorGroup.add(hibridoButton);
        motorGroup.add(electricoButton);
        

        JButton cocheBtn = new JButton("Añadir ");
        cocheBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText().trim();
                String marca = marcaField.getText().trim();
                String modelo = modeloField.getText().trim();
                Integer anio = null;
                Integer puertas = null;
                String tipoMotor = null;

                try {
                    anio = (Integer) anioField.getValue();
                } catch (ClassCastException ex) {
                    // Si no se puede convertir el valor a Integer, ignora el valor
                	JOptionPane.showMessageDialog(AddCoche.this, ex.getMessage());
                }
                
                try {
                	puertas = (Integer) puertasField.getValue();
                } catch(ClassCastException ex) {
                	JOptionPane.showMessageDialog(AddCoche.this, ex.getMessage());
                }
              
                if (hibridoButton.isSelected()) {
                    tipoMotor = "Híbrido";
                } else if (electricoButton.isSelected()) {
                    tipoMotor = "Eléctrico";
                }

                if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anio == null || tipoMotor == null) {
                    JOptionPane.showMessageDialog(AddCoche.this, "Error: todos los campos deben estar llenos.");
                } else {
                    if (anio <= 2000) {
                        JOptionPane.showMessageDialog(AddCoche.this, "Error: el año debe ser mayor a 2000.");
                    } else if (puertas < 1) {
                        JOptionPane.showMessageDialog(AddCoche.this, "Error: el numero de puertas no puede ser negativo o nulo");
                    } else {
                        try {
                            // Llamar al método agregarCoche
                            agencia.agregarCoche(placa, marca, modelo, anio, puertas, tipoMotor);
                            JOptionPane.showMessageDialog(AddCoche.this, "Carro registrado con éxito!");
                            Concesionario inicio = new Concesionario();
                            inicio.setVisible(true);
                            AddCoche.this.dispose();
                        } catch (VehiculoDuplicadoException ex) {
                            JOptionPane.showMessageDialog(AddCoche.this, "Error: Vehículo ya existe.");
                        } catch (ParametroNuloException ex) {
                            JOptionPane.showMessageDialog(AddCoche.this, "Error: los parámetros no pueden ser nulos.");
                        } catch (NumeroInvalidoException ex) {
                            JOptionPane.showMessageDialog(AddCoche.this, "Error: los números ingresados son inválidos.");
                        }
                    }
                }
            }
        });
        
        cocheBtn.setBounds(260, 358, 89, 23);
        contentPane.add(cocheBtn);
        
        JRadioButton gasolinaBtn = new JRadioButton("Gasolina");
        gasolinaBtn.setBounds(362, 304, 77, 23);
        contentPane.add(gasolinaBtn);
        
    }
}
