/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Inventory;
import model.Product;
import view.frmInventory;


public class control_inventory implements ActionListener{

    private frmInventory frmInventory;
    private Product product;
    private Inventory inventory;

    public control_inventory(frmInventory frmInventory, Product product, Inventory inventory) {
        this.frmInventory = frmInventory;
        this.product = product;
        this.inventory = inventory;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
    }
    
    
    
}
