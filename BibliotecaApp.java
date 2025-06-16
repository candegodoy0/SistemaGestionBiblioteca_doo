package com.godoy.main;
import javax.swing.*;
import java.awt.*;
import com.godoy.main.vistas.PanelLibros;
import com.godoy.main.vistas.PanelPrestamos;
import com.godoy.main.vistas.PanelUsuarios;
import com.godoy.main.vistas.PanelMultas;
import java.awt.CardLayout;
import com.godoy.entities.GestorLibros;
import com.godoy.entities.GestorUsuarios;
import com.godoy.entities.GestorPrestamos;
import java.awt.event.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatDarkLaf;

public class BibliotecaApp extends JFrame {    

    private final JPanel frmPrincipal;
    private final CardLayout cardLayout;
    
    
    //Instancias de los gestores de préstamos, usuarios y libros 
    private GestorLibros gestorLibros;
    private GestorUsuarios gestorUsuarios;
    private GestorPrestamos gestorPrestamos;
            
public BibliotecaApp(){
        //Se configura JFrame
        setTitle("Sistema de Gestión de Biblioteca.");
        setSize(800, 600);
        pack();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Instancias de los gestores 
        gestorLibros = new GestorLibros();
        gestorUsuarios = new GestorUsuarios();
        gestorPrestamos = new GestorPrestamos();
               
        //Se agrega al panel principal
        cardLayout = new CardLayout();
        frmPrincipal = new JPanel(cardLayout);
        frmPrincipal.setBackground(Color.WHITE);
        
             // Se agregan los paneles 
        frmPrincipal.add(new PanelUsuarios(gestorUsuarios), "Usuarios");
        frmPrincipal.add(new PanelLibros(gestorLibros), "Libros"); 
        frmPrincipal.add(new PanelPrestamos(gestorPrestamos, gestorUsuarios, gestorLibros), "Préstamos");
        frmPrincipal.add(new PanelMultas(),"Multas");
        
          
        // Se agrega al panel principal
            add(frmPrincipal, BorderLayout.CENTER);
            
              // Se crean componentes
        // Se agrega barra de menú
        setJMenuBar(createMenuBar());
        
        // Se agrega barra de accesos rápidos / herramientas
        add(createToolBar(), BorderLayout.NORTH);
        
        // Registrar el WindowListener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(frmPrincipal, 
                        "¿Seguro que quieres salir de la plicación?", "Confirmación de salida", 
                        JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    dispose(); // Cerrar la ventana
}
                } 
});         
}
       
        // Menu
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new  JMenuBar();
        
        JMenu menuArchivo = new JMenu("Archivo:");
        menuArchivo.setMnemonic(KeyEvent.VK_A);
        
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.setMnemonic(KeyEvent.VK_S);
        itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        
        itemSalir.addActionListener(e->System.exit(0)); //Se cierra la aplicación
        menuArchivo.add(itemSalir);
    
        //Menu de gestión
        JMenu menuGestion = new JMenu("Gestión");
        menuGestion.setMnemonic(KeyEvent.VK_G);
        JMenuItem itemAgregar = new JMenuItem("Agregar");
        itemAgregar.setMnemonic(KeyEvent.VK_A);
        itemAgregar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itemAgregar.addActionListener(e->JOptionPane.showMessageDialog(this, "Agregar"));
                  
        JMenuItem itemEditar = new JMenuItem("Editar");
        itemEditar.setMnemonic(KeyEvent.VK_E);
        itemEditar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        itemEditar.addActionListener(e->JOptionPane.showMessageDialog(this, "Editar"));
                  
        
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        itemEliminar.setMnemonic(KeyEvent.VK_L);
        itemEliminar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        itemEliminar.addActionListener(e->JOptionPane.showMessageDialog(this, "Eliminar"));
                  
        
        menuGestion.add(itemAgregar);
        menuGestion.add(itemEditar);
        menuGestion.add(itemEliminar);
        
        // Se añaden los submenu al menú principal
        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);
        
        return menuBar;      
    }
    
    private JToolBar createToolBar(){
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        // Botones para cambiar entre los paneles Usuarios,Libros y Préstamos
        
        JButton botonUsuarios = new JButton("Usuarios");
        botonUsuarios.addActionListener(e-> {
            cardLayout.show(frmPrincipal, "Usuarios");
                });
        toolBar.add(botonUsuarios);
         
        JButton botonLibros = new JButton("Libros");
        botonLibros.addActionListener(e->{
            cardLayout.show(frmPrincipal, "Libros");
                });
        toolBar.add(botonLibros);
        
         
        JButton botonPrestamos = new JButton("Préstamos");
        botonPrestamos.addActionListener(e->{
            cardLayout.show(frmPrincipal, "Préstamos");
                });
        toolBar.add(botonPrestamos);
        
        JButton btnMultas = new JButton("Multas");
        btnMultas.addActionListener(e->{
            cardLayout.show(frmPrincipal, "Multas");
                });
        toolBar.add(btnMultas);
        
        return toolBar;
    }

    // Mostrar la ventana
 public static void main(String[] args) throws UnsupportedLookAndFeelException {
     UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
    SwingUtilities.invokeLater(() -> {
        BibliotecaApp app = new BibliotecaApp();
        app.setVisible(true);
    }); 
}
}
