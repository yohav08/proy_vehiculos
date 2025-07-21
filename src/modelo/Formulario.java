/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */ 

package modelo;

/**
 * La clase Formulario permite generar objetos o instancias a partir de los datos, 
 * Número de Formulario, Fecha de registro, Datos del propietario y Datos del 
 * vehiculo
 * @author Yohana Avila
 */
public class Formulario {
    /**
     * Corresponde al número del Formulario en formato texto o String
     */
    private String nroF;
    
    /**
     * Corresponde a la fecha de registro del Formulario en formato o instancia 
     * tipo Fecha
     */
    private Fecha f_registro;
    
    /**
     * Corresponde a los datos del propietario que está en el Formulario en formato 
     * o instancia tipo Persona
     */private Persona objP;
    
     /**
     * Corresponde a los datos del vehículo qe está en el Formulario en formato 
     * o instancia tipo Vehiculo
     */private Vehiculo objV;

    /**
     * Constructor paramétrico que permite crear una instancia de tipo Formulario 
     * a partir de los datos recibidos como parámetros
     * @param nroF Número de Formulario
     * @param f_registr Fecha de registro del Formulario
     * @param propietario Datos del propietario en formato o instancia tipo Persona 
     * @param objV Datos del vehiculo en formato o instancia tipo Vehiculo 
     */
    public Formulario(String nroF, Fecha f_registr, Persona propietario, Vehiculo objV) {
        this.nroF = nroF;
        this.f_registro = f_registr;
        this.objP = propietario;
        this.objV = objV;
    }
    
    /**
     * Constructor básico que permite crear una instancia de tipo Formulario a 
     * partir de datos base o datos nulos
     */
    public Formulario() {
        int cod=(int) (Math.random()*999+100);
        this.nroF = "FRM-"+cod;
        this.f_registro = new Fecha();
        this.objP = new Persona();
        this.objV = null; //Plantilla es abstracta
    }
    
    /**
     * Método que retorna el valor total a pagar de acuerdo al cálculo que realiza 
     * el método abstacto "iva" que calcula el valor del IVA dependiendo del modelo
     * ya sea de Auto o Moto
     * @return double
     */
    public double valorPago(){
        return objV.iva();
    }
     
    /**
     * Método que retorna el contenido del atributo número de Formulario en 
     * formato texto o String
     * @return String
     */
    public String getNroF() {
        return nroF;
    }

    /**
     * Método que modifica el contenido del atributo numero de formulario a 
     * partir de el valor recibido en formato texto o String
     * @param nroF Número del Formulario
     */
    public void setNroF(String nroF) {
        this.nroF = nroF;
    }

    /**
     * Método que retorna el contenido del atributo Fecha de registro del Formulario en 
     * formato o instancia de tipo Fecha
     * @return Fecha
     */
    public Fecha getF_registr() {
        return f_registro;
    }

    /**
     * Método que modifica el contenido del atributo fecha de registro del formulario a 
     * partir de el valor recibido en formato o instancia de tipo Fecha
     * @param f_registr Fecha de registro del Formulario
     */
    public void setF_registr(Fecha f_registr) {
        this.f_registro = f_registr;
    }

    /**
     * Método que retorna el contenido del atributo Persona que contiene los datos del 
     * propietario en el Formulario en formato o instancia de tipo Persona
     * @return Persona
     */
    public Persona getObjP() {
        return objP;
    }

    /**
     * Método que modifica el contenido del atributo Persona que contiene los datos del 
     * propietario en el Formulario en formato o instancia de tipo Persona
     * @param objP Persona que contiene los datos del propietario en el Formulario
     */
    public void setObjP(Persona objP) {
        this.objP = objP;
    }

    /**
     * Método que retorna el contenido del atributo Vehiculo que contiene los datos del 
     * vehiculo en el Formulario en formato o instancia de tipo Vehiculo
     * @return Vehiculo
     */
    public Vehiculo getObjV() {
        return objV;
    }

    /**
     * Método que modifica el contenido del atributo Vehiculo que contiene los datos del 
     * vehiculo en el Formulario en formato o instancia de tipo Persona
     * @param objV  Vehiculo que contiene los datos del vehiculo en el Formulario
     */ 
    public void setObjV(Vehiculo objV) {
        this.objV = objV;
    }

    @Override
    public String toString() {
        return "\nFormulario Impuesto \nNúmero Formulario: " + nroF + 
                "\nFecha de registro: " + f_registro.toString() + 
                "\n***Propietario*** " + objP.toString() + 
                "\n***Vehículo***" + objV.toString();
    }
    
    /**
     * Método que retorna un String con todos los datos concatenados por ";" para 
     * ser enviado al archivo txt en una sola línea 
     * @param cil Parámetro recibido para realizar la comprobación del tipo de vehículo
     * @return String
     */
    public String datosA(double cil) {
        // Orden de los datos
        // #Formulario, F_registro
        // id, nombre, telefono, email, f_nacimiento
        // Placa, marca, modelo, valor, cilindraje
        
        //el cilindraje si es 0 significa que es un auto, si es diferente de 0 será una Moto
        double cilindraje = -1;
        if (cil!=0) {
            cilindraje = cil;
        }
        
        String datos = nroF + ";" + f_registro.toString() + ";" + 
                       objP.getId() + ";" + objP.getNombre() + ";" + 
                       objP.getTelefono() + ";" + objP.getEmail()+ ";" +
                       objP.getfNac()+ ";" + objV.getPlaca()+ ";" +
                       objV.getMarca()+ ";" +objV.getModelo()+ ";" +
                       objV.getValor()+ ";" + cilindraje;
        return datos;
    }   
}