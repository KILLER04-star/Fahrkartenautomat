import java.util.ArrayList;
import java.util.List;

public class Search 
{
    public static ArrayList<Station> search(String Query)
    {
        ArrayList<Station> result = new ArrayList<>();
        List<Station> search;
        ExcelReader excelReader = new ExcelReader("src/DB.csv");
        search = excelReader.read();
        for (int i = 0; i < search.size(); i++) 
        {
            Station station = search.get(i);
            if (station.getName().contains(Query)&&!station.getName().equals("Station"))
            {
                result.add(station);
            }
        }
        return result;
    }
}
