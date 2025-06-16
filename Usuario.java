package com.godoy.entities;


public class Usuario extends Persona{

// Atributo especificos de la clase usuario - (como un contador estatico para generar id unico)
// Atributos especificos de la clase Usuario
    private static int contadorID = 0; 
    private final int id;
    private int limitePrestamo;
    private boolean deudor; // Para verificar si el usuario tiene deudas

    public int getLimitePrestamo() {
        return limitePrestamo;
    }

    public void setLimitePrestamo(int limitePrestamo) {
        this.limitePrestamo = limitePrestamo;
    }

    public boolean isDeudor() {
        return deudor;
    }

    public void setDeudor(boolean deudor) {
        this.deudor = deudor;
    }
    
// Constructor de la clase
public Usuario(String nombre, String apellido, int dni, String direccion, int telefono, String
email, String genero) {
 super(nombre, apellido, dni, direccion, telefono, email, genero);
        this.limitePrestamo = 0;
        this.deudor = false;
 this.id = generarID();
 }


// Metodo sobreescrito para obtener los datos
    @Override
    public String obtenerDatos() {
        return " Usuario: " + id + super.obtenerDatos(); 
    }
    
        
//Metodo para generar un id unico para el usuario
private int generarID(){
return ++contadorID; // El contador deberia incrementar y devolver el nuevo ID
}

// Metodo para verificar el estado del usuario
public void verificarEstado(){
if(GestorPrestamos.listaPrestamos.isEmpty()){
System.out.println("El usuario se encuentra en buen estado.");
}else{
System.out.println("El usuario tiene prestamos activos.");
}
}

// Metodo para verificar si el usuario tiene deudas
public void verificarDeudas(){
    if(deudor){
        System.out.println("El usuario tiene deudas");
    }else{
        System.out.println("El usuario no tiene deudas.");
    }
}

// Metodo para establecer el nuevo estado de deuda del usuario
public void establecerDeuda(boolean deudor){
this.deudor = deudor;
    }

// Metodo para disminuir el limite de prestamos del usuario 
public void disminuirLimitePrestamo(){
    if(limitePrestamo > 0){
        limitePrestamo--; // disminue el limite de prestamo en 1
    }
}

// Metodo para aumentar el limite de prestamos
public void aumentarLimitePrestamo(){
    limitePrestamo++; // aumenta el limite de prestamos en 1
}
}
    

