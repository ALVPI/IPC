/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Modelo.Tarea;
import Modelo.GestorListaTareas;
import Vista.VentanaListaTareas;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
/**
 *
 * @author Pascual Alvaro
 */
public class ControladorListaTarea {
    
    private String listaActual;
    private VentanaListaTareas vista;
    

    public ControladorListaTarea(VentanaListaTareas vista) {
    this.vista = vista;
    this.listaActual = "IPC";
    inicializarTareasPorDefecto();
    conectarVistaConEventos();
    }

    //Inicializamos las tareas en la lista IPC por defecto
    private void inicializarTareasPorDefecto() {
        // Si ya existe la lista, no volvemos a insertar las tareas por defecto que sino se buggea
        ArrayList<Tarea> ipcExistente = GestorListaTareas.obtenerTareas("IPC");
        if (ipcExistente != null && !ipcExistente.isEmpty()) {
            return;
        }
        ArrayList<Tarea> ipc = new ArrayList<>();

        ipc.add(new Tarea("TE 1", "Realizar un análisis de una aplicación.", crearFecha(5,3,2025), "Alta", false, "50"));
        ipc.add(new Tarea("Lectura", "Leer un artículo sobre el uso de deshacer para el tratamiento de errores.", crearFecha(11,3,2025), "Baja", true, "100"));
        ipc.add(new Tarea("Boceto", "Realizar un boceto de la práctica 2.", crearFecha(30, 3, 2025), "Alta", false, "25"));
        ipc.add(new Tarea("TE 2", "Realizar una aplicación web.", crearFecha(28, 4, 2025), "Media", true, "100"));

        GestorListaTareas.crearListaSiNoExiste("IPC");
        for (Tarea t : ipc) {
            GestorListaTareas.annadirTarea("IPC", t);
        }

        listaActual = "IPC";
    }
    
    /**
     * Permite obtener la informacion de las tareas pendientes
     * @param nombreLista la lista sobre la que queremos saber si las tareas estan pendientes o no
     * @return  unn ArrayList de tareas de esa lista
     */
    public ArrayList<Tarea> obtenerTareasPendientes(String nombreLista) {
        return GestorListaTareas.obtenerTareas(nombreLista).stream()
               .filter(t -> !t.getEstadoTarea())
               .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Permite obtener el numero de tareas completadas de una lista
     * @param nombreLista la lista de la que queremos sacar las tareas completadas
     * @return  un entero con el numero de tareas completadas
     */
    public int contarCompletadas(String nombreLista) {
        return (int) GestorListaTareas.obtenerTareas(nombreLista).stream()
               .filter(Tarea::getEstadoTarea)
               .count();
    }

    /**
     * Permite calcular los dias restastenes para terminar esa tarea
     * @param tarea objeto del que queremos saber los dias restantes
     * @return un entero con lso días faltantes
     */
    public int calcularDiasRestantes(Tarea tarea) {
        long diferenciaEnMiliSegundos = tarea.getFecha().getTime()- new Date().getTime();
        //debemos convertir esa diferencia de ms a dias
        return (int) diferenciaEnMiliSegundos /(1000 * 60 * 60 * 24);
    }
    /**
     * Permite marcar como completada una tarea
     * @param nombreLista la lista de tareas donde se encuentra la tarea
     * @param nombreTarea la tarea a completar
     */
    public void completarTarea(String nombreLista, String nombreTarea) {
    ArrayList<Tarea> tareas = GestorListaTareas.obtenerTareas(nombreLista);
        for (Tarea t : tareas) {
            if (t.getNombre().equalsIgnoreCase(nombreTarea)) {
                t.setIsCompletado();
                break;
            }
        }
    }
    
    
    /**
     * Permite generar una nueva lista de tareas
     * @param nombre de la nueva lista de tareas
     */
    public void crearNuevaLista(String nombre) {
         GestorListaTareas.crearListaSiNoExiste(nombre);
    }

    /**
     * Resmen de las tareas que hay en una lista de manera visual
     * @return un String con todas las tareas de la lista
     */
    public String generarResumenListas() {
        StringBuilder sb = new StringBuilder();
        for (String nombreLista : GestorListaTareas.obtenerNombresListas()) {
            sb.append("Lista: ").append(nombreLista).append("\n");
            for (Tarea t : GestorListaTareas.obtenerTareas(nombreLista)) {
                sb.append(" - ").append(t.getNombre()).append("\n");
            }
        }
        return sb.toString();
    }
    //Para poder meter la fecha en un lenguaje normal
    private Date crearFecha(int dia, int mes, int anno) {
        Calendar cal = Calendar.getInstance();
        cal.set(anno, mes, dia);
        return cal.getTime();
    }
  private void conectarVistaConEventos() {
        // Evento: Al cambiar la selección del combo de listas.
        vista.getComboSeleccionarLista().addActionListener(e -> {
            listaActual = (String) vista.getComboSeleccionarLista().getSelectedItem();
            vista.actualizarVistaLista(listaActual);
        });

        // Botón "Completar"
        vista.getBotonCompletar().addActionListener(e -> {
            String seleccion = vista.getTareaSeleccionada();
            if (seleccion != null && !seleccion.isEmpty()) {
                completarTarea(listaActual, seleccion);
                vista.actualizarVistaLista(listaActual);
            }
        });

        // Botón "Borrar"
        vista.getBotonBorrar().addActionListener(e -> {
            String seleccion = vista.getTareaSeleccionada();
            if (seleccion != null && !seleccion.isEmpty()) {
                eliminarTarea(listaActual, seleccion);
                vista.actualizarVistaLista(listaActual);
            }
        });

        // Buscar tarea por nombre (Enter en Buscador)
        vista.getBuscador().addActionListener(e -> {
            String texto = vista.getBuscador().getText().trim();
            ArrayList<Tarea> tareas = obtenerTareasPendientes(listaActual);

            vista.getListaTareas().removeAll();
            boolean encontrado = false;

            for (Tarea t : tareas) {
                if (t.getNombre().equalsIgnoreCase(texto)) {
                    int dias = calcularDiasRestantes(t);
                    vista.getListaTareas().add(t.getNombre() + " (Faltan " + dias + " días)");
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                javax.swing.JOptionPane.showMessageDialog(vista, "Tarea no encontrada.");
                vista.actualizarVistaLista(listaActual);
            }
        });

        // Selección en lista → actualizar barra de días restantes
        vista.getListaTareas().addItemListener(e -> {
            String seleccion = vista.getTareaSeleccionada();
            if (seleccion != null && !seleccion.isEmpty()) {
                Tarea t = buscarTareaPorNombre(listaActual, seleccion);
                if (t != null) {
                    int dias = calcularDiasRestantes(t);
                    vista.getBarraDiasRestantes().setMaximum(30); // por ejemplo
                    vista.getBarraDiasRestantes().setValue(dias);
                    vista.getBarraDiasRestantes().setString("Faltan " + dias + " días");
                    vista.getBarraDiasRestantes().setStringPainted(true);
                }
            }
        });
    }


   
    private Tarea buscarTareaPorNombre(String lista, String nombreTarea) {
        for (Tarea t : obtenerTareasLista(lista)) {
            // Comprobamos si el nombre coincide directamente
            if (t.getNombre().equalsIgnoreCase(nombreTarea)) {
                return t;
            }
            // Si en la lista se está mostrando el nombre seguido del sufijo, lo extraemos.
            if (nombreTarea.startsWith(t.getNombre() + " (")) {
                return t;
            }
        }
        return null;
    }
    public void annadirTarea(String nombreLista, Tarea tarea) {
        GestorListaTareas.annadirTarea(nombreLista, tarea);
    }
    /**
     * Permite teneruna lista con las tarreas de una lista
     * @param nombreLista la lista de la que queremos sacar las tareas
     * @return  una nueva lista con las tareas de esa lista 
     */
    public ArrayList<Tarea> obtenerTareasLista(String nombreLista) {
        return GestorListaTareas.obtenerTareas(nombreLista);
    }
    /**
     * Permite establecer un nombre a las listas
     * @return  la lista de tareas con el nombre 
     */
    public Set<String> obtenerNombresListas() {
        return GestorListaTareas.obtenerNombresListas();
    }
    /**
     * Permite aliminar una tarea de la lista
     * @param nombreLista La lista donde se encuentra la tarea
     * @param nombreTarea La tarea a eliminar
     */
    public void eliminarTarea(String nombreLista, String nombreTarea) {
        ArrayList<Tarea> tareas = GestorListaTareas.obtenerTareas(nombreLista);
        tareas.removeIf(t -> t.getNombre().equals(nombreTarea));
    }
    public void setListaActual(String nombreLista) {
        this.listaActual = nombreLista;
    }




}
