/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.*;
/**
 *
 * @author alvpi
 */
public class GestorListaTareas {
    public static Map<String, ArrayList<Tarea>> listasTareas = new HashMap<>();

    static {
        // Lista por defecto
        listasTareas.put("IPC", new ArrayList<>());
    }

    public static void crearListaSiNoExiste(String nombre) {
        listasTareas.putIfAbsent(nombre, new ArrayList<>());
    }

    public static void annadirTarea(String nombreLista, Tarea tarea) {
        crearListaSiNoExiste(nombreLista);
        listasTareas.get(nombreLista).add(tarea);
    }

    public static ArrayList<Tarea> obtenerTareas(String nombreLista) {
        return listasTareas.getOrDefault(nombreLista, new ArrayList<>());
    }

    public static Set<String> obtenerNombresListas() {
        return listasTareas.keySet();
    }
    public void crearNuevaLista(String nombre) {
        GestorListaTareas.crearListaSiNoExiste(nombre);
    }
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
