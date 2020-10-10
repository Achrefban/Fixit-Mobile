/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author SELON
 */
public class ServiceUser {
    private String description;
    private float prix;
    private int idUser;
    private int idService;

    public ServiceUser(String description, float prix, int idUser, int idService) {
        this.description = description;
        this.prix = prix;
        this.idUser = idUser;
        this.idService = idService;
    }

    public ServiceUser() {
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the prix
     */
    public float getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(float prix) {
        this.prix = prix;
    }

    /**
     * @return the idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /*@Override
    public String toString() {
        try {
            ServiceUserService s=new ServiceUserService();
            //s.getServiceName(this.idService);
            return ""+s.getServiceName(this.idService)+"                    " + description + "                  " + prix + "-SCoins" ;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }*/

    /**
     * @return the idService
     */
    public int getIdService() {
        return idService;
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(int idService) {
        this.idService = idService;
    }
    
    
}
