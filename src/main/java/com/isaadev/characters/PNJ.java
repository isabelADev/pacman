package com.isaadev.characters;

import com.isaadev.board.Casilla;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static java.util.Objects.requireNonNull;

public class PNJ extends Personaje {
    private Casilla objetivo;
    // Es el camino que seguirá el PNJ para encontrar su objetivo.
    private ArrayList<Casilla> caminoASeguir = new ArrayList<Casilla>();
    private boolean encontrado = false;
    private boolean buscar = true;
    private int contadorAparicion;
    public boolean vivo = false;

    public PNJ(String directorioImagen, Casilla posicion, Casilla objetivo, int contadorAparicion) {


        ImageIcon ii = new ImageIcon(requireNonNull(this.getClass().getResource(directorioImagen)));
        image = ii.getImage();

        this.posicion = posicion;
        this.x = posicion.getX();
        this.y = posicion.getY();

        this.objetivo = objetivo;
        this.contadorAparicion = contadorAparicion;

    }

    public void actualizarPosiciones(Casilla nuevaPosicion, Casilla nuevoObjetivo) {
        this.posicion = nuevaPosicion;
        this.objetivo = nuevoObjetivo;
    }

    public void setContador(int contador) {
        contadorAparicion = contador;
    }

    public int getContador() {
        return this.contadorAparicion;
    }

    public void cuentaAtras() {
        this.contadorAparicion -= 1;
    }

    public void moverVH() {

        if (buscar) {

            buscar = false;
            encontrado = false;
            // Establece un objetivo fijo hasta que sea encontrado
            Casilla objetivoASeguir = objetivo;
            // Determina la posición inicial de la que partirá el PNJ para la búsqueda.
            Casilla actualPNJ = posicion;

            caminoASeguir.add(actualPNJ);

            int distYobjetivo, distXobjetivo;
            // Mientras que el camino sea menor que 10 o estemos en la casilla del objetivo.
            while (!encontrado) {

                // Posición actual en la que se encuentra dentro del Arraylist.
                distYobjetivo = objetivoASeguir.getY() - actualPNJ.getY();
                distXobjetivo = objetivoASeguir.getX() - actualPNJ.getX();

                // Comenzamos la búsqueda del camino.
                if (caminoASeguir.size() == 6 || objetivoASeguir.equals(actualPNJ)) {

                    encontrado = true;
                    caminoASeguir.remove(0);

                } else if (distYobjetivo < 0) {
                    if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                        caminoASeguir.add(actualPNJ.getAr());
                        actualPNJ = actualPNJ.getAr();
                    } else if (distXobjetivo < 0) {
                        if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        }
                    } else if (distXobjetivo > 0) {
                        if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        }
                    } else {
                        if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        }
                    }
                } else if (distYobjetivo > 0) {
                    if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                        caminoASeguir.add(actualPNJ.getAb());
                        actualPNJ = actualPNJ.getAb();
                    } else if (distXobjetivo < 0) {
                        if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    } else if (distXobjetivo > 0) {
                        if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    } else {
                        if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    }
                } else {

                    if (distXobjetivo < 0) {
                        if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    } else if (distXobjetivo > 0) {
                        if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    } else {
                        if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    }
                }
            }
        } else if (caminoASeguir.size() != 0) {
            int centroYProxCasilla = caminoASeguir.get(0).getY() + 10;
            int centroXProxCasilla = caminoASeguir.get(0).getX() + 10;
            int centroYPNJ = this.getY() + 12;
            int centroXPNJ = this.getX() + 12;
            int distYSigPosicion = centroYProxCasilla - centroYPNJ;
            int distXSigPosicion = centroXProxCasilla - centroXPNJ;


            if (distYSigPosicion < 0) {
                dx = 0;
                dy = -1;
            } else if (distYSigPosicion > 0) {
                dx = 0;
                dy = 1;
            } else if (distXSigPosicion < 0) {
                dy = 0;
                dx = -1;
            } else if (distXSigPosicion > 0) {
                dy = 0;
                dx = 1;
            } else {
                caminoASeguir.remove(0);
            }
            x += dx;
            y += dy;
        } else {
            buscar = true;
            caminoASeguir.clear();
        }

    }

    public void moverHV() {

        if (buscar) {

            buscar = false;
            encontrado = false;
            // Establece un objetivo fijo hasta que sea encontrado
            Casilla objetivoASeguir = objetivo;
            // Determina la posición inicial de la que partirá el PNJ para la búsqueda.
            Casilla actualPNJ = posicion;

            caminoASeguir.add(actualPNJ);

            int distYobjetivo, distXobjetivo;
            // Mientras que el camino sea menor que 6 o estemos en la casilla del objetivo.
            while (!encontrado) {

                // Posición actual en la que se encuentra dentro del Arraylist.
                distYobjetivo = objetivoASeguir.getY() - actualPNJ.getY();
                distXobjetivo = objetivoASeguir.getX() - actualPNJ.getX();

                // Comenzamos la búsqueda del camino.
                if (caminoASeguir.size() == 6 || objetivoASeguir.equals(actualPNJ)) {

                    encontrado = true;
                    caminoASeguir.remove(0);

                } else if (distXobjetivo < 0) {
                    if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                        caminoASeguir.add(actualPNJ.getIzq());
                        actualPNJ = actualPNJ.getIzq();
                    } else if (distYobjetivo < 0) {
                        if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        }
                    } else if (distYobjetivo > 0) {
                        if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        }
                    } else {
                        if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        } else if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    }
                } else if (distXobjetivo > 0) {
                    if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                        caminoASeguir.add(actualPNJ.getDcha());
                        actualPNJ = actualPNJ.getDcha();
                    } else if (distYobjetivo < 0) {
                        if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        }
                    } else if (distYobjetivo > 0) {
                        if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    } else {
                        if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        } else if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    }
                } else {
                    if (distYobjetivo < 0) {
                        if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        }
                    } else if (distYobjetivo > 0) {
                        if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        } else if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    } else {
                        if (!actualPNJ.getIzq().isPared() && !caminoASeguir.contains(actualPNJ.getIzq())) {
                            caminoASeguir.add(actualPNJ.getIzq());
                            actualPNJ = actualPNJ.getIzq();
                        } else if (!actualPNJ.getDcha().isPared() && !caminoASeguir.contains(actualPNJ.getDcha())) {
                            caminoASeguir.add(actualPNJ.getDcha());
                            actualPNJ = actualPNJ.getDcha();
                        } else if (!actualPNJ.getAb().isPared() && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                            caminoASeguir.add(actualPNJ.getAb());
                            actualPNJ = actualPNJ.getAb();
                        } else if (!actualPNJ.getAr().isPared() && !caminoASeguir.contains(actualPNJ.getAr())) {
                            caminoASeguir.add(actualPNJ.getAr());
                            actualPNJ = actualPNJ.getAr();
                        }
                    }
                }

            }

        } else if (caminoASeguir.size() != 0) {
            int centroYProxCasilla = caminoASeguir.get(0).getY() + 10;
            int centroXProxCasilla = caminoASeguir.get(0).getX() + 10;
            int centroYPNJ = this.getY() + 12;
            int centroXPNJ = this.getX() + 12;
            int distYSigPosicion = centroYProxCasilla - centroYPNJ;
            int distXSigPosicion = centroXProxCasilla - centroXPNJ;


            if (distXSigPosicion < 0) {
                dy = 0;
                dx = -1;
            } else if (distXSigPosicion > 0) {
                dy = 0;
                dx = 1;
            } else if (distYSigPosicion < 0) {
                dx = 0;
                dy = -1;
            } else if (distYSigPosicion > 0) {
                dx = 0;
                dy = 1;
            } else {
                caminoASeguir.remove(0);
            }
            x += dx;
            y += dy;
        } else {
            buscar = true;
            caminoASeguir.clear();
        }
    }

    public void moverRdm() {

        if (buscar) {

            buscar = false;
            encontrado = false;
            // Establece un objetivo fijo hasta que sea encontrado
            Casilla objetivoASeguir = objetivo;
            // Determina la posición inicial de la que partirá el PNJ para la búsqueda.
            Casilla actualPNJ = posicion;

            caminoASeguir.add(actualPNJ);

            Random generarRdm = new Random();


            // Mientras que el camino sea menor que 10 o estemos en la casilla del objetivo.
            while (!encontrado) {

                int numero = (generarRdm.nextInt(4) + 1) % 4;
                // Comenzamos la búsqueda del camino.
                if (caminoASeguir.size() == 6 || objetivoASeguir.equals(actualPNJ)) {

                    encontrado = true;
                    caminoASeguir.remove(0);

                } else if (numero == 1 && !actualPNJ.getIzq().isPared() && actualPNJ.getIzq() != null && !caminoASeguir.contains(actualPNJ.getIzq())) {
                    caminoASeguir.add(actualPNJ.getIzq());
                    actualPNJ = actualPNJ.getIzq();
                } else if (numero == 2 && !actualPNJ.getDcha().isPared() && actualPNJ.getDcha() != null && !caminoASeguir.contains(actualPNJ.getDcha())) {
                    caminoASeguir.add(actualPNJ.getDcha());
                    actualPNJ = actualPNJ.getDcha();
                } else if (numero == 3 && !actualPNJ.getAb().isPared() && actualPNJ.getAb() != null && !actualPNJ.getAb().isParedFantasma() && !caminoASeguir.contains(actualPNJ.getAb())) {
                    caminoASeguir.add(actualPNJ.getAb());
                    actualPNJ = actualPNJ.getAb();
                } else if (numero == 4 && !actualPNJ.getAr().isPared() && actualPNJ.getIzq() != null && !caminoASeguir.contains(actualPNJ.getAr())) {
                    caminoASeguir.add(actualPNJ.getAr());
                    actualPNJ = actualPNJ.getAr();
                }
            }
        } else if (caminoASeguir.size() != 0) {
            int centroYProxCasilla = caminoASeguir.get(0).getY() + 10;
            int centroXProxCasilla = caminoASeguir.get(0).getX() + 10;
            int centroYPNJ = this.getY() + 12;
            int centroXPNJ = this.getX() + 12;
            int distYSigPosicion = centroYProxCasilla - centroYPNJ;
            int distXSigPosicion = centroXProxCasilla - centroXPNJ;


            if (distYSigPosicion < 0) {
                dx = 0;
                dy = -1;
            } else if (distYSigPosicion > 0) {
                dx = 0;
                dy = 1;
            } else if (distXSigPosicion < 0) {
                dy = 0;
                dx = -1;
            } else if (distXSigPosicion > 0) {
                dy = 0;
                dx = 1;
            } else {
                caminoASeguir.remove(0);
            }
            x += dx;
            y += dy;
        } else {
            buscar = true;
            caminoASeguir.clear();
        }

    }
}





