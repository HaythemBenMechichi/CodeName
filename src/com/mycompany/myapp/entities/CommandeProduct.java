/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Extreme PC
 */
public class CommandeProduct {
    int id;
    int quantity;
    String product;
    String order;
    String CommandeProduct;

    public CommandeProduct() {
    }

    public CommandeProduct(int id, int quantity, String product, String order, String CommandeProduct) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
        this.CommandeProduct = CommandeProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCommandeProduct() {
        return CommandeProduct;
    }

    public void setCommandeProduct(String CommandeProduct) {
        this.CommandeProduct = CommandeProduct;
    }
    

 
}
