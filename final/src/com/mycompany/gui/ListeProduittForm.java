/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.ModifierProduitForm;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.service.ServiceProduit;

/**
 *
 * @author PC
 */
public class ListeProduittForm extends BaseForm{
                    Form current ; 
                    
                    
                    

    ListeProduittForm(Resources res ,Form previous) {
         refreshTheme();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List user");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        setTitle("List Produit");
        for (Produit v : ServiceProduit.getInstance().getAllevenement()) {
            addItem(res,v);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addItem(Resources res ,Produit v) {
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label DateDebut = new Label(" Libelle : " + v.getLibelle());
        Label DateFin = new Label(" Prix : " + v.getPrix());
        Label image = new Label("image : " + v.getImageP());
        Label nom = new Label("Quantite : " + v.getQuantite());
        Label Description = new Label("Description : " + v.getDescription());
        
        
        Button btnsupprimer = new Button ("supprimer");
         Button btnupdate = new Button ("update");

        btnsupprimer.addActionListener(new ActionListener ()  {
           @Override
                    public void actionPerformed(ActionEvent evt){       
                        ServiceProduit.getInstance().deleteEvenement(v.getId());
} 
        });
        
        
       ///// LISTEN TAA UPDATE
       
  btnupdate.addActionListener(new ActionListener ()  {
           @Override
                    public void actionPerformed(ActionEvent evt){
                ServiceProduit.getInstance().modifierProduit(v);
                   
                    new ModifierProduitForm(res,v).show();

                    } 
        });

        
        
        
        

        Label sep = new Label("------------------------------------------------------------------");

        C1.add(DateDebut);
        C1.add(DateFin);
        C1.add(image);
        C1.add(nom);
        C1.add(Description);

        C1.add(btnsupprimer); 
        C1.add(btnupdate);
        C1.add(sep);
        C2.add(C1);
        add(C2);
    }
    

 
}
