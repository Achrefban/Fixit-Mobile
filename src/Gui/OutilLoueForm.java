/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Outil;
import Entities.User;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author SL-WASSIM
 */
public class OutilLoueForm {
    User user;
    Form detail;

    public OutilLoueForm(User user, Outil o) {
        try {
            detail = new Form("Détail Outil");
            MenuToolbar menu = new MenuToolbar(user);
            detail.setToolBar(menu.getMenu().getToolbar());
            this.user = user;
            Label solde = new Label("Vous avez " + user.getSolde() + " Scoins");
            solde.setAlignment(4);
            EncodedImage encImage;
            encImage = EncodedImage.create("/load.jpg");
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
            Label l = new Label("Vous avez déjà loué cet outil");
            Button BTNretour = new Button("Retour");

            BTNretour.addActionListener((evt) -> {
                EspaceOutilForm of = new EspaceOutilForm(user);
                of.getOutil().showBack();
            });
          
            Container v = new Container(BoxLayout.y());
            
            v.add(solde).add(nom).add(imgViewer).add(categorie).add(adresse).add(prix).add(l).add(BTNretour);
            detail.add(v);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Form getDetail() {
        return detail;
    }

    public void setDetail(Form detail) {
        this.detail = detail;
    }
    
}
