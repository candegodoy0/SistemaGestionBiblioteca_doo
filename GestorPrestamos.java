package com.godoy.entities;
import com.godoy.entities.Libro;
import com.godoy.entities.Usuario;
import com.godoy.entities.GestorPrestamos;
import java.text.ParseException;
import com.godoy.entities.GestorLibros;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.LinkedList;

public class GestorPrestamos {
public static LinkedList<Prestamo> listaPrestamos = new LinkedList<>();
private static final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
 
    // Metodo para registrar un nuev prestamo
    public void registrarPrestamo(Usuario usuario, Prestamo prestamo){
        if(usuario.getLimitePrestamo() > 0 && !usuario.isDeudor()){
            listaPrestamos.add(prestamo);
            usuario.disminuirLimitePrestamo();// disminuye el limite de prestamos del usuario 
            System.out.println("Prestamo registrado para el usuario: " + usuario.obtenerDatos());
        }else if(usuario.isDeudor()){
            System.out.println("El usuario tiene deudas y no puede solicitar nuevos prestamos");
}else{
                    System.out.println("El usuario alcanzo el limite de prestamos");
                    }
    }
    
    // Registrar la devolucion del prestamo
    public void registrarDevolucion(Prestamo prestamo){
        prestamo.registrarDevolucion();//se registra la devolucion del prestamo
        Usuario usuario = prestamo.getUsuario();// se obtiene el usuario asociado a ese prestamo
        usuario.aumentarLimitePrestamo();// se aumenta el limite de prestamos del usuario 
        System.out.println("Devolucion registrada para el usuario: " + usuario.getNombre() + ". Limite actualizado: " + usuario.getLimitePrestamo());

    }
        public void eliminarPrestamo(Prestamo prestamo){
Usuario usuario = prestamo.getUsuario();
            if(listaPrestamos.remove(prestamo)){
            usuario.aumentarLimitePrestamo();// se incrementa el limite del usuario
                    System.out.println("Prestamo eliminado: " + prestamo.getDetallesPrestamo());
        }else{
    System.out.println("No se encontro el prestamo para eliminar.");
}
}
        public void consultarPrestamosUsuario(Usuario usuario, Iterable<Prestamo> listaPrestamos){
            System.out.println("Prestamos activos: " + usuario.obtenerDatos());
            for(Prestamo prestamo : listaPrestamos){
                if(prestamo.getUsuario().equals(usuario)){
                    System.out.println(prestamo.getDetallesPrestamo());
                }
            }
        }
    public void consultarTodosLosPrestamoExistentes(){
if( listaPrestamos.isEmpty()){
System.out.println("No hay prestamos registrados.");
}else{
System.out.println("Lista de todos los prestamos existentes: ");
for(Prestamo prestamo : listaPrestamos){
System.out.println(prestamo.getDetallesPrestamo());
}
}
    }
    
    
    //Meétodo para obtener la lista completa préstamos
    public LinkedList<Prestamo>obtenerListaPrestamos(){
        return listaPrestamos;
    }
    
    
    // Metodo para guardar datos en archivos de texto
    public void guardarPrestamosEnTxt(String nombreArchivo)throws IOException{

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            for(Prestamo prestamo : GestorPrestamos.listaPrestamos){
                writer.write(prestamo.getId () + "," + prestamo.getUsuario() + "," + prestamo.getLibro() + "," + prestamo.getEstado());// Se guardan los datos del usuario
                writer.newLine();//Salto de linea 
            }
            System.out.println("Usuario guardado en: " + nombreArchivo);
            }catch(IOException e){
                System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
       
    }
      // Metodo para cargar datos desde archivos de texto
    public void cargarPrestamosTxt(String nombreArchivo)throws IOException, ParseException{
        try(BufferedReader reader = new BufferedReader (new FileReader(nombreArchivo))){
            String linea; 
            while((linea = reader.readLine()) != null){
            // ya que los datos estan separados por comas 
            String[]datos = linea.split(",");
            if(datos.length >= 7){
               
            Prestamo prestamo = new Prestamo(
              formato.parse(datos[1]),
                    formato.parse(datos[2]),
                    datos[3],
                    Integer.parseInt(datos[0]),
                    new Libro(datos[4]),
                    new Usuario(datos[5], datos[6], Integer.parseInt(datos[7]),datos[8], Integer.parseInt(datos[9]),datos[10], datos[11])
            );
// Se añade el prestamo a la lista
GestorPrestamos.listaPrestamos.add(prestamo);
}
            }
            
            System.out.println("Usuarios cargados desde: " + nombreArchivo);
        }catch(IOException e){
            System.out.println("Error al cargar los datos en el archivo: " + e.getMessage());
        }
        
    }

    
    // Metodo para guardar Prestamo en archivos XML 
    public void guardarPrestamosXML(String nombreArchivo)throws Exception{
        try {
            DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element root = doc.createElement("prestamos");
            doc.appendChild(root);
            
            for (Prestamo prestamo : GestorPrestamos.listaPrestamos){
                Element prestamoElement = doc.createElement("prestamo");
                
                prestamoElement.appendChild(createElement(doc, "id", String.valueOf(prestamo.getId())));
                prestamoElement.appendChild(createElement(doc, "usuario", prestamo.getUsuario().getNombre()));
                prestamoElement.appendChild(createElement(doc, "libro", prestamo.getLibro().getNombre()));
                prestamoElement.appendChild(createElement(doc, "estado", prestamo.getEstado()));
                
                root.appendChild(prestamoElement);       
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source =  new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nombreArchivo));
            
            transformer.transform(source, result);

            System.out.println("Usuarios guardados en XML: " + nombreArchivo);
        }catch(ParserConfigurationException | TransformerException | DOMException e){
        }
        }
    
    private Element createElement(Document doc, String tagName, String textContent){
        Element element = doc.createElement(tagName);
        element.setTextContent(textContent);
        return element;
}    
    }

