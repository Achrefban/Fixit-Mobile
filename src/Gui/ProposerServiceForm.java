/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.Service;
import Entities.ServicesProposes;
import Entities.User;
import Service.ServiceService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author SELON
 */
public class ProposerServiceForm {
    User user;
    Form proposerService;
    TextField nom;
    TextField description;
    ComboBox<CategorieService> comboProp;

    public ProposerServiceForm(User user) {
        try {
            
            proposerService = new Form("Ajouter un service");
            MenuToolbar menu = new MenuToolbar(user);
            proposerService.setToolBar(menu.getMenu().getToolbar());
            this.user = user;
            comboProp = new ComboBox<CategorieService>();
            EncodedImage encImage;
            encImage = EncodedImage.create("/load.jpg");
            ServiceService ss =new ServiceService();
            System.out.println("--------");
            System.out.println(ss.getListCategorie());
            comboboxCategorie();
         
           description=new TextField("","Descriprion");
            nom=new TextField("","Nom");
            Button valider =new Button("Valider");
            valider.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(ActionEvent evt) {
                ajouterService();
                
            }
        });
        
            proposerService.add(comboProp);
            proposerService.add(nom);
            proposerService.add(description);
            proposerService.add(valider);
            

        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        
    }
         public void ajouterService() {

        try {
            ServicesProposes sp = new ServicesProposes();
            ServiceService ss = new ServiceService();
            sp.setNom(nom.getText());
            sp.setCategorieService(comboProp.getSelectedItem().getNom());
            sp.setDescription(description.getText());
            ss.ajouterProposition(sp);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
     public void comboboxCategorie() {
        ServiceService ss = new ServiceService();
        ArrayList<CategorieService> listCategorieServices = new ArrayList<CategorieService>();
        listCategorieServices = ss.getListCategorie();
        System.out.println(listCategorieServices);
        //listService = rs.getListService(userReclame.getSelectedItem().getId(), this.user.getId());
        int i = 0;
/*        comboCat.getModel().removeItem(0);
        for (CategorieService cs : listCategorieServices) {
            comboCat.getModel().removeItem(i);
            i++;
        }*/
        proposerService.refreshTheme();
        for (CategorieService cs : listCategorieServices) {
            comboProp.addItem(cs);
        }
        
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Form getProposerService() {
        return proposerService;
    }

    public void setProposerService(Form proposerService) {
        this.proposerService = proposerService;
    }
    
}
