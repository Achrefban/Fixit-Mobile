/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieProduit;
import Entities.Produit;
import Entities.Service;
import Entities.User;
import Service.ProduitService;
import Service.ReclamationService;
import Service.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ali Saidani
 */
public class AjouterProduitForm {

    private Produit prod;

    Form f;
    ComboBox<CategorieProduit> Categorie;
    TextField nom;
    TextField prix;
    TextField quantite;
    TextField image;
    Button modifier;
    User user;

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

    public AjouterProduitForm(Produit produit, User user) {

        prod = produit;
        f = new Form("Espace Produit", BoxLayout.y());
        this.user = user;
        MenuToolbar menu =new MenuToolbar(user);
        f.setToolBar(menu.getMenu().getToolbar());
        Categorie = new ComboBox<CategorieProduit>();
        nom = new TextField(produit.getNom());
        prix = new TextField(Integer.toString(produit.getPrix()));
        quantite = new TextField(Integer.toString(produit.getQuantite()));
        image = new TextField("", "image");
        modifier = new Button("modifier");
        try {
            String urlImage = "http://localhost/fixit/web/uploads/images/produit/" + produit.getImage();
            EncodedImage encImage = EncodedImage.create("/load.jpg");
            Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
            ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
            f.add(imgViewer);
            f.add(Categorie);
            f.add(nom);
            f.add(prix);
            f.add(quantite);
            f.add(modifier);
            comboboxService();
            System.out.println(produit.getId());
        } catch (IOException ex) {
            System.out.println(ex);
        }

        modifier.addActionListener((evt) -> {
            boolean erreur = false;
            String error = "";
            ProduitService us = new ProduitService();
            prod.setIdCategorieProduit(Categorie.getSelectedItem());
            prod.setNom(nom.getText());
            prod.setQuantite(Integer.parseInt(quantite.getText()));
            prod.setPrix(Integer.parseInt(prix.getText()));
            prod.setId(produit.getId());
            us.ModifierProduit(prod);

        });
    }

    public void comboboxService() {
        ProduitService rs = new ProduitService();
        ArrayList<CategorieProduit> listCategorie = new ArrayList<CategorieProduit>();
        listCategorie = rs.getListService();
        int i = 0;
        Categorie.getModel().removeItem(0);
        for (CategorieProduit s : listCategorie) {
            Categorie.getModel().removeItem(i);
            i++;
        }
        f.refreshTheme();
        for (CategorieProduit s : listCategorie) {
            Categorie.addItem(s);
        }
    }
 
}

