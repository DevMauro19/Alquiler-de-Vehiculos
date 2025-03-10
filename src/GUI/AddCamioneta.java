package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import Exceptions.NumeroInvalidoException;
import Exceptions.ParametroNuloException;
import Exceptions.VehiculoDuplicadoException;
import principal.Agencia;

public class AddCamioneta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Agencia agencia = new Agencia();
    private JTextField placaField;
    private JTextField marcaField;
    private JTextField modeloField;
    private JFormattedTextField anioField;
    private JRadioButton hibridoButton;
    private JRadioButton electricoButton;
    private JFormattedTextField capacidadCargaField;
    private JFormattedTextField puertasField;
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCamioneta frame = new AddCamioneta();
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
	public AddCamioneta() {
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
                AddCamioneta.this.dispose();
            }
        });
        incioBtn.setBounds(10, 11, 89, 23);
        contentPane.add(incioBtn);
        
        JLabel camionetaLabel = new JLabel("Añadir camioneta");
        camionetaLabel.setBounds(250, 15, 99, 14);
        contentPane.add(camionetaLabel);
        
        JLabel placaLabel = new JLabel("Placa:");
        placaLabel.setBounds(118, 53, 46, 14);
        contentPane.add(placaLabel);

        JLabel marcaLabel = new JLabel("Marca:");
        marcaLabel.setBounds(118, 101, 46, 14);
        contentPane.add(marcaLabel);

        JLabel modeloLabel = new JLabel("Modelo:");
        modeloLabel.setBounds(118, 149, 46, 14);
        contentPane.add(modeloLabel);

        JLabel anioLabel = new JLabel("Año:");
        anioLabel.setBounds(118, 199, 46, 14);
        contentPane.add(anioLabel);
        
        JLabel capacidadCargaLabel = new JLabel("Capacidad de carga:");
        capacidadCargaLabel.setBounds(71,268,103,32);
        contentPane.add(capacidadCargaLabel);
        
        JLabel motorLabel = new JLabel("Tipo Motor:");
        motorLabel.setBounds(118, 304, 66, 14);
        contentPane.add(motorLabel);
        
        placaField = new JTextField();
        placaField.setBounds(190, 47, 248, 20);
        contentPane.add(placaField);
        placaField.setColumns(10);
        
        marcaField = new JTextField();
        marcaField.setColumns(10);
        marcaField.setBounds(190, 99, 248, 20);
        contentPane.add(marcaField);
        
        modeloField = new JTextField();
        modeloField.setColumns(10);
        modeloField.setBounds(190, 147, 248, 20);
        contentPane.add(modeloField);

        hibridoButton = new JRadioButton("Híbrido");
        hibridoButton.setBounds(190, 300, 66, 23);
        contentPane.add(hibridoButton);

        electricoButton = new JRadioButton("Eléctrico");
        electricoButton.setBounds(283, 300, 77, 23);
        contentPane.add(electricoButton);
        
        JLabel nPuertasLabel = new JLabel("N° Puertas:");
        nPuertasLabel.setBounds(118, 238, 66, 23);
        contentPane.add(nPuertasLabel);
        
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0); 
        numberFormatter.setAllowsInvalid(true); 
        numberFormatter.setCommitsOnValidEdit(false); 
        
        puertasField = new JFormattedTextField(numberFormatter);
        puertasField.setBounds(190, 240, 247, 19);
        contentPane.add(puertasField);
   
        
        anioField = new JFormattedTextField (numberFormatter);
        anioField.setColumns(10);
        anioField.setBounds(190, 197, 248, 20);
        contentPane.add(anioField);
     
        
        capacidadCargaField = new JFormattedTextField(numberFormatter);
        capacidadCargaField.setBounds(190, 275, 248, 19);
        contentPane.add(capacidadCargaField);
        
        JButton camionetaBtn = new JButton("Añadir");
        camionetaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText().trim();
                String marca = marcaField.getText().trim();
                String modelo = modeloField.getText().trim();
                Integer anio = null;
                Integer puertas = null;
                Integer capacidadCarga = null;
                String tipoMotor = null;

                try {
                    anio = (Integer) anioField.getValue();
                } catch (ClassCastException ex) {
                    // Si el año no es un número válido, lo dejamos como null
                	JOptionPane.showMessageDialog(AddCamioneta.this, ex.getMessage());
                }

                try {
                    puertas = (Integer) puertasField.getValue();
                } catch (ClassCastException ex) {
                    // Si la cantidad de puertas no es válida, lo dejamos como null
                	JOptionPane.showMessageDialog(AddCamioneta.this, ex.getMessage());
                }

                try {
                    capacidadCarga = (Integer) capacidadCargaField.getValue();
                } catch (ClassCastException ex) {
                    // Si la capacidad de carga no es válida, lo dejamos como null
                	JOptionPane.showMessageDialog(AddCamioneta.this, ex.getMessage());
                }

                if (hibridoButton.isSelected()) {
                    tipoMotor = "Híbrido";
                } else if (electricoButton.isSelected()) {
                    tipoMotor = "Eléctrico";
                }

                if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anio == null || tipoMotor == null || puertas == null || capacidadCarga == null) {
                    JOptionPane.showMessageDialog(AddCamioneta.this, "Error: todos los campos deben estar llenos y en el formato correcto.");
                } else if (anio <= 2000) {
                    JOptionPane.showMessageDialog(AddCamioneta.this, "Error: el año debe ser mayor a 2000.");
                } else if (puertas < 1) {
                    JOptionPane.showMessageDialog(AddCamioneta.this, "Error: el número de puertas no puede ser menor a 1.");
                } else {
                    try {
                        agencia.agregarCamioneta(placa, marca, modelo, anio, puertas, tipoMotor, capacidadCarga);
                        JOptionPane.showMessageDialog(AddCamioneta.this, "Camioneta registrada con éxito!");
                        Concesionario inicio = new Concesionario();
                        inicio.setVisible(true);
                        AddCamioneta.this.dispose();
                    } catch (VehiculoDuplicadoException | ParametroNuloException | NumeroInvalidoException ex) {
                        JOptionPane.showMessageDialog(AddCamioneta.this, "Error: " + ex.getMessage());
                    }
                }
            }
        });
        camionetaBtn.setBounds(250, 350, 100, 30);
        contentPane.add(camionetaBtn);
        
        JRadioButton gasolinaBtn = new JRadioButton("Gasolina");
        gasolinaBtn.setBounds(386, 300, 77, 23);
        contentPane.add(gasolinaBtn);
        
	}
}
