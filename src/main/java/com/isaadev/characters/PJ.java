package com.isaadev.characters;

import com.isaadev.board.Casilla;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;


public class PJ extends Personaje {

   public PJ(String directorioImagen, Casilla posicion){
       ImageIcon ii = new ImageIcon(this.getClass().getResource(directorioImagen));
       image = ii.getImage();
       this.posicion = posicion;
       this.x = posicion.getX();
       this.y = posicion.getY();
    }

    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT) {
               dx = -2;
        }
        if (tecla == KeyEvent.VK_RIGHT) {
               dx = 2;
        }
        if (tecla == KeyEvent.VK_UP) {
               dy = -2;
        }
        if (tecla == KeyEvent.VK_DOWN) {
               dy = 2;
        }

    }

    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (tecla == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (tecla == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (tecla == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

}
