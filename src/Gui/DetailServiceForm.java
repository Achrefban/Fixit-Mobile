/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Service;
import Entities.User;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author SELON
 */
public class DetailServiceForm {

    User user;
    Form detail;

    public DetailServiceForm(User user, Service s) {

        try {
            detail = new Form("Détail Service");
            MenuToolbar menu = new MenuToolbar(user);
            detail.setToolBar(menu.getMenu().getToolbar());
            this.user = user;
            Label nom = new Label(s.getNom());
            EncodedImage encImage;
            encImage = EncodedImage.create("/load.jpg");
            //String n = (String) s.getNom();
            //Label nom = new Label(n);
            String nomCat = (String) s.getCategorie().getNom();
            String urlCategorie = "http://localhost/fixit/web/uploads/images/categorieService/" + s.getCategorie().getImage();
            Image image = URLImage.createToStorage(encImage, urlCategorie, urlCategorie, URLImage.RESIZE_SCALE);
            Label categorieImg = new Label(nomCat, image.scaled(120, 120));
            String nbrP = Integer.toString(s.getNbrProviders()) + " réalisateur";
            String urlAdresse = "http://localhost/fixit/web/service/providers.png";
            Image providersIcon = URLImage.createToStorage(encImage, urlAdresse, urlAdresse, URLImage.RESIZE_SCALE);
            Label providers = new Label(nbrP, providersIcon.scaled(120, 120));
            //String p = Integer.toString(s.getPrix());
            //String urlScoin = "http://localhost/fixit/web/service/images/icons/scoinM.png";
            //Image scoin = URLImage.createToStorage(encImage, urlScoin, urlScoin, URLImage.RESIZE_SCALE);
            //Label prix = new Label(p, scoin.scaled(120, 120));
            String urlImage = "http://localhost/fixit/web/uploads/images/service/" + s.getImage();
            Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
            ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
            Container v = new Container(BoxLayout.y());
            Container h = new Container(BoxLayout.x());
            h.add(imgViewer).add(s.getDescription());
            v.add(nom).add(h).add(providers).add(categorieImg).add(s.getCategorie().getDescription());
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
