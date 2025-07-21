/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

/**
 * La clase Exceptions permite generar objetos o instancias a partir de los datos
 * número de excepción y mensaje/excepción
 * @author Yohana Avila
 */
public class Exceptions extends Exception {

    /**
     * Corresponde al número de la Excepción 
     */
    public int Nro;

    /**
     * Corresponde al mensaje que se retorna, o la excepción
     */
    public String msj;

    /**
     * Constructor paramétrico que permite crear una instancia de tipo Exceptions
     * a partir de los datos recibidos como parámetros
     * @param Nro Número de la excepción
     * @param dato Hace referencia al dato mandado y al cual pertenecerá la excepción
     */
    public Exceptions(int Nro, String dato) {
        this.msj = "";
        this.Nro = Nro;
        switch (Nro){
            
            case 101: // Para errores de valores ingresados 
                msj ="No se admiten valores nulos...";
            break;
            case 102: // Para errores de valores ingresados 
                msj ="No se admiten valores numéricos...";
            break;
            case 103: // Para errores de valores ingresados 
                msj ="No se admiten caracteres...";
            break;
            case 104: // Para errores de valores ingresados 
                msj ="No se admiten valores negativos o el caracter '-'...";
            break;
            case 105: // Para errores de valores ingresados 
                msj ="No se admite puntuación...";
            break;
            case 106: // Para errores de valores ingresados en el correo
                msj ="Formato de correo electrónico inválido...";
            break;
            default:
                msj = "Error no identificado...";
            break;
        }
        msj+=" Generado por: "+dato;
    }
    
    @Override
    public String toString() {
        return "Excepción Nro " + Nro + ": Error: " + msj;
    }

    /**
     * Método que retorna el contenido del atributo número de la excepción en 
     * formato entero o int
     * @return int
     */
    public int getNro() {
        return Nro;
    }

    /**
     * Método que modifica el contenido del atributo número de la excepción a 
     * partir de el valor recibido en formato entero o int
     * @param Nro Número de la Excepción
     */
    public void setNro(int Nro) {
        this.Nro = Nro;
    }

    /**
     * Método que retorna el contenido del mensaje de la excepción en 
     * formato texto o String
     * @return String
     */
    public String getMsj() {
        return msj;
    }

    /**
     * Método que modifica el contenido del mensaje de la excepción a 
     * partir de el valor recibido en formato texto o String
     * @param msj Mensaje de la excepción que se genere
     */
    public void setMsj(String msj) {
        this.msj = msj;
    }
}
