import java.awt.Toolkit;
import java.awt.Dimension;

public class AppDimentions {
    

    //frame dimentions
    static Dimension FRAMEDIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    static Dimension REDPANELDIMENSIONS = new Dimension(400, (int) FRAMEDIMENSIONS.getHeight());
    static Dimension SCROLLPANEDIMENSIONS = new Dimension((int) REDPANELDIMENSIONS.getWidth() - 100, (int) REDPANELDIMENSIONS.getHeight()/2 - 50);
    static Dimension DELETEBUTTONDIMENSIONS = new Dimension(30, 30);
    static Dimension ADDBUTTONDIMENSIONS = new Dimension((int) (SCROLLPANEDIMENSIONS.getWidth()*0.4), 30);
    static Dimension REGISTRYBUTTONDIMENSIONS = new Dimension((int) (SCROLLPANEDIMENSIONS.getWidth()*0.55), 30);
    static Dimension TEXTPANEDIMENSIONS = new Dimension(190, 30);
}
