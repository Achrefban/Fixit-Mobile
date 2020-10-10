/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Service.UserService;
import com.codename1.charts.compat.Paint;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Yassine
 */
public class RechercheUserForm {

    Form f;
    User user;
    TextField recherche;
    InfiniteContainer ic;

    public RechercheUserForm(User user) {
        this.user = user;
        f = new Form("Recherche", BoxLayout.y());
        MenuToolbar menu = new MenuToolbar(user);
        f.setToolbar(menu.getMenu().getToolbar());
        recherche = new TextField("", "Recherche...");
        f.add(recherche);
        UserService us = new UserService();
        ArrayList<User> data = us.rechercheUser("");
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
                for (int iter = index; iter < amount; iter++) {
                    try {
                        User u = data.get(iter);
                        if (u == null) {
                            return null;
                        }
                        EncodedImage encImage = EncodedImage.create("/user.png");
                        encImage.scale(400, 400);
                        String thumb_url = u.getImage();
                        String prenom = u.getFirstname();
                        String nom = u.getLastname();
                        //i.scaled(60, 60);
                        String urlImage = "http://localhost/fixit/web/uploads/images/user/" + u.getImage();
                        Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                        ImageViewer imgViewer = new ImageViewer(img.scaled(400, 400));

                        Container element = new Container(BoxLayout.y());
                        element.add(imgViewer);
                        Container infos = new Container(BoxLayout.y());
                        Label nomprenom = new Label(prenom + " " + nom);
                        nomprenom.setAlignment(Paint.Align.CENTER);
                        infos.add(nomprenom);
                        element.add(infos);
                        Button afficher = new Button();
                        afficher.addActionListener((evt) -> {
                            Dialog.show("Produit", nom, "Ok", null);
                        });
                        element.setLeadComponent(afficher);
                        cmps[iter] = element;
                    } catch (IOException ex) {
                        //Logger.getLogger(RechercheUserForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return cmps;
            }
        };
        ic.setScrollableY(false);
        recherche.addDataChangedListener((type, index) -> {
            update();
        });
        f.add(ic);
    }

    public void update() {
        UserService us = new UserService();
        ArrayList<User> data;
        if (recherche.getText().length()>0)
            data = us.rechercheUser(recherche.getText());
        else
            data = us.rechercheUser("");
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
                for (int iter = index; iter < amount; iter++) {
                    try {
                        User u = data.get(iter);
                        if (u == null) {
                            return null;
                        }
                        EncodedImage encImage = EncodedImage.create("/user.png");
                        encImage.scale(400, 400);
                        String thumb_url = u.getImage();
                        String prenom = u.getFirstname();
                        String nom = u.getLastname();
                        //i.scaled(60, 60);
                        String urlImage = "http://localhost/fixit/web/uploads/images/user/" + u.getImage();
                        Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                        ImageViewer imgViewer = new ImageViewer(img.scaled(400, 400));

                        Container element = new Container(BoxLayout.y());
                        element.add(imgViewer);
                        Container infos = new Container(BoxLayout.y());
                        Label nomprenom = new Label(prenom + " " + nom);
                        nomprenom.setAlignment(Paint.Align.CENTER);
                        infos.add(nomprenom);
                        element.add(infos);
                        Button afficher = new Button();
                        afficher.addActionListener((evt) -> {
                            Dialog.show("Produit", nom, "Ok", null);
                        });
                        element.setLeadComponent(afficher);
                        cmps[iter] = element;
                    } catch (IOException ex) {
                        //Logger.getLogger(RechercheUserForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return cmps;
            }
        };
        f.add(ic);
        f.revalidate();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
