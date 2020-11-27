import javax.swing.*;

public class MoneyButton
{
    JButton button;
    double value;
    public MoneyButton(JButton button,double value)
    {
        this.button = button;
        this.value = value;
    }
     JButton getButton()
    {
        return button;
    }
     double getValue()
    {
        return value;
    }
}
