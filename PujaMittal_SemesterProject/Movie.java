import java.awt.*;
import javax.swing.*;

/**
 * Takes in multiple parameters (String, String, Double) and associates them all together, this class then has
 * various methods which will return this information after doing any processing if necessary.
 * 
 * @author Puja Mittal 
 * @version 12/6/2014
 */
public class Movie
{
    // instance variables - replace the example below with your own
    private ImageIcon image;
    private String name, genre, date;
    private int runtime;
    private double costPer;
    private JPanel frame;
    private JLabel cost;
   
    /**
     * Constructor for objects of class movieTypes
     * Takes in all three of the parameters and rounds the cost to two decimal places, since the Movie Theater uses dollars
     * Additionally it creates a frame and resizes the image so that it is 200 x 200
     * @param   imageLocation   The file name of the image which will be used to create an imageIcon
     * @param   name            The name of the movie which is created
     * @param   cost            The cost for each ticket
     */
    public Movie(String imageLocation, String name, double cost, String genre, String date, int runtime)
    {
        image = new ImageIcon(imageLocation);
        this.name = name;
        this.genre = genre;
        this.date = date;
        this.runtime = runtime;
        costPer = Math.round(cost*100.0)/100.0;
        frame = new JPanel();
        convert();
    }

    /**
     * Converts the ImageIcon into a much smaller size
     */
    private void convert()
    {
        Image img = image.getImage();
        img = img.getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(img);
    }
    
    /**
     * Creates the panel for each Movie Object that is created.
     * The size of each panel is 210 * 300
     * 
     * @return frame    The frame that contains the name, cost, and image of the Movie Object
     */
    public JPanel createPanel()
    {
        frame.setLayout(null);
        frame.setSize(210,300);
        
        JLabel picture = new JLabel(image);
        picture.setLocation(5,0);
        picture.setSize(200,200);
        frame.add(picture);
        
        JLabel type = new JLabel("Movie Title: "+ name);
        type.setLocation(5,200);
        type.setSize(210,15);
        frame.add(type);
        
        if((""+costPer).length() == 4)
        {
            cost = new JLabel("Cost Per Ticket: $"+costPer);
        }
        else
        {
            cost = new JLabel("Cost Per Ticket: $" + costPer + "0");
        }
        cost.setLocation(5,215);
        cost.setSize(210,15);
        frame.add(cost);
        
        JLabel jenre = new JLabel("Genre: "+ genre);
        jenre.setLocation(5,230);
        jenre.setSize(210,15);
        frame.add(jenre);
        
        JLabel release = new JLabel("Release Date: "+ date);
        release.setLocation(5,245);
        release.setSize(210,15);
        frame.add(release);
        
        JLabel time = new JLabel(runtime + " minutes");
        time.setLocation(5,260);
        time.setSize(210,15);
        frame.add(time);
        
        return frame;
    }
}
