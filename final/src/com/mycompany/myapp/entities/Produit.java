package com.mycompany.myapp.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author PC
 */
public class Produit {
 
    int id,Quantite ;
    float Prix;
    String libelle ;
    String Description ,imageP;

    
    
    
    public Produit(){}
    public Produit(int Quantite, float Prix, String libelle, String Description, String imageP) {
  
        this.Quantite = Quantite;
        this.Prix = Prix;
        this.libelle = libelle;
        this.Description = Description;
        this.imageP = imageP;
    }

    public int getId() {
        return id;
    }

    public int getQuantite() {
        return Quantite;
    }

    public float getPrix() {
        return Prix;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDescription() {
        return Description;
    }

    public String getImageP() {
        return imageP;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImageP(String imageP) {
        this.imageP = imageP;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", Quantite=" + Quantite + ", Prix=" + Prix + ", libelle=" + libelle + ", Description=" + Description + ", imageP=" + imageP + '}';
    }


}
