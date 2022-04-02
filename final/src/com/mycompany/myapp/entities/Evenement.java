/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Evenement {
 
    int id ;
    String image,nom ;
    String DateDebut , DateFin;

    public Evenement(int id, String image, String nom, String DateDebut, String DateFin) {
        this.id = id;
        this.image = image;
        this.nom = nom;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement(String nom, String DateDebut, String DateFin) {
        this.nom = nom;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement(String image, String nom, String DateDebut, String DateFin) {
        this.image = image;
        this.nom = nom;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(String DateDebut) {
        this.DateDebut = DateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setDateFin(String DateFin) {
        this.DateFin = DateFin;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", image=" + image + ", nom=" + nom + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + '}';
    }
    

}
