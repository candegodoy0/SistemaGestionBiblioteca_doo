package com.godoy.main.vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.godoy.entities.GestorPrestamos;
import com.godoy.entities.GestorLibros;
import com.godoy.entities.GestorUsuarios;
import com.godoy.entities.Usuario;
import com.godoy.entities.Libro;
import com.godoy.entities.Prestamo;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;

public class PanelPrestamos extends JPanel{
    
    
    private JTextField txtIdUsuario, txtIdLibro, txtFechaPrestamo, txtFechaDevolucion;
    private GestorPrestamos gestorPrestamos;
    private GestorLibros gestorLibros;
    private GestorUsuarios gestorUsuarios;
    private JCheckBox chkVigente;
    private JComboBox<String>cmbEstadoPrestamo;
    private JList<String> listaPrestamos;
    private DefaultListModel<String> mdlListaPrestamos;
private JButton btnEliminarPrestamo, btnGuardarPrestamosTxt, btnCargarPrestamosTxt, btnGuardarPrestamosXml;
    
    public PanelPrestamos(GestorPrestamos gestorPrestamos, GestorUsuarios gestorUsuarios, GestorLibros gestorLibros){
        this.gestorPrestamos = gestorPrestamos;
        this.gestorUsuarios = gestorUsuarios;
        this.gestorLibros = gestorLibros;
        
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension (800, 600));
        
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(5, 10, 5, 10);// Márgenes entre los componentes
         gbc.fill = GridBagConstraints.HORIZONTAL;

        
        //Etiquetas y campos para escribir
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblIdUsuario = new JLabel("Usuario ID:");
        add(lblIdUsuario, gbc);
        lblIdUsuario.setForeground(Color.WHITE);
        
        txtIdUsuario = new JTextField(20);
        txtIdUsuario.setBackground(Color.WHITE);
        txtIdUsuario.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtIdUsuario, gbc);
     
     
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblIdLibro = new JLabel("Libro ID:");
        add(lblIdLibro, gbc);
        lblIdLibro.setForeground(Color.WHITE);

        
        txtIdLibro = new JTextField(20);
        txtIdLibro.setBackground(Color.WHITE);
        txtIdLibro.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtIdLibro, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblFechaPrestamo = new JLabel("Fecha préstamo:");
        add(lblFechaPrestamo, gbc);
        lblFechaPrestamo.setForeground(Color.WHITE);
        
        txtFechaPrestamo = new JTextField(20);
        txtFechaPrestamo.setBackground(Color.WHITE);
        txtFechaPrestamo.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtFechaPrestamo, gbc);
    
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblFechaDevolucion = new JLabel("Fecha devolución:");
        add(lblFechaDevolucion, gbc);
        lblFechaDevolucion.setForeground(Color.WHITE);
        
        txtFechaDevolucion = new JTextField(20);
        txtFechaDevolucion.setBackground(Color.WHITE);
        txtFechaDevolucion.setForeground(Color.BLACK);

        gbc.gridx = 1;
        add(txtFechaDevolucion, gbc);
                  
        // Boton de opción para estado del préstamos
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lblEstadoPrestamo = new JLabel("Estado del préstamo:");
        add(lblEstadoPrestamo, gbc);
        lblEstadoPrestamo.setForeground(Color.WHITE);
        String[]estadosPrestamo = {"Pendiente" , "Vigente" , "Finalizado"};
        cmbEstadoPrestamo = new JComboBox(estadosPrestamo);
        cmbEstadoPrestamo.setBackground(Color.WHITE);
        cmbEstadoPrestamo.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(cmbEstadoPrestamo, gbc);
        
            //Casilla de verificacion para préstamo vigente
    chkVigente  = new JCheckBox("Vigente");
    chkVigente.setBackground(Color.DARK_GRAY);
    chkVigente.setForeground(Color.WHITE);
    gbc.gridx = 1;
    gbc.gridx = 5;
    add(chkVigente, gbc);
        
   
        JButton btnRegistrar = new JButton("Registrar préstamo");
        btnRegistrar.setBackground(Color.DARK_GRAY);
        btnRegistrar.setForeground(Color.WHITE);
        gbc.gridx= 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e-> {
            try {
                registrarPrestamo();
            } catch (ParseException ex) {
                Logger.getLogger(PanelPrestamos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    
    // Lista para mostrar los préstamos registrados
    mdlListaPrestamos = new DefaultListModel();
    listaPrestamos = new JList(mdlListaPrestamos);
    listaPrestamos.setBackground(Color.WHITE);
    listaPrestamos.setForeground(Color.BLACK);

    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(new JScrollPane(listaPrestamos), gbc);
    
    //Evento para cargar datos en la lista de libros
    
    cargarDatosListaPrestamos();

    //Botón eliminar prestamo
    btnEliminarPrestamo = new JButton("Eliminar préstamo");
    btnEliminarPrestamo.setBackground(Color.DARK_GRAY);
    btnEliminarPrestamo.setForeground(Color.WHITE);
btnEliminarPrestamo.addActionListener(e->eliminarPrestamo());
gbc.gridx = 0;
gbc.gridy = 8;
 gbc.gridwidth = 2;
add(btnEliminarPrestamo, gbc);

//Botones para guardar y cargar
btnGuardarPrestamosTxt = new JButton("Guardar en TXT");
btnGuardarPrestamosTxt.setBackground(Color.DARK_GRAY);
btnGuardarPrestamosTxt.setForeground(Color.WHITE);
btnGuardarPrestamosTxt.addActionListener(e -> guardarPrestamosTxt()); 
gbc.gridx = 0;
gbc.gridy = 9;
add(btnGuardarPrestamosTxt, gbc);

btnCargarPrestamosTxt = new JButton("Cargar desde TXT");
btnCargarPrestamosTxt.setBackground(Color.DARK_GRAY);
btnCargarPrestamosTxt.setForeground(Color.WHITE);
btnCargarPrestamosTxt.addActionListener(e -> guardarPrestamosTxt()); 
gbc.gridx = 0;
gbc.gridy = 10;
add(btnCargarPrestamosTxt, gbc);
        
btnGuardarPrestamosXml = new JButton("Guardar en XML");
btnGuardarPrestamosXml.setBackground(Color.DARK_GRAY);
btnGuardarPrestamosXml.setForeground(Color.WHITE);
btnGuardarPrestamosXml.addActionListener(e -> guardarPrestamosXml()); 
gbc.gridx = 0;
gbc.gridy = 11;
add(btnGuardarPrestamosXml, gbc);

//Boton para consultar prestamos de usuario
JButton btnConsultarPrestamosUsuario = new JButton("Consultar préstamos de usuario");
btnConsultarPrestamosUsuario.setBackground(Color.DARK_GRAY);
btnConsultarPrestamosUsuario.setForeground(Color.WHITE);
btnConsultarPrestamosUsuario.addActionListener(e -> {
    int idUsuario = Integer.parseInt(txtIdUsuario.getText());
    Usuario usuario = gestorUsuarios.consultarUsuario(idUsuario);
    if(usuario != null){
        gestorPrestamos.consultarPrestamosUsuario(usuario, gestorPrestamos.obtenerListaPrestamos());
}else{
        JOptionPane.showMessageDialog(this, "Usuario no encontrado");
    }
});
gbc.gridx = 0;
gbc.gridy = 12;
add(btnConsultarPrestamosUsuario, gbc);

//Boton para consultar todos los prestamos existenes 
JButton btnConsultarTodosPrestamos = new JButton("Consultar todos los préstamos");
btnConsultarTodosPrestamos.setBackground(Color.DARK_GRAY);
btnConsultarTodosPrestamos.setForeground(Color.WHITE);
btnConsultarTodosPrestamos.addActionListener(e -> {
    gestorPrestamos.consultarTodosLosPrestamoExistentes();
});
gbc.gridx = 0;
gbc.gridy = 13;
gbc.gridwidth = 2;
add(btnConsultarTodosPrestamos, gbc);

//Boton para calcular dias de retraso
JButton btnCalcularDiasDeRetraso = new JButton("Calcular días de retraso");
btnCalcularDiasDeRetraso.setBackground(Color.DARK_GRAY);
btnCalcularDiasDeRetraso.setForeground(Color.WHITE);
btnConsultarPrestamosUsuario.addActionListener(e -> {
    int indiceSeleccionado = listaPrestamos.getSelectedIndex();
    if(indiceSeleccionado != -1){
        String nombreLibro = listaPrestamos.getSelectedValue();
        Prestamo prestamo = null;
        for(Prestamo pres : gestorPrestamos.obtenerListaPrestamos()){
            if(pres.getLibro().getNombre().equals(nombreLibro)){
                prestamo = pres;
                break;
            }
        }
        if(prestamo != null){
            prestamo.calcularDiasDeRetraso();
}else{
            JOptionPane.showMessageDialog(this, "Préstamo no encontrado");
        }
    }
});
gbc.gridx = 0;
gbc.gridy = 14;
add(btnCalcularDiasDeRetraso, gbc);

//Boton para registrar devolución de un prestamo 
JButton btnRegistrarDevolucion = new JButton("Registrar devolución");
btnRegistrarDevolucion.setBackground(Color.DARK_GRAY);
btnRegistrarDevolucion.setForeground(Color.WHITE);
btnRegistrarDevolucion.addActionListener(e -> {
    int indiceSeleccionado= listaPrestamos.getSelectedIndex();
    if(indiceSeleccionado != -1){
        String nombreLibro = listaPrestamos.getSelectedValue();
        Prestamo prestamo = null;
        for(Prestamo pres : gestorPrestamos.obtenerListaPrestamos()){
            if(pres.getLibro().getNombre().equals(nombreLibro)){
                prestamo = pres;
                break;
            }
        }
        if(prestamo != null){
            gestorPrestamos.registrarDevolucion(prestamo);
             JOptionPane.showMessageDialog(this, "Devolución registrada correctamente");
             cargarDatosListaPrestamos();
}else{
            JOptionPane.showMessageDialog(this, "Préstamo no encontrado");
        }
    }
});
gbc.gridx = 0;
gbc.gridy = 15;
add(btnRegistrarDevolucion, gbc);
       
    cargarDatosListaPrestamos();
    }
    
                public void registrarPrestamo() throws ParseException{
                    try{
                    int idUsuario = Integer.parseInt(txtIdUsuario.getText());
                    int idLibro = Integer.parseInt(txtIdLibro.getText());
                    String fechaPrestamoStr = txtFechaPrestamo.getText();
                    String fechaDevolucionStr = txtFechaDevolucion.getText();
                    
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date fechaPrestamo = formato.parse(fechaPrestamoStr);
                    java.util.Date fechaDevolucion = formato.parse(fechaDevolucionStr);
                    
                    // Se busca al libro y usuario por su respectivo ID
                    Usuario usuario = gestorUsuarios.consultarUsuario(idUsuario);
                    Libro libro = gestorLibros.buscarLibroPorId(String.valueOf(idLibro));
                           
                    // Se valida si el usuario y la lista existe
                    if(usuario == null){
                        JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
                        return;
                    }
                     if(libro == null){
                        JOptionPane.showMessageDialog(this, "Libro no encontrado.");
                        return;
                     }
                            
               Prestamo prestamo = new Prestamo(fechaPrestamo, fechaDevolucion,"Pendiente", Prestamo.getContador(), libro, usuario);
               
               //Seregistra el prestamo en el getsor
               gestorPrestamos.registrarPrestamo(usuario, prestamo);
               
               //Se muestra mensaje
               
               JOptionPane.showMessageDialog(this, "Préstamo registrado correctamente.");
               cargarDatosListaPrestamos();
                   }catch(ParseException e){
    JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto.");
    }catch(NumberFormatException e){
    JOptionPane.showMessageDialog(this, "Ingresa números válidos.");
       }catch(HeadlessException e){
        JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage());
}
}
        
                private void eliminarPrestamo(){
                    // Se obtiene el índice del préstamos seleccionado de la lista
                    int IndiceSeleccionado = listaPrestamos.getSelectedIndex();
                    
                    if(IndiceSeleccionado != -1){
                        //Se obtiene el nombre del libro asociado al préstamo seleccionado
                        String nombreLibro = listaPrestamos.getSelectedValue();
                        
                        //Se busca el préstamo en el gestorPrestamos usando el nombre del libro
                        Prestamo prestamo = null;
                        for(Prestamo pres : gestorPrestamos.obtenerListaPrestamos()){
                            if(pres.getLibro().getNombre().equals(nombreLibro)){
                                prestamo = pres;
                                break;
                            }
                        }   
                        if(prestamo != null){
                            //Se elimina el préstamo 
                            gestorPrestamos.eliminarPrestamo(prestamo);
                            
                            //Se muestra mensaje de confirmación
                            JOptionPane.showMessageDialog(this, "Préstamo eliminado correctamente.");
                            
                            //Se recarga la lista de préstamos
                            cargarDatosListaPrestamos();
                        }else{
                            JOptionPane.showMessageDialog(this, "Préstamo no encontrado.");
                        }
                        }else{
                            JOptionPane.showMessageDialog(this, "Préstamo eliminado correctamente.");
                    }
                
                }            
                  // Metodo para cargar datos en la lista de préstamos
    private void cargarDatosListaPrestamos(){
mdlListaPrestamos.clear();
for(Prestamo prestamo : gestorPrestamos.obtenerListaPrestamos()){
    mdlListaPrestamos.addElement(prestamo.getLibro().getNombre());
}    
    }
    
    //Metodo para guardar prestamos en Txt
    private void guardarPrestamosTxt(){
         try{
         gestorPrestamos.guardarPrestamosEnTxt("prestamos.txt");
          JOptionPane.showMessageDialog(this, "Préstamos guardados correctamente.");
     }catch(IOException ex){
JOptionPane.showMessageDialog(this, "Error al guardar los préstamos: " + ex.getMessage());         
     }
 }
    
   //Metodo para cargar prestamos en Txt
    private void cargarPrestamosEnTxt(){
         try{
         gestorPrestamos.cargarPrestamosTxt("prestamos.txt");
         cargarDatosListaPrestamos();
         JOptionPane.showMessageDialog(this, "Préstamos cargados correctamente.");
     }catch(IOException | ParseException e){
JOptionPane.showMessageDialog(this, "Error al cargar los préstamos (IOException): " + e.getMessage());         
     }
 }
     
         //Metodo para guardar prestamos en XML
    private void guardarPrestamosXml(){
         try{
         gestorPrestamos.guardarPrestamosXML("prestamos.xml");
         JOptionPane.showMessageDialog(this, "Préstamos guardados correctamente.");
     }catch(Exception e){
JOptionPane.showMessageDialog(this, "Error al guardar los préstamos: " + e.getMessage());         
     }
 }   
}


