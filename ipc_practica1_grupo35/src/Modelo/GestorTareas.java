/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Vista.Vista;
import java.util.ArrayList;

/**
 *
 * @author Alvaro y Pascual
 */
public class GestorTareas {
    Vista vista;
    private ArrayList<Tarea> lista_tareas;
    /*Inicializamos el GestorTareas con un ArrayList de tareas vacío*/
    public GestorTareas(){
        this.lista_tareas = new ArrayList<>();
    }
    /**
     * Nos permite consultar las tareas que hay en la lista
     * @return Un ArrayList de tareas con todas las que hay en la lista.
     */
    public ArrayList<Tarea> getTareas(){
        return lista_tareas;
    }
    /**
     * Permite consultar si la tarea esta ya en la lista
     * @param tarea a consultar si ya esta en la lista
     * @return true si esta en la lista false si no lo esta 
     * @throws IllegalArgumentException si la tarea es null
     */
    public boolean isRepeated(Tarea tarea){
        if(tarea == null){
            throw new IllegalArgumentException("La tarea no puede ser null");
        }
        return lista_tareas.contains(tarea);
    }
    /**
     * Nos permite annadir una tarea a la lista de tareas
     * @param tarea que queremos annadir a la lista 
     * @throws IllegalArgumentException si la tarea es null
     * @throws IllegalStateException si la tarea esta repetida
     */
    public void addTarea(Tarea tarea){
        if(tarea == null){
            throw new IllegalArgumentException("La tarea no puede ser null");
        }
        if (!isRepeated(tarea)){
            lista_tareas.add(tarea);   
        }else{
            throw new IllegalStateException("No puede annadir tareas repetidas");
        }
        System.out.print(tarea.getNombre());
    }
    /**
     * Permite eliminar una tarea 
     * @param tarea que queremos eliminar de la lista
     * @throws IllegalArgumentException si la tarea es null
     * @throws IllegalStateException si la tarea no se encuentra en la lista 
     */
    public void deleteTarea(Tarea tarea){
        if(tarea == null){
            throw new IllegalArgumentException("La tarea no puede ser null");
        }
        if(lista_tareas.contains(tarea)){
            lista_tareas.remove(tarea);
        }else{
            throw new IllegalStateException("No puede eliminar una tarea que no está en la lista");
        }
    }
    /**
     * Nos permite elimininar una Tarea
     * @param nombreTarea el nombre de la tarea que queremos eliminar 
     * @throws IllegalArgumentException si el nombre de la tarea es null
     */
    public void deleteTarea(String nombreTarea){
        if(nombreTarea == null){
            throw new IllegalArgumentException("el nombre de la tarea no puede ser null");
        }
        for(Tarea tarea : lista_tareas){
            if(tarea.getNombre().equals(nombreTarea)){
                lista_tareas.remove(tarea);
            }
        }
    }
    /**
     * Nos permite retornar una tarea 
     * @param tarea aquella que queremos versi esta en la lista_tareas
     * @return  la tarea si existe
     * @throws IllegalArgumentException si tarea es null
     * @throws IllegalStateException si la tarea no existe en lista_tareas
     */
    public Tarea searchTarea(Tarea tarea){
        if(tarea == null){
            throw new IllegalArgumentException("La tarea a buscar no puede ser null");
        }
        if(lista_tareas.contains(tarea)){
            return tarea;
        }else{
            throw new IllegalStateException("No existe la tarea que quiere buscar");
        }
    }
    /**
     * Permite buscar una tarea con su nombre
     * @param nombreTarea
     * @return la tarea que tiene ese nombre
     * @throws IllegalArgumentException si el nombre de la tarea es null
     */
    public Tarea searchTarea(String nombreTarea){
        if(nombreTarea == null){
            throw new IllegalArgumentException("el nombre de la tarea no puede ser null");
        }
        for(Tarea tarea : lista_tareas){
            if(tarea.getNombre().equals(nombreTarea)){
                return tarea; 
            }
        }
        return null;
    }
    
}
