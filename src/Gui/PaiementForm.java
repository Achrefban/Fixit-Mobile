/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Service.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yassine
 */
public class PaiementForm {

    Form f;
    TextField cc, month, year, nbScoins, montant;
    User user;

    public PaiementForm(User user) {
        this.user = user;
        f = new Form("Paiement", BoxLayout.y());
        MenuToolbar menu = new MenuToolbar(user);
        f.setToolBar(menu.getMenu().getToolbar());
        Container mmyy = new Container(BoxLayout.x());
        Container nbqt = new Container(BoxLayout.x());
        Button payer = new Button("Payer");
        cc = new TextField("", "Numéro de la carte");
        month = new TextField("", "MM", 12, 0);
        year = new TextField("", "YY", 12, 0);
        nbScoins = new TextField("", "Nombre de SCoins", 12, 0);
        montant = new TextField("", "Montant", 12, 0);
        cc.addDataChangedListener((i, ii) -> {
            if (!isValidInput(cc.getText()) && cc.getText().length() > 0) {
                cc.stopEditing();
                if (cc.getText().length() < 1) {
                    cc.setText("");
                } else {
                    cc.setText(cc.getText().substring(0, cc.getText().length() - 1));
                }
                cc.startEditing();
            }
            if (cc.getText().length() > 16) {
                cc.stopEditing();
                cc.setText(cc.getText().substring(0, 16));
                cc.startEditing();
            }
        });
        month.addDataChangedListener((i, ii) -> {
            if (!isValidInput(month.getText()) && month.getText().length() > 0) {
                month.stopEditing();
                if (month.getText().length() < 1) {
                    month.setText("");
                } else {
                    month.setText(month.getText().substring(0, month.getText().length() - 1));
                }
                month.startEditing();
            }
            if (month.getText().length() > 2) {
                month.stopEditing();
                month.setText(month.getText().substring(0, 2));
                month.startEditing();
            }
        });
        year.addDataChangedListener((i, ii) -> {
            if (!isValidInput(year.getText()) && year.getText().length() > 0) {
                year.stopEditing();
                if (year.getText().length() < 1) {
                    year.setText("");
                } else {
                    year.setText(year.getText().substring(0, year.getText().length() - 1));
                }
                year.startEditing();
            }
            if (year.getText().length() > 2) {
                year.stopEditing();
                year.setText(year.getText().substring(0, 2));
                year.startEditing();
            }
        });
        montant.setEnabled(false);
        nbScoins.addDataChangedListener((i, ii) -> {
            float mn=0;
            if (!isValidInput(nbScoins.getText()) && nbScoins.getText().length() > 0) {
                nbScoins.stopEditing();
                if (nbScoins.getText().length() < 1) {
                    nbScoins.setText("");
                } else {
                    nbScoins.setText(nbScoins.getText().substring(0, nbScoins.getText().length() - 1));
                }
                nbScoins.startEditing();
            }
            if (nbScoins.getText().length() > 3) {
                nbScoins.stopEditing();
                nbScoins.setText(nbScoins.getText().substring(0, 2));
                nbScoins.startEditing();
            }
            if (nbScoins.getText().length() > 0){
                mn=Float.valueOf(nbScoins.getText())/2;
                montant.setText(String.valueOf(mn)+" DT");
            }
            if (nbScoins.getText().length() == 0){
                montant.setText("");
            }
        });
        nbqt.add(nbScoins).add(montant);
        mmyy.add(month).add(year);
        f.add(nbqt).add(cc).add(mmyy).add(payer);
        payer.addActionListener((evt) -> {
            float mn;
            int nbS;
            if (nbScoins.getText().length()>0) nbS = Integer.parseInt(nbScoins.getText());
            else nbS=0;
            mn = nbS / 2;
            Stripe.apiKey = "sk_test_rkfr2kuDbj8a7LRmarLt40W7";
            Map<String, Object> chargeMap = new HashMap<String, Object>();
            chargeMap.put("amount", nbS * 50);
            chargeMap.put("currency", "usd");
            Map<String, Object> cardMap = new HashMap<String, Object>();
            cardMap.put("number", cc.getText());
            cardMap.put("exp_month", month.getText());
            cardMap.put("exp_year", year.getText());
            chargeMap.put("card", cardMap);
            try {
                Charge charge = Charge.create(chargeMap);
                //Button payer=(Button) event.getSource();
                //payer.setDisable(true);
                if (charge.getStatus().equals("succeeded")) {
                    Dialog.show("Paiement", "Paiement effectué avec succès", "Retour", null);
                    UserService us=new UserService();
                    us.modifierSoldeUser(this.user, nbS);
                    this.user=us.getUserById(this.user.getId());
                    try {
                        ProfilForm pf=new ProfilForm(this.user);
                        pf.getF().showBack();
                        //PaiementService ps=new PaiementService();
                        //us.modifierSolde(user, nbS);
                        //ps.ajouterPaiement(user, mn, nbS, charge.getId());
                        //payer.setDisable(false);
                        //quitterAction(event);
                    } catch (IOException ex) {
                        //Logger.getLogger(PaiementForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else Dialog.show("Erreur", "Vérifiez vos données", "Retour", null);
            } catch (StripeException e) {
                Dialog.show("Erreur", "Vérifiez vos données", "Retour", null);
            }
        });

    }

    public boolean isValidInput(String input) {

        if (input.endsWith("0") || input.endsWith("1") || input.endsWith("2") || input.endsWith("3") || input.endsWith("4")
                || input.endsWith("5") || input.endsWith("6") || input.endsWith("7") || input.endsWith("8") || input.endsWith("9")) {
            return true;
        } else {
            return false;
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
