/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import model.*;
import view.*;
import control.*;

public class inventory_products {
    public static void main(String args[]) {
        // crear objetos de la clase teams y de los freams
        frmInventory frmInventory = new frmInventory();
        Inventory inventory = new Inventory();
        
        control_inventory ci = new control_inventory(frmInventory,inventory); // objeto clase controlador del juego
        
        frmInventory.setVisible(true); // hacer visible el frame de menu
    }
    
}
