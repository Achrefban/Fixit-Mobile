/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author SL-WASSIM
 */
public class AccueilForm {

    Form Accueil ;
    private User user;

    public AccueilForm(User u) {
        user=u;
        Accueil = new Form("Accueil",BoxLayout.y());
        MenuToolbar menu = new MenuToolbar(user);
        Accueil.setToolBar(menu.getMenu().getToolbar());
        Button recherche=new Button("Trouvez quelqu'un");
        recherche.addActionListener((evt) -> {
            RechercheUserForm ruf=new RechercheUserForm(user);
            ruf.getF().show();
        });
      OutilDisponible od =new OutilDisponible();
        Accueil.add(recherche).add(od.getGlobal());
       // Accueil.setScrollableX(false);
    }

    public Form getAccueil() {
        return Accueil;
    }

    public void setAccueil(Form Accueil) {
        this.Accueil = Accueil;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

}
