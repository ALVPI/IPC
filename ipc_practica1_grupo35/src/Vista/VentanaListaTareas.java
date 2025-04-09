/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
import Modelo.Tarea;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.Date;

/**
 *
 * @author alvpi
 */
public class VentanaListaTareas extends javax.swing.JFrame {
    //Variables privadas necesarias para que funcione el sistema 
    private ControladorListaTarea controlador;
    private String nombreListaActual = "IPC"; //nombre por defecto 

    /**
     * Creates new form EditarListaTareas
     */
    public VentanaListaTareas() {   
        
       
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Evita que al cerrar la pestanna se cierre toda la aplicacion

        //Boton de volver
        Volver.setLabel("Volver");
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });
        //Para el buscador
        Buscador.setText("Buscar tarea...");
        Buscador.setForeground(java.awt.Color.GRAY);
           
        Buscador.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            //Cuando esta escribiendo 
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (Buscador.getText().equals("Buscar tarea...")) {
                    Buscador.setText("");
                    Buscador.setForeground(java.awt.Color.BLACK);
                }
            }
               //Cuando no esta escribiendo 
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (Buscador.getText().trim().isEmpty()) {
                    Buscador.setText("Buscar tarea...");
                    Buscador.setForeground(java.awt.Color.GRAY);
                }
            }
        });
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Right = new javax.swing.JPanel();
        SeleccionarLista = new javax.swing.JComboBox<>();
        list1 = new java.awt.List();
        Completadas = new javax.swing.JProgressBar();
        label1 = new java.awt.Label();
        jProgressBar1 = new javax.swing.JProgressBar();
        label2 = new java.awt.Label();
        Completar = new java.awt.Button();
        Borrar = new java.awt.Button();
        Left = new javax.swing.JPanel();
        Buscador = new java.awt.TextField();
        ListaGeneral = new java.awt.TextArea();
        MostrarFechaActual = new com.toedter.calendar.JDateChooser();
        Gráfico = new javax.swing.JPanel();
        CompletadoTotal = new javax.swing.JProgressBar();
        label3 = new java.awt.Label();
        Volver = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SeleccionarLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        label1.setText("Tareas de la lista completadas"
        );

        label2.setText("Días hasta la fecha límite");

        Completar.setLabel("Completar"
        );

        Borrar.setLabel("Eliminar");

        javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
        Right.setLayout(RightLayout);
        RightLayout.setHorizontalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SeleccionarLista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(RightLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightLayout.createSequentialGroup()
                        .addComponent(Completar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(RightLayout.createSequentialGroup()
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RightLayout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Completadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
        );
        RightLayout.setVerticalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(SeleccionarLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Completadas, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Completar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        getContentPane().add(Right, java.awt.BorderLayout.LINE_END);

        Buscador.setText("Buscar tarea....");

        label3.setText("% Compleatado Total"
        );

        javax.swing.GroupLayout GráficoLayout = new javax.swing.GroupLayout(Gráfico);
        Gráfico.setLayout(GráficoLayout);
        GráficoLayout.setHorizontalGroup(
            GráficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GráficoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(GráficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CompletadoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        GráficoLayout.setVerticalGroup(
            GráficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GráficoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(CompletadoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        Volver.setLabel("Volver");

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Buscador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ListaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LeftLayout.createSequentialGroup()
                                .addComponent(MostrarFechaActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(LeftLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)))
                        .addComponent(Gráfico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Buscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Gráfico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(MostrarFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        getContentPane().add(Left, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaListaTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaListaTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaListaTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaListaTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               VentanaListaTareas ventana = new VentanaListaTareas();
                ControladorListaTarea controlador = new ControladorListaTarea(ventana);
                ventana.setControlador(controlador);
                ventana.setVisible(true);
            }
        });
    }
    /**
     * Permite establecer el controlador que vamos a emplear
     * @param controlador 
     */
    public void setControlador(ControladorListaTarea controlador) {
        this.controlador = controlador;
        inicializarVista(); 
    }
    /**
     * Permite inicializar la gui 
     */
    public void inicializarVista() {    
        //VAmos por partes Pascual
        //Primero debemos mostrar las listas en el combo
        SeleccionarLista.removeAllItems();
        for (String nombre : controlador.obtenerNombresListas()) {
            SeleccionarLista.addItem(nombre);
        }
        // Ahora moastramos tareas pendientes de la primera lista ("IPC")
        String listaSeleccionada = "IPC"; //La por defecto
        actualizarVistaLista("IPC");
        list1.removeAll();
        for (Tarea t : controlador.obtenerTareasLista(listaSeleccionada)) {
            long dias = controlador.calcularDiasRestantes(t);
            list1.add(t.getNombre() + " (Faltan " + dias + " días)");
        }
        // Movida de la barra de cuan completas estan las tareas de la lista
        int total = controlador.obtenerTareasLista(listaSeleccionada).size();
        int completadas = controlador.contarCompletadas(listaSeleccionada);
        Completadas.setMaximum(total);
        Completadas.setValue(completadas);
        Completadas.setStringPainted(true);
        Completadas.setString(completadas + " de " + total + " completadas");

        // Mostrar todas las listas en la zona de texto
        ListaGeneral.setText(controlador.generarResumenListas());

        // Setteamos fecha inical 
        MostrarFechaActual.setDate(new Date());
        // Calculamos cuan completas estan todas las tarreas en general 
        int totalTareas = 0;
        int completadasTareas = 0;

        for (String nombreLista : controlador.obtenerNombresListas()) {
            ArrayList<Tarea> tareas = controlador.obtenerTareasLista(nombreLista);
            totalTareas += tareas.size();
            completadasTareas += (int) tareas.stream().filter(Tarea::getEstadoTarea).count();
        }

        CompletadoTotal.setMaximum(totalTareas);
        CompletadoTotal.setValue(completadasTareas);
        CompletadoTotal.setStringPainted(true);
        CompletadoTotal.setString(completadasTareas + " de " + totalTareas + " completadas");

    }
    /**
     * Permite consultar la lista de tareas
     * @return una lista con todas las tareas
     */
    public java.awt.List getListaTareas() {
        return list1;
    }
    /**
     * Permite consultar el buscador
     * @return  la casilla de texto del buscador
     */
    public java.awt.TextField getBuscador() {
        return Buscador;
    }
    /**
     * Permite consultar los dias que le quedan a la tarea en una barra
     * @return la barra que indica el progreso 
     */
    public javax.swing.JProgressBar getBarraDiasRestantes() {
        return jProgressBar1;
    }
    /**
     * Permite seleccionar uan lista con el menu de arriba a la izquierda (desplegable hacia abajo)
     * @return la lista seleccionada por el usuario.
     */
    public javax.swing.JComboBox<String> getComboSeleccionarLista() {
        return SeleccionarLista;
    }
    /**
     * Permite consultar el boton completar
     * @return el boton que permite compeltar tareas
     */
    public java.awt.Button getBotonCompletar() {
        return Completar;
    }
   /**
     * Permite consultar el boton borrar
     * @return el boton que permite eliminar tareas
     */
    public java.awt.Button getBotonBorrar() {
        return Borrar;
    }
    /**
     * Permite consultar la tarea seleccionada
     * @return El nombre de la tarea como string
     */
    public String getTareaSeleccionada() {
        String seleccion = list1.getSelectedItem();
            if (seleccion != null && seleccion.contains(" (")) {
                return seleccion.split(" \\(")[0].trim(); // extrae solo el nombre
            }
            return seleccion;
    }
    /**
     * Permite actualizar la lista que estamos viendo 
     * @param nombreLista la lista a modificar
     */
    public void actualizarVistaLista(String nombreLista) {
        list1.removeAll();
        for (Tarea t : controlador.obtenerTareasLista(nombreLista)) {
            int dias = controlador.calcularDiasRestantes(t);
            list1.add(t.getNombre() + " (Faltan " + dias + " días)");
        }

        int total = controlador.obtenerTareasLista(nombreLista).size();
        int completadas = controlador.contarCompletadas(nombreLista);
        Completadas.setMaximum(total);
        Completadas.setValue(completadas);
        Completadas.setStringPainted(true);
        Completadas.setString(completadas + " de " + total + " completadas");

        ListaGeneral.setText(controlador.generarResumenListas());
    }
    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose(); // cierra la ventana actual
        new MenuInicial().setVisible(true); // abre el menú principal
    }
    /**
     * Permite establecer el nombre de la lista 
     * @param nombreLista  String con el nombre de la lista 
     */
    public void setNombreLista(String nombreLista) {
    this.nombreListaActual = nombreLista;
    }
    @Override
    
    public void setVisible(boolean b) {
        if (b) {
            inicializarVista();   //Fuerzo el reinicio de la vista
        }
        super.setVisible(b);
    }
    /**
     * Permite mostrar las tareas pendientes
     * @param nombreLista 
     */
    public void actualizarVistaSoloPendientes(String nombreLista) {
        list1.removeAll();
        list1.clear();

        for (Tarea t : controlador.obtenerTareasPendientes(nombreLista)) {
            int dias = controlador.calcularDiasRestantes(t);
            list1.add(t.getNombre() + " (Faltan " + dias + " días)");
        }
    }






    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Borrar;
    private java.awt.TextField Buscador;
    private javax.swing.JProgressBar Completadas;
    private javax.swing.JProgressBar CompletadoTotal;
    private java.awt.Button Completar;
    private javax.swing.JPanel Gráfico;
    private javax.swing.JPanel Left;
    private java.awt.TextArea ListaGeneral;
    private com.toedter.calendar.JDateChooser MostrarFechaActual;
    private javax.swing.JPanel Right;
    private javax.swing.JComboBox<String> SeleccionarLista;
    private java.awt.Button Volver;
    private javax.swing.JProgressBar jProgressBar1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
