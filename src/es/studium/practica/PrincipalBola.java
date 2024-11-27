package es.studium.practica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class PrincipalBola extends JFrame implements KeyListener
{
	private static final long serialVersionUID = 1L;
	static PanelBola panel;  // PanelBola sin inicializar aún

	// Constructor que recibe el nombre
	public PrincipalBola(String nombre)
	{
		super();
		panel = new PanelBola(nombre, this); // Pasa el nombre al constructor de PanelBola
		addKeyListener(this); // Agregar el KeyListener al JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurar la acción al cerrar
		setTitle("Bolas"); // Título de la ventana
		setContentPane(panel); // Establecer el contenido del panel
		setSize(500, 600); // Tamaño de la ventana
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setVisible(true); // Hacer visible la ventana
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// No implementado
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		switch (arg0.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			panel.mover(2);
			break;
		case KeyEvent.VK_UP:
			panel.mover(3);
			break;
		case KeyEvent.VK_RIGHT:
			panel.mover(0);
			break;
		case KeyEvent.VK_DOWN:
			panel.mover(1);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// No implementado
	}
}
