/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.ArrayList;

/**
 * La clase Recaudo permite generar objetos o instancias a partir de un ArrayList
 * que contendrá en cada posición un Objeto o instancia de tipo Formulario
 * @author Yohana Avila
 */
public class Recaudo {
    
    /**
     * Corresponde a la lista de Formularios en formato ArrayList
     */
    private ArrayList<Formulario> listaF;

    /**
     * Construnctor paramétrico que permite crear una instancia de tipo Recaudo 
     * a partir de la lista recibida como parámetro en formato ArrayList
     * @param listaF Lista de recaudo
     */
    public Recaudo(ArrayList<Formulario> listaF) {
        this.listaF = listaF;
    }

    /**
     * Constructor básico que permite crear una instancia de tipo Recaudo a 
     * partir de datos base extraídos de la clase Formulario
     */
    public Recaudo() {
        this.listaF = new ArrayList<Formulario>();
    }
   
    @Override
    public String toString() {
        String form="", tipoV;
        for (int i = 0; i < listaF.size(); i++) {
            if(listaF.get(i).getObjV() instanceof Auto){  //Polimorfismo
                tipoV="\n\nFORMULARIO AUTO"; 
            }else{
                tipoV="\n\nFORMULARIO MOTO";
            }
            form+= tipoV + listaF.get(i).toString()+"\n"+"Valor IVA: "+listaF.get(i).valorPago();
        }
        return "Lista Recaudo " + form;
    }
    
    /**
     * Método que retorna el contenido de la Lista de Formularios en formato ArrarList
     * @return ArrayList<> Lista de Recaudo
     */
    public ArrayList<Formulario> getListaF() {
        return listaF;
    }

    /**
     * Método que modifica el contenido de la Lista de Formularios a partir del valor 
     * recibido en formato ArrayList
     * @param listaF Lista de Recaudo
     */
    public void setListaF(ArrayList<Formulario> listaF) {
        this.listaF = listaF;
    }
    
    /**
     * Método que calcula el total Recaudado de todos los elementos o Formularios 
     * de la lista double
     * @return double
     */
    public double totalRecaudo() {
        double form=0;
        for (int i = 0; i < listaF.size(); i++) {
            
            form+= listaF.get(i).valorPago();
        }
        return form;
    }
    
    /**
     * Método que calcula la cantidad de instancias de tipo Auto que contiene la lista
     * @return int
     */
    public int cantAutos() {
        int form=0;
        for (int i = 0; i < listaF.size(); i++) {
            if(listaF.get(i).getObjV() instanceof Auto){
                form++;
            }
        }
        return form;
    }
    
    /**
     * Método que calcula la cantidad de instancias de tipo Moto que contiene la lista
     * @return int
     */
    public int cantMoto() {
        int form=0;
        for (int i = 0; i < listaF.size(); i++) {
            if(listaF.get(i).getObjV() instanceof Moto){
                form++;
            }
        }
        return form;
    }
}
