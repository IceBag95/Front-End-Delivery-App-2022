import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class DeliveryScrolpane implements ActionListener{

    JScrollPane scrollPane;
    JPanel mainpanel, parentPanel, childPanel;
    JLabel deliveryName;
    JButton showContaintButton;
    static int count = -1;

    public DeliveryScrolpane(){

        
        deliveryName = new JLabel("DELIVERY");
        deliveryName.setFont(new Font("Arial", Font.BOLD, 20));
        deliveryName.setForeground(Color.WHITE);
        deliveryName.setBounds(20, 9, (int) AppDimentions.DELIVERYSCROLLPANEDIMENSIONS.getWidth() - 1, 30);
        deliveryName.setHorizontalAlignment(SwingConstants.CENTER);
        deliveryName.setBorder(BorderFactory.createEtchedBorder(new Color(50, 166, 217), new Color(75, 197, 250)));
        deliveryName.setBackground(new Color(56, 195, 255));
        deliveryName.setOpaque(true);
        
        
        parentPanel = new JPanel();
        parentPanel.setBorder(BorderFactory.createEmptyBorder());
        parentPanel.setBackground(Color.WHITE);
        
        
        childPanel = new JPanel();
        childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
        childPanel.setBorder(BorderFactory.createEmptyBorder());
        childPanel.setBackground(Color.WHITE);
        parentPanel.add(childPanel);
        
        
        scrollPane = new JScrollPane(parentPanel);
        scrollPane.setBounds(20, 40, (int) AppDimentions.DELIVERYSCROLLPANEDIMENSIONS.getWidth(), (int) AppDimentions.DELIVERYSCROLLPANEDIMENSIONS.getHeight());
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        scrollPane.setMaximumSize(AppDimentions.SCROLLPANEDIMENSIONS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        showContaintButton = new JButton("SHOW ANALYTICS");
        showContaintButton.setBounds(180, 
        (int) AppDimentions.DELIVERYSCROLLPANEDIMENSIONS.getHeight() + 50, 
        140, 30);
        showContaintButton.setPreferredSize(AppDimentions.ADDBUTTONDIMENSIONS);
        showContaintButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        showContaintButton.setMargin(new Insets(0, 0, 0, 0));
        showContaintButton.setBackground(new Color(114, 213, 75));
        showContaintButton.setForeground(Color.WHITE);
        showContaintButton.setFocusable(false);
        showContaintButton.setBorderPainted(false);
        showContaintButton.addActionListener(this);
        
        
        mainpanel = new JPanel();
        mainpanel.setLayout(null);
        mainpanel.setBackground(new Color(240, 240, 240));
        mainpanel.setPreferredSize(AppDimentions.DELIVERYORDERSMAINPANEL);
        mainpanel.setVisible(false);
        

        mainpanel.add(deliveryName);
        mainpanel.add(scrollPane);
        mainpanel.add(showContaintButton);

    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    

    public String getDeliveryName(){
        return deliveryName.getText().trim();
    }

    public static int GetCount(){
        return count;
    }

    public static void SetCountTo(int k){
        count = k;
    }

    public JLabel getNameLabel(){
        return deliveryName;
    }

    public JPanel GetMainPanel(){
        return mainpanel;
    }

    public JPanel GetChildnPanel(){
        return childPanel;
    }
}
