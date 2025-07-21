/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * La clase Conexion_SQL permite generar objetos o instancias a partir de datos dados
 * @author Sonia Pinzón - Documentada por Yohana Avila 
 */
public class Conexion_SQL {
  
    /**
     * Corresponde a la conexión con la base de datos
     */
    private Connection conexion;
  
    /**
     * Corresponde al nombre de la base de datos
     */
    private String bd,
    
    /**
     * Corresponde al usuario de la base de datos
     */        
    usuario, 
            
    /**
     * Corresponde a la clave de la base de datos
     */        
    clave,
            
    /**
     * Corresponde al mensaje que nos confirma si la conexión fue un éxito o hubo errores
     */        
    mensaje;

    /**
     * Construnctor paramétrico que permite crear una instancia de tipo Conexion_SQL 
     * a partir de los datos recibidos como parámetros
     * @param conexion Conexión a la base de datos
     * @param bd Nombre de la base de datos
     * @param usuario Usuario para el acceso a la base de datos
     * @param clave Clave de la base de datos si tiene 
     * @param mensaje Mensaje que nos confirma si la conexión fue un éxito o hubo errores
     */
    public Conexion_SQL(Connection conexion, String bd, String usuario, String clave, String mensaje) {
        this.conexion = conexion;
        this.bd = bd;
        this.usuario = usuario;
        this.clave = clave;
        this.mensaje = mensaje;
    }

    /**
     * Constructor básico que permite crear una instancia de tipo Conexion_SQL a 
     * partir de datos nulos - bases
     */
    public Conexion_SQL() {
        this.conexion = null;
        this.bd = "recaudo";
        this.usuario = "root";
        this.clave = "";
        this.mensaje = "";
    }

    /**
     * Método que se conecta a la base de datos con los datos datos del nombre 
     * de la base se datos y nos confirma si la conexión fue un éxito o hubo errores
     */
    public void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String ruta="jdbc:mysql://localhost:3306/"+bd;
            System.out.println(ruta);
            conexion= DriverManager.getConnection(ruta,usuario,clave);
            mensaje="Conexión exitosa...";
        } catch (ClassNotFoundException ex) {
            mensaje="No se pudo establecer conexion...";
        } catch (SQLException ex) {
            mensaje=" No se puede conectar con MySQL...";
        }
    }
   
    @Override
    public String toString() {
        return "Conexion{" + "conexion=" + conexion + ", bd=" + bd + ", usuario=" + usuario + ", clave=" + clave + ", mensaje=" + mensaje + '}';
    }

    /**
     * Método que retorna el contenido del atributo conexion en formato tipo 
     * Connection
     * @return Connection Conexión a la base de datos
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Es un método que modifica el contenido del atributo conexion a partir 
     * del valor recibido en formato Connection
     * @param conexion Conexión con la base de datos dada
     */
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Método que retorna el contenido del atributo bd en formato texto o String
     * @return String
     */
    public String getBd() {
        return bd;
    }

    /**
     * Es un método que modifica el contenido del atributo bd a partir del valor 
     * recibido en formato texto o String
     * @param bd Nombre de la base de datos
     */
    public void setBd(String bd) {
        this.bd = bd;
    }

    /**
     *Método que retorna el contenido del atributo usuario en formato texto o String
     * @return String
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Es un método que modifica el contenido del atributo usuario a partir del valor 
     * recibido en formato texto o String
     * @param usuario Usuario de la base de datos
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Método que retorna el contenido del atributo clave en formato texto o String
     * @return String
     */
    public String getClave() {
        return clave;
    }

    /**
     * Es un método que modifica el contenido del atributo clave a partir del valor 
     * recibido en formato texto o String
     * @param clave Clave de la base de datos
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Método que retorna el contenido del atributo mensaje en formato texto o String
     * @return String
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Es un método que modifica el contenido del atributo mensaje a partir del valor 
     * recibido en formato texto o String
     * @param mensaje Mensaje que nos confirma si la conexión fue un éxito o hubo errores
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

/*
INSERT INTO `productos` (`codigo`, `nombre`, `precio`, `cantidad`) VALUES ('P-125', 'tecla', '500', '2'), ('P-130', 'Mouse gamer', '45000', '1');
*/