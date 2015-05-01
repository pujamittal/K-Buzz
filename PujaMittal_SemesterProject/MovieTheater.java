import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class MovieTheater here.
 * 
 * @author  Puja Mittal 
 * @version 12/6/2014
 */
public class MovieTheater
{

    public static void main(String[] args)
    {
        double totalCost = 0.0;
        String totalOrder = "";
        int n = JOptionPane.YES_OPTION;
        JOptionPane.showMessageDialog(new Frame(),"Hi! Welcome to the Bollywood Throwback Theater! \nWhat movie would you like to see?","Hello",JOptionPane.PLAIN_MESSAGE);
        do{
            MovieTheaterPanel firstMenu = new MovieTheaterPanel();
            totalCost += firstMenu.getPrice();
            while(!firstMenu.checkMenu())
            {
                try
                {
                    Thread.sleep(200);
                }
                catch(InterruptedException ex) 
                {
                    Thread.currentThread().interrupt();
                }
            }
            totalOrder += "" + firstMenu.getTickets() + " tickets for " + firstMenu.getMovie();                
            if(firstMenu.getConcessions())
            {
                totalOrder += " with Concessions";
                MenuPanel secondMenu = new MenuPanel();
                while(!secondMenu.checkMenu())
                {
                    try
                    {
                        Thread.sleep(200);
                    }
                    catch(InterruptedException ex) 
                    {
                        Thread.currentThread().interrupt();
                    }
                }
                totalCost += secondMenu.getPrice();
            }
            totalOrder += "\n";
            totalCost += firstMenu.getPrice();
            Object[] options = {"Yes","No"};
            n = JOptionPane.showOptionDialog(new Frame(),"Would you like to purchase more tickets?","Would you like more?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
        }while(n == JOptionPane.YES_OPTION);
        JOptionPane.showMessageDialog(new Frame(),"Your order is: \n" + totalOrder +"\nIt will cost you $" + totalCost,"Order Summary",JOptionPane.PLAIN_MESSAGE);
    }
}
