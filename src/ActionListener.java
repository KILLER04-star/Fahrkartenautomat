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
        });
        GUI.startTextField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                String Query = GUI.startTextField.getText();
                GUI.list_stations(Search.search(Query), Query, GUI.startTextField, GUI.DestinationTV);
                GUI.startTextField.requestFocus();
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
            }
        });

    }
}