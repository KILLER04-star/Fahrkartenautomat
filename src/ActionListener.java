import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ActionListener
{
    public static void Actions()
    {
        GUI.startTextField.addActionListener(e ->
        {
            String Query = GUI.startTextField.getText();
            GUI.list_stations(Search.search(Query),Query,GUI.startTextField,GUI.DestinationTV);
            GUI.startTextField.requestFocus();
            GUI.mainFrame.repaint();
            if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText()))
            {
                GUI.same_station_error();
            }else
            {
                GUI.apply.setBackground(Color.GREEN);
            }
        });
        GUI.startTextField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                String Query = GUI.startTextField.getText();
                GUI.list_stations(Search.search(Query), Query, GUI.startTextField, GUI.DestinationTV);
                GUI.startTextField.requestFocus();
                GUI.mainFrame.repaint();
                if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText()))
                {
                    GUI.same_station_error();
                }else
                {
                    GUI.apply.setBackground(Color.GREEN);
                }
            }
        });

        GUI.DestinationTV.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String Query = GUI.DestinationTV.getText();
                if(Query.equals(GUI.startTextField.getText()))
                {
                    GUI.same_station_error();
                }else GUI.list_stations(Search.search(Query),Query,GUI.DestinationTV,GUI.startTextField);
                GUI.DestinationTV.requestFocus();
                GUI.mainFrame.repaint();
                if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText()))
                {
                    GUI.same_station_error();
                }else
                {
                    GUI.apply.setBackground(Color.GREEN);
                }
            }
        });
        GUI.DestinationTV.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                    String Query = GUI.DestinationTV.getText();
                    if(Query.equals(GUI.startTextField.getText()))
                    {
                        GUI.same_station_error();
                    }else GUI.list_stations(Search.search(Query),Query,GUI.DestinationTV,GUI.startTextField);
                    GUI.DestinationTV.requestFocus();
                    GUI.mainFrame.repaint();
                if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText()))
                {
                    GUI.same_station_error();
                }else
                {
                    GUI.apply.setBackground(Color.GREEN);
                }
            }
        });
        GUI.apply.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText()))
                {
                    GUI.same_station_error();
                }else
                {
                    GUI.apply.setBackground(Color.GREEN);
                }
            }
        });
    }
}