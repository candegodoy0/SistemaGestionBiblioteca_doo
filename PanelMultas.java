package com.godoy.main.vistas;
import com.godoy.entities.GestorLibros;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.godoy.entities.Multa;
import java.io.IOException;
import java.util.List;

public class PanelMultas extends JPanel{
    private JTextField txtMonto;
    private GestorLibros gestorLibros;
    private JComboBox<String> cmbEstado;
    private JList <Multa>listaMultas;
    private DefaultListModel <Multa> mdlListaMultas;
    private JButton btnRegistrarMulta, btnPagarMulta, btnVerMultas, btnGuardarMultasTxt, btnCargarMultasTxt;
    
    public PanelMultas(){
        
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension (800, 600));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);// Márgenes entre los componentes
        
        //Etiquetas y campos para escribir
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Monto de la multa:"), gbc);
        ((JLabel)getComponent(0)).setForeground(Color.WHITE);
        
        txtMonto = new JTextField(20);
        txtMonto.setForeground(Color.BLACK);
        txtMonto.setBackground(Color.WHITE); 
        gbc.gridx = 1;
        add(txtMonto, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Estado de la multa:"), gbc);
        ((JLabel)getComponent(2)).setForeground(Color.WHITE);
        
        String[]estados = {"Pendiente", "Pagada"};
                cmbEstado = new JComboBox <>(estados);
        cmbEstado.setBackground(Color.WHITE);
       cmbEstado.setForeground(Color.BLACK);
        gbc.gridx = 1;
        add(cmbEstado, gbc);
        
        btnRegistrarMulta = new JButton("Registrar multa");
        btnRegistrarMulta.setBackground(Color.DARK_GRAY);
       btnRegistrarMulta.setForeground(Color.WHITE);
        btnRegistrarMulta.addActionListener(e->registrarMulta());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnRegistrarMulta, gbc);
        
            // Lista para mostrar las multas registradas
    mdlListaMultas = new DefaultListModel<>();
    listaMultas = new JList(mdlListaMultas);
    listaMultas.setBackground(Color.WHITE);
       listaMultas.setForeground(Color.BLACK);
    JScrollPane scrollPane = new JScrollPane(listaMultas);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.BOTH;
    add(scrollPane, gbc);
    
    //Boton para pagar la multa
      btnPagarMulta = new JButton("Pagar multa");
      btnPagarMulta.setBackground(Color.DARK_GRAY);
       btnPagarMulta.setForeground(Color.WHITE);
        btnPagarMulta.addActionListener(e->pagarMulta());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(btnPagarMulta, gbc);
              
 //Boton para guardar multas
 btnGuardarMultasTxt = new JButton("Guardar multas");
       btnGuardarMultasTxt.setBackground(Color.DARK_GRAY);
       btnGuardarMultasTxt.setForeground(Color.WHITE);
 btnGuardarMultasTxt.addActionListener(e-> 
     guardarMultasTxt());
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(btnGuardarMultasTxt, gbc);
 
 //Botón para cargar multas
 btnCargarMultasTxt = new JButton("Cargar multas");
       btnCargarMultasTxt.setBackground(Color.DARK_GRAY);
       btnCargarMultasTxt.setForeground(Color.WHITE);
 btnCargarMultasTxt.addActionListener((ActionEvent e )-> { 
     cargarMultas();
      });
  gbc.gridx = 0;
  gbc.gridy = 6;
  add(btnCargarMultasTxt, gbc);
 
  //Botón para ver las multas
 btnVerMultas = new JButton("Ver multas");
       btnVerMultas.setBackground(Color.DARK_GRAY);
       btnVerMultas.setForeground(Color.WHITE);
 btnVerMultas.addActionListener((ActionEvent e )-> { cargarMultas();
});

  gbc.gridx = 1;
  gbc.gridy = 6;
  add(btnVerMultas, gbc);
 }
 
        
 //Metodo para registrar una nueva multa
 private void registrarMulta(){
     try{
         float monto = Float.parseFloat(txtMonto.getText());
         String estado = (String) cmbEstado.getSelectedItem();
         
         //Se valida el monto
         if(monto <= 0){
             JOptionPane.showMessageDialog(this, "El monto debe ser mayor que 0");
             return;
         }
         
         //Se crea la nueva multa y se agrega a la lista
         Multa multa = new Multa(estado, monto);
         multa.registrarMulta();
         
         //Se actualiza la lisat de multas
         cargarMultas();
         
         JOptionPane.showMessageDialog(this, "Multa registrada correctamente");
     }catch(NumberFormatException e){
         JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido");
     }
     }

 
 // Metodo para pagar una multa
 private void pagarMulta(){
     Multa multaSeleccionada = listaMultas.getSelectedValue();
     if(multaSeleccionada != null && multaSeleccionada.getEstado().equals("Pendiente")){

//Se paga la multa
multaSeleccionada.registrarPago();
cargarMultas(); // Se actualiza la lista para reflejar el cambio
JOptionPane.showMessageDialog(this, "La multa fue pagada");
     }else{
         JOptionPane.showMessageDialog(this, "No se selecciono una multa pendiente");
         }
     }     

//Metodo para cargar las multas desde la lista de la clase Multa
   private void cargarMultas(){
         mdlListaMultas.clear();
         for(Multa multa : Multa.obtenerListaMultas()){
             mdlListaMultas.addElement(multa);
         }
         }

        //Metodo para guardar multas en Txt
    private void guardarMultasTxt(){
         try{
String archivo = "multas.txt";
Multa multa = new Multa("--", 0);
multa.guardarMultasEnTxt(archivo);
JOptionPane.showMessageDialog(this, "Multas guardadas correctamente");
}catch(IOException e){
JOptionPane.showMessageDialog(this, "Error al guardar las multas: " + e.getMessage());
}
    }

   //Metodo para cargar multas en Txt
   private void cargarMultasTxt(){
            try{
String archivo = "multas.txt";
Multa multa = new Multa("--", 0);
multa.cargarDatosMultas(archivo);
cargarMultas();
JOptionPane.showMessageDialog(this, "Multas cargadas correctamente");
}catch(IOException e){
JOptionPane.showMessageDialog(this, "Error al cargar las multas: " + e.getMessage());
}
     }
     //Metodo para mostrar las multas registradas
     private void mostrarMultasRegistradas(){
         System.out.println("Multas registradas:");
         for(Multa multa : Multa.listaMultas){
             System.out.println("Estado: " + multa.getEstado() + ", Monto: " + multa.getMonto());
         }
     }
     }
 
 
 
        
    
    

