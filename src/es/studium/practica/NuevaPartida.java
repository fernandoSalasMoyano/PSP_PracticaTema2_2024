package es.studium.practica;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NuevaPartida extends JFrame implements ActionListener
{
    static final long serialVersionUID = 1L;
    JLabel lblNombre = new JLabel("Indique su nombre:");
    JTextField txtNombre = new JTextField(10);
    JButton btnAceptar = new JButton("Aceptar");
    JButton btnVolver = new JButton("Volver");

    public NuevaPartida()
    {
        // Configuración de la ventana
        setVisible(true);
        setTitle("Nueva Partida");
        setSize(300, 200); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar al pulsar la "X"
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear un contenedor para los componentes
        Container contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout()); // Disposición

        btnAceptar.addActionListener(this);
        btnVolver.addActionListener(this);

        // Añadir al contenedor
        contenedor.add(lblNombre);
        contenedor.add(txtNombre);
        contenedor.add(btnAceptar);
        contenedor.add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent evento)
    {
        if (evento.getSource().equals(btnAceptar))
        {
            // Comprobar si el campo de texto está vacío
            if (txtNombre.getText().trim().isEmpty())
            {
                // Mostrar un cuadro de diálogo si el nombre está vacío
                JOptionPane.showMessageDialog(this, "Por favor, introduzca un nombre antes de continuar.", "Error", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
            	setVisible(false);
            	String nombre = txtNombre.getText();
                // Crear una nueva instancia de PrincipalBola si el nombre es válido
                new PrincipalBola(nombre);
            }
        }
        else
        {
            // Volver al menú principal
            setVisible(false);
            new MenuPrincipal();
        }
    }
}
