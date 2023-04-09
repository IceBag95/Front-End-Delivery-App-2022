import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener{
    
    JLabel bannerPanLabel, redPanJLabel;

    JPanel bannerPanel, deliveryPanel, parentPanel, childPanel;

    JButton addDelivery, registerNames, unlockButton;

    static ArrayList<String> names; 

    static ArrayList<String> workingNames; 

    static boolean flag = false;


    public MyFrame(){
        setPreferredSize(AppDimentions.FRAMEDIMENSIONS);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        setBackground(Color.RED); //title bar color
        setLayout(null);
        AddComponents();
        retrieveData();

    }

    public void AddComponents(){

        //create top panel for Banner
        bannerPanel = new JPanel();
        bannerPanel.setLayout(null);
        bannerPanel.setBorder(BorderFactory.createEtchedBorder());
        bannerPanel.setBounds(0, 0, (int) AppDimentions.BANNERPANELDIMENSIONS.getWidth(), (int) AppDimentions.BANNERPANELDIMENSIONS.getHeight());
        bannerPanel.setBackground(new Color(220, 27, 21));

        //create Label for bannerPanel and add it to bannerPanel
        bannerPanLabel = new JLabel("DELIVERY MANAGER");
        bannerPanLabel.setBounds(20, 20, 1200, 210);
        bannerPanLabel.setForeground(Color.WHITE);
        Font bannerPanLabelFont = new Font("Arial", Font.BOLD + Font.ITALIC, 100);
        bannerPanLabel.setFont(bannerPanLabelFont);
        bannerPanel.add(bannerPanLabel);

        //create panel foρ Delivery table
        deliveryPanel = new JPanel();
        deliveryPanel.setLayout(null);
        deliveryPanel.setBounds((int) AppDimentions.FRAMEDIMENSIONS.getWidth() - 410, 200, (int) AppDimentions.REDPANELDIMENSIONS.getWidth(), (int) AppDimentions.REDPANELDIMENSIONS.getHeight());
        //deliveryPanel.setSize((int) AppDimentions.REDPANELDIMENSIONS.getWidth(), (int) AppDimentions.REDPANELDIMENSIONS.getHeight());
        deliveryPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        deliveryPanel.setBackground(new Color(237, 237, 237));
        //deliveryPanel.setBackground(new Color(252, 184, 184));
        
        
                            
                            
        parentPanel = new JPanel();
        parentPanel.setBorder(BorderFactory.createEmptyBorder());
        parentPanel.setBackground(Color.WHITE);
                            
                            
                            
        childPanel = new JPanel();
        childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
        childPanel.setBorder(BorderFactory.createEmptyBorder());
        childPanel.setBackground(Color.WHITE);
        parentPanel.add(childPanel);

        
        
        //show Delivery Table
        addDelivery = new JButton("ADD DELIVERY");
        addDelivery.setBounds((int) AppDimentions.REDPANELDIMENSIONS.getWidth() - 168, 
                                (int) (AppDimentions.REDPANELDIMENSIONS.getHeight()) - 200, 
                                (int) AppDimentions.ADDBUTTONDIMENSIONS.getWidth(), 
                                (int) AppDimentions.ADDBUTTONDIMENSIONS.getHeight());
        addDelivery.setPreferredSize(AppDimentions.ADDBUTTONDIMENSIONS);
        addDelivery.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addDelivery.setMargin(new Insets(0, 0, 0, 0));
        addDelivery.setBackground(new Color(21, 189, 180));
        addDelivery.setForeground(Color.WHITE);
        addDelivery.setFocusable(false);
        addDelivery.setBorderPainted(false);
        addDelivery.addActionListener(this);
        deliveryPanel.add(addDelivery);
        

        //show Delivery Table
        registerNames = new JButton("REGISTER NAMES");
        registerNames.setBounds((int) AppDimentions.REDPANELDIMENSIONS.getWidth() - 362, 
                                (int) (AppDimentions.REDPANELDIMENSIONS.getHeight()) - 200, 
                                (int) AppDimentions.REGISTRYBUTTONDIMENSIONS.getWidth(), 
                                (int) AppDimentions.REGISTRYBUTTONDIMENSIONS.getHeight());
        registerNames.setPreferredSize(AppDimentions.ADDBUTTONDIMENSIONS);
        registerNames.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerNames.setMargin(new Insets(0, 0, 0, 0));
        registerNames.setBackground(new Color(114, 213, 75));
        registerNames.setForeground(Color.WHITE);
        registerNames.setFocusable(false);
        registerNames.setBorderPainted(false);
        registerNames.addActionListener(this);
        deliveryPanel.add(registerNames);
        
        
        //create unlock Button
        unlockButton = new JButton("UNLOCK");
        unlockButton.setBounds((int) AppDimentions.REDPANELDIMENSIONS.getWidth() - 282, 
                                (int) (AppDimentions.REDPANELDIMENSIONS.getHeight()) - 200, 
                                (int) AppDimentions.REGISTRYBUTTONDIMENSIONS.getWidth(), 
                                (int) AppDimentions.REGISTRYBUTTONDIMENSIONS.getHeight());
        unlockButton.setPreferredSize(AppDimentions.ADDBUTTONDIMENSIONS);
        unlockButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        unlockButton.setMargin(new Insets(0, 0, 0, 0));
        unlockButton.setBackground(new Color(230, 148, 2));
        unlockButton.setForeground(Color.WHITE);
        unlockButton.setFocusable(false);
        unlockButton.setBorderPainted(false);
        unlockButton.setVisible(false);
        unlockButton.addActionListener(this);
        deliveryPanel.add(unlockButton);
        
        
        
        //add scroll to mainPanel
        JScrollPane scrollPane = new JScrollPane(parentPanel);
        scrollPane.setBounds(
            38,
            (int) AppDimentions.REDPANELDIMENSIONS.getHeight()/4 ,
            deliveryPanel.getWidth() - 87,
            (int) AppDimentions.REDPANELDIMENSIONS.getHeight()/2 - 20);
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        scrollPane.setMaximumSize(AppDimentions.SCROLLPANEDIMENSIONS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        deliveryPanel.add(scrollPane);
        


        //create Red Panel Label
        redPanJLabel = new JLabel("DELIVERY NAMES");
        redPanJLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
        redPanJLabel.setBounds( 38, (int) AppDimentions.REDPANELDIMENSIONS.getHeight()/4 - 57, scrollPane.getWidth(), 40);
        redPanJLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        redPanJLabel.setBackground(new Color(56, 195, 255));
        redPanJLabel.setForeground(Color.white);
        redPanJLabel.setBorder(BorderFactory.createEtchedBorder(new Color(50, 166, 217), new Color(75, 197, 250)));
        redPanJLabel.setOpaque(true);
        redPanJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deliveryPanel.add(redPanJLabel);               
        

        this.getContentPane().setBackground(new Color(222, 220, 220));
        //add components to contentPane
        this.getContentPane().add(deliveryPanel);
        this.getContentPane().add(bannerPanel);

        
        
    }

    public void DisableAddDeliveryButtonAndRegisrtyButton(){
        addDelivery.setEnabled(false);
        registerNames.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("ADD DELIVERY")){
            
            // create a name
            DeliveryNames name = new DeliveryNames(childPanel);
            name.setBorder(BorderFactory.createEtchedBorder());
            childPanel.add(name);
        
        
            // make the previous task appear disabled
            if(childPanel.getComponentCount() > 1){
                DeliveryNames previousTask = (DeliveryNames) childPanel.getComponent(
                        childPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(Color.WHITE);
            }

            // make the task field request focus after creation
            name.getTaskField().requestFocus();
            revalidate();
            repaint();
        }
        
        if(command.equalsIgnoreCase("REGISTER NAMES")){
            names = new ArrayList<String>();
            workingNames = new ArrayList<String>();
            for (int i = 0; i< childPanel.getComponentCount(); i++){
                DeliveryNames currentDelivery = (DeliveryNames) childPanel.getComponent(i);
                String text = currentDelivery.getTaskField().getText();
                names.add(text);
                if (currentDelivery.getFlag()) workingNames.add(text);
                //currentDelivery.SetIndex();
                currentDelivery.DisableTaskField();
                currentDelivery.getTaskField().requestFocusInWindow();
                currentDelivery.DisableAddButton();
                currentDelivery.DisableDelButton();
            }
            //DisableAddDeliveryButtonAndRegisrtyButton();
            addDelivery.setVisible(false);
            registerNames.setVisible(false);
            unlockButton.setVisible(true);

            revalidate();
            repaint();

            System.out.println("\nWorking names:");
            for (int i = 0; i < workingNames.size(); i++){
                System.out.println(workingNames.get(i));
            }
            System.out.println("\nRegistered names:");
            for (int i = 0; i < childPanel.getComponentCount(); i++){
                System.out.println(names.get(i));
            }
            try {
                FileOutputStream fileOut1 = new FileOutputStream("SavedNames.ser");
                ObjectOutputStream out1 = new ObjectOutputStream(fileOut1);
                out1.writeObject(names);
                out1.close();
                fileOut1.close();
            } catch (IOException E) {
                E.printStackTrace();
            }
        }

        if(command.equalsIgnoreCase("UNLOCK")){
            for (int i = 0; i< childPanel.getComponentCount(); i++){
                DeliveryNames currentDelivery = (DeliveryNames) childPanel.getComponent(i);
                currentDelivery.EnableTaskField();
                currentDelivery.EnableAddButton();
                currentDelivery.EnableDelButton();
            }
            addDelivery.setVisible(true);
            registerNames.setVisible(true);
            unlockButton.setVisible(false);
            revalidate();
            repaint();
        }
    }


    public void retrieveData(){
        File file1 = new File("SavedNames.ser");
        try {
            FileInputStream fileIn1 = new FileInputStream(file1);
            ObjectInputStream in1 = new ObjectInputStream(fileIn1);
            names = (ArrayList<String>) in1.readObject();
            in1.close();
            fileIn1.close();
            for (int i = 0; i < names.size(); i++){
                // create a task component
                DeliveryNames delname = new DeliveryNames(childPanel);
                delname.setBorder(BorderFactory.createEtchedBorder());
                delname.getTaskField().setText(names.get(i));
                //delname.getTaskField().requestFocusInWindow();
                childPanel.add(delname);

            }
            names = new ArrayList<String>();
            requestFocusInWindow();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  static ArrayList<String> getNames(){
        return names;
    }
}