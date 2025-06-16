package com.godoy.entities;

public class Persona {
   
// Atributos de la clase Persona
 private String nombre;
 private String apellido;
 private int dni;
 private String direccion;
 private int telefono;
 private String email;
 private String genero;
 private int id;
 
// Constructor de la clase
public Persona(String nombre, String apellido, int dni, String direccion, int telefono, String
email, String genero) {
 this.nombre = nombre;
 this.apellido = apellido;
 this.dni = dni;
 this.direccion = direccion;
 this.telefono = telefono;
 this.email = email;
 this.genero = genero;
 } 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }
    
      public int getId() {
          return id;
    }

    
    public void setGenero(String genero) {
        this.genero = genero;
    }


//Metodo para obtener los datos de la persona
public String obtenerDatos(){
return nombre + " " + apellido + " DNI: " + dni;
}

//Metodo para actualizar los datos de la persona
public void actualizarDatos(String direccion, int telefono, String email){
this.direccion = direccion;
this.telefono = telefono;
this.email = email; 
System.out.println("Datos actualizados.");
}
}