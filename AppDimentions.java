import java.awt.Toolkit;
import java.awt.Dimension;

public class AppDimentions {
    

    //App dimentions
    static Dimension FRAMEDIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    static Dimension BANNERPANELDIMENSIONS = new Dimension((int) FRAMEDIMENSIONS.getWidth(), 190);
    
    
    static Dimension REDPANELDIMENSIONS = new Dimension(400, (int) FRAMEDIMENSIONS.getHeight() - 280);
    static Dimension SCROLLPANEDIMENSIONS = new Dimension((int) REDPANELDIMENSIONS.getWidth() - 100, (int) (2/3*REDPANELDIMENSIONS.getHeight()));
    static Dimension DELETEBUTTONDIMENSIONS = new Dimension(30, 30);
    static Dimension ADDBUTTONDIMENSIONS = new Dimension((int) (SCROLLPANEDIMENSIONS.getWidth()*0.4), 30);
    static Dimension REGISTRYBUTTONDIMENSIONS = new Dimension((int) (SCROLLPANEDIMENSIONS.getWidth()*0.55), 30);
    static Dimension TEXTPANEDIMENSIONS = new Dimension(185, 30);
    

    static Dimension ORDERPANELDIMENSIONS = new Dimension((int) ((FRAMEDIMENSIONS.getWidth() - 400)/2) + 270, (int) FRAMEDIMENSIONS.getHeight() - 280);
    static Dimension ORDERSCROLLPANEDIMENSIONS = new Dimension((int) ORDERPANELDIMENSIONS.getWidth() - 38, (int) ORDERPANELDIMENSIONS.getHeight() - 100);
    static Dimension NEWORDERBUTTONDIMENSIONS = new Dimension((int) (ORDERPANELDIMENSIONS.getWidth()/2), 60);

    static Dimension TOTALPANELDIMENSIONS = new Dimension((int) ((FRAMEDIMENSIONS.getWidth() - 400)/2) - 10, (int) FRAMEDIMENSIONS.getHeight() - 280);


    static Dimension NEWENTRYFRAMEDIMENSIONS = new Dimension(700, 200);

    static Dimension DELIVERYORDERSMAINPANEL = new Dimension((int) (ORDERPANELDIMENSIONS.getWidth()/2), (int) (ORDERPANELDIMENSIONS.getHeight()/2));
    static Dimension DELIVERYSCROLLPANEDIMENSIONS = new Dimension((int) (ORDERPANELDIMENSIONS.getWidth()/2) - 100, (int) (ORDERPANELDIMENSIONS.getHeight()/2) - 100);

}
