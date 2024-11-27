package es.studium.practica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Ranking extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	GestorConexiones datos = new GestorConexiones();
	
	public Ranking()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar al pulsar la "X"
		setTitle("Consulta Tickets");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 32, 271, 159);
		contentPanel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		datos.conexion();
		textArea.setText(datos.consultarRanking());
		datos.desconectar();
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(154, 201, 114, 44);
		contentPanel.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		setVisible(false);
		new MenuPrincipal();
	}
}
