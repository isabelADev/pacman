package com.isaadev.characters;
/**
 * Es una clase abstracta de personaje que son los que recorrerán el tablero
 *
 * @M Isabel Almellones Cabello
 * @v1.0
 */

import com.isaadev.board.Casilla;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

import static java.util.Objects.requireNonNull;

public abstract class Personaje {

    int x, y, dx, dy;
    Image image;
    private int width = 20;
    private int height = 20;
    Casilla posicion;

    Personaje() {
    }

    public void setImage(String nuevaImagen) {
        ImageIcon ii = new ImageIcon(requireNonNull(this.getClass().getResource(nuevaImagen)));
        this.image = ii.getImage();
    }

    public void mover() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int valorX) {
        x = valorX;
    }

    public void setY(int valorY) {
        y = valorY;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDX() {
        return dx;
    }

    public void setDX(int valorDX) {
        dx = valorDX;
    }

    public int getDY() {
        return dy;
    }

    public void setDY(int valorDY) {
        dy = valorDY;
    }

    public Rectangle getLimites() {
        return new Rectangle(x + dx + 4, y + dy + 4, width - 8, height - 8);
    }
}
