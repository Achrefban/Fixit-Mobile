/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.AchatProduit;
import Entities.CategorieOutil;
import Entities.CategorieProduit;
import Entities.Outil;
import Entities.Produit;
import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ali Saidani
 */
public class ProduitService {
    ArrayList<CategorieProduit> listCategorie= new ArrayList<>();


    public ArrayList<Produit> parseListProduitJson(String json) {

        ArrayList<Produit> listProduitJson = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("root");
            for (Map<String, Object> obj : list) {
                CategorieProduit co = new CategorieProduit();
                Map<String, Object> listCategorie = (Map<String, Object>) obj.get("CategorieProduit");
                float idC = Float.parseFloat(listCategorie.get("id").toString());
                co.setId((int) idC);
                co.setNom(listCategorie.get("nom").toString());
                co.setImage(listCategorie.get("image").toString());
                co.setDescription(listCategorie.get("description").toString());
                
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setNom(obj.get("nom").toString());
                p.setQuantite((int) Float.parseFloat(obj.get("quantite").toString()));
                p.setIdCategorieProduit(co);
                p.setPrix((int) Float.parseFloat(obj.get("prix").toString()));

                p.setDate_exp((Date) obj.get("date_exp"));
                p.setImage(obj.get("image").toString());
                System.out.println(p);
                listProduitJson.add(p);
            }

        } catch (IOException ex) {
        }
        System.out.println(listProduitJson);
        return listProduitJson;

    }
      public ArrayList<Produit> parseListMesProduitJson(String json) {

        ArrayList<Produit> listMesProduitJson = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> MesProduits = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> listt = (List<Map<String, Object>>) MesProduits.get("root");
            for (Map<String, Object> obj : listt) {
                CategorieProduit co = new CategorieProduit();
                Map<String, Object> listCategorie = (Map<String, Object>) obj.get("CategorieProduit");
                float idC = Float.parseFloat(listCategorie.get("id").toString());
                co.setId((int) idC);
                co.setNom(listCategorie.get("nom").toString());
                co.setImage(listCategorie.get("image").toString());
                co.setDescription(listCategorie.get("description").toString());
                
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setNom(obj.get("nom").toString());
                p.setQuantite((int) Float.parseFloat(obj.get("quantite").toString()));
                p.setIdCategorieProduit(co);
                p.setPrix((int) Float.parseFloat(obj.get("prix").toString()));

                p.setDate_exp((Date) obj.get("date_exp"));
                p.setImage(obj.get("image").toString());
                System.out.println(p);
                listMesProduitJson.add(p);
            }

        } catch (IOException ex) {
        }
        System.out.println(listMesProduitJson);
        return listMesProduitJson;

    }

    ArrayList<Produit> listProduits = new ArrayList<>();
    ArrayList<Produit> listMesProduits = new ArrayList<>();

    public ArrayList<Produit> getProduit() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getAllProduitsWS");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                listProduits = ser.parseListProduitJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
    }
       public ArrayList<Produit> getMesProduit(User user) {
        ConnectionRequest con = new ConnectionRequest();
    
        con.setUrl("http://localhost/fixit/web/app_dev.php/getAllMesroduitsWS/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                listMesProduits = ser.parseListMesProduitJson(new String(con.getResponseData()));
            }
        });
           System.out.println(user.getId());
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMesProduits;
    }
           public ArrayList<CategorieProduit> getListService() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getAllCateorieWS");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());

                listCategorie = parseListServiceJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategorie;
    }
            public ArrayList<CategorieProduit> parseListServiceJson(String json) {

        ArrayList<CategorieProduit> listCat = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                CategorieProduit serv = new CategorieProduit();
                float id = Float.parseFloat(obj.get("id").toString());
                serv.setId((int) id);
                serv.setNom(obj.get("nom").toString());
                listCat.add(serv);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return listCat;
    }
            public void ModifierProduit(Produit p){
                
       ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/ModifierProduitWS/";
        con.setUrl(Url);
        con.addArgument("nom", p.getNom());
        con.addArgument("quantite", Integer.toString(p.getQuantite()));
        con.addArgument("prix", Integer.toString(p.getPrix()));
        con.addArgument("id", String.valueOf(p.getId()));
        con.addArgument("Categorie", Integer.toString(p.getIdCategorieProduit().getId()));
        con.setPost(true);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        String str = new String(con.getResponseData());
                        if (str.equals("200")){
                            Dialog.show("Modification", "Vos données ont été mise à jour", "Retour", null);
                        }
                    }
                });
        NetworkManager.getInstance().addToQueueAndWait(con);
            }
            public void AchatProduit(int idProd,int user,int quantite){
                
       ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/fixit/web/app_dev.php/AcheterProduitWS/"+idProd+"/"+user+"/"+quantite);
                 /*ach = new AchatProduit();
                   con.addArgument("produit", ach.getNom());
        con.addArgument("idProduit", Integer.toString(ach.getIdProduit()));
        con.addArgument("quantite", Integer.toString(ach.getQuantite()));
        con.addArgument("idAcheteur",Integer.toString(ach.getIdAcheteur()));
        con.addArgument("prix", Integer.toString(ach.getPrix()));
        con.setPost(true);*/
                System.err.println(con.getUrl());
         con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        String str = new String(con.getResponseData());
                        if (str.equals("200")){
                            Dialog.show("Ahat", "avec succés", "Retour", null);
                        }
                        else if(str.equals("500")){
                            Dialog.show("votre solde insuffisant", "", "Retour", null);
                        }
                         else if(str.equals("300")){
                            Dialog.show("cette quantite n'est pas disponible ", "", "Retour", null);
                        }
                    }
                });
        NetworkManager.getInstance().addToQueueAndWait(con);
            }
}
