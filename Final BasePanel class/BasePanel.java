

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
                             
    //saves the last button used so that it can be used later in determining the result. buttonrecorder saves the file tag of the response chosen
    public String buttonrecorder;
    //there's only one response being displayed at a time, so there is only one JLabel, this is the preview
    JLabel response;
    //static question banner
    JLabel question;
    
    public boolean doneYet;
    //JLabel instructions; //instructions if things get weird with stringing all the panels together
    public String[] list;
    static JButton okay;
    static JFrame frame;
 
    /*
    *The constructor creates the panel itself. takes an array of strings and uses the array size to determine the amount of buttons. Array is the file names for icons/question banner
    *
    *no return
    */
    public BasePanel(String[] jpg) {
        super(new BorderLayout());
        doneYet = false;
        //if no button is pressed yet, string is set to null
        buttonrecorder = "Start";
        list = jpg;
        int last = list.length;
       //now I create the radio buttons... there is one less than the size of array bc the last file is the question banner
        JRadioButton ans[] = new JRadioButton[last-1];
        for (int i = 0; i < last-1; i++)
        {
            ans[i] = new JRadioButton(list[i]);
            ans[i].setMnemonic(KeyEvent.VK_A);
            ans[i].setActionCommand(list[i]);
        }
        ans[0].setSelected(true);
        
        //creating the "okay" button
        okay  = new JButton("Okay");
        //putting all the buttons in one group to display
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < last-1; i++)
        {
            group.add(ans[i]);
        }
        group.add(okay);
        //action listeners for the radio buttons... sets buttonrecorder whenever a button is pressed
        for (int i = 0; i < last-1; i++)
        {
            ans[i].addActionListener(this);
        }
        //the default image is the list[0] template and the image at the top is a static picture of the question
        question = new JLabel(createImageIcon(list[last-1] + ".jpg"));
        question.setPreferredSize(new Dimension(400, 100));
        response = new JLabel(createImageIcon(list[0] + ".jpg"));
        response.setPreferredSize(new Dimension(400, 300));

        //Puts all the buttons on the panel
        JPanel panel = new JPanel(new GridLayout(6, 4));
        for (int i = 0; i < last-1; i++)
        {
            panel.add(ans[i]);
        }
        panel.add(okay);
        frame = new JFrame("BasePanel");
        JPanel picpanel = new JPanel(new GridLayout(7, 5));
        //when the okay button is pressed, doneYet is set as true, locking in the choice, even if buttons are pressed or if ok button is pressed.Frame becomes invisible.
        okay.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent t) {
            doneYet = true;
            for(int i = 0; i < last-1; i++)
            {
                if (ans[i].isSelected()== true) buttonrecorder = list[i];
            }
            frame.setVisible(false);
        }
        });
        //adds everything to the panel... question banner is at top and answer icon changes at the side
        add(panel, BorderLayout.WEST);
        add(question,BorderLayout.NORTH);
        add(response, BorderLayout.EAST);
    }
    /*
     * method actionPerformed() changes icon for the corresponding button
     */ 
    public void actionPerformed(ActionEvent e) {
        buttonrecorder = e.getActionCommand();
        response.setIcon(createImageIcon( buttonrecorder + ".jpg"));
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
        
        JComponent newContentPane = this;
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