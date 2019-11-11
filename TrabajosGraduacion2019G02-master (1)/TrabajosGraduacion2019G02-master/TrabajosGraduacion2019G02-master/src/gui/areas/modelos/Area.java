/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.modelos;

import java.util.Objects;

/**
 *
 * @author gabinete
 */
public class Area implements Comparable<Area> {
    String nombre;
    
    public Area(String nombre){
        this.nombre = nombre;
    }
    
    public void setNombre(String nombre){
           this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    @Override
    public String toString(){
        return "Nombre del area: " + nombre.toUpperCase() + "\n";
    }
    
    public void mostrar(){
        System.out.println("Nombre del area : " + nombre.toUpperCase());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Area other = (Area) obj;
        if (!Objects.equals(this.nombre.toLowerCase() , other.nombre.toLowerCase())) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Area o) {
        return this.nombre.compareToIgnoreCase(o.nombre);
    }
       
    
    }
    

