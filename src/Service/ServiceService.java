/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.CategorieService;
import Entities.Service;
import Entities.ServiceUser;
import Entities.ServicesProposes;
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
 * @author SELON
 */
public class ServiceService {
    public ArrayList<Service> parseListServiceJson(String json) {

        ArrayList<Service> listServiceJson = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> services = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) services.get("root");
            for (Map<String, Object> obj : list) {
                CategorieService cs = new CategorieService();
                Map<String, Object> listCategorie = (Map<String, Object>) obj.get("CategorieService");
                    float idC = Float.parseFloat(listCategorie.get("id").toString());
                    cs.setId((int) idC);
                    cs.setNom(listCategorie.get("nom").toString());
                    cs.setDescription(listCategorie.get("description").toString());
                    cs.setImage(listCategorie.get("imageCategorie").toString());
                Service s = new Service();
                float id = Float.parseFloat(obj.get("id").toString());
                s.setId((int) id);
                s.setNom(obj.get("nom").toString());
                s.setDescription(obj.get("description").toString());
                s.setCategorie(cs);
                s.setVisible((int)Float.parseFloat(obj.get("visible").toString()));
                s.setNbrProviders((int)Float.parseFloat(obj.get("NbrProviders").toString()));
                s.setImage(obj.get("imageService").toString());
                listServiceJson.add(s);
            }

        } catch (IOException ex) {
        }
        System.out.println(listServiceJson);
        return listServiceJson;

    }

    ArrayList<Service> listServices = new ArrayList<>();

    public ArrayList<Service> getService() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/affichageServiceWS/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceService ser = new ServiceService();
                listServices = ser.parseListServiceJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listServices;
    }
    
    public ArrayList<ServiceUser> parseListServiceUserJson(String json) {

        ArrayList<ServiceUser> listServiceJson = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> servicesUser = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) servicesUser.get("root");
            for (Map<String, Object> obj : list) {
                Service s = new Service();
               
                Map<String, Object> listService= (Map<String, Object>) obj.get("idService");
                    float idC = Float.parseFloat(listService.get("id").toString());
                    s.setId((int) idC);
                    s.setNom(listService.get("nom").toString());
                    s.setDescription(listService.get("description").toString());
                    s.setImage(listService.get("imageService").toString());
                ServiceUser su = new ServiceUser();
                float prix = Float.parseFloat(obj.get("prix").toString());
                su.setPrix((int)prix);
                su.setDescription(obj.get("description").toString());
                su.setIdService(s.getId());
                
                listServiceJson.add(su);
            }

        } catch (IOException ex) {
        }
        System.out.println(listServiceJson);
        return listServiceJson;

    }

    ArrayList<ServiceUser> listServicesUser = new ArrayList<>();

    public ArrayList<ServiceUser> getServiceUser(int idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/affichageServiceUserWS/"+idUser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceService ser = new ServiceService();
                listServicesUser = ser.parseListServiceUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listServicesUser;
    }
       String listImg ;
    public String nomImage(int id){
         ConnectionRequest con = new ConnectionRequest();
         ServiceUser s =new ServiceUser();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getImageServiceUserWS/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceService ser = new ServiceService();
                listImg = ser.parseImgJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listImg;
    }
    public String parseImgJson(String json) {

        String listImgJson="";

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> servicesUser = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) servicesUser.get("root");
            for (Map<String, Object> obj : list) {
                 listImgJson = obj.get("imageService").toString();
            }

        } catch (IOException ex) {
        }
        
        return listImgJson;

    }
    
            ArrayList<CategorieService> listCategorie = new ArrayList<>();
     public ArrayList<CategorieService> getListCategorie() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getAllCategorieWS/");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());

                listCategorie = parseListCategorieJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategorie;
    }
    public ArrayList<CategorieService> parseListCategorieJson(String json) {

        ArrayList<CategorieService> listCat = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                CategorieService cs = new CategorieService();
                float id = Float.parseFloat(obj.get("id").toString());
                cs.setId((int) id);
                cs.setNom(obj.get("nom").toString());
                cs.setDescription(obj.get("description").toString());
                cs.setImage(obj.get("imageCategorie").toString());
                listCat.add(cs);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return listCat;
    }
    public ArrayList<Service> parseListServiceCategorieJson(String json) {

        ArrayList<Service> listServiceCategorie = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Service s = new Service();
                float id = Float.parseFloat(obj.get("id").toString());
                s.setId((int) id);
                s.setNom(obj.get("nom").toString());
                s.setDescription(obj.get("description").toString());
                s.setImage(obj.get("imageService").toString());
                listServiceCategorie.add(s);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
       // System.out.println(listDat.toString());
        return listServiceCategorie;
    }
     ArrayList<Service> listServiceCategorie = new ArrayList<>();
    public ArrayList<Service> getListService(int idCategorie) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getServiceCategorieWS/" + idCategorie);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                listServiceCategorie = parseListServiceCategorieJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listServiceCategorie;
    }
   public void ajouterService(ServiceUser su)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/ajouterServiceWS";
        con.setUrl(Url);
        con.setPost(true);
        con.addArgument("user", Integer.toString(su.getIdUser()));
        con.addArgument("service", Integer.toString(su.getIdService()));
        con.addArgument("description",su.getDescription());
        con.addArgument("prix", Float.toString(su.getPrix()));
        con.setPost(true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void supprimerService(ServiceUser su)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/supprimerServiceUserWS";
        con.setUrl(Url);
        con.setPost(true);
        con.addArgument("idu", Integer.toString(su.getIdUser()));
        con.addArgument("ids", Integer.toString(su.getIdService()));
        con.setPost(true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void ajouterProposition(ServicesProposes sp)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/ajouterPropositionWS";
        con.setUrl(Url);
        con.setPost(true);
        con.addArgument("nom", sp.getNom());
        con.addArgument("categorie",sp.getCategorieService());
        con.addArgument("description",sp.getDescription());
        con.setPost(true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
