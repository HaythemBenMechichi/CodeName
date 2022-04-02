/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.commande;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class Home extends Form{

Form current;
Resources res;

    public Home(Resources res)
    {
        setLayout(BoxLayout.y());

        Button btnListCommande = new Button("List Des Commandes");
        Button btnAddCommande = new Button("ADD Commandes");

        btnListCommande.addActionListener(e-> new List(res, current).show());
        btnAddCommande.addActionListener(e-> new Add(current).show());

        addAll(btnListCommande,btnAddCommande);
        
    }
}
