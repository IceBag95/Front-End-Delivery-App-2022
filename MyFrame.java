import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener{
    
    JLabel bannerPanLabel, redPanJLabel;

    JPanel totalPanel, orderPanel, bannerPanel, deliveryPanel, parentPanel, childPanel, orderParentPanel, orderChildPanel, orderChildPanel1, orderChildPanel2;

    JButton addDelivery, registerNames, unlockButton, newOrderButton;

    JScrollPane orderScrollPane;
    
    CustomerDatabase customerDB;
    CatalogDatabase catalogDB;
    DeliveryDatabase deliveryDB;

    static DeliveryScrolpane[] scrolpane;

    static ArrayList<String> names; 

    static ArrayList<String> workingNames; 

    static boolean flag = false;

    static boolean previousEnding = false;

    static boolean isEnabled = true;

    static DeliveryNames[] prevDeliveryNames = null; // !!!!!!!!!!!!!

    static DeliveryNames[] deliveryNames = null; // !!!!!!!!!!!!!!!!!

    public MyFrame(){
        setTitle("Delivery Manager");
        setPreferredSize(AppDimentions.FRAMEDIMENSIONS);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(new Color(222, 220, 220));
        setBackground(Color.RED); //title bar color
        setLayout(null);
        AddComponents();
        CreateDBifNeeded();
        customerDB = new CustomerDatabase();
        catalogDB = new CatalogDatabase();
        deliveryDB = new DeliveryDatabase();
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


        //create panel for totals
        totalPanel = new JPanel();
        totalPanel.setLayout(null);
        totalPanel.setBounds(10, 200, (int) AppDimentions.TOTALPANELDIMENSIONS.getWidth() - 300, (int) AppDimentions.TOTALPANELDIMENSIONS.getHeight());
        totalPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        totalPanel.setBackground(new Color(237, 237, 237));




        /*
            
        ===========================
        *** ORDER PANEL STAFF ***
        ===========================
        
        */



        //create panel for orders
        orderPanel = new JPanel();
        orderPanel.setLayout(null);
        orderPanel.setBounds((int) AppDimentions.FRAMEDIMENSIONS.getWidth() -  ((int) AppDimentions.ORDERPANELDIMENSIONS.getWidth() + 420), 200, (int) AppDimentions.ORDERPANELDIMENSIONS.getWidth(), (int) AppDimentions.ORDERPANELDIMENSIONS.getHeight());
        orderPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        orderPanel.setBackground(new Color(237, 237, 237));


        //create panel for order panel scrollPane
        orderParentPanel = new JPanel();
        orderParentPanel.setBorder(BorderFactory.createEmptyBorder());
        orderParentPanel.setBackground(new Color(240, 240, 240));


        // left panel in scroll pane for even numbered Delivery scrollpanes
        orderChildPanel1 = new JPanel();
        //orderChildPanel1.setAlignmentX(10);
        //orderChildPanel1.setAlignmentY(10);
        orderChildPanel1.setBorder(BorderFactory.createEmptyBorder());
        orderChildPanel1.setBackground(new Color(240, 240, 240));
        orderChildPanel1.setLayout(new BoxLayout(orderChildPanel1, BoxLayout.Y_AXIS));
        orderParentPanel.add(orderChildPanel1);
        
        
        // right panel in scroll pane for odd numbered Delivery scrollpanes
        orderChildPanel2 = new JPanel();
        //orderChildPanel2.setAlignmentX((int) (AppDimentions.ORDERPANELDIMENSIONS.getWidth()/2) - 20);
        //orderChildPanel2.setAlignmentY(-100);
        orderChildPanel2.setBorder(BorderFactory.createEmptyBorder());
        orderChildPanel2.setBackground(new Color(240, 240, 240));
        orderChildPanel2.setLayout(new BoxLayout(orderChildPanel2, BoxLayout.Y_AXIS));
        orderParentPanel.add(orderChildPanel2);


        //add scroll to orderPanel
        orderScrollPane= new JScrollPane(orderParentPanel);
        orderScrollPane.setBounds(
            20,
            20 ,
            (int) AppDimentions.ORDERSCROLLPANEDIMENSIONS.getWidth(),
            (int) AppDimentions.ORDERSCROLLPANEDIMENSIONS.getHeight());
        orderScrollPane.setBorder(BorderFactory.createEtchedBorder());
        orderScrollPane.setMaximumSize(AppDimentions.SCROLLPANEDIMENSIONS);
        orderScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        orderScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        orderScrollPane.setEnabled(false);
        orderPanel.add(orderScrollPane);


        //add new order entry to scrollPane
        newOrderButton = new JButton("ENTER NEW ORDER");
        newOrderButton.setBounds((int) (AppDimentions.ORDERSCROLLPANEDIMENSIONS.getWidth()/2 - AppDimentions.NEWORDERBUTTONDIMENSIONS.getWidth()/2), (int) (AppDimentions.ORDERSCROLLPANEDIMENSIONS.getHeight() + AppDimentions.NEWORDERBUTTONDIMENSIONS.getHeight() - 30),
        (int) AppDimentions.NEWORDERBUTTONDIMENSIONS.getWidth(), (int) AppDimentions.NEWORDERBUTTONDIMENSIONS.getHeight());
        newOrderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newOrderButton.setMargin(new Insets(0, 0, 0, 0));
        newOrderButton.setBackground(new Color(21, 189, 180));
        newOrderButton.setForeground(Color.WHITE);
        newOrderButton.setFocusable(false);
        newOrderButton.setBorderPainted(false);
        newOrderButton.setFont(new Font("Arial", Font.BOLD, 30));
        newOrderButton.setEnabled(false);
        newOrderButton.addActionListener(this);
        orderPanel.add(newOrderButton);

        /*
            
        =============================
        *** DELIVERY PANEL STAFF ***
        =============================
        
        */

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
        addDelivery.setBounds((int) AppDimentions.REDPANELDIMENSIONS.getWidth() - 162, 
                                (int) (AppDimentions.REDPANELDIMENSIONS.getHeight()) - 200, 
                                (int) AppDimentions.ADDBUTTONDIMENSIONS.getWidth(), 
                                (int) AppDimentions.ADDBUTTONDIMENSIONS.getHeight());
        //addDelivery.setPreferredSize(AppDimentions.ADDBUTTONDIMENSIONS);
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
        registerNames.setBounds((int) AppDimentions.REDPANELDIMENSIONS.getWidth() - 356, 
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
        unlockButton.setBounds((int) AppDimentions.REDPANELDIMENSIONS.getWidth() - 276, 
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
            44,
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
        redPanJLabel.setBounds( 44, (int) AppDimentions.REDPANELDIMENSIONS.getHeight()/4 - 57, scrollPane.getWidth(), 40);
        redPanJLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        redPanJLabel.setBackground(new Color(56, 195, 255));
        redPanJLabel.setForeground(Color.white);
        redPanJLabel.setBorder(BorderFactory.createEtchedBorder(new Color(50, 166, 217), new Color(75, 197, 250)));
        redPanJLabel.setOpaque(true);
        redPanJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deliveryPanel.add(redPanJLabel);               
        

        
        //add components to contentPane
        this.getContentPane().add(totalPanel);
        this.getContentPane().add(orderPanel);
        this.getContentPane().add(deliveryPanel);
        this.getContentPane().add(bannerPanel);

        
        
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
            if (workingNames.size() > 0){
                //DisableAddDeliveryButtonAndRegisrtyButton();
                deliveryNames = new DeliveryNames[names.size()];
                //int k = 0;
                if (deliveryNames != null) {
                    prevDeliveryNames = new DeliveryNames[deliveryNames.length];
                    for (int i = 0; i < deliveryNames.length; i++){
                        prevDeliveryNames[i] = deliveryNames[i];
                    }
                }
                for (int i =0; i<childPanel.getComponentCount(); i++){
                    DeliveryNames currentDelivery = (DeliveryNames) childPanel.getComponent(i);
                    if (currentDelivery.getFlag()) deliveryNames[i] = currentDelivery;
                    else deliveryNames[i] = null;
                }

                System.out.println("\n\nDelivery table's names:");
                for (int i =0; i<deliveryNames.length; i++){
                    if(deliveryNames[i] != null) System.out.println((i+1) +" "+ deliveryNames[i].getTaskField().getText().trim());
                    else System.out.println((i+1) + "");
                }

                addDelivery.setVisible(false);
                registerNames.setVisible(false);
                unlockButton.setVisible(true);

                orderParentPanel.remove(orderChildPanel1);               
                orderParentPanel.remove(orderChildPanel2);

                orderChildPanel1 = new JPanel();
                //orderChildPanel1.setAlignmentX(10);
                //orderChildPanel1.setAlignmentY(10);
                orderChildPanel1.setBorder(BorderFactory.createEmptyBorder());
                orderChildPanel1.setBackground(new Color(240, 240, 240));
                orderChildPanel1.setLayout(new BoxLayout(orderChildPanel1, BoxLayout.Y_AXIS));
                
                
                
                // right panel in scroll pane for odd numbered Delivery scrollpanes
                orderChildPanel2 = new JPanel();
                //orderChildPanel2.setAlignmentX((int) (AppDimentions.ORDERPANELDIMENSIONS.getWidth()/2) - 20);
                //orderChildPanel2.setAlignmentY(-100);
                orderChildPanel2.setBorder(BorderFactory.createEmptyBorder());
                orderChildPanel2.setBackground(new Color(240, 240, 240));
                orderChildPanel2.setLayout(new BoxLayout(orderChildPanel2, BoxLayout.Y_AXIS));
                
                
                orderParentPanel.add(orderChildPanel1);
                orderParentPanel.add(orderChildPanel2);


                scrolpane = new DeliveryScrolpane[names.size()];
                
                int k = 0;
                for (int i = 0; i <names.size(); i++){
                    scrolpane[i] = new DeliveryScrolpane();
                    scrolpane[i].getNameLabel().setText(names.get(i).trim());
                    if (workingNames.contains(scrolpane[i].getNameLabel().getText())){
                        scrolpane[i].GetMainPanel().setVisible(true);
                        if (k%2 ==0) orderChildPanel1.add(scrolpane[i].GetMainPanel());
                        else orderChildPanel2.add(scrolpane[i].GetMainPanel());
                        k++;
                    }
                }

                if (workingNames.size() % 2 == 1){
                    JPanel fake = new JPanel();
                    fake.setPreferredSize(AppDimentions.DELIVERYORDERSMAINPANEL);
                    fake.setLayout(null);
                    fake.setBackground(new Color(240, 240, 240));
                    orderChildPanel2.add(fake);
                }
                
                newOrderButton.setEnabled(true);

                revalidate();
                repaint();

                System.out.println("\nWorking names:");
                for (int i = 0; i < workingNames.size(); i++){
                    System.out.println(workingNames.get(i));
                    //System.out.println(deliveryNames[i].getTaskField().getText());
                    
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
            else{
                JOptionPane.showMessageDialog(null, "At least one Delivery should be registered.", "No Delivery Registered", JOptionPane.WARNING_MESSAGE);
                for (int i = 0; i< childPanel.getComponentCount(); i++){
                    DeliveryNames currentDelivery = (DeliveryNames) childPanel.getComponent(i);
                    currentDelivery.EnableTaskField();
                    currentDelivery.EnableAddButton();
                    currentDelivery.EnableDelButton();
                }
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
            orderScrollPane.setEnabled(false);
            newOrderButton.setEnabled(false);
            revalidate();
            repaint();
        }

        if(command.equalsIgnoreCase("ENTER NEW ORDER")){
            /*NewEntry newentry = new NewEntry(this);
            newentry.setVisible(true); */
            new NewEntry(this).setVisible(true);
            this.setEnabled(false);
            //isEnabled = this.isEnabled();

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

    public JPanel GetOrderChildPanel1(){
        return orderChildPanel1;
    }

    public JPanel GetOrderChildPanel2(){
        return orderChildPanel2;
    }

    public static DeliveryScrolpane[] getDeliveryScrolpane(){
        return scrolpane;
    }

    public static DeliveryScrolpane getDeliveryScrolpane(int i){
        return scrolpane[i];
    }



    public  static ArrayList<String> getNames(){
        return names;
    }

    public  static ArrayList<String> getWorkingNames(){
        return workingNames;
    }

    public  static DeliveryNames getDeliveryNames(int i){
        return deliveryNames[i];
    }

    public static boolean GetPreviousEnding(){
        return previousEnding;
    }

    public void CreateDBifNeeded(){
        String dbUrl = AbstractDatabase.DB_URL;
        String dbUser = AbstractDatabase.DB_USER;
        String dbPassword = AbstractDatabase.DB_PASSWORD;

        try {
            // Σύνδεση με τον MySQL server
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement statement = connection.createStatement();

            // Εκτέλεση της εντολής για να λάβουμε όλες τις βάσεις δεδομένων
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES");

            boolean databaseExists = false;
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);   // επιστρέφει σε 1 στήλη όλα τα αποτελέσματα ονομάτων βάσεων δεδομένων που
								// είναι αποθηκευμένα στον local host

                // Έλεγχος αν το όνομα "MYDB" υπάρχει
                if (databaseName.equals("MYDB")) {
                    databaseExists = true;
                    break;
                }
            }

            if (databaseExists) {
                System.out.println("Η βάση δεδομένων υπάρχει.");
            } else {
                System.out.println("Η βάση δεδομένων δεν υπάρχει.");

                // Κώδικας για τη δημιουργία της βάσης δεδομένων
                statement.executeUpdate("CREATE DATABASE MYDB;");
                System.out.println("Η βάση δεδομένων δημιουργήθηκε.");
            }

            // Κλείσιμο των ανοικτών συνδέσεων
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
