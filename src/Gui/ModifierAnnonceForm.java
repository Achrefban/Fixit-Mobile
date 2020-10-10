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
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Achref Bannouri
 */
public class ModifierAnnonceForm {
    ComboBox<Service> services;
    ComboBox<CategorieService> categories;
    TextField description;
    TextField type;
    TextField prix;
    TextField adresse;
    TextField date;
    Button valider;
    Form modifier;
    User user;
    public void comboboxCategorie() {
        ServiceService ss = new ServiceService();
        ArrayList<CategorieService> listCategorieServices = new ArrayList<CategorieService>();
        listCategorieServices = ss.getListCategorie();
        System.out.println(listCategorieServices);        
        modifier.refreshTheme();
        for (CategorieService cs : listCategorieServices) {
            categories.addItem(cs);
        }
        
    }
    
    public void comboboxService() {
        ServiceService ss = new ServiceService();
        ArrayList<Service> listServices = new ArrayList<Service>();
        listServices = ss.getListService(categories.getSelectedItem().getId());
        System.out.println(listServices);
        modifier.refreshTheme();
        for (Service s : listServices) {
            services.addItem(s);
        }
    }
        
    /**
     *
     */
           public ModifierAnnonceForm(Annonce ann,User u) {
            modifier = new Form("Modifer L'annonce"); 
            modifier.getToolbar().addCommandToSideMenu("Accueil", null, ev -> {
            AccueilForm sf = new AccueilForm(u);
            sf.getAccueil().show();
        });
            modifier.getToolbar().addCommandToSideMenu("Mes Annonces", null, evt -> {
            MesAnnoncesForm f = new MesAnnoncesForm(u);
            f.getAnnonce().show();
        });
        modifier.getToolbar().addCommandToSideMenu("Les Offres", null, evt -> {   
            ListOffreForm f = new ListOffreForm(u);
            f.getF().show();
        });
        modifier.getToolbar().addCommandToSideMenu("Les Demandes", null, evt -> {
            ListDemandeForm f = new ListDemandeForm(u);
            f.getF().show();
        });
        modifier.getToolbar().addCommandToSideMenu("Ajouter Annonce", null, evt -> {
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
            description=new TextField(ann.getDescription(),"Descriprion");
            prix=new TextField(Long.toString(ann.getMontant()),"Prix",5,TextArea.NUMERIC);
            type=new TextField(ann.getType(),"Type");
            adresse=new TextField(ann.getAdresse(),"Adresse");
            date=new TextField(ann.getDate(),"yyyy-MM-dd");
            Button valider =new Button("Valider");
            valider.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    modifierAnnonce(ann);
                    MesAnnoncesForm h = new MesAnnoncesForm(u);
                    h.getAnnonce().showBack();
                    
                }
            });
            modifier.add(description);
            modifier.add(categories);
            modifier.add(services);
            modifier.add(adresse);
            modifier.add(type);
            modifier.add(date);
            modifier.add(prix);
            modifier.add(valider);
        
    }
        
    public void modifierAnnonce(Annonce a) {

        try {
            
            AnnonceService sa = new AnnonceService();            
            a.setService(services.getSelectedItem());
            a.setCategorieService(categories.getSelectedItem());
            a.setDescription(description.getText());
            a.setType(type.getText());
            a.setAdresse(adresse.getText());
            a.setDate(date.getText());
            a.setMontant(Long.parseLong(prix.getText()));
            /*a.setNbr_c(0);
            a.setNbr_d(0);
            a.setNbr_o(0);*/
            sa.modifierAnnonce(a);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public Form getModifier() {
        return modifier;
    }

    public void setModifier(Form modifier) {
        this.modifier = modifier;
    }
    
    
   

}
