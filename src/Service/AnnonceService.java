/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Annonce;
import Entities.CategorieService;
import Entities.Produit;
import Entities.Service;
import Entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Achref Bannouri
 */
public class AnnonceService {
    public ArrayList<Annonce> parseListAnnonceJson(String json) {

        ArrayList<Annonce> listAnnonceJson = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> annonces= j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) annonces.get("root");
            for (Map<String, Object> obj : list) {
                //Catégorie Service
                CategorieService cs = new CategorieService();
                Map<String, Object> listCategorie = (Map<String, Object>) obj.get("CategorieService");
                float idC = Float.parseFloat(listCategorie.get("id").toString());
                System.out.println(idC);
                cs.setId((int) idC);
                cs.setNom(listCategorie.get("nom").toString());
                cs.setImage(listCategorie.get("imageCategorie").toString());
                cs.setDescription(listCategorie.get("description").toString());
                //Service
                Service s = new Service();
                Map<String, Object> services = (Map<String, Object>) obj.get("Service");
                float ids = Float.parseFloat(services.get("id").toString());
                s.setId((int) ids);
                s.setNom(services.get("nom").toString());
                s.setVisible((int) Float.parseFloat(services.get("visible").toString()));
                s.setImage(services.get("imageService").toString());
                s.setDescription(services.get("description").toString());
                s.setNbrProviders((int) Float.parseFloat(services.get("nbrProviders").toString()));
                s.setCategorie(cs);
                //Date
                Map<String, Object> resultDateReclamation =(Map<String, Object>)obj.get("date");
                Double longV1 = Double.parseDouble(resultDateReclamation.get("timestamp").toString());
                long millisecond1 = new Double(longV1).longValue()*1000;
                Date d1 = new Date(millisecond1);
                SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
                
                
                Annonce a = new Annonce();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int) id);
                a.setDescription(obj.get("description").toString());
                a.setType(obj.get("type").toString());
                a.setMontant((int) Float.parseFloat(obj.get("prix").toString()));
                a.setTel((int) Float.parseFloat(obj.get("tel").toString()));
                a.setAdresse(obj.get("adresse").toString());
                //a.setDate((Date) obj.get("date"));
                a.setDate(dateformat1.format(d1));
                a.setNbr_c((int) Float.parseFloat(obj.get("nbrC").toString()));
                a.setNbr_d((int) Float.parseFloat(obj.get("nbrD").toString()));
                a.setNbr_o((int) Float.parseFloat(obj.get("nbrO").toString()));                                  
                a.setCategorieService(cs);
                a.setService(s);
                

                

                System.out.println(a);
                listAnnonceJson.add(a);
            }

        } catch (IOException ex) {
        }
        System.out.println(listAnnonceJson);
        return listAnnonceJson;

    }
    
     ArrayList<Annonce> listOffres = new ArrayList<>();
     ArrayList<Annonce> listDemandes = new ArrayList<>();
     ArrayList<Annonce> listMesAnnonces = new ArrayList<>();

    public ArrayList<Annonce> getOffres() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/listeOffreWS");
        con.addResponseListener((NetworkEvent evt) -> {
            AnnonceService ser = new AnnonceService();
            listOffres = ser.parseListAnnonceJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listOffres;
    }
    
    public ArrayList<Annonce> getDemandes() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/listeDemandeWS");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
                listDemandes = ser.parseListAnnonceJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDemandes;
    }
    
     public ArrayList<Annonce> getMesAnnonces(User user) {
        ConnectionRequest con = new ConnectionRequest();
    
        con.setUrl("http://localhost/fixit/web/app_dev.php/mesAnnonces/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
                listMesAnnonces = ser.parseListAnnonceJson(new String(con.getResponseData()));
            }
        });
           //System.out.println(user.getId());
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMesAnnonces;
    }
     
     public void ajouterAnnonce(Annonce a)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/ajouterAnnonceWS";
        con.setUrl(Url);
        con.setPost(true);
        con.addArgument("description", a.getDescription());
        con.addArgument("type", a.getType());
        con.addArgument("prix", Long.toString(a.getMontant()));
        con.addArgument("tel", Integer.toString(a.getTel()));
        con.addArgument("adresse", a.getAdresse());
        con.addArgument("date", a.getDate());
        con.addArgument("iduser", Integer.toString(a.getUser().getId()));
        con.addArgument("categorie",Integer.toString(a.getCategorieService().getId()));
        con.addArgument("service",Integer.toString(a.getService().getId()));
        con.setPost(true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
     public void supprimerAnnonce(Annonce a)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/supprimerWS/"+a.getId();
        con.setUrl(Url);        
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void modifierAnnonce(Annonce a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/modifierAnnonceWS/"+a.getId();
        con.setPost(true);
        con.setUrl(Url);
        con.addArgument("description", a.getDescription());
        con.addArgument("type", a.getType());
        con.addArgument("prix", Long.toString(a.getMontant()));
        con.addArgument("tel", Integer.toString(a.getTel()));
        con.addArgument("adresse", a.getAdresse());
        con.addArgument("date", a.getDate());
        con.addArgument("categorie",Integer.toString(a.getCategorieService().getId()));
        con.addArgument("service",Integer.toString(a.getService().getId()));

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

     
     
     

}
