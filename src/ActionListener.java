import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*fast sämtliche Actions (außer diejenigen aus den Listen) werden hier erkannt und dann verarbeitet*/
public class ActionListener
{
    public static void Actions()
    {
        GUI.startTextField.addActionListener(new java.awt.event.ActionListener() //Nutzer drückt Enter im Feld für die erste Station
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                startTVActions();
            }
        });
        GUI.startTextField.addKeyListener(new KeyAdapter() //Nutzer schreibt Text in das Feld für die erste Station
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                startTVActions();
            }
        });

        GUI.DestinationTV.addActionListener(new java.awt.event.ActionListener() //Nutzer drückt Enter im Feld für die zweite Station
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DestinationTVActions();
            }
        });
        GUI.DestinationTV.addKeyListener(new KeyAdapter() //Nutzer schreibt Text in das Feld für die Zweite Station
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
            public void actionPerformed(ActionEvent e) //Nutzer drückt Ok-Button
            {
                if (GUI.DestinationTV.getText().equals(GUI.startTextField.getText()) || !Search.isStation(GUI.DestinationTV.getText())
                        || !Search.isStation(GUI.startTextField.getText()))
                {
                    GUI.same_station_error();
                } else
                    {
                    GUI.user_HasCard.setText("Haben sie eine Bahncard?");
                    GUI.apply.setBackground(Color.GREEN);
                    ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                    Station end = possible.get(0);
                    possible = Search.search(GUI.startTextField.getText());
                    GUI.price_Label.setText((Pricing.calculatePrice(possible.get(0), end)) + " " + GUI.euro);
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
            public void actionPerformed(ActionEvent e) //Nutzer besitzt eine Bahncard
            {
                GUI.no_card.setVisible(false);
                GUI.user_HasCard.setText("Bitte wählen: ");
                GUI.card.setVisible(false);
                GUI.BC25_Btn.setVisible(true);
                GUI.BC50_Btn.setVisible(true);
                GUI.mainFrame.repaint();
            }
        });
        GUI.no_card.addActionListener(new java.awt.event.ActionListener() //Nutzer besitzt keine Bahncard
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Var.Card = "";
                ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                Station end = possible.get(0);
                possible = Search.search(GUI.startTextField.getText());
                Var.Price = Pricing.calculatePrice(possible.get(0), end);
                GUI.price_Label.setText((Pricing.calculatePrice(possible.get(0), end)) + " " + GUI.euro);
                GUI.print_ticket.setVisible(true);
                GUI.show_Qr.setVisible(true);
                GUI.user_HasCard.setVisible(false);
                GUI.card.setVisible(false);
                GUI.no_card.setVisible(false);
                Var.still_to_pay = Var.Price;
                GUI.still_to_pay_label.setText("Noch zu zahlen: " + Var.still_to_pay + " " + GUI.euro);
                GUI.mainFrame.repaint();
                Var.hasToPay = true;
            }
        });
        GUI.print_ticket.addActionListener(new java.awt.event.ActionListener() //Nutzer möchte das Ticket edruckt bekommen
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                Station end = possible.get(0);
                possible = Search.search(GUI.startTextField.getText());
                PrintTicket.print_ticket(possible.get(0), end, Var.Price);
                try
                {
                    if (Var.isPayed)
                    {
                        GUI.showTicket();
                        GUI.print_ticket.setVisible(false);
                        GUI.show_Qr.setVisible(false);
                    }
                } catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        });
        GUI.show_Qr.addActionListener(new java.awt.event.ActionListener() //Nutzer möchte einen QR-Code sehen
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Station> possible = Search.search(GUI.DestinationTV.getText());
                Station end = possible.get(0);
                possible = Search.search(GUI.startTextField.getText());
                try
                {
                    if (Var.isPayed)
                    {
                        generate_Ticket_Information(possible.get(0),end);
                        GUI.Show_Qr();
                        GUI.print_ticket.setVisible(false);
                        GUI.show_Qr.setVisible(false);
                    }
                } catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        });
        GUI.BC25_Btn.addActionListener(new java.awt.event.ActionListener() //Nutzer hat eine Bahncard 25
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Station> stations = Search.search(GUI.startTextField.getText());
                Station station_start = stations.get(0);
                stations = Search.search(GUI.DestinationTV.getText());
                Station station_end = stations.get(0);
                Var.Price = Math.round(((Pricing.calculatePrice(station_start, station_end) / 4) * 3) * 100) / 100;
                Var.Card = "Bahncard 25";
                Var.still_to_pay = Var.Price;
                GUI.still_to_pay_label.setText("Noch zu zahlen: " + Var.still_to_pay + " " + GUI.euro);
                Label_setText();
                Var.hasToPay = true;
            }
        });
        GUI.BC50_Btn.addActionListener(new java.awt.event.ActionListener() //Nutzer hat eine Bahncard 50
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Station> stations = Search.search(GUI.startTextField.getText());
                Station station_start = stations.get(0);
                stations = Search.search(GUI.DestinationTV.getText());
                Station station_end = stations.get(0);
                Var.Price = Math.round(((Pricing.calculatePrice(station_start, station_end) / 4) * 2) * 100) / 100;
                Var.Card = "Bahncard 50";
                Var.still_to_pay = Var.Price;
                GUI.still_to_pay_label.setText("Noch zu zahlen: " + Var.still_to_pay + " " + GUI.euro);
                Label_setText();
                Var.hasToPay = true;
            }
        });
    }

    private static void Label_setText()
    {
        GUI.price_Label.setText("Preis in " + GUI.euro + " : " + Var.Price);
        GUI.BC50_Btn.setVisible(false);
        GUI.BC25_Btn.setVisible(false);
        GUI.user_HasCard.setVisible(false);
        GUI.print_ticket.setVisible(true);
        GUI.show_Qr.setVisible(true);
    }

    public static void DestinationTVActions()
    { //Aktion für das Generieren der Liste an möglichen Zielen (Start und Ziel können nicht identisch sein)
        String Query = GUI.DestinationTV.getText();
        if (Query.equals(GUI.startTextField.getText()))
        {
            GUI.same_station_error();
        } else GUI.list_stations(Search.search(Query), Query, GUI.DestinationTV, GUI.startTextField);
        GUI.DestinationTV.requestFocus();
        GUI.mainFrame.repaint();
        if (GUI.DestinationTV.getText().equals(GUI.startTextField.getText()) || !Search.isStation(GUI.DestinationTV.getText())
                || !Search.isStation(GUI.startTextField.getText()))
        {
            GUI.same_station_error();
        } else GUI.apply.setBackground(Color.GREEN);
    }

    public static void startTVActions()
    {//Aktion für das Generieren der Liste an möglichen Startpunkten (Start und Ziel können nicht identisch sein)
        String Query = GUI.startTextField.getText();
        GUI.list_stations(Search.search(Query), Query, GUI.startTextField, GUI.DestinationTV);
        GUI.startTextField.requestFocus();
        GUI.mainFrame.repaint();
        if (GUI.DestinationTV.getText().equals(GUI.startTextField.getText()) || !Search.isStation(GUI.DestinationTV.getText())
                || !Search.isStation(GUI.startTextField.getText()))
        {
            GUI.same_station_error();
        } else GUI.apply.setBackground(Color.GREEN);
    }
    public static void generate_Ticket_Information(Station start, Station end)
    {
        SimpleDateFormat sdf_date = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm:ss");
        Var.Ticket_line1 = "Von: "+start.getName()+" nach: "+end.getName();
        Var.Ticket_line2 = " gekauft am: "+sdf_date.format(new Date())+ " um: "+sdf_time.format(new Date())+" Uhr";
        Var.Ticket_line3 = " Preis: "+Var.Price+ " "+GUI.euro+ " "+Var.Card;
    }
}