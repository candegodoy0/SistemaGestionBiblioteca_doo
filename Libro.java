package com.godoy.entities;

public class Libro {
    
// Atributos de la clase Libro
 private String nombre;
 private String direccion;
 private String isbn;
 private boolean disponible = true;
 private Categoria categoria;
 
// Constructor de la clase
public Libro(String nombre, String direccion, String isbn)
{
 this.nombre = nombre;
 this.direccion = direccion;
 this.isbn = isbn;
 } 

//Constructor alternativo que toma una linea de texto y divide en atributos
public Libro(String linea){
    try{
    //Divide la linea en partes usando coma y espacio, y signa cada parte a un campo espec.
    String[] datos = linea.split(",") ; // datos contiene [nombre, direccion, isb, genero]
    // Verifica que la linea tenga al menos 4 elementos para evitar errores al querer acceder al arreglo 
    if(datos.length < 4){
        // Si la linea no tiene al menos 4 elementos, se lanza una excepcion de formato
        throw new IllegalArgumentException ("Formato de datos incompleto: " + linea);
}
    // Se asigna losvalores de los campos a las variables de instancia
        this.nombre = datos[0].trim();
        this.direccion = datos [1].trim();
        this.isbn = datos [2].trim();
    }catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e)
        // Se captura cualquier error relacionado con el formato de los datos o el acceso fuera de los limites del arreglo
    {
        System.out.println("Error al procesar datos del libro: " + e.getMessage());    
    }
}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isDisponible(){
    return disponible;}

    public Categoria getCategoria() {
        return categoria;
    }
    
    public String obtenerDatos() {
        return nombre + ", " + direccion + ", " + isbn; 
    }
    
   
}
