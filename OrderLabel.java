import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class OrderLabel extends JPanel {
    
    JLabel wayOfPayment;

    public OrderLabel(Order order, JPanel parent){

        //JPanel panel = new JPanel();
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEADING, 30, 5));
        setBorder(BorderFactory.createEmptyBorder());

        

        
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createEmptyBorder());

        JScrollPane scrollPane1 = new JScrollPane(panel1);
        scrollPane1.setBounds(20, 40, 20, 20);
        scrollPane1.setBorder(BorderFactory.createEmptyBorder());
        scrollPane1.setMaximumSize(AppDimentions.SCROLLPANEDIMENSIONS);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        panel2.setBorder(BorderFactory.createEmptyBorder());
        
        JScrollPane scrollPane2 = new JScrollPane(panel2);
        scrollPane2.setBounds(20, 40, 60, 20);
        scrollPane2.setBorder(BorderFactory.createEmptyBorder());
        scrollPane2.setMaximumSize(AppDimentions.SCROLLPANEDIMENSIONS);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.WHITE);
        panel3.setBorder(BorderFactory.createEmptyBorder());

        JScrollPane scrollPane3 = new JScrollPane(panel3);
        scrollPane3.setBounds(20, 40, 40, 20);
        scrollPane3.setBorder(BorderFactory.createEmptyBorder());
        scrollPane3.setMaximumSize(AppDimentions.SCROLLPANEDIMENSIONS);
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.WHITE);
        panel4.setBorder(BorderFactory.createEmptyBorder());

        JScrollPane scrollPane4 = new JScrollPane(panel4);
        scrollPane4.setBounds(20, 40, 40, 20);
        scrollPane4.setBorder(BorderFactory.createEmptyBorder());
        scrollPane4.setMaximumSize(AppDimentions.SCROLLPANEDIMENSIONS);
        scrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        
        JLabel NoOLabel = new JLabel();
        if (order.getNumber() < 10) NoOLabel.setText("000" + order.getNumber());
        else if (order.getNumber() < 100) NoOLabel.setText("00" + order.getNumber());
        else if (order.getNumber() < 1000) NoOLabel.setText("0" + order.getNumber()); 
        NoOLabel.setForeground(Color.BLACK);
        NoOLabel.setBackground(Color.WHITE);
        NoOLabel.setOpaque(true);
        NoOLabel.setBounds(5, 5, 20, 20);
        NoOLabel.setBorder(BorderFactory.createEmptyBorder());
        NoOLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel deliveryName = new JLabel();
        deliveryName.setText(order.getDeliveryName());
        deliveryName.setForeground(Color.BLACK);
        deliveryName.setBackground(Color.WHITE);
        deliveryName.setOpaque(true);
        deliveryName.setBounds(30, 5, 60, 20);

       // deliveryName.setBorder(BorderFactory.createEmptyBorder(4,25, 4, 25));
        deliveryName.setFont(new Font("Arial", Font.PLAIN, 16));


        wayOfPayment = new JLabel();
        if (order.getWayOfPayment().equals("Κάρτα")) wayOfPayment.setText(order.getWayOfPayment() + "   ");
        else wayOfPayment.setText(order.getWayOfPayment());
        wayOfPayment.setForeground(Color.BLACK);
        wayOfPayment.setBackground(Color.WHITE);
        wayOfPayment.setOpaque(true);
        //wayOfPayment.setBorder(BorderFactory.createEmptyBorder(4,25, 4, 25));
        wayOfPayment.setBounds(100, 5, 40, 20);
        wayOfPayment.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel amount = new JLabel();
        amount.setText("" + order.getAmount());
        amount.setForeground(Color.BLACK);
        amount.setBackground(Color.WHITE);
        amount.setOpaque(true);
        if (order.getWayOfPayment().equals("Κάρτα"))amount.setBorder(BorderFactory.createEmptyBorder(0,1, 0, 0));
        amount.setBounds(145, 5, 40, 20);
        amount.setFont(new Font("Arial", Font.PLAIN, 16));


        panel1.add(NoOLabel );
        panel2.add(deliveryName);
        panel3.add(wayOfPayment);
        panel4.add(amount);

        add(scrollPane1);
        add(scrollPane2);
        add(scrollPane3);
        add(scrollPane4);

        parent.add(this);




    }

}
