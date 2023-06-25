import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;


public class DeliveryNames extends JPanel implements ActionListener {
    
    int index;
    JTextField taskField;
    JButton deleteButton, addButton;
    Font font;
    String fontfamily;
    boolean enabledTaskfield;
    ArrayList<Order> deliveryOrders = new ArrayList<Order>();
    static ArrayList<String> checkednames = new ArrayList<String>();
    static int noclicks = 0;
    boolean flag = false;
    static boolean atLeastOneObjectCreated = false;
    //static  ArrayList<String> registerednames = new ArrayList<String>();
    
    
    private JPanel parentPanel;

    public DeliveryNames(JPanel parentPanel){
        
        this.parentPanel = parentPanel;
        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        
        //create Add Button
        addButton = new JButton("REGISTER");
        addButton.setSize((int) AppDimentions.DELETEBUTTONDIMENSIONS.getWidth(), (int) AppDimentions.DELETEBUTTONDIMENSIONS.getHeight());
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setMargin(new Insets(6, 0, 6, 0));
        addButton.setBackground(new Color(21, 189, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusable(false);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);

        //create Del Button
        deleteButton = new JButton("DELETE");
        deleteButton.setSize((int) AppDimentions.DELETEBUTTONDIMENSIONS.getWidth(), (int) AppDimentions.DELETEBUTTONDIMENSIONS.getHeight());
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setMargin(new Insets(6, 0, 6, 0));
        deleteButton.setBackground(new Color(220, 27, 21));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusable(false);
        deleteButton.setBorderPainted(false);
        deleteButton.addActionListener(this);

        //create JtextPane
        taskField = new JTextField();
        taskField.setPreferredSize(AppDimentions.TEXTPANEDIMENSIONS);
        taskField.setMargin(new Insets(0, 0, 0, 0));
        taskField.setBackground(Color.WHITE);
        taskField.setBorder(BorderFactory.createEtchedBorder());
        taskField.setFont(new Font("Arial", Font.PLAIN, 14));


        // add listeners to taskfield
        taskField.addFocusListener(new FocusListener() {
            // indicate which task field is currently being edited
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }
        });

        taskField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if(taskField.getText() != null && taskField.getText().length() >= 15 && !(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
             }
        });
        taskField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                flag = false;
                addButton.setText("REGISTER");
                addButton.setBackground(new Color(21, 189, 180));
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                    return true;
                }
                return false;
            }
        });


        add(taskField);
        add(addButton);
        add(deleteButton);
        

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("DELETE")){
            // delete this component from the parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }

        if(e.getActionCommand().equalsIgnoreCase("REGISTER")){
            flag = true;
            checkednames.add(this.getTaskField().getText());
            addButton.setText(" REMOVE ");
            addButton.setBackground(new Color(230, 148, 2));
        }

        if(e.getActionCommand().equalsIgnoreCase(" REMOVE ")){
            flag = false;
            checkednames.remove(this.getTaskField().getText());
            addButton.setText("REGISTER");
            addButton.setBackground(new Color(21, 189, 180));
        }
    }




    public JTextField getTaskField(){
        return taskField;
    }
/* 
    public static ArrayList<String> getRegisteredNames(){
        return registerednames;
    } 
    
    */

    public static ArrayList<String> getCheckedNames(){
        return checkednames;
    }


    public void DisableTaskField(){
        taskField.setEditable(false);
        taskField.setEnabled(false);
    }


    public void DisableAddButton(){
        addButton.setEnabled(false);
    }


    public void DisableDelButton(){
        deleteButton.setEnabled(false);
    }

    public void EnableTaskField(){
        taskField.setEditable(true);
        taskField.setEnabled(true);
    }


    public void EnableAddButton(){
        addButton.setEnabled(true);
    }


    public void EnableDelButton(){
        deleteButton.setEnabled(true);
    }

    public void SetIndex(){
        index = noclicks;
    }
    
    public void AddOrder(Order order){
        deliveryOrders.add(order);
    }

    public boolean getFlag(){
        return flag;
    }

    public ArrayList<Order> getDeliveryOrderList(){
        return deliveryOrders;
    }

}
