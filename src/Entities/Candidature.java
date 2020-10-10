/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Achref Bannouri
 */
public class Candidature {
    private int id;
    private int id_annonce;
    private String message;
    private String etat;
    private String email;
    private String tel;
    private Date date;
    private int idUser;

    public Candidature() {
    }

    public Candidature(int id_annonce, String message, String etat, String email, String tel, Date date, int idUser) {
        this.id_annonce = id_annonce;
        this.message = message;
        this.etat = etat;
        this.email = email;
        this.tel = tel;
        this.date = date;
        this.idUser = idUser;
    }

    public Candidature(int id, int id_annonce, String message, String etat, String email, String tel, Date date, int idUser) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.message = message;
        this.etat = etat;
        this.email = email;
        this.tel = tel;
        this.date = date;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Candidature{" + "id=" + id + ", id_annonce=" + id_annonce + ", message=" + message + ", etat=" + etat + ", email=" + email + ", tel=" + tel + ", date=" + date + ", idUser=" + idUser + '}';
    }
    
    
}
