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
public class Outil {
    private int id;
    private String nom;
    private int quantite;
    private int dureeMaximale;
    private int prix;
    private String adresse;
    private int codePostal;
    private String ville;
    private String image;
    private CategorieOutil c;
    private String nomCategorie;
    private ImageView im;

    public Outil() {
    }
    

    public Outil(int id, String nom, int quantite, int dureeMaximale, int prix, String adresse, int codePostal, String ville, String image, CategorieOutil c, String nomCategorie, ImageView im) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.dureeMaximale = dureeMaximale;
        this.prix = prix;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.image = image;
        this.c = c;
        this.nomCategorie = nomCategorie;
        this.im = im;
    }

    public Outil(String nom, int quantite, int dureeMaximale, int prix, String adresse, int codePostal, String ville, String image, CategorieOutil c, String nomCategorie, ImageView im) {
        this.nom = nom;
        this.quantite = quantite;
        this.dureeMaximale = dureeMaximale;
        this.prix = prix;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.image = image;
        this.c = c;
        this.nomCategorie = nomCategorie;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getDureeMaximale() {
        return dureeMaximale;
    }

    public void setDureeMaximale(int dureeMaximale) {
        this.dureeMaximale = dureeMaximale;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategorieOutil getC() {
        return c;
    }

    public void setC(CategorieOutil c) {
        this.c = c;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public ImageView getIm() {
        return im;
    }

    public void setIm(ImageView im) {
        this.im = im;
    }
    
    @Override
        public String toString(){
            return nom;
        }

   
    
    
    
}
