package Entities;

import javafx.scene.image.ImageView;

public class Service {
    
private int id;
private String nom;
private int visible;    
private String description;
private String image;
private int nbrProviders;
private CategorieService categorie;
private String etat;
private ImageView im;

    public Service() {
    }

    public Service(int id, String nom, int visible, String description, String image, int nbrProviders, CategorieService categorie, String etat, ImageView im) {
        this.id = id;
        this.nom = nom;
        this.visible = visible;
        this.description = description;
        this.image = image;
        this.nbrProviders = nbrProviders;
        this.categorie = categorie;
        this.etat = etat;
        this.im = im;
    }

    public Service(String nom, int visible, String description, String image, int nbrProviders, CategorieService categorie, String etat, ImageView im) {
        this.nom = nom;
        this.visible = visible;
        this.description = description;
        this.image = image;
        this.nbrProviders = nbrProviders;
        this.categorie = categorie;
        this.etat = etat;
        this.im = im;
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

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
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

    public int getNbrProviders() {
        return nbrProviders;
    }

    public void setNbrProviders(int nbrProviders) {
        this.nbrProviders = nbrProviders;
    }

    public CategorieService getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieService categorie) {
        this.categorie = categorie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public ImageView getIm() {
        return im;
    }

    public void setIm(ImageView im) {
        this.im = im;
    }

    
    @Override
    public String toString() {
        return nom;
    }
    
}
