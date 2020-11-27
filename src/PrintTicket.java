import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintTicket
{ //Generiert das Ticket als .png-Datai
    public static void print_ticket(Station start,Station end, Double price)
    {
        SimpleDateFormat sdf_date = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm:ss");
        String text1 = "Von: "+start.getName()+" nach: "+end.getName();
        String text2 = " gekauft am: "+sdf_date.format(new Date())+ " um: "+sdf_time.format(new Date())+" Uhr";
        String text3 = " Preis: "+price+ " "+GUI.euro+ " "+Var.Card;
        BufferedImage img = new BufferedImage(1, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 18);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text1)+100;
        int height = 300;
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text1, 0, fm.getAscent());
        g2d.drawString(text2,0,50);
        g2d.drawString(text3,0,100);
        Font smallFont = new Font("Arial",Font.PLAIN,8);
        g2d.setFont(smallFont);
        g2d.drawString("(zu finden unter src/ticket.png)",0,150);
        g2d.dispose();
        try
        {
            ImageIO.write(img, "png", new File("src/Ticket.png"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}