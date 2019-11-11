/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import gui.personas.modelos.Profesor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Rosalba Caldez
 */
public class RolEnTrabajo {
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private String razon;
    private Profesor unProfesor;
    private Rol unRol;
    
    public RolEnTrabajo(LocalDate fechaDesde, Profesor unProfesor, Rol unRol){
        this.fechaDesde = fechaDesde;
        this.unProfesor = unProfesor;
        this.unRol = unRol;
    }
    
    public void setFechaDesde(LocalDate fd){
        this.fechaDesde = fd;
    }
    
    public LocalDate getFechaDesde (){
        return this.fechaDesde;
    }
    
     public void setFechaHasta(LocalDate fh){
        this.fechaHasta = fh;
    }
    
    public LocalDate getFechaHasta (){
        return this.fechaHasta;
    }
    
    public void setRazon(String razon){
        this.razon = razon;
    }
    
    public String getRazon(){
        return this.razon;
    }
    
    public void setProfesor(Profesor unProfesor){
        this.unProfesor = unProfesor;
    }
    
    public Profesor getProfesor(){
        return this.unProfesor;
    }

    public void setRol(Rol unRol){
        this.unRol = unRol;
    }
    
    public Rol getRol(){
        return this.unRol;
    }
    
    public void mostrar(){
        String apellido = unProfesor.getApellido();
        String nombre = unProfesor.getNombre();
        System.out.println("Nombre: " + apellido.toUpperCase() + ", " + nombre.toUpperCase());
        System.out.println("Rol del profesor: " + unRol);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        if(fechaDesde != null){            
            System.out.println("Comenzo a colaborar en el trabajo: " + formatter.format(fechaDesde));
        }
        if(fechaHasta != null){
            System.out.println("Trabajo en el proyecto hasta: " + formatter.format(fechaHasta));
            System.out.println("Razon de abandono del proyecto: \n" + razon);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.unProfesor);
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
        final RolEnTrabajo other = (RolEnTrabajo) obj;
        if (!Objects.equals(this.unProfesor, other.unProfesor)) {
            return false;
        }
        return true;
    }
}
