package com.isaadev.board;

import com.isaadev.characters.PJ;
import com.isaadev.characters.PNJ;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import static com.isaadev.configuration.SystemProperties.getProperty;

public class Tablero extends JPanel implements ActionListener {

    private int anchoTablero, altoTablero;

    private char[][] matrizLectura;
    private Casilla[][] tablero;
    private String archivoMapa;
    private Timer timer;


    // Determina si el juego se encuentra en pausa o no.
    private boolean pausa = false;

    // Indica el tiempo de superpoder punto que queda.
    private int puntoGordo = 0;
    private int puntosRestantes = 0;

    // Indica el tiempo de superpoder punto que queda.
    private int vidas = 1;
    private boolean gameOver = false;


    // Personajes
    private PJ pacman;
    private PNJ blinky, pinky, clyde;

    // Posición actual de cada personaje
    private Casilla casillaPacman;
    private Casilla casillaBlinky, casillaPinky, casillaClyde;
    private Casilla casillaRespawn;
    private Casilla casillaAparicionF;

    private int iActualPacman = 1;
    private String pacmanImage1 = getProperty("pacman.img1");
    private String pacmanImage2 = getProperty("pacman.img2");
    private String blinkyImage = getProperty("blinky.img");
    private String pinkyImage = getProperty("pinky.img");
    private String clydeImage = getProperty("clyde.img");
    private String superModeGhost = getProperty("supermode.ghost.img");
    private String eatenGhost = getProperty("eaten.ghost.img");

    private int puntos = 0;

    public Tablero(String archivoMapa, int altoTablero, int anchoTablero) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        this.anchoTablero = anchoTablero;
        this.altoTablero = altoTablero;
        this.archivoMapa = archivoMapa;

        matrizLectura = new char[altoTablero][anchoTablero];
        tablero = new Casilla[altoTablero][anchoTablero];

        leerArchivo(archivoMapa, matrizLectura);
        crearTablero(matrizLectura, tablero);

        pacman = new PJ(pacmanImage1, casillaPacman);
        blinky = new PNJ(blinkyImage, casillaBlinky, casillaPacman, 120);
        pinky = new PNJ(pinkyImage, casillaPinky, casillaPacman, 200);
        clyde = new PNJ(clydeImage, casillaClyde, casillaPacman, 280);

        timer = new Timer(10, this);
        timer.start();
    }

    /**
     * Realiza la lectura de un archivo facilitado por caracteres
     * y los incluye en una matriz.
     *
     * @archivoLectura es el mapa a leer.
     * @matrizLectura es la matriz que recibirá los caracteres.
     **/

    private void leerArchivo(String archivoLectura, char[][] matrizLectura) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(archivoLectura)));
            for (int i = 0; i <= matrizLectura.length - 1; i++) {
                for (int j = 0; j <= matrizLectura[i].length - 1; j++) {
                    char caracterLeido = (char) br.read();
                    if (caracterLeido == '\n') {
                    } else {
                        matrizLectura[i][j] = caracterLeido;
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Toma una matriz de caracteres y crea una matriz de casillas.
     *
     * @matrizLectura es la matriz de caracteres a interpretar.
     * @tablero es la matriz de casillas que representará el tablero.
     **/

    public void crearTablero(char[][] matriLecturaa, Casilla[][] tablero) {

        // Transcribe el archivo a una matriz de casillas en la cual cada casilla conoce su contenido en cada momento.

        for (int line = 0; line <= matrizLectura.length - 1; line++) {

            char[] row = matrizLectura[line];
            for (int column = 0; column <= row.length - 1; column++) {

                Casilla cell = new Casilla();
                cell.setX(column * 20);
                cell.setY(line * 20);
                tablero[line][column] = cell;


                switch (row[column]) {
                    case '*':
                        cell.setPared(true);
                        break;
                    case 'S':
                        casillaPacman = cell;
                        break;
                    case 'B':
                        casillaBlinky = cell;
                        break;
                    case 'P':
                        casillaPinky = cell;
                        casillaRespawn = cell;
                        break;
                    case 'A':
                        casillaAparicionF = cell;
                        break;
                    case 'C':
                        casillaClyde = cell;
                        break;
                    case '0':
                        cell.setPared(false);
                        break;
                    case '1':
                        puntosRestantes += 1;
                        cell.setPunto(true);
                        break;
                    case '2':
                        puntosRestantes += 1;
                        cell.setPuntoGordo(true);
                        break;
                    case '4':
                        cell.setParedFantasma(true);
                        break;
                    default:
                        cell.setPared(false);
                        break;

                }

            }
        }

        // Indica a la casilla las casillas contigüas.

        for (int line = 0; line <= tablero.length - 1; line++) {
            for (int column = 0; column <= tablero[line].length - 1; column++) {
                Casilla cell = tablero[line][column];
                if (line == 0) {
                    cell.setAr(null);
                    cell.setAb(tablero[line + 1][column]);
                } else if (line == tablero.length - 1) {
                    cell.setAb(null);
                    cell.setAr(tablero[line - 1][column]);
                } else {
                    cell.setAr(tablero[line - 1][column]);
                    cell.setAb(tablero[line + 1][column]);
                }

                if (column == 0) {
                    cell.setIzq(null);
                    cell.setDcha(tablero[line][column + 1]);
                } else if (column == tablero[line].length - 1) {
                    cell.setDcha(null);
                    cell.setIzq(tablero[line][column - 1]);
                } else {
                    cell.setIzq(tablero[line][column - 1]);
                    cell.setDcha(tablero[line][column + 1]);
                }

            }
        }

    }

    /**
     * Sobrescribe el método paint para usar Graphics2D
     */

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        int xInicio, yInicio;

        puntosRestantes = 0;

        for (int i = 0; i <= tablero.length - 1; i++) {
            for (int j = 0; j <= tablero[i].length - 1; j++) {

                Casilla actual = tablero[i][j];

                if (tablero[i][j].isPared() == true) {
                    puntosRestantes += 1;
                    g2d.setColor(Color.BLUE);
                    g2d.fillRect(actual.getX(), actual.getY(), actual.getWidth(), actual.getHeight());
                    g2d.drawRect(actual.getX(), actual.getY(), actual.getWidth(), actual.getHeight());
                } else if (tablero[i][j].isParedFantasma() == true) {
                    g2d.setColor(Color.CYAN);
                    g2d.fillRect(actual.getX(), actual.getY(), actual.getWidth(), actual.getHeight());
                    g2d.drawRect(actual.getX(), actual.getY(), actual.getWidth(), actual.getHeight());
                } else if (tablero[i][j].isPunto() == true) {
                    xInicio = actual.getX() + 7;
                    yInicio = actual.getY() + 7;
                    g2d.setColor(Color.YELLOW);
                    g2d.fillOval(xInicio, yInicio, 6, 6);
                    g2d.drawOval(xInicio, yInicio, 6, 6);
                } else if (tablero[i][j].isPuntoGordo() == true) {
                    xInicio = actual.getX() + 5;
                    yInicio = actual.getY() + 5;
                    g2d.setColor(Color.YELLOW);
                    g2d.fillOval(xInicio, yInicio, 10, 10);
                    g2d.drawOval(xInicio, yInicio, 10, 10);
                }
            }
        }


        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 15));
        g2d.drawString("PUNTUACIÓN ACTUAL: " + Integer.toString(puntos), 0, 20 * tablero.length + 20);
        g2d.drawString("VIDAS: " + Integer.toString(vidas), 20 * tablero[0].length - 80, 20 * tablero.length + 20);

        g2d.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), this);
        g2d.drawImage(blinky.getImage(), blinky.getX(), blinky.getY(), this);
        g2d.drawImage(pinky.getImage(), pinky.getX(), pinky.getY(), this);
        g2d.drawImage(clyde.getImage(), clyde.getX(), clyde.getY(), this);

        g2d.setFont(new Font("Times New Roman", Font.BOLD, 40));
        if (gameOver == true) {
            g2d.drawString("GAME OVER", 190, 300);
            timer.stop();
        } else if (puntosRestantes == 0) {
            g2d.setFont(new Font("Times New Roman", Font.BOLD, 30));
            g2d.drawString("!Felicidades has ganado la partida! \n Tu putntuación es: " + Integer.toString(puntos), 180, 300);
            timer.stop();
        } else if (pausa == true) {
            g2d.drawString("PAUSA", 240, 300);
        }
        Toolkit.getDefaultToolkit().sync();

        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        requestFocus();

        if (vidas < 1) {
            gameOver = true;
        } else {
            comprobarColision();

            if (blinky.getContador() > 0) {
                blinky.cuentaAtras();
            } else if (blinky.vivo == false) {
                blinky.setX(casillaAparicionF.getX() - 4);
                blinky.setY(casillaAparicionF.getY() - 4);
                blinky.vivo = true;
            } else {
                blinky.moverVH();
            }


            if (pinky.getContador() > 0) {
                pinky.cuentaAtras();
            } else if (pinky.vivo == false) {
                pinky.setX(casillaAparicionF.getX() - 4);
                pinky.setY(casillaAparicionF.getY() - 4);
                pinky.vivo = true;
            } else {
                pinky.moverHV();
            }

            if (clyde.getContador() > 0) {
                clyde.cuentaAtras();
            } else if (clyde.vivo == false) {
                clyde.setX(casillaAparicionF.getX() - 4);
                clyde.setY(casillaAparicionF.getY() - 4);
                clyde.vivo = true;
            } else {
                clyde.moverRdm();
            }

            if (iActualPacman != 15 && iActualPacman != 30) {
                iActualPacman += 1;
            } else if (iActualPacman == 15) {
                pacman.setImage(pacmanImage2);
                iActualPacman += 1;
            } else if (iActualPacman == 30) {
                pacman.setImage(pacmanImage1);
                iActualPacman = 0;
            }
            ;

            pacman.mover();

            casillaPacman = tablero[(pacman.getY() + 12) / 20][(pacman.getX() + 12) / 20];
            casillaBlinky = tablero[(blinky.getY() + 12) / 20][(blinky.getX() + 12) / 20];
            casillaPinky = tablero[(pinky.getY() + 12) / 20][(pinky.getX() + 12) / 20];
            casillaClyde = tablero[(clyde.getY() + 12) / 20][(clyde.getX() + 12) / 20];


            blinky.actualizarPosiciones(casillaBlinky, casillaPacman);
            pinky.actualizarPosiciones(casillaPinky, casillaPacman);
            clyde.actualizarPosiciones(casillaClyde, casillaPacman);


            if (puntoGordo > 0) {
                puntoGordo -= 1;
                if (puntoGordo == 1) {
                    blinky.setImage(blinkyImage);
                    pinky.setImage(pinkyImage);
                    clyde.setImage(clydeImage);
                }
            }
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {


        public void keyPressed(KeyEvent e) {

            char caracter = e.getKeyChar();

            if (caracter == 'p' || caracter == 'P') {
                if (pausa == false) {
                    pausa = true;
                    timer.stop();
                    System.out.println("pausa on");
                } else {
                    pausa = false;
                    timer.start();
                    System.out.println("pausa off");
                }
            }

            pacman.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            pacman.keyReleased(e);
        }

    }

    public void comprobarColision() {

        Rectangle rpacman = pacman.getLimites();
        Rectangle rblinky = blinky.getLimites();
        Rectangle rpinky = pinky.getLimites();
        Rectangle rclyde = clyde.getLimites();

        // Recorremos el tablero para saber si pacman choca con una pared, punto o puntoGordo.
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j].isPared() == true) {

                    Rectangle rParedDest = tablero[i][j].getLimites();

                    if (rpacman.intersects(rParedDest)) {
                        pacman.setDY(0);
                        pacman.setDX(0);
                    }
                } else if (tablero[i][j].isParedFantasma() == true) {

                    Rectangle rparedfantasma = tablero[i][j].getLimites();
                    if (rpacman.intersects(rparedfantasma)) {
                        pacman.setDY(0);
                        pacman.setDX(0);
                    }

                } else if (tablero[i][j].isPunto() == true) {

                    Rectangle rpunto = tablero[i][j].getLimites();
                    if (rpacman.intersects(rpunto)) {
                        puntos += 10;
                        tablero[i][j].setPunto(false);
                    }

                } else if (tablero[i][j].isPuntoGordo() == true) {

                    Rectangle rpunto = tablero[i][j].getLimites();
                    if (rpacman.intersects(rpunto)) {
                        puntoGordo = 500;
                        blinky.setImage(superModeGhost);
                        pinky.setImage(superModeGhost);
                        clyde.setImage(superModeGhost);
                        tablero[i][j].setPuntoGordo(false);
                    }
                }

                if (pacman.getX() < -1) {
                    pacman.setX((tablero[i].length - 1) * 20);
                } else if (pacman.getY() < -1) {
                    pacman.setY(tablero.length * 20);
                } else if (pacman.getX() > (tablero[i].length - 1) * 20 + 1) {
                    pacman.setX(0);
                } else if (pacman.getY() > (tablero.length - 1) * 20 + 1) {
                    pacman.setY(0);
                }


            }
        }
        if (blinky.getX() == -5) {
            blinky.setX(tablero[0].length * 20);
        } else if (blinky.getY() == -5) {
            blinky.setY(tablero.length * 20);
        } else if (blinky.getX() == (tablero[0].length - 1) * 20 + 5) {
            blinky.setX(0);
        } else if (blinky.getY() == (tablero.length - 1) * 20 + 5) {
            blinky.setY(0);
        }

        if (pinky.getX() == -5) {
            pinky.setX(tablero[0].length * 20);
        } else if (pinky.getY() == -5) {
            pinky.setY(tablero.length * 20);
        } else if (pinky.getX() == (tablero[0].length - 1) * 20 + 5) {
            pinky.setX(0);
        } else if (pinky.getY() == (tablero.length - 1) * 20 + 5) {
            pinky.setY(0);
        }

        if (clyde.getX() == -5) {
            clyde.setX(tablero[0].length * 20);
        } else if (clyde.getY() == -5) {
            clyde.setY(tablero.length * 20);
        } else if (clyde.getX() == (tablero[0].length - 1) * 20 + 5) {
            clyde.setX(0);
        } else if (clyde.getY() == (tablero.length - 1) * 20 + 5) {
            clyde.setY(0);
        }

        if (rpacman.intersects(rblinky)) {
            if (puntoGordo > 0) {
                blinky.vivo = false;
                blinky.setContador(500);
                blinky.setImage(blinkyImage);
                blinky.setX(casillaRespawn.getX());
                blinky.setY(casillaRespawn.getY());
                puntos += 100;
            } else {
                vidas -= 1;
            }
        }

        if (rpacman.intersects(rpinky)) {
            if (puntoGordo > 0) {
                pinky.vivo = false;
                pinky.setImage(pinkyImage);
                pinky.setContador(500);
                pinky.setX(casillaRespawn.getX());
                pinky.setY(casillaRespawn.getY());
                puntos += 100;
            } else {
                vidas -= 1;
            }
        }

        if (rpacman.intersects(rclyde)) {
            if (puntoGordo > 0) {
                clyde.vivo = false;
                clyde.setContador(500);
                clyde.setImage(clydeImage);
                clyde.setX(casillaRespawn.getX());
                clyde.setY(casillaRespawn.getY());
                puntos += 100;
            } else {
                vidas -= 1;
            }
        }

    }

}