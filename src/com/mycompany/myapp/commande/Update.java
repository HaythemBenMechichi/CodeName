/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.commande;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.ServiceCommande;

/**
 *
 * @author EXTREME-GAMING
 */
public class Update extends Form {
     Form current;
     public Update(Commande u,Resources res) {  
         
        TextField prix = new TextField(String.valueOf(u.getPrix()) , "prix" , 20 , TextField.ANY);       
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox    
       
        prix.setUIID("NewsTopLine");
        
        
        prix.setSingleLineTextArea(true);
       
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener((ActionEvent l) ->   { 
           
           
           u.setPrix((int)Float.parseFloat(prix.getText()));

        
       //appel fonction modfier reclamation men service
                  System.out.println("user"+u);

        if(ServiceCommande.getInstance().updateCommande(u)) { // if true
           new Home(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new Home(res).show();
       });
       
       
       
       Label l2 = new Label("");
       
      
        
        /////   TABDIL
        
        Container content = BoxLayout.encloseY(
                 l2, 
                new FloatingHint(prix),
            
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}
