/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.Calendar;

/**
 * La clase Hora permite generar objetos o instancias a partir de los datos, 
 * hora, minuto y segundo 
 * @author Yohana Avila
 */
public class Hora {
    /**
     * Corresponde al día en formato entero o int
     */
    private int hh,
    
    /**
     * Corresponde al día en formato entero o int
     */
    mn,
   
    /**
     * Corresponde al día en formato entero o int
     */
    sg;

    /**
     * Construnctor paramétrico que permite crear una instancia de tipo Hora 
     * a partir de los datos recibidos como parámetros
     * @param hh Hora
     * @param mn Minuto
     * @param sg Segundo
     */
    public Hora(int hh, int mn, int sg) {
        this.hh = hh;
        this.mn = mn;
        this.sg = sg;
    }

    /**
     *
     */
    public Hora() {
        // Iniciar la hora con la hora del sistema
        Calendar FSystem = Calendar.getInstance();
        this.hh = FSystem.get(Calendar.HOUR_OF_DAY);
        this.mn = FSystem.get(Calendar.MINUTE);
        this.sg = FSystem.get(Calendar.SECOND);
    }
    
    @Override
    public String toString() {
        return hh + ":" + mn + ":" + sg;
    } 

    
    /**
     * Método que valida los segundos en rango 0-59
     */
    public void incSS(){
        if(sg<59)
            sg++;
        else{
            sg=0;
        }
    }

    /**
     * Método que valida los minutos en rango 0-59
     */
    public void incMN(){ 
        if(sg == 0){
            mn++;
        } else if(mn > 59){
            mn = 0;
        }
    }

    /**
     * Método que valida las horas en rango 0-24
     */
    public void incHH(){
        if(mn == 0){
            hh++;
        } else if(hh >= 23){
            hh = 0;
        }
    }

    /**
     * Método que retorna el contenido del atributo Hora en formato entero o int
     * @return int
     */
    public int getHh() {
        return hh;
    }

    /**
     * Método que modifica el contenido del atributo Hora a partir de el valor 
     * recibido en formato entero o int
     * @param hh Hora
     */
    public void setHh(int hh) {
        this.hh = hh;
    }

    /**
     * Método que retorna el contenido del atributo Minuto en formato entero o int
     * @return int
     */
    public int getMn() {
        return mn;
    }

    /**
     * Método que modifica el contenido del atributo Minuto a partir de el valor 
     * recibido en formato entero o int
     * @param mn Minuto
     */
    public void setMn(int mn) {
        this.mn = mn;
    }

    /**
     * Método que retorna el contenido del atributo Segundo en formato entero o int
     * @return int
     */
    public int getSg() {
        return sg;
    }

    /**
     * Método que modifica el contenido del atributo Segundo a partir de el valor 
     * recibido en formato entero o int
     * @param sg Segundo
     */
    public void setSg(int sg) {
        this.sg = sg;
    }    
}
