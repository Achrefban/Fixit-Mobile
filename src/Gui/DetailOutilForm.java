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
import java.util.concurrent.TimeUnit;

/**
 *
 * @author SL-WASSIM
 */
public class DetailOutilForm {

    User user;
    Form detail;

    public DetailOutilForm(User user, Outil o) {

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
            String urlDate = "http://localhost/fixit/web/service/images/icons/date.png";
            Image dateIcon = URLImage.createToStorage(encImage, urlDate, urlDate, URLImage.RESIZE_SCALE);
            Label location = new Label("La date de location");
            Picker dateLocation = new Picker();
            dateLocation.setType(Display.PICKER_TYPE_DATE);
            dateLocation.setIcon(dateIcon.scaled(120, 120));
            Container hLocation = new Container(BoxLayout.x());
            hLocation.add(location).add(dateLocation);
            Label retour = new Label("La date de retour");
            Picker dateRetour = new Picker();
            dateRetour.setType(Display.PICKER_TYPE_DATE);
            dateRetour.setIcon(dateIcon.scaled(120, 120));
            Container hRetour = new Container(BoxLayout.x());
            hRetour.add(retour).add(dateRetour);
            Container v = new Container(BoxLayout.y());
            Label total = new Label("0");
            Label s = new Label("Scoins");
            Label payer =new Label("Le prix total:");
            dateLocation.addActionListener((evt) -> {
                total.setText(Integer.toString((int)(compareDates(dateLocation.getDate(), dateRetour.getDate())*o.getPrix())));
            });
            dateRetour.addActionListener((evt) -> {
                total.setText(Integer.toString((int)(compareDates(dateLocation.getDate(), dateRetour.getDate())*o.getPrix())));
            });
            Container hPrix = new Container(BoxLayout.x());
            hPrix.add(payer).add(total).add(s);
            Button BTNlouer = new Button("Louer");
            BTNlouer.addActionListener((evt) -> {
            Date today=new Date();
            if (compareDates(dateLocation.getDate(), dateRetour.getDate())<0 || compareDates(dateLocation.getDate(), today)>0) {
                Dialog.show("Erreur", "Vérifiez vos données", "Retour", null);
            }
            else if (user.getSolde()<Integer.parseInt(total.getText())){
                Dialog.show("Erreur", "Solde insuffisant", "Retour", null);
            }else{
                OutilService os = new OutilService();
                os.Louer(user, o, total.getText(), dateLocation.getDate().toString(), dateRetour.getDate().toString());
                EspaceOutilForm of = new EspaceOutilForm(user);
                of.getOutil().showBack();
            }
            });
            Button BTNretour = new Button("Retour");

            BTNretour.addActionListener((evt) -> {
                EspaceOutilForm of = new EspaceOutilForm(user);
                of.getOutil().showBack();
            });
            Container hButton = new Container(BoxLayout.x());
            hButton.add(BTNlouer).add(BTNretour);
            v.add(solde).add(nom).add(imgViewer).add(categorie).add(adresse).add(prix).add(hLocation).add(hRetour).add(hPrix).add(hButton);
            detail.add(v);

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public long compareDates(Date date1,Date date2){
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        if (date2.getTime() < date1.getTime()){
            return 0-diff;
        }
        else return diff;
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
