/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ReclamationService {

    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Service> listService = new ArrayList<>();
    ArrayList<String> listDateReal = new ArrayList<>();
    ArrayList<Reclamation> listReclamation = new ArrayList<>();

    public ArrayList<User> getListUser(int idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getusersreclameWS/"+idUser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());

                listUser = parseListUserJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUser;
    }

    public ArrayList<User> parseListUserJson(String json) {

        ArrayList<User> listU = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setUsername(obj.get("username").toString());
                u.setFirstname(obj.get("firstname").toString());
                u.setLastname(obj.get("lastname").toString());
                listU.add(u);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return listU;
    }

    public ArrayList<Service> getListService(int idUserOffreur,int idUserDemandeur) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getservicerealiseWS/" + idUserOffreur + "/"+idUserDemandeur);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());

                listService = parseListServiceJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listService;
    }

    public ArrayList<Service> parseListServiceJson(String json) {

        ArrayList<Service> listServ = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Service serv = new Service();
                float id = Float.parseFloat(obj.get("id").toString());
                serv.setId((int) id);
                serv.setNom(obj.get("nom").toString());
                listServ.add(serv);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return listServ;
    }

    public ArrayList<String> getListDate(int idUserOffreur, int idService,int idUserDemandeur) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getservicerealiseWS/" + idUserOffreur + "/"+idUserDemandeur+"/" + idService);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                listDateReal = parseListDateJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDateReal;
    }

    public ArrayList<String> parseListDateJson(String json) {

        ArrayList<String> listDat = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Map<String, Object> result =(Map<String, Object>)obj.get("DateRealisation");
                Double longV = Double.parseDouble(result.get("timestamp").toString());
                long millisecond = new Double(longV).longValue()*1000;
                Date d = new Date(millisecond);
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                listDat.add(dateformat.format(d));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(listDat.toString());
        return listDat;
    }
     public void ajouterReclamation(Reclamation rec)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/ajouterReclamationWS/";
        con.setUrl(Url);
        con.setPost(true);
        con.addArgument("UserAReclamer", Integer.toString(rec.getUserReclame().getId()));
        con.addArgument("user", Integer.toString(rec.getUserReclamant().getId()));
        con.addArgument("service", Integer.toString(rec.getIdServiceRealise().getId()));
        con.addArgument("dateRealisation", rec.getDateRealisation());
        con.addArgument("Object", rec.getObjet());
        con.addArgument("Description", rec.getDescription());
        con.setPost(true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public ArrayList<Reclamation> parseListReclamationJson(String json) {

        ArrayList<Reclamation> listRec = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation rec = new Reclamation();
                // Objet User Réclamé
                Map<String, Object> resultUserOffreur =(Map<String, Object>)obj.get("userreclame");
                User u = new User();
                float id = Float.parseFloat(resultUserOffreur.get("id").toString());
                u.setId((int)id);
                u.setUsername(resultUserOffreur.get("username").toString());
                u.setFirstname(resultUserOffreur.get("firstname").toString());
                u.setLastname(resultUserOffreur.get("lastname").toString());
                // Objet User
                Map<String, Object> resultUser =(Map<String, Object>)obj.get("user");
                User u1 = new User();
                float id1 = Float.parseFloat(resultUser.get("id").toString());
                u1.setId((int)id);
                u1.setUsername(resultUser.get("username").toString());
                u1.setFirstname(resultUser.get("firstname").toString());
                u1.setLastname(resultUser.get("lastname").toString());
                // Objet Service
                Map<String, Object> resultService =(Map<String, Object>)obj.get("servicerealise");
                Service s = new Service();
                float ids = Float.parseFloat(resultService.get("id").toString());
                s.setId((int)id);
                s.setNom(resultService.get("nom").toString());
                //Objet Date Realisation 
                Map<String, Object> resultDateRealisation =(Map<String, Object>)obj.get("dateRealisation");
                Double longV = Double.parseDouble(resultDateRealisation.get("timestamp").toString());
                long millisecond = new Double(longV).longValue()*1000;
                Date d = new Date(millisecond);
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                //Objet Date Reclamation 
                Map<String, Object> resultDateReclamation =(Map<String, Object>)obj.get("DateReclamation");
                Double longV1 = Double.parseDouble(resultDateReclamation.get("timestamp").toString());
                long millisecond1 = new Double(longV1).longValue()*1000;
                Date d1 = new Date(millisecond1);
                SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
 
                rec.setUserReclamant(u1);
                rec.setUserReclame(u);
                rec.setIdServiceRealise(s);
                rec.setDateRealisation(dateformat.format(d));
                rec.setDateReclamation(dateformat1.format(d1));
                rec.setArchive((int)Float.parseFloat(obj.get("show").toString()));
                rec.setSeen((int)Float.parseFloat(obj.get("seen").toString()));
                rec.setTraite((int)Float.parseFloat(obj.get("traite").toString()));
                rec.setObjet(obj.get("object").toString());
                rec.setDescription(obj.get("description").toString());
                listRec.add(rec);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(listRec.toString());
        return listRec;
    }
     public ArrayList<Reclamation> getListReclamations(int idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/afficherReclamationsWS/"+idUser);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                listReclamation = parseListReclamationJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamation;
    }
     

}
