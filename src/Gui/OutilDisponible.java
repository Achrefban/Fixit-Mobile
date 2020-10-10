/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Outil;
import Entities.User;
import Service.OutilService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author SL-WASSIM
 */
public class OutilDisponible {
Container global;
    public OutilDisponible() {
         global = new Container(BoxLayout.x());
        
        OutilService os = new OutilService();
        ArrayList<Outil> data = os.getOutilD();
        for (int iter = 0; iter < data.size(); iter++) {
                    Outil o = data.get(iter);
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
                        

                        Container v = new Container(BoxLayout.y());
                        v.add(nom).add(categorie).add(adresse).add(prix);
                        Container vImage = new Container(BoxLayout.y());
                        vImage.add(imgViewer);
                        Container h = new Container(BoxLayout.x());
                        h.add(vImage);
                        h.add(v);
                        global.add(h);
                    } catch (IOException ex) {

                        System.out.println(ex);
                    }
                }
        
        global.setScrollableX(true);
    }

    public Container getGlobal() {
        return global;
    }

    public void setGlobal(Container global) {
        this.global = global;
    }

   
    
}
