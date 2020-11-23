public class main
{
    public static void main(String[] args)
    {
        ExcelReader reader = new ExcelReader("src/DB.csv");
        reader.read();
        GUI.createGUI();
        ActionListener.Actions();
    }
}
