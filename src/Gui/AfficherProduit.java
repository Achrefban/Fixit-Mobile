/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.AchatProduit;
import Entities.Outil;
import Entities.Produit;
import Entities.User;
import Service.OutilService;
import Service.ProduitService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author Ali Saidani
 */
public class AfficherProduit {

    User user;
    Produit pp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    Form produit;

    public Form getProduit() {
        return produit;
    }

    public void setProduit(Form produit) {
        this.produit = produit;
    }

    public AfficherProduit(User user) {
        this.user = user;
        produit = new Form();
        MenuToolbar menu =new MenuToolbar(user);
        produit.setToolBar(menu.getMenu().getToolbar());

        ProduitService os = new ProduitService();
        ArrayList<Produit> data = os.getProduit();
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
                    Produit pr = data.get(iter);
                    pp=pr;
                    if (pr == null) {
                        return null;
                    }

                    Button btn1 = new Button("details");
                    btn1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Form hi1 = new Form("details ", new BoxLayout(CENTER).y());
                            Label solde = new Label("Vous avez " + user.getSolde() + " Scoins");
                            TextField quantiteField = new TextField("", "quantite");

                            Label lb = new Label("Nom :" + pr.getNom());
                            Label lb1 = new Label("prix :" + pr.getPrix() + " $");
                            Label lb2 = new Label("quantite :" + pr.getQuantite());
                            Label lb3 = new Label("catégorie :" + pr.getIdCategorieProduit().getNom());
                            Label lb4 = new Label("catégorie :" + pr.getIdCategorieProduit().getNom());
                            Button btn2 = new Button("acheter");
                            btn2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    ProduitService serv = new ProduitService();
                                    int quantite=Integer.parseInt(quantiteField.getText());
                                    serv.AchatProduit(pr.getId(), user.getId(),quantite);
                                }
                            });
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String stringDate = dateFormat.format(pr.getDate_exp());

                            try {
                                String urlImage = "http://localhost/fixit/web/uploads/images/produit/" + pr.getImage();
                                EncodedImage encImage = EncodedImage.create("/load.jpg");
                                Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                                ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));
                                hi1.add(solde);
                                hi1.add(imgViewer);
                                hi1.add(lb3);
                                hi1.add(lb);
                                hi1.add(lb1);
                                hi1.add(lb2);
                                hi1.add(stringDate);
                                hi1.add(quantiteField);
                                hi1.add(btn2);
                                hi1.show();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            hi1.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                                AfficherProduit h = new AfficherProduit(user);
                                h.getProduit().show();
                            });
                        }

                    });

                    String n = (String) pr.getNom();

                    String p = Integer.toString(pr.getPrix());
                    String ca = (String) pr.getIdCategorieProduit().getNom();
                    Label nom = new Label(n);
                    Label prix = new Label(p);
                    Label categorie = new Label(ca);

                    try {
                        String urlImage = "http://localhost/fixit/web/uploads/images/produit/" + pr.getImage();
                        EncodedImage encImage = EncodedImage.create("/load.jpg");
                        Image img = URLImage.createToStorage(encImage, urlImage, urlImage, URLImage.RESIZE_SCALE);
                        ImageViewer imgViewer = new ImageViewer(img.scaled(500, 500));

                        Container v = new Container(BoxLayout.y());
                        v.add(nom);
                        v.add(prix);

                        v.add(categorie);
                        v.add(btn1);
                        Container h = new Container(BoxLayout.x());
                        h.add(imgViewer);
                        h.add(v);
                 
                        
                        cmps[iter] = (h);
                       
                        System.out.println("affichage avec succés");
                    } catch (IOException ex) {

                        System.out.println(ex);
                    }
                }
                return cmps;

            }
        };
        ic.setScrollableX(false);
               produit.getToolbar().addCommandToRightBar("Mes produits", null, (ev) -> {
                                MesProduitForm h = new MesProduitForm(user,pp);
                                h.getProd().show();
                            });
        produit.add(ic);
    }



}
