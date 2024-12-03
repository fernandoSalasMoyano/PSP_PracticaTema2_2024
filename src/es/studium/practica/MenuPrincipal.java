package es.studium.practica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame implements ActionListener
{
	static final long serialVersionUID = 1L;

	// Crear los botones
	JButton btnNuevaPartida = new JButton("Nueva Partida");
	JButton btnRanking = new JButton("Ranking");
	JButton btnSalir = new JButton("Salir");

	// Constructor de la clase
	public MenuPrincipal()
	{
		// Configuración de la ventana
		setVisible(true);
		setTitle("Menú Principal");
		setSize(300, 200); // Tamaño de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar al pulsar la "X"
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla

		// Crear un contenedor para los componentes
		Container contenedor = getContentPane();
		contenedor.setLayout(new FlowLayout()); // Disposición de los botones

		btnNuevaPartida.addActionListener(this);
		btnRanking.addActionListener(this);
		btnSalir.addActionListener(this);

		// Añadir los botones al contenedor
		contenedor.add(btnNuevaPartida);
		contenedor.add(btnRanking);
		contenedor.add(btnSalir);
	}

	// Método principal para ejecutar el programa
	public static void main(String[] args)
	{

		new MenuPrincipal();
		
	}

	@Override
	public void actionPerformed(ActionEvent evento)
	{
		if (evento.getSource().equals(btnNuevaPartida))
		{
			setVisible(false);
			new NuevaPartida();
		}
		else if(evento.getSource().equals(btnRanking))
		{
			setVisible(false);
			new Ranking();
		}
		else
		{
			System.exit(0);
		}
		
	}
}
