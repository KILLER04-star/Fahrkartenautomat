import java.awt.event.ActionEvent;

public class ActionListener
{
    public static void Actions()
    {
        GUI.startTextField.addActionListener(e -> 
        {
            String Query = GUI.startTextField.getText();
            GUI.list_stations(Search.search(Query),Query,GUI.startTextField,GUI.DestinationTV);
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
            }
        });
    }
}
