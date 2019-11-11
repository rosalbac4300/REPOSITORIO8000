/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

/**
 *
 * @author gabinete
 */
public class Profesor extends Persona{
    private Cargo cargo;
    
    /**
     * Constructor para inicializar el profesor. 
     * @param nombre
     * @param apellido
     * @param cargo
     * @param dni 
     */
    public Profesor(String apellido, String nombre, int dni, Cargo cargo) {
        super(nombre, apellido, dni);
        this.cargo = cargo;
    }
    
    public Cargo getCargo(){
        return cargo;
    }
    
    public void setCargo(Cargo cargo){
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode(); 
    }

    @Override
    public void setDni(int dni) {
        super.setDni(dni); 
    }

    @Override
    public int getDni() {
        return super.getDni(); 
    }

    @Override
    public void setApellido(String apellido) {
        super.setApellido(apellido); 
    }

    @Override
    public String getApellido() {
        return super.getApellido(); 
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); 
    }

    @Override
    public String getNombre() {
        return super.getNombre(); 
    }
    
    @Override
    public String toString(){
        return "\n" + "PROFESOR: \n" + super.toString() + "\n" + "CARGO: " + cargo;
    }
     
    
    @Override
     public void mostrar(){
         System.out.println();
         System.out.println("PROFESOR: ");
        super.mostrar();
        System.out.println("Cargo: " + cargo);
        
    }
}
