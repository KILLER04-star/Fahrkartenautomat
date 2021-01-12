import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pricing
{
    public static double calculatePrice(Station start, Station destination)
        /*Berechnet den Preis aus den Postleitzahlen
    der Stationen (Obergrenze ist 100€, Untergrenze 5€)*/
    {
        double result;
        if(!start.getPostal_Code().equals(destination.getPostal_Code()))
        {
            double start_val = Double.parseDouble(start.getPostal_Code())/2;
            double end_val = Double.parseDouble(destination.getPostal_Code())/2;
            result = (start_val-end_val)/80;
            if(result<0) result*=-1;
            if(result>100) result = 100;
            if(result<5) result = 5;
        }else result = 5.0;
     /*   String test = Double.toString(result);
        if(test.length()>5)
        {
            System.out.println("Test");
            char[] testChar = new char[5];
            testChar = test.toCharArray();
            test = Arrays.toString(testChar);
        }
        result = Double.parseDouble(test);
      */
        BigDecimal bigDecimal = BigDecimal.valueOf(result);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}