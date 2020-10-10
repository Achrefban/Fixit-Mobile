/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Service.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author Yassine
 */
public class ProfilForm {

    Form f;
    TextField firstname, lastname, address, city, zip_code, phone;
    private User user;

    public ProfilForm(User u) throws IOException {
        user = u;
        f = new Form("Profil", BoxLayout.y());
        EncodedImage encImage = EncodedImage.create("/load.jpg");
        String urlImage = "http://localhost/fixit/web/uploads/images/user/" + user.getImage();
        Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
        ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
        MenuToolbar menu = new MenuToolbar(user);
        f.setToolbar(menu.getMenu().getToolbar());
        Container prenom = new Container(BoxLayout.x());
        Container nom = new Container(BoxLayout.x());
        Container adresse = new Container(BoxLayout.x());
        Container ville = new Container(BoxLayout.x());
        Container codePostal = new Container(BoxLayout.x());
        Container telephone = new Container(BoxLayout.x());
        firstname = new TextField(user.getFirstname());
        lastname = new TextField(user.getLastname());
        address = new TextField(user.getAddress(), "Adresse");
        city = new TextField(user.getCity(), "Ville");
        zip_code = new TextField(user.getZip_code(), "Code postal", 5, TextArea.NUMERIC);
        phone = new TextField(user.getPhone(), "Téléphone", 12, TextArea.NUMERIC);
        zip_code.addDataChangedListener((i, ii) -> {
            if (!isValidInput(zip_code.getText()) && zip_code.getText().length() > 0) {
                zip_code.stopEditing();
                if (zip_code.getText().length() < 1) {
                    zip_code.setText("");
                } else {
                    zip_code.setText(zip_code.getText().substring(0, zip_code.getText().length() - 1));
                }
                zip_code.startEditing();
            }
        });
        phone.addDataChangedListener((i, ii) -> {
            if (!isValidInput(phone.getText()) && phone.getText().length() > 0) {
                phone.stopEditing();
                if (phone.getText().length() < 1) {
                    phone.setText("");
                } else {
                    phone.setText(phone.getText().substring(0, phone.getText().length() - 1));
                }
                phone.startEditing();
            }
        });
        Button modifier = new Button("Modifier");
        prenom.add(new Label("Prénom: ")).add(firstname);
        nom.add(new Label("Nom: ")).add(lastname);
        adresse.add(new Label("Adresse: ")).add(address);
        ville.add(new Label("Ville: ")).add(city);
        codePostal.add(new Label("Code postal: ")).add(zip_code);
        telephone.add(new Label("Téléphone: ")).add(phone);
        f.add(imgViewer).add(nom).add(prenom).add(adresse).add(ville).add(codePostal).add(telephone).add(new Label("Solde: " + user.getSolde() + " SCoins")).add(modifier);
        modifier.addActionListener((evt) -> {
            boolean erreur = false;
            String error = "";
            if (firstname.getText().length() < 2) {
                erreur = true;
                error += "Le prénom doit contenir au moins 2 caractères\n";
            }
            if (lastname.getText().length() < 2) {
                erreur = true;
                error += "Le nom doit contenir au moins 2 caractères";
            }
//            if (!zip_code.getText().matches("\\d+")){
//                erreur = true;
//                error+= "Le code postal ne doit contenir que des chiffres";
//            }
//            if (!phone.getText().matches("[0-9]+")){
//                erreur = true;
//                error+= "Le téléphone ne doit contenir que des chiffres";
//            }
            if (erreur) {
                Dialog.show("Erreur", error, "Retour", null);
            } else {
                UserService us = new UserService();
                user.setFirstname(firstname.getText());
                user.setLastname(lastname.getText());
                user.setAddress(address.getText());
                user.setCity(city.getText());
                user.setZip_code(zip_code.getText());
                user.setPhone(phone.getText());
                us.modifierUser(user);
                refresh();
            }
        });
        Button acheter=new Button("Achetez des SCoins");
        acheter.addActionListener((evt) -> {
            PaiementForm pf=new PaiementForm(user);
            pf.getF().show();
        });
        f.add(acheter);
    }

    public void refresh() {
        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        address.setText(user.getAddress());
    }

    public boolean isValidInput(String input) {

        if (input.endsWith("0") || input.endsWith("1") || input.endsWith("2") || input.endsWith("3") || input.endsWith("4")
                || input.endsWith("5") || input.endsWith("6") || input.endsWith("7") || input.endsWith("8") || input.endsWith("9")) {
            return true;
        } else {
            return false;
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
