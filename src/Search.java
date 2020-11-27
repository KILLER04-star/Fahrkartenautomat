import java.util.ArrayList;
import java.util.List;

public class Search 
{
    public static ArrayList<Station> search(String Query) //gibt eine Liste mit möglichen Stationen zurück
    {
        ArrayList<Station> result = new ArrayList<>();
        ExcelReader excelReader = new ExcelReader("src/DB.csv");
        List<Station> search = excelReader.read();

        for (Station station : search)
        {
            if (station.getName().contains(Query) && !station.getName().equals("Station")) result.add(station);
        }
        return result;
    }

    public static boolean isStation(String Query) //Testet, ob ein Name zu einer Station gehört
    {
        ExcelReader excelReader = new ExcelReader("src/DB.csv");
        List<Station> stations = excelReader.read();
        for (Station station : stations)
        {
            if (station.getName().equals(Query)) return true;
        }
        return false;
    }
}