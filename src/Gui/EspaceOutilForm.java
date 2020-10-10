/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Outil;
import Entities.User;
import Service.OutilService;
import Service.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author SL-WASSIM
 */
public class EspaceOutilForm {

    User user;
    Form outil;
    TextField recherche;
    Container ic;

    public EspaceOutilForm(User u) {
        outil = new Form("Espace Outil",BoxLayout.y());
        this.user = u;
        UserService us = new UserService();
                    this.user=us.getUserById(this.user.getId());
        MenuToolbar menu = new MenuToolbar(user);
        outil.setToolBar(menu.getMenu().getToolbar());
        recherche = new TextField("", "Recherche...");
        OutilService os = new OutilService();
        ArrayList<Outil> data = os.getOutil();
         ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    amount = data.size();
                }
                if (amount + index > data.size()) {
                    amount = data.size() - index;
                }
                if (index < 0) {
                    return null;
                }
                Component[] cmps = new Component[amount];
                for (int iter = 0; iter < cmps.length; iter++) {
                    Outil o = data.get(iter);
                    if (o == null) {
                        return null;
                    }
                    try {
                        EncodedImage encImage = EncodedImage.create("/load.jpg");
                        String n = (String) o.getNom();
                        Label nom = new Label(n);
                        String c = (String) o.getC().getNom();
                        String urlCategorie = "http://localhost/fixit/web/uploads/images/categorieOutil/" + o.getC().getLogo();
                        Image logo = URLImage.createToStorage(encImage, urlCategorie, urlCategorie, URLImage.RESIZE_SCALE);
                        Label categorie = new Label(c, logo.scaled(120, 120));
                        String a = (String) o.getAdresse() + " " + o.getVille();
                        String urlAdresse = "http://localhost/fixit/web/service/images/icons/adresse.png";
                        Image adresseIcon = URLImage.createToStorage(encImage, urlAdresse, urlAdresse, URLImage.RESIZE_SCALE);
                        Label adresse = new Label(a, adresseIcon.scaled(120, 120));
                        String p = Integer.toString(o.getPrix());
                        String urlScoin = "http://localhost/fixit/web/service/images/icons/scoinM.png";
                        Image scoin = URLImage.createToStorage(encImage, urlScoin, urlScoin, URLImage.RESIZE_SCALE);
                        Label prix = new Label(p, scoin.scaled(120, 120));
                        String urlImage = "http://localhost/fixit/web/uploads/images/outil/" + o.getImage();
                        Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                        ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
                        String urlDisponible = "";
                        Button detail = new Button("Details");
                        if (o.getQuantite() > 0) {
                            urlDisponible = "http://localhost/fixit/web/service/images/icons/disponible.png";
                        } else {
                            urlDisponible = "http://localhost/fixit/web/service/images/icons/indisponible.png";
                            detail.setEnabled(false);
                        }
                        detail.addActionListener((evt) -> {
                            if(os.getUserOutil(user, o).getUser().getId()==user.getId()){
                                OutilLoueForm olf = new OutilLoueForm(user,o);
                                olf.getDetail().show();
                            }else{
                            DetailOutilForm dof = new DetailOutilForm(user,o);
                            dof.getDetail().show();
                            }
                        });
                        Image imgDisponible = URLImage.createToStorage(encImage, urlDisponible, urlDisponible, URLImage.RESIZE_SCALE);
                        ImageViewer disponibleViewer = new ImageViewer(imgDisponible.scaled(500, 100));
                        

                        Container v = new Container(BoxLayout.y());
                        v.add(nom).add(categorie).add(adresse).add(prix).add(detail);
                        Container vImage = new Container(BoxLayout.y());
                        vImage.add(imgViewer).add(disponibleViewer);
                        Container h = new Container(BoxLayout.x());
                        h.add(vImage);
                        h.add(v);
                        cmps[iter] = (h);
                    } catch (IOException ex) {

                        System.out.println(ex);
                    }
                }
                return cmps;

            }
        };
        
        recherche.addDataChangedListener((type, index) -> {
            update();
        });
        ic.setScrollableY(false);
        outil.add(recherche).add(ic);
    }
    public void update() {
        OutilService os = new OutilService();
        ArrayList<Outil> data;
        if (recherche.getText().length()>0)
            data = os.rechercheOutil(recherche.getText());
        else
            data = os.rechercheOutil("");
        ic.remove();
        ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    amount = data.size();
                }
                if (amount + index > data.size()) {
                    amount = data.size() - index;
                }
                if (index < 0) {
                    return null;
                }
                Component[] cmps = new Component[amount];
                for (int iter = 0; iter < cmps.length; iter++) {
                    Outil o = data.get(iter);
                    if (o == null) {
                        return null;
                    }
                    try {
                        EncodedImage encImage = EncodedImage.create("/load.jpg");
                        String n = (String) o.getNom();
                        Label nom = new Label(n);
                        String c = (String) o.getC().getNom();
                        String urlCategorie = "http://localhost/fixit/web/uploads/images/categorieOutil/" + o.getC().getLogo();
                        Image logo = URLImage.createToStorage(encImage, urlCategorie, urlCategorie, URLImage.RESIZE_SCALE);
                        Label categorie = new Label(c, logo.scaled(120, 120));
                        String a = (String) o.getAdresse() + " " + o.getVille();
                        String urlAdresse = "http://localhost/fixit/web/service/images/icons/adresse.png";
                        Image adresseIcon = URLImage.createToStorage(encImage, urlAdresse, urlAdresse, URLImage.RESIZE_SCALE);
                        Label adresse = new Label(a, adresseIcon.scaled(120, 120));
                        String p = Integer.toString(o.getPrix());
                        String urlScoin = "http://localhost/fixit/web/service/images/icons/scoinM.png";
                        Image scoin = URLImage.createToStorage(encImage, urlScoin, urlScoin, URLImage.RESIZE_SCALE);
                        Label prix = new Label(p, scoin.scaled(120, 120));
                        String urlImage = "http://localhost/fixit/web/uploads/images/outil/" + o.getImage();
                        Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                        ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
                        String urlDisponible = "";
                        Button detail = new Button("Details");
                        if (o.getQuantite() > 0) {
                            urlDisponible = "http://localhost/fixit/web/service/images/icons/disponible.png";
                        } else {
                            urlDisponible = "http://localhost/fixit/web/service/images/icons/indisponible.png";
                            detail.setEnabled(false);
                        }
                        detail.addActionListener((evt) -> {
                            if(os.getUserOutil(user, o).getUser().getId()==user.getId()){
                                OutilLoueForm olf = new OutilLoueForm(user,o);
                                olf.getDetail().show();
                            }else{
                            DetailOutilForm dof = new DetailOutilForm(user,o);
                            dof.getDetail().show();
                            }
                        });
                        Image imgDisponible = URLImage.createToStorage(encImage, urlDisponible, urlDisponible, URLImage.RESIZE_SCALE);
                        ImageViewer disponibleViewer = new ImageViewer(imgDisponible.scaled(500, 100));
                        

                        Container v = new Container(BoxLayout.y());
                        v.add(nom).add(categorie).add(adresse).add(prix).add(detail);
                        Container vImage = new Container(BoxLayout.y());
                        vImage.add(imgViewer).add(disponibleViewer);
                        Container h = new Container(BoxLayout.x());
                        h.add(vImage);
                        h.add(v);
                        cmps[iter] = (h);
                    } catch (IOException ex) {

                        System.out.println(ex);
                    }
                }
                return cmps;

            }
        };
        outil.add(ic);
        outil.revalidate();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Form getOutil() {
        return outil;
    }

    public void setOutil(Form outil) {
        this.outil = outil;
    }

}
