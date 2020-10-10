/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Reclamation;
import Entities.User;
import Gui.LoginForm;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yassine
 */
public class UserService {

    private ArrayList<User> listUser;

    public User login(String email, String password) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getUserWs/");
        con.setPost(true);
        con.addArgument("email", email);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                listUser = parseListUserJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (listUser.size() < 1) {
            return null;
        } else {
            for (User u : listUser) {
                String crypted = "$2a" + u.getPassword().substring(3);
                if (BCrypt.checkpw(password, crypted)) {
                    return u;
                }
            }
        }
        return null;
    }
    public boolean checkEmail(String email) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getUserByEmailWs/");
        con.setPost(true);
        con.addArgument("email", email);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                listUser = parseListUserJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (listUser.size() < 1) {
            return false;
        } else {
            return true;
        }
    }
    
    public String createPwd(String password) {
        String newPass;
        newPass = BCrypt.hashpw(password, BCrypt.gensalt());
        return newPass;
    }
    
    public boolean checkUsername(String username) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getUserByUsernameWs/");
        con.setPost(true);
        con.addArgument("username", username);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                listUser = parseListUserJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (listUser.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public User getUserById(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getUserByIdWs/");
        con.setPost(true);
        con.addArgument("id", String.valueOf(id));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                listUser = parseUserJson(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (listUser.size() < 1) {
            return null;
        } else {
            for (User u : listUser) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> rechercheUser(String rech) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/getUsersWs/");
        con.setPost(true);
        con.addArgument("nom", String.valueOf(rech));
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
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                if (obj.get("address") == null) {
                    u.setAddress("");
                } else {
                    u.setAddress(obj.get("address").toString());
                }
                if (obj.get("city") == null) {
                    u.setCity("");
                } else {
                    u.setCity(obj.get("city").toString());
                }
                if (obj.get("zipCode") == null) {
                    u.setZip_code("");
                } else {
                    u.setZip_code(obj.get("zipCode").toString());
                }
                if (obj.get("phone") == null) {
                    u.setPhone("");
                } else {
                    u.setPhone(obj.get("phone").toString());
                }
                if (obj.get("image") == null) {
                    u.setImage("");
                } else {
                    u.setImage(obj.get("image").toString());
                }
                u.setSolde((int) Float.parseFloat(obj.get("solde").toString()));
                listU.add(u);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return listU;
    }

    public ArrayList<User> parseUserJson(String json) {

        ArrayList<User> listU = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //Map<String, Object> obj = (Map<String, Object>) tasks.get("root");
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setUsername(obj.get("username").toString());
                u.setFirstname(obj.get("firstname").toString());
                u.setLastname(obj.get("lastname").toString());
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                if (obj.get("address") == null) {
                    u.setAddress("");
                } else {
                    u.setAddress(obj.get("address").toString());
                }
                if (obj.get("city") == null) {
                    u.setCity("");
                } else {
                    u.setCity(obj.get("city").toString());
                }
                if (obj.get("zipCode") == null) {
                    u.setZip_code("");
                } else {
                    u.setZip_code(obj.get("zipCode").toString());
                }
                if (obj.get("phone") == null) {
                    u.setPhone("");
                } else {
                    u.setPhone(obj.get("phone").toString());
                }
                if (obj.get("image") == null) {
                    u.setImage("");
                } else {
                    u.setImage(obj.get("image").toString());
                }
                u.setSolde((int) Float.parseFloat(obj.get("solde").toString()));
                listU.add(u);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return listU;
    }

    public void modifierUser(User u) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/modifierUserWs/");
        con.setPost(true);
        con.addArgument("firstname", u.getFirstname());
        con.addArgument("lastname", u.getLastname());
        con.addArgument("address", u.getAddress());
        con.addArgument("city", u.getCity());
        con.addArgument("zipCode", u.getZip_code());
        con.addArgument("phone", u.getPhone());
        con.addArgument("id", String.valueOf(u.getId()));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                if (str.equals("200")) {
                    Dialog.show("Modification", "Vos données ont été mise à jour", "Retour", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void modifierSoldeUser(User u,int montant) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/modifierSoldeUserWs/");
        con.setPost(true);
        con.addArgument("montant", String.valueOf(montant+u.getSolde()));
        con.addArgument("id", String.valueOf(u.getId()));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void inscription(User u) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/inscriptionUserWs/");
        con.setPost(true);
        u.setPassword(createPwd(u.getPassword()));
        con.addArgument("email", u.getEmail());
        con.addArgument("email_canonical", u.getEmail_canonical());
        con.addArgument("username", u.getUsername());
        con.addArgument("username_canonical", u.getUsername_canonical());
        con.addArgument("password", u.getPassword());
        con.addArgument("firstname", u.getFirstname());
        con.addArgument("lastname", u.getLastname());
        con.addArgument("address", u.getAddress());
        con.addArgument("city", u.getCity());
        con.addArgument("zipCode", u.getZip_code());
        con.addArgument("phone", u.getPhone());
        con.addArgument("solde", "0");
        con.addArgument("enabled", "1");
        con.addArgument("roles", u.getRoles());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                if (str.equals("200")) {
                    Dialog.show("Inscription", "Votre inscription a été éffectuée avec succès", "Retour", null);
                    LoginForm lf=new LoginForm();
                    lf.getF().showBack();
                }
                else{
                    Dialog.show("Inscription", "Une erreur est survenue", "Retour", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
