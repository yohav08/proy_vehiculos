/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase Vehiuclo permite generar objetos o instancias a partir de los datos 
 * placa, marca, modelo y valor del vehiculo, datos que serán heredados a sus 
 * clases hijas Auto y Moto 
 * @author Yohana Avila
 */
public abstract class Vehiculo {
    // Clase super o padre

    /**
     * Corresponde a la placa del vehículo
     */
    protected String placa,

    /**
     *Corresponde a la marca del vehículo
     */
    marca;

    /**
     * Corresponde al modelo del vehículo
     */
    protected int modelo;

    /**
     * Corresponde al valor del vehículo
     */
    protected double valor;
    
    /**
     * Construnctor paramétrico que permite crear una instancia de tipo VEhículo,
     * ya sea Moto o Auto a partir de los datos recibidos como parámetros
     * @param placa Placa del vehículo
     * @param marca Marca del vehículo
     * @param modelo Modelo del vehículo
     * @param valor Valor del vehículo
     */
    public Vehiculo(String placa, String marca, int modelo, double valor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
    }

    /**
     * Constructor básico que permite crear una instancia de tipo Vehículo de tipo 
     * Auto o Moto a partir de datos base o datos nulos
     */
    public Vehiculo() {
        this.placa = "";
        this.marca = "";
        this.modelo = 0;
        this.valor = 0;
    }

    /**
     * Método que retorna el contenido del atributo Placa del vehículo en 
     * formato texto o String
     * @return String
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Método que modifica el contenido del atributo Placa del vehículo a 
     * partir de el valor recibido en formato  texto o String
     * @param placa Placa del vehículo
     * @throws modelo.Exceptions 
     */
    public void setPlaca(String placa) throws Exceptions {
        Pattern pat = Pattern.compile("\\p{Punct}");
        Matcher mat = pat.matcher(placa);
        
        if (placa.equals("")) { //Para campos nulos
            throw new Exceptions(101,"Placa ");
        }else{
            this.placa = placa;
        }
        
    }

    /**
     * Método que retorna el contenido del atributo Marca del vehículo en 
     * formato texto o String
     * @return String
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Método que modifica el contenido del atributo Marca del vehículo a 
     * partir de el valor recibido en formato  texto o String
     * @param marca Marca del vehículo
     * @throws modelo.Exceptions
     */
    public void setMarca(String marca) throws Exceptions {
        Pattern pat = Pattern.compile("[0-9]");
        Pattern pat2 = Pattern.compile("\\p{Punct}");
        Matcher mat = pat.matcher(marca);
        Matcher mat2 = pat2.matcher(marca);
        
        if (marca.equals("")) { //Para campos nulos
            throw new Exceptions(101,"Marca ");
        }else if(mat.find()){ //Para evitar números
            throw new Exceptions(102,"Marca ");
        }else if(mat2.find()){ //Para evitar puntuación
            throw new Exceptions(105,"Marca ");
        }else{
            this.marca = marca;
        }
    }
    
    /**
     * Método que retorna el contenido del atributo Placa del vehículo en 
     * formato entero o int
     * @return int
     */
    public int getModelo() {
        return modelo;
    }

    /**
     * Método que modifica el contenido del atributo Modelo del vehículo a 
     * partir de el valor recibido en formato  entero o int
     * @param modelo Modelo (Año de modelo) del vehículo, ya sea auto o moto
     */
    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    /**
     * Método que retorna el contenido del atributo Valor del vehículo en 
     * formato decimal o double
     * @return double
     */
    public double getValor() {
        return valor;
    }

    /**
     * Método que modifica el contenido del atributo Valor del vehículo a 
     * partir de el valor recibido en formato decimal o double
     * @param valor Valor del vehículo
     * @throws modelo.Exceptions 101-Nulos, 104-Números negativos, 104-Puntuación
     */
    public void setValor(double valor) throws Exceptions {
        if(valor<0){ //Para evitar números negativos
            throw new Exceptions(104,"Valor ");
        }else if (Double.toString(valor).equals("")) {//Para campos nulos
            throw new Exceptions(101,"Valor ");
        } else{ 
            this.valor = valor;
        }
    }
    
    /**
     * Método abstracto heredado a las clases hijas Auto y Moto, para ser 
     * utilizado de acuerdo a las necesidades
     * @return double
     */
    public abstract double iva(); //método abstracto

    @Override 
    public String toString(){
        return  "\nPlaca: " + placa + 
                "\nMarca: " + marca + 
                "\nModelo: " + modelo + 
                "\nValor: " + String.format("%.0f",valor);
    }   
}
