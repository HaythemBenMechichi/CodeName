   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.service.ServiceProduit;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author PC
 */
public class ProduitForm extends Form{

    public ProduitForm(Form previous) {
        setTitle("Ajouter Produit");
        setLayout(BoxLayout.y());
        TextField DateDebut = new TextField("","Libelle");
        TextField DateFin = new TextField("","Prix");
        TextField Quantite = new TextField("","Quantite");

        TextField image = new TextField("","image");
        TextField Description = new TextField("","Description");
        
        
        
     Label jobIcon = new Label();
     final String[] image_name = {""};
        final String[] pathToBeStored = {""};
     
        
       Button btnUpload = new Button("Upload");
        btnUpload.addActionListener((evt) -> {
       Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        String filePath = (String) ev.getSource();
                        int fileNameIndex = filePath.lastIndexOf("/") + 1;
                        String fileName = filePath.substring(fileNameIndex);
                        Image img = null;
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        image_name[0] = System.currentTimeMillis() + ".jpg";
                        jobIcon.setIcon(img);
                        System.out.println(filePath);
                        System.out.println(image_name[0]);

                        try {
                            pathToBeStored[0] = FileSystemStorage.getInstance().getAppHomePath() + image_name[0];
                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored[0]);
                            ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                            os.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
      
        
        
        
        Button btnValider = new Button ("Ajouter");
        btnValider.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent evt)
        {
            if((DateDebut.getText().length()==0||DateFin.getText().length()==0||image.getText().length()==0||Quantite.getText().length()==0))
                Dialog.show("Alert", "Please fill all the fields " , new Command ("Ok")); 
            else {
        
            try{
            Produit r = new Produit(Quantite.getAsInt(BASELINE) ,DateFin.getAsInt(BASELINE),DateDebut.getText(),Description.getText(),image.getText());
            if(new ServiceProduit().add(r))
            {
                Dialog.show("Success", "Connection accepted", new Command("ok")); 
                System.out.println("r"+r);
            }
            else 
                 Dialog.show("ERROR", "server error ", new Command("ok")); 
            }
            catch (Exception e){
              Dialog.show("ERROR", "Enter a number ", new Command("OK"));  
              }
              }
        }        
        
        });
        
        addAll(DateDebut,DateFin , image ,Description,Quantite,btnUpload , btnValider) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e->previous.showBack());
        
    }
    
}
