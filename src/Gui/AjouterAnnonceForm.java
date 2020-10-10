/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Annonce;
import Entities.CategorieService;
import Entities.Service;
import Entities.User;
import Service.AnnonceService;
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
 * @author Achref Bannouri
 */
public class AjouterAnnonceForm {
    ComboBox<Service> services;
    ComboBox<CategorieService> categories;
    TextField description;
    ComboBox type;
    TextField prix;
    TextField adresse;
    TextField date;
    Button valider;
    Form ajouter;
    User user;
    public void comboboxCategorie() {
        ServiceService ss = new ServiceService();
        ArrayList<CategorieService> listCategorieServices = new ArrayList<CategorieService>();
        listCategorieServices = ss.getListCategorie();
        System.out.println(listCategorieServices);        
        ajouter.refreshTheme();
        for (CategorieService cs : listCategorieServices) {
            categories.addItem(cs);
        }
        
    }
    
    public void comboboxService() {
        ServiceService ss = new ServiceService();
        ArrayList<Service> listServices = new ArrayList<Service>();
        listServices = ss.getListService(categories.getSelectedItem().getId());
        System.out.println(listServices);
        ajouter.refreshTheme();
        for (Service s : listServices) {
            services.addItem(s);
        }
    }
        
    /**
     *
     */
           public AjouterAnnonceForm(User u) {
               this.user=u;
            ajouter = new Form("Ajouter une annonce");
            ajouter.getToolbar().addCommandToSideMenu("Accueil", null, ev -> {
            AccueilForm sf = new AccueilForm(u);
            sf.getAccueil().show();
        });
            ajouter.getToolbar().addCommandToSideMenu("Mes Annonces", null, evt -> {
            MesAnnoncesForm f = new MesAnnoncesForm(u);
            f.getAnnonce().show();
        });
        ajouter.getToolbar().addCommandToSideMenu("Les Offres", null, evt -> {   
            ListOffreForm f = new ListOffreForm(u);
            f.getF().show();
        });
        ajouter.getToolbar().addCommandToSideMenu("Les Demandes", null, evt -> {
            ListDemandeForm f = new ListDemandeForm(u);
            f.getF().show();
        });
        ajouter.getToolbar().addCommandToSideMenu("Ajouter Annonce", null, evt -> {
            AjouterAnnonceForm f = new AjouterAnnonceForm(u);
            f.getAjouter().show();
        });
            categories = new ComboBox<CategorieService>();
            services = new ComboBox<Service>();            
            comboboxCategorie();
            comboboxService();
            /*comboCat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    comboboxService(comboCat.getSelectedItem().getId());
                }
                
            });*/
            description=new TextField("","Descriprion");
            prix=new TextField("","Prix",5,TextArea.NUMERIC);
            type=new ComboBox();
            type.addItem("offre");
            type.addItem("demande");
            adresse=new TextField("","Adresse");
            date=new TextField("","yyyy-MM-dd");
            Button valider =new Button("Valider");
            valider.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ajouterAnnonce(u);
                    //ajouter.showBack();
                    MesAnnoncesForm f = new MesAnnoncesForm(u);
                    f.getAnnonce().show();
                    
                }
            });
            ajouter.add(description);
            ajouter.add(categories);
            ajouter.add(services);
            ajouter.add(adresse);
            ajouter.add(type);
            ajouter.add(date);
            ajouter.add(prix);
            ajouter.add(valider);
        
    }
        
    public void ajouterAnnonce(User u) {

        try {
            Annonce a = new Annonce();
            AnnonceService sa = new AnnonceService();            
            a.setService(services.getSelectedItem());
            a.setCategorieService(categories.getSelectedItem());
            a.setDescription(description.getText());
            a.setType(type.getSelectedItem().toString());
            a.setAdresse(adresse.getText());
            a.setDate(date.getText());
            a.setMontant(Long.parseLong(prix.getText()));
            a.setUser(u);
            /*a.setNbr_c(0);
            a.setNbr_d(0);
            a.setNbr_o(0);*/
            sa.ajouterAnnonce(a);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public Form getAjouter() {
        return ajouter;
    }

    public void setAjouter(Form ajouter) {
        this.ajouter = ajouter;
    }
    
    
    
}
