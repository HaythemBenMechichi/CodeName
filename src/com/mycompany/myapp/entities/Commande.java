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
public class Commande {

    public static void setObject(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    int id;
    float prix;
    Date date;
    int user;
    String commande;
    
    public Commande(int id, float prix, int user, Date date) {
        this.id = id;
        this.prix = prix;
        this.user = user;
        this.date = date;
    }

    public Commande(float prix, int user, Date date) {
        this.prix = prix;
        this.user = user;
        this.date = date;
    }
    
    public Commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date=" + date + ", prix=" + prix +  "}";
    }

    public String getCommande() {
        return commande;
    }

    public void setCommande(String jsonText) {
        }

    public void getPrix(Float valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
