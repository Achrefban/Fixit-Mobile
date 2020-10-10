/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.Service;
import Entities.ServiceUser;
import Entities.User;
import Service.ServiceService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author SELON
 */
public class AjouterServiceForm {
    User user;
    Form ajouterService;
    TextField description;
    TextField prix;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Form getAjouterService() {
        return ajouterService;
    }

    public void setAjouterService(Form ajouterService) {
        this.ajouterService = ajouterService;
    }
    ComboBox<CategorieService> comboCat;
    ComboBox<Service> comboServ;

    public AjouterServiceForm(User user) {
        try {
            
            ajouterService = new Form("Ajouter un service");
            MenuToolbar menu = new MenuToolbar(user);
            ajouterService.setToolBar(menu.getMenu().getToolbar());
            this.user = user;
            comboCat = new ComboBox<CategorieService>();
            comboServ = new ComboBox<Service>();
            Label nom = new Label("gggggg");
            EncodedImage encImage;
            encImage = EncodedImage.create("/load.jpg");
            ServiceService ss =new ServiceService();
            System.out.println("--------");
            System.out.println(ss.getListCategorie());
            comboboxCategorie();
            comboCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                comboboxService(comboCat.getSelectedItem().getId());
            }

        });
           description=new TextField("","Descriprion");
            prix=new TextField("","Prix",5,TextArea.NUMERIC);
            Button valider =new Button("Valider");
            valider.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(ActionEvent evt) {
                ajouterService();
                
            }
        });
        
            ajouterService.add(comboCat);
            ajouterService.add(comboServ);
            ajouterService.add(description);
            ajouterService.add(prix);
            ajouterService.add(valider);
            

        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
     public void ajouterService() {

        try {
            ServiceUser su = new ServiceUser();
            ServiceService ss = new ServiceService();
            su.setIdUser(this.user.getId());
            su.setIdService(comboServ.getSelectedItem().getId());
            su.setDescription(description.getText());
            su.setPrix(Float.valueOf(prix.getText()));
            ss.ajouterService(su);
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
        ajouterService.refreshTheme();
        for (CategorieService cs : listCategorieServices) {
            comboCat.addItem(cs);
        }
        System.out.println(comboCat);
    }
    public void comboboxService(int idC) {
        ServiceService ss = new ServiceService();
        ArrayList<Service> listServicesCategorie = new ArrayList<Service>();
        listServicesCategorie = ss.getListService(idC);
        System.out.println(listServicesCategorie);
        //listService = rs.getListService(userReclame.getSelectedItem().getId(), this.user.getId());
        int i = 0;
        comboServ.getModel().removeItem(0);
        for (Service cs : listServicesCategorie) {
            comboServ.getModel().removeItem(i);
            i++;
        }
        ajouterService.refreshTheme();
        for (Service s : listServicesCategorie) {
            comboServ.addItem(s);
        }
        System.out.println(comboCat);
    }
    
    
}
