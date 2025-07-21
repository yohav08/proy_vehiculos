/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proy_vehiculos;

import control.*;

/**
 * La clase Proy_vehiculos permite Ejecutar el programa/proyecto a partir del 
 * llamado a método iniciar del Controlador 
 * @author Yohana Avila
 */
public class Proy_vehiculos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controlador_Persist objC = new Controlador_Persist();
        objC.iniciar();
    }    
}
