/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.seminarios.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Rosalba Caldez
 */
public class Seminario implements Comparable<Seminario> {
    private LocalDate fechaExposicion;
    private String observaciones;
    private NotaAprobacion nota;
    
    public Seminario(LocalDate fechaExposicion){
        this.fechaExposicion = fechaExposicion;
    }

    public Seminario(LocalDate fechaExposicion, NotaAprobacion nota, String obs){
        this(fechaExposicion);
        this.nota = nota;
        this.observaciones = obs;
    }
    
    public void setFechaExposicion(LocalDate fe){
        this.fechaExposicion = fe;
    }
    
    public LocalDate getFechaExposicion(){
        return this.fechaExposicion;
    }
    
    public void setObservaciones(String obs){
        this.observaciones = obs;
    }
    
    public String getObservaciones(){
        return this.observaciones;
    }
    
    public void setNota(NotaAprobacion nota){
        this.nota = nota;
    }
    
    public NotaAprobacion getNota(){
        return this.nota;
    }
    
    public void mostrar(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        System.out.print("Seminario rendido el: " + formatter.format(fechaExposicion) + " Nota: " + nota + " Observaciones: " + observaciones);
        
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return ("Seminario rendido el: " + formatter.format(fechaExposicion) + " Nota: " + nota + " Observaciones: " + observaciones);
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.fechaExposicion);
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
        final Seminario other = (Seminario) obj;
        if (!Objects.equals(this.fechaExposicion, other.fechaExposicion)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Seminario o) {
        return o.getFechaExposicion().compareTo(this.fechaExposicion);
    }
    
    
    
}
