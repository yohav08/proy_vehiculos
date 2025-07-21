/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author Yohana Avila  y Santiago Rios
 */
public class ImagenFondo extends JPanel{
    
    /**
     * Corresponde a la Imagen que será Fondo
     */
    private Image imagen;

    /**
     * Constructor paramétrico que permite crear una instancia de tipo ImagenFondo
     * a partir de la Imagen recibida como parámetro
     * @param imagen Imagen de Fondo
     */
    public ImagenFondo(Image imagen) {
        this.imagen = imagen;
    }
    
    /**
     * Constructor básico que permite crear una instancia de tipo ImagenFondo a 
     * partir de una Imagen base
     */
    public ImagenFondo() {
        this.imagen = new ImageIcon(getClass().getResource("/img/car_minimalist.png")).getImage();
    }
    
    /**
     * En el método paint() se está obteniendo el objeto Graphics sobre el cual se 
     * puede utilizar el método .drawImage() permitiendo graficar la imagen dentro 
     * del contenedor. 
     * Adicionalmente se ejecutan los métodos setOpaque() con valor falso, para que 
     * la imagen sea visible dentro del contenedor y finalmente se ejecuta la acción 
     * Paint para que se grafique la imagen en el contenedor.
     * @author Sonia Pinzón
     * @param g Graphics - Graficar la imagen
     */
    @Override
    public void paint(Graphics g) {
        Dimension tamFrm = this.getSize();
        g.drawImage(imagen, 0, 0, tamFrm.width, tamFrm.height, null);
        setOpaque (false);
        super.paint (g);
    }

    /**
     * Método que retorna el contenido del atributo Imagen en formato Image
     * @return Image
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Método que modifica el contenido del atributo Imagen a partir de el valor 
     * recibido en formato Image
     * @param imagen Imagen de Fondo
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}
