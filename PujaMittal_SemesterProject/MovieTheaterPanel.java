import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * This class creates a GUI for the user to use
 * It asks them to select which Movie they would like, how many tickets, and if they want concessions
 * It stores all of the users selections for later use and has accessor methods to get to them
 * This will be sued with the MovieTheater class to charge and create Orders for customers.
 * 
 * @author  Puja Mittal
 * @version 12/6/2014
 */
public class MovieTheaterPanel extends JFrame implements ActionListener
{

    private JRadioButton k3g, mdk, ddlj, yes, no;
    private JButton finished;
    private Movie k, m, d;
    private JTextField numberOfTickets;
    private JFrame frame;
    private JLabel numTickets, question;
    private JPanel wholeGUI, MA, MB, MC, buttonsA, inputBar, buttonsB;
    private ButtonGroup movieType, foods, single;
    private int tickets;
    private String movie;
    private boolean foodSelection, isFinished;
    private double price;

    /**
     * Constructor for objects of class Menu
     * This creates various Movie Objects for use on the menu
     * It also creates the GUI  with various buttons and displays it
     */
    public MovieTheaterPanel()
    {
        isFinished = false;
        wholeGUI = new JPanel();
        wholeGUI.setLayout(null);

        k = new Movie("K3G.jpg","Kabhi Khushi Kabhi Gum",3.50,"Drama","14 Dec 2001", 210);
        MA = k.createPanel();
        MA.setLocation(10,10);
        wholeGUI.add(MA);

        m = new Movie("MDK.jpg", "Mujhse Dosti Karoge", 5.00,"Romantic Drama", "9 Aug 2002", 149);
        MB = m.createPanel();
        MB.setLocation(225,10);
        wholeGUI.add(MB);

        d = new Movie("DDLJ.jpg", "Dilwale Dulhania Le Jayenge", 2.00, "Romantic Drama", "20 Oct 1995", 186);
        MC = d.createPanel();
        MC.setLocation(440,10);
        wholeGUI.add(MC);

        buttonsA = new JPanel();
        buttonsA.setLayout(null);
        buttonsA.setLocation(10,320);
        buttonsA.setSize(900,30);
        wholeGUI.add(buttonsA);

        inputBar = new JPanel();
        inputBar.setLayout(null);
        inputBar.setLocation(195,350);
        inputBar.setSize(300,30);
        wholeGUI.add(inputBar);

        buttonsB = new JPanel();
        buttonsB.setLayout(null);
        buttonsB.setLocation(230,380);
        buttonsB.setSize(216,90);
        wholeGUI.add(buttonsB);

        numberOfTickets = new JTextField(2);
        numberOfTickets.setLocation(230,0);
        numberOfTickets.setSize(70,30);
        numberOfTickets.setText("1");
        inputBar.add(numberOfTickets);

        numTickets = new JLabel("How many tickets would you like?", SwingConstants.RIGHT);
        numTickets.setLocation(0, 0);
        numTickets.setSize(225,30);
        numTickets.setForeground(Color.black);
        inputBar.add(numTickets);

        k3g = new JRadioButton("Kabhi Khushi Kabhi Gum");
        k3g.setLocation(0,0);
        k3g.setSize(200,30);

        mdk = new JRadioButton("Mujhse Dosti Karoge");
        mdk.setLocation(215,0);
        mdk.setSize(200,30);

        ddlj = new JRadioButton("Dilwale Dulhania Le Jayenge");
        ddlj.setLocation(430,0);
        ddlj.setSize(200,30);

        k3g.setActionCommand("Kabhi Khushi Kabhi Gum");
        mdk.setActionCommand("Mujhse Dosti Karoge");
        ddlj.setActionCommand("Dilwale Dulhania Le Jayenge");

        k3g.setSelected(true);

        movieType = new ButtonGroup();
        movieType.add(k3g);
        movieType.add(mdk);
        movieType.add(ddlj);

        k3g.addActionListener(this);
        mdk.addActionListener(this);
        ddlj.addActionListener(this);

        buttonsA.add(k3g);
        buttonsA.add(mdk);
        buttonsA.add(ddlj);

        yes = new JRadioButton("Yes");
        yes.setLocation(0,30);
        yes.setSize(50,30);

        no = new JRadioButton("No");
        no.setLocation(50,30);
        no.setSize(40,30);

        yes.setActionCommand("Yes");
        no.setActionCommand("No");

        no.setSelected(true);

        foods = new ButtonGroup();
        foods.add(yes);
        foods.add(no);

        yes.addActionListener(this);
        no.addActionListener(this);

        question = new JLabel("Would you like concessions?", SwingConstants.LEFT);
        question.setLocation(0,0);
        question.setSize(200,30);
        
        buttonsB.add(question);
        buttonsB.add(yes);
        buttonsB.add(no);

        finished = new JButton("Done!");
        finished.setLocation(0,60);
        finished.setSize(70,30);
        finished.addActionListener( this );

        single = new ButtonGroup();
        single.add(finished);
        buttonsB.add(finished);

        wholeGUI.setOpaque(true);

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Welcome to the Bollywood Throwback Theater!");

        frame.setContentPane(wholeGUI);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(670, 510);
        frame.setVisible(true);
    }

    /**
     * Returns a boolean value based on whether the user wanted foods or not
     * 
     * @return  foodSelection    true if yes was sleceted, false if no was selected. No is selected by default. Value is assigned when the finished button is pressed.
     */
    public boolean getConcessions()
    {
        return foodSelection;
    }

    /**
     * Returns the price based on the user selection of movie and the amount of tickets they wanted
     * 
     * @return  price               uses cost assigned to each Movie and number of tickets and returns that value. Price is calculated when the finished button is pressed.
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * Returns the type of Movie that the user entered.
     * 
     * @return  movie                Determined by the Movie which the user selected
     */
    public String getMovie()
    {
        return movie;
    }
    
    /**
     * Returns the number of tickets that the user entered
     * 
     * @return  tickets              the value that the user entered into the program. It is only recorded when the finished button is pressed.
     */
    public int getTickets()
    {
        return tickets;
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
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == no)
        {
            no.setSelected(true);
        }
        else if(e.getSource() == yes)
        {  
            yes.setSelected(true);
        }
        else if(e.getSource() == k3g)
        {
            k3g.setSelected(true);
        }

        else if(e.getSource() == mdk)
        {
            mdk.setSelected(true);
        }

        else if(e.getSource() == ddlj)
        {
            ddlj.setSelected(true);
        }

        else if(e.getSource() == finished)
        {
            isFinished = true;
            tickets = Integer.parseInt(numberOfTickets.getText());
            if(no.isSelected())
            {
                foodSelection = false;
            }
            else if(yes.isSelected())
            {
                foodSelection = true;
            }
            if(k3g.isSelected())
            {
                movie = "Kabhi Khushi Kabhi Gum";
                price = tickets * 3.5;
            }
            else if(mdk.isSelected())
            {
                movie = "Mujhse Dosti Karoge";
                price = tickets * 5.0;
            }
            else if(ddlj.isSelected())
            {
                movie = "Dilwale Dulhania Le Jayenge";
                price = tickets * 2.00;
            }
            frame.setVisible(false);
        }
    }
}
