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
            result = start_val-end_val;
            if(result<0) result*=-1;
            if(result>100) result = 100;
        }else result = 5.0;
        return result;
    }
}