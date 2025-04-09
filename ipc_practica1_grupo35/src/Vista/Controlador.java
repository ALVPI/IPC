/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Modelo.Tarea;
import Modelo.GestorListaTareas;
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
    private ControladorListaTarea controladorListas;

    public Controlador(Vista vista, ControladorListaTarea controladorListas) {
        this.vista = vista;
        this.controladorListas = controladorListas;
        this.vista.setControlador(this);
    }

    public void setTareas() {
        String lista = vista.getNombreListaActual();
        ArrayList<Tarea> tareas = controladorListas.obtenerTareasLista(lista);
        vista.setTareas(tareas);
    }

    public void viewTarea(String nombre) {
        String lista = vista.getNombreListaActual();
        ArrayList<Tarea> tareas = controladorListas.obtenerTareasLista(lista);

        // Limpiar el nombre si viene con formato adicional
        String nombreLimpio = nombre;
        int parentesis = nombre.indexOf(" (");
        if (parentesis != -1) {
            nombreLimpio = nombre.substring(0, parentesis).trim();
        }

        for (Tarea t : tareas) {
            if (t.getNombre().trim().equalsIgnoreCase(nombreLimpio)) {
                vista.showTarea(t);  
                return;
            }
        }

        vista.showToast("⚠ No se pudo mostrar la tarea: " + nombre);
    }



    public void prepareEdition(String nombre) {
        String lista = vista.getNombreListaActual();
        ArrayList<Tarea> tareas = controladorListas.obtenerTareasLista(lista);
        boolean encontrada = false;

        for (Tarea t : tareas) {
            if (t.getNombre().equals(nombre)) {
                vista.prepareEdicion(t);
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            vista.showToast("⚠ No se puede editar, tarea no encontrada");
        }
    }


    public void addTarea() {
        String nombre = vista.getNombreTarea();
        String descripcion = vista.getDescripcionTarea();
        String prioridad = vista.getPrioridad();
        boolean completado = vista.getCompletado();
        String porcentaje = vista.getPorcentaje();
        Date fecha = vista.getFechaTarea();
        String lista = vista.getNombreListaActual();

        // Obtenemos la lista actual
        ArrayList<Tarea> tareas = controladorListas.obtenerTareasLista(lista);
        if (tareas == null) tareas = new ArrayList<>();

        // Detectamos si estamos editando una tarea
        String nombreOriginal = vista.getNombreOriginalEdicion();
        boolean enEdicion = (nombreOriginal != null);

        // Si estamos editando, eliminamos la anterior
        if (enEdicion) {
            controladorListas.eliminarTarea(lista, nombreOriginal);
        }

        // Verificamos si ya existe otra tarea con ese nombre
        for (Tarea t : tareas) {
            if (t.getNombre().equals(nombre) && (!enEdicion || !nombre.equals(nombreOriginal))) {
                vista.showToast("⚠ Ya existe una tarea con ese nombre");
                return;
            }
        }

        // Creamos la nueva tarea
        Tarea nueva = new Tarea(nombre, descripcion, fecha, prioridad, completado, porcentaje);
        controladorListas.crearNuevaLista(lista); // solo la crea si no existe
        controladorListas.annadirTarea(lista, nueva);

        // Feedback al usuario
        if (enEdicion) {
            vista.showToast("✅ Tarea editada correctamente");
        } else {
            vista.showToast("✅ Tarea añadida correctamente");
        }

        // Actualizamos la lista visual
        vista.setTareas(controladorListas.obtenerTareasLista(lista));
        setTareas(); // si usas esto para gráficos o stats, lo dejas

        // Limpiamos los campos y modo edición
        vista.clear();
    }


    public void deleteTarea(String nombre) {
        String lista = vista.getNombreListaActual();
        controladorListas.eliminarTarea(lista, nombre);
        setTareas();
        vista.showToast("Tarea Eliminada correctamente");
    }
} 
