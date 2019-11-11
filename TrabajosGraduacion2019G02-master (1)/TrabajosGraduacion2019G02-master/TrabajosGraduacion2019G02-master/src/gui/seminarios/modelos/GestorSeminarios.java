/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.seminarios.modelos;

import gui.interfaces.IGestorSeminarios;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public class GestorSeminarios implements IGestorSeminarios {
    public static GestorSeminarios gestor;
    private List<Seminario> seminarios = new ArrayList<>();
    
    private GestorSeminarios(){     
    }
    
    public static GestorSeminarios crear(){
        if(gestor == null){
            gestor = new GestorSeminarios();
        }
        return gestor;
    }
    
    /**
     * Agrega el seminario a la lista de seminarios. 
     * @param seminario 
     */
    public void agregarSeminario(Seminario seminario){
        seminarios.add(seminario);
    }
    
    public boolean contiene(Seminario seminario){
        if(seminarios.contains(seminario)){
            return true;
        }
        return false;
    }
    
    /**
     * Valida los datos para crear un seminario y retorna el mensaje correspondiente. 
     * Si la nota aprobaicione es aprobado con obs o desaprobado, se deben indicar las observaciones. 
     * @param fechaExposicion
     * @param nota
     * @param obs
     * @return 
     */
    @Override
    public String validarSeminario(LocalDate fechaExposicion, NotaAprobacion nota, String obs) {
       if(fechaExposicion == null){
           return "No se validó el seminario.";
       }
       if(nota == null){
           return "No se validó el seminario.";
       }
       
       if(nota != NotaAprobacion.APROBADO_SINOBS){
          if((obs.isEmpty()) || obs == null){
              return "No se validó el seminario.";
          }
       }
     
       return "Se validó el seminario.";
       
    }
    
    public void mostrar(){
        Collections.sort(this.seminarios);
        for(Seminario s : this.seminarios){
            s.mostrar();
        }
    }
    
}
