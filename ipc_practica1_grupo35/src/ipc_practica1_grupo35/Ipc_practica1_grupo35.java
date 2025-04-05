/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ipc_practica1_grupo35;
import Vista.Vista;
import java.util.*;
/**
 *
 * @author alvpi
 */
public class Ipc_practica1_grupo35 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Para el estilo, he usado nimbus
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Si algo falla, deja el estilo por defecto
        }

        //Controlador controlador = new Controlador();
        Vista vista = new Vista();
        vista.setVisible(true);
    }
    
}
