
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinnot
 */
public class CalendarPanel extends JPanel{
    public static final int MONTH_VIEW = 0;
    public static final int WEEK_VIEW = 1;
    public static final int DAY_VIEW = 2;
    
    public static final int LEFT_WIDTH = 150;
    
    private int m_view;
    private JPanel m_left_panel;
    private JPanel m_right_panel;
    
    public CalendarPanel() {
        m_view = CalendarPanel.MONTH_VIEW;
        setLayout(new GridBagLayout());
        m_left_panel = new JPanel();
        m_left_panel.setBackground(Color.red);
        initLeftPanel();
        m_right_panel = new MonthView();
        m_right_panel.setBackground(Color.blue);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0;
        c.weighty = 1;
        c.ipadx = CalendarPanel.LEFT_WIDTH;
        add(m_left_panel, c);
        c.ipadx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        add(m_right_panel, c);
    }
    
    private void initLeftPanel() { //init left panel, mini calendar | etc
        m_left_panel.add(new Button("Btnasd"));
    }
}
