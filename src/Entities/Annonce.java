package Entities;

import java.util.Date;

public class Annonce {
	private int id;
	private String description;
	private String type;
	private long montant;
	private int tel;
	private String adresse;
	private String date;
	private int nbr_c;
	private int nbr_o;
	private int nbr_d;
	private User user;	
	private CategorieService CategorieService ;
        private Service service;

    public Annonce() {
    }

    public Annonce(String description, String type, long montant, int tel, String adresse, String date, int nbr_c, int nbr_o, int nbr_d, User user, CategorieService CategorieService, Service service) {
        this.description = description;
        this.type = type;
        this.montant = montant;
        this.tel = tel;
        this.adresse = adresse;
        this.date = date;
        this.nbr_c = nbr_c;
        this.nbr_o = nbr_o;
        this.nbr_d = nbr_d;
        this.user = user;
        this.CategorieService = CategorieService;
        this.service = service;
    }

    public Annonce(int id, String description, String type, long montant, int tel, String adresse, String date, int nbr_c, int nbr_o, int nbr_d, User user, CategorieService CategorieService, Service service) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.montant = montant;
        this.tel = tel;
        this.adresse = adresse;
        this.date = date;
        this.nbr_c = nbr_c;
        this.nbr_o = nbr_o;
        this.nbr_d = nbr_d;
        this.user = user;
        this.CategorieService = CategorieService;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    public int getNbr_c() {
        return nbr_c;
    }

    public void setNbr_c(int nbr_c) {
        this.nbr_c = nbr_c;
    }

    public int getNbr_o() {
        return nbr_o;
    }

    public void setNbr_o(int nbr_o) {
        this.nbr_o = nbr_o;
    }

    public int getNbr_d() {
        return nbr_d;
    }

    public void setNbr_d(int nbr_d) {
        this.nbr_d = nbr_d;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CategorieService getCategorieService() {
        return CategorieService;
    }

    public void setCategorieService(CategorieService CategorieService) {
        this.CategorieService = CategorieService;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", description=" + description + ", type=" + type + ", montant=" + montant + ", tel=" + tel + ", adresse=" + adresse + ", date=" + date + ", nbr_c=" + nbr_c + ", nbr_o=" + nbr_o + ", nbr_d=" + nbr_d + ", user=" + user + ", CategorieService=" + CategorieService + ", service=" + service + '}';
    }

    public Annonce(String description, String type, long montant, int tel, String adresse, String date, CategorieService CategorieService, Service service) {
        this.description = description;
        this.type = type;
        this.montant = montant;
        this.tel = tel;
        this.adresse = adresse;
        this.date = date;
        this.CategorieService = CategorieService;
        this.service = service;
    }
    
    
        
        
        
}
