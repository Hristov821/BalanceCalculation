/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancecalculation2;

import static java.lang.Math.abs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Balance {

    ArrayList<String> Input = null;
    Date FinalDate = null;
    Date[] Dates = null;
    int[] Moneys = null;
    SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

    public Balance(ArrayList<String> Input) {
        this.setInput(Input);
        this.FilterInput();
        Dates = new Date[Input.size()];
        Moneys = new int[Input.size()];
        this.spliInputintoDatesAndMoneys();
        this.SortDatesAndMoneys();
    }

    private Balance() {
    }

    public void setInput(ArrayList<String> Input) {
        this.Input = Input;
    }

    private void FilterInput() {
        for (int i = 0; i < Input.size(); i++) {
            if (!Input.get(i).matches("^[+-]\\d+[,]([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")) {
                Input.remove(i);
            }
        }
    }

    public void setFinalDate(String FinalDate) {
        try {
            this.FinalDate = formater.parse(FinalDate);
        } catch (ParseException ex) {
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void spliInputintoDatesAndMoneys() {

        for (int i = 0; i < Input.size(); i++) {
            String[] splitInput = Input.get(i).split(",");
            Moneys[i] = Integer.parseInt(splitInput[0]);
            try {
                Dates[i] = formater.parse(splitInput[1]);
            } catch (ParseException ex) {
                Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void SortDatesAndMoneys() {
        for (int i = 0; i < Input.size(); i++) {
            for (int j = 1; j < Input.size(); j++) {
                if (Dates[j - 1].compareTo(Dates[j]) == 1) {
                    Date t1 = Dates[j - 1];
                    int t2 = Moneys[j - 1];
                    Dates[j - 1] = Dates[j];
                    this.Moneys[j - 1] = Moneys[j];
                    Dates[j] = t1;
                    Moneys[j] = t2;
                }
            }
        }
    }

    public Date closestDate() {
        long min = Long.MAX_VALUE;
        Date temp = Dates[0];
        for (Date date : Dates) {
            long diference = abs(date.getTime() - FinalDate.getTime());
            if (diference < min) {
                
                min = diference;
                temp = date;
            }
            else if(diference==0){
                min=diference;
                temp=date;
                break;
            }
        }
        return temp;
    }

    public int finalAmount() {
        int temp = 0;
        int finalAmount = 0;
        Date closestDate = this.closestDate();
        while (Dates[temp].compareTo(closestDate) != 0) {
            finalAmount += Moneys[temp];
            temp++;
        }
        while (temp < Dates.length && Dates[temp].compareTo(closestDate) == 0) {
            finalAmount += Moneys[temp];
            temp++;
        }
        return finalAmount;
    }
  

}
