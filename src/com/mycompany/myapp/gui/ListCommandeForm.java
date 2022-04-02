/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.ServiceCommande;

/**
 *
 * @author bhk
 */


public class ListCommandeForm extends Form {
    Form current;
    public ListCommandeForm(Form previous) {
        current=this;
        setTitle("List Commande");
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceCommande.getInstance().getAllCommandes().toString());
        add(sp);
        Button btnAddCommande = new Button("Add Commande");
        btnAddCommande.addActionListener(e-> new AddCommande(current).show());
        add(btnAddCommande);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}

