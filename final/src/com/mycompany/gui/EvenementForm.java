   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.service.ServiceProduit;
import com.mycompany.myapp.service.ServiceEvenement;
import com.codename1.ui.util.Resources;

import com.mycompany.myapp.utils.Static;
import java.io.IOException;
import java.io.OutputStream;



/**
 *
 * @author PC
 */
public class EvenementForm extends BaseForm{

    public EvenementForm(Form previous,Resources res) {
        
        
        refreshTheme();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List user");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        
        
      //  Picker datePicker = new Picker();
        //    datePicker.setType(Display.PICKER_TYPE_DATE);
        setTitle("Ajouter Evenement");
        setLayout(BoxLayout.y());
        TextField DateDebut = new TextField("","DateDebut");
        TextField DateFin = new TextField("","DateFin");
     /*  protected String saveFileToDevice(String hi, String ext) throws IOException, URISyntaxException {
	URI uri;
	try {
		uri = new URI(hi);
		String path = uri.getPath();
		int index = hi.lastIndexOf("/");
		hi = hi.substring(index + 1);
		return hi;
		} catch (URISyntaxException ex) {
		}
		return "hh";
		}

        image.addActionListener((ActionEvent e) -> {
                if(FileChoser.isAvailable()) {
		    FileChoser.setOpenFilesInPlace(true);
		    FileChoser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png/plain", (ActionEvent e2) -> {
		  	if (e2 == null || e2.getSource() == null) {
			    add("No file was selected");
			    revalidate();
			    return;
			}
		if(multiSelect.isSelected()){
		    String[] paths = (String[]) e2.getSource();
		    for(String[] path : paths) {
		 	System.out.println(path);
			CN.execute(path);
		}
		String file = (String) e2.getSource();
		if (file == null) {
			add("No file was selected");
			revalidate();
		} else {
			Image logo;

			try {
			     logo = Image.createImage(file).scaledHeight(500);;
				add(logo);
				String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";
				
				try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)){
					System.out.println(imageFile);
					ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
				} catch (IOException err) {
					}

				String extension = null;
				if (file.lastIndexOf(".") > 0) {
					extension = file.substring(file.lastIndexOf(".") + 1);
					StringBuilder hi = new StringBuilder(file);
					if (file.startsWith("file://")) {
						hi.delete(0, 7);
				}
				int lastIndexPeriod = hi.toString().lastIndexOf(".");
				Log.p(hi.toString());
				String ext = hi.toString().substring(lastIndexPeriod);
				String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
				try {
					String namePic = SaveFileToDevice(file, ext);
					System.out.println(namePic);
					} catch(IOException ex){
					}
					revalidate();
					}
                                } catch (IOException ex) {
                            Logger.getLogger(EvenementForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
	};
                    
*/
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
        
        
        TextField nom = new TextField("","nom");
        Button btnValider = new Button ("Ajouter");
        btnValider.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent evt)
        {
            if((DateDebut.getText().length()==0||DateFin.getText().length()==0||nom.getText().length()==0))
                Dialog.show("Alert", "Please fill all the fields " , new Command ("Ok")); 
            else {
            
            try{
            Evenement r = new Evenement((nom.getText()),(DateDebut.getText().toString()), (DateFin.getText().toString()));
            
            if(new ServiceEvenement().add(r)){
                Dialog.show("Success", "Connection accepted", new Command("ok")); 
                new ListeEvenementForm(previous,res).showBack();
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
        
        addAll(DateDebut,DateFin ,btnUpload, jobIcon, nom , btnValider) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e->previous.showBack());
     
      //  add(btnUpload);
    }
    
}
