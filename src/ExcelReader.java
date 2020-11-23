import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader
{
    String path;
    public ExcelReader(String path) 
    {
        this.path = path;
    }
    List<Station> read()
    {
        List<Station> result = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine())!=null)
            {
                String[] values = line.split(";");
                Station station = new Station(values[4],values[7],values[0],values[8]);
                result.add(station);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
