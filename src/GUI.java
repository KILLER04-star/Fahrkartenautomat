import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GUI
{
    public static final JFrame mainFrame = new JFrame("Fahrkarten");
    public static JPanel mainPanel = new JPanel();
    public static final JTextField DestinationTV = new JTextField();
    public static final JTextField startTextField = new JTextField();
    public static final ArrayList<JTextField> possible_station = new ArrayList<>();
    public static final JTextField ErrorTV = new JTextField();
    public static final JLabel startLabel = new JLabel("von");
    public static final JLabel toJLabel = new JLabel("nach");
    public static final JButton apply = new JButton("Ok");
    public static final String euro = ""+'\u20ac';
    public static final JLabel price_Label = new JLabel("Preis in "+euro);
    public static final JLabel user_HasCard = new JLabel("Haben sie eine Bahncard?");
    public static final JButton card = new JButton("ja");
    public static final JButton no_card = new JButton("nein");
    public static final JButton print_ticket = new JButton("Ticket drucken");
    public static final JButton show_Qr = new JButton("QR-Code anzeigen");
    public static BufferedImage bufferedImage;
    public static final JButton BC25_Btn = new JButton("Bahncard 25");
    public static final JButton BC50_Btn = new JButton("Bahncard 50");
    public static BufferedImage insert_coin;

    static
    {
        try
        {
            insert_coin = ImageIO.read(new File("src/insert_coin.jpg"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static final int possible_station_x = 350;
    public static int possible_station_y = 50;

    public static int size = 0;

    public static  int money_x = 1000;
    public static  int money_y = 200;

    public static ArrayList<MoneyButton> moneyButtons = new ArrayList<>();
    public static JLabel still_to_pay_label = new JLabel();
    public static JLabel no_change = new JLabel();

    public static final String[] queries = {"1ct.jpg","2ct.jpg","5ct.jpg","10ct.jpg","20ct.jpg",
            "50ct.jpg","1euro.jpg","2euro.jpg","5euro.jpg",
            "10euro.jpg","20euro.jpg","50euro.jpg"};

    public static void createGUI()
    {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setBounds(0,0,d.width,d.height);
        mainFrame.setEnabled(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        mainPanel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                if(insert_coin!=null)
                {
                    g.drawImage(insert_coin,1000,25,insert_coin.getWidth()/2,insert_coin.getHeight()/2,null);
                }
            }
        };
        apply.setVisible(true);
        apply.setEnabled(true);
        apply.setBounds(50,200,200,30);
        apply.setBackground(Color.RED);
        mainPanel.add(apply);
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
                        apply.setBackground(Color.GREEN);
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
        money_x = 1000;
        money_y = 200;

        mainPanel.setLayout(null);
        mainPanel.setEnabled(true);

        int ops = 0;

        for(int i = 0;i<4;i++) //zeichnet Geldscheine und lässt den Nutzer Bezahlen
        {
            money_x = 1000;
            int height = 0;
            for(int y = 0;y<3;y++)
            {
                JButton button = new JButton();
                button.setVisible(true);
                button.setEnabled(true);
                ImageIcon imageIcon = new ImageIcon("src/"+queries[ops]);
                button.setIcon(imageIcon);
                button.setBorder(null);
                button.setBorderPainted(false);
                if(queries[ops].equals("5euro.jpg"))
                {
                    money_x = 1000;
                    money_y+=height;
                }
                button.setBounds(money_x,money_y,imageIcon.getIconWidth(),imageIcon.getIconHeight());
                mainPanel.add(button);
                MoneyButton moneyButton = new MoneyButton(button,Var.allowed_values[ops]);
                moneyButtons.add(moneyButton);
                mainFrame.repaint();
                money_x +=imageIcon.getIconWidth()+10;
                if(height<imageIcon.getIconHeight())height = imageIcon.getIconHeight();
                ops++;
                button.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if(Var.hasToPay)
                        {
                            Var.still_to_pay -= moneyButton.getValue();
                            GUI.still_to_pay_label.setText("Noch zu zahlen: " + Var.still_to_pay + " " + GUI.euro);
                            GUI.mainFrame.repaint();
                            if(Var.still_to_pay<=0)
                            {
                                Var.isPayed = true;
                                Var.hasToPay = false;
                                Var.still_to_pay = 0;
                            }
                        }
                    }
                });
            }
            money_y +=height;
            mainFrame.repaint();
        }
        mainFrame.repaint();
        /*Tut nichts weiter, als sämtliche Objekte in das Frame zu zeichnen*/
        ImageIcon BC25_Icon = new ImageIcon("src/BC25.jpg");
        ImageIcon BC50_Icon = new ImageIcon("src/BC50.jpg");

        BC25_Btn.setVisible(false);
        BC25_Btn.setEnabled(true);
        BC25_Btn.setIcon(BC25_Icon);
        BC25_Btn.setBounds(startTextField.getX(),400,BC25_Icon.getIconWidth(),BC25_Icon.getIconHeight());
        BC25_Btn.setBorderPainted(false);
        BC25_Btn.setBorder(null);
        mainPanel.add(BC25_Btn);

        apply.setVisible(true);
        apply.setEnabled(true);
        apply.setBounds(50,200,200,30);
        apply.setBorderPainted(false);
        apply.setBorder(null);
        mainPanel.add(apply);

        BC50_Btn.setVisible(false);
        BC50_Btn.setEnabled(true);
        BC50_Btn.setIcon(BC50_Icon);
        BC50_Btn.setBounds(startTextField.getX()+BC25_Btn.getWidth()+20,400,BC50_Icon.getIconWidth(),BC50_Icon.getIconHeight());
        BC50_Btn.setBorderPainted(false);
        BC50_Btn.setBorder(null);
        mainPanel.add(BC50_Btn);

        startTextField.setBounds(50,50,300,30);
        startTextField.setEnabled(true);
        startTextField.setEditable(true);
        startTextField.setVisible(true);
        mainPanel.add(startTextField);

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

        price_Label.setEnabled(true);
        price_Label.setVisible(true);
        price_Label.setBounds(startTextField.getX(),250,200,30);
        mainPanel.add(price_Label);

        GUI.user_HasCard.setText("Haben sie eine Bahncard?");
        user_HasCard.setEnabled(true);
        user_HasCard.setVisible(false);
        user_HasCard.setBounds(startTextField.getX(),300,200,30);
        mainPanel.add(user_HasCard);

        card.setEnabled(true);
        card.setVisible(false);
        card.setBounds(startTextField.getX(),340,70,20);
        card.setBackground(Color.GREEN);
        card.setBorderPainted(false);
        card.setBorder(null);
        mainPanel.add(card);

        no_card.setEnabled(true);
        no_card.setVisible(false);
        no_card.setBounds(startTextField.getX()+card.getWidth()*2,340,70,20);
        no_card.setBackground(Color.RED);
        no_card.setBorderPainted(false);
        no_card.setBorder(null);
        mainPanel.add(no_card);

        print_ticket.setVisible(false);
        print_ticket.setEnabled(true);
        print_ticket.setBounds(startTextField.getX(),850,1920/2,50);
        print_ticket.setBackground(Color.CYAN);
        print_ticket.setBorderPainted(false);
        print_ticket.setBorder(null);
        mainPanel.add(print_ticket);

        show_Qr.setVisible(false);
        show_Qr.setEnabled(true);
        show_Qr.setBounds(startTextField.getX(),950,1920/2,50);
        show_Qr.setBackground(Color.ORANGE);
        show_Qr.setBorderPainted(false);
        show_Qr.setBorder(null);
        mainPanel.add(show_Qr);

        still_to_pay_label.setEnabled(true);
        still_to_pay_label.setVisible(true);
        still_to_pay_label.setBounds(1500,25,200,50);
        still_to_pay_label.setBorder(null);
        mainPanel.add(still_to_pay_label);

        no_change.setVisible(true);
        no_change.setText("Hinweis: Automat gibt kein Wechselgeld.");
        no_change.setEnabled(true);
        no_change.setBounds(still_to_pay_label.getX(),still_to_pay_label.getHeight()
                +still_to_pay_label.getY()+10,300,50);
        no_change.setBorder(null);
        mainPanel.add(no_change);

        mainPanel.setVisible(true);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
    public static void same_station_error()
    {
        apply.setBackground(Color.RED);
        mainFrame.repaint();
        paint();
    }
    public static void showTicket()
    { //Zeigt das generierte Ticket in einem seperatem Fenster
        JFrame ticketFrame = new JFrame("Ticket");
        final JPanel ticket_panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                File f;
                try
                {
                    f = new File("src/ticket.png");
                    bufferedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
                    bufferedImage = ImageIO.read(f);
                    g.drawImage(bufferedImage, 0, 0, null);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        ticketFrame.setResizable(false);
        ticketFrame.setBounds(500,500,500,300);
        ticketFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ticketFrame.setEnabled(true);

        ticket_panel.setLayout(null);
        ticket_panel.setEnabled(true);

        JButton ok = new JButton("Schliessen");
        ok.setEnabled(true);
        ok.setVisible(true);
        ok.setBounds(ticketFrame.getWidth()/2-50,200,100,30);
        ok.setBackground(Color.CYAN);

        ticket_panel.add(ok);
        ticket_panel.setVisible(true);
        ticketFrame.add(ticket_panel);
        ticketFrame.setVisible(true);

        ok.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ticketFrame.removeAll();
                ticketFrame.setVisible(false);
                ticketFrame.setEnabled(false);
            }
        });
    }
    public static void Show_Qr()
    {
        //zeigt den generierten QR-Code in einem seperatem Fenster
        JFrame qrFrame = new JFrame("QR");
        String final_info = Var.Ticket_line1+'\n'+Var.Ticket_line2+'\n'+Var.Ticket_line3;
        final int[] width = {0};
        final JPanel qr_panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                try
                {
                    bufferedImage = QrCodeGenerator.generateQRCodeImage(final_info);
                    g.drawImage(bufferedImage, 0, 0, null);
                    width[0] = bufferedImage.getWidth();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        qrFrame.setResizable(false);
        qrFrame.setBounds(500,500,500,300);
        qrFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        qrFrame.setEnabled(true);


        qr_panel.setLayout(null);
        qr_panel.setEnabled(true);

        JButton ok = new JButton("Schliessen");
        ok.setEnabled(true);
        ok.setVisible(true);
        ok.setBounds(350,200,100,30);
        ok.setBackground(Color.CYAN);
        qr_panel.add(ok);

        qr_panel.setVisible(true);
        qrFrame.add(qr_panel);
        qrFrame.repaint();
        qrFrame.setVisible(true);

        ok.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                qrFrame.removeAll();
                qrFrame.setVisible(false);
                qrFrame.setEnabled(false);
            }
        });
    }
}