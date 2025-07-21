
package control;

import com.google.zxing.WriterException;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 * La clase Controlador_Persist permite generar objetos o instancias a partir de 
 * los datos dados y será llamada en el main para ser ejecutado
 * @author Yohana Avila
 */
public class Controlador_Persist implements Runnable, ActionListener {
    /**
     * Corresponde a la instancia de tipo Recaudo
     */
    Recaudo objR;
    
    /**
     * Corresponde a la instancia de tipo JFrame que contendrá las demás ventanas
     */
    frmPrincip frmP;
    
    /**
     * Corresponde a la instancia de tipo JInternalFrame de la interfaz registrar
     */
    frmRegistrar frmR;
    
    /**
     * Corresponde a la instancia de tipo JInternalFrame de la interfaz consultar
     */
    frmConsultar frmC;
    
    /**
     * Corresponde a la instancia de tipo Archivo_Pdf que nos ayuda a generar 
     * archivos pdf
     */
    Archivo_Pdf aPdf;  
    
    /**
     * Corresponde a la instancia de tipo Fecha
     */
    Fecha objF;
    
    /**
     * Corresponde a la instancia de tipo Hora
     */
    Hora objH;
    
    /**
     * Corresponde a la instancia de tipo Thread para el manejo de hilos
     */
    Thread hilo;

    /**
     * Constructor básico que permite crear una instancia de tipo Controlador_Persist
     * a partir de datos base
     */
    public Controlador_Persist() {
        this.objR = new Recaudo();
        this.frmP = new frmPrincip();
        this.frmR = new frmRegistrar();
        this.frmC = new frmConsultar();
        this.aPdf = new Archivo_Pdf();
        this.objF = new Fecha();
        this.objH = new Hora();
        this.hilo = new Thread(this);

    }

    /**
     * Método que contiene los llamados  los Formularios y las vistas del programa
     * y que al ser llamado, será ejecutado todo el programa con sus componentes
     */
    public void iniciar(){
        frmP.setTitle("Proyecto Vehículo");
        //Botones Ventana Registrar - y sus detectores de eventos
        frmR.getCmbVehiculo().addActionListener(this);
        frmR.getBtnRegistrar().addActionListener(this);
        
        //Botones Ventana Consultar - y sus detectores de eventos
        frmC.getBtnVehiculo().add(frmC.getRdbAuto());
        frmC.getRdbAuto().addActionListener(this);
        frmC.getBtnVehiculo().add(frmC.getRdbMoto());
        frmC.getRdbMoto().addActionListener(this);
        
        frmC.getTxtRecaudoMotos().setEnabled(false);
        frmC.getTxtRecaudoAutos().setEnabled(false);
        frmC.getTxtTotalRecaudo().setEnabled(false);     
        frmC.getBtnVer_archivo().addActionListener(this);
        frmC.getBtnConectarDB().addActionListener(this);
        frmC.getBtn_Actualizar().addActionListener(this);
        frmC.getBtn_Eliminar().addActionListener(this);
        frmC.getBtn_Actualizar().setEnabled(false);
        frmC.getBtn_Eliminar().setEnabled(false);
        
        //Botones ventana Principal - y sus detectores de eventos
        frmP.getOpcConsultar().addActionListener(this);
        frmP.getOpcSalir().addActionListener(this);
        frmP.getBtnbar_Registrar().addActionListener(this);
        frmP.getBtnbar_Consultar().addActionListener(this);
        frmP.getBtnbar_Salir().addActionListener(this);
        frmP.getTgbtn_fecha().addActionListener(this);
        frmP.getTgbtn_fecha().setSelected(false);
        frmP.getTxtFecha().setEnabled(false);
        
        //frmP.getEscritorio().setBorder(new Imagen_Escritorio());
        frmP.getEscritorio().setBorder(new Imagen_Escritorio(new ImageIcon(
                getClass().getResource("/img/minimalist.png")).getImage()));
        frmP.setLocationRelativeTo(null);
        frmP.setVisible(true);
    }

    /**
     * Método que se encarga para detectar y manejar eventos que tienen algunas 
     * acciones específicas en la interfaz del programa
     * @param e ActionEvent - detectar y manejar eventos
     */
    @Override
    public void actionPerformed(ActionEvent e) { 
        Recaudo_CRUD objRC = new Recaudo_CRUD();
        
        //Manejo de las vistar y ventanas internas del programa
        if (e.getSource().equals(frmP.getOpcRegistrar()) || e.getSource().equals(frmP.getBtnbar_Registrar()) ) {
            frmC.dispose();
            frmP.getEscritorio().add(frmR);
            frmR.setVisible(true);
        }
        if (e.getSource().equals(frmP.getOpcConsultar()) || e.getSource().equals(frmP.getBtnbar_Consultar()) ) {
            frmR.dispose();
            frmP.getEscritorio().add(frmC);
            frmC.setVisible(true);
        }
        //Evento Salir del programa
        if (e.getSource().equals(frmP.getOpcSalir()) || e.getSource().equals(frmP.getBtnbar_Salir())) {
            int resp = JOptionPane.showConfirmDialog(frmC, "Desea terminar la ejecución", "Salir", 
                                                     JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp==JOptionPane.YES_OPTION) {
                frmP.dispose();
            }
        }
        //Condicional para que el registro del cilindraje solo sea visible al 
        //elegir la opción "Moto" en el Combobox
        if ("Auto".equals(frmR.getCmbVehiculo().getSelectedItem().toString())) {
            frmR.getTxtCilindraje().setVisible(false);
            frmR.getLabelcilindraje().setVisible(false);
        }else {
            frmR.getTxtCilindraje().setVisible(true);
            frmR.getLabelcilindraje().setVisible(true);
        }  
        //Togglebutton de la Fecha.
        //ora con hilos
        if (frmP.getTgbtn_fecha().isSelected() == false) {
            frmP.getTxtFecha().setText("");
            hilo.stop();
        }
        if (frmP.getTgbtn_fecha().isSelected()) {
            hilo.start();
        }
        
        //Filtro RadioButton de Moto, para solo Mostrar los Registros de tipo Moto
        if (e.getSource().equals(frmC.getRdbMoto())) {
            iniciarTabla(frmC.getTblDatos());
            
            frmC.getLblimg().setIcon(new ImageIcon("src/img/moto.png"));
            Formulario objF =new Formulario();
            objR.getListaF().add(objF);

            for (int i = 0; i < objR.getListaF().size(); i++) {    
                if(objR.getListaF().get(i).getObjV() instanceof Moto){  //Polimorfismo
                    agregarVehiculo( objR.getListaF().get(i), frmC.getTblDatos());
                }
            }
        }
        //Filtro RadioButton de Auto, para solo Mostrar los Registros de tipo Auto
        if (e.getSource().equals(frmC.getRdbAuto())) {
            iniciarTabla(frmC.getTblDatos());
            
            frmC.getLblimg().setIcon(new ImageIcon("src/img/auto.png"));
            Formulario objF =new Formulario();
            objR.getListaF().add(objF);

            for (int i = 0; i < objR.getListaF().size(); i++) {    
                if(objR.getListaF().get(i).getObjV() instanceof Auto){  //Polimorfismo
                    agregarVehiculo( objR.getListaF().get(i), frmC.getTblDatos());
                }
            }
        }
        //Persistencia de Archivos con txt, Primero limpia la tabla y después trae 
        //los registros del archivo
        if (e.getSource().equals(frmC.getBtnVer_archivo())) {
            iniciarTabla(frmC.getTblDatos());
            leertxt();
        }
        //Persistencia con base de datos y Habilitación de los botones Actualizar y Eliminar
        if (e.getSource().equals(frmC.getBtnConectarDB()) ) {
            frmC.getBtn_Actualizar().setEnabled(true);
            frmC.getBtn_Eliminar().setEnabled(true);
            frmC.getTblDatos().setModel(objRC.consultar());
        }
        //Acciones realizadas con el Botón Actualizar
        if (e.getSource().equals(frmC.getBtn_Actualizar())) {
            int fila=frmC.getTblDatos().getSelectedRow();
            Formulario Frm=new Formulario();
            try { 
                //#F, id, nombre, placa, marca, modelo, valor
                Frm.getObjP().setId(frmC.getTblDatos().getValueAt(fila, 1).toString());
                Frm.getObjP().setNombre(frmC.getTblDatos().getValueAt(fila, 2).toString());
                Auto a =  new Auto(frmC.getTblDatos().getValueAt(fila, 3).toString(), 
                                   frmC.getTblDatos().getValueAt(fila, 4).toString(), 
                                   Integer.parseInt(frmC.getTblDatos().getValueAt(fila, 5).toString()), 
                                   Double.parseDouble(frmC.getTblDatos().getValueAt(fila, 6).toString()));
                Frm.setObjV(a);
            } catch (Exceptions ex) {
            }
            objRC.setObjF(Frm);
            objRC.actualizar();
            //OptionPane.showMessageDialog(frmC, objRC.actualizar());
        }
        //Acciones realizadas con el Botón Eliminar
        if (e.getSource().equals(frmC.getBtn_Eliminar())) {
            int fila=frmC.getTblDatos().getSelectedRow();
            Formulario Frm=new Formulario();
            try {
                //#F, id, nombre, placa, marca, modelo, valor
                Frm.setNroF(frmC.getTblDatos().getValueAt(fila, 0).toString());
                Frm.getObjP().setId(frmC.getTblDatos().getValueAt(fila, 1).toString());
                Auto a =  new Auto(frmC.getTblDatos().getValueAt(fila, 3).toString(), "", 0, 0);
                
                Frm.setObjV(a);
            } catch (Exceptions ex) {
            }
            objRC.setObjF(Frm);
            JOptionPane.showMessageDialog(frmC, objRC.eliminar());
        }
        // Acciones Boton Registrar
        if (e.getSource().equals(frmR.getBtnRegistrar())) {
           //Dando Formato a la Fecha que toma el JDateChooser
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Formulario objF=new Formulario();
            
            try {
                //Modificando los datos del Propietario en el Formulario vacío
                objF.getObjP().setId(frmR.getTxtid().getText());
                objF.getObjP().setNombre(frmR.getTxtNom().getText());
                objF.getObjP().setTelefono(frmR.getTxtTel().getText());
                objF.getObjP().setEmail(frmR.getTxtEmail().getText());
                String [] fecha = formato.format(frmR.getDate_C().getDate()).split("/");
                objF.getObjP().setfNac(new Fecha(Integer.parseInt(fecha[0]),
                                                 Integer.parseInt(fecha[1]),
                                                 Integer.parseInt(fecha[2]))); 
//                if (objF.getObjP().getfNac()==null) {
//                    throw new Exceptions(101,"Fecha de Nacimiento  ");
//                }
                //Creando la conexion con el archivo txt
                Conexion_txt con = new Conexion_txt();
                switch (frmR.getCmbVehiculo().getSelectedIndex()) {
                    //Creando un Vehículo de tipo Moto
                    case 0:
                        Moto objM = new Moto();
                        objM.setPlaca(frmR.getTxtPlaca().getText());
                        objM.setMarca(frmR.getTxtMarca().getText());
                        objM.setModelo((int) frmR.getJSpin_Modelo().getValue());
                        objM.setValor(Double.parseDouble(frmR.getTxtValor().getText()));
                        objM.setCilindraje(Double.parseDouble(frmR.getTxtCilindraje().getText()));
                        objF.setObjV(objM);

                        frmR.getTextArea().setText("\nDatos Registrados\n"+ objF.toString()+ 
                                                   "\nValor del IVA: "+String.format("%.0f",objF.valorPago()));
                        objR.getListaF().add(objF);
                        //Escribiendo los datos del Recaudo en el Archivo .txt
                        con.EscribeDatos(objF.datosA(objM.getCilindraje()));
                        //Generando y Abriendo el Pdf automáticamente
                        generar_pdf(objM.getCilindraje());
                        //Modificando el objeto Formulario de la Clase Recaudo_CRUD
                        objRC.setObjF(objF);
                        //Insertando los datos a la base de datos
                        objRC.insertar_propietario(); 
                        objRC.insertar_formulario();
                        objRC.insertar_vehiculo(objM.getCilindraje());
                    break;
                    
                    //Creando un Vehículo de tipo Auto
                    case 1:
                        Auto objA = new Auto();
                        objA.setPlaca(frmR.getTxtPlaca().getText());
                        objA.setMarca(frmR.getTxtMarca().getText());
                        objA.setModelo((int) frmR.getJSpin_Modelo().getValue());                      
                        objA.setValor(Double.parseDouble(frmR.getTxtValor().getText()));
                        objF.setObjV(objA);

                        frmR.getTextArea().setText("\nDatos Registrados\n"+ objF.toString()+ 
                                                   "\nValor del IVA: "+String.format("%.0f",objF.valorPago()));
                        objR.getListaF().add(objF);
                        //Escribiendo los datos del Recaudo en el Archivo .txt
                        con.EscribeDatos(objF.datosA(0));
                        //Generando y Abriendo el Pdf automáticamente
                        generar_pdf(-1);
                        //Modificando el objeto Formulario de la Clase Recaudo_CRUD
                        objRC.setObjF(objF);
                        //Insertando los datos a la base de datos
                        objRC.insertar_propietario();
                        objRC.insertar_formulario();
                        objRC.insertar_vehiculo(-1);
                    break;

                    default:
                        throw new AssertionError();
                }
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(frmP, "Error de escritura en Archivo");
            } catch (Exceptions ex) {
                JOptionPane.showMessageDialog(frmP, ex.toString());
            } catch (WriterException ex1) {
                JOptionPane.showMessageDialog(frmP, ex1.toString());
            }

            // Ver los precios de recaudo por tipo de vehículo
            objR.getListaF();
            double Recaudo_M = 0, Recaudo_A = 0;
            for (int i = 0; i < objR.getListaF().size(); i++) {    
                if(objR.getListaF().get(i).getObjV() instanceof Auto){  //Polimorfismo
                    Recaudo_A+= objR.getListaF().get(i).valorPago();
                }
                if(objR.getListaF().get(i).getObjV() instanceof Moto){  //Polimorfismo
                    Recaudo_M+= objR.getListaF().get(i).valorPago();
                }
            }
            frmC.getTxtRecaudoMotos().setText(" $ "+String.format("%.0f",Recaudo_M));
            frmC.getTxtRecaudoAutos().setText(" $ "+String.format("%.0f",Recaudo_A));
            frmC.getTxtTotalRecaudo().setText(" $ "+String.format("%.0f",objR.totalRecaudo()));
                      
            //Limpiar todas las cajas de texto del panel de información del propietario
            for (Component object : frmR.getPnlRPropietario().getComponents()) {
                if (object instanceof JTextField) {
                    ((JTextField) object).setText("");
                } 
            }
            //Limpiar todas las cajas de texto del panel de información del vehículo
            for (Component object : frmR.getPnlRVehiculo().getComponents()) {
                if (object instanceof JTextField) {
                    ((JTextField) object).setText("");
                }
            }
        }
    }
    
    /**
     * Método que nos permite hacer el llamado a la instancia Archivo_Pdf para 
     * hacer uso de sus métodos y poder crear archivos de tipo .pdf
     * @param cil Cilindraje del vehículo para realizar comprobaciones internas
     * @throws com.google.zxing.WriterException Excepción de Archivo
     */
    public void generar_pdf(double cil) throws WriterException{
        objR.getListaF();
            String form="";
            for (int i = 0; i < objR.getListaF().size(); i++) { 
                form = objR.getListaF().get(i).getNroF();
                if (objR!=null) {
                    try {
                        aPdf.crear_PDF(form, objR,cil);
                    } catch (IOException ex) {
                        JOptionPane.showConfirmDialog(frmP, "Error de escritura en Archivo");
                    }
                }else{
                    JOptionPane.showMessageDialog(frmC, "No se ha registrado la persona");
                }
            }
        
        
    }

    /**
     * Método que se encarga de separar todos los datos contenidos en el archivo 
     * Recaudo.txt logrando persistencia y extracción de datos de archivos
     */
    public  void leertxt(){
        Conexion_txt con = new Conexion_txt();
        try {
            Formulario objF = null;
            Fecha fech = null;
            Fecha fech_nac = null;
            Persona objP = null;
            Auto objA = null;
            Moto objM = null;
            
            //Separamos cada línea de datos
            String [] productos = con.leerDatos().split("\n");
            for (String producto: productos) {
                //Separamos cada dato
                Object datos [] = producto.split(";");

                //Sacamos la fecha de registro del formulario
                String fecha1= (String) datos[1];
                for (Object fechh : datos) {
                    String fecha_r [] = fecha1.split("/");
                    fech = new Fecha(Integer.parseInt(fecha_r[0]),
                                     Integer.parseInt(fecha_r[1]),
                                     Integer.parseInt(fecha_r[2])
                    );                        
                }
                //Sacamos los datos del propietario --- Fecha de nacimiento
                String fecha2= (String) datos[6];
                for (Object fechh : datos) {
                    String fecha_na [] = fecha2.split("/");
                    fech_nac = new Fecha(Integer.parseInt(fecha_na[0]),
                                         Integer.parseInt(fecha_na[1]),
                                         Integer.parseInt(fecha_na[2])
                    );                        
                }
                objP = new Persona(datos[2].toString(), 
                                   datos[3].toString(), 
                                   datos[4].toString(), 
                                   datos[5].toString(), 
                                   fech_nac
                );
                //Sacamos los datos del Vehículo 
                //condicional para saber si es un vehículo de tipo Auto
                if (Double.parseDouble(datos[11].toString()) == -1) {
                    objA = new Auto(datos[7].toString(),
                                    datos[8].toString(), 
                                    Integer.parseInt(datos[9].toString()),
                                    Double.parseDouble(datos[10].toString())
                    );
                    //Creación del Formulario a partir de los datos del archivo
                    objF = new Formulario(datos[0].toString(), fech, objP, objA);
                }
                //condicional para saber si es un vehículo de tipo Moto
                if (Double.parseDouble(datos[11].toString()) != -1){
                    objM = new Moto(Double.parseDouble(datos[11].toString()),
                                    datos[7].toString(),
                                    datos[8].toString(), 
                                    Integer.parseInt(datos[9].toString()),
                                    Double.parseDouble(datos[10].toString())
                    );
                    //Creación del Formulario a partir de los datos del archivo
                    objF = new Formulario(datos[0].toString(), fech, objP, objM);
                }
                agregarVehiculo(objF, frmC.getTblDatos());
                // Orden de los datos del Archivo
                // 0.#Formulario, 1.F_registro,  2.id, 3.nombre, 4.telefono, 5.email, 6.f_nacimiento
                // 7.Placa, 8.marca, 9.modelo, 10.valor, 11.cilindraje
                
                //Agregando los datos extraídos del archivo a la lista de Formularios
                objR.getListaF().add(objF);
            }
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(frmC, "Error de entrada/Salida en Archivo");
        }
    }      
    
    /**
     * Método que se encarga de agregar los datos de un vehículo a la tabla de 
     * la ventana Consultar
     * @param frm Formulario de Recaudo
     * @param tabla Modelo de tabla para la insersión de datos
     */
    public  void agregarVehiculo(Formulario frm, JTable tabla) {
        Recaudo objRec = new Recaudo();
        
        objRec.getListaF().add(frm);
        String tipoV = "";
                
        for (int i = 0; i < objRec.getListaF().size(); i++) {
            if(objRec.getListaF().get(i).getObjV() instanceof Auto){  //Polimorfismo
                tipoV="Auto"; 
            }else{
                tipoV="Moto";
            }
        }
        //Enviando los datos en orden a la tabla
        Object datos [] = { frm.getNroF(), 
                            frm.getObjP().getNombre() , 
                            tipoV,
                            frm.getObjV().getPlaca(),
                            frm.getObjV().getMarca(),
                            frm.getObjV().getModelo(),
                            String.format("%.0f",frm.getObjV().getValor()),
                            String.format("%.0f",frm.getObjV().iva())
        };
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        plantilla.addRow(datos); 
    }

    /**
     * Método que se encarga de iniciar, vaciar o limpiar la tabla que es recibida 
     * como parámetro
     * @param tabla Tabla que requiere ser iniciada, vaciada o limpiada
     */
    public void iniciarTabla(JTable tabla){
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        plantilla.setRowCount(0);
    }

    /**
     * Método que se encarga de mantener la ejecución y uso de los hilos para 
     * la ejecución constante de la hora
     */
    @Override
    public void run() {
        while(true){
            frmP.getTxtFecha().setText(objF.toString()+" - "+objH.toString()); 
            objH.incSS();
            objH.incMN();
            objH.incHH();
        try {hilo.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controlador_Persist.class.getName()).log(Level.SEVERE, null, ex); }
        }
    }
}