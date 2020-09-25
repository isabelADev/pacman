package com.isaadev;
/**
 *  
 * @author (your name) 
 * @version (a version number or a date)
 */

import com.isaadev.board.Tablero;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.isaadev.configuration.SystemProperties.getProperty;

public class Juego extends JFrame {
    private String titulo = getProperty("title.img");
    private String archivoMapa = getProperty("map.path");
    private Tablero tablero = new Tablero(archivoMapa, 31, 28);
    private JLabel puntuacion;
    
    /**
     * Inicializa la ventana del Juego Pac-man
     */
    public Juego(){
        setSize(565,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        construirMenu();
        this.add(tablero);
    }
    
    public static void main(String[] args){
        new Juego();
    };

    private void salir() {
        System.exit(0);
    }
    
    private void construirMenu()
    {
        JMenuBar barraDeMenu = new JMenuBar();
        setJMenuBar(barraDeMenu);
                
        JMenu menuJuego = new JMenu("Juego");
        barraDeMenu.add(menuJuego);

        JMenuItem opcionNuevo = new JMenuItem("Nuevo Juego");
        menuJuego.add(opcionNuevo);
        JMenuItem opcionPausar = new JMenuItem("Pausar");
        menuJuego.add(opcionPausar);
        JMenuItem opcionSalir = new JMenuItem("Salir");
        opcionSalir.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { salir(); }
                           });
        menuJuego.add(opcionSalir);

        JMenu menuAyuda = new JMenu("Ayuda");
        barraDeMenu.add(menuAyuda);

        JMenuItem opcionAyuda = new JMenuItem("Como jugar");
        menuAyuda.add(opcionAyuda);
        JMenuItem opcionAcercaDe = new JMenuItem("Acerca de..");
        menuAyuda.add(opcionAcercaDe);
    }
}