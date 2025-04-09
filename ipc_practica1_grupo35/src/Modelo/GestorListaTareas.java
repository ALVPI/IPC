/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.*;
/**
 *
 * @author Alvaro y Pascual
 */
public class GestorListaTareas {
    public static Map<String, ArrayList<Tarea>> listasTareas = new HashMap<>();

    static {
        // Lista por defecto
        listasTareas.put("IPC", new ArrayList<>());
    }
    /**
     * Permite crear una lista si estas no existe previamente solo tenemos por dfecto creada ipc
     * @param nombre String con el nombre de la lista
     */
    public static void crearListaSiNoExiste(String nombre) {
        listasTareas.putIfAbsent(nombre, new ArrayList<>());
    }
    /**
     * Permite annadir una tarea a una lista ya existente
     * @param nombreLista la lista donde se va a introducir la tarea (IPC si el user no dice nada)
     * @param tarea  Tarea a annadir
     */
    public static void annadirTarea(String nombreLista, Tarea tarea) {
        crearListaSiNoExiste(nombreLista);
        listasTareas.get(nombreLista).add(tarea);
    }
    /**
     * Permite obtener las tareas de una lista
     * @param nombreLista String origen de sacar las tareas
     * @return Un ArrayList de tareas con todas las de la lista
     */
    public static ArrayList<Tarea> obtenerTareas(String nombreLista) {
        return listasTareas.getOrDefault(nombreLista, new ArrayList<>());
    }
    /**
     * Permite obtener el nombre de la lista
     * @return SetString con el nombre
     */
    public static Set<String> obtenerNombresListas() {
        return listasTareas.keySet();
    }
    /**
     * Permite crear una nueva lista 
     * @param nombre El nombre de la lista
     */
    public void crearNuevaLista(String nombre) {
        GestorListaTareas.crearListaSiNoExiste(nombre);
    }
    /**
     * Permite marcar como completadas tareas
     * @param nombreLista el String con el nombre de la lista donde la vamos a meter
     * @param nombreTarea el string con la tarea que vamos a marcar como completadas
     */
    public static void marcarTareaComoCompletada(String nombreLista, String nombreTarea) {
    ArrayList<Tarea> tareas = listasTareas.get(nombreLista);
    if (tareas != null) {
        for (Tarea t : tareas) {
            if (t.getNombre().equals(nombreTarea)) {
                t.setEstadoTarea(true);
            }
        }
    }
}


}
