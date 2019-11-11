/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.modelos;

import gui.interfaces.IGestorAreas;
import gui.trabajos.modelos.GestorTrabajos;
import gui.trabajos.modelos.Trabajo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public class GestorAreas implements IGestorAreas {
    
    private List<Area> areas = new ArrayList<>();
    public static GestorAreas gestor;
    
    private GestorAreas(){ 
    }
    /**
     * Solo crea un gestor de areas si previamente no se creo uno. 
     * @return 
     */
    public static GestorAreas crear(){
        if(gestor == null){
            gestor = new GestorAreas();
        }
        return gestor;
    }

    /**
     * Permite crear un area nueva y agregarla a la lista de Areas siempre y cuando no exista otra igual.
     * El area no puede tener como nombre una cadena vacía o nula. 
     * @param nombre Nombre del area.
     * @return String - dice si se aregó o no el area a la lista. 
     */
    @Override
    public String nuevaArea(String nombre) {
        if((nombre != null) && (!nombre.isEmpty())){
            Area area = new Area(nombre);
            this.areas.add(area);
            return "Se agregó el área a la lista.";
        }
        
        return "No se agregó el area a la lista";
    }
    
    /**
     * Muestra todas las areas creadas. 
     */
    @Override
    public void mostrarAreas() {
        Collections.sort(areas);
        for(Area a : this.areas){
            a.mostrar();
        }
    }
    
    /**
     * Busca si existe un area cuyo nombre coincida con el filtro especificado (nombre). 
     * @param nombre - Busqueda
     * @return Area si la encuentra, null si no la encuentra
     */
    @Override
    public Area dameArea(String nombre) {
        for(Area a : areas){
            if(a.nombre.equalsIgnoreCase(nombre)){
                return a;
            }
        }
        return null;
    }
    
    /**
     * Para buscar si exiten Areas cuyo nombre contenga el filtro especificado. Si el filtro es null, devuelve todas las areas.
     * Devuelve el resultado en una lista, si no hay coincidencias devuelve una lista vacia. 
     * @param nombre
     * @return 
     */
    @Override
    public List<Area> buscarArea(String nombre) {
        if((nombre == null)||(nombre.isEmpty())){
            return this.areas;
        }
        List<Area> coincidencias = new ArrayList<>();
        for(Area a : this.areas){
            if(a.nombre.toLowerCase().contains(nombre.toLowerCase())){
                coincidencias.add(a);
            }
        }
        return coincidencias;
    }
    
    /**
     * Borra un área siempre y cuando no haya trabajos con la misma.
     * @param area
     * @return 
     */    
    @Override
    public String borrarArea(Area area) {
        GestorTrabajos gestor = GestorTrabajos.crear();
        for(Trabajo t: gestor.getTrabajos()){
            if(t.getArea().contains(area)){
                return "No se puede borrar el area ya que esta en el trabajo " + t.getTitulo().toUpperCase();
            }
        }
        if(!this.areas.contains(area)){
            return "El area no se encuentra en la lista.";
        }
        this.areas.remove(area);
        return "El area fue borrada";
    }
    
    
    
}
