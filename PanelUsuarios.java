package com.godoy.main.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.godoy.entities.GestorUsuarios;
import com.godoy.entities.Usuario;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelUsuarios extends JPanel {
    private JTextField txtNombre,txtApellido, txtDNI, txtDireccion, txtTelefono, txtEmail, txtGenero;
    private GestorUsuarios gestorUsuarios; // Se agerga una referencia a GestorLibros
    private JComboBox<String> cmbTipoUsuario;
    private JCheckBox chkAdministrador;
    private JList <String>listaUsuarios;
    private DefaultListModel mdlListaUsuarios;
    private JButton btnRegistrar, btnAgregarUsuario, btnEliminarUsuario, btnCargarUsuariosTxt, btnGuardarUsuariosTxt;
    
    
    public PanelUsuarios(GestorUsuarios gestorUsuarios){
        this.gestorUsuarios = gestorUsuarios;
        
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
        txtNombre.setBackground(Color.WHITE);
        txtNombre.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtNombre, gbc);
        
        
        //Se agrega el KeyListener
        txtNombre.addKeyListener(new KeyListener(){
            public void KeyPressed(KeyEvent e){
                // Se detecta cuando se presiona la tecla ENTER 
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                     txtApellido.requestFocus(); //Se cambia el foco al campo apellido
                 }
            }
                    @Override
            public void keyTyped(KeyEvent e) {     
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            });

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Apelido:"), gbc);
        ((JLabel)getComponent(2)).setForeground(Color.WHITE);
        
        txtApellido = new JTextField(20);
        txtApellido.setBackground(Color.WHITE);
        txtApellido.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtApellido, gbc);
        
        txtApellido.addKeyListener(new KeyListener(){
            public void KeyPressed(KeyEvent e){
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                     txtDNI.requestFocus();  //Se mueve al siguiente campo
                 }
            }          

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
                
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("DNI:"), gbc);
        ((JLabel)getComponent(4)).setForeground(Color.WHITE);
        
        txtDNI = new JTextField(20);
        txtDNI.setBackground(Color.WHITE);
        txtDNI.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtDNI, gbc);
        
        
        txtDNI.addKeyListener(new KeyListener(){
            public void KeyPressed(KeyEvent e){
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                     txtDireccion.requestFocus();  //Se mueve al siguiente campo
                 }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Dirección:"), gbc);
        ((JLabel)getComponent(6)).setForeground(Color.WHITE);
        
        txtDireccion = new JTextField(20);
        txtDireccion.setBackground(Color.WHITE);
        txtDireccion.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtDireccion, gbc);
        
        txtDireccion.addKeyListener(new KeyListener(){
            public void KeyPressed(KeyEvent e){
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                     txtTelefono.requestFocus();  //Se mueve al siguiente campo
                 }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Teléfono:"), gbc);
        ((JLabel)getComponent(8)).setForeground(Color.WHITE);
        
        txtTelefono = new JTextField(20);
        txtTelefono.setBackground(Color.WHITE);
        txtTelefono.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtTelefono, gbc);
        
        txtTelefono.addKeyListener(new KeyListener(){
            public void KeyPressed(KeyEvent e){
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                     txtEmail.requestFocus();  //Se mueve al siguiente campo
                 }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Email:"), gbc);
        ((JLabel)getComponent(10)).setForeground(Color.WHITE);
        
        txtEmail = new JTextField(20);
        txtEmail.setBackground(Color.WHITE);
        txtEmail.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtEmail, gbc);
        
        txtEmail.addKeyListener(new KeyListener(){
            public void KeyPressed(KeyEvent e){
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                     txtGenero.requestFocus();  //Se mueve al siguiente campo
                 }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Género:"), gbc);
        ((JLabel)getComponent(12)).setForeground(Color.WHITE);
        
        txtGenero = new JTextField(20);
        txtGenero.setBackground(Color.WHITE);
        txtGenero.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(txtGenero, gbc);     
        
        txtGenero.addKeyListener(new KeyListener(){
          
            public void KeyPressed(KeyEvent e){
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    registrarUsuario();
                 }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Tipo de usuario: "), gbc);
        
        ((JLabel) getComponent(14)).setForeground(Color.WHITE);
        
        //Boton de opcion para tipo de usuario
        String[] tiposUsuario= {"Estudiante" , "Profesor" ,"Adiminstrador"};
        cmbTipoUsuario = new JComboBox(tiposUsuario);
        cmbTipoUsuario.setBackground(Color.WHITE);
        cmbTipoUsuario.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(cmbTipoUsuario, gbc);
        
        cmbTipoUsuario.addActionListener(e-> {
            String tipoUsuario = (String) cmbTipoUsuario.getSelectedItem();
            //Se selecciona la opción de tipo de usuario
        });
        
        
        btnRegistrar = new JButton ("Registrar Usuario");
         btnRegistrar.setBackground(Color.DARK_GRAY);
        btnRegistrar.setForeground(Color.WHITE);

        btnRegistrar.addActionListener(e->registrarUsuario());

        gbc.gridx= 1;
        gbc.gridy = 8;
        add(btnRegistrar, gbc);
        
         
        //Lista para mostrar usuarios registrados
        mdlListaUsuarios = new DefaultListModel();
        listaUsuarios = new JList<>(mdlListaUsuarios);
        listaUsuarios.setBackground(Color.WHITE);
        listaUsuarios.setForeground(Color.BLACK);
        listaUsuarios.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int indiceSeleccionado= listaUsuarios.getSelectedIndex();
                if(indiceSeleccionado != -1){
                    String nombreUsuario = (String) mdlListaUsuarios.getElementAt(indiceSeleccionado);
                    //Se selecciona el usuario
                }
            }    
    });
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(new JScrollPane(listaUsuarios), gbc);
        
        //Evento para cargar datos en la lista de usuarios
        cargarDatosListaUsuarios();     
    
        
        
        // Botón de eliminar usuario
btnEliminarUsuario = new JButton("Eliminar usuario");
btnEliminarUsuario.setBackground(Color.DARK_GRAY);
btnEliminarUsuario.setForeground(Color.WHITE);
btnEliminarUsuario.addActionListener( e ->{ 

                    // Se obtiene el índice del usuario seleccionado de la JList
                    int indiceSeleccionado = listaUsuarios.getSelectedIndex();
                    
                    //Se verifica si se selecciono un usuario
                    if(indiceSeleccionado != -1){

int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el usuario?");
if(opcion == JOptionPane.YES_OPTION){

                        //Se obtiene el nombre del usuario selecionado desde el modelo de la lista de usuarios
                        String nombreUsuario = (String) mdlListaUsuarios.getElementAt(indiceSeleccionado);   
                        
                        //Se busca el id del usuario usando el nombre
                        int idUsuario = -1;
                       
                        for(Usuario u : gestorUsuarios.obtenerListaUsuarios()){
                            if(u.getNombre().equals(nombreUsuario)){
                                idUsuario = u.getId();
                                break;
                            }
                        }   
                        //Si se encuentra el id, el usuario se elimina
                        if(idUsuario != -1){
                            //Se elimina el usuario 
                            gestorUsuarios.eliminar(idUsuario);
                            
                            //Se actualiza la lista despues de eliminar el usuario
                            cargarDatosListaUsuarios();
                            
                            
                            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                            
                        }else{
                            JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
                }
                    }
}
});
gbc.gridx = 1;
gbc.gridy = 10;
add(btnEliminarUsuario, gbc);

    //Boton para verificar el estado del usuario
    JButton btnVerificarEstado = new JButton("Verificar Estado");
    btnVerificarEstado.setBackground(Color.DARK_GRAY);
    btnVerificarEstado.setForeground(Color.WHITE);
    btnVerificarEstado.addActionListener(e-> {
        int indiceSeleccionado = listaUsuarios.getSelectedIndex();
        if(indiceSeleccionado != -1){
            String nombreUsuario = (String) 
                    mdlListaUsuarios.getElementAt(indiceSeleccionado);
            
            //Se obtiene el id del usuario seleccionado
            for(Usuario usuario: gestorUsuarios.obtenerListaUsuarios()){
                if(usuario.getNombre().equals(nombreUsuario)){
                    int idUsuario = usuario.getId();
                    Usuario usuarioSeleccionado = gestorUsuarios.consultarUsuario(idUsuario);
                    if(usuarioSeleccionado != null){
            usuario.verificarEstado();
                    }
                    }
            
        }
        }
    });
    gbc.gridx = 1;
    gbc.gridy = 11;
    add(btnVerificarEstado,gbc);
    
        //Boton para verificar si el usuario tiene deudas 
     JButton btnVerificarDeudas = new JButton("Verificar Deudas");
     btnVerificarDeudas.setBackground(Color.DARK_GRAY);
     btnVerificarDeudas.setForeground(Color.WHITE);
     btnVerificarDeudas.addActionListener(e-> {
        int indiceSeleccionado = listaUsuarios.getSelectedIndex();
        if(indiceSeleccionado != -1){
            String nombreUsuario = (String) 
                    mdlListaUsuarios.getElementAt(indiceSeleccionado);
            //Se obtiene el id del usuario seleccionado
            for(Usuario usuario: gestorUsuarios.obtenerListaUsuarios()){
                if(usuario.getNombre().equals(nombreUsuario)){
                    int idUsuario = usuario.getId();
                    Usuario usuarioSeleccionado = gestorUsuarios.consultarUsuario(idUsuario);
                    if(usuarioSeleccionado != null){
            usuario.verificarEstado();
            usuario.verificarDeudas();
                    }
                }
        }
        }
    });
    gbc.gridx = 1;
    gbc.gridy = 12;
    add(btnVerificarDeudas, gbc);
         
    //Boton para establecer las deudas al usuario
     JButton btnEstablecerDeuda = new JButton("Establecer Deuda");
     btnEstablecerDeuda.setBackground(Color.DARK_GRAY);
     btnEstablecerDeuda.setForeground(Color.WHITE);
    btnEstablecerDeuda.addActionListener(e-> {
        int indiceSeleccionado = listaUsuarios.getSelectedIndex();
        if(indiceSeleccionado != -1){
            String nombreUsuario = (String) 
                    mdlListaUsuarios.getElementAt(indiceSeleccionado);
            //Se obtiene el id del usuario seleccionado
            for(Usuario usuario: gestorUsuarios.obtenerListaUsuarios()){
                if(usuario.getNombre().equals(nombreUsuario)){
                    int idUsuario = usuario.getId();
                    Usuario usuarioSeleccionado = gestorUsuarios.consultarUsuario(idUsuario);
                    if(usuarioSeleccionado != null){
            usuario.verificarEstado();
            boolean deudor = JOptionPane.showConfirmDialog(this, "¿El usuario es deudor?") == JOptionPane.YES_OPTION;
            usuario.establecerDeuda(deudor);
        }
        }
            }
        }
    });
    gbc.gridx = 1;
    gbc.gridy = 13;
    add(btnEstablecerDeuda, gbc);
    
        // Botón de guardar usuarios en TXT
btnGuardarUsuariosTxt = new JButton("Guardar usuarios");
btnGuardarUsuariosTxt.setBackground(Color.DARK_GRAY);
btnGuardarUsuariosTxt.setForeground(Color.WHITE);
btnGuardarUsuariosTxt.addActionListener( e -> guardarUsuariosTxt());
gbc.gridx = 1;
gbc.gridy = 15;
add(btnGuardarUsuariosTxt, gbc);
        

// Botón de cargar usuarios en TXT
btnCargarUsuariosTxt = new JButton("Cargar usuarios");
    btnCargarUsuariosTxt.setBackground(Color.DARK_GRAY);
btnCargarUsuariosTxt.setForeground(Color.WHITE);
btnCargarUsuariosTxt.addActionListener( e -> cargarUsuariosTxt());
gbc.gridx = 1;
gbc.gridy = 14;
add(btnCargarUsuariosTxt, gbc);


JButton btnGuardarUsuariosXml = new JButton("Guardar usuarios en XML");
btnGuardarUsuariosXml.setBackground(Color.DARK_GRAY);
btnGuardarUsuariosXml.setForeground(Color.WHITE);
btnGuardarUsuariosXml.addActionListener(e ->{
        try{
         JOptionPane.showMessageDialog(this, "Usuarios guardados correctamente.");
         String archivo = "usuarios.xml";
          gestorUsuarios.guardarUsuarios("usuarios.xml");
     }catch(Exception ex){
JOptionPane.showMessageDialog(this, "Error al guardar los usuarios: " + ex.getMessage());         
     }
});
gbc.gridx = 1; 
gbc.gridy = 16;
add(btnGuardarUsuariosXml, gbc);

    }
//Metodo para cargar datos en la lista de usuarios
private void cargarDatosListaUsuarios(){
        mdlListaUsuarios.clear();
        for(Usuario usuario : gestorUsuarios.obtenerListaUsuarios()){
            mdlListaUsuarios.addElement(usuario.getNombre());
        }
    }

//Metodo para registrar el usuario uando el GestorLibros
    public void registrarUsuario(){
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dniStr = txtDNI.getText();
        String direccion = txtDireccion.getText();
        String telefonoStr = txtTelefono.getText();
        String genero = txtGenero.getText();
        
        int dni;
        int telefono;
        
        //Validaciones para asegurar que los datos sean validos
        if(nombre.isEmpty()||apellido.isEmpty() || dniStr.isEmpty()||direccion.isEmpty()||telefonoStr.isEmpty()||genero.isEmpty()){
             JOptionPane.showMessageDialog(this, "Por favor,complete todos los campos");
             return;
            
        }
        
        //Convierto dni y telefono a string, manejo excepciones si son validos
        try{
            dni= Integer.parseInt(dniStr);  
            telefono = Integer.parseInt(telefonoStr); 
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "El DNI y el teléfono deben ser números enteros.");
            return;
        }
       
        //Se crea una instancia de usuario con esos datos
        Usuario usuario = new Usuario(nombre,apellido, dni, direccion, telefono, txtEmail.getText(), genero);
        
        //Se llama a registrarUsuario() de la clase GestorLibros 
        gestorUsuarios.registrarUsuario(usuario);
        cargarDatosListaUsuarios();
        
        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
        limpiarCampos();
    }       
    
    
    //Metodo para limpiar los campos
    private void limpiarCampos()
    {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDNI.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtGenero.setText("");
        
    }
    
 //Metodo para cargar usuarios en Txt
    private void cargarUsuariosTxt(){
         try{
             String archivo = "usuarios.txt";
         gestorUsuarios.cargarDatosUsuarios("usuarios.txt");
         cargarDatosListaUsuarios();
         JOptionPane.showMessageDialog(this, "Usuarios cargados correctamente.");
     }catch(IOException e){
JOptionPane.showMessageDialog(this, "Error al cargar los usuarios (IOException): " + e.getMessage());         
     }
 }
 //Metodo para guardar usuarios en Txt
    private void guardarUsuariosTxt(){
         try{
             String archivo = "usuarios.txt";
         gestorUsuarios.guardarUsuarios("usuarios.txt");
         JOptionPane.showMessageDialog(this, "Usuarios guardados correctamente.");
     }catch(IOException ex){
JOptionPane.showMessageDialog(this, "Error al guardar los usuarios: " + ex.getMessage());         
     }
    }

    

}
        



    

