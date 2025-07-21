/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement; 
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * La clase Recaudo_CRUD permite generar objetos o instancias a partir de un Formulario
 * que será insertado, actualizado y/o eliminado de la base de datos
 * @author Sonia Pinzón - Modificada por Yohana Avila
 */
public class Recaudo_CRUD {
    Formulario objF;

    /**
     * Construnctor paramétrico que permite crear una instancia de tipo Recaudo_CRUD
     * a partir de los datos recibidos como parámetro
     * @param objF Instancia de tipo Formulario que será utilizada para extraer datos
     */
    public Recaudo_CRUD(Formulario objF) {
        this.objF = objF;
    }

    /**
     * Constructor básico que permite crear una instancia de tipo Recaudo_CRUD a partir 
     * de datos base de la instancia de tipo Formulario
     */
    public Recaudo_CRUD() {
        this.objF = new Formulario();
    }

    /**
     * Método que nos permite extraer los datos de la base de datos y agregarlos 
     * a un modelo de tabla 
     * @return DefaultTableModel
     */  
    public DefaultTableModel consultar(){
        //SELECT frm.num_formulario, p.nombre, veh.placa, veh.marca, veh.modelo, veh.valor 
        //FROM formulario frm JOIN propietario p ON (frm.id_propietario = p.id)
        //                    JOIN vehiculo veh ON (p.id = veh.id_propietario) 
        DefaultTableModel plantilla= new DefaultTableModel();
        Conexion_SQL con= new Conexion_SQL();
        String select="";
        try {
            con.conectar();
            Statement consulta= con.getConexion().createStatement();
            //num_formulario, id, nombre, placa, marca, modelo, valor
            select+="select frm.num_formulario, p.id, p.nombre, veh.placa, veh.marca, veh.modelo, veh.valor ";
            select+="from formulario frm JOIN propietario p ON (frm.id_propietario = p.id) ";
            select+="                    JOIN vehiculo veh ON (p.id = veh.id_propietario) ";
            //nombre, usuario, mensaje
            ResultSet datos= consulta.executeQuery(select);
            ResultSetMetaData campos=datos.getMetaData();
            for (int i = 1; i <= campos.getColumnCount(); i++) {
                plantilla.addColumn(campos.getColumnName(i));
            }
            while(datos.next()){
             Object fila[]=new Object[campos.getColumnCount()];
                for (int i = 0; i < campos.getColumnCount(); i++) {
                   fila[i]=datos.getObject(i+1);
                }
                plantilla.addRow(fila);
            }
            datos.close();
            con.getConexion().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return plantilla;
    }
    
    /**
     * Método que nos permite insertar los datos de una persona a la tabla 
     * propietario de la base de datos "recaudo"
     * @return String
     * @throws IOException
     */
    public String insertar_propietario() throws IOException{
        String mensaje=""; 
        try {
            //inser into propietario(id, nombre, telefono, email, fecha_nac)
            Conexion_SQL conexion=new Conexion_SQL();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando= "insert into propietario values(?,?,?,?,?)";
            consulta=conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objF.getObjP().getId());
            consulta.setString(2, objF.getObjP().getNombre());
            consulta.setString(3, objF.getObjP().getTelefono());
            consulta.setString(4, objF.getObjP().getEmail());
            consulta.setString(5, objF.getObjP().getfNac().Fecha_BD());
            consulta.execute();
            
            mensaje="Registro exitoso...";
            consulta.close();
            conexion.getConexion().close();
            
        } catch (SQLException ex) {
           mensaje="Error al intentar insertar...\n"+ex;
        }
      return mensaje;  
    }
    
    /**
     * Método que nos permite insertar los datos de un vehículo a la tabla 
     * vehiculo de la base de datos "recaudo"
     * @param cilindraje Cilindraje del Vehículo tipo moto paara hacer comprobaciones
     * @return String
     * @throws IOException
     */
    public String insertar_vehiculo(double cilindraje) throws IOException{
        String mensaje=""; 
        
        try {
            //inser into vehiculo(placa, marca, modelo, cilindraje, valor, id_propietario) todo ''
            Conexion_SQL conexion=new Conexion_SQL();
            PreparedStatement consulta = null;
            conexion.conectar();
            
            String comando2= "insert into vehiculo values(?,?,?,?,?,?)";
            consulta=conexion.getConexion().prepareStatement(comando2);
            consulta.setString(1,objF.getObjV().getPlaca());
            consulta.setString(2,objF.getObjV().getMarca());
            consulta.setInt(3,objF.getObjV().getModelo());
            
            if (cilindraje != -1) {
                consulta.setDouble(4,cilindraje);
            }else if (cilindraje == -1) {
                consulta.setString(4,null);
            }
            consulta.setDouble(5,objF.getObjV().getValor());
            consulta.setString(6,objF.getObjP().getId());
            
            consulta.execute();
            
            mensaje="Registro exitoso...";
            consulta.close();
            conexion.getConexion().close();        
        } catch (SQLException ex) {
           mensaje="Error al intentar insertar...\n"+ex;
        }
      return mensaje;  
    }
    
    /**
     * Método que nos permite insertar los datos de un Formulario a la tabla 
     * formulario de la base de datos "recaudo"
     * @return String
     * @throws IOException
     */
    public String insertar_formulario() throws IOException{
        String mensaje=""; 
        
        try {
            //inser into vehiculo(#form, fecha, id_propietario)
            Conexion_SQL conexion=new Conexion_SQL();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando2= "insert into formulario values(?,?,?)";
            consulta=conexion.getConexion().prepareStatement(comando2);
            
            consulta.setString(1,objF.getNroF());
            consulta.setString(2,objF.getF_registr().Fecha_BD());
            consulta.setString(3,objF.getObjP().getId());
            consulta.execute();
            
            mensaje="Registro exitoso...";
            consulta.close();
            conexion.getConexion().close();        
        } catch (SQLException ex) {
           mensaje="Error al intentar insertar...\n"+ex;
        }
      return mensaje;  
    }
    
    /**
     * Método que nos permite actualizar los datos de la marca, modelo y valor 
     * de un vehículo, la placa no nos permite actualizar, ya que es una llave 
     * primaria en nuestra base de datos y genera conflictos, lo mismo para 
     * actualizar los datos del propietario, solo se actualiza en este caso, el 
     * nombre de la persona o propietario  ya que si se actualiza la identificación
     * genera conflictos con las demás tablas
     * @return String
     */
    public String actualizar(){
        String mensaje="",mensaje1="";
        try {
            //FFF: num_formulario,  //PPP: nombre,  //VVV: placa, marca, modelo, valor
            
            //Actualizar vehículo
            Conexion_SQL conexion=new Conexion_SQL();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = " update vehiculo"
                           + " set marca=?, modelo=?, valor=?"
                           + " where placa = '"+objF.getObjV().getPlaca()+"' ;";
            consulta=conexion.getConexion().prepareStatement(comando);
            consulta.setString(1,objF.getObjV().getMarca());
            consulta.setInt(2,objF.getObjV().getModelo());
            consulta.setDouble(3,objF.getObjV().getValor());
            consulta.execute();
            mensaje="Registro exitoso...";
            consulta.close();
            conexion.getConexion().close();
            
            //actualizar propietario
            Conexion_SQL conexion2=new Conexion_SQL();
            PreparedStatement consulta2 = null;
            conexion2.conectar();
            String comando2 = " update propietario"
                           + " set nombre=?"
                           + " where id = "+objF.getObjP().getId()+";";
            consulta2=conexion2.getConexion().prepareStatement(comando2);
            consulta2.setString(1,objF.getObjP().getNombre());
            consulta2.execute();
            mensaje1="Registro exitoso...";
            consulta2.close();
            conexion2.getConexion().close();
            
        } catch (SQLException ex) {
           mensaje="Error al intentar insertar...\n"+ex;
        }
      return mensaje;  
    }
    
    /**
     * Método que nos permite eliminar los datos de un Formulario completo, esto
     * conlleva a su vez la eliminación de los datos del propietario y los datos 
     * de su respectivo vehículo
     * @return String
     */
    public String eliminar() {
        String mensaje = "";
        try {
            Conexion_SQL conexion = new Conexion_SQL();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = " delete from formulario"
                           + " where num_formulario = '"+objF.getNroF()+"';";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.execute();
            consulta.close();
            conexion.getConexion().close();
            
            
            Conexion_SQL conexion1 = new Conexion_SQL();
            PreparedStatement consulta1 = null;
            conexion1.conectar();
            String comando1 = " delete from vehiculo"
                           + " where placa = '"+objF.getObjV().getPlaca()+"';";
            consulta1 = conexion1.getConexion().prepareStatement(comando1);
            consulta1.execute();
            consulta1.close();
            conexion1.getConexion().close();
            
            Conexion_SQL conexion2 = new Conexion_SQL();
            PreparedStatement consulta2 = null;
            conexion2.conectar();
            String comando2 = " delete from propietario"
                           + " where id = '"+objF.getObjP().getId()+"';";
            consulta2 = conexion2.getConexion().prepareStatement(comando2);
            consulta2.execute();
            mensaje = "Eliminacion exitosa...";
            consulta2.close();
            conexion2.getConexion().close();
            
        } catch (SQLException ex) {
            mensaje = "Error al intentar insertar...\n" + ex;
        }
        return mensaje;
    }
    
    @Override
    public String toString() {
        return objF.toString();
    }

    /**
     * Método que retorna el contenido del atributo Formulario de la lista de Recaudo 
     * en formato o instancia de tipo Formulario
     * @return Formulario
     */
    public Formulario getObjF() {
        return objF;
    }
    /**
     * Método que modifica el contenido del atributo Formulario de la lista de Recaudo
     * de la a partir de el valor recibido en formato o instancia de tipo Formulario
     * @param objF Formulario de Recaudo
     */
    public void setObjF(Formulario objF) {
        this.objF = objF;
    }

    
/*

INSERT INTO `propietario`(`id`,`nombre`,`telefono`,`email`,`fecha_nac`) 
    VALUES ('1007135419','yohana avila','310439109','yohav@gmail.com','2002-30-09');

INSERT INTO `formulario`(`num_formulario`,`fecha`,`id_propietario`) 
    VALUES ('FRM-999','2022-30-09','1007135419');

INSERT INTO `vehiculo`(`placa`,`marca`,`modelo`,`cilindraje`,`valor`,`id_propietario`) 
    VALUES ('rbw70','yamaha','1990','125','2000000','1007135419');
      
*/     
}
