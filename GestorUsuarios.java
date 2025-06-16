package com.godoy.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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


public class GestorUsuarios{
      //Mapa para manejar los usuarios
    public static List<Usuario>listaUsuarios = new ArrayList<>();  
    
    public void registrarUsuario(Usuario usuario){
        try{
            if(usuario == null){
                throw new NullPointerException("El usuario no puede ser nulo.");
        }
            
            //Se verifica si el usuario ya esta registrado
            if(listaUsuarios.contains(usuario)){
                System.out.println("El usuario con ID: " + usuario.getId() + " ya esta registrado.");
            }else{
        // Si el usuario no esta regit¡strado, agregar al usuario a la lista de usuarios
        if(listaUsuarios.add(usuario)){
        System.out.println("Usuario registrado, ID numero: " + usuario.obtenerDatos());
        }
            }
            }catch(NullPointerException e){
                // Manejar el caso donde se intenta registrar un usuario nulo
                System.out.println("Error al registrar usuario: " + e.getMessage());
        }
        }
        
    public Usuario consultarUsuario(int id){
        for(Usuario usuario : listaUsuarios){
        if(usuario.getId() == id){
             System.out.println("Usuario encontrado: " + usuario.obtenerDatos());
             return usuario;
        }
    }
    System.out.println("Usuario no encontrado: " + id);
return null;

}
    // Metodo para eliminar un usuario
    public void eliminar(int id){
        Usuario usuarioAEliminar = consultarUsuario(id);
        if(usuarioAEliminar != null){
            listaUsuarios.remove(usuarioAEliminar);
                System.out.println("Usuario con ID: " + id + " fue eliminado.");
        }else{
System.out.println("El usuario con ID: " + id + " no se encontro en la lista.");
            }
    }
    
    public List <Usuario> obtenerListaUsuarios(){
        return listaUsuarios;
    }
    
    
  // Metodo para guardar datos en archivos de texto
    public void guardarUsuarios(String nombreArchivo)throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            for(Usuario usuario : GestorUsuarios.listaUsuarios){
                writer.write(usuario.obtenerDatos());// S guardanlos datos del usuario
                writer.newLine();//Salto de linea 
            }
            System.out.println("Usuario guardado en: " + nombreArchivo);
            }catch(IOException e){
                System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
       
    }
      // Metodo para cargar datos desde archivos de texto
    public void cargarDatosUsuarios(String nombreArchivo)throws IOException{
        try(BufferedReader reader = new BufferedReader (new FileReader(nombreArchivo))){
            String linea; 
            while((linea = reader.readLine()) != null){
            // ya que los datos estan separados por comas 
            String[]datos = linea.split(",");
            if(datos.length == 7){
            Usuario usuario = new Usuario(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3] , Integer.parseInt(datos[4]), datos[5], datos[6]) {};
            GestorUsuarios.listaUsuarios.add(usuario);
            System.out.println("Usuarios cargados desde: " + nombreArchivo);
            }
            }
        }catch(IOException e){
            System.out.println("Error al cargar los datos en el archivo: " + e.getMessage());
        }
    }
    
    // Metodo para guardar Usuario en archivos XML 
    public void guardarDatosXML(String nombreArchivo){
        try {
            //Se crea el documento XML
            DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            
            Element root = doc.createElement("usuarios");
            doc.appendChild(root);
            
            for (Usuario usuario : GestorUsuarios.listaUsuarios){
                Element usuarioElement = doc.createElement("usuario");
                 
             usuarioElement.appendChild(createElement(doc, "nombre", usuario.getNombre()));
             usuarioElement.appendChild(createElement(doc, "apellido", usuario.getApellido()));
             usuarioElement.appendChild(createElement(doc, "dni", String.valueOf(usuario.getDni())));
             usuarioElement.appendChild(createElement(doc, "direccion", usuario.getDireccion()));
             usuarioElement.appendChild(createElement(doc, "telefono", String.valueOf((char) usuario.getTelefono())));
             usuarioElement.appendChild(createElement(doc, "email", usuario.getEmail()));
             usuarioElement.appendChild(createElement(doc, "genero", usuario.getGenero()));
             usuarioElement.appendChild(createElement(doc, "id", String.valueOf(usuario.getId())));
             
             root.appendChild(usuarioElement);
             
            }
            
            //Especificar la ruta del archivo
            String ruta = System.getProperty("user.dir")+File.separator + nombreArchivo;
            //Ruta relativa al proyecto
            File archivo = new File(ruta);
            
            //Se crea el directorio si no existe
            archivo.getParentFile().mkdirs();
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source =  new DOMSource(doc);
            
            StreamResult result = new StreamResult(archivo);
            transformer.transform(source, result);
            System.out.println("Usuarios guardados en XML: " + ruta);
        }catch(ParserConfigurationException | TransformerException | DOMException e){
            System.out.println("Error al guardar usuarios en XML: " + e.getMessage());
        }
        }
 private static Element createElement(Document doc, String tag, String value){
        Element element = doc.createElement(tag);
        element.appendChild(doc.createTextNode(value));
        return element;
        
 }
 
 

}

