/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;


public class Inventory {
    
    private ArrayList <Product> list_products ;
    private double price_total;
   

    public Inventory(ArrayList<Product> list_products) {
        this.list_products = list_products;
    }

    public ArrayList<Product> getList_products() {
        return list_products;
    }

    public void setList_products(ArrayList<Product> list_products) {
        this.list_products = list_products;
    }
    
}
