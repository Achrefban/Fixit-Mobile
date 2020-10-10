/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import java.io.IOException;

/**
 *
 * @author SL-WASSIM
 */
public class MenuToolbar {
    Form menu;
    private User user;

    public MenuToolbar(User u) {
        user=u;
        menu= new Form();
        menu.getToolbar().addCommandToSideMenu("Accueil", null, ev -> {
            AccueilForm sf = new AccueilForm(user);
            sf.getAccueil().show();
        });
        menu.getToolbar().addCommandToSideMenu("Espace Service", null, ev -> {
            EspaceServiceForm sf = new EspaceServiceForm(user);
            sf.getService().show();
            
        });
        menu.getToolbar().addCommandToSideMenu("Espace Outil", null, ev -> {
            EspaceOutilForm of = new EspaceOutilForm(this.user);
            of.getOutil().show();
        });
        menu.getToolbar().addCommandToSideMenu("Espace Produit", null, ev -> {
               AfficherProduit of = new AfficherProduit(user);
            of.getProduit().show();
        });
        menu.getToolbar().addCommandToSideMenu("Espace Annoce", null, ev -> {
            MesAnnoncesForm f = new MesAnnoncesForm(u);
            f.getAnnonce().show();
        });
        menu.getToolbar().addCommandToSideMenu("Espace Réclamations", null, ev -> {
            ajouterReclamationForm f = new ajouterReclamationForm(this.user);
            f.getF().show();
        });
        menu.getToolbar().addCommandToSideMenu("Espace Avis", null, ev -> {
            EspaceAvisForm f = new EspaceAvisForm(this.user);
            f.getF().show();
        });
        menu.getToolbar().addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_SETTINGS, ev -> {
            try {
                ProfilForm pf=new ProfilForm(user);
                pf.getF().show();
            } catch (IOException ex) {
                System.err.println("error");
            }
        });
        menu.getToolbar().addMaterialCommandToSideMenu("Déconnexion", FontImage.MATERIAL_CLOSE, ev -> {
                LoginForm lf=new LoginForm();
                lf.getF().showBack();
        });
    }

    public Form getMenu() {
        return menu;
    }

    public void setMenu(Form menu) {
        this.menu = menu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
