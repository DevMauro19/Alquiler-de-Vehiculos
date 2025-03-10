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
import javax.swing.JTextField;

import Exceptions.ClienteNoEncontradoException;
import principal.Agencia;

public class ActualizarCliente extends JFrame {

    private static final long serialVersionUID = 1L;/*importa una interfaz y hace una 
    conversión de un objeto en un flujo de bytes para guardar o enviar a otro sistema*/
    
    private JPanel contentPane;
    private Agencia agencia = new Agencia();

    private JTextField idActualField;
    private JTextField nuevoIdField;
    private JTextField nombreField;
    private JTextField telefonoField;
    private JTextField emailField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ActualizarCliente frame = new ActualizarCliente();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ActualizarCliente() {
        setResizable(false);
        setFont(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel tituloLabel = new JLabel("Actualizar Cliente");
        tituloLabel.setBounds(160, 10, 150, 20);
        contentPane.add(tituloLabel);

        // ID actual del cliente
        JLabel idActualLabel = new JLabel("ID Actual:");
        idActualLabel.setBounds(50, 50, 100, 20);
        contentPane.add(idActualLabel);

        idActualField = new JTextField();
        idActualField.setBounds(180, 50, 200, 20);
        contentPane.add(idActualField);

        // Nuevo ID del cliente
        JLabel nuevoIdLabel = new JLabel("Nuevo ID:");
        nuevoIdLabel.setBounds(50, 90, 100, 20);
        contentPane.add(nuevoIdLabel);

        nuevoIdField = new JTextField();
        nuevoIdField.setBounds(180, 90, 200, 20);
        contentPane.add(nuevoIdField);

        // Nombre del cliente
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(50, 130, 100, 20);
        contentPane.add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(180, 130, 200, 20);
        contentPane.add(nombreField);

        // Teléfono del cliente
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(50, 170, 100, 20);
        contentPane.add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(180, 170, 200, 20);
        contentPane.add(telefonoField);

        // Email del cliente
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 210, 100, 20);
        contentPane.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(180, 210, 200, 20);
        contentPane.add(emailField);

        // Botón para actualizar cliente
        JButton actualizarBtn = new JButton("Actualizar Cliente");
        actualizarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idActual = idActualField.getText().trim();
                String nuevoId = nuevoIdField.getText().trim();
                String nombre = nombreField.getText().trim();
                String telefono = telefonoField.getText().trim();
                String email = emailField.getText().trim();

                // Validación de campos vacíos
                if (idActual.isEmpty() || nuevoId.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(ActualizarCliente.this, "Error: Todos los campos deben estar llenos.");
                    return;
                }

                try {
                    // Llamar al método de actualización en Agencia
                    agencia.actualizarCliente(idActual, nuevoId, nombre, telefono, email);
                    JOptionPane.showMessageDialog(ActualizarCliente.this, "Cliente actualizado con éxito!");

                } catch (ClienteNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(ActualizarCliente.this, "Error: Cliente no encontrado.");
                }
            }
        });
        actualizarBtn.setBounds(140, 260, 180, 30);
        contentPane.add(actualizarBtn);

        // Botón para volver al inicio
        JButton inicioBtn = new JButton("Volver");
        inicioBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actualizar volver = new Actualizar();
                volver.setVisible(true);
                ActualizarCliente.this.dispose();
            }
        });
        inicioBtn.setBounds(10, 10, 80, 25);
        contentPane.add(inicioBtn);
    }
}