
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinnot
 */
public class MonthView extends JPanel{

    public static final int DAYS_IN_A_WEEK = 7;
    public static final int WEEKS_IN_A_MONTH = 5;
    
    public static final int TOP_HEIGHT = 20;
    public static final int MIDDLE_HEIGHT = 5;
    
    private JPanel m_top_panel;
    private JPanel m_middle_panel;
    private JPanel m_bottom_panel;
    private int m_month;
    private int m_year;
    
    public MonthView() {
        m_month = Data.getCurrentMonth();
        m_year = Data.getCurrentYear();
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        
        m_top_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        m_top_panel.setBackground(Color.yellow);
        m_middle_panel = new JPanel(new GridLayout(1, MonthView.DAYS_IN_A_WEEK));
        m_middle_panel.setBackground(Color.green);
        m_bottom_panel = new JPanel(new GridLayout(MonthView.WEEKS_IN_A_MONTH, MonthView.DAYS_IN_A_WEEK));
        m_bottom_panel.setBackground(Color.black);
        
        c.ipady = MonthView.TOP_HEIGHT;
        add(m_top_panel, c);
        c.ipady = MonthView.MIDDLE_HEIGHT;
        c.gridy++;
        add(m_middle_panel, c);
        c.ipady = 0;
        c.weighty = 1;
        c.gridy++;
        c.fill = GridBagConstraints.BOTH;
        add(m_bottom_panel, c);
        
        renderMV();
    }
    
    public void renderMV() {
        //remove components
        m_middle_panel.removeAll();
        m_bottom_panel.removeAll();
        
        //setting the date
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, m_month);
        cal.set(Calendar.YEAR, m_year);
        
        //finding the first monday
        while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        
        //putting the panels in bottom_panel and days in middle_panel
        for(int i = 0; i < MonthView.WEEKS_IN_A_MONTH; i++) {
            for(int j = 0; j < MonthView.DAYS_IN_A_WEEK; j++) {
                if(i == 0) {
                    m_middle_panel.add(new JLabel(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale(Data.getSettings().get("locale")))));
                }
                m_bottom_panel.add(new DayPanel(cal, m_month));
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        
    }
    
}
