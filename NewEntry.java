import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class NewEntry extends JFrame implements ActionListener{
    
    JPanel entryPanel;
    JButton okButton, cancelButton;
    
    public NewEntry(){
        setSize(AppDimentions.NEWENTRYFRAMEDIMENSIONS);
        setLayout(null);
        setResizable(false);
        setTitle("New Entry");
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(237, 237, 237));
        AddComponents();
    }

    public void AddComponents(){
        entryPanel = new JPanel();
        entryPanel.setLayout(null);
        //entryPanel.setBackground(new Color(237, 237, 237));
        entryPanel.setBackground(Color.WHITE);
        entryPanel.setBounds(10, 10,960, 100);

        okButton = new JButton("OK");
        //okButton.setBounds((int) (entryPanel.getWidth()/2) - 40, (int) entryPanel.getHeight() + 20, 30, 20);
        okButton.setBounds(350,120,100,30);
        okButton.setFocusable(false);
        okButton.setBorder(BorderFactory.createEtchedBorder());
        okButton.setBackground(new Color(114, 213, 75));
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.setBorderPainted(false);


        cancelButton = new JButton("Cancel");
        //cancelButton.setBounds((int) (entryPanel.getWidth()/2) + 10, (int) entryPanel.getHeight() + 20, 30, 20);
        cancelButton.setBounds(550,120,100,30);
        cancelButton.setFocusable(false);
        cancelButton.setBorder(BorderFactory.createEtchedBorder());
        cancelButton.setBackground(new Color(220, 27, 21));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBorderPainted(false);

        this. getContentPane().add(entryPanel);
        this.getContentPane().add(okButton);
        this.getContentPane().add(cancelButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
