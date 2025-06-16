package com.godoy.entities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Multa {
    
    //Lista para manejar multas como una base de datos
    public static List<Multa>listaMultas = new ArrayList<>();
    
// Atributos de la clase Multa
 private String estado;
 private float monto;
 
// Constructor de la clase
 public Multa(String estado, float monto) {
 this.estado = estado;
 this.monto = monto;
 
 }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
 //Metodo para registrar una multa
    public void registrarMulta(){
        // Se agrega la multa a la lista de multas en el sistema 
        listaMultas.add(this);
        System.out.println("La multa fue registrada con un monto de: " + monto + " y estado: " + estado);
    }

// Metodo para registar multa pagada
    public void registrarPago(){
        if(estado.equals("Pendiente")){
        estado = "Pagada";
        System.out.println("El pago de la multa fue registrado. El estado ahora es: " + estado);
        }else{
            System.out.println("La multa ya fue pagada.");
        }
        }
    
    // Metodo para visualizar todas las multas registradas
    public void mostrarMultasRegistradas(){
    System.out.println("Multas registradas: ");
    for (Multa multa : listaMultas){
        System.out.println("Estado: " + multa.getEstado() + "Monto: " + multa.getMonto());
    }
    }
    
    // Guardar multas en archivo de texto
    // Metodo para guardar datos en archivos de texto
        public void guardarMultasEnTxt(String nombreArchivo)throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            for(Multa multa : listaMultas){
                writer.write(multa.estado + "," + multa.monto);
                writer.newLine();
            } 
            System.out.println("Usuario guardado en: " + nombreArchivo);
            }catch(IOException e){
                System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
        
        // 
         }
      // Metodo para cargar datos desde archivos de texto
    public void cargarDatosMultas(String nombreArchivo)throws IOException{
        try(BufferedReader reader = new BufferedReader (new FileReader(nombreArchivo))){
            String linea; 
            while((linea = reader.readLine()) != null){
            // ya que los datos estan separados por comas 
            String[]datos = linea.split(",");
                Multa multa = new Multa(datos[0], Float.parseFloat(datos[1])); 
            }
            
            System.out.println("Usuarios cargados desde: " + nombreArchivo);
        }catch(IOException e){
            System.out.println("Error al cargar los datos en el archivo: " + e.getMessage());
   
    }
    }
        //Metodo para obtener la lista de multas
        public static List<Multa>obtenerListaMultas(){
            return listaMultas;
        }
}

