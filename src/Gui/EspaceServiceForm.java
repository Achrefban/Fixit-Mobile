/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Service;
import Entities.ServiceUser;
import Entities.User;
import Service.ServiceService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author SELON
 */
public class EspaceServiceForm {
    User user;
    Form service;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Form getService() {
        return service;
    }

    public void setService(Form service) {
        this.service = service;
    }

    public EspaceServiceForm(User user) {
        
        this.user = user;
        service=new Form("Espace Service",BoxLayout.y());
         MenuToolbar menu = new MenuToolbar(user);
        service.setToolBar(menu.getMenu().getToolbar());
        ServiceService ss = new ServiceService();
        ArrayList<Service> data = ss.getService();
        ArrayList<ServiceUser> dataSu = ss.getServiceUser(this.user.getId());
        service.getToolbar().addCommandToOverflowMenu("Mes services", null, ev1->{
            Form mesServices =new Form("Mes Services");
            mesServices.setToolBar(menu.getMenu().getToolbar());
            Container ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    amount = dataSu.size();
                }
                if (amount + index > dataSu.size()) {
                    amount = dataSu.size() - index;
                }
                if (index < 0) {
                    return null;
                }
                Component[] cmps = new Component[amount];
                for (int iter = 0; iter < cmps.length; iter++) {
                    ServiceUser s = dataSu.get(iter);
                    if (s == null) {
                        return null;
                    }

                    try {
                        String n = (String) s.getDescription();
                        String p = Integer.toString((int)s.getPrix());
                        Label nom = new Label(n);
                        Label prix = new Label(p);
                        String nomImage=ss.nomImage(s.getIdService());
                        String urlImage = "http://localhost/fixit/web/uploads/images/service/"+nomImage;
                        EncodedImage encImage = EncodedImage.create("/load.jpg");
                        Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                        ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
                        Button supprimer = new Button("supprimer");
                        supprimer.addActionListener((evt) -> {
                            //ss.supprimerService(s);
                            ServiceUser su = new ServiceUser();
                            System.out.println(user.getId());
                            System.out.println(s.getIdService());
                        su.setIdUser(user.getId());
                        su.setIdService(s.getIdService());
                        ss.supprimerService(su);
                        });
                        Container v = new Container(BoxLayout.y());
                        v.add(nom);
                        v.add(prix);
                        v.add(supprimer);
                        Container h = new Container(BoxLayout.x());
                        h.add(imgViewer);
                        h.add(v);
                       // h.add(supprimer);
                        cmps[iter] = (h);
                        
            System.out.println(nom);
                    } catch (IOException ex) {
                        
                        System.out.println(ex);
                    }
                }
                return cmps;

            }
            
           
        };
            
        ic.setScrollableY(false);
            mesServices.add(ic);
            mesServices.show();
              });
        service.getToolbar().addCommandToOverflowMenu("Ajouter un service", null, ev2->{
                 AjouterServiceForm asf = new AjouterServiceForm(user);
                            asf.getAjouterService().show();
              });
         service.getToolbar().addCommandToOverflowMenu("Proposer un service", null, ev3->{
                 ProposerServiceForm psf = new ProposerServiceForm(user);
                            psf.getProposerService().show();
              });
             // service.show();
        Container ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    amount = data.size();
                }
                if (amount + index > data.size()) {
                    amount = data.size() - index;
                }
                if (index < 0) {
                    return null;
                }
                Component[] cmps = new Component[amount];
                for (int iter = 0; iter < cmps.length; iter++) {
                    Service s = data.get(iter);
                    if (s == null) {
                        return null;
                    }

                    try {
                        String n = (String) s.getNom();
                        String p = Integer.toString(s.getNbrProviders());
                        Label nom = new Label(n);
                        Label prix = new Label(p);
                        String urlImage = "http://localhost/fixit/web/uploads/images/service/" + s.getImage();
                        EncodedImage encImage = EncodedImage.create("/load.jpg");
                        Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                        ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
                        Button detail = new Button("Details");
                        detail.addActionListener((evt) -> {
                            DetailServiceForm dsf = new DetailServiceForm(user,s);
                            dsf.getDetail().show();
                        });
                        Container v = new Container(BoxLayout.y());
                        v.add(nom);
                        v.add(prix);
                        v.add(detail);
                        Container h = new Container(BoxLayout.x());
                        h.add(imgViewer);
                        h.add(v);
                        cmps[iter] = (h);
                    } catch (IOException ex) {
                        
                        System.out.println(ex);
                    }
                }
                return cmps;

            }
        };
        ic.setScrollableX(true);
        ic.setScrollableY(false);
        
        service.add(ic);
    }
   
    
}
