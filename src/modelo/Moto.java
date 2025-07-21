/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase Moto permite generar objetos o instancias a partir de los datos 
 * heredados de la clase padre Vehiculo (placa, marca, modelo, valor) y el 
 * cilindraje de la moto (atributo propio de la clase Moto)
 * @author Yohana Avila
 */
public class Moto extends Vehiculo{
    /**
     * Corresponde al cilindraje de la Moto
     */
    private double cilindraje;
    
    /**
     * Construnctor paramétrico que permite crear una instancia de tipo Auto 
     * a partir del llamado al contructor paramétrico de la clase padre Vehiculo
     * @param cilindraje Cilindraje de la Moto - atributo propio de la Clase 
     * @param placa Placa del Vehículo (atributo heredado de la clase Vehiculo)
     * @param marca Marca del Vehículo (atributo heredado de la clase Vehiculo)
     * @param modelo Modelo del Vehículo (atributo heredado de la clase Vehiculo)
     * @param valor Valor del Vehículo (atributo heredado de la clase Vehiculo)
     */
    public Moto(double cilindraje, String placa, String marca, int modelo, double valor) {
        super(placa, marca, modelo, valor);
        this.cilindraje = cilindraje;
    }
    
    /**
     * Constructor básico que permite crear una instancia de tipo Moto a 
     * partir de datos base o datos nulos heredados de la clase padre Vehiculo
     */
    public Moto() {
        super(); //Llamado a constructor básico de vehículo
    }

    /**
     * Método abstracto heredado de la clase padre vehiculo que se encarga de calcular 
     * el iva de la Moto dependiendo del clindraje de la Moto
     * @return double
     */
    @Override
    public double iva() {
        if(cilindraje >125){
            return this.valor*0.1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return super.toString()+ "\nCilindraje: " + String.format("%.0f",cilindraje);
    }
    
    /**
     * Método que retorna el contenido del atributo cilindraje de la Moto en 
     * formato decimal o double
     * @return double
     */
    public double getCilindraje() { 
        return cilindraje;
    }

    /**
     * Método que modifica el contenido del atributo cilindraje de la Moto a 
     * partir de el valor recibido en formato decimal o double
     * @param cilindraje  Cilindraje de la Moto
     * @throws Exceptions
     */
    public void setCilindraje(double cilindraje) throws Exceptions {
        if(cilindraje<0){ //Para evitar números negativos
            throw new Exceptions(104,"Valor ");
        }else if (Double.toString(cilindraje).equals("")) {//Para campos nulos
            throw new Exceptions(101,"Valor ");
        } else{
            this.cilindraje = cilindraje;
        }
            
    
        
    }
}
