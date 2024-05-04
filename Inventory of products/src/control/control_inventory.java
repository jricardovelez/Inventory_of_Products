/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Inventory;
import model.Product;
import view.frmInventory;


public class control_inventory  implements ActionListener {

    private frmInventory frmInventory;
    private Inventory inventory;
    private int swiche=0;
    int selectedRow ;

    public control_inventory(frmInventory frmInventory,Inventory inventory) {
        this.frmInventory = frmInventory;
        this.inventory = inventory;
        this.frmInventory.btnAdd.addActionListener(this);
        this.frmInventory.btnDelete.addActionListener(this);
        this.frmInventory.btnSearch.addActionListener(this);
        this.frmInventory.btnUpdate.addActionListener(this);
        
        // Cargar productos desde el archivo al iniciar la aplicación
        loadProductsFromFile();
        // Inicializar la tabla con los productos cargados
        initTable();
    }
    
    // Método para cargar los productos desde un archivo
    private void loadProductsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\estiv\\OneDrive\\Documentos\\Inventory_of_Products\\Inventory of products\\src\\data\\productos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Suponiendo que el archivo tzt tiene el formato: nombre,precio,stock
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int stock = Integer.parseInt(parts[2]);
                inventory.add_product(new Product(name, price, stock)); // añadimos el producto al inventario
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar los productos en un archivo
    private void saveProductsInFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\estiv\\OneDrive\\Documentos\\Inventory_of_Products\\Inventory of products\\src\\data\\productos.txt", false))) {
            for (Product product : inventory.getList_products()) {
                bw.write(product.getName() + "," + product.getPrice() + "," + product.getStock()); //se escriben los productos en el archivo txt
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        selectedRow = this.frmInventory.table_Inventory.getSelectedRow(); // se obtiene el indice de la fila seleccionada de la tabla
        if(e.getSource() == this.frmInventory.btnAdd){ // dectectar llamada del boton add
            if(validateFields()){  // validar campos de entrada
                String name = this.frmInventory.txtName.getText();
                double price= Double.parseDouble(this.frmInventory.txtPrice.getText());
                int stock = Integer.parseInt(this.frmInventory.txtStock.getText()); 
         
                if(swiche==1){ // si swiche esta en uno quiere decir que se va añadir un producto ya existente pero actualizado
                    this.inventory.update_product(selectedRow,new Product(name,price,stock));
                    initTable();
                    saveProductsInFile(); // llamamos al metodo para guaradr el producto en el archivo
                    swiche=0;
                }else if(inventory.check_if_product_exists(this.frmInventory.txtName.getText())){ // se valida que el producto ya esta en el inventario
                    JOptionPane.showMessageDialog(null,"the product is already in inventory");
                }else{
                           
                    this.inventory.add_product(new Product (name, price, stock)); // se agrega el producto al inventario
                    initTable();
                    saveProductsInFile(); // llamamos al metodo para guaradr el producto en el archivo
                }
            }
                    clean_txt_data(); // limpiar txt componentes
        }
        
        if (e.getSource() == this.frmInventory.btnDelete) {  // dectectar llamada del boton delete
            if (selectedRow != -1) {
                this.inventory.remove_product(selectedRow);
                initTable();
                saveProductsInFile();
            }else {
                JOptionPane.showMessageDialog(null, "Please, select a product.");
            }
        }  
        
        if (e.getSource() == this.frmInventory.btnUpdate) { // dectectar llamada del boton update
            if (selectedRow != -1) {
                String name =  (String) this.frmInventory.table_Inventory.getValueAt(selectedRow, 0); // Obtiene el valor de la celda en la columna 0 (nombre)
                String price = valueOf(this.frmInventory.table_Inventory.getValueAt(selectedRow, 1)); // Obtiene el valor de la celda en la columna 1 (precio)
                String stock = valueOf(this.frmInventory.table_Inventory.getValueAt(selectedRow, 2)); // Obtiene el valor de la celda en la columna 2 (stock)

                this.frmInventory.txtName.setText(name);
                this.frmInventory.txtPrice.setText(price);
                this.frmInventory.txtStock.setText(stock);  
                swiche=1;
            }else {
                JOptionPane.showMessageDialog(null, "Please, select a product.");
            }
        }
        
        if (e.getSource() == this.frmInventory.btnSearch) {  // dectectar llamada del boton search
            // Crear una nueva lista para almacenar los productos encontrados
            ArrayList<Product> products_found;
            if (this.frmInventory.txtSearch.getText().isEmpty()){
                   initTable();
            }else{
                products_found = this.inventory.search_products(this.frmInventory.txtSearch.getText());
                // Retornar la lista de productos encontrados // mientras esta no este vacia
                if(products_found.isEmpty()){
                    JOptionPane.showMessageDialog(null,"No products with those characteristics were found"); 
                }else{
                    filtered_table(products_found);
                }

            }
            
        }
         
        
    }

    
    // validar que los campos no esten vacios y tengan un tipo correcto
    private boolean validateFields() {
        // Verificar que los campos no estén vacíos
        if (this.frmInventory.txtName.getText().isEmpty() || this.frmInventory.txtPrice.getText().isEmpty() || this.frmInventory.txtStock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Fill out all fields"); 
            return false;
        }

        // Verificar que el precio y el stock sean números válidos y que el name no sea so lo numeros
        try {
            if (!validarNombre(this.frmInventory.txtName.getText())) {
                JOptionPane.showMessageDialog(null,"The product name cannot be just numbers"); 
                return false;
            } 
            Double.valueOf(this.frmInventory.txtPrice.getText());
            Integer.valueOf(this.frmInventory.txtStock.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Enter a correct number for price or stock"); 
            return false;
        }

        // Todos los campos son válidos
        return true;
    } 
    
    public boolean validarNombre(String name) {
         // La expresión regular ^.*[a-zA-Z]+.*$ coincide con cualquier cadena que contenga al menos una letra
        // Explicación de la expresión regular:
        // ^          -> Coincide con el inicio de la cadena
        // .*         -> Coincide con cero o más caracteres (cualquier carácter)
        // [a-zA-Z]+  -> Coincide con una o más letras (mayúsculas o minúsculas)
        // .*         -> Coincide con cero o más caracteres (cualquier carácter)
        // $          -> Coincide con el final de la cadena
        return name.matches("^.*[a-zA-Z]+.*$");
    }
    
     // inicializar tabla
    public void initTable(){
        
        // Verificar si la tabla ya tiene filas
        if (this.frmInventory.tb.getRowCount() > 0) {
        // Si la tabla ya tiene filas, eliminarlas todas
            this.frmInventory.tb.setRowCount(0);
        }
        
        if(!this.inventory.getList_products().isEmpty()){// mientras la lista no este vacia
            // Recorrer la lista de productos y agregar los datos a la tabla
            for (Product product : this.inventory.getList_products()) {
                Object[] product_data = {
                product.getName(),
                product.getPrice(),
                product.getStock(),
                };
                this.frmInventory.tb.addRow(product_data); // añadir fila a la tabla
            }
        
        }
        
    } 
    
    public void filtered_table(ArrayList<Product> products_found){
        
        // Verificar si la tabla ya tiene filas
        if (this.frmInventory.tb.getRowCount() > 0) {
        // Si la tabla ya tiene filas, eliminarlas todas
            this.frmInventory.tb.setRowCount(0);
        }
        
        if(!products_found.isEmpty()){// mientras la lista no este vacia
            // Recorrer la lista de productos y agregar los datos a la tabla
            for (Product product : products_found) {
                Object[] product_data = {
                product.getName(),
                product.getPrice(),
                product.getStock(),
                };
                this.frmInventory.tb.addRow(product_data); // añadir fila a la tabla
            }
        
        }
        
    } 
    
    // limpiar componentes de texto del producto
    public void clean_txt_data(){
        this.frmInventory.txtName.setText("");
        this.frmInventory.txtPrice.setText("");
        this.frmInventory.txtStock.setText("");
    }
    
    
}
