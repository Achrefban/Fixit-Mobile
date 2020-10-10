/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Avis;
import Entities.User;
import Service.AvisService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author lenovo
 */
public class EspaceAvisForm {

    Form f;
    User user;
    ComboBox<String> satisfaction;
    TextField desc = new TextField("Description");
    Label note;
    Label satis;
    Label des;
    Slider s;
    Button modifier;
    Button ajouter;

    public EspaceAvisForm(User u) {
        this.user = u;
        instanceAndAddToForm();
    }

    public void instanceAndAddToForm() {
        User u = this.user;
        
        AvisService as = new AvisService();
        Avis a = as.getListAvis(this.user.getId());
        f = new Form("Espace Avis", BoxLayout.y());
        MenuToolbar menu = new MenuToolbar(user);
        f.setToolBar(menu.getMenu().getToolbar());
        note = new Label("note : ");
        satis = new Label("Satisfaction : ");
        des = new Label("Description : ");
        satisfaction = new ComboBox<String>();
        satisfaction.addItem("Non");
        satisfaction.addItem("Moyennement");
        satisfaction.addItem("Totalement");
        desc = new TextField("", "Decription");
        modifier = new Button("modifier");
        ajouter = new Button("modifier");
        s = createStarRankSlider();
        if (a != null) {
            s.setProgress(a.getNote());
            if (a.getSatisfaction().equals("Totalement")) {
                satisfaction.setSelectedIndex(2);
            }
            if (a.getSatisfaction().equals("Moyennement")) {
                satisfaction.setSelectedIndex(1);
            }
            if (a.getSatisfaction().equals("Non")) {
                satisfaction.setSelectedIndex(0);
            }
            desc.setText(a.getDescription());
            Container c = new Container(BoxLayout.x());
            c.add(note);
            c.add(s);
            Container c1 = new Container(BoxLayout.x());
            c1.add(satis);
            c1.add(satisfaction);
            Container c2 = new Container(BoxLayout.x());
            c2.add(des);
            c2.add(desc);
            f.add(c);
            f.add(c1);
            f.add(c2);
            f.add(modifier);
            modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Avis avs= new Avis(desc.getText(),s.getProgress(),satisfaction.getSelectedItem(),u);
                    as.modifierAvis(avs);
                    Dialog.show("Avis", "Avis modifiée avec succées", "Ok", null);
                    instanceAndAddToForm();
                }
            });
        } else {
            Container c = new Container(BoxLayout.x());
            c.add(note);
            c.add(s);
            Container c1 = new Container(BoxLayout.x());
            c1.add(satis);
            c1.add(satisfaction);
            Container c2 = new Container(BoxLayout.x());
            c2.add(des);
            c2.add(desc);
            f.add(c);
            f.add(c1);
            f.add(c2);
            f.add(ajouter);
            ajouter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Avis avs= new Avis(desc.getText(),s.getProgress(),satisfaction.getSelectedItem(),u);
                     as.ajouterAvis(avs);
                     Dialog.show("Avis", "Merci pour votre avis", "Ok", null);
                     instanceAndAddToForm();
                }
            });
        }

    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
