

/*
 * 
 * Code in project is borrowed from the orcale java teaching website
 * url for the concepts: 
 * http://docs.oracle.com/javase/tutorial/uiswing/components/button.html
 * 
 * url for code template:
 * http://docs.oracle.com/javase/tutorial/uiswing/examples/components/RadioButtonDemoProject/src/components/RadioButtonDemo.java
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *Creates the selector panel. 
 * 
 */
public class BasePanel extends JPanel
                             implements ActionListener {
                             
    //saves the last button used so that it can be used later in the printing of the avatar. buttonrecorder saves the file tag of the response chosen
    public String buttonrecorder;
    //there's only one response being displayed at a time, so there is only one JLabel, this is the preview
    JLabel response;
    //static question banner
    JLabel question;
    
    public boolean doneYet;
    //JLabel instructions; //instructions if things get weird with stringing all the panels together
    public String[] list;
    static JFrame frame;
 
    /*
    *The constructor creates the panel itself
    *
    *no return
    */
    public BasePanel(String[] jpg) {
        super(new BorderLayout());
        doneYet = false;
        //if no button is pressed yet, string is set to jpg[0](original pic/choice)
        buttonrecorder = null;
        list = jpg;
        int last = list.length;
       //now I create the radio buttons
        JRadioButton ans1 = new JRadioButton(list[0]);
        ans1.setMnemonic(KeyEvent.VK_A);
        ans1.setActionCommand(list[0]);
        ans1.setSelected(true);
 
        JRadioButton ans2 = new JRadioButton(list[1]);
        ans2.setMnemonic(KeyEvent.VK_B);
        ans2.setActionCommand(list[1]);
        
        JRadioButton ans3 = new JRadioButton(list[2]);
        ans3.setMnemonic(KeyEvent.VK_C);
        ans3.setActionCommand(list[2]);
        
        JRadioButton ans4 = new JRadioButton(list[3]);
        ans3.setMnemonic(KeyEvent.VK_D);
        ans4.setActionCommand(list[3]);
        ans4.setSelected(true);
 
        JRadioButton ans5 = new JRadioButton(list[4]);
        ans5.setMnemonic(KeyEvent.VK_E);
        ans5.setActionCommand(list[4]);
        
        //creating the "okay" button
        JButton  okay  = new JButton("Okay");
        
        
        //putting all the buttons in one group to display
        ButtonGroup group = new ButtonGroup();
        group.add(ans1);
        group.add(ans2);
        group.add(ans3);
        group.add(ans4);
        group.add(ans5);
        group.add(okay);
       
        //action listeners for the radio buttons
        ans1.addActionListener(this);
        ans2.addActionListener(this);
        ans3.addActionListener(this);
        ans4.addActionListener(this);
        ans5.addActionListener(this);
        
        //the default image is the list[0] template
        question = new JLabel(createImageIcon(list[last-1] + ".jpg"));
        question.setPreferredSize(new Dimension(400, 100));
        response = new JLabel(createImageIcon(list[0] + ".jpg"));
        response.setPreferredSize(new Dimension(400, 300));

        //Puts all the buttons on the panel
        JPanel panel = new JPanel(new GridLayout(6, 4));
        panel.add(ans1);
        panel.add(ans2);
        panel.add(ans3);
        panel.add(ans4);
        panel.add(ans5);
        panel.add(okay);
        frame = new JFrame("BasePanel");
        JPanel picpanel = new JPanel(new GridLayout(7, 5));
        //when the okay button is pressed, doneYet is set as true, locking in the choice, even if buttons are pressed or if ok button is pressed.Frane becomes invisible.
       okay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent t) {
                    doneYet = true;
                    frame.setVisible(false);
            }
        });
        //adds everything to the panel
        add(panel, BorderLayout.WEST);
        add(question,BorderLayout.NORTH);
        add(response, BorderLayout.EAST);
    }
    /*
     * method actionPerformed() sets the response to the corresponding button using the strings stated as fields
     * shows preview image as the icon
     * also sets the button recorder to an integer so that it can be retrieved and used later
     * no return
     * if the button selected is the "list[0]" button, sets button recorder to "list[0]"
     * else if the button selected is the "list[1]" button, sets button recorder to "medium skin"
     * else if the button selected is the "list[2]" button, sets button recorder to "dark skin"
     */ 
    public void actionPerformed(ActionEvent e) {
        buttonrecorder = e.getActionCommand();
        response.setIcon(createImageIcon( e.getActionCommand() + ".jpg"));

    }
    
    
    //creates the ImageIcon, if not found sends error message
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = BasePanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
   
    //Creates and displays the JFrame
    public void displaywindow(){
        
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new BasePanel(list);
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }
 
    //display method displays the window
    public void display() {

        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() {
                displaywindow();
            }
        });
    }
    
    /*
     * returnChoice method 
     * returns the String buttonrecorder
     * this will be used later to save the choice made by this panel
     * other panels also have a buttonrecorder that will save the choice made
     * 
     */
    public String returnChoice()
    {
        return buttonrecorder;
    }
}