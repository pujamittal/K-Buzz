import java.awt.*;
import javax.swing.*;

/**
 * Takes in multiple parameters (String, String, Double) and associates them all together, this class then has
 * various methods which will return this information after doing any processing if necessary.
 * 
 * @author Puja Mittal
 * @version 12/6/2014
 */
public class Menu
{
    // instance variables - replace the example below with your own
    private ImageIcon image;
    private String name;
    private double cost;
    private JPanel frame;
    private JLabel costP;
   
    /**
     * Constructor for objects of class Menu
     * Takes in all three of the parameters and rounds the cost to two decimal places, since the Movie Theater uses dollars
     * Additionally it creates a frame and resizes the image so that it is 200 x 200
     * @param   imageLocation   The file name of the image which will be used to create an imageIcon
     * @param   name            The name of the Food item which is created
     * @param   cost            The cost for each concession
     */
    public Menu(String imageLocation, String name, double cost)
    {
        image = new ImageIcon(imageLocation);
        this.name = name;
        this.cost = Math.round(cost*100.0)/100.0;
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
     * Creates the panel for each Concession that is created.
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
        
        JLabel type = new JLabel("Type: "+ name);
        type.setLocation(5,200);
        type.setSize(210,30);
        frame.add(type);
        
        if((""+cost).length() == 4)
        {
            costP = new JLabel("Additional Cost: $"+cost);
        }
        else
        {
            costP = new JLabel("Additional Cost: $" + cost + "0");
        }
        costP.setLocation(5,230);
        costP.setSize(210,30);
        frame.add(costP);
        
        return frame;
    }
}
