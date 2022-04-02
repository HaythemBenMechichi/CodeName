/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.commande;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.ServiceCommande;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */


public class Detail extends Form {

    private Form current;
    private Container CommandesContainer;
    private Container addContainer;
    private Button addButton;
    
    public Detail(Resources theme,Form previous){
        
        current = this;
        addButton = new Button("Add Commande");
        CommandesContainer = new Container(BoxLayout.y());
        
        setLayout(new BorderLayout());
        setTitle("Commande List");
        
        
        ArrayList<Commande> Commandes = ServiceCommande.getInstance().getAllCommandes();
        
        for(Commande q : Commandes){
            Container a = new Container(new FlowLayout(CENTER));
            Container b = new Container(BoxLayout.y());
            Container dmContainer = new Container(new FlowLayout(CENTER));
            
            Label lTitle = new Label(q.getCommande());
            Label lId = new Label("Commande Id : "+String.valueOf(q.getId()));
            Label lPrix = new Label("Prix : "+String.valueOf(q.getPrix()));
            Label lDate = new Label("Date : "+String.valueOf(q.getDate()));
            Button bDelete = new Button("Delete");
            Button bModify = new Button("Modify");
            Button bListQuestion = new Button("Show");
            
            dmContainer.add(bDelete).add(bModify).add(bListQuestion);
            b.add(lTitle).add(lId).add(lPrix).add(lDate).add(dmContainer);
            a.add(b);
            
            
            bDelete.addActionListener(e->{
                // ONCE I GET SESSION WORKING, I NEED TO CHANGE 11 with getUserId()
                if( ServiceCommande.getInstance().deleteCommande(q))
                    Dialog.show("Success","Commande Deleted",new Command("OK"));
                else
                    Dialog.show("ERROR", "Server Error", new Command("OK"));
                
                new Detail(theme,previous).show();
            });
            bModify.addActionListener(e->{
                new Update(q, theme).show();
            });

            
            
            System.out.println("Commande NAMBAR WAHED : \n"+q);
            CommandesContainer.add(a);
        }
        add(BorderLayout.NORTH, CommandesContainer).add(BorderLayout.SOUTH, addButton);
        setScrollable(true);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                        , e->previous.showBack()); // Revenir vers l'interface précédente
        revalidate();
    }
    
}


