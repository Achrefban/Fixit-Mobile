/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Produit;
import Entities.Reclamation;
import Entities.User;
import Service.ReclamationService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class ListReclamationForm {

    User user;
    Form f;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public ListReclamationForm(User u) {
        this.user = u;
        f = new Form("Mes reclamations");
         MenuToolbar menu = new MenuToolbar(user);
        f.setToolBar(menu.getMenu().getToolbar());
        Container ic = afficherReclamation();
        f.add(ic);
    }
    
    
    
    public Container afficherReclamation()
    {
        ReclamationService rs = new ReclamationService();
        ArrayList<Reclamation> listRec = rs.getListReclamations(this.user.getId());
        Container ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    amount = listRec.size();
                }
                if (amount + index > listRec.size()) {
                    amount = listRec.size() - index;
                }
                if (index < 0) {
                    return null;
                }
                Component[] cmps = new Component[amount];
                for (int iter = 0; iter < cmps.length; iter++) {
                    Reclamation r = listRec.get(iter);
                    if (r == null) {
                        return null;
                    }

                    String userRec = r.getUserReclame().toString();
                    String service = r.getIdServiceRealise().toString();
                    String dateReclamation = r.getDateReclamation();
                    String objet = r.getObjet();
                    Label userRecLb = new Label(userRec);
                    Label serviceLb = new Label(service);
                    Label dateReclamationLb = new Label(dateReclamation);
                    Label objetLb = new Label(objet);
                    Button btn1 = afficherDetailReclamation(r);
                    try {
                        if (r.getTraite() == 1) {
                            String urlImage = "http://localhost/fixit/web/service/images/icons/traite.jpg";
                            EncodedImage encImage = EncodedImage.create("/load.jpg");
                            Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                            ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
                            Container v = new Container(BoxLayout.y());
                            v.add(userRecLb);
                            v.add(serviceLb);

                            v.add(dateReclamationLb);
                            v.add(objetLb);
                            v.add(btn1);
                            Container h = new Container(BoxLayout.x());
                            h.add(imgViewer);
                            h.add(v);
                            cmps[iter] = (h);
                        } else {
                            String urlImage = "http://localhost/fixit/web/service/images/icons/encours.jpg";
                            EncodedImage encImage = EncodedImage.create("/load.jpg");
                            Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                            ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
                            Container v = new Container(BoxLayout.y());
                            v.add(userRecLb);
                            v.add(serviceLb);

                            v.add(dateReclamationLb);
                            v.add(objetLb);
                            v.add(btn1);
                            Container h = new Container(BoxLayout.x());
                            h.add(imgViewer);
                            h.add(v);
                            cmps[iter] = (h);
                        }
                    } catch (IOException ex) {

                        System.out.println(ex);
                    }
                }
                return cmps;
            }
        };
        ic.setScrollableX(false);
        ic.setScrollableY(false);
        return ic;
    }
    public Button afficherDetailReclamation(Reclamation r) 
    {
        Button btnDetail = new Button("Details");
        btnDetail.addActionListener(new ActionListener()
        {
             @Override
                        public void actionPerformed(ActionEvent evt) {
                            Form hi1 = new Form("detail Reclamation ", new BoxLayout(CENTER).y());

                            Label contre = new Label("Contre :" +r.getUserReclame().toString());
                            Label le = new Label("Le  :" + r.getDateReclamation() );
                            Label service = new Label("Service  :" + r.getIdServiceRealise().toString());
                            Label realise = new Label("Réalise le  :" + r.getDateRealisation());
                            Label objet = new Label("Objet :" + r.getObjet());
                            Label description = new Label ("Description : "+ r.getDescription());
                            Label etat1 = new Label ("Reclamation bien reçu");
                            Label etat2 = new Label ("Reclamation vu par les administrateur \n elle sera bientôt taité");
                            Label etat3 = new Label ("Reclamation traité");
                            Slider s = new Slider();
                            s.setMaxValue(100);
                            s.setMinValue(0);
                            if(r.getSeen()==0)
                            {
                                hi1.add(contre);
                                hi1.add(le);
                                hi1.add(service);
                                hi1.add(realise);
                                hi1.add(objet);
                                hi1.add(description);
                                hi1.show();
                                s.setProgress(20);
                                hi1.add(s);
                                hi1.add(etat1);
                                
                            }
                            if(r.getSeen()==1 && r.getTraite()==0)
                            {
                                hi1.add(contre);
                                hi1.add(le);
                                hi1.add(service);
                                hi1.add(realise);
                                hi1.add(objet);
                                hi1.add(description);
                                hi1.show();
                                s.setProgress(50);
                                hi1.add(s);
                                hi1.add(etat2);
                            }
                            if(r.getSeen() == 1 && r.getTraite()==1)
                            {
                                hi1.add(contre);
                                hi1.add(le);
                                hi1.add(service);
                                hi1.add(realise);
                                hi1.add(objet);
                                hi1.add(description);
                                hi1.show();
                                s. setProgress(100);
                                hi1.add(s);
                                hi1.add(etat3);
                            }
                            
                            hi1.getToolbar().addCommandToRightBar("back", null, (ev) -> {
                                ListReclamationForm h = new ListReclamationForm(user);
                                h.getF().show();
                            });
                        }

        });
        return btnDetail;
    }
}
