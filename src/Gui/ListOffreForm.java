/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Annonce;
import Entities.User;
import Service.AnnonceService;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author Achref Bannouri
 */
public class ListOffreForm {
    Form f;
    SpanLabel lb;
    User user;

    public Form getF() {
        return f;
    }
   
    public void setF(Form f) {
        this.f = f;
    }

    /*public ListOffreForm() {
         f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        AnnonceService sa=new AnnonceService();
        lb.setText(sa.getOffres().toString());
        
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{AccueilForm h=new AccueilForm(this.user);
          h.getAccueil().show();
          });
    }*/
    
    public ListOffreForm(User u) {
        this.user = u;
        f = new Form();
        f.getToolbar().addCommandToSideMenu("Accueil", null, ev -> {
            AccueilForm sf = new AccueilForm(u);
            sf.getAccueil().show();
        });
        f.getToolbar().addCommandToSideMenu("Mes Annonces", null, evt -> {
            MesAnnoncesForm f = new MesAnnoncesForm(u);
            f.getAnnonce().show();
        });
        f.getToolbar().addCommandToSideMenu("Les Offres", null, evt -> {            
        });
        f.getToolbar().addCommandToSideMenu("Les Demandes", null, evt -> {
            ListDemandeForm f = new ListDemandeForm(u);
            f.getF().show();
        });
        f.getToolbar().addCommandToSideMenu("Ajouter Annonce", null, evt -> {
            AjouterAnnonceForm f = new AjouterAnnonceForm(u);
            f.getAjouter().show();
        });
        AnnonceService os = new AnnonceService();
        ArrayList<Annonce> data = os.getOffres();
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
                    Annonce an = data.get(iter);
                    if (an == null) {
                        return null;
                    }

                    Button btn1 = new Button("Détails");
                    btn1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Form hi1 = new Form("Details ", new BoxLayout(CENTER).y());

                            Label lb = new Label("Description :" + an.getDescription());
                            Label lb1 = new Label("type :" + an.getType());
                            Label lb2 = new Label("prix :" + an.getMontant() + " DT");
                            Label lb3 = new Label("tel :" + an.getTel());
                            Label lb4 = new Label("Adresse :" + an.getAdresse());
                            //Label lb5 = new Label("date :" + an.getDate());
                            Label lb6 = new Label("catégorie :" + an.getCategorieService().getNom());
                            Label lb7 = new Label("service :" + an.getService().getNom());
                            Button btn2 = new Button("Postuler");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String stringDate = dateFormat.format(an.getDate());

                            hi1.add(lb1);
                            hi1.add(lb2);
                            hi1.add(lb3);
                            hi1.add(lb4);
                            hi1.add(stringDate);
                            hi1.add(lb6);
                            hi1.add(lb7);
                            hi1.add(btn2);
                            hi1.show();
                            hi1.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                                f.showBack();
                            });
                        }

                    });

                    String desc = (String) an.getDescription();
                    String typ = (String) an.getType();
                    String t = Integer.toString(an.getTel());
                    String adr = (String) an.getAdresse();
                    String p = Float.toString(an.getMontant());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String stringDate = dateFormat.format(an.getDate());
                    String ca = (String) an.getCategorieService().getNom();
                    String s = (String) an.getService().getNom();
                    Label description = new Label(desc);
                    Label type = new Label(typ);
                    Label tel = new Label(t);
                    Label adresse = new Label(adr);
                    Label prix = new Label(p);
                    Label date = new Label(stringDate);                    
                    Label categorie = new Label(ca);
                    Label service = new Label(s);

                    Container v = new Container(BoxLayout.y());
                    v.add(description);
                    v.add(type);
                    v.add(tel);
                    v.add(adresse);
                    v.add(prix);
                    v.add(date);
                    v.add(categorie);
                    v.add(service);
                    v.add(btn1);
                    Container h = new Container(BoxLayout.x());
                    h.add(v);
                    cmps[iter] = (h);
                    System.out.println("affichage avec succés");
                }
                return cmps;

            }
        };
        ic.setScrollableX(false);
        f.add(ic);
    }
    
    
    
}
