/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

/**
 * La clase Auto permite generar objetos o instancias a partir de los datos 
 * heredados de la clase padre Vehiculo (placa, marca, modelo, valor)
 * @author Yohana Avila
 */
public class Auto extends Vehiculo {

    /**
     * Construnctor paramétrico que permite crear una instancia de tipo Auto 
     * a partir del llamado al contructor paramétrico de la clase padre Vehiculo
     * @param placa Placa del Vehículo (atributo heredado de la clase Vehiculo)
     * @param marca Marca del Vehículo (atributo heredado de la clase Vehiculo)
     * @param modelo Modelo del Vehículo (atributo heredado de la clase Vehiculo)
     * @param valor Valor del Vehículo (atributo heredado de la clase Vehiculo)
     */
    public Auto(String placa, String marca, int modelo, double valor) {
        super(placa, marca, modelo, valor);
    }

    /**
     * Constructor básico que permite crear una instancia de tipo Auto a 
     * partir del llamado al contructor básico de la clase padre Vehiculo
     */
    public Auto() {
        super(); //Llamado a constructor básico de vehículo
    }
    
    /**
     * Método que calcula el valor del IVA dependiendo del modelo del Auto
     * Si es menor a 2000 calcula el IVA al 12%
     * Si es mayor a 2000 calcula el IVA al 12%
     * @return Valor del IVA de acuerdo al modelo
     */
    @Override
    public double iva() {
        if(modelo <2000){
            return this.valor*0.12;
        }else{
            return this.valor*0.19;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
}
