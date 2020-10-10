/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Produit;
import Entities.User;
import Service.ProduitService;
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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ali Saidani
 */
public class MesProduitForm {

    Form prod;
    
    User user;

    public Form getProd() {
        return prod;
    }

    public void setProd(Form prod) {
        this.prod = prod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   

    public MesProduitForm(User user, Produit pro) {
        this.user = user;
        prod = new Form();
         MenuToolbar menu =new MenuToolbar(user);
        prod.setToolBar(menu.getMenu().getToolbar());
       

        ProduitService os = new ProduitService();
        ArrayList<Produit> data = os.getMesProduit(user);
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
                    if (pr == null) {
                        return null;
                    }

                    Button btn1 = new Button("Modifier");
                    btn1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            AjouterProduitForm fo = new AjouterProduitForm(pr,user);
                            fo.getF().show();
                            
                         
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
        prod.add(ic);
    }
}
