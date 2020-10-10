/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.codename1.ui.Button;
import java.util.Date;
import javafx.scene.image.ImageView;


/**
 *
 * @author Ali Saidani
 */
public class Produit {
      private int id;
  private String Nom;
  private int Quantite;
  private  CategorieProduit idCategorieProduit;
  private int prix;
  public Date date_exp;
  private User user;
  private String image;
  private Button Detaille;
  private int idProd;
  private ImageView im;

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }
  public Produit(){}

    public Produit(String Nom, int Quantite, CategorieProduit idCategorieProduit, int prix, Date date_exp, User user, String image, ImageView im) {
        this.Nom = Nom;
        this.Quantite = Quantite;
        this.idCategorieProduit = idCategorieProduit;
        this.prix = prix;
        this.date_exp = date_exp;
        this.user = user;
        this.image = image;
        this.im=im;
    }

    public ImageView getIm() {
        return im;
    }

    public void setIm(ImageView im) {
        this.im = im;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public CategorieProduit getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public void setIdCategorieProduit(CategorieProduit idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(Date date_exp) {
        this.date_exp = date_exp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public Button getDetaille() {
        return Detaille;
    }

    public void setDetaille(Button Detaille) {
        this.Detaille = Detaille;
    }

    @Override
    public String toString() {
        return Nom + Detaille;
    }
    
}
