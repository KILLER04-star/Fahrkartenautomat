import javax.swing.*;

public class Money
{
    double value;
    public static final String[] queries = {"1ct.jpg","2ct.jpg","5ct.jpg","10ct.jpg","20ct.jpg",
            "50ct.jpg","1euro.jpg","2euro.jpg","5euro.jpg",
            "10euro.jpg","20euro.jpg","50euro.jpg"};
    public static final double[] allowed_values = {0.01,0.02,0.05,0.10,0.20,0.50,1.0,2.0,5.0,10.0,20.0,50.0};

    public Money(double value)
    {
        this.value = value;
    }
    public ImageIcon getImage()
    {
        ImageIcon result = null;
        try
        {
            for(int i = 0;i<allowed_values.length;i++)
            {
                if(allowed_values[i]==value)
                {
                    result = new ImageIcon("src/"+queries[i]);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if(result==null) System.out.println("No image found for value: "+value);
        return result;
    }
    public double getValue()
    {
        return value;
    }
}