/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author Yohana Avila y Santiago Rios
 */
public class Imagen_Escritorio implements Border{
    private Image imagen;
    public Imagen_Escritorio(Image imagen) {
        this.imagen = imagen;
    }
    public Imagen_Escritorio() {
        this.imagen = new ImageIcon(getClass().getResource("/img/minimalist.png")) .getImage();
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int ancho, int alto) {
        if (imagen.getWidth(null)<ancho || imagen.getHeight(null)<alto) {
            g.drawImage(imagen, 0, 0, ancho, alto, null);
        }else{
            g.drawImage(imagen, 0, 0, imagen.getWidth(null) - (imagen.getWidth(null) - ancho), 
                        imagen.getHeight(null) - (imagen.getHeight(null) - alto), null);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    public Image getImagen() {
        return imagen;
    }
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
}
