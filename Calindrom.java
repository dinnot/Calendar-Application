
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinnot
 */
public class Calindrom extends JFrame{

    private CalendarPanel m_calendar;
    private AddressBookPanel m_addressbook;
    
    public static final int MIN_WIDTH = 800;
    public static final int MIN_HEIGHT = 600;
    
    public Calindrom(String title) throws HeadlessException, Exception {
        super(title);
        Data.init();
        m_calendar = new CalendarPanel();
        m_addressbook = new AddressBookPanel();
        getContentPane().add(m_calendar);
        setMinimumSize(new Dimension(Calindrom.MIN_WIDTH, Calindrom.MIN_HEIGHT));
    }
    
    public static void main(String args[]) {
        Calindrom app;
        try {
            app = new Calindrom("Calindrom");
            app.setVisible(true);
        app.pack();
        } catch (HeadlessException ex) {
            Logger.getLogger(Calindrom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Calindrom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
