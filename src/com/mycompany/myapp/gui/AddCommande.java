/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.ServiceCommande;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class AddCommande extends Form{

    public AddCommande(Form previous) {
        setTitle("Add a new Commande");
        setLayout(BoxLayout.y());

        TextField tfPrix = new TextField("","Prix");
        Date d=new Date();
          
        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            Date d = new Date();
            public void actionPerformed(ActionEvent evt) {
                if ((tfPrix.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Commande t = new Commande(Float.parseFloat(tfPrix.getText()),2,d);
                        if( ServiceCommande.getInstance().addCommande(t))
                        {
                           Dialog.show("Success","Sahha ya Ahla chrik!! :D",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                    }
                    
                }
                
                
            }

            private String parseInt(String text) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        addAll(tfPrix,btnValider);
  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());                
    }
    
    
}
