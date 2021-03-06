/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.service.ServiceProduit;
import com.mycompany.myapp.service.ServiceEvenement;

import java.io.IOException;

/**
 *
 * @author PC
 */
public class ListeEvenementForm extends BaseForm {

    private Resources theme;

    ListeEvenementForm(Form previous,Resources res) {
        
        
        
        refreshTheme();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List user");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        
        
        
        setTitle("List Evenement");
        for (Evenement v : ServiceEvenement.getInstance().getAllevenement()) {
            addItem(v,res);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    ListeEvenementForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addItem(Evenement v,Resources res) {
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label DateDebut = new Label("Date debut : " + v.getDateDebut());
        Label DateFin = new Label("Date fin : " + v.getDateFin());
        Label nom = new Label("Nom : " + v.getNom());

        Image img = null;
        try {
            img = Image.createImage("file://C://xampp//htdocs//testbundle//testbundle//projetpi//public//uploads//" + v.getImage());
        } catch (IOException ex) {
        }

        Button btnsupprimer = new Button("supprimer");
        btnsupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceProduit.getInstance().deleteEvenement(v.getId());
            }
        });

        // Update icon
        /*   Label lModifier = new Label(" ");
       lModifier.setUIID("NewsTopLine");
       Style modifierStyle = new Style(lModifier.getUnselectedStyle());
       modifierStyle.setFgColor(0xf7ad02);
       
       FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
       lModifier.setIcon(mFontImage);
       lModifier.setTextPosition(LEFT);
       
       lModifier.addPointerPressedListener(l -> {
     //   System.out.println("Update Event");
     new ModifierEvenementForm(res , v).show();
    });
         */
        ///// LISTEN TAA UPDATE
        Button btnupdate = new Button("update");
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceEvenement.getInstance().modifierEvenement(v);

                new ModifierEvenementForm(v,res).show();

            }
        });

        Label sep = new Label("------------------------------------------------------------------");

        C1.add(DateDebut);
        C1.add(DateFin);
        C1.add(nom);
        C1.add(new ImageViewer(img));
        C1.add(btnsupprimer);
        C1.add(btnupdate);
        C1.add(sep);
        C2.add(C1);
        add(C2);
    }

}
