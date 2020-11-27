import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ActionListener
{
    public static void Actions()
    {
        GUI.startTextField.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                startTVActions();
            }
        });
        GUI.startTextField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                startTVActions();
            }
        });

        GUI.DestinationTV.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DestinationTVActions();
            }
        });
        GUI.DestinationTV.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                DestinationTVActions();
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
                    GUI.user_HasCard.setText("Haben sie eine Bahncard?");
                    GUI.apply.setBackground(Color.GREEN);
                    ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                    Station end = possible.get(0);
                    possible = Search.search(GUI.startTextField.getText());
                    GUI.price_Label.setText((Pricing.calculatePrice(possible.get(0),end))+" "+GUI.euro);
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
                GUI.no_card.setVisible(false);
                GUI.user_HasCard.setText("Bitte wählen: ");
                GUI.card.setVisible(false);
                GUI.BC25_Btn.setVisible(true);
                GUI.BC50_Btn.setVisible(true);
                GUI.mainFrame.repaint();
            }
        });
        GUI.no_card.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Var.Card="";
                ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                Station end = possible.get(0);
                possible = Search.search(GUI.startTextField.getText());
                Var.Price = Pricing.calculatePrice(possible.get(0),end);
                GUI.price_Label.setText((Pricing.calculatePrice(possible.get(0),end))+" "+GUI.euro);
                GUI.print_ticket.setVisible(true);
                GUI.show_Qr.setVisible(true);
                GUI.user_HasCard.setVisible(false);
                GUI.card.setVisible(false);
                GUI.no_card.setVisible(false);
                Var.still_to_pay = Var.Price;
                GUI.still_to_pay_label.setText("Noch zu zahlen: "+Var.still_to_pay+" "+GUI.euro);
                GUI.mainFrame.repaint();
                Var.hasToPay = true;
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
                PrintTicket.print_ticket(possible.get(0),end,Var.Price);
                try
                {
                    if(Var.isPayed)
                    {
                        GUI.showTicket();
                        GUI.print_ticket.setVisible(false);
                        GUI.show_Qr.setVisible(false);
                    }
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
                try
                {
                    if(Var.isPayed)
                    {
                        GUI.Show_Qr();
                        GUI.print_ticket.setVisible(false);
                        GUI.show_Qr.setVisible(false);
                    }
                }catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        });
        GUI.BC25_Btn.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Station> stations = Search.search(GUI.startTextField.getText());
                Station station_start = stations.get(0);
                stations = Search.search(GUI.DestinationTV.getText());
                Station station_end = stations.get(0);
                Var.Price = Math.round(((Pricing.calculatePrice(station_start,station_end)/4)*3)*100)/100;
                Var.Card = "Bahncard 25";
                Var.still_to_pay = Var.Price;
                GUI.still_to_pay_label.setText("Noch zu zahlen: "+Var.still_to_pay+" "+GUI.euro);
                Label_setText();
                Var.hasToPay = true;
            }
        });
        GUI.BC50_Btn.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Station> stations = Search.search(GUI.startTextField.getText());
                Station station_start = stations.get(0);
                stations = Search.search(GUI.DestinationTV.getText());
                Station station_end = stations.get(0);
                Var.Price = Math.round(((Pricing.calculatePrice(station_start,station_end)/4)*2)*100)/100;
                Var.Card = "Bahncard 50";
                Var.still_to_pay = Var.Price;
                GUI.still_to_pay_label.setText("Noch zu zahlen: "+Var.still_to_pay+" "+GUI.euro);
                Label_setText();
                Var.hasToPay = true;
            }
        });
        money_actions();
    }

    private static void Label_setText()
    {
        GUI.price_Label.setText("Preis in "+ GUI.euro+" : "+Var.Price);
        GUI.BC50_Btn.setVisible(false);
        GUI.BC25_Btn.setVisible(false);
        GUI.user_HasCard.setVisible(false);
        GUI.print_ticket.setVisible(true);
        GUI.show_Qr.setVisible(true);
    }

    public static void DestinationTVActions()
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
    }else GUI.apply.setBackground(Color.GREEN);
}
public static void startTVActions()
{
    String Query = GUI.startTextField.getText();
    GUI.list_stations(Search.search(Query), Query, GUI.startTextField, GUI.DestinationTV);
    GUI.startTextField.requestFocus();
    GUI.mainFrame.repaint();
    if (GUI.DestinationTV.getText().equals(GUI.startTextField.getText()) || !Search.isStation(GUI.DestinationTV.getText())
            || !Search.isStation(GUI.startTextField.getText())) {
        GUI.same_station_error();
    } else GUI.apply.setBackground(Color.GREEN);
}
public static void money_actions()
{
    for(int i = 0;i<GUI.moneyButtons.size();i++)
    {
        MoneyButton moneyButton = GUI.moneyButtons.get(i);
        moneyButton.getButton().addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Var.still_to_pay -= moneyButton.getValue();
                System.out.println(Var.still_to_pay);
                GUI.still_to_pay_label.setText("Noch zu zahlen: "+Var.still_to_pay+" "+GUI.euro);
                GUI.mainFrame.repaint();
            }
        });
    }
}
}