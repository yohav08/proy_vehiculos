/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.Calendar;

/**
 * La clase Fecha permite generar objetos o instancias a partir de los datos, 
 * día, mes y año
 * @author Yohana Avila Mendoza
 */


public class Fecha {
    /**
     * Corresponde al día en formato entero o int
     */
    private int dd,
            
    /**
     * Corresponde al mes en formato entero o int
     */       
    mm,
            
    /**
     * Corresponde al año en formato entero o int
     */      
    aa;
    

    /**
     * Construnctor paramétrico que permite crear una instancia de tipo Fecha 
     * a partir de los datos recibidos como parámetros
     * @param dd Día
     * @param mm Mes
     * @param aa Años
     */
    public Fecha(int dd, int mm, int aa) {
        this.dd = dd;
        this.mm = mm;
        this.aa = aa; 
    }

    /**
     * Constructor básico que permite crear una instancia de tipo Fecha a 
     * partir de datos base extraídos de la clase Calendar
     */
    public Fecha() {
        // Iniciar la fecha con la fecha del sistema
        Calendar FSystem = Calendar.getInstance();
        this.dd = FSystem.get(Calendar.DAY_OF_MONTH);
        this.mm = FSystem.get(Calendar.MONTH);
        this.aa = FSystem.get(Calendar.YEAR);
    }
       
    /*public void incDD(){ //método que valida los días
        if(objH.getHh()==0)
            dd++;
        else{
            dd=1;
        }
    }*/
    
    @Override
    public String toString() {
        return aa + "/" + mm + "/" + dd;
    }
    
    /**
     * Atributo que retorna la Fecha en formato aaaa-mm-dd para la insersión a la 
     * base de datos
     * @return String
     */
    public String Fecha_BD() {
        return aa + "-" + mm + "-" + dd;
    }

    /**
     * Método que retorna el contenido del atributo día en formato entero o int
     * @return int
     */
    public int getDd() {
        return dd;
    }

    /**
     * Método que modifica el contenido del atributo Día a partir de el valor 
     * recibido en formato entero o int
     * @param dd Día
     */
    public void setDd(int dd) {
        this.dd = dd;
    }

    /**
     * Método que retorna el contenido del atributo Mes en formato entero o int
     * @return int
     */
    public int getMm() {
        return mm;
    }

    /**
     * Método que modifica el contenido del atributo Mes a partir de el valor 
     * recibido en formato entero o int
     * @param mm Mes
     */
    public void setMm(int mm) {
        this.mm = mm;
    }

    /**
     * Método que retorna el contenido del atributo Año en formato entero o int
     * @return int
     */
    public int getAa() {
        return aa;
    }

    /**
     * Método que modifica el contenido del atributo Año a partir de el valor 
     * recibido en formato entero o int
     * @param aa Año
     */
    public void setAa(int aa) {
        this.aa = aa;
    }
}
