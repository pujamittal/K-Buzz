
/**
 * Write a description of class BasePanelTestClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasePanelTestClass
{
    public static void main(String args[])
    {
        String[] jpg = new String[5];
        jpg = new String[]{"1","2","3","4","5","banner"};
        BasePanel test = new BasePanel(jpg);
        test.display();
    }
}
