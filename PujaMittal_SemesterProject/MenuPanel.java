import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * This class creates a Menu GUI for the use to use
 * It asks them to select which Concessions they would like
 * It stores all of the users selections for later use and has accessor methods to get to them
 * This will be used with the MovieTheater class to charge and create Orders for customers.
 * 
 * @author  Puja Mittal 
 * @version 12/6/2014
 */
public class MenuPanel extends JFrame implements ItemListener
{

    private JCheckBox soda, popcorn, candy, finished;
    private Menu ca, ch, s;
    private JFrame frame;
    private JPanel wholeGUI, CA, CB, CC, buttons;
    private ButtonGroup menus;
    private double price;
    private boolean isFinished;

    /**
     * Constructor for objects of class Menu
     * This creates various menus Objects for use on the menu
     * It also creates the GUI  with various buttons and displays it
     */
    public MenuPanel()
    {
        isFinished = false;
        
        wholeGUI = new JPanel();
        wholeGUI.setLayout(null);

        ch = new Menu("soda.jpg","Soda",1.25);
        CA = ch.createPanel();
        CA.setLocation(10,10);
        wholeGUI.add(CA);

        ca = new Menu("popcorn.jpg", "Popcorn", 3.00);
        CB = ca.createPanel();
        CB.setLocation(225,10);
        wholeGUI.add(CB);

        s = new Menu("candy.jpg", "Candy", 1.25);
        CC = s.createPanel();
        CC.setLocation(440,10);
        wholeGUI.add(CC);

        buttons = new JPanel();
        buttons.setLayout(null);
        buttons.setLocation(222,320);
        buttons.setSize(300,30);
        wholeGUI.add(buttons);

        popcorn = new JCheckBox("Soda");
        popcorn.setLocation(0,0);
        popcorn.setSize(100,30);

        soda = new JCheckBox("Popcorn");
        soda.setLocation(100,0);
        soda.setSize(100,30);

        candy = new JCheckBox("Candy");
        candy.setLocation(200,0);
        candy.setSize(100,30);

        popcorn.addItemListener(this);
        soda.addItemListener(this);
        candy.addItemListener(this);

        buttons.add(popcorn);
        buttons.add(soda);
        buttons.add(candy);

        finished = new JCheckBox("Done!");
        finished.setLocation(320,370);
        finished.setSize(70,30);
        finished.addItemListener( this );
        wholeGUI.add(finished);

        wholeGUI.setOpaque(true);

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Welcome to Concessions!");

        frame.setContentPane(wholeGUI);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(670, 440);
        frame.setVisible(true);
    }

    /**
     * Returns the price based on the user selection of concessions
     * 
     * @return  price               uses cost assigned to each food item and returns that value. Price is calculated when the finished button is pressed.
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Returns whether the user has finished using the menu 
     * 
     * @return  isFinished          true if the user has finished using the menu. 
     */
    public boolean checkMenu()
    {
        return isFinished;
    }
    
    /**
     * Handles all the buttons for the class and preforms various actions based on which one is pressed. 
     * When a radio button is pressed, it is selected 
     * When the finished button is pressed all the values are stored and the GUI disappears.
     * 
     * @param   e   The Action Event when the mouse is pressed. The method checks the source of e to find out which button was pressed.
     */
    public void itemStateChanged(ItemEvent e)
    {
        Object source = e.getItemSelectable();
        if(source == popcorn)
        {
            if(e.getStateChange() == ItemEvent.DESELECTED)
            {
                popcorn.setSelected(false);
            }
            else
            {
                popcorn.setSelected(true);
            }
        }

        else if(source == soda)
        {
            if(e.getStateChange() == ItemEvent.DESELECTED)
            {
                soda.setSelected(false);
            }
            else
            {
                soda.setSelected(true);
            }
        }

        else if(source == candy)
        {
            if(e.getStateChange() == ItemEvent.DESELECTED)
            {
                candy.setSelected(false);
            }
            else
            {
                candy.setSelected(true);
            }
        }

        else if(source == finished)
        {
            price = 0.0;
            isFinished = true;
            if(popcorn.isSelected())
            {
                price += 1.25;
            }
            if(soda.isSelected())
            {
                price += 3.00;
            }
            if(candy.isSelected())
            {
                price += 1.25;
            }
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
            frame.setVisible(false);
            frame.dispose();
        }
    }
}
