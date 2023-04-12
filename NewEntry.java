import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class NewEntry extends JFrame implements ActionListener{
    
    JPanel entryPanel;
    JButton okButton, cancelButton;
    MyFrame mainFrame;
    String[] names;
    JComboBox<String> namesBox, paymentBox;
    String selectedName;
    static Integer AA = 1;
    JTextField amount;
    Float currentamount;

    public NewEntry(MyFrame mf){

        setSize(AppDimentions.NEWENTRYFRAMEDIMENSIONS);
        setLayout(null);
        setResizable(false);
        setTitle("New Entry");
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(237, 237, 237));
        
        mainFrame  = mf;
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setEnabled(true);
            }
        });
        int N = MyFrame.getWorkingNames().size();
        System.out.println("\n\nN = " + N);
        names = new String[N];
        System.out.println("\n\nTrying to pass names in array...");
        for (int i = 0; i<N; i++){
            names[i] = MyFrame.getWorkingNames().get(i).toString().trim();
            System.out.println("Name " + names[i] + "is added to the array.");
        }


        AddComponents();
    }

    public void AddComponents(){

        //Create entry panel for menus etc
        entryPanel = new JPanel();
        //entryPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 40));
        entryPanel.setLayout(null);
        entryPanel.setBackground(Color.WHITE);
        entryPanel.setBounds(10, 10,660, 100);


        /*
        ================================     
        *** STAFF INSIDE ENTRY PANEL ***
        ================================
        */


        JLabel numberOfOrderLabel = new JLabel("A/A:");
        numberOfOrderLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        numberOfOrderLabel.setBounds(20, 15, 30, 30);

        JLabel numberOfOrder = new JLabel();
        numberOfOrder.setFont(new Font("Consolas", Font.BOLD, 16));
        numberOfOrder.setBounds(20, 40, 30, 30);
        numberOfOrder.setText(AA.toString());
        numberOfOrder.setBorder(BorderFactory.createEtchedBorder());
        numberOfOrder.setBackground(Color.WHITE);
        numberOfOrder.setOpaque(true);
        numberOfOrder.setVerticalAlignment(SwingConstants.BOTTOM);


        JLabel deliveryNameLabel = new JLabel("Delivery Name:");
        deliveryNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        deliveryNameLabel.setBounds(70, 15, 200, 30);

        JLabel wayOfPaymentLabel = new JLabel("Way of Payment:");
        wayOfPaymentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        wayOfPaymentLabel.setBounds(290, 15, 200, 30);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setBounds(510, 15, 130, 30);

        /* 
        JLabel tipsLabel = new JLabel("Tips");
        tipsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        tipsLabel.setBounds(20, 15, 30, 30);
        */

        amount = new JTextField();
        amount.setBounds(510, 40, 130, 30);
        amount.setText("#############");
        amount.setEditable(false);
        amount.setEnabled(false);


        namesBox = new JComboBox<String>(names);
        namesBox.insertItemAt("", 0);
        namesBox.setSelectedIndex(0);
        namesBox.setBounds(70, 40, 200, 30);
        namesBox.addActionListener(this);

        String[] WOP = {"","Κάρτα","Μετρητά"};
        paymentBox = new JComboBox<String>(WOP);
        paymentBox.setSelectedIndex(0);
        paymentBox.setBounds(290, 40, 200, 30);
        paymentBox.addActionListener(this);


        //add elements to entryPanel
        entryPanel.add(numberOfOrderLabel);
        entryPanel.add(numberOfOrder);
        entryPanel.add(deliveryNameLabel); 
        entryPanel.add(wayOfPaymentLabel);
        entryPanel.add(amountLabel);
        entryPanel.add(namesBox);
        entryPanel.add(paymentBox);
        entryPanel.add(amount);
        




        /*
        ====================================    
        *** BUTTONS FOR NEW ENTRY FRAME ***
        ====================================
        */


        okButton = new JButton("OK");
        okButton.setBounds(200,120,100,30);
        okButton.setFocusable(false);
        okButton.setBorder(BorderFactory.createEtchedBorder());
        okButton.setBackground(new Color(114, 213, 75));
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.setBorderPainted(false);
        okButton.addActionListener(this);


        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(380,120,100,30);
        cancelButton.setFocusable(false);
        cancelButton.setBorder(BorderFactory.createEtchedBorder());
        cancelButton.setBackground(new Color(220, 27, 21));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBorderPainted(false);
        cancelButton.addActionListener(this);



        this.getContentPane().add(entryPanel);
        this.getContentPane().add(okButton);
        this.getContentPane().add(cancelButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        /*if(command.equalsIgnoreCase("OK")){
            if (amount.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid Price.", "", JOptionPane.WARNING_MESSAGE);
            } 
            else {
                String pattern = "^[0-9,.]*$";
                if (amount.getText().matches(pattern)) {
                    Float currentamount = Float.parseFloat(amount.getText().trim());
                    if(currentamount > 0){
                        //staff must be added
                        AA++;
                        mainFrame.setEnabled(true);
                        this.dispose();
                    }
                    else JOptionPane.showMessageDialog(null, "Invalid Price.", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Price.", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        }*/

        if(command.equalsIgnoreCase("OK")){
            if (amount.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid Price.", "", JOptionPane.WARNING_MESSAGE);
            } 
            else {
                currentamount = 0f;
                if(paymentBox.getSelectedItem().toString().equals("Μετρητά")){
                    try{
                        currentamount = Float.parseFloat(amount.getText().trim());
                    }
                    catch (NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "Invalid Price.", "", JOptionPane.WARNING_MESSAGE);
                    }
                    if(currentamount > 0 ){
                        AA++;
                        mainFrame.setEnabled(true);
                        this.dispose();
                    }
                }
                else if(paymentBox.getSelectedItem().toString().equals("Κάρτα")){
                    AA++;
                    mainFrame.setEnabled(true);
                    this.dispose();
                }
                else JOptionPane.showMessageDialog(null, "No Delivery Name Selected.", "", JOptionPane.WARNING_MESSAGE);
                System.out.println("Current number: " + currentamount);
            }
        }
        if(command.equalsIgnoreCase("Cancel")){
            
            //staff must be added
            mainFrame.setEnabled(true);
            this.dispose();
            
        }

        if(e.getSource() == namesBox){
            System.out.println("\n\nTrying to get name selected...");
            selectedName = namesBox.getSelectedItem().toString().trim();
            System.out.println("\n\nName selected: " + selectedName);
        }

        if(e.getSource() == paymentBox) {
            if(paymentBox.getSelectedItem().toString().equals("Μετρητά")) {amount.setEditable(true); amount.setText(""); amount.setEnabled(true);}
            else {amount.setEditable(false); amount.setText("#############"); amount.setEnabled(false);}
            System.out.println("Way of payment selected: " + paymentBox.getSelectedItem().toString());
        }
    }

    public boolean containsOnlyNumbers(String str) {
        String pattern = "^[0-9,.]*$";
        return str.matches(pattern);
    }
}
