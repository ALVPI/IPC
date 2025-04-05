/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Date;
/**
 *
 * @author alvpi y Pascual
 */
public class Tarea {
    private String nombre;
    private String descripcion;
    private Date fecha;
    private String prioridad;
    private boolean isCompletada;
    private String porcentajeCompletado;
    public Tarea(String nombre, String descripcion, Date fecha, String prioridad, boolean isCompletada, String porcentajeCompletado){
        setNombre(nombre);
        setDescripcion(descripcion);
        this.fecha = fecha;
        setPrioridad(prioridad);
        this.isCompletada = isCompletada;
        setProrcentajeTareaHecha(porcentajeCompletado);
    }
    /**
     * Nos permite consultar el nombre de la tarea
     * @return String con el nombre de la tarea
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * Nos permite consultar la descripción de la tarea
     * @return String con la descripción de la tarea
     */
    public String getDescripcion(){
        return descripcion;
    }
    /**
     * Nos permite consultar la fecha de la tarea
     * @return Date con la fecha de la tarea
     */
    public Date getFecha(){
        return fecha;
    }
    /**
     * Nos permite obtener la prioridad de la tarea
     * @return String con la prioridad de la tarea
     */
    public String getPrioridad(){
        return prioridad;
    }
    /**
     * Nos permite consultar si la tarea ha sido completada o no
     * @return boolean true si ha sido completado false si no 
     */
    public boolean getEstadoTarea(){
        return isCompletada;
    }
    /**
     * Nos permite consultar el porcentaje de tarea que está hecho 
     * @return double con el porcentaje
     */
    public String getPorcentajeCompletado(){
        return porcentajeCompletado;
    }
    
    public void setNombre(String nombre){
          if(nombre == null){
              throw new IllegalArgumentException("El nombre de la tarea no puede estar en blanco");
          }
          if(nombre.length()<1 || nombre.length()>10){
              throw new IllegalArgumentException("El nombre de la tarea tiene que tener entre 1 y 10 caracteres");
          }
          this.nombre = nombre;
    }
    public void setDescripcion(String descripcion){
        if(descripcion == null){
            throw new IllegalArgumentException("La descripción de la tarea no puede estar en blanco");
        }
        if(descripcion.length()<0 || descripcion.length()>100){
            throw new IllegalArgumentException("La descripción de la tarea tiene que tener de 0 a 100 caracteres");
        }
        this.descripcion = descripcion;
    }
    public void setPrioridad(String prioridad){
        if(prioridad == null){
            throw new IllegalArgumentException("La prioridad de la tarea no puede estar en blanco");
        }
        if(prioridad.equals(" ")){
            this.prioridad = "Baja";
        }
        if(!prioridad.toUpperCase().equals("BAJA") && !prioridad.toUpperCase().equals("MEDIA") && !prioridad.toUpperCase().equals("ALTA")){
            throw new IllegalArgumentException("La prioridad de la tarea ha de ser media, alta o baja");
        }
        this.prioridad = prioridad;
    }
    public void setProrcentajeTareaHecha(String porcentajeCompletado){
        this.porcentajeCompletado = porcentajeCompletado;
    }
    public void setIsCompletado(){
        this.isCompletada = true;
    }
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fechaStr = (fecha != null) ? sdf.format(fecha) : "Sin fecha";
        return nombre + " " + fechaStr + " " + (isCompletada ? "Completada" : "Pendiente");
    }

    
   
}
