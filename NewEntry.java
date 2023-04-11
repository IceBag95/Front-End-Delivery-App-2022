import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
//import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class NewEntry extends JFrame implements ActionListener{
    
    JPanel entryPanel;
    JButton okButton, cancelButton;
    MyFrame mainFrame;
    String[] names;
    JComboBox<String> namesBox;
    String selectedName;
    static Integer AA = 1;

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
        entryPanel.setBounds(10, 10,960, 100);


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
        //numberOfOrder.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfOrder.setVerticalAlignment(SwingConstants.BOTTOM);


        JLabel deliveryNameLabel = new JLabel("Delivery Name:");
        deliveryNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        deliveryNameLabel.setBounds(70, 15, 200, 30);

        JLabel wayOfPaymentLabel = new JLabel("Way of Payment:");
        wayOfPaymentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        wayOfPaymentLabel.setBounds(20, 15, 30, 30);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setBounds(20, 15, 30, 30);

        JLabel tipsLabel = new JLabel("Tips");
        tipsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        tipsLabel.setBounds(20, 15, 30, 30);


        namesBox = new JComboBox<String>(names);
        namesBox.insertItemAt("", 0);
        namesBox.setSelectedIndex(0);
        namesBox.setBounds(70, 40, 200, 30);;
        namesBox.addActionListener(this);



        //add elements to entryPanel
        entryPanel.add(namesBox);
        entryPanel.add(numberOfOrderLabel);
        entryPanel.add(numberOfOrder);
        entryPanel.add(deliveryNameLabel);





        /*
        ====================================    
        *** BUTTONS FOR NEW ENTRY FRAME ***
        ====================================
        */


        okButton = new JButton("OK");
        okButton.setBounds(350,120,100,30);
        okButton.setFocusable(false);
        okButton.setBorder(BorderFactory.createEtchedBorder());
        okButton.setBackground(new Color(114, 213, 75));
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.setBorderPainted(false);
        okButton.addActionListener(this);


        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(550,120,100,30);
        cancelButton.setFocusable(false);
        cancelButton.setBorder(BorderFactory.createEtchedBorder());
        cancelButton.setBackground(new Color(220, 27, 21));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBorderPainted(false);
        cancelButton.addActionListener(this);

        this. getContentPane().add(entryPanel);
        this.getContentPane().add(okButton);
        this.getContentPane().add(cancelButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("OK")){
            
            //staff must be added
            AA++;
            mainFrame.setEnabled(true);
            this.dispose();

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
    }
}
