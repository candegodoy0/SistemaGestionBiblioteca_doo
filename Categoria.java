package com.godoy.entities;


public class Categoria implements Gestion {
    
 // Atributos de la clase Categoría
private String nombreLibro;
 private String edicion;
 
// Constructor de la clase
 public Categoria(String nombreLibro, String edicion) {
 this.nombreLibro = nombreLibro;
 this.edicion = edicion;
 }
    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }
    
    // Metodo para asignar una categoria a un libro
    public void asignarCategoria(Libro libro){
    libro.setCategoria(this); // Se le asignara esta categoria a ese libro
    System.out.println("Categoria: " + this.nombreLibro + " asignada al libro.");
    }
    
    //Metodo para restringir prestamo dependiendo de la categoria
    public boolean restringirPrestamo(){
    if(this.edicion.equalsIgnoreCase("Edicion limitada")){
        System.out.println("Prestamos restringidos para libros de categoria: " + this.nombreLibro + ".");
        return true; //se restringe
    }
    System.out.println("No hay restricciones para prestamo de libros de categoria: " + this.nombreLibro + ".");
    return false; //no restringido
    }
      //Metodo para restringir prestamo dependiendo de la categoria
     public boolean autoizarPrestamo(){
     if(!this.restringirPrestamo())
     {
     System.out.println("Prestamos autorizados para libros de categoria: " + this.nombreLibro + ".");
     return true; // autorizado
     }
     System.out.println("Prestamos no autorizados para libros de categoria: " + this.nombreLibro + ".");
     return false; // no autorizado 
     }
     
     // Metodo para consultar informacion de la categoria

    @Override
    public void consultar(){
    System.out.println("Categoria: " + this.nombreLibro);
    System.out.println("Edicion: " + this.edicion);
    }
    
    @Override
    public void registrar(){
        System.out.println("Categoria registrada: " + nombreLibro);
    }
    
    @Override
    public void eliminar(){
        System.out.println("Categoria: " + nombreLibro + ", Edicion: " + edicion);
    }
    }
     
     
    
           
    
