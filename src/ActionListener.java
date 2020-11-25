import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

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
            if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText())||!Search.isStation(GUI.DestinationTV.getText())
                    ||!Search.isStation(GUI.startTextField.getText()))
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
                if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText())||!Search.isStation(GUI.DestinationTV.getText())
                        ||!Search.isStation(GUI.startTextField.getText()))
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
                if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText())||!Search.isStation(GUI.DestinationTV.getText())
                        ||!Search.isStation(GUI.startTextField.getText()))
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
                if( GUI.DestinationTV.getText().equals(GUI.startTextField.getText())||!Search.isStation(GUI.DestinationTV.getText())
                    ||!Search.isStation(GUI.startTextField.getText()))
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
                if(GUI.DestinationTV.getText().equals(GUI.startTextField.getText())||!Search.isStation(GUI.DestinationTV.getText())
                        ||!Search.isStation(GUI.startTextField.getText()))
                {
                    GUI.same_station_error();
                }else
                {
                    GUI.apply.setBackground(Color.GREEN);
                    ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                    Station end = possible.get(0);
                    possible = Search.search(GUI.startTextField.getText());
                    GUI.price_Label.setText(Double.toString(Pricing.calculatePrice(possible.get(0),end))+" "+GUI.euro);
                    GUI.no_card.setVisible(true);
                    GUI.card.setVisible(true);
                    GUI.user_HasCard.setVisible(true);
                    GUI.mainFrame.repaint();
                }
            }
        });
        GUI.card.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
        GUI.no_card.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                GUI.print_ticket.setVisible(true);
                GUI.show_Qr.setVisible(true);
                GUI.user_HasCard.setVisible(false);
                GUI.card.setVisible(false);
                GUI.no_card.setVisible(false);
                GUI.mainFrame.repaint();
            }
        });
        GUI.print_ticket.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                Station end = possible.get(0);
                possible = Search.search(GUI.startTextField.getText());
                PrintTicket.print_ticket(possible.get(0),end,Pricing.calculatePrice(possible.get(0),end));
                try {
                    GUI.showTicket();
                    GUI.print_ticket.setVisible(false);
                    GUI.show_Qr.setVisible(false);
                }catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        });
        GUI.show_Qr.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                Station end = possible.get(0);
                possible = Search.search(GUI.startTextField.getText());
             //   PrintTicket.print_ticket(possible.get(0),end,Pricing.calculatePrice(possible.get(0),end));
                try {
                    GUI.Show_Qr();
                    GUI.print_ticket.setVisible(false);
                    GUI.show_Qr.setVisible(false);
                }catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        });
    }
}