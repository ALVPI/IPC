/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Modelo.Tarea;
import Modelo.GestorListaTareas;
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
    private ControladorListaTarea controladorListas;

    public Controlador(Vista vista, ControladorListaTarea controladorListas) {
        this.vista = vista;
        this.controladorListas = controladorListas;
        this.vista.setControlador(this);
    }

    public void setTareas() {
        String lista = vista.getNombreListaActual();
        ArrayList<Tarea> tareas = controladorListas.obtenerTareasLista(lista);
        String[] tmp = tareas.stream().map(Object::toString).toArray(String[]::new);
        vista.setTareas(tmp);
    }

    public void viewTarea(String nombre) {
        String lista = vista.getNombreListaActual();
        ArrayList<Tarea> tareas = controladorListas.obtenerTareasLista(lista);
        boolean encontrada = false;

        for (Tarea t : tareas) {
            if (t.getNombre().equals(nombre)) {
                vista.mostrarTarea(t);
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            vista.mostrarToast("⚠ No se ha encontrado la tarea");
        }
    }


    public void prepararEdicion(String nombre) {
        String lista = vista.getNombreListaActual();
        ArrayList<Tarea> tareas = controladorListas.obtenerTareasLista(lista);
        boolean encontrada = false;

        for (Tarea t : tareas) {
            if (t.getNombre().equals(nombre)) {
                vista.prepararEdicion(t);
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            vista.mostrarToast("⚠ No se puede editar, tarea no encontrada");
        }
    }


    public void addTarea() {
        String nombre = vista.getNombreTarea();
        String descripcion = vista.getDescripcionTarea();
        String porcentaje = vista.getPorcentaje();
        String prioridad = vista.getPrioridad();
        Boolean completado = vista.getCompletado();
        Date fecha = vista.getFechaTarea();

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            vista.mostrarToast("⚠ Rellene los campos por favor");
            return;
        }

        String lista = vista.getNombreListaActual(); // aquí ya tienes IPC por defecto
        ArrayList<Tarea> tareas = controladorListas.obtenerTareasLista(lista);

        for (Tarea t : tareas) {
            if (t.getNombre().equals(nombre)) {
                vista.mostrarToast("⚠ Ya existe una tarea con ese nombre");
                return;
            }
        }

        Tarea nueva = new Tarea(nombre, descripcion, fecha, prioridad, completado, porcentaje);
        controladorListas.crearNuevaLista(lista); // Crea la lista solo si no existe
        controladorListas.annadirTarea(lista, nueva);
        vista.mostrarToast("✅ Tarea añadida correctamente");
        controladorListas.setListaActual(lista); // << NUEVO: actualiza referencia interna
        vista.setTareas(controladorListas.obtenerTareasLista(lista)
                      .stream().map(Object::toString).toArray(String[]::new));
        setTareas();
        vista.clear();
    }

    public void deleteTarea(String nombre) {
        String lista = vista.getNombreListaActual();
        controladorListas.eliminarTarea(lista, nombre);
        setTareas();
        vista.mostrarToast("Tarea Eliminada correctamente");
    }
} 
