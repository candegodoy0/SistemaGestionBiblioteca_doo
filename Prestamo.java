package com.godoy.entities;

import java.util.Date;

public class Prestamo {
    
// Atributos de la clase Prestamo
 private Date fechaPrestamo;
 private Date fechaDevolucion;
 private String estado;
 // Contador que servira para generar id unico
 private static int contador = 0; 
 private int id; // id unico del prestamo
 private Libro libro; // libro asociado al prestamo 
 private Usuario usuario; // usuario que realiza el prestamo

    public Prestamo(Date fechaPrestamo, Date fechaDevolucion, String estado, int id, Libro libro, Usuario usuario) {
        if(usuario == null || libro == null){
            throw new IllegalArgumentException("Usuario y libro no pueden ser nulos.");
        }
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Prestamo.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        if(libro == null){
            throw new IllegalArgumentException("El libro no puede ser nulo.");
}
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

// Metodo para registrar un libro a un prestamo
    public void registarLibro(Libro libro) {
        if(libro == null){
            throw new IllegalArgumentException("El libro no puede ser nulo.");
    }
        GestorLibros.catalogoLibros.put(libro.getIsbn(),libro);
        System.out.println("Libro registrado en el prestamo: " + libro.getNombre());
    }
 
// Metodo para generar un id unico al prestamo 
    public int generarID(){
    return ++contador; // Incrementa el contador y lo devuelve como id 
    }
    
    public void calcularDiasDeRetraso(){
        // Implementar logica para calcular dias de retraso
    System.out.println("Calculando dias de retraso.");
    }
    
    // Metodo para registrar la devolucion de un libro
    public void registrarDevolucion(){
    this.estado = "Libro devuelto";
    this.fechaDevolucion = new Date(); // Se registra la fecha actual como la nueva fecha, la de devolucion 
    System.out.println("Prestamo devuelto en la fecha: " + libro.getNombre() + this.fechaDevolucion);
    }
    
    // Metodo para obtener los detalles del prestamo
    public String getDetallesPrestamo(){
        return "Prestamo ID: " + id +
                ". Libro: " + libro.getNombre() + 
                ". Fecha de prestamo: " + 
                fechaPrestamo + 
                ". Fecha devolucion: " + 
                (fechaDevolucion != null ?
                fechaDevolucion : "Aun no se ha devuelto este libro") + 
                ". Estado: " + estado;
    }
    
}
    

