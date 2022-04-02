/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 * @author PC
 */
public class ProduitHomeForm  extends BaseForm{
    Form current ; 
    public ProduitHomeForm(Resources res){
        current=this; 
        refreshTheme();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List user");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
    
     setTitle("PRINCEPS"); 
     setLayout(BoxLayout.y()); 
    Container add = add(new Label ("Select an option ")); 
Button Add = new Button ("Ajout produit"); 
Button List = new Button ("Liste Produit"); 

Add.addActionListener(e-> new ProduitForm(current).show()); 
List.addActionListener(e-> new ListeProduittForm(res, current).show());
    Container addAll = addAll(Add, List); 
    }
}
 