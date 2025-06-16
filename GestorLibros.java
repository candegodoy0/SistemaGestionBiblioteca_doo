package com.godoy.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.godoy.entities.Libro;
import com.godoy.entities.Categoria;


public class GestorLibros {
     // Mapa para manejar los libros
public static HashMap<String, Libro> catalogoLibros = new HashMap<>();
//// Lista para manejar las categorias
public List<Categoria> categorias = new ArrayList<>();

    public void registrarLibro(Libro libro) {

            if(libro == null){
            throw new IllegalArgumentException("El libro no puede ser nulo.");
        }
        if(!catalogoLibros.containsKey(libro.getIsbn())){
            catalogoLibros.put(libro.getIsbn(),libro);
        System.out.println("Libro: " + libro.getNombre() + " registrado.");
    }else{
    System.out.println("Libro con ISBN: " + libro.getIsbn() + " ya esta registrado.");
}
    }
public void eliminar(String isbn){
if(catalogoLibros.remove(isbn) != null){
System.out.println("El libro con ISBN: " + isbn + " fue eliminado.");
}else{
    System.out.println("El libro con ISBN: " + isbn + " no se encontro.");
    }
}

public void consultar(String isbn){
    try{
// Se busca el libro por isbn
    Libro libro = catalogoLibros.get(isbn);
// Verifica si el libro existe en el catalogo(si no es null)
    if(libro == null){
//Si el libro no existe, lanza excepcion
        throw new IllegalArgumentException("El libro con ISBN " + isbn + " no existe.");
    }
// Si el libro existe verifica que este disponible
            if(libro.isDisponible()){
                System.out.println("El libro: " + libro.getNombre() + " esta disponible.");
            }else{
                System.out.println("El libro con el ISBN: " +  libro.getNombre() + " no esta disponible");
}
}catch(IllegalArgumentException e){
    System.out.println("Error: " + e.getMessage());
}
}

// Metodo para buscar libro por ID
public Libro buscarLibroPorId(String isbn){
    return catalogoLibros.get(isbn);
}
    
// Metodo para asignarle categoria a un libro
    public void asignarCategoria(String isbn, Categoria categoria){
        if(categorias.contains(categoria)){
            Libro libro = catalogoLibros.get(isbn);
            if(libro != null){
                libro.setCategoria(categoria);
            System.out.println("Categoria: " + categoria + "asignada al libro: " + libro.getNombre() + ".");
        }else{
                System.out.println("El l ibro con ISBN: " + isbn + " no se encontro en la base de datos.");
            }
            }else{
            System.out.println("Categoria: " + categoria + " no existe.");
        }
    }
    // Se devuelve lista de libros registrados
    public List<Libro>obtenerLibros(){
        return new ArrayList<>(catalogoLibros.values());
    }
    
 // Metodo para guardar datos en archivos de texto
    public void guardarLibros(String nombreArchivo)throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            for(Libro libro : catalogoLibros.values()){
                writer.write(libro.obtenerDatos());// Se guardan los datos del usuario
                writer.newLine();//Salto de linea 
            }
            System.out.println("Libro guardado en: " + nombreArchivo);
            }catch(IOException e){
                System.out.println("Error al guardar libros: " + e.getMessage());
            }
    }
    
    // Metodo para cargar datos desde archivos de texto
    public void cargarDatosLibros(String nombreArchivo)throws IOException{
        // Intenta abrir y leer el archivo
        try(BufferedReader reader = new BufferedReader (new FileReader(nombreArchivo))){
            String linea; 
            // Lee cada linea del archivo
            while((linea = reader.readLine()) != null){
            try{
                // Crea un nuevo objeto libro en el catalogo
            Libro libro = new Libro(linea);
            registrarLibro(libro );
            }catch(IllegalArgumentException e){
                //Informa si hay un error en el formato de la linea 
                System.out.println("Error al cargar libro: " + e.getMessage());
        }
    }
            System.out.println("Libros cargados desde: " + nombreArchivo);
        }catch(IOException e){
        // Informa si hay un error al leer el archivo
            System.out.println("Error al cargar los datos en el archivo: " + e.getMessage());
        }
    }
}
    

