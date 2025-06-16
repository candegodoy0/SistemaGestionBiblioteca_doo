package com.godoy.main.vistas;

import com.godoy.entities.Categoria;
import javax.swing.*;
import java.awt.*;
import com.godoy.entities.GestorLibros;
import com.godoy.entities.Libro;
import  java.awt.event.*;
import java.io.IOException;

public class PanelLibros extends JPanel{
    private JTextField txtNombre, txtDireccion, txtIsbn, txtGenero, txtDisponible, txtCategoria, txtEdicion;
    private GestorLibros gestorLibros;
    private JCheckBox chkDisponible;
    private JComboBox<String> cmbCategoria;
    private JList <String>listaLibros;
    private DefaultListModel <String> mdlListaLibros;
    private JButton btnRegistrarLibro, btnEliminarLibro, btnGuardarLibros, btnCargarLibros;
    
    public PanelLibros(GestorLibros gestorLibros){
        this.gestorLibros = gestorLibros;
        
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension (800, 600));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);// Márgenes entre los componentes
        
        //Etiquetas y campos para escribir
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);
        ((JLabel)getComponent(0)).setForeground(Color.WHITE);
        
        txtNombre = new JTextField(20);
        txtNombre.setForeground(Color.BLACK);
        txtNombre.setBackground(Color.WHITE);
        gbc.gridx = 1;
        add(txtNombre, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Dirección:"), gbc);
        ((JLabel)getComponent(2)).setForeground(Color.WHITE);
        
        txtDireccion = new JTextField(20);
        txtDireccion.setBackground(Color.WHITE);
        txtDireccion.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtDireccion, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("ISBN:"), gbc);
        ((JLabel)getComponent(4)).setForeground(Color.WHITE);
        
        txtIsbn = new JTextField(20);
        txtIsbn.setBackground(Color.WHITE);
        txtIsbn.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtIsbn, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Género:"), gbc);
        ((JLabel)getComponent(6)).setForeground(Color.WHITE);
        
        txtGenero = new JTextField(20);
        txtGenero.setBackground(Color.WHITE);
        txtGenero.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtGenero, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Edición:"), gbc);
        ((JLabel)getComponent(8)).setForeground(Color.WHITE);
        
        txtEdicion = new JTextField(20);
        txtEdicion.setBackground(Color.WHITE);
        txtEdicion.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtEdicion, gbc);
        
        gbc.gridx = 0;
        gbc.gridy= 5;
        add(new JLabel("Categoria:"), gbc);
        
        
        txtCategoria = new JTextField(20);
        ((JLabel)getComponent(10)).setForeground(Color.WHITE);

  //Se crea y añade JComboBox para categoría
        String[] categorias = {"Ficción" , "Ciencia" , "Historia", "Poética" , "Didáctico", "Juvenil"};
        cmbCategoria = new JComboBox<>(categorias);
        cmbCategoria.setBackground(Color.WHITE);
        cmbCategoria.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(cmbCategoria, gbc);  
        
    //Casilla de verificacion para disponibilidad
    chkDisponible  = new JCheckBox("Disponible");
    chkDisponible.setBackground(Color.DARK_GRAY);
    chkDisponible.setForeground(Color.WHITE);
    gbc.gridx = 1;
    gbc.gridy= 6;
    add(chkDisponible, gbc);
        
        btnRegistrarLibro = new JButton("Registrar Libro");
        btnRegistrarLibro.setBackground(Color.DARK_GRAY);
        btnRegistrarLibro.setForeground(Color.WHITE);
        btnRegistrarLibro.addActionListener(e->registrarLibro());
               
        gbc.gridx= 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(btnRegistrarLibro, gbc);
    
    
    // Lista para mostrar los libros registrados
    mdlListaLibros = new DefaultListModel();
    listaLibros = new JList(mdlListaLibros);
    listaLibros.setBackground(Color.WHITE);
    listaLibros.setForeground(Color.WHITE);
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(new JScrollPane(listaLibros), gbc);
    
    //Evento para cargar datos en la lista de libros
    
    cargarDatosListaLibros();
    
    //Botón de eliminar libro
    btnEliminarLibro = new JButton("Eliminar Libro");
    btnEliminarLibro.setBackground(Color.DARK_GRAY);
    btnEliminarLibro.setForeground(Color.WHITE);
    btnEliminarLibro.addActionListener((ActionEvent e) -> {
        String selectedIsbn = listaLibros.getSelectedValue();
        if(selectedIsbn != null){
            gestorLibros.eliminar(selectedIsbn); //Se elimina el libro usando el ISBN
            cargarDatosListaLibros();
            JOptionPane.showMessageDialog(this, "Libro eliminado.");
        }else{
             JOptionPane.showMessageDialog(this, "Seleccione un libro para eliminar.");   
        }
        });
 
 gbc.gridx = 0;
 gbc.gridy = 9;
 add(btnEliminarLibro, gbc);  
 
 
 //Boton para guardar libros
 btnGuardarLibros = new JButton("Guardar libros");
 btnGuardarLibros.setBackground(Color.DARK_GRAY);
 btnGuardarLibros.setForeground(Color.WHITE);
 btnGuardarLibros.addActionListener((ActionEvent e )-> {
     try{
         gestorLibros.guardarLibros("libros.txt");
         JOptionPane.showMessageDialog(this,"Libros guardados correctamente.");
     }catch(IOException ex){
JOptionPane.showMessageDialog(this, "Error al guardar libros: " + ex.getMessage());         
     }
 });
  gbc.gridx = 0;
 gbc.gridy = 10;
 add(btnEliminarLibro, gbc);  
 
 //Botón para cargar libros
 btnCargarLibros = new JButton("Cargar libros");
 btnCargarLibros.setBackground(Color.DARK_GRAY);
 btnCargarLibros.setForeground(Color.WHITE);
 btnCargarLibros.addActionListener((ActionEvent e )-> {
     try{
         gestorLibros.cargarDatosLibros("libros.txt");
         cargarDatosListaLibros();
         JOptionPane.showMessageDialog(this,"Libros cargados correctamente.");
     }catch(IOException ex){
JOptionPane.showMessageDialog(this, "Error al cargar libros: " + ex.getMessage());         
     }
 });

    //Se agregan botones para guardar y cargar libros
    gbc.gridx = 0;
    gbc.gridy = 11;
    add(btnCargarLibros, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 12;
    add(btnGuardarLibros, gbc);   
    
    //Boton para consultar la disponibilidad de un libro
   JButton btnConsultarDisponibilidad = new JButton("Consultar disponibilidad");
   btnConsultarDisponibilidad.setBackground(Color.DARK_GRAY);
   btnConsultarDisponibilidad.setForeground(Color.WHITE);
 btnConsultarDisponibilidad.addActionListener((ActionEvent e )-> {
     String isbn = txtIsbn.getText();
     if(!isbn.isEmpty()){
         Libro libro = gestorLibros.buscarLibroPorId(isbn);
         if(libro !=null){
         if(libro.isDisponible()){
             JOptionPane.showMessageDialog(this, "El libro está disponible");
         }else{
             JOptionPane.showMessageDialog(this, "El libro no está disponible");
         }
     }else{
         JOptionPane.showMessageDialog(this, "El libro no existe");
     }
 }else{
         JOptionPane.showMessageDialog(this, "Ingrese el ISBN del libro");
         }   

    });
    
    gbc.gridx = 0;
    gbc.gridy = 13; 
    add(btnConsultarDisponibilidad, gbc);
    
    }
    // Metodo para cargar datos en la lista de libros
    private void cargarDatosListaLibros(){
mdlListaLibros.clear();
for(Libro libro : gestorLibros.obtenerLibros()){
    mdlListaLibros.addElement(libro.getNombre() + " , ISBN:" + libro.getIsbn());
}
    }
    
    // Metodo para registrar libros
    public void registrarLibro(){
        
        try{
    
        String nombre = txtNombre.getText();
        String isbn = txtIsbn.getText();
        String categoria = (String) cmbCategoria.getSelectedItem();
        boolean disponible = chkDisponible.isSelected();
        
        // Se validan los campos
        if(nombre.isEmpty()||isbn.isEmpty() || categoria.isEmpty()){
        JOptionPane.showMessageDialog(this,"Por favor complete todos los campos." );
        return;
        }
        
        //Validar ISBN unico
        if(gestorLibros.buscarLibroPorId(isbn) != null){
            JOptionPane.showMessageDialog(this, "El ISBN ya existe");
            return;
        }
        
        //Se crea el libro
        Libro libro = new Libro(nombre, isbn, categoria);
        
        //Se registra el libro en gestorLibros
        gestorLibros.registrarLibro(libro);
        
        //Se confirma con un mensaje 
         JOptionPane.showMessageDialog(this, "Libro registrado correctamente.");
         
         //Se limpian los campos despues de registrar
         txtNombre.setText("");
         txtIsbn.setText("");
         chkDisponible.setSelected(false);
         
        }catch(HeadlessException e){
             JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage());
        }
         
         
    }
}
