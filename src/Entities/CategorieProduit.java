/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author Ali Saidani
 */
public class CategorieProduit {

    private int id;
    private String Nom;
    private String description;
    private String image;
    private ImageView im;

    public CategorieProduit() {
    }

    public CategorieProduit(int id, String Nom, String description, String image, ImageView im) {
        this.id = id;
        this.Nom = Nom;
        this.description = description;
        this.image = image;
        this.im = im;
    }

    public CategorieProduit(String Nom, String description, String image, ImageView im) {
        this.Nom = Nom;
        this.description = description;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return Nom;
    }

    public ImageView getIm() {
        return im;
    }

    public void setIm(ImageView im) {
        this.im = im;
    }

}
