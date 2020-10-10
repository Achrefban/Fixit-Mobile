/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.CategorieOutil;
import Entities.Outil;
import Entities.User;
import Entities.UserOutil;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SL-WASSIM
 */
public class OutilService {

    public ArrayList<Outil> parseListOutilJson(String json) {

        ArrayList<Outil> listOutilsJson = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> outils = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) outils.get("root");
            for (Map<String, Object> obj : list) {
                CategorieOutil co = new CategorieOutil();
                Map<String, Object> listCategorie = (Map<String, Object>) obj.get("CategorieOutils");
                    float idC = Float.parseFloat(listCategorie.get("id").toString());
                    co.setId((int) idC);
                    co.setNom(listCategorie.get("nom").toString());
                    co.setLogo(listCategorie.get("logo").toString());
                
                Outil o = new Outil();
                float id = Float.parseFloat(obj.get("id").toString());
                o.setId((int) id);
                o.setNom(obj.get("nom").toString());
                o.setQuantite((int)Float.parseFloat(obj.get("quantite").toString()));
                o.setC(co);
                o.setDureeMaximale((int)Float.parseFloat(obj.get("dureeMaximale").toString()));
                o.setPrix((int)Float.parseFloat(obj.get("prix").toString()));
                o.setAdresse(obj.get("adresse").toString());
                o.setCodePostal((int)Float.parseFloat(obj.get("codePostal").toString()));
                o.setVille(obj.get("ville").toString());
                o.setImage(obj.get("image").toString());
                listOutilsJson.add(o);
            }

        } catch (IOException ex) {
        }
        return listOutilsJson;

    }

    ArrayList<Outil> listOutils = new ArrayList<>();

    public ArrayList<Outil> getOutil() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/affichageOutilsWS");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                OutilService ser = new OutilService();
                listOutils = ser.parseListOutilJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listOutils;
    }
    
    ArrayList<Outil> listOutilsD = new ArrayList<>();

    public ArrayList<Outil> getOutilD() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/outilDisponibleWS");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                OutilService ser = new OutilService();
                listOutilsD = ser.parseListOutilJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listOutilsD;
    }
    public UserOutil parseUserOutilJson(String json) {

        UserOutil uo = new UserOutil();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> uoJ = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) uoJ.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                Map<String, Object> listUser = (Map<String, Object>) obj.get("idUser");
                if (listUser.get("id") == null) {
                    u.setId(0);
                } else {
                    u.setId((int)Float.parseFloat(listUser.get("id").toString()));
                    uo.setUser(u);
                }
            }
        } catch (IOException ex) {
        }
        return uo;
    }
    UserOutil uo = new UserOutil();
    public UserOutil getUserOutil(User user, Outil outil) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/detailOutilWS");
        con.addArgument("outil", Integer.toString(outil.getId()));
        con.addArgument("user", Integer.toString(user.getId()));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if((new String(con.getResponseData())).equals("200")){
                    User test = new User(); test.setId(0);
                    uo.setUser(test);
                }else{
                OutilService ser = new OutilService();
                uo = ser.parseUserOutilJson(new String(con.getResponseData()));
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return uo;
    }
    public void Louer(User u, Outil o ,String total ,String l, String r)
    {
    ConnectionRequest con = new ConnectionRequest();
    String Url = "http://localhost/fixit/web/app_dev.php/louerWS";
    con.setUrl(Url);
    con.addArgument("outil", Integer.toString(o.getId()));
    con.addArgument("user", Integer.toString(u.getId()));
    con.addArgument("total", total);
    con.addArgument("dateLocation", l);
    con.addArgument("dateRetour", r);
    con.setPost(true);
    con.addResponseListener((e) -> {
    String str = new String(con.getResponseData());
    System.out.println(str);
    
    });
    NetworkManager.getInstance().addToQueueAndWait(con);
    
    }
public ArrayList<Outil> rechercheOutil(String rech) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/rechercheOutilWS");
        con.setPost(true);
        con.addArgument("nom", String.valueOf(rech));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                listOutils = parseListOutilJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listOutils;
    }
}
