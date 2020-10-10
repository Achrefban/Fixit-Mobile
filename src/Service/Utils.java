/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

//import java.sql.Timestamp;
//import java.text.DecimalFormat;

/**
 *
 * @author Yassine
 */
public class Utils {
    public static String upperCaseFirst(String value) {

        // Convert String to char array.
        char[] array = value.toCharArray();
        // Modify first element in array.
        array[0] = Character.toUpperCase(array[0]);
        // Return string.
        return new String(array);
    }
    public static String convertMonth(int month) {
        if (month==1) return "Janvier";
        else if (month==2) return "Février";
        else if (month==3) return "Mars";
        else if (month==4) return "Avril";
        else if (month==5) return "Mai";
        else if (month==6) return "Juin";
        else if (month==7) return "Juillet";
        else if (month==8) return "Août";
        else if (month==9) return "Septembre";
        else if (month==10) return "Octobre";
        else if (month==11) return "Novembre";
        else return "Décembre";
    }
    /*public static String formatDateTime(Timestamp m)
    {
    String jour=String.valueOf(m.getDate());
    String mois=convertMonth(m.getMonth()+1);
    int an=m.getYear()+1900;
    String annee=String.valueOf(an);
    String heure=String.format("%02d", m.getHours());
    String minute=String.format("%02d", m.getMinutes());
    String date=jour+" "+mois+" "+annee+" à "+heure+":"+minute;
    return date;
    }*/
    public static boolean checkVoyelle(String s)
    {
        if (s!=null && s!="")
        {
            char ch=s.charAt(0);
            if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' ||ch == 'I' || ch == 'o' || ch == 'O' || ch == 'u' || ch=='U')
            {
                return true;
            }
            else return false;
        }
        return false;
    }
}
