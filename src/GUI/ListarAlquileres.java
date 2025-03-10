package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
import principal.Alquiler;
public class ListarAlquileres extends JFrame{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Agencia agencia = new Agencia();
    
    
    
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListarAlquileres frame = new ListarAlquileres();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ListarAlquileres() {
        setResizable(false);
        setFont(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Botón para volver al inicio
        JButton volverBtn = new JButton("Volver");
        volverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Listar volver = new Listar();
                volver.setVisible(true);
                ListarAlquileres.this.dispose();
            }
        });
        volverBtn.setBounds(10, 10, 80, 25);
        contentPane.add(volverBtn);
        // Botón para volver a Listar
        JButton ListarBtn = new JButton("Listar");
        ListarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Listar inicio = new Listar();
                inicio.setVisible(true);
                ListarAlquileres.this.dispose();
            }
        });
        ListarBtn.setBounds(100, 10, 80, 25);
        contentPane.add(ListarBtn);
        
     // ✅ Mostrar los alquileres correctamente en el JTextArea
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(agencia.listarAlquileres2()); // ✅ Usa correctamente el método

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 100, 650, 500);
        contentPane.add(scrollPane);
        
        JLabel listarAlLabel = new JLabel("Listar Alquileres");
        listarAlLabel.setBounds(305, 12, 90, 20);
        contentPane.add(listarAlLabel);
}
}