/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Avis;
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
 * @author lenovo
 */
public class AvisService {
    Avis avis = new Avis();
    public Avis parseListAvisJson(String json)
    {
        Avis a = new Avis();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            if(list.size()!=0)
            {
                Map<String, Object> result = (Map<String, Object>) list.get(0).get("User");
            User u = new User();
            float id = Float.parseFloat(result.get("id").toString());
            u.setId((int)id);
            u.setUsername(result.get("username").toString());
            u.setFirstname(result.get("firstname").toString());
            u.setLastname(result.get("lastname").toString());
            
            a.setId((int)Float.parseFloat(list.get(0).get("id").toString()));
            a.setNote((int)Float.parseFloat(list.get(0).get("note").toString()));
            a.setSatisfaction(list.get(0).get("satisfaction").toString());
            a.setDescription(list.get(0).get("description").toString());
            a.setUser(u);
return a;
            }
                    } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(a.toString());
        return null;
    }
    public Avis getListAvis(int idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getAvisWS/" + idUser);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                avis = parseListAvisJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return avis;
    }
    
    public void modifierAvis(Avis a)
    {
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/modifierAvisWS/";
        con.setUrl(Url);
        con.setPost(true);
        con.addArgument("satisfaction",a.getSatisfaction());
        con.addArgument("Description", a.getDescription());
        con.addArgument("rating",Integer.toString(a.getNote()));
        con.addArgument("user",Integer.toString(a.getUser().getId()));
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void ajouterAvis(Avis a)
    {
        System.out.println(a.getUser().getId());
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/ajouterAvisWS/";
        con.setUrl(Url);
        con.setPost(true);
        con.addArgument("satisfaction",a.getSatisfaction());
        con.addArgument("Description", a.getDescription());
        con.addArgument("rating",Integer.toString(a.getNote()));
        con.addArgument("user",Integer.toString(a.getUser().getId()));
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
