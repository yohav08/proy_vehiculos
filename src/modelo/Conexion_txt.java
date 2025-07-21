/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * La clase Conexion_txt permite generar objetos o instancias a partir de los datos
 * que se encargan de realizar operaciones de lectura, ecritura y creación de archivos txt
 * @author Yohana Avila
 */
public class Conexion_txt {
    private BufferedReader bufEntrada;
    private FileReader flujoLee;
    private FileWriter flujoEscr;
    private PrintWriter bufSalida;

    /**
     * Construnctor paramétrico que permite crear una instancia de tipo Conexion_txt 
     * a partir de los datos recibidos como parámetros
     * @param bufEntrada Atributo de tipo BufferedReader que realiza operaciones de lectura dentro del txt
     * @param flujoLee Atributo de tipo FileReader que lee documentos de tipo .txt
     * @param flujoEscr Atributo de tipo FileWriter que crea archivos de tipo .txt
     * @param bufSalida  Atributo de tipo PrintWriter que realiza operaciones de escritura dentro del txt
     */
    public Conexion_txt(BufferedReader bufEntrada, FileReader flujoLee, FileWriter flujoEscr, PrintWriter bufSalida) {
        this.bufEntrada = bufEntrada;
        this.flujoLee = flujoLee;
        this.flujoEscr = flujoEscr;
        this.bufSalida = bufSalida;
    }

    /**
     * Constructor básico que permite crear una instancia de tipo Conexion_txt a 
     * partir de datos nulos 
     */
    public Conexion_txt() {
        this.bufEntrada = null;
        this.flujoLee = null;
        this.flujoEscr = null;
        this.bufSalida = null;
    }

    /**
     * Método que hace operaciónes de lectura utilizando los atributos flujoLee 
     * de tipo FileReader y bufEntrada de tipo BufferedReader
     * @return String
     * @throws IOException
     */
    public String leerDatos() throws IOException{
        this.flujoLee= new FileReader("Recaudo.txt");
        bufEntrada= new BufferedReader(flujoLee);
        String datos="";
        String linea= this.bufEntrada.readLine();
        while(linea!=null){
          datos+=linea+"\n";
          linea=this.bufEntrada.readLine();
        }
        bufEntrada.close();
        return datos;
    }

    /**
     * Método que hace operaciónes de excritura utilizando los atributos flujoEscr 
     * de tipo FileWriter, bufSalida de tipo PrintWriter, recibiendo 
     * como parámetros los datos del Formulario que serán almacenados en un .txt
     * @param datos Datos que serán escritos en el Archivo
     * @throws IOException
     */
    public void EscribeDatos(String datos) throws IOException{
     flujoEscr= new FileWriter("Recaudo.txt", true);
     bufSalida= new PrintWriter(flujoEscr);
     bufSalida.println(datos);
     bufSalida.close();
    }
}
