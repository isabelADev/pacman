package com.isaadev.board;

import java.awt.Rectangle;


public class Casilla {

    private boolean pared = false;
    private boolean punto = false;
    private boolean puntoGordo = false;
    private boolean paredFantasma = false;

    // Variables que guardan las casillas cercanas;

    private Casilla casillaAr;
    private Casilla casillaAb;
    private Casilla casillaIzq;
    private Casilla casillaDcha;

    // Posiciones de comienzo de la casilla
    private int x;
    private int y;
    private int width = 15;
    private int height = 15;
    
    public Casilla(){
        }
    
     /** 
     * Devuelve la casilla situada Arriba.
     */
    public Casilla getAr(){
        return casillaAr;
    }

     /**
     * Devuelve la casilla situada Abajo.
     */
    public Casilla getAb(){
        return casillaAb;
    }
     /**
     * Devuelve la casilla situada a la Izquierda.
     */
    public Casilla getIzq(){
        return casillaIzq;
    }

     /**
     * Devuelve la casilla situada a la derecha.
     */
    public Casilla getDcha(){
        return casillaDcha;
    }

    /**
     * Asocia una casilla como casilla de arriba.
     *
     * @casillaAr casilla que se encuentra a la arriba
     */
    public void setAr(Casilla casillaAr){
        this.casillaAr = casillaAr;
    }

    /**
     * Asocia una casilla como casilla de abajo.
     *
     * @casillaAb casilla que se encuentra abajo
     */
    public void setAb(Casilla casillaAb){
        this.casillaAb = casillaAb;
    }

    /**
     * Asocia una casilla como casilla de izquierda.
     *
     * @casillaIzq casilla que se encuentra a la izquierda
     */
    public void setIzq(Casilla casillaIzq){
        this.casillaIzq = casillaIzq;
    }
    
    /** 
     * Asocia una casilla como casilla de arriba.
     *
     * @casillaDcha casilla que se encuentra a la derecha
     */
    public void setDcha(Casilla casillaDcha){
        this.casillaDcha = casillaDcha;
    }
    
    /** 
     * Comprueba si la casilla es una pared
     * @return 1 si es Pared 0 para cualquier otra cosa.
     */
    public boolean isPared() {
        return pared;
    }
    
    /** 
     * Establece si la casilla es una pared o no.
     * @pared 1 si es una pared 0 para cualquier otra cosa.
     */
    public void setPared(boolean pared) {
        this.pared = pared;
    }
    /** 
     * Comprueba si la casilla es una pared de la casa de los fantasmas
     * @return 1 si es paredFantasma 0 para cualquier otra cosa.
     */
    public boolean isParedFantasma(){
        return paredFantasma;
    }
    
    /** 
     * Comprueba si la casilla es una pared fantasma que sólo puede ser atravesada hacia arriba por un fantasma, el resto de intentos de atravesarla serán rechazados.
     * @return 1 si es ParedFantasma 0 para cualquier otra cosa.
     */
    public void setParedFantasma(boolean paredFantasma){
        this.paredFantasma = paredFantasma;
    }
    
    /** 
     * Comprueba si la casilla es un punto
     * @return 1 si es punto 0 para cualquier otra cosa.
     */
    public boolean isPunto() {
        return punto;
    }
    
        /** 
     * Establece si la casilla contiene o no un Punto Gordo
     * @puntoGordo 1 si es Punto Gordo 0 para cualquier otra cosa.
     */
    public void setPunto(boolean punto) {
        this.punto = punto;
    }
    
    /** 
     * Comprueba si la casilla es un puntoGordo
     * @return 1 si es Punto Gordo 0 para cualquier otra cosa.
     */
    public boolean isPuntoGordo(){
        return puntoGordo;
    }
    
    /** 
     * Establece si la casilla contiene o no un Punto Gordo
     * @puntoGordo 1 si es Punto Gordo 0 para cualquier otra cosa.
     */
    public void setPuntoGordo(boolean puntoGordo) {
        this.puntoGordo = puntoGordo;
    }
    
    /**
     * Comprueba la posición de X donde comienza la casilla teniendo en cuenta que son casillas de 20 px X 20 px
     */
    public int getX() {
        return x;
    }

    /**
     * Comprueba la posición de Y donde comienza la casilla teniendo en cuenta que son casillas de 20 px X 20 px
     */
    public int getY() {
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public Rectangle getLimites(){
        return new Rectangle(x, y, width, height);
    }
    
}
