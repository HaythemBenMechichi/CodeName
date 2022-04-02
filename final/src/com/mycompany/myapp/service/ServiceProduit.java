/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author PC
 */
public class ServiceProduit {

    public static ServiceProduit instance;

    public ServiceProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    public ArrayList<Produit> evenement;
    private  ConnectionRequest req;
    public boolean resultOK;

    public boolean add(Produit p) {
        String url = Static.BASE_URL + "/AjouterProduitMob?Libelle=" + p.getLibelle() + "&Quantite=" + p.getQuantite() + "&imageP=" + p.getImageP() + "&Description=" + p.getDescription() + "&Prix=" + p.getPrix();
        req.setUrl(url);
                        System.out.println("babsfdsdsdsddsdssdaba");

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("bababa");
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Produit> parseEvenement(String jsonText) {
        try {
            evenement = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> evenementListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenementListJson.get("show_product");
            for (Map<String, Object> obj : list) {
                Produit r = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                r.setLibelle((obj).get("libelle").toString());
                float qqt = Float.parseFloat(obj.get("Quantite").toString());
                r.setQuantite((int)qqt);
                float prix = Float.parseFloat(obj.get("prix").toString());
                r.setPrix(prix);
                r.setDescription((obj).get("Description").toString());
                r.setImageP(obj.get("imageP").toString());
                evenement.add(r);
            }
        } catch (Exception e) {
        }
        return evenement;
    }
    

 /*   public ArrayList<Evenement>affichageEvenement(){
        ArrayList<Evenement> result = new ArrayList<>();
        
        String url = Static.BASE_URL+"/events";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt){
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try(
                        Map<String,Object>mapEvenements = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                        
                        List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapEvenements.get("root");
                        
                        for(Map<String, Object> obj : listOfMaps)(
                                Evenement ev = new Evenement());
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String image = obj.get("image").toString();
                        
                        String DateConverter = obj.get("DateDebut").toString().substring(obj.get("DateDebut").toString().indexOf("timestamp")+ 10 , obj.get("obj").toString().lastIndexOf(")"));
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue()* 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        ev.setDate(dateString);
                        
                        
                        
                        )catch(Exception ex){
                                
                                ex.printStackTrace();
                                }
            }
            
                }
    });
    
    */
    
    public ArrayList<Produit> getAllevenement(){
        req = new ConnectionRequest();
        String url = Static.BASE_URL+"/mobile/produitAffiche";
      
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenement = parseEvenement(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenement;
    }
    
    
    
    
    
    
    public boolean deleteEvenement(int t) {
        String url = Static.BASE_URL + "/DeleteProduitMob?id=" + t;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
    
    //Update   TABDIL
    public boolean modifierProduit(Produit prod) {
        String url = Static.BASE_URL +"/UpdateProduitMob?id="+prod.getId()+"&Libelle="+prod.getLibelle()+"&Description="+prod.getDescription()+"&Prix="+prod.getPrix()+"&Quantite="+prod.getQuantite()+"&ImageP="+prod.getImageP();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }  
}
