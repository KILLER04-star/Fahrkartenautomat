import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class GUI
{
    public static JFrame mainFrame = new JFrame("Fahrkarten");
    public static JPanel mainPanel = new JPanel();
    public static JTextField DestinationTV = new JTextField();
    public static JTextField startTextField = new JTextField();
    public static ArrayList<JTextField> possible_station = new ArrayList<>();
    public static JTextField ErrorTV = new JTextField();
    public static JLabel startLabel = new JLabel("von");
    public static JLabel toJLabel = new JLabel("nach");
    public static JButton apply = new JButton("Ok");
    public static String euro = ""+'\u20ac';
    public static JLabel price_Label = new JLabel("Preis in "+euro);
    public static JLabel user_HasCard = new JLabel("Haben sie eine Bahncard?");
    public static JButton card = new JButton("ja");
    public static JButton no_card = new JButton("nein");
    public static JButton print_ticket = new JButton("Ticket drucken");
    public static JButton show_Qr = new JButton("QR-Code anzeigen");
    public static BufferedImage bufferedImage;

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

        apply.setVisible(true);
        apply.setEnabled(true);
        apply.setBounds(startTextField.getX(),200,200,30);
        mainPanel.add(apply);

        price_Label.setEnabled(true);
        price_Label.setVisible(true);
        price_Label.setBounds(startTextField.getX(),250,200,30);
        mainPanel.add(price_Label);

        user_HasCard.setEnabled(true);
        user_HasCard.setVisible(false);
        user_HasCard.setBounds(startTextField.getX(),300,200,30);
        mainPanel.add(user_HasCard);

        card.setEnabled(true);
        card.setVisible(false);
        card.setBounds(startTextField.getX(),340,70,20);
        card.setBackground(Color.GREEN);
        mainPanel.add(card);

        no_card.setEnabled(true);
        no_card.setVisible(false);
        no_card.setBounds(startTextField.getX()+card.getWidth()*2,340,70,20);
        no_card.setBackground(Color.RED);
        mainPanel.add(no_card);

        print_ticket.setVisible(false);
        print_ticket.setEnabled(true);
        print_ticket.setBounds(startTextField.getX(),850,1920/2,50);
        print_ticket.setBackground(Color.CYAN);
        mainPanel.add(print_ticket);

        show_Qr.setVisible(false);
        show_Qr.setEnabled(true);
        show_Qr.setBounds(startTextField.getX(),950,1920/2,50);
        show_Qr.setBackground(Color.ORANGE);
        mainPanel.add(show_Qr);

        mainFrame.add(mainPanel);
    }
    public static void same_station_error()
    {
        apply.setBackground(Color.RED);
        mainFrame.repaint();
        paint();
    }
    public static void showTicket()
    {
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
        ticketFrame.setVisible(true);
        ticketFrame.setEnabled(true);


        ticket_panel.setVisible(true);
        ticket_panel.setLayout(null);
        ticket_panel.setEnabled(true);

        JButton ok = new JButton("Schliessen");
        ok.setEnabled(true);
        ok.setVisible(true);
        ok.setBounds(ticketFrame.getWidth()/2-50,200,100,30);
        ok.setBackground(Color.CYAN);
        ticket_panel.add(ok);

        ticketFrame.add(ticket_panel);

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
        JFrame qrFrame = new JFrame("QR");
        final JPanel qr_panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                File f;
                try
                {
                    f = new File("src/qr.png");
                    bufferedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
                    bufferedImage = ImageIO.read(f);
                    g.drawImage(bufferedImage, 0, 0, null);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        qrFrame.setResizable(false);
        qrFrame.setBounds(500,500,500,300);
        qrFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        qrFrame.setVisible(true);
        qrFrame.setEnabled(true);


        qr_panel.setVisible(true);
        qr_panel.setLayout(null);
        qr_panel.setEnabled(true);

        JButton ok = new JButton("Schliessen");
        ok.setEnabled(true);
        ok.setVisible(true);
        ok.setBounds(qrFrame.getWidth()/2-50,200,100,30);
        ok.setBackground(Color.CYAN);
        qr_panel.add(ok);

        qrFrame.add(qr_panel);

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
