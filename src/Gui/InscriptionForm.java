/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Service.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Yassine
 */
public class InscriptionForm {
    Form f;
    TextField firstname, lastname, address, city, zip_code, phone,email,username,password,passwordV;
    private User user;

    public InscriptionForm() {
        user = new User();
        f = new Form("Inscription", BoxLayout.y());
        MenuToolbar menu = new MenuToolbar(user);
        f.setToolbar(menu.getMenu().getToolbar());
        Container pseudo = new Container(BoxLayout.x());
        Container email1 = new Container(BoxLayout.x());
        Container prenom = new Container(BoxLayout.x());
        Container nom = new Container(BoxLayout.x());
        Container adresse = new Container(BoxLayout.x());
        Container ville = new Container(BoxLayout.x());
        Container codePostal = new Container(BoxLayout.x());
        Container telephone = new Container(BoxLayout.x());
        Container mdp = new Container(BoxLayout.x());
        Container mdpV = new Container(BoxLayout.x());
        username = new TextField("","Pseudo");
        email = new TextField("","Email");
        firstname = new TextField(user.getFirstname(), "Prénom");
        lastname = new TextField(user.getLastname(), "Nom");
        address = new TextField(user.getAddress(), "Adresse");
        city = new TextField(user.getCity(), "Ville");
        zip_code = new TextField(user.getZip_code(), "Code postal");
        phone = new TextField(user.getPhone(), "Téléphone");
        password=new TextField("","Mot de passe",30,TextField.PASSWORD);
        passwordV=new TextField("","Vérification du mot de passe",30,TextField.PASSWORD);
        zip_code.addDataChangedListener((i, ii) -> {
            if (!isValidInput(zip_code.getText()) && zip_code.getText().length()>0) {
                zip_code.stopEditing();
                if (zip_code.getText().length()<1)
                    zip_code.setText("");
                else zip_code.setText(zip_code.getText().substring(0, zip_code.getText().length()-1));
                zip_code.startEditing();
            }
        });
        phone.addDataChangedListener((i, ii) -> {
            if (!isValidInput(phone.getText()) && phone.getText().length()>0) {
                phone.stopEditing();
                if (phone.getText().length()<1)
                    phone.setText("");
                else phone.setText(phone.getText().substring(0, phone.getText().length()-1));
                phone.startEditing();
            }
        });
        Button confirmer = new Button("Inscription");
        pseudo.add(new Label("Pseudo: ")).add(username);
        email1.add(new Label("Email: ")).add(email);
        prenom.add(new Label("Prénom: ")).add(firstname);
        nom.add(new Label("Nom: ")).add(lastname);
        adresse.add(new Label("Adresse: ")).add(address);
        ville.add(new Label("Ville: ")).add(city);
        codePostal.add(new Label("Code postal: ")).add(zip_code);
        telephone.add(new Label("Téléphone: ")).add(phone);
        mdp.add(new Label("Mot de passe: ")).add(password);
        mdpV.add(new Label("Vérification: ")).add(passwordV);
        f.add(pseudo).add(email1).add(nom).add(prenom).add(adresse).add(ville).add(codePostal).add(telephone).add(mdp).add(mdpV).add(confirmer);
        confirmer.addActionListener((evt) -> {
            UserService us = new UserService();
            boolean erreur = false;
            String error = "";
            if (username.getText().length() < 2) {
                erreur = true;
                error += "Le pseudo doit contenir au moins 2 caractères\n";
            }
            if (!validEmail(email.getText())) {
                erreur = true;
                error += "L'email est invalide\n";
            }
            if (firstname.getText().length() < 2) {
                erreur = true;
                error += "Le prénom doit contenir au moins 2 caractères\n";
            }
            if (lastname.getText().length() < 2) {
                erreur = true;
                error += "Le nom doit contenir au moins 2 caractères\n";
            }
            if (us.checkEmail(email.getText())){
                erreur = true;
                error += "L'email éxiste déjà";
            }
            if (us.checkUsername(username.getText())){
                erreur = true;
                error += "Le pseudo éxiste déjà";
            }
            if (!password.getText().equals(passwordV.getText())){
                erreur = true;
                error += "Les mots de passes ne correspondent pas";
            }
            if (password.getText().length() < 4) {
                erreur = true;
                error += "Le mot de passe doit dépasser 4 caractères";
            }
            if (erreur) {
                Dialog.show("Erreur", error, "Retour", null);
            } else {
                user.setEmail(email.getText());
                user.setEmail_canonical(email.getText().toLowerCase());
                user.setUsername(username.getText());
                user.setUsername_canonical(username.getText().toLowerCase());
                user.setPassword(password.getText());
                user.setFirstname(firstname.getText());
                user.setLastname(lastname.getText());
                user.setAddress(address.getText());
                user.setCity(city.getText());
                user.setZip_code(zip_code.getText());
                user.setPhone(phone.getText());
                user.setRoles("a:0:{}");
                user.setEnabled(1);
                user.setSolde(0);
                us.inscription(user);
            }
        });
    }

    public boolean validEmail(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean isValidInput(String input) {

        if (input.endsWith("0")||input.endsWith("1")||input.endsWith("2")||input.endsWith("3")||input.endsWith("4")||
                input.endsWith("5")||input.endsWith("6")||input.endsWith("7")||input.endsWith("8")||input.endsWith("9")) {
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
}
