
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceCommande {

    public ArrayList<Commande> Commandes;
    
    public static ServiceCommande instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommande() {
         req = new ConnectionRequest();
    }

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }



    public ArrayList<Commande> parseTasks(String jsonText){
        try {
            Commandes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CommandeListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            Date d = new Date();
            List<Map<String,Object>> list = (List<Map<String,Object>>)CommandeListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande t = new Commande();
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());

                t.setPrix((int)prix);
                t.setId((int)id);

                t.setDate(d);

                t.setCommande(jsonText);
                Commandes.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Commandes;
    }
    
    public ArrayList<Commande> getAllCommandes(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"liste_commandeMobile";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commandes = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Commandes;
    }
        public boolean addCommande(Commande t) {
       String url = Statics.BASE_URL + "ajout_adherantmobile?prix=" + t.getPrix();
       //String url = Statics.BASE_URL + "ajout_adherantmobile"+ t.getId()  + "&USER=" + t.getUser() + "&DATE="  + t.getDate()+ "&PRIX=" + t.getPrix() ;
    
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("Commande", t.getCommande());
  
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean deleteCommande(Commande q) {
        String url = Statics.BASE_URL + "DeleteAM"+"?id="+q.getId();
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
    
       public boolean updateCommande(Commande q) {
        String url = Statics.BASE_URL+"Modify_Com_Mob"+"?id="+q.getId()+"&prix="+q.getPrix(); //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
}
