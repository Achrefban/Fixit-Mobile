/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import Service.ReclamationService;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;

import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author lenovo
 */
public class ajouterReclamationForm {

    Form f;
    ComboBox<User> userReclame;
    ComboBox<Service> serviceRealise;
    ComboBox<String> dateRealisation;
    TextField motif;
    TextField description;
    Button valider;
    User user;

    public ajouterReclamationForm(User user) {
        this.user = user;
        System.out.println(this.user);
        instanceAndAddToForm();
         f.getToolbar().addCommandToOverflowMenu("Mes Reclamations",null,ev1->{
            ListReclamationForm lr = new ListReclamationForm(this.user);
            lr.getF().show();
        });
        userReclame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                comboboxService();
            }
        });
        serviceRealise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                comboBoxDate();
            }

        });
        valider.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(ActionEvent evt) {
                ajouterReclamation();
            }
        });
    }

    public void comboBoxUser() {
        ReclamationService rs = new ReclamationService();
        ArrayList<User> listUser = new ArrayList<User>();
        listUser = rs.getListUser(this.user.getId());
        for (User u : listUser) {
            userReclame.addItem(u);
        }
    }

    public void comboboxService() {
        ReclamationService rs = new ReclamationService();
        ArrayList<Service> listService = new ArrayList<Service>();
        listService = rs.getListService(userReclame.getSelectedItem().getId(), this.user.getId());
        int i = 0;
        serviceRealise.getModel().removeItem(0);
        for (Service s : listService) {
            serviceRealise.getModel().removeItem(i);
            i++;
        }
        f.refreshTheme();
        for (Service s : listService) {
            serviceRealise.addItem(s);
        }
    }

    public void comboBoxDate() {
        ReclamationService rs = new ReclamationService();

        ArrayList<String> listDate = new ArrayList<String>();
        listDate = rs.getListDate(userReclame.getSelectedItem().getId(), serviceRealise.getSelectedItem().getId(), this.user.getId());
        int i = 0;
        dateRealisation.getModel().removeItem(0);
        for (String s : listDate) {
            dateRealisation.getModel().removeItem(i);
            i++;
        }
        for (String s : listDate) {
            dateRealisation.addItem(s);
        }

    }

    public void instanceAndAddToForm() {

        f = new Form("Espace Reclamation", BoxLayout.y());
       
        userReclame = new ComboBox<User>();
        serviceRealise = new ComboBox<Service>();
        dateRealisation = new ComboBox<String>();
        motif = new TextField("", "Motif de reclamation");
        description = new TextField("", "Description");
        valider = new Button("valider");
         MenuToolbar menu = new MenuToolbar(user);
        f.setToolBar(menu.getMenu().getToolbar());
        comboBoxUser();
        f.add(userReclame);
        f.add(serviceRealise);
        f.add(dateRealisation);
        f.add(motif);
        f.add(description);
        f.add(valider);
    }

    public void ajouterReclamation() {

        try {
            Reclamation rec = new Reclamation();
            ReclamationService rs = new ReclamationService();
            rec.setUserReclamant(this.user);
            rec.setUserReclame(userReclame.getSelectedItem());
            rec.setIdServiceRealise(serviceRealise.getSelectedItem());
            String date = dateRealisation.getSelectedItem();
            rec.setDateRealisation(date);
            rec.setDescription(description.getText());
            rec.setObjet(motif.getText());
            rs.ajouterReclamation(rec);
            Dialog.show("Succées", "Reclamation ajoutée avec succées", "Ok", null);
            motif.setText("");
            description.setText("");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
