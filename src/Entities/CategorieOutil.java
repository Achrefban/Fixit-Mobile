/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author SL-WASSIM
 */
public class CategorieOutil {
    private int id;
    private String nom;
    private String logo;
    private ImageView im;

    public CategorieOutil(int id, String nom, String logo, ImageView im) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.im = im;
    }

    public CategorieOutil(String nom, String logo, ImageView im) {
        this.nom = nom;
        this.logo = logo;
        this.im = im;
    }
    
    public CategorieOutil() {
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    @Override
    public String toString() {
        return nom;
    }

    public ImageView getIm() {
        return im;
    }

    public void setIm(ImageView im) {
        this.im = im;
    }
    
    
}
