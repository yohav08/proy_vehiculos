/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase Persona permite generar objetos o instancias a partir de los datos
 * id, nombre, teléfono y correo electronico de la persona
 * @author Yohana Avila
 */
public class Persona {
    /**
     * Corresponde a la identificación de la persona - propietario
     */
    private String id, 
    
    /**
     * Corresponde al nombre de la persona - propietario
     */
    nombre,
            
    /**
     * Corresponde al telefono de la persona - propietario
     */        
    telefono,
            
    /**
     * Corresponde al correo electrónico de la persona - propietario
     */          
    email;
    
    /**
     * Corresponde a la Fecha de nacimiento de la persona - propietario
     */
    private Fecha fNac;

    /**
     * Constructor paramétrico que permite crear una instancia de tipo Persona 
     * a partir de los datos recibidos como parámetros
     * @param id Número de identificación de la persona
     * @param nombre Nombre de la persona
     * @param telefono Telefono de la persona
     * @param email Correo electrónico de la persona
     * @param fNac Fecha de nacimiento de la persona
     */
    public Persona(String id, String nombre, String telefono, String email, Fecha fNac) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.fNac = fNac;
    }

    /**
     * Constructor básico que permite crear una instancia de tipo Persona a 
     * partir de datos base o datos nulos
     */
    public Persona() {
        this.id = "";
        this.nombre = "";
        this.telefono = "";
        this.email = "";
        this.fNac = new Fecha(); // Agregación/Composición
    }
    
    @Override
    public String toString(){
        return  "\nID: " + id + 
                "\nNombre: " + nombre + 
                "\nTeléfono: " + telefono +
                "\nEmail: " + email +
                "\nFecha Nacimiento: " + fNac.toString();
    } 

    /**
     * Método que retorna el contenido del atributo Identificación de la Persona en 
     * formato texto o String
     * @return String 
     */
    public String getId() {
        return id;
    }

    /**
     * Método que modifica el contenido del atributo Identificación de la Persona a 
     * partir de el valor recibido en formato  texto o String
     * @param id Número de Identificación de la persona
     * @throws Exceptions 101-Nulos, 103-Letras/caracteres, 105-Puntuación
     */
    public void setId(String id) throws Exceptions {
        Pattern pat = Pattern.compile("[a-zA-Z]");
        Pattern pat2 = Pattern.compile("\\p{Punct}"); 
        Matcher mat = pat.matcher(id);
        Matcher mat2 = pat2.matcher(id);
        
        if (id.equals("")) { //Para campos nulos
            throw new Exceptions(101,"Identificación ");
        }else if(mat.find()){ //Para evitar letras
            throw new Exceptions(103,"Identificación ");
        }else if(mat2.find()){ //Para evitar puntuación (incluye el - para evitar negativos)
            throw new Exceptions(105,"Identificación ");
        }else{
            this.id = id;
        }
    }

    /**
     * Método que retorna el contenido del atributo Nombre de la Persona en 
     * formato texto o String
     * @return String 
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Método que modifica el contenido del atributo Nombre de la Persona a 
     * partir de el valor recibido en formato  texto o String
     * @param nombre Nombre de la persona
     * @throws Exceptions 101-Nulos, 102-Números, 105-Puntuación
     */
    public void setNombre(String nombre) throws Exceptions {
        Pattern pat = Pattern.compile("[0-9]");
        Pattern pat2 = Pattern.compile("\\p{Punct}"); 
        Matcher mat = pat.matcher(nombre);
        Matcher mat2 = pat2.matcher(nombre);
        
        if (nombre.equals("")) { //Para campos nulos
            throw new Exceptions(101,"Nombre ");
        }else if(mat.find()){ //Para evitar valores numéricos
            throw new Exceptions(102,"Nombre ");
        }else if(mat2.find()){ //Para evitar puntuación
            throw new Exceptions(105,"Nombre ");
        }else{
            this.nombre = nombre;
        }
    }

    /**
     * Método que retorna el contenido del atributo Teléfono de la Persona en 
     * formato texto o String
     * @return String 
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que modifica el contenido del atributo Teléfono de la Persona a 
     * partir de el valor recibido en formato  texto o String
     * @param telefono Número telefónico de la persona
     * @throws Exceptions 101-Nulos, 103-Letras/caracteres, 105-Puntuación
     */
    public void setTelefono(String telefono) throws Exceptions {
        Pattern pat = Pattern.compile("[a-zA-Z]");
        Pattern pat2 = Pattern.compile("\\p{Punct}"); 
        Matcher mat = pat.matcher(telefono);
        Matcher mat2 = pat2.matcher(telefono);
        
        if (telefono.equals("")) { //Para campos nulos
            throw new Exceptions(101,"Teléfono ");
        }else if(mat.find()){ //Para evitar letras
            throw new Exceptions(103,"Teléfono ");
        }else if(mat2.find()){ //Para evitar puntuación
            throw new Exceptions(105,"Teléfono ");
        }else{
            this.telefono = telefono;
        }
        
    }

    /**
     * Método que retorna el contenido del atributo Correo electrónico de la 
     * Persona en formato texto o String
     * @return String 
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que modifica el contenido del atributo Correo electrónico de la 
     * Persona a partir de el valor recibido en formato  texto o String
     * @param email Correo electrónico de la persona
     * @throws Exceptions 106-Validación @ de correo
     */
    public void setEmail(String email) throws Exceptions {
        Pattern pat = Pattern.compile("@");
        Matcher mat = pat.matcher(email); 
        
        if (email.equals("")) {  //Para campos nulos
            throw new Exceptions(101,"Email ");
        }else if(mat.find()==false){ //Para registrar unicamente correos con @
            throw new Exceptions(106,"Email ");
        }else{
            this.email = email;
        }
    }

    /**
     * Método que retorna el contenido del atributo Fecha de nacimiento de la 
     * Persona en formato o instancia de tipo Fecha
     * @return Fecha
     */
    public Fecha getfNac() {
        return fNac;
    }

    /**
     * Método que modifica el contenido del atributo fecha de nacimiento de la
     * Persona a partir de el valor recibido en formato o instancia de tipo Fecha
     * @param fNac Fecha de nacimiento de la Persona o propietario
     * @throws Exceptions ..
     */
    public void setfNac(Fecha fNac) throws Exceptions{
        this.fNac = fNac;
    }
}
