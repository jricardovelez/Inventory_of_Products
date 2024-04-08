/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Inventory;
import model.Product;
import view.frmInventory;


public class control_inventory  extends AbstractTableModel implements ActionListener {

    private final frmInventory frmInventory;
    private final Inventory inventory;

    public control_inventory(frmInventory frmInventory,Inventory inventory) {
        this.frmInventory = frmInventory;
        this.inventory = inventory;
        this.frmInventory.btnAdd.addActionListener(this);
        this.frmInventory.btnDelete.addActionListener(this);
        this.frmInventory.btnSearch.addActionListener(this);
        this.frmInventory.btnUpdate.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.frmInventory.btnAdd){
            if(validateFields()){
                if(inventory.check_if_product_exists(this.frmInventory.txtName.getText())){
                    JOptionPane.showMessageDialog(null,"the product is already in inventory");
                }
                else{
                    String name = this.frmInventory.txtName.getText();
                    double price= Double.parseDouble(this.frmInventory.txtPrice.getText());
                    int stock = Integer.parseInt(this.frmInventory.txtStock.getText());        
                    this.inventory.add_product(new Product (name, price, stock));
                }
            }
        }
        
        
        
    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // validar que los campos no esten vacios y tengan un tipo correcto
    private boolean validateFields() {
        // Verificar que los campos no estén vacíos
        if (this.frmInventory.txtName.getText().isEmpty() || this.frmInventory.txtPrice.getText().isEmpty() || this.frmInventory.txtStock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Fill out all fields"); 
            return false;
        }

        // Verificar que el precio y el stock sean números válidos
        try {
            Double.valueOf(this.frmInventory.txtPrice.getText());
            Integer.valueOf(this.frmInventory.txtStock.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Enter a correct number for price or stock"); 
            return false;
        }

        // Todos los campos son válidos
        return true;
    } 
    
    
}
