/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.areas.modelos.Area;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public interface IGestorAreas {
    String nuevaArea(String nombre);
    void mostrarAreas();
    Area dameArea(String nombre);
    List<Area> buscarArea(String nombre);
    public String borrarArea(Area area);
}
