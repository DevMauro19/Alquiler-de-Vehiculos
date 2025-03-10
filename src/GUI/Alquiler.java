package GUI;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import principal.Agencia;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alquiler extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Agencia agencia = new Agencia();
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alquiler frame = new Alquiler();
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
	public Alquiler() {
		setTitle("Alquilar");
        setSize(691, 475);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 1, 1));
        
        JButton btnAlquilar = new JButton("Alquilar");
        JButton btnDevolver = new JButton("Devolver");	
        
        btnAlquilar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AlquilarFrame alqVentana = new AlquilarFrame();
                alqVentana.setVisible(true);
                Alquiler.this.dispose();
            }
        });
        
        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DevolverFrame devVentana = new DevolverFrame();
                devVentana.setVisible(true);
                Alquiler.this.dispose();
            }
        });
        
        JButton volverBtn = new JButton("Inicio");
        volverBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Concesionario inicio = new Concesionario();
        		inicio.setVisible(true);
        		Alquiler.this.dispose();
        	}
        });
        panel.add(volverBtn);
        
        panel.add(btnAlquilar);
        panel.add(btnDevolver);
        
        getContentPane().add(panel);
        setVisible(true);
        
	}
	

}