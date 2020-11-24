import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI
{
    public static JFrame mainFrame = new JFrame("Fahrkarten");
    public static JPanel mainPanel = new JPanel();
    public static JTextField DestinationTV = new JTextField();
    public static JTextField startTextField = new JTextField();
    public static ArrayList<JTextField> possible_station = new ArrayList<>();
    public static  JTextField ErrorTV = new JTextField();
    public static JLabel startLabel = new JLabel("von");
    public static JLabel toJLabel = new JLabel("nach");

    public static int possible_station_x = 350;
    public static int possible_station_y = 50;

    public static int size = 0;

    public static void createGUI()
    {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setBounds(0,0,d.width,d.height);
        mainFrame.setEnabled(true);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        paint();
    }
    public static void list_stations(ArrayList<Station> list,String Query,JTextField TV,JTextField OtherTV)
    {
        ArrayList<JTextField> collection = possible_station;
        possible_station.removeAll(collection);
        mainPanel.removeAll();
        mainFrame.add(mainPanel);
        mainFrame.repaint();
        paint();
        if(list.size() ==0 )
        {
            ErrorTV.setText("Fehler: Suche für "+Query+" ergab 0 Ergebnisse");
            ErrorTV.setBounds(possible_station_x,possible_station_y,300,30);
            ErrorTV.setBounds(TV.getX(),possible_station_y+10+startTextField.getHeight(),300,30);
            ErrorTV.setVisible(true);
            ErrorTV.setEnabled(true);
            ErrorTV.setEditable(false);
            mainPanel.add(ErrorTV);
        }else
        {
            ErrorTV.setVisible(false);
            ErrorTV.setEnabled(true);
            ErrorTV.setEditable(false);
            mainPanel.remove(ErrorTV);
        }
        mainFrame.add(mainPanel);
        paint();
        size = list.size();
        if(size>20) size= 20;
        for(int i = 0;i<size;i++)
        {
            JTextField textField = new JTextField(list.get(i).getName());
            textField.setBounds(possible_station_x,possible_station_y,300,30);
            textField.setBounds(TV.getX(),possible_station_y+TV.getHeight()+10,300,30);
            textField.setVisible(true);
            textField.setEnabled(true);
            textField.setEditable(false);
            possible_station_y+=40;
            mainPanel.add(textField);
            mainFrame.add(mainPanel);
            paint();
            textField.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if(textField.getText().equals(OtherTV.getText()))
                    {
                        same_station_error();
                    }else
                    {
                        TV.setText(textField.getText());
                        possible_station.removeAll(collection);
                        mainPanel.removeAll();
                        mainFrame.add(mainPanel);
                        mainFrame.repaint();
                        paint();
                    }
                }
            });
            possible_station.add(textField);
        }
        possible_station_y = 50;
    }
    public static void paint()
    {
        mainPanel.setVisible(true);
        mainPanel.setLayout(null);
        mainPanel.setEnabled(true);

        startTextField.setBounds(50,50,300,30);
        startTextField.setEnabled(true);
        startTextField.setEditable(true);
        startTextField.setVisible(true);
        mainPanel.add(startTextField);

        DestinationTV.setBounds(800,50,300,30);
        startLabel.setVisible(true);
        startLabel.setEnabled(true);
        startLabel.setBounds(50,25,300,25);
        mainPanel.add(startLabel);

        toJLabel.setBounds(startTextField.getX()+startTextField.getWidth()+50,25,300,25);
        toJLabel.setVisible(true);
        toJLabel.setEnabled(true);
        mainPanel.add(toJLabel);

        DestinationTV.setBounds(startTextField.getX()+startTextField.getWidth()+50,50,300,30);
        DestinationTV.setEnabled(true);
        DestinationTV.setEditable(true);
        DestinationTV.setVisible(true);
        mainPanel.add(DestinationTV);

        mainFrame.add(mainPanel);
    }
    public static void same_station_error()
    {
        mainFrame.repaint();
        ErrorTV.setText("Sie müssen zwei verschiedene Stationen auswählen!");
        ErrorTV.setVisible(true);
        ErrorTV.setEditable(false);
        ErrorTV.setEnabled(true);
        mainPanel.add(ErrorTV);
        mainFrame.add(mainPanel);
        paint();
    }
}
