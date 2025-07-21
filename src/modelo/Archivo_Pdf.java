/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

//liberías de itext
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.EncodeHintType;
import com.itextpdf.text.BadElementException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

//liberías zxing-core
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//archivos
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import javax.imageio.ImageIO;

//librerias ajenas a itext
import javax.swing.JOptionPane;


/**
 * @see http://jc-mouse.blogspot.com/
 * @author Mouse - Documentado por Yohana Avila
 */
public class Archivo_Pdf {

    private File ruta_destino;

    /**
     * Constructor básico que permite crear una instancia de tipo File o archivo
     * a partir de una dirección nulla 
     */
    public Archivo_Pdf(){
        ruta_destino=null; //datos nulos
    }

    /**
     * Método que hace uso de las clases itext para manipular archivos PDF y
     * jbarcodebean para generar códigos de barras
     * @param Formulario Nombre del Formulario de recaudo 
     * @param objR Lista de Formularios de Recaudo
     * @param cil Atributo cilindraje de la clase Moto para realizar comprobaciones
     * @throws IOException
     * @throws com.google.zxing.WriterException 
     */
    public void crear_PDF(String Formulario, Recaudo objR, double cil) throws IOException, WriterException{
        //Coloca el destino automáticamente
        this.ruta_destino= new File(System.getProperty("user.dir")+"/Formularios_PDF/"+Formulario+".pdf");
                
        if(this.ruta_destino!=null){ //si destino es diferente de null
            try {
                // se crea instancia del documento
                Document mipdf = new Document();
                Image imagen = Image.getInstance("src/img/Crop.jpg");
                // se establece una instancia a un documento pdf                
                PdfWriter pw= PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf"));
                mipdf.open();// se abre el documento
                mipdf.addTitle("Datos de Recaudo"); // se añade el titulo
                
                objR.getListaF();
                
                for (int i = 0; i < objR.getListaF().size(); i++) { 
                    if (objR.getListaF().get(i).getNroF().equals(Formulario)){
                        //mipdf.add(new Paragraph("Datos Formulario "+objR.getListaF().get(i).toString()+"\n"));
                        Paragraph title = new Paragraph("Formulario "+objR.getListaF().get(i).getNroF()+
                                                        "               "+
                                                        objR.getListaF().get(i).getF_registr()+"\n", 
                                                           new Font(Font.FontFamily.COURIER, 15) );
                        
                        title.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
                        Paragraph persona = new Paragraph("DATOS PROPIETARIO\n\n", 
                                                           new Font(Font.FontFamily.COURIER, 18) );
                        
                        persona.setAlignment(Element.ALIGN_CENTER);
                        //Datos del propietario agregados en el siguiente orden
                        //nombre, id, teléfono, email y fecha de nacimiento
                        Paragraph propietario_datos = new Paragraph("Nombre del propietario: "+
                                                                    objR.getListaF().get(i).getObjP().getNombre()+
                                                                    "\nIdentificación del propietario: "+
                                                                    objR.getListaF().get(i).getObjP().getId()+
                                                                    "\nTeléfono del propietario: "+
                                                                    objR.getListaF().get(i).getObjP().getTelefono()+
                                                                    "\nEmail del propietario: "+
                                                                    objR.getListaF().get(i).getObjP().getEmail()+
                                                                    "\nFecha de Nacimiento: "+
                                                                    objR.getListaF().get(i).getObjP().getfNac()
                                                                    ,new Font(Font.FontFamily.UNDEFINED, 14) );
                        propietario_datos.setAlignment(Element.ALIGN_CENTER);
                        
                        Paragraph vehiculo = new Paragraph("\nDATOS VEHÍCULO\n\n", 
                                                           new Font(Font.FontFamily.COURIER, 18) );
                        vehiculo.setAlignment(Element.ALIGN_CENTER);

                        //Creando tabla que contendrá los datos del vehículo
                        PdfPTable table = new PdfPTable(2);
                        table.setWidths(new float[] {30,30});
                        PdfPCell columnHeader;

                        columnHeader = new PdfPCell(new Phrase("Datos "));
                        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(columnHeader);
                        columnHeader = new PdfPCell(new Phrase("Vehículo "));
                        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(columnHeader);
                        table.setHeaderRows(1);
                        
                        //Agregando las filas con los atributos
                        table.addCell("Placa: ");
                        table.addCell(objR.getListaF().get(i).getObjV().getPlaca());
                        table.addCell("Marca: ");
                        table.addCell(objR.getListaF().get(i).getObjV().getMarca());
                        table.addCell("Modelo: ");
                        table.addCell(String.valueOf(objR.getListaF().get(i).getObjV().getModelo()));
                        table.addCell("Valor: ");
                        table.addCell(String.valueOf(objR.getListaF().get(i).getObjV().getValor()));
                        
                        // condicional que agrega la fila de cilindraje solo si el vehículo es una Moto
                        if (cil!=-1) { 
                            table.addCell("cilindraje: ");
                            table.addCell(String.valueOf(cil));
                        }
                        Paragraph Bar_code = new Paragraph("\nCÓDIGOS QR Y DE BARRAS\n\n", 
                                                           new Font(Font.FontFamily.COURIER, 18) );
                        Bar_code.setAlignment(Element.ALIGN_CENTER);
                                          
                        //Añadiendo todo al PDF: imágenes, títulos, tabla...
                        Bar_code.setAlignment(Element.ALIGN_CENTER);
                        mipdf.add(title);
                        mipdf.add(imagen);
                        mipdf.add(persona);
                        mipdf.add(propietario_datos);
                        mipdf.add(vehiculo);
                        mipdf.add(table);
                        mipdf.add(Bar_code);
                        mipdf.add(codBar(mipdf, pw,objR.getListaF().get(i).getNroF()));
                        mipdf.add(codQr(objR.getListaF().get(i).getNroF()));   
                    }
                }
                mipdf.close(); //se cierra el PDF&
                abrirPdf(this.ruta_destino+".pdf");
            } catch (DocumentException ex) {
                JOptionPane.showMessageDialog(null, "Error Creando Documento");
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null, "Error de entrada/Salida en Archivo");
            } 
        }
    }
    
    /**
     * Método que permite crear una imagen que contiene el código de Barras de los 
     * datos enviados como parámetros
     * @param doc Instancia de tipo Document
     * @param pw Instancia de tipo PdfWriter
     * @param datos Datos que serán convertidos en código QR
     * @return Image 
     */
    public Image codBar(Document doc, PdfWriter pw, String datos){
        PdfContentByte cimg = pw.getDirectContent();
        Barcode128 code128 = new Barcode128();
        code128.setCode(datos);
        //tipo de codigo que se le va a enviar
        code128.setCodeType(Barcode128.CODE128);
        code128.setTextAlignment(Element.ALIGN_CENTER);
        Image img= code128.createImageWithBarcode(cimg, BaseColor.BLACK, BaseColor.WHITE);
        img.setAlignment(Element.ALIGN_CENTER);
        return img;
    }
    
    /**
     * Método que permite crear una imagen que contiene el código QR de los datos
     * enviados como parámetros
     * @param datos Datos que serán convertidos en código QR
     * @return Image
     * @throws IOException
     * @throws WriterException
     */
    public Image codQr(String datos) throws WriterException, IOException {
        Image img = null;   
        try {
            //se crea la matriz tipo byte para el codigo QR desde el String
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            int size=125;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(datos, BarcodeFormat.QR_CODE, size, size, hintMap);
            // se crea el BufferedImage para agregar el código QR
            int matrixWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            // Se dibuja y almacena la imagen usando ByteMatrix
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            String ruta = System.getProperty("user.dir")+"/Formularios_PDF/QRCode.png";
            //String archiv = System.getProperty("user.dir") + "/QRCod.png";
            File qrFile = new File(ruta);
            ImageIO.write(image, "png", qrFile);
            img = Image.getInstance(ruta);
            img.setAlignment(Element.ALIGN_CENTER);
        } catch (BadElementException | MalformedURLException ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        return img;
}
    
    /**
     * El metodo abrir PDF permite abrir un documento mediante su ruta destino
     * @param path Variable que contiene la ruta destino del archivo a abrir
     */
    public void abrirPdf(String path){
        try {
            File pdf = new File(path);
            Desktop.getDesktop().open(pdf);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    /**
     * Método que retorna el contenido de la ruta de destino de los archivos 
     * creados en la clase, dado en formato  File o archivo
     * @return File Ruta destino del Pdf
     */
    public File getRuta_destino() {
        return ruta_destino;
    }

    /**
     * Es un método que modifica el conteido del atributo ruta de destino de la 
     * clase a partir de el valor recibido en formato File o archivo
     * @param ruta_destino Ruta destino donde será guardado el PDF
     */
    public void setRuta_destino(File ruta_destino) {
        this.ruta_destino = ruta_destino;
    }
    
}