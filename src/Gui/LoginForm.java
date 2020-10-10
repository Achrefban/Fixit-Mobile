/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Service.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Yassine
 */
public class LoginForm {
    Form f;
    TextField login;
    TextField password;
    Button connexion,inscription;
    public LoginForm(){
        f=new Form("Connexion", BoxLayout.y());
        login=new TextField("","Email");
        password=new TextField("","Mot de passe",30,TextField.PASSWORD);
        connexion = new Button("Connexion");
        inscription = new Button("S'inscrire");
        f.add(login);
        f.add(password);
        f.add(connexion);
        f.add(inscription);
        connexion.addActionListener((evt) -> {
            UserService us=new UserService();
            if (us.login(login.getText(), password.getText())==null)
            {
                Dialog.show("Erreur", "Vérifiez vos données", "Retour", null);
            }
            else
            {
                //Dialog.show("Connexion", "Connexion avec succès", "Retour", null);
                AccueilForm af=new AccueilForm(us.login(login.getText(), password.getText()));
                af.getAccueil().show();
            }
        });
        inscription.addActionListener((evt) -> {
            InscriptionForm insf=new InscriptionForm();
            insf.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
