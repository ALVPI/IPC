/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Modelo.Tarea;
import Modelo.GestorTareas;
import Vista.Vista;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author Alvlope y Pascual
 */
public class Controlador {
    private Vista vista;
    private GestorTareas gestor;
    private String nombreTareaEditando = null;

    /**
     * Permite inicializar un controlador
     * @param vista la informacion que conseguimos de la clase vista
     * @throws IllegalArgumentException  si la vista es null
     */
    public Controlador(Vista vista){
        if(vista == null){
            throw new IllegalArgumentException("Vista no puede ser null");
        }
        this.vista = vista;
        gestor = new GestorTareas();
        loadTareasPorDefecto();
        setTareas();

    }
    /**
     * Permite annadir una tarea cogiendo la informacion de la vista
     */
    public void addTarea() {
        String nombre = vista.getNombreTarea();
        
        String descripcion = vista.getDescripcionTarea();
        String porcentaje = vista.getPorcentaje();
        String prioridad = vista.getPrioridad();
        Boolean completado = vista.getCompletado();
        Date fecha = vista.getFechaTarea();
        if(nombre.isEmpty() || descripcion.isEmpty()){
            vista.mostrarToast("⚠ Rellene los campos por favor ️️");
            return; //con esto cortamos el flujo para que no se añada la tarea
        }
        
        if (nombreTareaEditando != null) {
        Tarea tareaOriginal = gestor.buscarTarea(nombreTareaEditando);
        if (tareaOriginal != null) {
            tareaOriginal.setDescripcion(descripcion);
            tareaOriginal.setPrioridad(prioridad);
            tareaOriginal.setProrcentajeTareaHecha(porcentaje);
            tareaOriginal.setIsCompletado();
            tareaOriginal.setFecha(fecha);
        }
        vista.mostrarToast("📝 Tarea modificada");
        nombreTareaEditando = null;
        } else if (gestor.buscarTarea(nombre) != null) {
            // Ya hay una tarea con ese nombre
            vista.mostrarToast("⚠️️ Ya existe una tarea con ese nombre");
            return; 
        }else{
            Tarea nueva = new Tarea(nombre, descripcion, fecha, prioridad, completado, porcentaje);
            gestor.addTarea(nueva);
            vista.mostrarToast("✅ Tarea añadida correctamente");
        }
        
        setTareas();
        vista.clear();
    }
    /**
     * Permite ver una tarea
     */
    public void viewTarea() {
        //Hay que contemplar que puede ser nulo 
        
        if (vista.getTareaActiva() == null || vista.getTareaActiva().isEmpty()) {
            System.out.println("No hay tarea seleccionada");
            return;
        }

        String[] partes = vista.getTareaActiva().split(" ");
        if (partes.length < 1) {
            System.out.println("Formato de tarea inválido: " + vista.getTareaActiva());
            return;
        }

        String nombre = partes[0];
        Tarea tarea = gestor.buscarTarea(nombre);
        if (tarea != null) {
            vista.setNombre(tarea.getNombre());
            vista.setDes(tarea.getDescripcion());
            vista.setPrio(tarea.getPrioridad());
            vista.mostrarDescripiconTarea();
        } else {
            System.out.println("Tarea no encontrada: " + nombre);
        }
    }

    /**
     * Permite borrar una tarea
     */
    public void deleteTarea() {
        String tareaSeleccionada = vista.getTareaActiva();
        if (tareaSeleccionada != null && !tareaSeleccionada.isEmpty()) {
            String[] partes = tareaSeleccionada.split(" ");
            String nombreTarea = partes[0]; // El nombre de la tarea
            gestor.deleteTarea(nombreTarea);
            setTareas(); // Refresca la lista de tareas despues de la eliminacion
            vista.mostrarToast("🗑 Tarea Eliminada ");
        } else {
            System.out.println("No se ha seleccionado una tarea para eliminar.");
        }
    }
    /**
     * Permite annadir una tarea a la lista de tareas
     */
    public void setTareas() {
        ArrayList<Tarea> tareas = gestor.getTareas();
        String[] tmp = tareas.stream()
                              .map(Object::toString)
                              .toArray(String[]::new);
        vista.setTareas(tmp);

    }
    /**
     * 
     */
    public void prepararEdicion() {
    String seleccion = vista.getTareaActiva();
        if (seleccion != null && !seleccion.isEmpty()) {
            String nombre = seleccion.split(" ")[0]; // Asumes que nombre es la primera parte
            Tarea tarea = gestor.buscarTarea(nombre);
            vista.setNombreEdicion(tarea.getNombre());
            vista.setDescripcionEdicion(tarea.getDescripcion());
            vista.setPrioridadEdicion(tarea.getPrioridad());
            vista.setPorcentajeEdicion(tarea.getPorcentajeCompletado());
            vista.setCompletadoEdicion(tarea.getEstadoTarea());
            nombreTareaEditando = nombre;
        }
    }
    private void loadTareasPorDefecto() {
        Tarea t1 = new Tarea("TE 1", "Realizar un a", new Date(05/03/2025), "Alta", false, "50");
        Tarea t2 = new Tarea("Lectura", "Leer un artículo sobre el uso de deshacer para el tratamiento de errores.", new Date(11/03/2025), "Baja", true, "100");
        Tarea t3 = new Tarea("Boceto", "Realizar bocetos de la práctica 2", new Date(30/03/2025), "Alta", false, "25");
        Tarea t4 = new Tarea("TE 2", "Realizar una aplicación web", new Date(28/04/2025), "Media", true, "100");
        gestor.addTarea(t1);
        gestor.addTarea(t2);
        gestor.addTarea(t3);
        gestor.addTarea(t4);
    }
    
    

}   
