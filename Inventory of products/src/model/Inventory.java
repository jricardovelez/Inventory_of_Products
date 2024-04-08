/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;


public class Inventory {
    
    private ArrayList <Product> list_products ;
   

    public Inventory(ArrayList<Product> list_products) {
        this.list_products = list_products;
    }

    public ArrayList<Product> getList_products() {
        return list_products;
    }

    public void setList_products(ArrayList<Product> list_products) {
        this.list_products = list_products;
    }
    
    public boolean check_if_product_exists (String name){
        
        boolean is_present = false;
        for (Product producto : this.list_products) {
            if (producto.getName().equalsIgnoreCase(name)) {// comparar la lista con el nombre del producto sin importar mayusulas o minusculas
                is_present = true; // si el producto ya esta retorna true
                break;
            }
        }
            return is_present;
    }
    
    public void add_product (Product product){
        this.list_products.add(product);
    }
    
    public void remove_product (int index){
        this.list_products.remove(index);
    }
    
    public void update_product (int index, Product product){
        this.list_products.set(index, product);
    }
    
    public ArrayList<Product> search_products(String search_parameter) {
        // Crear una nueva lista para almacenar los productos encontrados
        ArrayList<Product> products_found = new ArrayList<>();

        // Iterar sobre la lista de productos y buscar coincidencias
        for (Product product : this.list_products) {
            if (product.getName().toLowerCase().contains(search_parameter.toLowerCase())||
            String.valueOf(product.getPrice()).contains(search_parameter) ||
            String.valueOf(product.getStock()).contains(search_parameter)) {
                // Si se encuentra una coincidencia, agregar el producto a la lista de productos encontrados
                products_found.add(product);
            }
        }

        // Retornar la lista de productos encontrados // mientras esta no este vacia
        if(!products_found.isEmpty()){
            return products_found;
        }else{
            return this.list_products; // si esta vacia la lista products_found retorna la lista original
        }
    }
    
}
