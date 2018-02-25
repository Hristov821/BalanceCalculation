
package balancecalculation2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BalanceCalculation2 {

 
    public static void main(String[] args) {
       BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> rawInput = new ArrayList();
        Balance balance=null;
        try {
            String Input=reader.readLine();
            while(!Input.equals("----")){
                rawInput.add(Input);
                Input=reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(BalanceCalculation2.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        balance=new Balance(rawInput);
        
        
        try{
            String FinalDateInput=reader.readLine();
            while(!FinalDateInput.matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")){
                balance.setFinalDate(FinalDateInput);
                FinalDateInput=reader.readLine();
            }
            balance.setFinalDate(FinalDateInput);
        }
       catch (IOException ex) {
            Logger.getLogger(BalanceCalculation2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.printf("%+d",balance.finalAmount());
 
    }
    
}
