package es.studium.practica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelBola extends JPanel
{
    private static final long serialVersionUID = 1L;
    private int numBolas = 5;
    Bola[] bola;
    Bolin bolin;
    boolean fin = false;
    private Timer temporizador; // Temporizador para controlar el juego
    private long tiempoInicio; // Variable para almacenar el tiempo de inicio
    private double tiempoTotal;  // Variable para almacenar el tiempo total
    private String nombre;
    boolean altaCorrecta;
    private PrincipalBola ventanaPrincipal;
    
    GestorConexiones datos = new GestorConexiones();
    
    public PanelBola(String n, PrincipalBola ventanaPrincipal)
    {
    	nombre = n;
    	this.ventanaPrincipal = ventanaPrincipal;
        bola = new Bola[numBolas];
        bolin = new Bolin(200, 530, new Color(0, 0, 0));

        Random aleatorio = new Random();
        for (int i = 0; i < numBolas; i++)
        {
            int velocidad = aleatorio.nextInt(50) + 10; // Velocidad entre 10 y 60 ms
            int posX = aleatorio.nextInt(250) + 50;
            int posY = aleatorio.nextInt(300) + 50;
            Color color = new Color(
                aleatorio.nextInt(254),
                aleatorio.nextInt(254),
                aleatorio.nextInt(254)
            );

            bola[i] = new Bola(posX, posY, velocidad, color);
        }

        // Inicializar el tiempo de inicio
        tiempoInicio = System.currentTimeMillis();

        // Configurar el temporizador
        temporizador = new Timer(16, e -> { // ~60 FPS (16 ms por ciclo)
            if (!fin)
            {
                // Actualizar la posición de las bolas
                for (Bola b : bola)
                {
                    b.mover();
                }
                repaint(); // Redibujar el panel
            }
            else
            {
                temporizador.stop(); // Detener el temporizador si el juego ha terminado
            }
        });

        temporizador.start(); // Iniciar el temporizador

        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Dibujar texto en la parte superior
        Font fuente = new Font("Times New Roman", 0, 20);
        g.setFont(fuente);
        g.drawString("Salida", 200, 30);

        // Dibujar las bolas
        for (Bola b : bola)
        {
            b.pinta(g);
        }

        // Dibujar el Bolin
        bolin.pinta(g);
    }

    // Método para mover el Bolin
    public void mover(int direccion)
    {
        int xBolin, yBolin;
        int xBola, yBola;
        xBolin = bolin.dameX();
        yBolin = bolin.dameY();
        
        // Si el Bolin llega a la salida
        if (yBolin <= 30) 
        {
            if (!fin) // Solo ejecutar si el juego no ha terminado
            {
                // Solo mostrar el tiempo si gana
                tiempoTotal = System.currentTimeMillis() - tiempoInicio;
                tiempoTotal = tiempoTotal / 1000.0;
                datos.conexion();
                altaCorrecta = datos.altaTiempo(nombre, tiempoTotal);
                datos.desconectar();
                if(altaCorrecta)
                {
                    JOptionPane.showMessageDialog(this, "Ganaste!!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
                    ventanaPrincipal.setVisible(false);
                    new MenuPrincipal();
                }
                System.out.println(nombre + " terminó en " + tiempoTotal + " segundos.");
            }
            fin = true;
        }
        else
        {
            for (Bola b : bola)
            {
                xBola = b.dameX();
                yBola = b.dameY();
                if ((xBolin + 12 >= xBola - 25) && (yBolin + 12 >= yBola - 25) &&
                    (xBolin - 12 <= xBola + 25) && (yBolin - 12 <= yBola + 25))
                {
                    if (!fin)
                    {
                    	JOptionPane.showMessageDialog(this, "Has muerto!!", "Derrota", JOptionPane.INFORMATION_MESSAGE);
                    	ventanaPrincipal.setVisible(false);
                        new MenuPrincipal();
                    }
                    fin = true;
                }
            }
            if (!fin)
            {
                bolin.mover(direccion); // Mover el Bolin si no ha terminado el juego
            }
        }
    }
}
