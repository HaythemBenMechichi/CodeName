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
import com.mycompany.myapp.entities.Evenement;
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
public class ServiceEvenement {

    public static ServiceEvenement instance;

    public ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
    public ArrayList<Evenement> evenement;
    private  ConnectionRequest req;
    public boolean resultOK;

    public boolean add(Evenement p) {
        String url = Static.BASE_URL + "/Ajouterevenement?DateDebut=" + p.getDateDebut() + "&DateFin=" + p.getDateFin() + "&image=" + p.getImage() + "&nom=" + p.getNom();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

    public ArrayList<Evenement> parseEvenement(String jsonText) {
        try {
            evenement = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> evenementListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenementListJson.get("root");
            for (Map<String, Object> obj : list) {
                Evenement r = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                r.setDateDebut((obj).get("DateDebut").toString());
                r.setDateFin((obj).get("DateFin").toString());
                r.setImage(obj.get("image").toString());
                r.setNom(obj.get("nom").toString());
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
    
    
    public ArrayList<Evenement> getAllevenement() {
        req = new ConnectionRequest();
       String url = Static.BASE_URL+"/events";
        System.out.println("===>"+url);
      
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
        String url = Static.BASE_URL + "/Deleteevent?id=" + t;
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
    
    // update 
    public boolean modifierEvenement(Evenement evenement) {
        String url = Static.BASE_URL +"/updateEvenement?id="+evenement.getId()+"&DateDebut="+evenement.getDateDebut()+"&DateFin="+evenement.getDateFin()+"&image="+evenement.getImage()+"&nom="+evenement.getNom();
        req.setUrl(url);
        System.out.println("aaaaaaaaaaa");
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;
                req.removeResponseListener(this);
            }
        });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    
    }
    
    
    
    
}
