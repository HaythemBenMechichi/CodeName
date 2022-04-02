
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.service.ServiceProduit;

/**
 * @author Lenovo
 */
public class ModifierProduitForm extends BaseForm {
    
    Form current;
    
    
    
    public ModifierProduitForm(Resources res ,Produit r) {
        
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        ////     TABDIL
        
        
        TextField objet = new TextField(r.getLibelle() , "Libelle" , 20 , TextField.ANY);

        TextField description = new TextField(r.getDescription() , "Description" , 20 , TextField.ANY);

        TextField prix = new TextField(String.valueOf(r.getQuantite()) , "Prix" , 20 , TextField.ANY);

        TextField quantite = new TextField(String.valueOf(r.getQuantite()) , "Quantite" , 20 , TextField.ANY);

        TextField imageP = new TextField(r.getImageP() , "imageP" , 20 , TextField.ANY);
     
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
       
        
//        if(r.getEtat() == 0 ) {
//            etatCombo.setSelectedIndex(0);
//        }
//        else 
//            etatCombo.setSelectedIndex(1);
        
        objet.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        
        objet.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
       
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setLibelle(objet.getText());
           r.setDescription(description.getText());
           r.setPrix(prix.getAsInt(BASELINE));
           r.setQuantite(quantite.getAsInt(BASELINE));
           r.setImageP(imageP.getText());
       //appel fonction modfier reclamation men service
       
       if(ServiceProduit.getInstance().modifierProduit(r)) { // if true
           new ListeProduittForm(res ,current).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListeProduittForm(res ,current).show();
       });
       
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        /////   TABDIL
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(objet),
                new FloatingHint(description),
                new FloatingHint(prix),
                new FloatingHint(quantite),
                new FloatingHint(imageP),
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
   
    }
}